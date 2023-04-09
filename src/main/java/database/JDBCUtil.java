package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class JDBCUtil {
    public static Connection getConnection(){
        Connection c = null;
        try{
            Driver driver = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
            DriverManager.registerDriver(driver);
            c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=STUDENT_MANAGEMENT;user=sa;password=123;trustServerCertificate=true");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c){
        try{
            if(c!=null){
                c.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
