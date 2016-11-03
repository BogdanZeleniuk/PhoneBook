package com.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]", message = "NotEmpty.userForm.login")
    @Length(min = 3, max = 50)
    protected String login;

    @Length(min = 5, max = 100, message = "NotEmpty.userForm.password")
    @NotEmpty
    private String password;

    @Length(min = 5, max = 100, message = "NotEmpty.userForm.fullName")
    private String fullName;

    public UserDTO() {
    }

    public UserDTO(Integer id, String login, String password, String fullName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
