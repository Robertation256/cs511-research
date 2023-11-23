package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.ComplexProto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class ProtobufComplexNestedDataSource extends RichSourceFunction<ComplexProto.complex_proto> {
    private boolean running = true;


    @Override
    public void run(SourceContext<ComplexProto.complex_proto> sourceContext) throws Exception {
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
            ComplexProto.full_desc1 desc_ = ComplexProto.full_desc1.newBuilder()
                    .setSort(sort)
                    .setDesc(desc)
                    .build();

            JSONObject requirement_obj = (JSONObject) lineNode.get("requirements");
            JSONObject minimum_obj = (JSONObject) requirement_obj.get("minimum");
            JSONObject windows_obj = (JSONObject) minimum_obj.get("windows");
            String processor = (String) windows_obj.get("processor");
            String memory = (String) windows_obj.get("memory");
            String graphics = (String) windows_obj.get("graphics");
            String os = (String) windows_obj.get("os");
            ComplexProto.windows win = ComplexProto.windows.newBuilder()
                    .setProcessor(processor)
                    .setMemory(memory)
                    .setGraphics(graphics)
                    .setOs(os)
                    .build();
            ComplexProto.minimum min = ComplexProto.minimum.newBuilder()
                    .setWin(win)
                    .build();
            ComplexProto.requirements req = ComplexProto.requirements.newBuilder()
                    .setMin(min)
                    .build();

            ComplexProto.complex_proto resultElement = ComplexProto.complex_proto.newBuilder()
                    .setDate(date)
                    .setDeveloper(developer)
                    .setPublisher(publisher)
                    .setD(desc_)
                    .setR(req)
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