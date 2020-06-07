package com.health_eat.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
