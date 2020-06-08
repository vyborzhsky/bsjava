package com.rkubyshkin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

public class TextUnit extends Unit{
    private static final long serialVersionUID = 1L;

    private  String textContent;

    public String getTextContent() {
        return textContent;
    }

    public TextUnit() {
    }

    public TextUnit(String textContent) {
        Objects.requireNonNull(textContent, "textContent must not be null");
        this.textContent = textContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextUnit textUnit = (TextUnit) o;
        return Objects.equals(textContent, textUnit.textContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textContent);
    }
}
