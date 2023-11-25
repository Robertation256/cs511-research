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

public class PojoComplexNestDataSource extends RichSourceFunction<PojoComplexNestDataSource.MyPojo> {
    private boolean running = true;
    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

    public PojoComplexNestDataSource(){}

    public PojoComplexNestDataSource(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }

    public static final class Windows {
        private String processor = "1";
        private String memory = "1";
        private String graphics = "1";
        private String os = "1";

        public void setprocessor(String field1) {
            this.processor = field1;
        }

        public void setmemory(String field1) {
            this.memory = field1;
        }

        public void setgraphics(String field1) {
            this.graphics = field1;
        }

        public void setos(String field1) {
            this.os = field1;
        }
    }

    public static final class Minimum {
        private Windows win = new Windows();

        public void setwin(Windows field1) {
            this.win = field1;
        }
    }

    public static final class Requirements {
        private Minimum min = new Minimum();

        public void setmin(Minimum field1) {
            this.min = field1;
        }
    }

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
        private Requirements r = new Requirements();

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

        public void setrequirement(Requirements field4) {
            this.r = field4;
        }
    }

    @Override
    public void run(SourceContext<PojoComplexNestDataSource.MyPojo> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/steam.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<MyPojo> data = new ArrayList<>();

        while (itr.hasNext()){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();
            // insert code for parsing file into records and emit one at a time

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

            JSONObject requirement_obj = (JSONObject) lineNode.get("requirements");
            JSONObject minimum_obj = (JSONObject) requirement_obj.get("minimum");
            JSONObject windows_obj = (JSONObject) minimum_obj.get("windows");
            String processor = (String) windows_obj.get("processor");
            String memory = (String) windows_obj.get("memory");
            String graphics = (String) windows_obj.get("graphics");
            String os = (String) windows_obj.get("os");
            Windows win = new Windows();
            Minimum min = new Minimum();
            min.setwin(win);
            Requirements req = new Requirements();
            req.setmin(min);

            MyPojo resultElement = new MyPojo();
            resultElement.setdate(date);
            resultElement.setdeveloper(developer);
            resultElement.setpublisher(publisher);
            resultElement.setdesc(desc_);
            resultElement.setrequirement(req);

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