package com.ramsbaby.stockview.domain.info;

import com.ramsbaby.stockview.common.response.ApiSuccessResponse;
import com.ramsbaby.stockview.domain.price.PriceService;
import com.ramsbaby.stockview.domain.tradehistory.TradeService;
import com.ramsbaby.stockview.domain.viewcount.ViewCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 11:12:37
 */
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockService stockService;
    private final PriceService priceService;
    private final TradeService tradeService;
    private final ViewCountService viewCountService;

    @GetMapping("/hot")
    public ApiSuccessResponse getMostViewedStocks(@PageableDefault(size = 20) Pageable pageable) {
        return ApiSuccessResponse.success(viewCountService.getMostViewed(pageable));
    }

    @GetMapping("/top-volume")
    public ApiSuccessResponse getTopVolumeStocks(@PageableDefault(size = 20) Pageable pageable) {
        return ApiSuccessResponse.success(tradeService.getTopVolumed(pageable));
    }

    @GetMapping("/top-increase")
    public ApiSuccessResponse getTopIncreasedStocks(@PageableDefault(size = 20) Pageable pageable) {
        return ApiSuccessResponse.success(priceService.getTopChangedPriceStocks(Direction.DESC, pageable));
    }

    @GetMapping("/top-decrease")
    public ApiSuccessResponse getTopDecreasedStocks(@PageableDefault(size = 20) Pageable pageable) {
        return ApiSuccessResponse.success(priceService.getTopChangedPriceStocks(Direction.ASC, pageable));
    }

    @GetMapping("/all/top5")
    public ApiSuccessResponse getAllTop5(@PageableDefault(size = 5) Pageable pageable) {
        return ApiSuccessResponse.success(stockService.getAllTop5(pageable));
    }

    @PutMapping("/changeRank")
    public ApiSuccessResponse random() {
        return ApiSuccessResponse.success(stockService.changeRank());
    }
}
