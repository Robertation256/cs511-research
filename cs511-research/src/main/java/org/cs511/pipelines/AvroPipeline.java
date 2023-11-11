package org.cs511.pipelines;


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.avro.DummyAvro;
import org.cs511.datasource.DummyAvroDataSource;


public class AvroPipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){

        DataStream<DummyAvro> inputStream = env.addSource(new DummyAvroDataSource());

        DataStream<DummyAvro> mapped = inputStream.map(new MapFunction<DummyAvro, DummyAvro>() {
            @Override
            public DummyAvro map(DummyAvro pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        // use a key by to break operator chaining
        KeyedStream<DummyAvro, Integer> keyed = mapped.keyBy((KeySelector<DummyAvro, Integer>) pojo -> 1);

        keyed.map(new MapFunction<DummyAvro, DummyAvro>() {
            @Override
            public DummyAvro map(DummyAvro pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        return env;
    }
}
