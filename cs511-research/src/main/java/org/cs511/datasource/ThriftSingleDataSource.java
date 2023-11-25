package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;
import org.cs511.thrift.SingleThrift;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThriftSingleDataSource extends RichSourceFunction<SingleThrift> {
    private boolean running = true;


    @Override
    public void run(SourceContext<SingleThrift> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/ImdbTitleRatings.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<SingleThrift> data = new ArrayList<>();

        while (itr.hasNext()){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            // Accessing fields in the JsonNode
            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            SingleThrift thriftObj = new SingleThrift();
            thriftObj.setTconst(tconst);
            thriftObj.setRating(averageRating);

            data.add(thriftObj); 
        }

        while(true){
            for (SingleThrift thriftObj: data) {
                sourceContext.collect(thriftObj);
            }
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}