package org.cs511.datasource;


import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;


import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;


public class DummyAvroDataSource extends RichSourceFunction<DummyAvro> {
    private boolean running = true;

    @Override
    public void run(SourceContext<DummyAvro> sourceContext) throws Exception {
        String filename = "ImdbTitleRatings.json";

        // works
        ObjectMapper om = new ObjectMapper();
        InputStream inputStream = DummyAvroDataSource.class.getClassLoader().getResourceAsStream(filename);
        JSONArray dataLines = om.readValue(inputStream, JSONArray.class);
        System.out.println(dataLines.asString());
        // works

        // but maybe try this https://attacomsian.com/blog/jackson-create-json-array



        Iterator itr = dataLines.iterator();
        while (itr.hasNext() && this.running){
            System.out.println("garbage value");
            JSONObject dataLine = (JSONObject) itr.next();  // java treating this as LinkedHashMap
            System.out.println("garbage value 2");
            System.out.println(dataLine.toString());

            DummyAvro avroObj = new DummyAvro();
            avroObj.setTconst((String) dataLine.get("tconst"));
            avroObj.setAverageRating((String) dataLine.get("averageRating"));
            avroObj.setNumVotes((String) dataLine.get("numVotes"));

            sourceContext.collect(avroObj);
        }

        this.running = false;
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}