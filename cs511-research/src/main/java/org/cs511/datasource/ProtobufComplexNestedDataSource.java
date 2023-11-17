package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.ComplexProto;

public class ProtobufComplexNestedDataSource extends RichSourceFunction<DummyProto.dummy_proto> {
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
                ComplexProto.full_desc desc_ = ComplexProto.full_desc.newBuilder()
                        .setSort(sort)
                        .setDesc(desc)
                        .build();
                
                JsonNode requirement_obj = lineNode.get("requirements");
                JsonNode minimum_obj = requirement_obj.get("minimum");
                JsonNode windows_obj = minimum_obj.get("windows");
                String processor = windows_obj.get("processor").asText();
                String memory = windows_obj.get("memory").asText();
                String graphics = windows_obj.get("graphics").asText();
                String os = windows_obj.get("os").asText();
                ComplexProto.windows win = ComplexProto.windows.newBuilder()
                        .setProcessor(processor)
                        .setMemory(memory)
                        .setGraphics(graphics)
                        .setOs(os);
                ComplexProto.minimum min = ComplexProto.minimum.newBuilder()
                        .setWin(win);
                ComplexProto.requirements req = ComplexProto.requirements.newBuilder()
                        .setMin(min);

                ComplexProto.complex_proto resultElement = ComplexProto.complex_proto.newBuilder()
                        .setDate(date)
                        .setDeveloper(developer)
                        .setPublisher(publisher)
                        .setD(desc_)
                        .setR(req);

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
