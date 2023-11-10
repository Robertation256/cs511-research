package org.cs511.pipelines;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.datasource.DummyDataSource;


public class TuplePipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){
         DataStream<Tuple2<Integer, String>> inputStream = env.addSource(new DummyDataSource());


         DataStream<Tuple2<Integer, String>> mapped = inputStream.map(new MapFunction<Tuple2<Integer, String>, Tuple2<Integer, String>>() {
             @Override
             public Tuple2<Integer, String> map(Tuple2<Integer, String> integerStringTuple2) throws Exception {
                 return integerStringTuple2;    // do a simple identity map
             }
         });

         // use a key by to break operator chaining
        KeyedStream<Tuple2<Integer, String>, Integer> keyed = mapped.keyBy((KeySelector<Tuple2<Integer, String>, Integer>) integerStringTuple2 -> 1);

        keyed.map(new MapFunction<Tuple2<Integer, String>, Tuple2<Integer, String>>() {
            @Override
            public Tuple2<Integer, String> map(Tuple2<Integer, String> integerStringTuple2) throws Exception {
                return integerStringTuple2;    // do a simple identity map
            }
        });

        return env;
    }
}
