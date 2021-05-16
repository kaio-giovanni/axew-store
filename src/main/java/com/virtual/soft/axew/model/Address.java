package com.virtual.soft.axew.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String district;

    @Column
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Address () {
    }

    public Address (Long id, String street, String number, String district, String zipCode, Client client) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.district = district;
        this.zipCode = zipCode;
        this.client = client;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getStreet () {
        return street;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    public String getNumber () {
        return number;
    }

    public void setNumber (String number) {
        this.number = number;
    }

    public String getDistrict () {
        return district;
    }

    public void setDistrict (String district) {
        this.district = district;
    }

    public String getZipCode () {
        return zipCode;
    }

    public void setZipCode (String zipCode) {
        this.zipCode = zipCode;
    }

    public Client getClient () {
        return client;
    }

    public void setClient (Client client) {
        this.client = client;
    }
}
