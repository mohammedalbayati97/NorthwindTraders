package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do?");
        System.out.println("1) Display all products");
        System.out.println("2) Display all customers");
        System.out.println("0) Exit");
        System.out.print("Select an option: ");



        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            displayProducts();
        }
        else if (choice.equals("2")) {
            displayCustomers();
        }
        else {
            System.out.println("Goodbye!");
        }

        scanner.close();
    }

    private static void displayProducts() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "“yearup”"
            );

            Statement statement = connection.createStatement();

            String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                System.out.println("Product ID: " + results.getInt("ProductID"));
                System.out.println("Name: " + results.getString("ProductName"));
                System.out.println("Price: " + results.getDouble("UnitPrice"));
                System.out.println("Stock: " + results.getInt("UnitsInStock"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignore) {}
        }
    }

    private static void displayCustomers() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "“yearup”"
            );

            Statement statement = connection.createStatement();

            String query =
                    "SELECT ContactName, CompanyName, City, Country, Phone " +
                            "FROM customers " +
                            "ORDER BY Country";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                System.out.println("Contact: " + results.getString("ContactName"));
                System.out.println("Company: " + results.getString("CompanyName"));
                System.out.println("City: " + results.getString("City"));
                System.out.println("Country: " + results.getString("Country"));
                System.out.println("Phone: " + results.getString("Phone"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignore) {}
        }
    }
}
