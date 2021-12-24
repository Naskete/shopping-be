package com.work.shopping.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "orders")
public class orders {
    @Id
    @Column(name = "account")
    private BigInteger account;
    @Column(name = "id")
    private String id;
    @Column(name = "data")
    private String data;
    @Column(name = "destination")
    private String destination;

    public BigInteger getAccount() {
        return account;
    }

    public void setAccount(BigInteger account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "orders{" +
                "account=" + account +
                ", id='" + id + '\'' +
                ", data=" + data +
                ", destination='" + destination + '\'' +
                '}';
    }
}

