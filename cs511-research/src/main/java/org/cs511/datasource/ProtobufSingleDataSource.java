package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.SingleProto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class ProtobufSingleDataSource extends RichSourceFunction<SingleProto.single_proto> {
    private boolean running = true;


    @Override
    public void run(SourceContext<SingleProto.single_proto> sourceContext) throws Exception {

        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader("../datasets/ImdbTitleRatings.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            SingleProto.single_proto proto = SingleProto.single_proto.newBuilder()
                    .setTconst(tconst)
                    .setRating(averageRating)
                    .build();
            sourceContext.collect(proto);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
