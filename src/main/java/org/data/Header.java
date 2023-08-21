package org.data;

public class Header {

    private final String name;
    private final String value;

    Header(String name, String value) {

        this.name = name;
        this.value = value;
    }

    public String getName() {

        return name;
    }

    public String getValue() {

        return value;
    }
}