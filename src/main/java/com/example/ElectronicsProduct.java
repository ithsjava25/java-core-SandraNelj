package com.example;
import java.math.BigDecimal;
import java.util.UUID;


public class ElectronicsProduct extends Product implements Shippable {
    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID uuid, String name, Category category,
                              BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(uuid, name, category, price);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }
    public int warrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public BigDecimal weight() {
        return weight;
    }
    @Override
    public BigDecimal calculateShippingCost() {
        double w = weight.doubleValue();
        BigDecimal cost = BigDecimal.valueOf(79);
        if (w > 5.0) cost = cost.add(BigDecimal.valueOf(49));
        return cost;
    }
    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}
