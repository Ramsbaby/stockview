package com.ramsbaby.stockview.domain.tradehistory;

import static org.junit.jupiter.api.Assertions.*;

import com.ramsbaby.stockview.domain.info.Stock;
import com.ramsbaby.stockview.domain.info.StockRepository;
import com.ramsbaby.stockview.domain.info.StockService;
import com.ramsbaby.stockview.domain.price.PriceRepository;
import com.ramsbaby.stockview.domain.price.PriceService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 11:06:17
 */
@SpringBootTest
class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    final int TEST_NUMBER = 120;

    @Test
    void 가장_많은_거래량_성공() {
        // given
        Random r = new Random();
        List<Stock> stockList = stockService.getAllStocks(Pageable.ofSize(TEST_NUMBER)).getContent();
        stockList.forEach(stock -> stock.setTrade(Trade.of(stock, r.nextInt(100) + 1)));
        stockRepository.saveAll(stockList);

        // when
        List<TradeDto> tradeDtos = tradeService.getTopVolumed(Pageable.ofSize(TEST_NUMBER));

        // then
        Assertions.assertEquals(tradeDtos.size(), TEST_NUMBER);
    }

    @Test
    void 거래량_초기화_성공() {
        // given
        Page<Stock> stockList = stockService.getAllStocks(Pageable.ofSize(TEST_NUMBER));
        Random r = new Random();
        stockList.forEach(stock -> stock.setTrade(Trade.of(stock, r.nextInt(100) + 1)));
        stockRepository.saveAll(stockList);

        // when
        Page<Trade> trades = tradeRepository.findAll(Pageable.ofSize(TEST_NUMBER));

        // then
        Assertions.assertEquals(trades.getContent().size(), TEST_NUMBER);
    }
}