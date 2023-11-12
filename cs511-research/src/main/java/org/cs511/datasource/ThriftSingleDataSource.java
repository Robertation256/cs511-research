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

            // insert code for parsing file into records and emit one at a time
            JsonNode jsonNode = readJsonFile("ImdbTitleRatings.json");
            for (JsonNode lineNode : jsonNode) {
                // Accessing fields in the JsonNode
                String tconst = lineNode.get("tconst").asText();
                String averageRating = lineNode.get("averageRating").asText();

                SingleThrift thriftObj = new SingleThrift();
                thriftObj.setTconst(tconst);
                thriftObj.setRating(averageRating);
                sourceContext.collect(thriftObj);   // emit record
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
