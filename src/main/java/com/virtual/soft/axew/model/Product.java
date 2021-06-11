package com.virtual.soft.axew.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 2974634322300116190L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column
    private String imgUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product () {
    }

    public Product (String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId () {
        return id;
    }

    public Product setId (Long id) {
        this.id = id;
        return this;
    }

    public String getName () {
        return name;
    }

    public Product setName (String name) {
        this.name = name;
        return this;
    }

    public String getDescription () {
        return description;
    }

    public Product setDescription (String description) {
        this.description = description;
        return this;
    }

    public Double getPrice () {
        return price;
    }

    public Product setPrice (Double price) {
        this.price = price;
        return this;
    }

    public String getImgUrl () {
        return imgUrl;
    }

    public Product setImgUrl (String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Set<Category> getCategories () {
        return categories;
    }

    public Product setCategories (Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Set<Order> getOrders () {
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id);
    }

    @Override
    public String toString () {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", categories=" + categories +
                ", items=" + items +
                '}';
    }
}
