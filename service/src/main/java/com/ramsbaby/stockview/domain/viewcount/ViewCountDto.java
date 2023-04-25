package com.ramsbaby.stockview.domain.viewcount;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : RAMSBABY
 * @date : 2023-04-20 오전 12:49:40
 */
@Getter
@Builder
public class ViewCountDto {

    private String code;
    private Long count;

    static ViewCountDto of(ViewCount viewCount) {
        return ViewCountDto.builder()
            .code(viewCount.getCode())
            .count(viewCount.getCount())
            .build();
    }
}
