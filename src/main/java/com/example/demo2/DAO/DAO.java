package com.example.demo2.DAO;

import com.example.demo2.Model.SowModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract  class DAO<T>{



    public List<T> getAll()  {
        List<T> list = new ArrayList<>();
        PreparedStatement preparedStatementSELECT = null;
        try {
            preparedStatementSELECT = connection().prepareStatement(selectString());
            ResultSet resultSet= preparedStatementSELECT.executeQuery();

            while (resultSet.next()){
                T setObjectResult = setObjectResult(resultSet);
                list.add(setObjectResult);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                connection().close();
                preparedStatementSELECT.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void save(T t){ //3
        //  PreparedStatement preparedStatement = null;
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(insertString());


            setValueInsert(t,preparedStatement);//4

//            SowModel sowModel = new SowModel();
//            sowModel.setPath_file("a");
//            sowModel.setStatus("s");
//            sowModel.setFile_name("s");
//
//            preparedStatement.setString(1,sowModel.getFile_name());
//            preparedStatement.setString(2,sowModel.getStatus());
//            preparedStatement.setString(3,sowModel.getPath_file());
//            System.out.println("Sql Statement -> "+preparedStatement);

            boolean isSuccess = preparedStatement.execute();//6

            preparedStatement.close();
            connection().close();

            System.out.println("Insert :"+!isSuccess);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(T t){
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(deleteString());
            setValueDelete(t,preparedStatement);

            boolean isSuccess = preparedStatement.execute();
            System.out.println("Delete success : "+ !isSuccess);
            preparedStatement.close();
            connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void update(T t) {
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(updateString());
            setValueUpdate(t,preparedStatement);
            int isSuccess = preparedStatement.executeUpdate();
            System.out.println("Update :"+isSuccess+" row");
            preparedStatement.close();
            connection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String deleteString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("DELETE FROM ").append(tableName()).append(" WHERE ");

        for(int i = 0; i<= setId().size()-1; i++){
            stringBuilder.append(setId().get(i)).append(" = ? ");
            if(i!= setId().size()-1){
                stringBuilder.append("AND ");
            }
        }
        return stringBuilder.toString();
    }
    private String updateString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UPDATE ").append(tableName()).append(" SET ");

        for(int i = 1; i<= columnAllList().size()-1; i++){
            stringBuilder.append(columnAllList().get(i)).append(" = ?");
            if(i!= columnAllList().size()-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" WHERE ");

        for(int i = 0; i<= setId().size()-1; i++){
            stringBuilder.append(setId().get(i)).append(" = ? ");
            if(i!= setId().size()-1){
                stringBuilder.append("AND ");
            }
        }
        return stringBuilder.toString();
    }
    private String insertString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("INSERT INTO ").append(tableName()).append(" (");

        for(int i = 0; i<= columnAllList().size()-1; i++){
            stringBuilder.append(columnAllList().get(i));
            if(i!= columnAllList().size()-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(") ").append("VALUES ").append("(");

        for(int i = 0; i<= columnAllList().size()-1; i++){
            stringBuilder.append("?");
            if(i!= columnAllList().size()-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
    private String selectString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");

        for(int i = 0; i <= columnAllList().size()-1 ; i++){
            stringBuilder.append(columnAllList().get(i));
            if(i!= columnAllList().size()-1){
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName());
        return stringBuilder.toString();

    }

    private Connection connection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                "jdbc:mariadb://127.0.1:3306/projectplan",
                "design_view","1234"
        );
    }


    public abstract List<String> columnAllList();
    public abstract String tableName();
    public abstract List<String> setId();
    public abstract void setValueInsert(T t,PreparedStatement preparedStatement) throws SQLException;
    public abstract void setValueUpdate(T t,PreparedStatement preparedStatement) throws SQLException;
    public abstract void setValueDelete(T t,PreparedStatement preparedStatement) throws SQLException;
    public abstract T setObjectResult(ResultSet resultSet) throws SQLException;
}
