package com.ramsbaby.stockview.domain.price;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 11:09:36
 */
@Repository
public interface PriceRepository extends PagingAndSortingRepository<Price, Long> {

    Page<Price> findAll(Pageable pageable);

    Optional<List<Price>> findByCreatedAtEquals(LocalDate createdAt);

    @Query(value = "SELECT p.* FROM stock_price p, "
        + "(SELECT price, code FROM stock_price "
        + "WHERE created_at = :yesterday) p2 "
        + "WHERE p.code = p2.code AND p.created_at = :today "
        + "ORDER BY ((CAST(p.price AS FLOAT) - CAST(p2.price AS FLOAT)) / CAST(p2.price AS FLOAT) * 100) ASC ",
        nativeQuery = true)
    Page<Price> findTopDecreasedPrice(LocalDate today, LocalDate yesterday, Pageable pageable);

    @Query(value = "SELECT p.* FROM stock_price p, "
        + "(SELECT price, code FROM stock_price "
        + "WHERE created_at = :yesterday) p2 "
        + "WHERE p.code = p2.code AND p.created_at = :today "
        + "ORDER BY ((CAST(p.price AS FLOAT) - CAST(p2.price AS FLOAT)) / CAST(p2.price AS FLOAT) * 100) DESC ",
        nativeQuery = true)
    Page<Price> findTopIncreasedPrice(LocalDate today, LocalDate yesterday, Pageable pageable);

}
