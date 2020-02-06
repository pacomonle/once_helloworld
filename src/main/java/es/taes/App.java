package es.taes;

import java.sql.Statement;

import org.hibernate.boot.archive.scan.spi.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Hello world!
 *
 */
public class App {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
    static final String DB_URL = "jdbc:mysql://iprocuratio.com:3333/Employees";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "once012020";

    public static void main(String[] args) throws Exception {
      char sn;
      
      Scanner scan = new Scanner (System.in);
      System.out.print("¿Quieres añadir un nuevo empleado (s/n)? ");
      char sn = scan.nextChar(); 
      
     do while (sn == 's') {
        Scanner a = new Scanner (System.in);
        System.out.print("¿firstname nuevo empleado? ");
        String first = a.nextString();

        Scanner b = new Scanner (System.in);
        System.out.print("¿lastname nuevo empleado? ");
        String last = b.nextString();

        Scanner c = new Scanner (System.in);
        System.out.print("¿age nuevo empleado? ");
        int age = c.nextInt();

        
        a.close ();
        b.close ();
        c.close ();
    

       }

        
    

        Connection conn = null;
        Statement stmt = null;

        // Register JDBC driver
        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        // Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT id, first, last, age FROM Employees";
        stmt.executeUpdate(
                "CREATE TABLE if not exists Employees ( id INT(11) NOT NULL auto_increment PRIMARY KEY, first VARCHAR(256),  last VARCHAR(256),age INTEGER)");
        stmt.executeUpdate("INSERT ignore INTO Employees VALUES( first, last, age) ");

        ResultSet rs = stmt.executeQuery(sql);

      // Extract data from result set
      while (rs.next()) {
        // Retrieve by column name
        int id = rs.getInt("id");
        int age = rs.getInt("age");
        String first = rs.getString("first");
        String last = rs.getString("last");

        System.out.print("ID: " + id);
        System.out.print(", Age: " + age);
        System.out.print(", First: " + first);
        System.out.println(", Last: " + last);
      }
      // Clean-up environment
      rs.close();
      stmt.close();
      conn.close();

    }
}
