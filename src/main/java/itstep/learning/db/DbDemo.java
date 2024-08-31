package itstep.learning.db;

import com.google.inject.Inject;

import javax.activation.DataSource;
import java.sql.*;

public class DbDemo {
    @Inject
    private Connection connection;

    public void run(){
        System.out.println("Db Demo");
        Driver mysqlDriver;
        try {
//            mysqlDriver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.registerDriver(mysqlDriver);
//
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3308/java" +
//                            "?useUnicode=true&characterEncoding=utf8",
//                    "user222",
//                    "pass222");

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SHOW DATABASES");
            while (res.next()) {
                System.out.println(res.getString(1));
            }
            res.close();
            statement.close();

//            DriverManager.deregisterDriver(mysqlDriver);
            mysqlDriver = null;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
