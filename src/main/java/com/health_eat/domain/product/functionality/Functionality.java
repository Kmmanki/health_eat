package com.health_eat.domain.product.functionality;

import com.health_eat.domain.BaseTimeEntity;
import com.health_eat.domain.DeleteState;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
public class Functionality extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Enumerated(EnumType.STRING)
    DeleteState deleteState;
    String unit;
    @ColumnDefault("0")
    Float dayHighLimit;
    @ColumnDefault("0")
    Float dayLowLimit;
    String functionality_text;
}
