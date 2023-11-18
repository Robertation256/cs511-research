package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.SingleProto;
import org.cs511.thrift.SingleThrift;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ThriftSingleDataSource extends RichSourceFunction<SingleThrift> {
    private boolean running = true;


    @Override
    public void run(SourceContext<SingleThrift> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader("../datasets/ImdbTitleRatings.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            // Accessing fields in the JsonNode
            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            SingleThrift thriftObj = new SingleThrift();
            thriftObj.setTconst(tconst);
            thriftObj.setRating(averageRating);
            sourceContext.collect(thriftObj);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
