package org.cs511.pipelines;

import com.twitter.chill.protobuf.ProtobufSerializer;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.datasource.DummyProtobufDataSource;
import org.cs511.proto.DummyProto;
import org.cs511.proto.ComplexProto;
import org.cs511.proto.SimpleProto;
import org.cs511.proto.SingleProto;

public class ProtobufPipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){
        env.getConfig().registerTypeWithKryoSerializer(DummyProto.dummy_proto.class, ProtobufSerializer.class);

        DataStream<DummyProto.dummy_proto> inputStream = env.addSource(new DummyProtobufDataSource());

        DataStream<DummyProto.dummy_proto> mapped = inputStream.map(new MapFunction<DummyProto.dummy_proto, DummyProto.dummy_proto>() {
            @Override
            public DummyProto.dummy_proto map(DummyProto.dummy_proto pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        // use a key by to break operator chaining
        KeyedStream<DummyProto.dummy_proto, Integer> keyed = mapped.keyBy((KeySelector<DummyProto.dummy_proto, Integer>) pojo -> 1);

        keyed.map(new MapFunction<DummyProto.dummy_proto, DummyProto.dummy_proto>() {
            @Override
            public DummyProto.dummy_proto map(DummyProto.dummy_proto pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        return env;
    }
}
