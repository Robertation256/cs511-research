package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class TupleSingleDataSource extends RichSourceFunction<Tuple2<String, String>> {
    private boolean running = true;

    @Override
    public void run(SourceContext<Tuple2<String, String>> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/ImdbTitleRatings.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();
            String tconst = (String) lineNode.get("tconst");
            String averageRating = (String) lineNode.get("averageRating");

            Tuple2<String, String> resultElement = new Tuple2<>(tconst, averageRating);
            sourceContext.collect(resultElement);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}