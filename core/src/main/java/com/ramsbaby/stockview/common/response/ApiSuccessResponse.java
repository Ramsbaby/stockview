package com.ramsbaby.stockview.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiSuccessResponse {

    private Object results;

    public static ApiSuccessResponse success(Object results) {
        return ApiSuccessResponse.builder()
            .results(results)
            .build();
    }
}
