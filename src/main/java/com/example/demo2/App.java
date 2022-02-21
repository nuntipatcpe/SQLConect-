package com.example.demo2;

import com.example.demo2.DAO.DAO;
import com.example.demo2.DAO.SowDAO;
import com.example.demo2.Listener.onSelectListener;
import com.example.demo2.Model.SowModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App extends Application implements onSelectListener<SowModel> {

    String tableName = "sow";
    String columnPk = "file_name";

    String columnStatus = "status";
    String columnDate = "date";

    @Override
    public void start(Stage stage) throws IOException, SQLException {

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();


//        InsertTask insertTask = new InsertTask("t2","t1");
//        Thread t2 = new Thread(insertTask);
//        t2.start();
//

//        SelectTask selectTask = new SelectTask(App.this);
//        Thread t1 = new Thread(selectTask);
//        t1.start();

        DAO<SowModel> dao  = new SowDAO();//  1

        SowModel sowModel = new SowModel();
        sowModel.setFile_name("s2");
        sowModel.setStatus("ssss");
        sowModel.setPath_file("sss");

        dao.save(sowModel);//2

//        SelectTask<SowModel> selectTask = new SelectTask<>(dao,App.this);
//        Thread thread = new Thread(selectTask);
//        thread.start();

//        for(SowModel data : dao.getAll()){
//            System.out.println( data.getFile_name()+" || "+data.getStatus()+" || "+data.getPath_file());
//        }

//        try {
//            System.out.println(dao.resultSetStatement());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            insertStatement("boom3","success");
//            updateStatement("boomT1","fail","boom");
//            deleteStatement("boom3");
//            selectStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection().close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

//
    }

    public static void main(String[] args) {
        launch();
    }



    private void updateStatement(String newC1, String newC2 , String oldPk) throws SQLException {

        PreparedStatement preparedStatementUPDATE = connection().prepareStatement("UPDATE "+tableName+" SET "+columnPk+" = ?,"+columnStatus+" = ? WHERE file_name =?  ");
        preparedStatementUPDATE.setString(1,newC1);
        preparedStatementUPDATE.setString(2,newC2);
        preparedStatementUPDATE.setString(3,oldPk);//PK
        Integer isRowUpdate = preparedStatementUPDATE.executeUpdate();
        System.out.println("Update row :"+isRowUpdate);
        preparedStatementUPDATE.close();
    }

    private void selectStatement() throws SQLException {
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

    private void deleteStatement(String deletePk) throws SQLException {
        PreparedStatement preparedStatementDELETE = connection().prepareStatement("DELETE FROM "+tableName+" WHERE "+columnPk+" = ? ");
            preparedStatementDELETE.setString(1,deletePk);
            Boolean isSuccess=  preparedStatementDELETE.execute();

            System.out.println("delete :"+!isSuccess);
            preparedStatementDELETE.close();

    }

    private void insertStatement(String c1, String c2) throws SQLException {
        PreparedStatement preparedStatementINSERT = connection().prepareStatement("INSERT INTO "+tableName+" ("+columnPk+","+columnStatus+","+columnDate+") VALUES(?,?,?)");
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

    @Override
    public void resultSet(ResultSet resultSet) throws SQLException {

        List<SowModel> list = new ArrayList<>();
        while (resultSet.next()){
            SowModel sowModel = new SowModel();
            sowModel.setFile_name(resultSet.getString(1));
            sowModel.setStatus(resultSet.getString(2));
            sowModel.setPath_file(resultSet.getString(3));
            list.add(sowModel);
        }
   for(SowModel data : list){
       System.out.println( data.getFile_name()+" || "+data.getStatus()+" || "+data.getPath_file());
   }
    }

    @Override
    public void setList(List<SowModel> list) {
        for(SowModel data :list){
            System.out.println( data.getFile_name()+" || "+data.getStatus()+" || "+data.getPath_file());
        }
    }
}