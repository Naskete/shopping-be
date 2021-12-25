package com.work.shopping.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "bussiness")
    private String bussiness;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shop_name) {
        this.shopName = shop_name;
    }

    public String getBussiness() {
        return bussiness;
    }

    public void setBussiness(String bussiness) {
        this.bussiness = bussiness;
    }

    @Override
    public String toString() {
        return "shop{" +
                "bussiness='" + bussiness + '\'' +
                ", shop_name='" + shopName + '\'' +
                '}';
    }
}
