package org.cs511.datasource;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;
import org.cs511.avro.DummyAvroHigh;


public class DummyAvroDataSource extends RichSourceFunction<DummyAvro> {
    private boolean running = true;

    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

    public DummyAvroDataSource(){}

    public DummyAvroDataSource(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }
    @Override
    public void run(SourceContext<DummyAvro> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/ImdbTitleRatings.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;
   
        Iterator itr = dataLines.iterator();
        List<DummyAvro> data = new ArrayList<>();

        while (itr.hasNext()){
            JSONObject dataLine = (JSONObject) itr.next();
            DummyAvro avroObj = new DummyAvro();

            avroObj.setTconst((String) dataLine.get("tconst"));
            avroObj.setAverageRating((String) dataLine.get("averageRating"));
            avroObj.setNumVotes((String) dataLine.get("numVotes"));

            data.add(avroObj);
        }

        long recordsRemaining = this.recordsPerInvocation;
        while(true){
            for (DummyAvro avroObj: data) {
                if (isInfiniteSource || recordsRemaining > 0) {
                    sourceContext.collect(avroObj);
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
