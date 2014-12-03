package com.goznauk;

import java.sql.*;

/**
 * Created by goznauk on 2014. 11. 24..
 */
public class DAO {
    public static void insert(boolean isMovie, int code, String peers) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String addr = "jdbc:mysql://173.194.240.33/algorithm";
            String user = "root";
            String pw = "jkl;'";

            String sql = "";

            if(isMovie) {
                sql += "INSERT INTO movieDB values(?,?)";
            } else {
                sql += "INSERT INTO actorDB values(?,?)";
            }
            connection = DriverManager.getConnection(addr, user, pw);
            System.out.println("creating a statement..");


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, code);
            preparedStatement.setString(2, peers);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getPeers(boolean isMovie, int code) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        String str = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String addr = "jdbc:mysql://173.194.240.33/algorithm";
            String user = "root";
            String pw = "jkl;'";

            String sql = "";

            if(isMovie) {
                sql += "SELECT peers FROM movieDB WHERE code = ?";
            } else {
                sql += "SELECT peers FROM actorDB WHERE code = ?";
            }
            connection = DriverManager.getConnection(addr, user, pw);
            System.out.println("creating a statement..");


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, code);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            str = resultSet.getString(1);

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return str;
    }
}