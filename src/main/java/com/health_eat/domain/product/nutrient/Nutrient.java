package com.health_eat.domain.product.nutrient;

import com.health_eat.domain.BaseTimeEntity;
import com.health_eat.domain.DeleteState;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
public class Nutrient extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Enumerated(EnumType.STRING)
    DeleteState deleteState;
    @Column(length = 30)
    String nutrientName;
    @Column(length = 10)
    String unit;
    @ColumnDefault("0")
    Float dayHighLimit;
    @ColumnDefault("0")
    Float dayLowLimit;
    @Column(columnDefinition = "Text")
    String functionalityText;

}
