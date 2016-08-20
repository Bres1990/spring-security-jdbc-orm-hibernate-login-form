package com.bres.siodme.web.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Adam on 2016-07-29.
 */

@Entity
@Table(name = "user")
public class User {
    private Long id;


    @Size(min=6, max=32, message="Username must fit between 6 and 32 letters") @NotEmpty
    @Column
    private String username;

    @Size(min=8, max=32, message="Password must fit between 8 and 32 letters") @NotEmpty
    private String password;

    private String passwordConfirm;

    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany//(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return String.format(
                "User [ id = '%d', username ='%s', password ='%s' ]",
                id, username, password);
    }
}

