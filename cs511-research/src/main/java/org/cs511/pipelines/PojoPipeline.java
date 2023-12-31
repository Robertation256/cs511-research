package org.cs511.pipelines;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.datasource.DummyPojoDataSource;
import org.cs511.datasource.DummyTupleDataSource;
import org.cs511.datasource.PojoSingleDataSource;
import org.cs511.datasource.PojoSimpleNestDataSource;
import org.cs511.datasource.PojoComplexNestDataSource;

public class PojoPipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){
        DataStream<PojoComplexNestDataSource.MyPojo> inputStream = env.addSource(new PojoComplexNestDataSource());


        DataStream<PojoComplexNestDataSource.MyPojo> mapped = inputStream.map(new MapFunction<PojoComplexNestDataSource.MyPojo, PojoComplexNestDataSource.MyPojo>() {
            @Override
            public PojoComplexNestDataSource.MyPojo map(PojoComplexNestDataSource.MyPojo pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        // use a key by to break operator chaining
        KeyedStream<PojoComplexNestDataSource.MyPojo, Integer> keyed = mapped.keyBy((KeySelector<PojoComplexNestDataSource.MyPojo, Integer>) pojo -> 1);

        keyed.map(new MapFunction<PojoComplexNestDataSource.MyPojo, PojoComplexNestDataSource.MyPojo>() {
            @Override
            public PojoComplexNestDataSource.MyPojo map(PojoComplexNestDataSource.MyPojo pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        return env;
    }
}
