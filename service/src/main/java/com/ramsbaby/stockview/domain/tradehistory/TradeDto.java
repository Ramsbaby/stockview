package com.ramsbaby.stockview.domain.tradehistory;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : RAMSBABY
 * @date : 2023-04-20 오전 12:49:40
 */
@Getter
@Builder
public class TradeDto {

    private String code;
    private int volume;

    static TradeDto of(Trade trade) {
        return TradeDto.builder()
            .code(trade.getCode())
            .volume(trade.getVolume())
            .build();
    }
}
