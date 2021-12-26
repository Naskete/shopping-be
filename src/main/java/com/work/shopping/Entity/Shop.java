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
    @Column(name = "business")
    private String business;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shop_name) {
        this.shopName = shop_name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String bussiness) {
        this.business = bussiness;
    }

    @Override
    public String toString() {
        return "shop{" +
                "business='" + business + '\'' +
                ", shop_name='" + shopName + '\'' +
                '}';
    }
}
