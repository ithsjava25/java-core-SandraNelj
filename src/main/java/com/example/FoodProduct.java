package com.example;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable{
    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(UUID uuid, String name, Category category,
                       BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(uuid, name, category, price);
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        if (weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }
    @Override
    public BigDecimal weight() {
        return weight;
    }
    @Override
    public BigDecimal calculateShippingCost() {
    return weight.multiply (BigDecimal.valueOf(50));
    }
    @Override
    public String productDetails() {
    return "Food: " + name() + ", Expires: " + expirationDate;
    }
}