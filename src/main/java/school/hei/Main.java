package school.hei;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        DataRetriever dr = new DataRetriever();

        System.out.println("=== ALL CATEGORIES ===");
        dr.getAllCategories().forEach(System.out::println);

        System.out.println("\n=== PAGINATION EXAMPLE ===");
        dr.getProductList(1, 2).forEach(System.out::println);

        System.out.println("\n=== CRITERIA FILTER ===");
        dr.getProductsByCriteria("phone", null, null, null).forEach(System.out::println);

        System.out.println("\n=== CRITERIA + PAGINATION ===");
        dr.getProductsByCriteria(
                "phone", "Phone",
                Instant.parse("2025-01-01T00:00:00Z"),
                Instant.parse("2025-12-31T23:59:59Z"),
                1, 1
        ).forEach(System.out::println);
    }
}
