package com.muscidae.parrot.common.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author muscidae
 * @date 2019/10/28 18:56
 * @copyright ©2019
 * @description Java HttpClient Utils(Sync)
 */
public final class HttpClientSyncUtils {

    private HttpClientSyncUtils() { }

    private final Logger log = LoggerFactory.getLogger(HttpClientSyncUtils.class);

    private static final HttpClient client = HttpClient.newHttpClient();

    public enum Method{ GET, POST, DELETE, PUT }

    public String encode(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }

    public String encode(String url, Charset charset) {
        return URLEncoder.encode(url, charset);
    }

    public String decode(String url) {
        return URLDecoder.decode(url, StandardCharsets.UTF_8);
    }
    
    public String decode(String url, Charset charset) {
        return URLDecoder.decode(url, charset);
    }
    
    public HttpResponse<String> get(String url, String... headers) throws IOException, InterruptedException {
        return get(url, null, headers);
    }

    /**
     * [RFC7230] Request message framing is independent of method semantics, even if the method does not define any use for a message body.
     */
    @Deprecated
    public HttpResponse<String> get(String url, String requestBody, String... headers) throws IOException, InterruptedException {
        return get(url, requestBody, StandardCharsets.UTF_8, headers);
    }

    /**
     * [RFC7231] A payload within a GET request message has no defined semantics;
     * sending a payload body on a GET request might cause some existing implementations to reject the request.
     */
    @Deprecated
    public HttpResponse<String> get(String url, String requestBody, Charset charset, String... headers) throws IOException, InterruptedException {
        return request(Method.GET, url, null == requestBody || 0 == requestBody.length() ?
                        HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(requestBody, charset), HttpResponse.BodyHandlers.ofString(), headers);
    }

    public HttpResponse<String> post(String url, String requestBody, String... headers) throws IOException, InterruptedException {
        return post(url, requestBody, StandardCharsets.UTF_8, headers);
    }

    public HttpResponse<String> post(String url, Map<String, String> params, String... headers) throws IOException, InterruptedException {
        return post(url, params, StandardCharsets.UTF_8, headers);
    }

    public HttpResponse<String> post(String url, Map<String, String> params, Charset charset, String... headers) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        params.forEach((k, v) -> sb.append(encode(k)).append("=").append(encode(v)).append("&"));
        return post(url, sb.substring(0, sb.length() - 1), charset, headers);
    }

    public HttpResponse<String> post(String url, String requestBody, Charset charset, String... headers) throws IOException, InterruptedException {
        return request(Method.POST, url, null == requestBody || 0 == requestBody.length() ?
                HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(requestBody, charset), HttpResponse.BodyHandlers.ofString(), headers);
    }

    public HttpResponse<String> post(String url, Supplier<? extends InputStream> streamSupplier, String... headers) throws IOException, InterruptedException {
        return request(Method.POST, url, null == streamSupplier ?
                HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofInputStream(streamSupplier), HttpResponse.BodyHandlers.ofString(), headers);
    }

    public HttpResponse<String> post(String url, Path path, String... headers) throws IOException, InterruptedException {
        return request(Method.POST, url, null == path ?
                HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofFile(path), HttpResponse.BodyHandlers.ofString(), headers);
    }

    public HttpResponse<String> post(String url, Map<String, Object> params, String boundary, String... headers) throws IOException, InterruptedException {
        return post(url, params, boundary, StandardCharsets.UTF_8, headers);
    }

    public HttpResponse<String> post(String url, Map<String, Object> params, String boundary, Charset charset, String... headers) throws IOException, InterruptedException {
        return request(Method.POST, url, null == params || 0 == params.size() ?
                HttpRequest.BodyPublishers.noBody() : ofMultipartData(params, boundary, charset), HttpResponse.BodyHandlers.ofString(), headers);
    }

    private HttpRequest.BodyPublisher ofMultipartData(Map<String, Object> data, String boundary, Charset charset) throws IOException {
        List<byte[]> byteArrays = new ArrayList<>();
        byte[] separator = ("--" + boundary + "\r\nContent-Disposition: form-data; name=").getBytes(charset);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            byteArrays.add(separator);
            if (entry.getValue() instanceof Path) {
                Path path = (Path) entry.getValue();
                byteArrays.add(("\"" + entry.getKey()
                        + "\"; filename=\"" + path.getFileName()
                        + "\"\r\nContent-Type: " + Files.probeContentType(path) + "\r\n\r\n")
                        .getBytes(charset));
                byteArrays.add(Files.readAllBytes(path));
                byteArrays.add("\r\n".getBytes(charset));
            } else {
                byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n").getBytes(charset));
            }
        }
        byteArrays.add(("--" + boundary + "--").getBytes(charset));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    public HttpResponse<byte[]> download(String url, String... headers) throws IOException, InterruptedException {
        return download(Method.GET, url, null, headers);
    }

    public HttpResponse<byte[]> download(Method method, String url, String requestBody, String... headers) throws IOException, InterruptedException {
        return download(method, url, requestBody, StandardCharsets.UTF_8, headers);
    }

    public HttpResponse<byte[]> download(Method method, String url, String requestBody, Charset charset, String... headers) throws IOException, InterruptedException {
        return request(method, url, null == requestBody || 0 == requestBody.length() ?
                HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(requestBody, charset), HttpResponse.BodyHandlers.ofByteArray(), headers);
    }

    public <T> HttpResponse<T> request(Method method, String url, HttpRequest.BodyPublisher bodyPublishers,
                                HttpResponse.BodyHandler<T> responseBodyHandler, String... headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(url)).method(method.toString(), bodyPublishers);
        if (null != headers && 0 < headers.length)
            builder.headers(headers);
        HttpRequest request = builder.build();
        log.debug("Execute HttpClient Method:『{}』, Url:『{}』", request.method(), request.uri().toString());
        return client.send(request, responseBodyHandler);
    }

    public enum Singleton{
        INSTANCE;
        private HttpClientSyncUtils httpClientSyncUtils;
        Singleton(){ httpClientSyncUtils = new HttpClientSyncUtils(); }
        public HttpClientSyncUtils newInstance(){ return httpClientSyncUtils; }
    }

}
