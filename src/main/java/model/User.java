package model;

public class User {

    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private String firstName;
    private Integer userId;
    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User(String firstName, String lastName, String address, String city, String telephone, Integer userId, Pet pet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.userId = userId;
        this.pet = pet;
    }

    public User(String lastName, String address, String city, String telephone, String firstName, Integer userId) {
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.firstName = firstName;
        this.userId = userId;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
