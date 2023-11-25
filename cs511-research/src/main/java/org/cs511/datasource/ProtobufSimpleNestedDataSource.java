package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;
import org.cs511.proto.SimpleProto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProtobufSimpleNestedDataSource extends RichSourceFunction<SimpleProto.simple_proto> {
    private boolean running = true;

    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

    public ProtobufSimpleNestedDataSource(){}

    public ProtobufSimpleNestedDataSource(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }


    @Override
    public void run(SourceContext<SimpleProto.simple_proto> sourceContext) throws Exception {

        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/steam.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<SimpleProto.simple_proto> data = new ArrayList<>();

        while (itr.hasNext()){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();
            // Accessing fields in the JsonNode
            String date = (String) lineNode.get("date");
            String developer = (String) lineNode.get("developer");
            String publisher = (String) lineNode.get("publisher");

            JSONObject desc_obj = (JSONObject) lineNode.get("full_desc");
            String sort = (String) desc_obj.get("sort");
            String desc = (String) desc_obj.get("desc");
            SimpleProto.full_desc desc_ = SimpleProto.full_desc.newBuilder()
                    .setSort(sort)
                    .setDesc(desc)
                    .build();

            SimpleProto.simple_proto resultElement = SimpleProto.simple_proto.newBuilder()
                    .setDate(date)
                    .setDeveloper(developer)
                    .setPublisher(publisher)
                    .setD(desc_)
                    .build();

            data.add(resultElement);
        }

        long recordsRemaining = this.recordsPerInvocation;
        while(true){
            for (SimpleProto.simple_proto protoObj: data) {
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