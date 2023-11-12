package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.SingleProto;

public class DummyProtobufDataSource extends RichSourceFunction<DummyProto.dummy_proto> {
    private boolean running = true;


    @Override
    public void run(SourceContext<DummyProto.dummy_proto> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time
            JsonNode jsonNode = readJsonFile("ImdbTitleRatings.json");
            for (JsonNode lineNode : jsonNode) {
                String tconst = lineNode.get("tconst").asText();
                String averageRating = lineNode.get("averageRating").asText();

                SingleProto.single_proto proto = SingleProto.single_proto.newBuilder()
                        .setTconst(tconst)
                        .setRating(averageRating)
                        .build();
                sourceContext.collect(proto);   // emit record
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
