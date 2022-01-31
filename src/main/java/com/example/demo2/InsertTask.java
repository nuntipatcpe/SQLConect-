package com.example.demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertTask implements Runnable{
    SowDAO sowDAO = new SowDAO();


    private String c1;
    private String c2;


    public InsertTask(String c1 ,String c2) {
        this.c1 = c1;
        this.c2=c2;

    }

    @Override
    public void run() {
        try {
            insertStatement(c1,c2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void insertStatement(String c1, String c2) throws SQLException {
        sowDAO.columnList();
        PreparedStatement preparedStatementINSERT = connection().prepareStatement("INSERT INTO "+sowDAO.nameTable()+" ("+sowDAO.columnList().get(0)+","+sowDAO.columnList().get(1)+","+sowDAO.columnList().get(2)+") VALUES(?,?,?)");
        preparedStatementINSERT.setString(1,c1);
        preparedStatementINSERT.setString(2,c2);
        preparedStatementINSERT.setString(3, String.valueOf(LocalDate.now()));
        Boolean isSuccess = preparedStatementINSERT.execute();

        System.out.println("Insert :"+!isSuccess);
        preparedStatementINSERT.close();
    }

    private Connection connection() throws SQLException {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://127.0.1:3306/projectplan",
                "design_view","1234"
        );

        return connection;
    }
}
