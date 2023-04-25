package com.ramsbaby.stockview.domain.viewcount;

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
public class ViewCountService {

    private final ViewCountRepository viewCountRepository;

    @Transactional(readOnly = true)
    public List<ViewCountDto> getMostViewed(Pageable pageable) {
        Pageable param = PageParamConvertUtil.convert(pageable, "count", Direction.DESC);
        return viewCountRepository.findAll(param).map(ViewCountDto::of).getContent();
    }

    public void initViewCount(Page<Stock> stocks) {
        Random r = new Random();

        stocks.forEach(stock ->
            stock.setViewCount(ViewCount.of(stock, r.nextInt(10000) + 1)));
    }

    public void changeRandomViewCount(Page<Stock> stocks) {
        Random r = new Random();

        stocks.forEach(stock -> stock.getViewCount().setCount(r.nextInt(10000) + 1));
    }
}
