package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.DummyProto;

public class DummyProtobufDataSource extends RichSourceFunction<DummyProto.dummy_proto> {
    private boolean running = true;


    @Override
    public void run(SourceContext<DummyProto.dummy_proto> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time


            DummyProto.dummy_proto proto = DummyProto.dummy_proto.newBuilder()
                    .setField1(1)
                    .setField2("hello")
                    .build();
            sourceContext.collect(proto);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
