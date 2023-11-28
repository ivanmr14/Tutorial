package com.ccsw.springboot.author.model;

import com.ccsw.springboot.common.pagination.PageableRequest;

/**
 * @author ccsw
 *
 */
public class AuthorSearchDto {

    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}
