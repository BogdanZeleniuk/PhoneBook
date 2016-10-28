package com.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @NotEmpty
    @Column(name = "login", nullable = false)
    @Pattern(regexp = "[a-zA-Z]", message = "Choose the letters form 'a' to 'z'.")
    @Length(min = 3, max = 50)
    protected String login;

    public NamedEntity() {
    }

    protected NamedEntity(Integer id, String login) {
        super(id);
        this.login = login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    @Override
    public String toString() {
        return login;
    }
}
