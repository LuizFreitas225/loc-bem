package br.com.luiz.locbem.util;

import br.com.luiz.locbem.constant.DefaultValuePagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface PaginationUtil {

        static PageRequest configuringPageable(Integer page, Integer size, String sort, String orderBy) {

            return PageRequest.of(
                page == null ? DefaultValuePagination.PAGE : page,
                size == null ? DefaultValuePagination.QUANTITY_ELEMENTS : size,
                sort == null || sort.isEmpty() ? Sort.Direction.valueOf(DefaultValuePagination.SORT) : Sort.Direction.valueOf(sort.toUpperCase()),
                orderBy == null || orderBy.isEmpty()  ? DefaultValuePagination.ORDER_BY : orderBy
            );
        }
}
