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
import org.cs511.avro.DummyAvroHighFullDesc;
import org.cs511.avro.DummyAvroHighRequirements;
import org.cs511.avro.DummyAvroHighRequirementsMinimum;
import org.cs511.avro.DummyAvroHighRequirementsMinimumWindows;


// FIXME: https://kapilsreed.medium.com/apache-avro-demystified-66d80426c752
public class DummyAvroDataSourceHigh extends RichSourceFunction<DummyAvroHigh> {
    private boolean running = true;

    @Override
    public void run(SourceContext<DummyAvroHigh> sourceContext) throws Exception {
        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader(getClass().getClassLoader().getResource("datasets/steam.json").getFile()));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        List<DummyAvroHigh> data = new ArrayList<>();
        
        while (itr.hasNext()){
            JSONObject dataLine = (JSONObject) itr.next();
            DummyAvroHigh avroObj = new DummyAvroHigh();  // level 1

            avroObj.setDate((String) dataLine.get("date"));
            avroObj.setDeveloper((String) dataLine.get("developer"));
            avroObj.setPublisher((String) dataLine.get("publisher"));

            DummyAvroHighFullDesc avroFullDesc = new DummyAvroHighFullDesc();  // level 2
            JSONObject dataLineFullDesc = (JSONObject) dataLine.get("full_desc");
            avroFullDesc.setSort((String) dataLineFullDesc.get("sort"));
            avroFullDesc.setDesc((String) dataLineFullDesc.get("desc"));

            DummyAvroHighRequirements avroRequirements = new DummyAvroHighRequirements();  // level 2
            JSONObject dataLineRequirements = (JSONObject) dataLine.get("requirements");
            JSONObject dataLineRequirementsMinimum = (JSONObject) dataLineRequirements.get("minimum");
            JSONObject dataLineRequirementsMinimumWindows = (JSONObject) dataLineRequirementsMinimum.get("windows");

            DummyAvroHighRequirementsMinimum avroRequirementsMinimum = new DummyAvroHighRequirementsMinimum();  // level 3
            DummyAvroHighRequirementsMinimumWindows avroRequirementsMinimumWindows = new DummyAvroHighRequirementsMinimumWindows();  // level 4

            avroRequirementsMinimumWindows.setProcessor((String) dataLineRequirementsMinimumWindows.get("processor"));
            avroRequirementsMinimumWindows.setMemory((String) dataLineRequirementsMinimumWindows.get("memory"));
            avroRequirementsMinimumWindows.setGraphics((String) dataLineRequirementsMinimumWindows.get("graphics"));
            avroRequirementsMinimumWindows.setOs((String) dataLineRequirementsMinimumWindows.get("os"));

            avroRequirementsMinimum.setWindows(avroRequirementsMinimumWindows);
            avroRequirements.setMinimum(avroRequirementsMinimum);
            avroObj.setFullDesc(avroFullDesc);
            avroObj.setRequirements(avroRequirements);

            data.add(avroObj);
        }

         while(true){
            for (DummyAvroHigh avroObj: data) {
                sourceContext.collect(avroObj);
            }
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
