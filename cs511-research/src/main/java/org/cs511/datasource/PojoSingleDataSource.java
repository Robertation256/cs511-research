package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.io.File;
import java.io.IOException;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PojoSingleDataSource extends RichSourceFunction<PojoSingleDataSource.MyPojo> {
    private boolean running = true;

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

    @Override
    public void run(SourceContext<PojoSingleDataSource.MyPojo> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/ImdbTitleRatings.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();

            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            MyPojo resultElement = new MyPojo();
            resultElement.settconst(tconst);
            resultElement.setrating(averageRating);

            // emit record
            sourceContext.collect(resultElement);
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}