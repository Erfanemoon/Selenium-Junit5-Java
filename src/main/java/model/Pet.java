package model;

public class Pet {
    private PetEnum petEnum;
    private String birthDate;
    private String name;

    public Pet(PetEnum petEnum, String birthDate, String name) {
        this.petEnum = petEnum;
        this.birthDate = birthDate;
        this.name = name;
    }


    public PetEnum getPetEnum() {
        return petEnum;
    }

    public void setPetEnum(PetEnum petEnum) {
        this.petEnum = petEnum;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
