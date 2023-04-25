package com.ramsbaby.stockview.domain.info;

import com.ramsbaby.stockview.common.entity.CommonEntity;
import com.ramsbaby.stockview.domain.tradehistory.Trade;
import com.ramsbaby.stockview.domain.viewcount.ViewCount;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 10:46:11
 */
@Getter
@Setter
@Entity(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {AuditingEntityListener.class})
public class Stock extends CommonEntity {

    @Id
    private String code;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_code", referencedColumnName = "code")
    private Trade trade;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "view_count_code", referencedColumnName = "code")
    private ViewCount viewCount;

}
