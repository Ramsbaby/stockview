package com.ramsbaby.stockview.domain.tradehistory;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 11:09:36
 */
@Repository
public interface TradeRepository extends PagingAndSortingRepository<Trade, String> {

}
