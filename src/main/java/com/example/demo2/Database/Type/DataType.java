package com.example.demo2.Database.Type;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface  DataType {

    public String setDriver();
    public String setDriverManagerUrl(String ip,String port,String nameDatabase);


//    private Connection connection() throws SQLException {
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return DriverManager.getConnection(
//                "jdbc:mariadb://127.0.1:3306/projectplan",
//                "design_view","1234"
//        );
//    }






}
