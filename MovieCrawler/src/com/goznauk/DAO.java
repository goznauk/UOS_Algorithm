package com.goznauk;

import java.sql.*;

/**
 * Created by goznauk on 2014. 11. 24..
 */
public class DAO {
    Connection connection;
    Statement statement;
    ResultSet resultSet;


    public DAO() {
       /* try {
            connection = DriverManager.getConnection(addr, user, pw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void insert(int code, String peers) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String addr = "jdbc:mysql://173.194.240.33/algorithm";
            String user = "root";
            String pw = "jkl;'";

            String sql = "INSERT INTO movieDB values(?,?)";
            connection = DriverManager.getConnection(addr, user, pw);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, code);
            preparedStatement.setString(2, peers);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void enterDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver error");
            return;
        }
        System.out.println("suc");
    }
}
