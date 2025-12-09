package school.hei;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {

        DataRetriever dr = new DataRetriever();

        // === 1. Afficher toutes les catégories ===
        System.out.println("=== ALL CATEGORIES ===");
        dr.getAllCategories().forEach(System.out::println);

        // === 2. Afficher les produits avec pagination simple ===
        System.out.println("\n=== PRODUCT LIST (page 1, size 2) ===");
        dr.getProductList(1, 2).forEach(System.out::println);

        System.out.println("\n=== PRODUCT LIST (page 2, size 2) ===");
        dr.getProductList(2, 2).forEach(System.out::println);

        // === 3. Filtrer par critères sans pagination ===
        System.out.println("\n=== FILTER: productName contains 'phone' ===");
        dr.getProductsByCriteria("phone", null, null, null)
                .forEach(System.out::println);

        System.out.println("\n=== FILTER: categoryName = 'Accessory' ===");
        dr.getProductsByCriteria(null, "Accessory", null, null)
                .forEach(System.out::println);

        System.out.println("\n=== FILTER: creation between 2025-01-01 and 2025-03-01 ===");
        dr.getProductsByCriteria(
                null,
                null,
                Instant.parse("2025-01-01T00:00:00Z"),
                Instant.parse("2025-03-01T00:00:00Z")
        ).forEach(System.out::println);
    }
}
