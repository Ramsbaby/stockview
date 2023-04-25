package com.ramsbaby.stockview.domain.price;

import com.ramsbaby.stockview.common.advice.NotExistStockPriceException;
import com.ramsbaby.stockview.common.utils.PageParamConvertUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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
public class PriceService {

    private final PriceRepository priceRepository;

    private final double CHANGE_MAX_RATE = 0.3;

    @Transactional(readOnly = true)
    public List<PriceDto> getTopChangedPriceStocks(Direction sort, Pageable pageable) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Page<Price> result;

        Pageable param = PageParamConvertUtil.convert(pageable);

        if (sort.isDescending()) {
            result = priceRepository.findTopIncreasedPrice(today, yesterday, param);
        } else {
            result = priceRepository.findTopDecreasedPrice(today, yesterday, param);
        }

        List<Price> yesterdayPrice = priceRepository.findByCreatedAtEquals(yesterday)
            .orElseThrow(() -> new NotExistStockPriceException("Stock Price is not exist."));

        Map<String, Integer> yesterdayMap = yesterdayPrice.stream().collect(
            Collectors.toMap(Price::getCode, Price::getPrice));

        return result.map(todayPrice ->
                PriceDto.of(yesterdayMap.get(todayPrice.getCode()), todayPrice))
            .getContent();
    }

    public void initTodayPrices() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Price> yesterdayPrices = priceRepository.findByCreatedAtEquals(yesterday)
            .orElseThrow(() -> new NotExistStockPriceException("Stock Price is not exist."));
        List<Price> todayPrices = new ArrayList<>();
        Random r = new Random();

        yesterdayPrices.forEach(price -> {
            double randomRate = (1.0 + (r.nextDouble() * 2 - 1) * CHANGE_MAX_RATE);
            Price todayPrice = Price.of(price.getCode(), (int) Math.round(price.getPrice() * randomRate));
            todayPrices.add(todayPrice);
        });

        priceRepository.saveAll(todayPrices);
    }

    public void changeRandomTodayPrices() {
        LocalDate today = LocalDate.now();
        List<Price> todayPrices = priceRepository.findByCreatedAtEquals(today)
            .orElseThrow(() -> new NotExistStockPriceException("Stock Price is not exist."));
        Random r = new Random();

        todayPrices.forEach(price -> {
            double randomRate = (1.0 + (r.nextDouble() * 2 - 1) * CHANGE_MAX_RATE);
            price.setPrice((int) Math.round(price.getPrice() * randomRate));
        });
    }
}
