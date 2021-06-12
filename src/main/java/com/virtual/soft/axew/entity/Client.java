package com.virtual.soft.axew.entity;

import com.virtual.soft.axew.entity.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = -5455201925710969077L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROLES")
    private Set<Integer> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.REMOVE)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client () {
        addRole(RoleEnum.USER);
    }

    public Client (String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        addRole(RoleEnum.USER);
    }

    public Long getId () {
        return id;
    }

    public Client setId (Long id) {
        this.id = id;
        return this;
    }

    public String getName () {
        return name;
    }

    public Client setName (String name) {
        this.name = name;
        return this;
    }

    public String getCpf () {
        return cpf;
    }

    public Client setCpf (String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getEmail () {
        return email;
    }

    public Client setEmail (String email) {
        this.email = email;
        return this;
    }

    public String getPassword () {
        return password;
    }

    public Client setPassword (String password) {
        this.password = password;
        return this;
    }

    public String getPhone () {
        return phone;
    }

    public Client setPhone (String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getBirthDate () {
        return birthDate;
    }

    public Client setBirthDate (LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Set<RoleEnum> getRoles () {
        return roles.stream()
                .map(RoleEnum::toEnum)
                .collect(Collectors.toSet());
    }

    public void addRole (RoleEnum role) {
        roles.add(role.getId());
    }

    public Address getAddress () {
        return address;
    }

    public Client setAddress (Address address) {
        this.address = address;
        return this;
    }

    public List<Order> getOrders () {
        return orders;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id);
    }

    @Override
    public String toString () {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", roles=" + roles +
                ", address=" + address +
                ", orders=" + orders +
                '}';
    }
}
