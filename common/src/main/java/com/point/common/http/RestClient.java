package com.point.common.http;

import com.alibaba.fastjson.JSON;
import com.point.common.data.FileWrapper;
import com.point.common.exception.DevelopmentException;
import com.point.common.exception.HttpException;
import com.point.common.util.FileUtil;
import com.point.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class RestClient {

    private ViewDepotRestTemplate rest;
    private ViewDepotRestTemplate multiValueRest;

    //    private HttpHeaders jsonHeader;
//    private HttpHeaders formHeader;
//    private HttpHeaders mutilPartHeader;
    private HttpStatus status;

    public RestClient() {
        this.rest = new ViewDepotRestTemplate();
        init(rest);

        this.multiValueRest = new ViewDepotRestTemplate();
        init(multiValueRest);
//
//        this.jsonHeader = new HttpHeaders();
//        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
//
//        this.formHeader = new HttpHeaders();
//        formHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        this.mutilPartHeader = new HttpHeaders();
//        mutilPartHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

    }

    private void init(ViewDepotRestTemplate viewDepotRestTemplate) {
        List<HttpMessageConverter<?>> converters = viewDepotRestTemplate.getMessageConverters();
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(Charset.forName("UTF-8"));
                ((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
                break;
            }
        }
        converters.add(new TextHtmlGsonHttpMessageConverter());

    }

    public <T> T get(String baseUrl, String uri, Class<T> type, Map<String, Object> args) {

        return get(baseUrl, uri, type, args, null);
    }

    public <T> T get(String baseUrl, String uri, Class<T> type, Map<String, Object> args, Map<String, String> externalHeader) {

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        mergeHeader(jsonHeader, externalHeader);

        HttpEntity<String> requestEntity = new HttpEntity<>("", jsonHeader);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + uri);
        if (args != null) {
            for (Map.Entry<String, Object> entry : args.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        log.debug("request--[{}]", builder.build().encode().toUri());
        ResponseEntity<T> responseEntity = rest.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, type);
//        } else {
//            responseEntity = rest.exchange(baseUrl + uri, HttpMethod.GET, requestEntity, type);
//        }
        this.setStatus(responseEntity.getStatusCode());
        T response = responseEntity.getBody();
        log.debug("response--[{}]", response);
        return response;
    }

    public <T> T get(String baseUrl, String uri, Class<T> type) {
        return get(baseUrl, uri, type, null, null);
    }


    public FileWrapper download(String baseUrl, String uri, boolean urlEncode) {

        return download(baseUrl, uri, urlEncode, null);
    }

    public FileWrapper download(String baseUrl, String uri, boolean urlEncode, Map<String, String> externalHeader) {

        // 非http开头的url认为是本地路径
        if (!StringUtils.startsWith(baseUrl, "http")) {
            byte[] content = FileUtil.toByteArray(baseUrl);
            FileWrapper result = new FileWrapper();
            result.setFileContent(content);
            String fileName = FileUtil.getFileName(baseUrl);
            result.setFileName(fileName);
            return result;
        }
        HttpHeaders formHeader = new HttpHeaders();
        formHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        mergeHeader(formHeader, externalHeader);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + uri);
        UriComponents components = builder.build(urlEncode);
        HttpEntity<String> entity = new HttpEntity<>(formHeader);
        ResponseEntity<byte[]> response = rest.exchange(
                components.toUri(), HttpMethod.GET, entity, byte[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            FileWrapper result = new FileWrapper();
            result.setFileContent(response.getBody());

            List<String> headers = response.getHeaders().get("Content-disposition");
            if (headers != null) {
                for (String header : headers) {
                    String fileName = null;
                    if (header.startsWith("attachment;filename*=UTF-8''")) {
                        fileName = StringUtils.right(header, header.length() - header.indexOf("''") - 2);
                    } else if (header.startsWith("attachment;filename")) {
                        fileName = StringUtils.right(header, header.length() - header.indexOf("=") - 1);
                    } else {
                        fileName = UUID.randomUUID().toString();
                    }
                    try {
                        result.setFileName(URLDecoder.decode(fileName, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        throw new DevelopmentException(e);
                    }
                    break;
                }
            }
            return result;
        }
        throw new HttpException(String.format("Download failed. %s", baseUrl + uri));
    }

    public FileWrapper download(String baseUrl, String uri) {

        return download(baseUrl, uri, true);
    }

    public <T> T download(String baseUrl, String uri, DownloadExtractor<T> extractor) {

        // Optional Accept header
        RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

        // Streams the response instead of loading it all in memory
        ResponseExtractor<T> responseExtractor = response -> {
            // Here I write the response to a file but do what you like
            if (extractor != null) {
                return extractor.extract(response.getBody());
//                FileUtil.toFile(response.getBody(), "D:\\test.dat");
            }
            return null;
        };
        return rest.execute(baseUrl + StringUtil.nvl(uri), HttpMethod.GET, requestCallback, responseExtractor);
    }

    public InputStream downloadByStream(String baseUrl, String uri) {

       return downloadByStream(baseUrl, uri, null);
    }

    public InputStream downloadByStream(String baseUrl, String uri, Map<String, String> externalHeader) {

        HttpHeaders formHeader = new HttpHeaders();
        formHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        mergeHeader(formHeader, externalHeader);

        HttpEntity<String> entity = new HttpEntity<>(formHeader);
        ResponseEntity<Resource> response = rest.exchange(
                baseUrl + uri, HttpMethod.GET, entity, Resource.class);

        try {
            return response.getBody().getInputStream();
        } catch (IOException e) {
            throw new DevelopmentException(e);
        }
    }

    public interface DownloadExtractor<T> {
        T extract(InputStream is);
    }

    public <T> T post(String baseUrl, String uri, MultiValueMap<String, ?> args, Class<T> responseType) {

        HttpHeaders multiPartHeader = new HttpHeaders();
        multiPartHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setBufferRequestBody(false);
//        rest.setRequestFactory(requestFactory);
        HttpEntity<MultiValueMap<String, ?>> requestEntity = new HttpEntity<>(args, multiPartHeader);
        ResponseEntity<T> responseEntity = multiValueRest.exchange(baseUrl + StringUtil.nvl(uri), HttpMethod.POST, requestEntity, responseType);
        if (HttpStatus.TEMPORARY_REDIRECT.equals(responseEntity.getStatusCode())) {
            URI newUri = responseEntity.getHeaders().getLocation();
            responseEntity = multiValueRest.exchange(baseUrl + StringUtil.nvl(newUri), HttpMethod.POST, requestEntity, responseType);
        }
        this.setStatus(responseEntity.getStatusCode());
        log.debug("request--[{}][{}][{}]", baseUrl, uri, args);
        T response = responseEntity.getBody();
        log.debug("response--[{}]", JSON.toJSONString(response));
        return response;
    }

    public <T> T postByRedirect(String baseUrl, String uri, MultiValueMap<String, ?> args, Class<T> responseType) {

//        ViewDepotRestTemplate rest = new ViewDepotRestTemplate();
//        init(rest);

        HttpHeaders multiPartHeader = new HttpHeaders();
        multiPartHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, ?>> requestEntity = new HttpEntity<>(args, multiPartHeader);
        ResponseEntity<T> responseEntity = rest.exchange(baseUrl + StringUtil.nvl(uri), HttpMethod.POST, null, responseType);
        if (HttpStatus.TEMPORARY_REDIRECT.equals(responseEntity.getStatusCode())) {
            URI newUri = responseEntity.getHeaders().getLocation();
            responseEntity = rest.exchange(baseUrl + StringUtil.nvl(newUri), HttpMethod.POST, requestEntity, responseType);
        }
        this.setStatus(responseEntity.getStatusCode());
        log.debug("request--[{}][{}][{}]", baseUrl, uri);
        T response = responseEntity.getBody();
        log.debug("response--[{}]", JSON.toJSONString(response));
        return response;
    }

    public <T> T post(String baseUrl, String uri, Object param, Class<T> responseType, Map<String, String> externalHeader) {

        return post(baseUrl, uri, JSON.toJSONString(param), responseType, externalHeader);
    }

    public <T> T put(String baseUrl, String uri, Object param, Class<T> responseType, Map<String, String> externalHeader) {

        return put(baseUrl, uri, JSON.toJSONString(param), responseType, externalHeader);
    }

    public <T> T post(String baseUrl, String uri, String param, Class<T> responseType) {

        return post(baseUrl, uri, param, responseType, null);
    }

    public <T> T post(String baseUrl, String uri, Object param, Class<T> responseType) {

        return post(baseUrl, uri, JSON.toJSONString(param), responseType, null);
    }


    public <T> T post(String baseUrl, String uri, String json, Class<T> responseType, Map<String, String> externalHeader) {

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        mergeHeader(jsonHeader, externalHeader);

        HttpEntity<String> requestEntity = new HttpEntity<>(json, jsonHeader);
        ResponseEntity<T> responseEntity = rest.exchange(baseUrl + StringUtil.nvl(uri), HttpMethod.POST, requestEntity, responseType);

        this.setStatus(responseEntity.getStatusCode());
        // 控制log输出
        if (StringUtils.length(json) <= 1024 * 2) {
            log.debug("request--[{}][{}][{}]", baseUrl, uri, json);
        } else {
            log.debug("request--[{}][{}][********]", baseUrl, uri);
        }
        T response = responseEntity.getBody();
        log.debug("response--[{}]", JSON.toJSONString(response));
        return response;
    }

    public <T> T put(String baseUrl, String uri, String json, Class<T> responseType, Map<String, String> externalHeader) {

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        mergeHeader(jsonHeader, externalHeader);

        HttpEntity<String> requestEntity = new HttpEntity<>(json, jsonHeader);
        ResponseEntity<T> responseEntity = rest.exchange(baseUrl + StringUtil.nvl(uri), HttpMethod.PUT, requestEntity, responseType);
        if (HttpStatus.TEMPORARY_REDIRECT.equals(responseEntity.getStatusCode())) {
            URI newUri = responseEntity.getHeaders().getLocation();
            responseEntity = rest.exchange(baseUrl + StringUtil.nvl(newUri), HttpMethod.PUT, requestEntity, responseType);
        }
        this.setStatus(responseEntity.getStatusCode());
        // 控制log输出
        if (StringUtils.length(json) <= 1024 * 2) {
            log.debug("request--[{}][{}][{}]", baseUrl, uri, json);
        } else {
            log.debug("request--[{}][{}][********]", baseUrl, uri);
        }
        T response = responseEntity.getBody();
        log.debug("response--[{}]", JSON.toJSONString(response));
        return response;
    }

    public <T> T post(String baseUrl, String uri, Class<T> responseType) {
        return post(baseUrl, uri, (String) null, responseType);
    }

    public <T> T delete(String baseUrl, String uri, Class<T> type, Map<String, Object> args, Map<String, String> externalHeader) {

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        mergeHeader(jsonHeader, externalHeader);

        HttpEntity<String> requestEntity = new HttpEntity<>("", jsonHeader);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + uri);
        if (args != null) {
            for (Map.Entry<String, Object> entry : args.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        ResponseEntity<T> responseEntity = rest.exchange(builder.build().encode().toUri(), HttpMethod.DELETE, requestEntity, type);
        if (HttpStatus.TEMPORARY_REDIRECT.equals(responseEntity.getStatusCode())) {
            URI newUri = responseEntity.getHeaders().getLocation();
            responseEntity = rest.exchange(baseUrl + newUri, HttpMethod.DELETE, requestEntity, type);
        }
        this.setStatus(responseEntity.getStatusCode());
        T response = responseEntity.getBody();
        log.debug("response--[{}]", JSON.toJSONString(response));
        return response;
    }

    public <T> T patch(String baseUrl, String uri, Class<T> responseType, Map<String, Object> args) {

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        T response = restTemplate.patchForObject(baseUrl + StringUtil.nvl(uri), args, responseType);
        //new ResponseEntity<T>(response, HttpStatus.OK);
        log.debug("response--[{}]", JSON.toJSONString(response));
        return response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setContentTypeReplacer(Map<String, String> replacer) {
        this.rest.setContentTypeReplacer(replacer);
    }

    private class TextHtmlGsonHttpMessageConverter extends GsonHttpMessageConverter {
        public TextHtmlGsonHttpMessageConverter() {
            List<MediaType> types = Arrays.asList(
                    new MediaType(MediaType.TEXT_HTML, DEFAULT_CHARSET),
                    new MediaType(MediaType.TEXT_PLAIN, DEFAULT_CHARSET),
                    new MediaType(MediaType.TEXT_XML, DEFAULT_CHARSET));
            super.setSupportedMediaTypes(types);
        }
    }

    private void mergeHeader(HttpHeaders httpHeaders, Map<String, String> externalHeader) {
        if (externalHeader != null) {
            for (Map.Entry<String, String> entry : externalHeader.entrySet()) {
                if (httpHeaders.containsKey(entry.getKey())) {
                    httpHeaders.replace(entry.getKey(), Arrays.asList(entry.getValue()));
                } else {
                    httpHeaders.add(entry.getKey(), entry.getValue());
                }
            }
        }
    }

}