package com.example.demo.enums;


import lombok.Getter;

@Getter

public enum PhoneType {

    HOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

    private PhoneType(final String description) {
        this.description = description;
    }
 
}
