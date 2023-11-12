package org.cs511.datasource;


import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.*;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvroMedium;


public class DummyAvroDataSource extends RichSourceFunction<DummyAvroMedium> {
    private boolean running = true;

    @Override
    public void run(SourceContext<DummyAvroMedium> sourceContext) throws Exception {
        Object datasetObj = new JSONParser().parse(new FileReader("steam.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        while (itr.hasNext() && this.running){
            JSONObject dataLine = itr.next();
            DummyAvroMedium avroObj = new DummyAvroMedium();  // level 1

            avroObj.setDate(dataLine.get("date"));
            avroObj.setDeveloper(dataLine.get("developer"));
            avroObj.setPublisher(dataLine.get("publisher"));

            DummyAvroMediumFullDesc avroFullDesc = new DummyAvroMediumFullDesc();  // level 2
            JSONObject dataLineFullDesc = dataLine.get("full_desc");
            avroFullDesc.setSort(dataLineFullDesc.get("sort"));
            avroFullDesc.setDesc(dataLineFullDesc.get("desc"));

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
