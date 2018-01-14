package br.com.rbarbioni.spark.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    public Product() {
        super();
    }

    public Product(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
