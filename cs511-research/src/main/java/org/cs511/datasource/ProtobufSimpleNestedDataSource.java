package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.SimpleProto;

public class ProtobufSimpleNestedDataSource extends RichSourceFunction<DummyProto.dummy_proto> {
    private boolean running = true;


    @Override
    public void run(SourceContext<DummyProto.dummy_proto> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo
            JsonNode jsonNode = readJsonFile("steam.json");
            for (JsonNode lineNode : jsonNode) {
                // Accessing fields in the JsonNode
                String date = lineNode.get("date").asText();
                String developer = lineNode.get("developer").asText();
                String publisher = lineNode.get("publisher").asText();

                JsonNode desc_obj = lineNode.get("full_desc");
                String sort = desc_obj.get("sort").asText();
                String desc = desc_obj.get("desc").asText();
                SimpleProto.full_desc desc_ = SimpleProto.full_desc.newBuilder()
                        .setSort(sort)
                        .setDesc(desc)
                        .build();

                SimpleProto.simple_proto resultElement = SimpleProto.simple_proto.newBuilder()
                        .setDate(date)
                        .setDeveloper(developer)
                        .setPublisher(publisher)
                        .setD(desc_);

                // emit record
                sourceContext.collect(resultElement);   
            }
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }

    private static JsonNode readJsonFile(String jsonFilePath) throws IOException {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON file into a JsonNode
        return objectMapper.readTree(new File(jsonFilePath));
    }
}
