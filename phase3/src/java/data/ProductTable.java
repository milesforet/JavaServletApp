/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author John Won
 */
import static data.UserTable.connection;
import static data.UserTable.password;
import static data.UserTable.resultset;
import static data.UserTable.selectUsers;
import static data.UserTable.url;
import static data.UserTable.username;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ProductTable {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    static {
        try {
            //Establich connnection
            out.println("connection establishing");
            connection = DriverManager.getConnection(url, username, password);
            out.println("Connection established");
        } catch (Exception exc) {
            out.println("why you no work.");
            out.println(exc);
        }

    }

    public static List<Product> selectProducts() {
        List<Product> products = new ArrayList<>();
        Product result = null;
        try {
            String preparedSQL = "SELECT code,description,price FROM shop.products";
            selectUsers = connection.prepareStatement(preparedSQL);
            resultset = selectUsers.executeQuery();

            while (resultset.next()) {
                String a = resultset.getString(1);
                String b = resultset.getString(2);
                double c = resultset.getDouble(3);
                result = new Product(a, b, c);
                products.add(result);
            }
        } catch (SQLException e) {
            out.println(e);
        }
        out.println("List products");
        out.println(products);
        return products;
    }

    public static Product selectProduct(String productCode) {
        Product product = new Product();
        try {
            String preparedSQL = "SELECT code,description,price FROM shop.products WHERE code = ?";
            selectUsers = connection.prepareStatement(preparedSQL);
            selectUsers.setString(1, productCode);
            resultset = selectUsers.executeQuery();

            while (resultset.next()) {
                String a = resultset.getString("code");
                product.setCode(a);
                String b = resultset.getString("description");
                product.setDescription(b);
                double c = resultset.getDouble("price");
                product.setPrice(c);
            }
        } catch (SQLException e) {
            out.println(e);
        }
        out.println("selectProduct");
        out.println(product);
        return product;
    }

    public static boolean exists(String productCode) {
        String a = null;
        boolean exist;
        try {
            String preparedSQL = "SELECT code FROM shop.products WHERE code = ?";
            selectUsers = connection.prepareStatement(preparedSQL);
            selectUsers.setString(1, productCode);
            resultset = selectUsers.executeQuery();

            while (resultset.next()) {
                a = resultset.getString("code");

                out.println("Find out if it is null or not.");
                out.println(a);
            }
        } catch (SQLException e) {
            out.println(e);
        }
        if (a != null) {
            exist = true;
        } else {
            exist = false;
        }
        out.println("boolean exist");
        out.println(exist);
        return exist;
    }

    private static void saveProducts(List<Product> products) {

        //for loop that iterates through the products arraylist
        for (int i = 0; i < products.size(); i++){
        try {
            String preparedSQL = "INSERT INTO shop.products (code,description,price) VALUES (?,?,?);";
            selectUsers = connection.prepareStatement(preparedSQL);
            //testing.
            out.println("making sure the product is getting added.");
            out.println(products.get(i).getCode());
            out.println(products.get(i).getDescription());
            out.println(products.get(i).getPrice());

            selectUsers.setString(1, products.get(i).getCode());
            selectUsers.setString(2, products.get(i).getDescription());
            selectUsers.setDouble(3, products.get(i).getPrice());

            selectUsers.executeUpdate();

        } catch (SQLException e) {
            out.println(e);
        }
    }
    }

    public static void insertProduct(Product product) {
        try {
            String preparedSQL = "INSERT INTO shop.products (code,description,price) VALUES (?,?,?);";
            selectUsers = connection.prepareStatement(preparedSQL);
            //testing.
            out.println("making sure the product is getting added.");
            out.println(product.getCode());
            out.println(product.getDescription());
            out.println(product.getPrice());

            selectUsers.setString(1, product.getCode());
            selectUsers.setString(2, product.getDescription());
            selectUsers.setDouble(3, product.getPrice());

            selectUsers.executeUpdate();

        } catch (SQLException e) {
            out.println(e);
        }
    }

    public static void updateProduct(Product product) {
        try {
            String preparedSQL = "UPDATE shop.products "
                    + "SET code = ?, description = ?, price = ?"
                    + " WHERE code = ?;";
            selectUsers = connection.prepareStatement(preparedSQL);
            //testing.
            out.println("making sure it is getting updated.");
            out.println(product.getCode());

            selectUsers.setString(1, product.getCode());
            selectUsers.setString(2, product.getDescription());
            selectUsers.setDouble(3, product.getPrice());
            selectUsers.setString(4, product.getCode());

            selectUsers.executeUpdate();

        } catch (SQLException e) {
            out.println(e);
        }
    }

    public static void deleteProduct(Product product) {
        try {
            String preparedSQL = "DELETE FROM shop.products WHERE code = ?;";
            selectUsers = connection.prepareStatement(preparedSQL);
            //testing.
            out.println("deleteProduct.");
            out.println(product.getCode());

            selectUsers.setString(1, product.getCode());

            selectUsers.executeUpdate();

        } catch (SQLException e) {
            out.println(e);
        }
    }
}
