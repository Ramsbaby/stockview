package com.ramsbaby.stockview.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 10:31:50
 */
public class PageParamConvertUtil {

    public static Pageable convert(Pageable pageable, String field, Direction direction) {
        Sort sort = direction.equals(Direction.ASC)
            ? Sort.by(field).ascending()
            : Sort.by(field).descending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    public static Pageable convert(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
    }
}
