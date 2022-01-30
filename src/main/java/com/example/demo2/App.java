package com.example.demo2;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    String tableName = "sow";
    String columnPk = "file_name";
    String columnStatus = "status";
    String columnDate = "date";

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
//            insertDataStatement("boom3","success");
//            upDataStatement("boomT1","fail","boom");
//            deleteDataStatement("boom3");
            selectDataStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//
    }

    public static void main(String[] args) {
        launch();



//
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(
//                    "jdbc:mariadb://127.0.1:3306/projectplan",
//                    "design_view","1234"
//            );
//
//
//            PreparedStatement preparedStatementSELECT = connection.prepareStatement("SELECT file_name, status ,date  FROM sow");
//
//
//            //__________INSERT
////            PreparedStatement preparedStatementINSERT = connection.prepareStatement("INSERT INTO "+tableName+" ("+columnPk+","+columnStatus+","+columnDate+") VALUES(?,?,?)");
////            preparedStatementINSERT.setString(1,"boom3");
////            preparedStatementINSERT.setString(2,"status");
////            preparedStatementINSERT.setString(3, String.valueOf(LocalDate.now()));
////            preparedStatementINSERT.execute();
////            preparedStatementINSERT.close();
////            //__________INSERT
////
////            //__________DELETE
////            PreparedStatement preparedStatementDELETE = connection.prepareStatement("DELETE FROM "+tableName+" WHERE "+columnPk+" = ? ");
////            preparedStatementDELETE.setString(1,"boom3");
////            preparedStatementDELETE.execute();
////            preparedStatementDELETE.close();
//            //__________DELETE
//
//            //__________UPDATE
//
////            PreparedStatement preparedStatementUPDATE = connection.prepareStatement("UPDATE "+tableName+" SET "+columnPk+" = ?,"+columnStatus+" = ? WHERE file_name =?  ");
////            preparedStatementUPDATE.setString(1,"Boom");
////            preparedStatementUPDATE.setString(2,"success");
////            preparedStatementUPDATE.setString(3,"test");//PK
////            Integer isRowUpdate = preparedStatementUPDATE.executeUpdate();
////            System.out.println(isRowUpdate);
////            preparedStatementUPDATE.close();
//            //__________UPDATE
//
//            comment();
//
//
//            List<String> fileNameList = new ArrayList<>();
//            List<String> statusList = new ArrayList<>();
//            List<String> dateList = new ArrayList<>();
//
//
//            ResultSet resultSet= preparedStatementSELECT.executeQuery();
//            while (resultSet.next()){
//                fileNameList.add(resultSet.getString(1));
//                statusList.add(resultSet.getString(2));
//                dateList.add(resultSet.getString(3));
//            }
//
//          //  System.out.println("List "+fileNameList);
//
//           for(int i = 0 ; i< fileNameList.size();i++){
//               System.out.println(fileNameList.get(i)+" || "+statusList.get(i)+" || "+dateList.get(i));
//           }
//            //__________SELECT__________
//
//            preparedStatementSELECT.close();
//            resultSet.close();//************ close every time
//
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.close(); //************ close every time
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        launch();

    }

    private static void comment() {

        //    preparedStatement.setString(1,"file_name");
//            preparedStatement.setString(2,"status");
        //    preparedStatement.setDate(3, Date.valueOf("date"));

//            Integer affectedRow = preparedStatement.executeUpdate();
//            String dataName = "test";
//            String dataStatus = "not";
//
//            String columnPk = "file_name";
//            String column = "status";

//            Boolean isSuccess = preparedStatement.execute("INSERT INTO sow (file_name,status) VALUES(' "+dataStatus+" ',' "+dataName+"')");
//            Boolean isSuccess = preparedStatement.execute("DELETE FROM sow WHERE file_name = ' "+dataName+"' ");
//            Integer isSuccess = preparedStatement.executeUpdate("UPDATE sow SET "+column+" = '"+dataStatus+"' WHERE "+columnPk+" = '"+dataName+"' ");
//            System.out.println(isSuccess);

    }

    private void upDataStatement(String newC1,String newC2 ,String oldPk) throws SQLException {

        PreparedStatement preparedStatementUPDATE = connection().prepareStatement("UPDATE "+tableName+" SET "+columnPk+" = ?,"+columnStatus+" = ? WHERE file_name =?  ");
        preparedStatementUPDATE.setString(1,newC1);
        preparedStatementUPDATE.setString(2,newC2);
        preparedStatementUPDATE.setString(3,oldPk);//PK
        Integer isRowUpdate = preparedStatementUPDATE.executeUpdate();
        System.out.println("Update row :"+isRowUpdate);
        preparedStatementUPDATE.close();
    }


    private void selectDataStatement() throws SQLException {
        PreparedStatement preparedStatementSELECT = connection().prepareStatement("SELECT file_name, status ,date  FROM sow");
        List<String> fileNameList = new ArrayList<>();
            List<String> statusList = new ArrayList<>();
            List<String> dateList = new ArrayList<>();

            ResultSet resultSet= preparedStatementSELECT.executeQuery();
            while (resultSet.next()){
                fileNameList.add(resultSet.getString(1));
                statusList.add(resultSet.getString(2));
                dateList.add(resultSet.getString(3));
            }

          //  System.out.println("List "+fileNameList);

           for(int i = 0 ; i< fileNameList.size();i++){
               System.out.println(fileNameList.get(i)+" || "+statusList.get(i)+" || "+dateList.get(i));
           }
            //__________SELECT__________

            preparedStatementSELECT.close();

    }

    private void deleteDataStatement(String deletePk) throws SQLException {
        PreparedStatement preparedStatementDELETE = connection().prepareStatement("DELETE FROM "+tableName+" WHERE "+columnPk+" = ? ");
            preparedStatementDELETE.setString(1,deletePk);
            Boolean isSuccess=  preparedStatementDELETE.execute();

            System.out.println("delete :"+!isSuccess);
            preparedStatementDELETE.close();

    }

    private void insertDataStatement(String c1,String c2) throws SQLException {
        PreparedStatement preparedStatementINSERT = connection().prepareStatement("INSERT INTO "+tableName+" ("+columnPk+","+columnStatus+","+columnDate+") VALUES(?,?,?)");
            preparedStatementINSERT.setString(1,c1);
            preparedStatementINSERT.setString(2,c2);
            preparedStatementINSERT.setString(3, String.valueOf(LocalDate.now()));
            Boolean isSuccess = preparedStatementINSERT.execute();

            System.out.println("Insert :"+!isSuccess);
            preparedStatementINSERT.close();
    }

    private Connection connection() throws SQLException {

        Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.1:3306/projectplan",
                    "design_view","1234"
            );

        return connection;
    }
}