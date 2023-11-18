package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.thrift.SimpleThrift;
import org.cs511.thrift.Desc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class ThriftSimpleNestedDataSource extends RichSourceFunction<SimpleThrift> {
    private boolean running = true;


    @Override
    public void run(SourceContext<SimpleThrift> sourceContext) throws Exception {

        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader("../datasets/steam.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();
            String date = (String) lineNode.get("date");
            String developer = (String) lineNode.get("developer");
            String publisher = (String) lineNode.get("publisher");

            JSONObject desc_obj = (JSONObject) lineNode.get("full_desc");
            String sort = (String) desc_obj.get("sort");
            String desc = (String) desc_obj.get("desc");
            Desc desc_ = new Desc();
            desc_.setSort(sort);
            desc_.setDesc(desc);

            SimpleThrift resultElement = new SimpleThrift();
            resultElement.setDate(date);
            resultElement.setDeveloper(developer);
            resultElement.setPublisher(publisher);
            resultElement.setD(desc_);

            // emit record
            sourceContext.collect(resultElement);
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}