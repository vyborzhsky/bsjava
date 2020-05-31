package com.rkubyshkin.model;

public enum ContactsType {
    NUMBERPHONE("Телефон"),
    EMAIL("Email"),
    TELEGRAM("Telegram"),
    LINKEDIN("Ссылка на LinkedIn"),
    VK("Ссылка Vkontakte");

    private String contactsTitle;

    ContactsType(String ContactsTitle) {
        this.contactsTitle = ContactsTitle;
    }

}
