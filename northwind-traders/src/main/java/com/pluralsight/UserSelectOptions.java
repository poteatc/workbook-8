package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;

public class UserSelectOptions {
    private static final String url = "jdbc:mysql://localhost:3306/northwind";
    private static final String user = "root";
    private static final String password = "yearup";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        displayHomeScreen();
    }

    private static void displayHomeScreen() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        /*
        Every driver has it’s own way to instantiate a DataSource
            - You will need to consult the documentation for your specific
                driver
            - In MySQL, we can instantiate and configure a
                DataSource as shown below
         */
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        try (Connection connection = dataSource.getConnection()) {
            do {
                System.out.print("""
                        What do you want to do?
                        1) Display all products
                        2) Display all customers
                        3) Display all categories
                        4) Show all columns in products table
                        5) Show all columns in customers table
                        0) Exit
                        Select an option:\s
                        """);
                String input = scanner.nextLine().trim();
                try {
                    int choice = Integer.parseInt(input);
                    if (choice < 0 || choice > 5) {
                        System.out.println("You must enter a valid number (0 - 5)...");
                        continue;
                    }
                    running = handleChoice(choice, connection);
                } catch (NumberFormatException e) {
                    System.out.println("You must enter an integer...");
                }
            } while (running);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean handleChoice(int choice, Connection connection) {
        switch (choice) {
            case 1 -> displayProducts();
            case 2 -> displayCustomers();
            case 3 -> displayCategories(connection);
            case 4 -> showTableColumns("products");
            case 5 -> showTableColumns("customers");
            case 0 -> {
                System.out.println("Exiting...");
                return false;
            }
        }
        return true;
    }

    private static void displayCategories(Connection connection) {
        String query = "select CategoryID as id, CategoryName as name " +
                "from categories " +
                "order by id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.print("""
                    Category ID\t\t\tCategory Name
                    """);
            while (rs.next()) {
                System.out.printf("%d\t\t\t\t\t%s\n", rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    Following is the list of values returned by various datatypes of java.sql.Type − i.e. getColumnType() gives these results.

        - Array: 2003
        - Big int: -5
        - Binary: -2
        - Bit: -7
        - Blob: 2004
        - Boolean: 16
        - Char: 1
        - Clob: 2005
        - Date: 91
        - Datalink:70
        - Decimal: 3
        - Distinct: 2001
        - Double: 8
        - Float: 6
        - Integer: 4
        - JavaObject: 2000
        - Long var char: -16
        - Nchar: -15
        - NClob: 2011
        - Varchar: 12
        - VarBinary: -3
        - Tiny int: -6
        - Time stamp with time zone: 2014
        - Timestamp: 93
        - Time: 92
        - Struct: 2002
        - SqlXml: 2009
        - Smallint: 5
        - Rowid: -8
        - Refcursor: 2012
        - Ref: 2006
        - Real: 7
        - Nvarchar: -9
        - Numeric: 2
        - Null: 0
        - Smallint: 5
     */
    private static void showTableColumns(String tableName) {
        String query = "select * from " + tableName;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            System.out.println("List of column names in the current table: ");
            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                System.out.println(rsMetaData.getColumnName(i) + " - type: " + rsMetaData.getColumnType(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayCustomers() {
        String query = "select * from customers order by country";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.printf("""
                    %-25s%36s%20s%20s%20s
                    """ + "-".repeat(121) + "\n", "Contact Name", "Company Name", "City", "Country", "Phone");
            while (rs.next()) {
                System.out.printf("""
                                %-25s%36s%20s%20s%20s
                                """, rs.getString("ContactName"),
                        rs.getString("CompanyName"),
                        rs.getString("City"),
                        rs.getString("Country"),
                        rs.getString("Phone"));
                //System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayProducts() {
    }
}
