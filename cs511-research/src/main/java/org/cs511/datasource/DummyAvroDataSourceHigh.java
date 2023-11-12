package org.cs511.datasource;


import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.*;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvroHigh;


// FIXME: https://kapilsreed.medium.com/apache-avro-demystified-66d80426c752
public class DummyAvroDataSource extends RichSourceFunction<DummyAvroHigh> {
    private boolean running = true;

    @Override
    public void run(SourceContext<DummyAvroHigh> sourceContext) throws Exception {
        Object datasetObj = new JSONParser().parse(new FileReader("steam.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        while (itr.hasNext() && this.running){
            JSONObject dataLine = itr.next();
            DummyAvroHigh avroObj = new DummyAvroHigh();  // level 1

            avroObj.setDate(dataLine.get("date"));
            avroObj.setDeveloper(dataLine.get("developer"));
            avroObj.setPublisher(dataLine.get("publisher"));

            DummyAvroHighFullDesc avroFullDesc = new DummyAvroHighFullDesc();  // level 2
            JSONObject dataLineFullDesc = dataLine.get("full_desc");
            avroFullDesc.setSort(dataLineFullDesc.get("sort"));
            avroFullDesc.setDesc(dataLineFullDesc.get("desc"));

            DummyAvroHighRequirements avroRequirements = new DummyAvroHighRequirements();  // level 2
            JSONObject dataLineRequirements = dataLine.get("requirements");
            JSONObject dataLineRequirementsMinimum = dataLineRequirements.get("minimum");
            JSONObject dataLineRequirementsMinimumWindows = dataLineRequirementsMinimum.get("windows");

            DummyAvroHighRequirementsMinimum avroRequirementsMinimum = new DummyAvroHighRequirementsMinimum();  // level 3
            DummyAvroHighRequirementsMinimumWindows avroRequirementsMinimumWindows = new DummyAvroHighRequirementsMinimumWindows();  // level 4

            avroRequirementsMinimumWindows.setProcessor(dataLineRequirementsMinimumWindows.get("processor"));
            avroRequirementsMinimumWindows.setMemory(dataLineRequirementsMinimumWindows.get("memory"));
            avroRequirementsMinimumWindows.setGraphics(dataLineRequirementsMinimumWindows.get("graphics"));
            avroRequirementsMinimumWindows.setOs(dataLineRequirementsMinimumWindows.get("os"));

            avroRequirementsMinimum.setWindows(avroRequirementsMinimumWindows);
            avroRequirements.setMinimum(avroRequirementsMinimum);
            avroObj.setFullDesc(avroFullDesc);
            avroObj.setRequirements(avroRequirements);

            sourceContext.collect(avroObj);

            this.running = false;
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
