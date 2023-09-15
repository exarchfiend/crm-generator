package fun.mjauto.crm.utils;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequest {

    private final CloseableHttpClient httpClient;
    private final String url;

    private HttpPost httpPost;

    public HttpRequest(String url) {
        // 创建CloseableHttpClient
        this.httpClient = HttpClients.createDefault();
        // 指定目标URL
        this.url = url;
    }

    private void prefixPost() {
        this.httpPost = new HttpPost(url);
    }

    private String suffix() {
        String res = null;
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 检查HTTP响应的状态码
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 如果状态码为200（OK），则获取响应内容并打印
                res = EntityUtils.toString(response.getEntity());
            } else {
                // 如果状态码不是200，则打印状态码
                System.err.println("请求失败，状态码：" + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String postJson(Object obj) throws IOException {
        // 创建一个HTTP POST请求
        prefixPost();
        // 设置请求体
        httpPost.setEntity(new StringEntity(
                new ObjectMapper().writeValueAsString(obj),
                ContentType.create("application/json", "UTF-8")));
        // 执行HTTP POST请求
        return suffix();
    }

    public String postForm(String form) throws IOException {
        // 创建一个HTTP POST请求
        prefixPost();
        // 设置请求体
        httpPost.setEntity(new StringEntity(form,
                ContentType.create("application/x-www-form-urlencoded", "UTF-8")));
        // 执行HTTP POST请求
        return suffix();
    }
}
