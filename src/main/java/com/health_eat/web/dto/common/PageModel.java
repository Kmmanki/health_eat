package com.health_eat.web.dto.common;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class PageModel {
    List<?> list;
    long page;
    long size;
    long totalCount;

    public PageModel(Page<?> page){
        this.list = page.getContent();
        this.page = page.getPageable().getPageNumber();
        this.size = page.getSize();
        this.totalCount = page.getTotalElements();
    }
}
