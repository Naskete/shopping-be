package com.work.shopping.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "data")
    private String data;
    @Column(name = "account")
    private String account;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

<<<<<<< HEAD
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
=======
    public String getAcount() {
        return account;
    }

    public void setAcount(String acount) {
        this.account = acount;
>>>>>>> 17860a85b76394ddc82389071de25b0b41e8b3ff
    }
}
