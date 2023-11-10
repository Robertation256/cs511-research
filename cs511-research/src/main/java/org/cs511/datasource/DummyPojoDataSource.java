package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

public class DummyPojoDataSource extends RichSourceFunction<DummyPojoDataSource.MyPojo> {
    private boolean running = true;

    // note that PoJo needs to follow below conventions
    public static final class MyPojo {
        private int field1 = 1;
        private String field2 = "hello";

        public void setField1(int field1) {
            this.field1 = field1;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public int getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }

    @Override
    public void run(SourceContext<DummyPojoDataSource.MyPojo> sourceContext) throws Exception {

        while (running){
            // parse each line to a pojo

            // insert code for parsing file into records and emit one at a time


            MyPojo resultElement = new MyPojo();
            sourceContext.collect(resultElement);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
