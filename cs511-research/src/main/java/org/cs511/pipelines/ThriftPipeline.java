package org.cs511.pipelines;

import com.twitter.chill.protobuf.ProtobufSerializer;
import com.twitter.chill.thrift.TBaseSerializer;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.datasource.DummyProtobufDataSource;
import org.cs511.datasource.DummyThriftDataSource;
import org.cs511.proto.DummyProto;
import org.cs511.thrift.DummyThrift;

public class ThriftPipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){
        env.getConfig().addDefaultKryoSerializer(DummyThrift.class, TBaseSerializer.class);

        DataStream<DummyThrift> inputStream = env.addSource(new DummyThriftDataSource());

        DataStream<DummyThrift> mapped = inputStream.map(new MapFunction<DummyThrift, DummyThrift>() {
            @Override
            public DummyThrift map(DummyThrift pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        // use a key by to break operator chaining
        KeyedStream<DummyThrift, Integer> keyed = mapped.keyBy((KeySelector<DummyThrift, Integer>) pojo -> 1);

        keyed.map(new MapFunction<DummyThrift, DummyThrift>() {
            @Override
            public DummyThrift map(DummyThrift pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        return env;
    }
}
