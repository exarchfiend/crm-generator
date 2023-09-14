package fun.mjauto.crm.utils;

import fun.mjauto.crm.model.dto.SelectDto;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequest {

    public String post(String url, SelectDto selectDto) throws IOException {
        // 创建CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个HTTP POST请求，指定目标URL
        HttpPost httpPost = new HttpPost(url);
        // 设置请求体
        httpPost.setEntity(new StringEntity(
                new ObjectMapper().writeValueAsString(selectDto),
                ContentType.create("application/json", "UTF-8")));
        // 执行HTTP POST请求
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 检查HTTP响应的状态码
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 如果状态码为200（OK），则获取响应内容并打印
                return EntityUtils.toString(response.getEntity());
            } else {
                // 如果状态码不是200，则打印状态码
                System.err.println("查询失败，状态码：" + response.getStatusLine().getStatusCode());
            }
        }
        return null;
    }
}
