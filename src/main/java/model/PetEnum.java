package model;

import java.util.Objects;

public enum PetEnum {
    UNDEFINED("undefined"),
    DOG("dog"),
    BIRD("bird"),
    CAT("cat"),
    HAMSTER("hamster"),
    LIZARD("lizard"),
    SNAKE("snake");

    private String name;

    PetEnum(String name) {
        this.name = name;
    }

    public static PetEnum get(String value) {
        if (value.isEmpty()) {
            return UNDEFINED;
        }
        PetEnum[] arr$ = values();
        for (PetEnum val : arr$) {
            if (Objects.equals(val.name, value)) {
                return val;
            }
        }
        return UNDEFINED;
    }

    public String getName() {
        return name;
    }
}
