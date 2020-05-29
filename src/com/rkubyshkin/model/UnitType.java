package com.rkubyshkin.model;

public enum UnitType {
    FAMILY("Близкие родственники"),
    WORK("Работа"),
    HOBBY("Хобби"),
    EDUCATION("Образование"),
    LOCATION("Город проживания"),
    PETS("Домашние животные");

    private String infoTitle;

   UnitType(String infoTitle) {
        this.infoTitle = infoTitle;

    }

    public String getInfoTitle() {
        return infoTitle;
    }
}
