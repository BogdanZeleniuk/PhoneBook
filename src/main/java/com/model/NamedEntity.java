package com.model;

import com.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @NotEmpty (message = "Password should not be empty")
    @Column(name = "login", nullable = false, unique=true)
    @Pattern(regexp = "[a-zA-Z]{3,50}", message = "Only English letters")
    @Length(min = 3, max = 50, message = "More than 3 symbols")
    @JsonView(View.REST.class)
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
