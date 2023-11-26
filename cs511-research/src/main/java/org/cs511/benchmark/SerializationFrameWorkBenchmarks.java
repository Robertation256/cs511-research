package org.cs511.benchmark;
import com.twitter.chill.protobuf.ProtobufSerializer;
import com.twitter.chill.thrift.TBaseSerializer;
import org.cs511.datasource.*;
import org.cs511.proto.ComplexProto;
import org.cs511.proto.SimpleProto;
import org.cs511.proto.SingleProto;
import org.cs511.thrift.ComplexThrift;
import org.cs511.thrift.SimpleThrift;
import org.cs511.thrift.SingleThrift;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.DiscardingSink;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.Throughput;
import static org.openjdk.jmh.annotations.Scope.Thread;
@SuppressWarnings("MethodMayBeStatic")
@State(Thread)
@OutputTimeUnit(MILLISECONDS)
@BenchmarkMode(Throughput)
@Fork(value = 3, jvmArgsAppend = {
        "-Djava.rmi.server.hostname=127.0.0.1",
        "-Dcom.sun.management.jmxremote.authenticate=false",
        "-Dcom.sun.management.jmxremote.ssl=false",
        "-Dcom.sun.management.jmxremote.ssl"})
@Warmup(iterations = 2)
@Measurement(iterations = 4)
public class SerializationFrameWorkBenchmarks {

    protected static final int RECORDS_PER_INVOCATION = 300_000;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .verbosity(VerboseMode.NORMAL)
                .include(".*" + SerializationFrameWorkBenchmarks.class.getCanonicalName() + ".*")
                .build();

        new Runner(options).run();
    }

    // tuple test
    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void tupleTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);

        env.addSource(new TupleSingleDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }


    // pojo tests

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void nonNestedPojoTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        ExecutionConfig executionConfig = env.getConfig();
        executionConfig.registerPojoType(PojoSingleDataSource.MyPojo.class);
        env.addSource(new PojoSingleDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void simpleNestedPojoTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        ExecutionConfig executionConfig = env.getConfig();
        executionConfig.registerPojoType(PojoSimpleNestDataSource.MyPojo.class);
        env.addSource(new PojoSimpleNestDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void complexNestedPojoTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        ExecutionConfig executionConfig = env.getConfig();
        executionConfig.registerPojoType(PojoComplexNestDataSource.MyPojo.class);
        env.addSource(new PojoComplexNestDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }



    // avro tests

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void nonNestedAvroTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.addSource(new DummyAvroDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void simpleNestedAvroTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.addSource(new DummyAvroDataSourceMedium(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void complexNestedAvroTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.addSource(new DummyAvroDataSourceHigh(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }


    // thrift tests
    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void nonNestedThriftTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.getConfig().addDefaultKryoSerializer(SingleThrift.class, TBaseSerializer.class);
        env.addSource(new ThriftSingleDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void simpleNestedThriftTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.getConfig().addDefaultKryoSerializer(SimpleThrift.class, TBaseSerializer.class);
        env.addSource(new ThriftSimpleNestedDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void complexNestedThriftTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.getConfig().addDefaultKryoSerializer(ComplexThrift.class, TBaseSerializer.class);
        env.addSource(new ThriftComplexNestedDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }



    //protobuf tests

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void nonNestedProtobufTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.getConfig().registerTypeWithKryoSerializer(SingleProto.single_proto.class, ProtobufSerializer.class);
        env.addSource(new ProtobufSingleDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void simpleNestedProtobufTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.getConfig().registerTypeWithKryoSerializer(SimpleProto.simple_proto.class, ProtobufSerializer.class);
        env.addSource(new ProtobufSimpleNestedDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }

    @Benchmark
    @OperationsPerInvocation(value = SerializationFrameWorkBenchmarks.RECORDS_PER_INVOCATION)
    public void complexNestedProtobufTest(FlinkEnvironmentContext context) throws Exception {
        StreamExecutionEnvironment env = context.env;
        env.setParallelism(1);
        env.getConfig().registerTypeWithKryoSerializer(ComplexProto.complex_proto.class, ProtobufSerializer.class);
        env.addSource(new ProtobufComplexNestedDataSource(RECORDS_PER_INVOCATION))
                .rebalance()
                .addSink(new DiscardingSink<>());
        env.execute();
    }


}
