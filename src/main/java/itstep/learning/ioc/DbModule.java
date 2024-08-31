package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import itstep.learning.fs.FileDemo;

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
                FileDemo demo = new FileDemo();
                mysqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mysqlDriver);

                connection = DriverManager.getConnection(
                        demo.formatDbString(),
                        demo.getUser(),
                        demo.getPassword());
                System.out.println(connection.getMetaData().getURL());

            }
            catch (SQLException e) {
                System.err.println("DbModule::getConnecton" + e.getMessage());
            }
        }
        return connection;
    }
}
