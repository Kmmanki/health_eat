package com.health_eat.web.dto.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseSearchRequest {
    private String keyWordType;
    private String keyWord;


}
