package com.example.autopasstest.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestfulClient {
    public <T> RestfulRs<T> call(String url, Object data, HttpHeaders headers,
                                 Class<T> responseType, HttpMethod requestMethod, MediaType mediaType){
        RestfulRs<T> rs = new RestfulRs<>();
        HttpEntity<Object> requestEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ReturnErrorHandler());

        try{
            if(requestMethod != HttpMethod.GET){
                if(mediaType != null){
                    headers.setContentType(mediaType);
                }
                if(data != null){
                    requestEntity = new HttpEntity<>(data, headers);
                }else{
                    requestEntity = new HttpEntity<>(headers);
                }
            }else{
                requestEntity = new HttpEntity<>(headers);
            }

            log.info("url : {}", url);

        }catch (Exception e){
            log.error(e.getMessage());
        }

        System.out.println(requestEntity.toString());

        ResponseEntity<T> rsEntity = restTemplate.exchange(url, requestMethod, requestEntity, responseType);

        rs.setStatus(HttpStatus.valueOf(rsEntity.getStatusCode().value()));
        rs.setData(rsEntity.getBody());

        return rs;
    }
}
