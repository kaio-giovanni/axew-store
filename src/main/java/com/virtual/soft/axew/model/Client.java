package com.virtual.soft.axew.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column
    private LocalDate birthDate;

    @OneToOne(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Address address;

    public Client () {
    }

    public Client (Long id, String name, String email, String phone, LocalDate birthDate, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate () {
        return birthDate;
    }

    public void setBirthDate (LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress () {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }
}
