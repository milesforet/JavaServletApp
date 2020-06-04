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
import data.User;
import java.io.*;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserTable {

    static String url = "jdbc:mysql://localhost:3306/shop?autoReconnect=true&useSSL=false";
    static String username = "user";
    static String password = "123";

    static Connection connection = null;
    static PreparedStatement selectUsers = null;
    static ResultSet resultset = null;

    //Static initializer, it runs when the class is intialized (it is executed once)
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

    public static void addRecord(User user) throws IOException {
        try {
            String preparedSQL = "INSERT INTO shop.users (firstName,lastName,email,password) VALUES (?,?,?,?);";
            selectUsers = connection.prepareStatement(preparedSQL);
            //testing.
            out.println(user.getFirstName());
            out.println(user.getLastName());
            out.println(user.getEmail());
            out.println(user.getPassword());

            selectUsers.setString(1, user.getFirstName());
            selectUsers.setString(2, user.getLastName());
            selectUsers.setString(3, user.getEmail());
            selectUsers.setString(4, user.getPassword());

            selectUsers.executeUpdate();

        } catch (SQLException e) {
            out.println(e);
        }
    }

    public static User getUser(String emailAddress) throws IOException {
        User user = new User();
        try {
            String preparedSQL = "SELECT firstName,lastName,email,password FROM shop.users WHERE email = ?";
            selectUsers = connection.prepareStatement(preparedSQL);
            selectUsers.setString(1, emailAddress);
            resultset = selectUsers.executeQuery();

            while (resultset.next()) {
                String a = resultset.getString("firstName");
                user.setFirstName(a);
                out.println(a);
                String b = resultset.getString("lastName");
                user.setLastName(b);
                out.println(b);
                String c = resultset.getString("email");
                user.setEmail(c);
                out.println(c);
                String d = resultset.getString("password");
                user.setPassword(d);
                out.println(d);
            }
        } catch (SQLException e) {
            out.println(e);
        }
        return user;
    }

    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        User user = null;
        try {
            String preparedSQL = "SELECT firstName,lastName,email,password FROM shop.users";
            selectUsers = connection.prepareStatement(preparedSQL);
            resultset = selectUsers.executeQuery();

            while (resultset.next()) {
                String a = resultset.getString(1);
                String b = resultset.getString(2);
                String c = resultset.getString(3);
                String d = resultset.getString(4);
                user = new User(a,b,c,d);

                users.add(user);
            }
        } catch (SQLException e) {
            out.println(e);
        }
        out.println(users);
        return users;

    }

    public static HashMap<String, User> getUsersMap() throws IOException {

        //creates hashmap
        HashMap<String, User> hash = new HashMap<>();

        //create arraylist of users by calling the getUsers() method above.
        ArrayList<User> users = UserTable.getUsers();
        System.out.println(users.toString());

        //iterates through the users arraylist
        System.out.println("show users:");
        for (int i = 0; i < users.size(); i++){

            System.out.println("index = "+i);
            System.out.println("user "+ i + " = " + users.get(i).getFirstName());
            //creates a hash key that is equal to the users first name
            String key = users.get(i).getFirstName();
            System.out.println(key);

            //adds the key and corresponding User object to the hashmap
            hash.put(key, users.get(i));
            System.out.println(Collections.singletonList(hash));
        }


        //returns hash
        return hash;
    }
}
