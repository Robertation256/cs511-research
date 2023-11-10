package org.cs511;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.pipelines.PojoPipeline;
import org.cs511.pipelines.ProtobufPipeline;
import org.cs511.pipelines.ThriftPipeline;
import org.cs511.pipelines.TuplePipeline;

public class Main {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setString("rest.port", "8000");
        conf.setBoolean("rest.flamegraph.enabled", true);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);

//        env = TuplePipeline.create(env);
//        env = PojoPipeline.create(env);
//        env = ProtobufPipeline.create(env);
        env = ThriftPipeline.create(env);

        try {
            env.execute();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}