package org.cs511;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cs511.pipelines.TuplePipeline;

public class Main {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env = TuplePipeline.create(env);

        try {
            env.execute();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}