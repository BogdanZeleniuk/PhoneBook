package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String mobilePhone;
    private String homePhone;
    private String address;
    private String email;

    public ContactDTO(@JsonProperty("id") Integer id,
                      @JsonProperty("firstName") String firstName,
                      @JsonProperty("lastName") String lastName,
                      @JsonProperty("patronymic") String patronymic,
                      @JsonProperty("mobilePhone") String mobilePhone,
                      @JsonProperty("homePhone") String homePhone,
                      @JsonProperty("address") String address,
                      @JsonProperty("email") String email ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.address = address;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
