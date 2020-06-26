package com.health_eat.domain.token;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash("token")
public class Token implements Serializable {
    @Id
    private String jwt;
    private LocalDateTime cratedAt;

    @TimeToLive
    public long timeToLive(){
        return 60 *60 *2;
    }

    @Builder
    public Token (String jwt, LocalDateTime cratedAt){
        this.jwt = jwt;
        this.cratedAt = cratedAt;
    }
}
