package com.ramsbaby.stockview.domain.tradehistory;

import com.ramsbaby.stockview.common.utils.PageParamConvertUtil;
import com.ramsbaby.stockview.domain.info.Stock;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : RAMSBABY
 * @date : 2023-04-20 오전 12:45:50
 */
@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    @Transactional(readOnly = true)
    public List<TradeDto> getTopVolumed(Pageable pageable) {
        Pageable param = PageParamConvertUtil.convert(pageable, "volume", Direction.DESC);
        return tradeRepository.findAll(param).map(TradeDto::of).getContent();
    }

    public void initTrade(Page<Stock> stockList) {
        Random r = new Random();

        stockList.forEach(stock -> stock.setTrade(Trade.of(stock, r.nextInt(100) + 1)));
    }

    public void changeRandomTrade(Page<Stock> stocks) {
        Random r = new Random();

        stocks.forEach(stock -> stock.getTrade().setVolume(r.nextInt(100) + 1));
    }
}
