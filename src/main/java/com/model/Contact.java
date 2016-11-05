package com.model;

import com.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;


@NamedQueries({
        @NamedQuery(name = Contact.GET, query = "SELECT cont FROM Contact cont WHERE cont.id=:id AND cont.user.id=:userId"),
        @NamedQuery(name = Contact.ALL_SORTED, query = "SELECT cont FROM Contact cont WHERE cont.user.id=:userId"),
        @NamedQuery(name = Contact.DELETE, query = "DELETE FROM Contact cont WHERE cont.id=:id AND cont.user.id=:userId"),
        @NamedQuery(name = Contact.GET_FILTERED, query = "SELECT cont FROM Contact cont WHERE cont.user.id=:userId " +
                "AND cont.firstName LIKE :fName AND cont.lastName LIKE :lName " +
                "AND cont.mobilePhone LIKE :mPhone"),
})
@Entity
@Table(name = "contacts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact extends BaseEntity{

    public static final String GET = "Contact.GET";
    public static final String ALL_SORTED = "Contact.ALL_SORTED";
    public static final String DELETE = "Contact.DELETE";
    public static final String GET_FILTERED = "Contact.GET_FILTERED";

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "First name should not be empty")
    @Length(min = 4, message = "More than 4 symbols")
    @JsonView(View.REST.class)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Last name should not be empty")
    @Length(min = 4, message = "More than 4 symbols")
    @JsonView(View.REST.class)
    private String lastName;

    @Column(name = "patronymic", nullable = false)
    @NotEmpty(message = "Patronymic should not be empty")
    @Length(min = 4, message = "More than 4 symbols")
    @JsonView(View.REST.class)
    private String patronymic;

    @Column(name = "mobile_phone_number", nullable = false)
    @NotEmpty(message = "Mobile Phone should not be empty")
    @Pattern(regexp = "\\+380\\([1-9]{2}\\)[0-9]{7}", message = "Something like +380(99)1234567")
    @JsonView(View.REST.class)
    private String mobilePhone;

    @Column(name = "home_phone_number")
    private String homePhone;

    @Column(name = "address")
    private String address;

    @Email(message = "Email should not be empty")
    @Column(name = "email", nullable = false)
    @JsonView(View.REST.class)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String patronymic, String mobilePhone, String homePhone, String address, String email ) {
        this(null,firstName,lastName,patronymic,mobilePhone,homePhone,address,email);
    }

    public Contact( Integer id, String firstName,  String lastName, String patronymic, String mobilePhone, String homePhone, String address, String email ) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.address = address;
        this.email = email;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
