package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.DummyProto;
import org.cs511.thrift.DummyThrift;

public class DummyThriftDataSource extends RichSourceFunction<DummyThrift> {
    private boolean running = true;


    @Override
    public void run(SourceContext<DummyThrift> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time


            DummyThrift thriftObj = new DummyThrift();
            thriftObj.setId(1);
            thriftObj.setName("hii");
            sourceContext.collect(thriftObj);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
