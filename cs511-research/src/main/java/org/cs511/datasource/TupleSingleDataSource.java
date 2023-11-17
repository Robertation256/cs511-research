package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

public class TupleSingleDataSource extends RichSourceFunction<Tuple2<String, String>> {
    private boolean running = true;

    @Override
    public void run(SourceContext<Tuple2<String, String>> sourceContext) throws Exception {

        while (running){
            // parse each line to a  tuples and emits it

            // insert code for parsing file into records and emit one at a time
            JsonNode jsonNode = readJsonFile("ImdbTitleRatings.json");
            for (JsonNode lineNode : jsonNode) {
                // Accessing fields in the JsonNode
                String tconst = lineNode.get("tconst").asText();
                String averageRating = lineNode.get("averageRating").asText();

                Tuple2<String, String> resultElement = new Tuple2<>(tconst, averageRating);
                sourceContext.collect(resultElement);   // emit record
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
