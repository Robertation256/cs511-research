package org.cs511.datasource;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.cs511.proto.ComplexProto;
import org.cs511.thrift.ComplexThrift;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class ThriftComplexNestedDataSource extends RichSourceFunction<ComplexThrift> {
    private boolean running = true;


    @Override
    public void run(SourceContext<ComplexThrift> sourceContext) throws Exception {

        JSONParser jp = new JSONParser();
        Object datasetObj = jp.parse(new FileReader("../datasets/steam.json"));
        JSONArray dataLines = (JSONArray) datasetObj;

        Iterator itr = dataLines.iterator();

        while (itr.hasNext() && this.running){
            // parse each line to a pojo
            JSONObject lineNode = (JSONObject) itr.next();
            // Accessing fields in the JsonNode
            String date = (String) lineNode.get("date");
            String developer = (String) lineNode.get("developer");
            String publisher = (String) lineNode.get("publisher");

            JSONObject desc_obj = (JSONObject) lineNode.get("full_desc");
            String sort = (String) desc_obj.get("sort");
            String desc = (String) desc_obj.get("desc");
            Desc desc_ = new Desc();
            desc_.setSort(sort);
            desc_.setDesc(desc);

            JSONObject requirement_obj = (JSONObject) lineNode.get("requirements");
            JSONObject minimum_obj = (JSONObject) requirement_obj.get("minimum");
            JSONObject windows_obj = (JSONObject) minimum_obj.get("windows");
            String processor = (String) windows_obj.get("processor");
            String memory = (String) windows_obj.get("memory");
            String graphics = (String)windows_obj.get("graphics");
            String os = (String) windows_obj.get("os");
            Windows win = new Windows();
            Minimum min = new Minimum();
            min.setWin(win);
            Requirements req = new Requirements();
            req.setMin(min);

            ComplexThrift resultElement = new ComplexThrift();
            resultElement.setDate(date);
            resultElement.setDeveloper(developer);
            resultElement.setPublisher(publisher);
            resultElement.setD(desc_);
            resultElement.setR(req);

            // emit record
            sourceContext.collect(resultElement);   
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
