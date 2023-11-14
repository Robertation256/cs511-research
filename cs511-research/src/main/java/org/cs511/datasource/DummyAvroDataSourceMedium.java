package org.cs511.datasource;


import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvroMedium;


public class DummyAvroDataSourceMedium extends RichSourceFunction<DummyAvroMedium> {
    private boolean running = true;

    @Override
    public void run(SourceContext<DummyAvroMedium> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader("../datasets/steam.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        while (itr.hasNext() && this.running){
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

            sourceContext.collect(avroObj);
        }

        this.running = false;
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
