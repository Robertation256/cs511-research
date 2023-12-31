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
import org.cs511.avro.DummyAvroMedium;
import org.cs511.avro.DummyAvroMediumFullDesc;


public class DummyAvroDataSourceMedium extends RichSourceFunction<DummyAvroMedium> {
    private boolean running = true;

    private boolean isInfiniteSource = true;
    private long recordsPerInvocation = 0L;

    public DummyAvroDataSourceMedium(){}

    public DummyAvroDataSourceMedium(long recordsPerInvocation){
        this.recordsPerInvocation = recordsPerInvocation;
        this.isInfiniteSource = false;
    }

    @Override
    public void run(SourceContext<DummyAvroMedium> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/steam.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<DummyAvroMedium> data = new ArrayList<>();

        while (itr.hasNext()){
            JSONObject dataLine = (JSONObject) itr.next();
            DummyAvroMedium avroObj = new DummyAvroMedium();  // level 1

            avroObj.setDate((String) dataLine.get("date"));
            avroObj.setDeveloper((String) dataLine.get("developer"));
            avroObj.setPublisher((String) dataLine.get("publisher"));

            DummyAvroMediumFullDesc avroFullDesc = new DummyAvroMediumFullDesc();  // level 2
            JSONObject dataLineFullDesc = (JSONObject) dataLine.get("full_desc");
            avroFullDesc.setSort((String) dataLineFullDesc.get("sort"));
            avroFullDesc.setDesc((String) dataLineFullDesc.get("desc"));

            avroObj.setFullDesc(avroFullDesc);

            data.add(avroObj);
        }

        long recordsRemaining = this.recordsPerInvocation;
        while(true){
            for (DummyAvroMedium avroObj: data) {
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