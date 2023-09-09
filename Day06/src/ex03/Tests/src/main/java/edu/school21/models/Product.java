package edu.school21.models;


import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Integer price;

    public Product(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) { return false; }
        return Objects.equals(id, ((Product) obj).id) &&
                Objects.equals(name, ((Product) obj).name) &&
                Objects.equals(price, ((Product) obj).price);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }

    public void setId(Long id) { this.id = id; }
    public void setPrice(Integer price) { this.price = price; }
    public void setId(String name) { this.name = name; }
}
