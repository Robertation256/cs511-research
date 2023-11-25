package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;

import java.io.File;
import java.io.IOException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PojoSimpleNestDataSource extends RichSourceFunction<PojoSimpleNestDataSource.MyPojo> {
    private boolean running = true;

    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

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

    public PojoSimpleNestDataSource(){}

    public PojoSimpleNestDataSource(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }

    @Override
    public void run(SourceContext<PojoSimpleNestDataSource.MyPojo> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/steam.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<MyPojo> data = new ArrayList<>();

        while (itr.hasNext()){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            // Accessing fields in the JsonNode
            String date = (String) lineNode.get("date");
            String developer = (String) lineNode.get("developer");
            String publisher = (String) lineNode.get("publisher");

            JSONObject desc_obj = (JSONObject) lineNode.get("full_desc");
            String sort = (String) desc_obj.get("sort");
            String desc = (String) desc_obj.get("desc");
            Desc desc_ = new Desc();
            desc_.setsort(sort);
            desc_.setdesc(desc);

            MyPojo resultElement = new MyPojo();
            resultElement.setdate(date);
            resultElement.setdeveloper(developer);
            resultElement.setpublisher(publisher);
            resultElement.setdesc(desc_);

            data.add(resultElement);
        }

        long recordsRemaining = this.recordsPerInvocation;

        while(true){
            for (MyPojo pojo: data) {
                if (isInfiniteSource || recordsRemaining > 0) {
                    sourceContext.collect(pojo);
                    if (!isInfiniteSource){
                        recordsRemaining--;
                    }
                } else {
                    return;
                }
            }
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}