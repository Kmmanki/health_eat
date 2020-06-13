package com.health_eat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result {
    private Long code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @Builder
    public Result(Long code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result OK(Object data){
        return Result.builder()
                .code(200L)
                .data(data)
                .msg("SUCCESS")
                .build();
    }
    public static Result ERROR(String msg){
        return Result.builder()
                .code(400L)
                .msg(msg)
                .build();
    }
}
