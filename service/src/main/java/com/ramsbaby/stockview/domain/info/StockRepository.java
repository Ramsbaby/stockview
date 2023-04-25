package com.ramsbaby.stockview.domain.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 11:09:36
 */
@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, String> {

    Page<Stock> findAll(Pageable pageable);
}
