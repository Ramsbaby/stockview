package com.ramsbaby.stockview.domain.tradehistory;

import com.ramsbaby.stockview.common.entity.CommonEntity;
import com.ramsbaby.stockview.domain.info.Stock;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author : RAMSBABY
 * @date : 2023-04-22 오후 11:07:16
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "stock_trade")
public class Trade extends CommonEntity {

    @Id
    private String code;

    @Column
    private int volume;

    static Trade of(Stock stock, int volume) {
        return Trade.builder()
            .code(stock.getCode())
            .volume(volume)
            .build();
    }

}
