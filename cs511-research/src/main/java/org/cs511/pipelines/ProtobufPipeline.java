package org.cs511.pipelines;

import com.twitter.chill.protobuf.ProtobufSerializer;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.datasource.DummyProtobufDataSource;
import org.cs511.proto.DummyProto;

import org.cs511.proto.SingleProto;
import org.cs511.datasource.ProtobufSingleDataSource;

import org.cs511.proto.SimpleProto;
import org.cs511.datasource.ProtobufSimpleNestedDataSource;

import org.cs511.proto.ComplexProto;
import org.cs511.datasource.ProtobufComplexNestedDataSource;

public class ProtobufPipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){
        env.getConfig().registerTypeWithKryoSerializer(ComplexProto.complex_proto.class, ProtobufSerializer.class);

        DataStream<ComplexProto.complex_proto> inputStream = env.addSource(new ProtobufComplexNestedDataSource());

        DataStream<ComplexProto.complex_proto> mapped = inputStream.map(new MapFunction<ComplexProto.complex_proto, ComplexProto.complex_proto>() {
            @Override
            public ComplexProto.complex_proto map(ComplexProto.complex_proto pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        // use a key by to break operator chaining
        KeyedStream<ComplexProto.complex_proto, Integer> keyed = mapped.keyBy((KeySelector<ComplexProto.complex_proto, Integer>) pojo -> 1);

        keyed.map(new MapFunction<ComplexProto.complex_proto, ComplexProto.complex_proto>() {
            @Override
            public ComplexProto.complex_proto map(ComplexProto.complex_proto pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        return env;
    }
}
