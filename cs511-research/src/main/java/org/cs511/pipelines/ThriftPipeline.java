package org.cs511.pipelines;

import com.twitter.chill.protobuf.ProtobufSerializer;
import com.twitter.chill.thrift.TBaseSerializer;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.datasource.DummyThriftDataSource;
import org.cs511.thrift.DummyThrift;

import org.cs511.thrift.SingleThrift;
import org.cs511.datasource.ThriftSingleDataSource;

import org.cs511.thrift.SimpleThrift;
import org.cs511.datasource.ThriftSimpleNestedDataSource;

import org.cs511.thrift.ComplexThrift;
import org.cs511.datasource.ThriftComplexNestedDataSource;

public class ThriftPipeline {
    public static StreamExecutionEnvironment create(StreamExecutionEnvironment env){
        env.getConfig().addDefaultKryoSerializer(ComplexThrift.class, TBaseSerializer.class);

        DataStream<ComplexThrift> inputStream = env.addSource(new ThriftComplexNestedDataSource());

        DataStream<ComplexThrift> mapped = inputStream.map(new MapFunction<ComplexThrift, ComplexThrift>() {
            @Override
            public ComplexThrift map(ComplexThrift pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        // use a key by to break operator chaining
        KeyedStream<ComplexThrift, Integer> keyed = mapped.keyBy((KeySelector<ComplexThrift, Integer>) pojo -> 1);

        keyed.map(new MapFunction<ComplexThrift, ComplexThrift>() {
            @Override
            public ComplexThrift map(ComplexThrift pojo) throws Exception {
                return pojo;    // do a simple identity map
            }
        });

        return env;
    }
}
