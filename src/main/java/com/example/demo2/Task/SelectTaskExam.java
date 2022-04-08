package com.example.demo2.Task;

import com.example.demo2.DAO.DAO;
import com.example.demo2.Database.DatabaseConnect;
import com.example.demo2.Database.Type.DataType;
import com.example.demo2.Database.Type.MariaDB;
import com.example.demo2.Listener.onSelectListener;
import com.example.demo2.DAO.SowDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectTaskExam implements Runnable{

private onSelectListener listener ;


    DataType dataType = new MariaDB();
    DatabaseConnect databaseConnect =
            new DatabaseConnect(dataType,"127.0.1:3306","3306","projectplan","design_view","1234");

    DAO stringDAO = new SowDAO(databaseConnect);

     public SelectTaskExam(onSelectListener listener){
         this.listener = listener;
     }

    @Override
    public void run() {


//        System.out.println(prepareStatementString());
//        try {
//            selectStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            listener.resultSet(stringDAO.resultSetStatement());
//           // stringDAO.resultSetStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }




    private void selectStatement() throws SQLException {
        PreparedStatement preparedStatementSELECT = connection().prepareStatement(prepareStatementString());
        ResultSet resultSet= preparedStatementSELECT.executeQuery();
        listener.resultSet(resultSet);
        preparedStatementSELECT.close();
    }

    private String prepareStatementString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT ");

        for(int i = 0 ; i <= column().size()-1 ; i++){
            stringBuilder.append(column().get(i));
            if(i!=column().size()-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName());

        return stringBuilder.toString();
    }
    private List<String> column(){
        List<String> stringList = new ArrayList<>();
        stringList.add("file_name");
        stringList.add("status");
        stringList.add("path_file");
        return  stringList;
    }
    private String tableName(){
        String string = "statement_of_work";
        return  string;
    }



    private class runnable implements Runnable{
        @Override
        public void run() {

        }
    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {

        }
    };




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
