package com.health_eat.domain.product.brand;

import com.health_eat.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProductBrand extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 30, nullable = false)
    String bandName;

    @Builder
    public ProductBrand(Long id, String brandName){
        this.id = id;
        this.bandName = brandName;
    }
}
