package com.health_eat.domain.token;

import com.health_eat.domain.token.Token;
import org.springframework.data.repository.CrudRepository;

public interface JwtRedisRepository extends CrudRepository<Token,String> {
}
