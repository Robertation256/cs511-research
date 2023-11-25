package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;
import org.cs511.proto.SingleProto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProtobufSingleDataSource extends RichSourceFunction<SingleProto.single_proto> {
    private boolean running = true;

    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

    public ProtobufSingleDataSource(){}

    public ProtobufSingleDataSource(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }


    @Override
    public void run(SourceContext<SingleProto.single_proto> sourceContext) throws Exception {

        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/ImdbTitleRatings.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<SingleProto.single_proto> data = new ArrayList<>();

        while (itr.hasNext()){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            SingleProto.single_proto proto = SingleProto.single_proto.newBuilder()
                    .setTconst(tconst)
                    .setRating(averageRating)
                    .build();
            data.add(proto);  
        }

        long recordsRemaining = this.recordsPerInvocation;
        while(true){
            for (SingleProto.single_proto protoObj: data) {
                if (isInfiniteSource || recordsRemaining > 0) {
                    sourceContext.collect(protoObj);
                    if (!isInfiniteSource){
                        recordsRemaining--;
                    }
                } else {
                    return;
                }
            }
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}