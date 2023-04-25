package com.ramsbaby.stockview.domain.price;

import com.ramsbaby.stockview.common.entity.CommonEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
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
@Builder
@Table(indexes = {@Index(name = "indexKey", columnList = "createdAt, code")})
@Entity(name = "stock_price")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {AuditingEntityListener.class})
public class Price extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private int price;

    static Price of(String code, int price) {
        return Price.builder()
            .code(code)
            .price(price)
            .build();
    }
}
