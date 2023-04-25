package com.ramsbaby.stockview.domain.viewcount;

import static org.junit.jupiter.api.Assertions.*;

import com.ramsbaby.stockview.domain.info.Stock;
import com.ramsbaby.stockview.domain.info.StockRepository;
import com.ramsbaby.stockview.domain.info.StockService;
import com.ramsbaby.stockview.domain.tradehistory.Trade;
import com.ramsbaby.stockview.domain.tradehistory.TradeDto;
import com.ramsbaby.stockview.domain.tradehistory.TradeRepository;
import com.ramsbaby.stockview.domain.tradehistory.TradeService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 11:06:24
 */
@SpringBootTest
class ViewCountServiceTest {

    @Autowired
    private ViewCountService viewCountService;

    @Autowired
    private ViewCountRepository viewCountRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    final int TEST_NUMBER = 120;

    @Test
    void 가장_많은_조회수_조회_성공() {
        // given
        Random r = new Random();
        int testCount = 2222;
        List<Stock> stockList = stockService.getAllStocks(Pageable.ofSize(TEST_NUMBER)).getContent();
        stockList.forEach(stock ->
            stock.setViewCount(ViewCount.of(stock, testCount)));
        stockRepository.saveAll(stockList);

        // when
        List<ViewCountDto> viewCountDtoList = viewCountService.getMostViewed(Pageable.ofSize(TEST_NUMBER));

        // then
        Assertions.assertEquals(viewCountDtoList.get(0).getCount(), testCount);
    }

    @Test
    void 조회수_초기화_성공() {
        // given
        Random r = new Random();
        List<Stock> stockList = stockService.getAllStocks(Pageable.ofSize(TEST_NUMBER)).getContent();
        stockList.forEach(stock ->
            stock.setViewCount(ViewCount.of(stock, r.nextInt(10000) + 1)));
        stockRepository.saveAll(stockList);

        // when
        List<ViewCount> viewCountList = viewCountRepository.findAll(Pageable.ofSize(TEST_NUMBER)).getContent();

        // then
        Assertions.assertEquals(viewCountList.size(), TEST_NUMBER);
    }
}