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
            Statement statement = connection.createStatement();
//            ResultSet res = statement.executeQuery("SHOW DATABASES");
//            while (res.next()) {
//                System.out.println(res.getString(1));
//            }
//            res.close();

            statement.execute("insert into journal(date)\n" +
                    "value(NOW());");
            statement.close();

//            DriverManager.deregisterDriver(mysqlDriver);
            mysqlDriver = null;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void run(String str){
        System.out.println("Db Demo");
        Driver mysqlDriver;
        try {
            Statement statement = connection.createStatement();
//            ResultSet res = statement.executeQuery("SHOW DATABASES");
//            while (res.next()) {
//                System.out.println(res.getString(1));
//            }
//            res.close();

            statement.execute("insert into journal(date)\n" +
                    "value(NOW());");

            if(str == "--journal"){
                ResultSet resultSet = statement.executeQuery("select * from journal");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("date"));
                }
            }

            statement.close();

//            DriverManager.deregisterDriver(mysqlDriver);
            mysqlDriver = null;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
