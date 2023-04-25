package com.ramsbaby.stockview.domain.viewcount;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 11:09:36
 */
@Repository
public interface ViewCountRepository extends PagingAndSortingRepository<ViewCount, String> {

}
