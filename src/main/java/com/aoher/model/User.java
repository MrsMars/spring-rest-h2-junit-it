package com.aoher.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 7875622941513777868L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "User(id=" + var10000 + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", dateOfBirth=" + this.getDateOfBirth() + ")";
    }

    public User() {
    }
}
