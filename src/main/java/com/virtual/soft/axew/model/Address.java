package com.virtual.soft.axew.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1646130179562554418L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Address () {
    }

    public Address (String street, String number, String district, String zipCode) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.zipCode = zipCode;
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

    public Address setClient (Client client) {
        this.client = client;
        return this;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id);
    }

    @Override
    public String toString () {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", district='" + district + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", client=" + client +
                '}';
    }
}
