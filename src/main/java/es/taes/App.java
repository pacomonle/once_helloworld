package es.taes;

import java.sql.Statement;
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
    static final String DB_URL = "jdbc:mysql://iprocuratio.com:3333/test_jdbc";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "once012020";

    public static void main(String[] args) throws Exception {
        String miMensaje = "Mi hola mundo";
        System.out.println(miMensaje);

        Persona persona = new Persona();
        persona.setEdad(45);
        persona.setNombre("Alfonso");
        System.out.println(persona);

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
                "CREATE TABLE if not exists Employees ( id INT(11) PRIMARY KEY, first VARCHAR(256),  last VARCHAR(256),age INTEGER)");
        stmt.executeUpdate("INSERT ignore INTO Employees VALUES(1,'Jack','Smith', 100) ");

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
