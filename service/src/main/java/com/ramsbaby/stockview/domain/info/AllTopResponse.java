package com.ramsbaby.stockview.domain.info;

import com.ramsbaby.stockview.domain.price.PriceDto;
import com.ramsbaby.stockview.domain.tradehistory.TradeDto;
import com.ramsbaby.stockview.domain.viewcount.ViewCountDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 9:40:34
 */
@Getter
@Builder
public class AllTopResponse {

    List<PriceDto> priceDecreasedTopList;
    List<PriceDto> priceIncreasedTopList;
    List<ViewCountDto> viewCountTopList;
    List<TradeDto> tradeDtoTopList;
}
