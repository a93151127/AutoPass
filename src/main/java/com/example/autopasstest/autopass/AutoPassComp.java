package com.example.autopasstest.autopass;

import com.example.autopasstest.component.RestfulRs;

public interface AutoPassComp {
    RestfulRs<AccessTokenRes> exeAccessToken(AccessTokenReq req);
}
