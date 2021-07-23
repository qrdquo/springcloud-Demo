package com.awei.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
zuul的熔断降级
* */
@Component
public class ZullFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        //对所有服务降级
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type","text/html;charset=UTF-8");
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                //响应体
                return new ByteArrayInputStream("服务正在维护，请稍后再试".getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                //状态码
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //上面状态码的值
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                //上面状态码的值对应的文本内容
                return HttpStatus.BAD_REQUEST.getReasonPhrase();
            }

            @Override
            public void close() {
            //关闭了response流，可以做一些清理工作
            }
        };
    }
}
