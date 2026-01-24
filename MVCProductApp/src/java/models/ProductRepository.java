package models;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {
    private final List<Product> data = Collections.synchronizedList(new ArrayList<>());
    private int sequence = 1;

    public ProductRepository() {
        add(new Product(0, "Pensil 2B", "Stationery", 3000, 100, "Pensil untuk ujian"));
        add(new Product(0, "Buku Tulis", "Stationery", 7000, 50, "Buku 40 lembar"));
    }

    public List<Product> findAll() { synchronized(data) { return new ArrayList<>(data); } }

    public List<Product> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return findAll();
        String k = keyword.toLowerCase();
        synchronized(data) {
            return data.stream()
                .filter(p -> (p.getName()!=null && p.getName().toLowerCase().contains(k)) ||
                             (p.getCategory()!=null && p.getCategory().toLowerCase().contains(k)))
                .collect(Collectors.toList());
        }
    }

    public Product findById(int id) {
        synchronized(data) {
            return data.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        }
    }

    public Product add(Product p) {
        synchronized(data) {
            p.setId(sequence++);
            data.add(p);
            return p;
        }
    }

    public boolean update(Product p) {
        synchronized(data) {
            Product old = findById(p.getId());
            if (old == null) return false;
            old.setName(p.getName());
            old.setCategory(p.getCategory());
            old.setPrice(p.getPrice());
            old.setStock(p.getStock());
            old.setDescription(p.getDescription());
            return true;
        }
    }

    public boolean delete(int id) {
        synchronized(data) {
            return data.removeIf(p -> p.getId() == id);
        }
    }
}