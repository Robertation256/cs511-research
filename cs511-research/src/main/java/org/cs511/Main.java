package org.cs511;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.log4j.BasicConfigurator;
import org.cs511.pipelines.*;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Configuration conf = new Configuration();
        conf.setString("rest.port", "8000");
        conf.setBoolean("rest.flamegraph.enabled", true);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

//        env = TuplePipeline.create(env);
//        env = PojoPipeline.create(env);
//        env = ProtobufPipeline.create(env);
//        env = ThriftPipeline.create(env);
        env = AvroPipeline.create(env);

        try {
            env.execute();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}