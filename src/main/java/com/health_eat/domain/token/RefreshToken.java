package com.health_eat.domain.token;

import com.health_eat.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Entity
public class RefreshToken extends BaseTimeEntity {
    @Id
    private String accessToken;
    private String refreshToken;


    @Builder
    public RefreshToken (String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public RefreshToken updateAcessToken(String accessToken){
        this.accessToken = accessToken;
        return this;
    }
}
