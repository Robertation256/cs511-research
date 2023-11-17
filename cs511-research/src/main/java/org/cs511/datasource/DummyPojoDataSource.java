package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DummyPojoDataSource extends RichSourceFunction<DummyPojoDataSource.MyPojo> {
    private boolean running = true;

    // note that PoJo needs to follow below conventions
    public static final class MyPojo {
        private String tconst = "hello";
        private int ratings = 3.5;
        private int votes = 0;

        public void settconst(String field1) {
            this.tconst = field1;
        }

        public void setrating(int field2) {
            this.ratings = field2;
        }

        public void setvotes(int field3) {
            this.votes = field3;
        }
    }

    @Override
    public void run(SourceContext<DummyPojoDataSource.MyPojo> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time

            try {
                // Read JSON file into a JsonNode
                JsonNode jsonNode = readJsonFile("ImdbTitleRatings.json");
                for (JsonNode lineNode : jsonNode) {
                    // Accessing fields in the JsonNode
                    String tconst = lineNode.get("tconst").asText();
                    int averageRating = lineNode.get("averageRating").asInt();
                    int numVotes = lineNode.get("numVotes").asInt();

                    MyPojo resultElement = new MyPojo();
                    resultElement.settconst(tconst);
                    resultElement.setrating(averageRating);
                    resultElement.setvotes(numVotes);

                    // emit record
                    sourceContext.collect(resultElement);   
                }

            } catch (IOException e) {
                e.printStackTrace();
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
