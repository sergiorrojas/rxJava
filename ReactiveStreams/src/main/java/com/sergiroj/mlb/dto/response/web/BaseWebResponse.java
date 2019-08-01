package com.sergiroj.mlb.dto.response.web;

import com.sergiroj.mlb.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseWebResponse<T> {

    private T data;
    private ErrorCode errorCode;

    public static BaseWebResponse successNoData() {
        return BaseWebResponse.builder()
                .build();
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
        return BaseWebResponse.<T>builder()
                .data(data)
                .build();
    }

    public static BaseWebResponse error(ErrorCode errorCode) {
        return BaseWebResponse.builder()
                .errorCode(errorCode)
                .build();
    }
}