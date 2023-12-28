package com.example.autopasstest.autopass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AccessTokenReq {
    private String scope;
    private String grantType;
    private String clientId;
    private String clientSecret;
}
