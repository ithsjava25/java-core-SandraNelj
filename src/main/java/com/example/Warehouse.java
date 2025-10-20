package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static Warehouse instance;
    private final String name;
    private final List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }
    public static Warehouse getInstance() {
        return getInstance("Default");
    }

    public void clearProducts() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        boolean duplicate = products.stream()
                        .anyMatch(p->p.uuid().equals(product.uuid()));
        if (duplicate) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        products.add(product);
    }

    public void remove(UUID id) {
        products.removeIf(p -> p.uuid().equals(id));
    }

    public Optional<Product> getProductById(UUID id) {
        return products.stream().filter(p -> p.uuid().equals(id)).findFirst();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(Product::category));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Perishable> expiredProducts() {
        LocalDate today = LocalDate.now();
        return products.stream()
                .filter(p -> p instanceof Perishable per && per.expirationDate().isBefore(today))
                .map(p -> (Perishable) p)
                .toList();
    }

    public List<Shippable> shippableProducts() {
        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .toList();
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product product = getProductById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        product.setPrice(newPrice);
    }

}

