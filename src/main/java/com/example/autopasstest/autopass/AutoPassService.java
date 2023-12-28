package com.example.autopasstest.autopass;

import com.example.autopasstest.component.RestfulRs;
import com.example.autopasstest.util.PropUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    private LocalDateTime tokenTime;

    private  AccessTokenRes tokenRes;

    @Autowired
    private AutoPassComp autoPassComp;
    public AccessTokenRes doAccessToken(){
        if(tokenTime != null && tokenTime.isAfter(LocalDateTime.now()) && tokenRes != null){
            return tokenRes;
        }

        AccessTokenReq req = new AccessTokenReq(SCOPE, GRANT_TYPE, CLIENT_ID, CLIENT_SECRET);

        RestfulRs<AccessTokenRes> res = autoPassComp.exeAccessToken(req);

        if(!isResultSuccess(res.getStatus().toString(), SUCCESS_CODE_200)){
            throw new RuntimeException();
        }

        long createAt = Long.parseLong(res.getData().getCreateAt() + "000");
        long lifeTime = Long.parseLong(res.getData().getExpiresIn() + "000");
        long expire = createAt + lifeTime - 10000L; // 減少10秒的buff

        tokenTime = toLocalDateTime(expire);
        tokenRes = res.getData();

        return res.getData();
    }

    private LocalDateTime toLocalDateTime(long time){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    private boolean isResultSuccess(String statusCode, String... acceptCode){
        return Arrays.asList(acceptCode).contains(statusCode);
    }
}
