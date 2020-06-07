package com.health_eat.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum DeleteState {
    Y(true),
    N(false);

    private final Boolean isDeleted;

    DeleteState(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
}
