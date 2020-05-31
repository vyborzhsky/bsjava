package com.rkubyshkin.model;

public enum UnitType {
    FAMILY("Близкие родственники"), //LIST
    WORK("Работа"), //STRUCTIRELIST
    HOBBY("Хобби"), //TEXT
    EDUCATION("Образование"), //STRUCTURELIST
    LOCATION("Город проживания"), //TEXT
    PETS("Домашние животные");  // LIST

    private String infoTitle;

   UnitType(String infoTitle) {
        this.infoTitle = infoTitle;

    }

    public String getInfoTitle() {
        return infoTitle;
    }
}
