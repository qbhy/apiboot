package com.qbhy.apiboot.framework.http.middlewares.cross;

import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class Cross {
    // 允许跨域的host
    private String host;

    // 允许跨域的方法
    private HttpMethod[] methods = new HttpMethod[]{
            HttpMethod.DELETE,
            HttpMethod.PUT,
            HttpMethod.GET,
            HttpMethod.POST,
    };

    private boolean withCredentials = true;
    private String[] withHeaders = new String[]{
            "Origin", "Content-Type", "Accept", "Authorization", "Cookie"
    };

    public Cross(String host, HttpMethod[] methods) {
        this.host = host;
        this.methods = methods;
    }

    public Cross(String host) {
        this.host = host;
    }

    public Cross(String host, HttpMethod[] methods, boolean withCredentials) {
        this.host = host;
        this.methods = methods;
        this.withCredentials = withCredentials;
    }

    public Cross(String host, HttpMethod[] methods, boolean withCredentials, String[] withHeaders) {
        this.host = host;
        this.methods = methods;
        this.withCredentials = withCredentials;
        this.withHeaders = withHeaders;
    }

    public Cross(String host, HttpMethod[] methods, String[] withHeaders) {
        this.host = host;
        this.methods = methods;
        this.withHeaders = withHeaders;
    }
}
