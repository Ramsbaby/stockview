package com.ramsbaby.stockview.domain.price;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ramsbaby.stockview.common.advice.NotExistStockPriceException;
import com.ramsbaby.stockview.domain.info.StockService;
import com.ramsbaby.stockview.domain.tradehistory.TradeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 11:06:05
 */
@SpringBootTest
class PriceServiceTest {

    @Autowired
    private StockService stockService;

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    public void setUp() {
//        stockService.initData();
    }

    @Test
    void 없는_날짜_검색() {
        //given
        LocalDate today = LocalDate.now();

        //stub
        String exceptionMessage = "Stock Price is not exist.";
        when(priceRepository.findByCreatedAtEquals(today)).thenThrow(
            new NotExistStockPriceException(exceptionMessage));

        //when
        RuntimeException exception = assertThrows(NotExistStockPriceException.class,
            () -> priceService.initTodayPrices());


        //then
        assertEquals(exception.getMessage(), exceptionMessage);
    }

    @Test
    void 정상_날짜_검색() {
        //given
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Price> list = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            list.add(mock(Price.class));
        }
        given(priceRepository.findByCreatedAtEquals(any())).willReturn(Optional.of(list));

        //when
        Optional<List<Price>> yesterdayPrice = priceRepository.findByCreatedAtEquals(yesterday);

        //then
        assertEquals(yesterdayPrice.get().size(), 120);
    }

    @Test
    void changeRandomTodayPrices() {
    }
}