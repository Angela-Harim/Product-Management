package school.hei;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    private final DBConnection dbConnection;

    public DataRetriever() {
        this.dbConnection = new DBConnection();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT id, name FROM Product_category";

        try (Connection conn = dbConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    public List<Product> getProductList(int page, int size) {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * size;

        String sql = """
                SELECT p.id, p.name, p.creation_datetime,
                       c.id AS cat_id, c.name AS cat_name
                FROM Product p
                LEFT JOIN Product_category c ON p.id = c.product_id
                ORDER BY p.id
                LIMIT ? OFFSET ?
                """;

        try (Connection conn = dbConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, size);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    Category category = new Category(
                            rs.getInt("cat_id"),
                            rs.getString("cat_name")
                    );

                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getTimestamp("creation_datetime").toInstant(),
                            category
                    );

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    public List<Product> getProductsByCriteria(
            String productName,
            String categoryName,
            Instant creationMin,
            Instant creationMax
    ) {

        List<Product> products = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
                SELECT p.id, p.name, p.creation_datetime,
                       c.id AS cat_id, c.name AS cat_name
                FROM Product p
                LEFT JOIN Product_category c ON p.id = c.product_id
                WHERE 1=1
                """);

        if (productName != null) {
            sql.append(" AND p.name ILIKE ?");
            params.add("%" + productName + "%");
        }

        if (categoryName != null) {
            sql.append(" AND c.name ILIKE ?");
            params.add("%" + categoryName + "%");
        }

        if (creationMin != null) {
            sql.append(" AND p.creation_datetime >= ?");
            params.add(Timestamp.from(creationMin));
        }

        if (creationMax != null) {
            sql.append(" AND p.creation_datetime <= ?");
            params.add(Timestamp.from(creationMax));
        }

        try (Connection conn = dbConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    Category category = new Category(
                            rs.getInt("cat_id"),
                            rs.getString("cat_name")
                    );

                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getTimestamp("creation_datetime").toInstant(),
                            category
                    );

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }
}
