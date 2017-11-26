package com.pl10.presta.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "ocupation")
    private String ocupation;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>();
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    public User() {
        super();
    }

    public User(String username, String password, boolean enabled) {
        super();
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }


    public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
        super();
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    public User(String username, String fullname, String ocupation, String password, boolean enabled, Set<UserRole> userRole, Date createAt) {
        this.username = username;
        this.fullname = fullname;
        this.ocupation = ocupation;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
        this.createAt = createAt;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public String getCreateAtFormat(){
        return new SimpleDateFormat("dd, MMM. yyyy").format(createAt);
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
