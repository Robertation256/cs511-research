package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.SimpleProto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class ProtobufSimpleNestedDataSource extends RichSourceFunction<SimpleProto.simple_proto> {
    private boolean running = true;


    @Override
    public void run(SourceContext<SimpleProto.simple_proto> sourceContext) throws Exception {

        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/steam.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
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

            // emit record
            sourceContext.collect(resultElement);
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}