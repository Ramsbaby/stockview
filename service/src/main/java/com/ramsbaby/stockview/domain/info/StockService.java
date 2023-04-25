package com.ramsbaby.stockview.domain.info;

import com.ramsbaby.stockview.common.config.CacheConfig;
import com.ramsbaby.stockview.domain.price.PriceService;
import com.ramsbaby.stockview.domain.tradehistory.TradeService;
import com.ramsbaby.stockview.domain.viewcount.ViewCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 11:13:51
 */
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final PriceService priceService;
    private final TradeService tradeService;
    private final ViewCountService viewCountService;

    @Transactional(readOnly = true)
    @Cacheable(value = CacheConfig.STOCK_INFO_CACHE_NAME, key = "#pageable")
    public Page<Stock> getAllStocks(Pageable pageable) {
        return stockRepository.findAll(pageable);
    }

    @Transactional
    public void initData() {
        Page<Stock> stocks = this.getAllStocks(Pageable.ofSize(120));
        priceService.initTodayPrices();
        viewCountService.initViewCount(stocks);
        tradeService.initTrade(stocks);
    }

    @Transactional
    public boolean changeRank() {
        Page<Stock> stocks = this.getAllStocks(Pageable.ofSize(120));
        priceService.changeRandomTodayPrices();
        viewCountService.changeRandomViewCount(stocks);
        tradeService.changeRandomTrade(stocks);

        return true;
    }

    public AllTopResponse getAllTop5(Pageable pageable) {
        return AllTopResponse.builder()
            .viewCountTopList(viewCountService.getMostViewed(pageable))
            .priceIncreasedTopList(priceService.getTopChangedPriceStocks(Direction.DESC, pageable))
            .priceDecreasedTopList(priceService.getTopChangedPriceStocks(Direction.ASC, pageable))
            .tradeDtoTopList(tradeService.getTopVolumed(pageable))
            .build();
    }

}
