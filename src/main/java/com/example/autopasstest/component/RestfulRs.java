package com.example.autopasstest.component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class RestfulRs<T> {
    private HttpStatus status;
    private T data;
}
