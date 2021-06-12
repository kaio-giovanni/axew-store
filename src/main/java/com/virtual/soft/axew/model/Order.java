package com.virtual.soft.axew.model;

import com.virtual.soft.axew.model.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -125525506440689739L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant moment;

    @Column(nullable = false)
    private Integer orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order () {
    }

    public Order (Instant moment, OrderStatus orderStatus, Client client) {
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Instant getMoment () {
        return moment;
    }

    public void setMoment (Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus () {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus (OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public Client getClient () {
        return client;
    }

    public void setClient (Client client) {
        this.client = client;
    }

    public Set<OrderItem> getItems () {
        return items;
    }

    public double getTotal () {
        var total = 0.0;
        for (OrderItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id);
    }

    @Override
    public String toString () {
        return "Order{" +
                "id=" + id +
                ", moment=" + moment +
                ", orderStatus=" + orderStatus +
                ", client=" + client +
                ", items=" + items +
                '}';
    }
}

