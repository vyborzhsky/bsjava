package com.rkubyshkin.model;

public enum Contacts extends Structure {
    NUMBERPHONE("Телефон"),
    EMAIL("Email"),
    TELEGRAM("Telegram"),
    LINKEDIN("Ссылка на LinkedIn"),
    VK("Ссылка Vkontakte");

    private String contactsTitle;

    private String value;

    Contacts(String ContactsTitle) {
        this.contactsTitle = ContactsTitle;
    }

    public String getContactsValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
