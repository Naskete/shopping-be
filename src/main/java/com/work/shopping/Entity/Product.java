package com.work.shopping.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "name")
    private String name;
    @Column(name = "shop")
    private String shop;
    @Column(name = "description")
    private String description;
    @Column(name = "img")
    private String img;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    private boolean status;

    public Product() {}
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", shop=" + shop +
                ", name=" + name +
                ", description=" + description +
                ", img=" + img +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
