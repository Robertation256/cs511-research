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

public class PojoSingleDataSource extends RichSourceFunction<PojoSingleDataSource.MyPojo> {
    private boolean running = true;

    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

    // note that PoJo needs to follow below conventions
    public static final class MyPojo {
        private String tconst = "hello";
        private String ratings = "3.5";

        public void settconst(String field1) {
            this.tconst = field1;
        }

        public void setrating(String field2) {
            this.ratings = field2;
        }
    }

    public PojoSingleDataSource(){}

    public PojoSingleDataSource(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }

    @Override
    public void run(SourceContext<PojoSingleDataSource.MyPojo> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/ImdbTitleRatings.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<MyPojo> data = new ArrayList<>();

        while (itr.hasNext()){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            MyPojo resultElement = new MyPojo();
            resultElement.settconst(tconst);
            resultElement.setrating(averageRating);

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