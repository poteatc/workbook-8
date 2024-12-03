package com.pluralsight;
import java.sql.*;
public class UsingDriverManager {
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }

        //In IntelliJ, you can pass input parameters to the
        //application by editing configurations

        //You can "hard code" your input parameters in the program
        //arguments textbox

// get the user name and password from the command line args
        String username = args[0];
        String password = args[1];
// load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
// create the connection and prepared statement
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila", username, password);
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT first_name, last_name FROM customer " +
                                "WHERE last_name LIKE ? ORDER BY first_name");
// set the parameters for the prepared statement
        preparedStatement.setString(1, "Sa%");
// execute the query
        ResultSet resultSet = preparedStatement.executeQuery();
// loop thru the results
        while (resultSet.next()) {
// process the data
            System.out.printf("first_name = %s, last_name = %s;\n",
                    resultSet.getString(1), resultSet.getString(2));
        }
// close the resources
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
