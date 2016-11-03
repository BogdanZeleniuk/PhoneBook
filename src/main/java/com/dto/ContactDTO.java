package com.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class ContactDTO {

    private Integer id;

    @NotEmpty(message = "First name should not be empty")
    @Length(min = 4, message = "More than 4 symbols")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Length(min = 4, message = "More than 4 symbols")
    private String lastName;

    @NotEmpty(message = "Patronymic should not be empty")
    @Length(min = 4, message = "More than 4 symbols")
    private String patronymic;

    @NotEmpty(message = "Mobile Phone should not be empty")
    @Pattern(regexp = "\\+380\\([1-9]{2}\\)[0-9]{7}", message = "Something like +380(99)1234567")
    private String mobilePhone;

    private String homePhone;

    private String address;

    @Email(message = "Email should not be empty")
    private String email;

    public ContactDTO( Integer id, String firstName, String lastName, String patronymic, String mobilePhone, String homePhone, String address, String email ) {
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
