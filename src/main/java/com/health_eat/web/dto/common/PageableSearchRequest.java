package com.health_eat.web.dto.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableSearchRequest extends BaseSearchRequest{
    private int size = 10;
    private int page = 0;
}
