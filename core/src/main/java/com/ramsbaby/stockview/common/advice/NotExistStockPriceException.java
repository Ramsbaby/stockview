package com.ramsbaby.stockview.common.advice;

import lombok.Getter;

@Getter
public class NotExistStockPriceException extends RuntimeException {

    public NotExistStockPriceException(String message) {
        super(message);
    }
}
