package com.example.demo2;

import java.io.IOException;
import java.nio.CharBuffer;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectTask implements Runnable{

private onSelectListener  listener ;
 SelectTask(onSelectListener listener){
     this.listener = listener;
 }

    @Override
    public void run() {

        try {
            selectStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void selectStatement() throws SQLException {

        PreparedStatement preparedStatementSELECT = connection().prepareStatement("SELECT file_name, status ,date  FROM sow");
        ResultSet resultSet= preparedStatementSELECT.executeQuery();
        listener.resultSet(resultSet);
        preparedStatementSELECT.close();

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
