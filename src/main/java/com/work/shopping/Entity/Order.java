package com.work.shopping.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
<<<<<<< HEAD
    private BigInteger id;
    @Column(name = "account")
    private String account;
=======
    BigInteger id;
    @Column(name = "account")
    String acount;
>>>>>>> 17860a85b76394ddc82389071de25b0b41e8b3ff
    @Column(name = "data")
    private String data;
    @Column(name = "destination")
    private String destination;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
}
