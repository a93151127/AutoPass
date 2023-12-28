package com.example.autopasstest.autopass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
@AllArgsConstructor
public enum AutoPassApi {
    GetAccessToken("/oauth/token", HttpMethod.POST, "取得 token");

    private String path;
    private HttpMethod requestMethod;
    private String description;
}
