package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbModule extends AbstractModule {

    private Connection connection;
    private Driver mysqlDriver = null;
    @Provides
    private Connection getConnection() {
        if (connection == null) {
            try {
                mysqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mysqlDriver);

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3308/java" +
                                "?useUnicode=true&characterEncoding=utf8",
                        "user222",
                        "pass222");

            }
            catch (SQLException e) {
                System.err.println("DbModule::getConnecton" + e.getMessage());
            }
        }
        return connection;
    }
}
