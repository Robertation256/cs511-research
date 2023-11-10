package org.cs511;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.pipelines.TuplePipeline;

public class Main {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
//        conf.setString("rest.port", "8000");
//        conf.setString("rest.flamegraph.enabled", "true");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);

        env = TuplePipeline.create(env);

        try {
            env.execute();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}