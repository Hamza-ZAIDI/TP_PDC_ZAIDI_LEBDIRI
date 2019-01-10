package com.company.model.AppConfig;

public enum Malus {
    MULTI_CHANCE (2) , PROPOSITION(1);

    private int value;

    Malus(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
