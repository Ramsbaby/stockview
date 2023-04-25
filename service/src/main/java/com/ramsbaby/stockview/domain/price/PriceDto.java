package com.ramsbaby.stockview.domain.price;

import java.text.DecimalFormat;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : RAMSBABY
 * @date : 2023-04-22 오전 1:49:59
 */
@Getter
@Builder
public class PriceDto {

    private String code;

    private int price;

    private int changePrice;

    private double changeRate;

    static PriceDto of(int sourceDatePrice, Price targetDate) {
        return PriceDto.builder()
            .code(targetDate.getCode())
            .price(targetDate.getPrice())
            .changePrice(targetDate.getPrice() - sourceDatePrice)
            .changeRate(getChangeRate(targetDate.getPrice(), sourceDatePrice))
            .build();
    }

    public static double getChangeRate(int targetPrice, int sourcePrice) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(((double) (targetPrice - sourcePrice) / sourcePrice) * 100));
    }
}
