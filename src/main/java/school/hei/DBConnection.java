package school.hei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String url = "jdbc:postgresql://localhost:5432/product_management_db";
    private final String user = "product_manager_user";
    private final String password = "123456";

    public Connection getDBConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base", e);
        }
    }
}
