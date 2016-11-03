package com.model;

import com.View;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(name = User.GRAPH_WITH_ROLES, attributeNodes = @NamedAttributeNode("roles")),
        @NamedEntityGraph(name = User.GRAPH_WITH_ROLES_AND_CONTACTS, attributeNodes =
                {
                        @NamedAttributeNode("roles"),
                        @NamedAttributeNode("contacts")
                })
})
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_LOGIN, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.login=:login"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.login"),
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")})
public class User extends NamedEntity{

    public static final String GRAPH_WITH_ROLES = "User.WithRoles";
    public static final String GRAPH_WITH_ROLES_AND_CONTACTS = "User.WithRolesAndContacts";
    public static final String DELETE = "User.DELETE";
    public static final String BY_LOGIN = "User.BY_LOGIN";
    public static final String ALL_SORTED = "User.All_SORTED";


    @Column(name = "password", nullable = false)
    @Length(min = 5, max = 100, message = "More than 5 symbols")
    @JsonView(View.REST.class)
    @NotEmpty (message = "Password should not be empty")
    private String password;

    @Column(name = "full_name", nullable = false)
    @Length(min = 5, max = 100, message = "More than 5 symbols")
    @JsonView(View.REST.class)
    @NotEmpty (message = "Password should not be empty")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.LAZY)
    protected Set<Role> roles;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("firstName DESC")
    @JsonManagedReference
    protected List<Contact> contacts;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getLogin(), u.getPassword(), u. getFullName(), u.getRoles());
    }

    public User(Integer id, String login, String password, String fullName, Role role, Role... roles) {
        this(id, login, password, fullName, EnumSet.of(role, roles));
    }

    public User(Integer id, String login, String password, String fullName, Set<Role> roles) {
        super(id, login);
        this.password = password;
        this.fullName = fullName;
        setRoles(roles);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
