package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.SingleProto;
import org.cs511.thrift.SingleThrift;

public class DummyThriftDataSource extends RichSourceFunction<DummyThrift> {
    private boolean running = true;


    @Override
    public void run(SourceContext<DummyThrift> sourceContext) throws Exception {

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
                Desc desc_ = new Desc();
                desc_.setSort(sort)
                desc_.setDesc(desc)

                SimpleThrift resultElement = new SimpleThrift();
                resultElement.setDate(date);
                resultElement.setDeveloper(developer);
                resultElement.setPublisher(publisher);
                resultElement.setD(desc_)

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
