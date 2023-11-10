package org.cs511.datasource;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

public class DummyTupleDataSource extends RichSourceFunction<Tuple2<Integer, String>> {
    private boolean running = true;

    @Override
    public void run(SourceContext<Tuple2<Integer, String>> sourceContext) throws Exception {

        while (running){
            // parse each line to a  tuples and emits it

            // insert code for parsing file into records and emit one at a time

            String dummyFile = "1,hii";
            String[] tokens = dummyFile.split(",");
            Integer field1 = Integer.parseInt(tokens[0]);
            String field2 = tokens[1];

            Tuple2<Integer, String> resultElement = new Tuple2<>(field1, field2);
            sourceContext.collect(resultElement);   // emit record
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
