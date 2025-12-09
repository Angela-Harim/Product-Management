package school.hei;

import java.time.Instant;

public class Product {
    private int id;
    private String name;
    private Instant creationDatetime;
    private Category category;

    public Product() {}

    public Product(int id, String name, Instant creationDatetime, Category category) {
        this.id = id;
        this.name = name;
        this.creationDatetime = creationDatetime;
        this.category = category;
    }

    // --- Getters ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreationDatetime() {
        return creationDatetime;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryName() {
        return (category != null) ? category.getName() : null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDatetime=" + creationDatetime +
                ", category=" + (category != null ? category.getName() : "null") +
                '}';
    }
}
