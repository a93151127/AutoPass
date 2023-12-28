package com.example.autopasstest.autopass;

import com.example.autopasstest.component.RestfulClient;
import com.example.autopasstest.component.RestfulRs;
import com.example.autopasstest.util.JsonUtil;
import com.example.autopasstest.util.PropUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service("AutoPassComp")
public class AutoPassCompImpl implements AutoPassComp{

    @Autowired
    private RestfulClient restfulClient;
    @Override
    public RestfulRs<AccessTokenRes> exeAccessToken(AccessTokenReq req) {
        HttpHeaders header = new HttpHeaders();

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("scope", req.getScope());
        bodyMap.add("grant_type", req.getGrantType());
        bodyMap.add("client_id", req.getClientId());
        bodyMap.add("client_secret", req.getClientSecret());

        String path = AutoPassApi.GetAccessToken.getPath();
        return doAutoPass(header, path, AutoPassApi.GetAccessToken.getRequestMethod(), bodyMap, AccessTokenRes.class,
                MediaType.APPLICATION_FORM_URLENCODED);
    }

    private <T> RestfulRs<T> doAutoPass(HttpHeaders header, String path, HttpMethod requestMethod, Object req,
                                        Class<T> responseType, MediaType mediaType){

        String url = PropUtils.getProperty("autopass.forward.url") + path;
        RestfulRs<String> resStr = null;
        RestfulRs<T> res = new RestfulRs<>();
        T resTypeClass = null;

        try{
            resStr = restfulClient.call(url, req, header, requestMethod, mediaType);

            try{
                resTypeClass = JsonUtil.jsonToObject(resStr.getData(), responseType);
            }catch(Exception e){
                System.out.println("parse error");
            }

            res.setStatus(resStr.getStatus());
            res.setData(resTypeClass);

        }catch(Exception e){
            throw e;
        }
        return res;
    }
}
