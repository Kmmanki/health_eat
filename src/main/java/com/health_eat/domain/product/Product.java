package com.health_eat.domain.product;

import com.health_eat.domain.BaseTimeEntity;
import com.health_eat.domain.DeleteState;
import com.health_eat.domain.product.brand.ProductBrand;
import com.health_eat.domain.product.functionality.Functionality;
import com.health_eat.domain.product.nutrient.Nutrient;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
public class Product extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Enumerated(EnumType.STRING)
    DeleteState deleteState;
    @OneToOne
    ProductBrand productBrand;
    @OneToMany
    List<Nutrient> nutrient;
    @OneToMany
    List<Functionality> functionality;
    String productName;
    String intakeWay;
    Long shelfLifeMonth;
    String manufacturingNumber;
    String functionalityText;
    String storageWay;
    String licenseNumber;
    String packingMaterial;
    String intakePrecaution;
    String standardSpecification;
    String properties;
    String shape;
    String functionalityNutrient_tmp;
    String brandTmp;

}
