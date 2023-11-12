package org.cs511.datasource;


import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.*;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;


public class DummyAvroDataSource extends RichSourceFunction<DummyAvro> {
    private boolean running = true;

    @Override
    public void run(SourceContext<DummyAvro> sourceContext) throws Exception {
        Object datasetObj = new JSONParser().parse(new FileReader("ImdbTitleRatings.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();
        while (itr.hasNext() && this.running){
            JSONObject dataLine = itr.next();
            DummyAvro avroObj = new DummyAvro();

            avroObj.setTconst(dataLine.get("tconst"));
            avroObj.setAverageRating(dataLine.get("averageRating"));
            avroObj.setNumVotes(dataLine.get("numVotes"));

            sourceContext.collect(avroObj);
        }

        this.running = false;
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
