package com.example.autopasstest.autopass;

import com.example.autopasstest.component.RestfulRs;
import com.example.autopasstest.util.PropUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service("AutoPassService")
public class AutoPassService {

    private static final String SCOPE = PropUtils.getProperty("autopass.scope");
    private static final String GRANT_TYPE = PropUtils.getProperty("autopass.granttype");
    private static final String CLIENT_ID = PropUtils.getProperty("autopass.clientid");
    private static final String CLIENT_SECRET = PropUtils.getProperty("autopass" +
            ".clientsecret");
    private static final String SUCCESS_CODE_200 = "200 OK";

    @Autowired
    private AutoPassComp autoPassComp;
    public AccessTokenRes doAccessToken(){
        AccessTokenReq req = new AccessTokenReq(SCOPE, GRANT_TYPE, CLIENT_ID, CLIENT_SECRET);

        RestfulRs<AccessTokenRes> res = autoPassComp.exeAccessToken(req);

        if(!isResultSuccess(res.getStatus().toString(), SUCCESS_CODE_200)){
            throw new RuntimeException();
        }

        return res.getData();
    }

    private boolean isResultSuccess(String statusCode, String... acceptCode){
        return Arrays.asList(acceptCode).contains(statusCode);
    }
}
