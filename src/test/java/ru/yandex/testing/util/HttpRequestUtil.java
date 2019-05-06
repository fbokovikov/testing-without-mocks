package ru.yandex.testing.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author fbokovikov
 */
public final class HttpRequestUtil {

    protected static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private HttpRequestUtil() {
    }

    public static String post(String url) {
        return post(url, null);
    }

    public static String post(String url, String body) {
        var httpEntity = new HttpEntity<>(body, jsonHeaders());
        return REST_TEMPLATE.postForObject(url, httpEntity, String.class);
    }

    private static HttpHeaders jsonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
