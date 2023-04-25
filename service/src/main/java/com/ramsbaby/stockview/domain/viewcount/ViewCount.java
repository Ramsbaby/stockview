package com.ramsbaby.stockview.domain.viewcount;

import com.ramsbaby.stockview.common.entity.CommonEntity;
import com.ramsbaby.stockview.domain.info.Stock;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : RAMSBABY
 * @date : 2023-04-20 오전 12:23:30
 */
@Getter
@Setter
@Entity(name = "stock_view_count")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {AuditingEntityListener.class})
public class ViewCount extends CommonEntity {

    @Id
    private String code;

    @Column
    private long count;

    static ViewCount of(Stock stock, int count) {
        return ViewCount.builder()
            .code(stock.getCode())
            .count(count)
            .build();
    }
}
