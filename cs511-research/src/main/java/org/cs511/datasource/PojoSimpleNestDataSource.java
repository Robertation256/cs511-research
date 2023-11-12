package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DummyPojoDataSource extends RichSourceFunction<DummyPojoDataSource.MyPojo> {
    private boolean running = true;

    public static final class Desc {
        private String sort = "hello";
        private String desc = "3.5";

        public void setsort(String field1) {
            this.sort = field1;
        }

        public void setdesc(String field2) {
            this.desc = field2;
        }
    }

    // note that PoJo needs to follow below conventions
    public static final class MyPojo {
        private String date = "hello";
        private String developer = "3.5";
        private String publisher = "3.5";
        private Desc d = new Desc();

        public void setdate(String field1) {
            this.date = field1;
        }

        public void setdeveloper(String field2) {
            this.developer = field2;
        }

        public void setpublisher(String field3) {
            this.publisher = field3;
        }

        public void setdesc(Desc field4) {
            this.d = field4;
        }
    }

    @Override
    public void run(SourceContext<DummyPojoDataSource.MyPojo> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time

            try {
                // Read JSON file into a JsonNode
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
                    desc_.setsort(sort)
                    desc_.setdesc(desc)

                    MyPojo resultElement = new MyPojo();
                    resultElement.setdate(date);
                    resultElement.setdeveloper(developer);
                    resultElement.setpublisher(publisher);
                    resultElement.setdesc(desc_)

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
