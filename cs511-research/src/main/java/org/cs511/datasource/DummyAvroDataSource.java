package org.cs511.datasource;


import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.avro.DummyAvro;


public class DummyAvroDataSource extends RichSourceFunction<DummyAvro> {
    private boolean running = true;


    @Override
    public void run(SourceContext<DummyAvro> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time


            DummyAvro avroObj = new DummyAvro();
            avroObj.setId(1);
            avroObj.setName("Hello");
            sourceContext.collect(avroObj);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
