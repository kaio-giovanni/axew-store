package com.virtual.soft.axew.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -6195591757514692734L;

    @EmbeddedId
    private OrderItemKey id = new OrderItemKey();

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    public OrderItem () {
    }

    public OrderItem (Order order, Product product, Integer quantity, Double price) {
        id.setProduct(product);
        id.setOrder(order);
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder () {
        return id.getOrder();
    }

    public void setOrder (Order order) {
        id.setOrder(order);
    }

    public Product getProduct () {
        return id.getProduct();
    }

    public void setProduct (Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity () {
        return quantity;
    }

    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice () {
        return price;
    }

    public void setPrice (Double price) {
        this.price = price;
    }

    public Double getSubTotal () {
        return price * quantity;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id);
    }

    @Override
    public String toString () {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
