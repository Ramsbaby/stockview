package com.ramsbaby.stockview.common.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 11:06:34
 */
class PageParamConvertUtilTest {

    @Test
    void convertTest() {
        Pageable pageable = Pageable.ofSize(120);
        Pageable convertedPageable = PageParamConvertUtil.convert(pageable);
        assertEquals(pageable.getPageSize(), convertedPageable.getPageSize());
    }
}