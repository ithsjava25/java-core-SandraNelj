package com.example;
import java.util.*;

public class Category {
    private static final Map<String, Category> CACHE = new HashMap<>();
    private final String name;

    private Category(String name) {
        this.name = name;
    }
    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name cannot be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be blank");
        }
        String normalized = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();
        return CACHE.computeIfAbsent(normalized, Category::new);
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
