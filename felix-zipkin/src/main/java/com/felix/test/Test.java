package com.felix.test;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final String serverUrl = "http://localhost:9411/api/v2/spans";

    public static void main(String[] args) {
        MySpan span = new MySpan();
        span.setTraceId("1ae1e4f435814744");
        span.setParentId("1ae1e4f435814744");
        span.setId("d1ab9cd2c50d13d1");
        span.setKind(MySpan.Kind.SERVER.toString());
        span.setName("hahah");
        span.setTimestamp(1565933251470428L);
        span.setDuration(8286);
        span.setLocalEndPoint(new MySpan.EndPoint("My"));
        Map<String, String> tags = new HashMap<>();
        tags.put("name", "pioneeryi");
        tags.put("lover", "dandan");
        span.setTags(tags);

        doPost(serverUrl, span);
        System.out.println("Hello World!");
    }

    public static void doPost(String url, MySpan span) {
        try {
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("charset", "UTF-8");

            String body = new Gson().toJson(span);
            body = "[" + body + "]";
            System.out.print(body);

            StringEntity entity = new StringEntity(body);
            post.setEntity(entity);

            HttpResponse httpResponse = httpClient.execute(post);
            System.out.print(httpResponse);
        } catch (Exception exception) {
            System.out.print("do post request fail");
        }
    }
}
