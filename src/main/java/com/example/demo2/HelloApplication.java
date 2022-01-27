package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.1:3306/projectplan",
                    "design_view","1234"
            );

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT file_name, status ,date  FROM sow");
//            preparedStatement.setString(1,"file_name");
//            preparedStatement.setString(2,"status");
       //    preparedStatement.setDate(3, Date.valueOf("date"));

//            Integer affectedRow = preparedStatement.executeUpdate();
//            String name = "boom2";
//            String status = "not";
//
//            Boolean isSuccess = preparedStatement.execute("INSERT INTO sow (file_name,status) VALUES(' "+status+" ',' "+name+"')");
//            System.out.println(isSuccess);
            List<String> stringList = new ArrayList<>();
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                stringList.add(resultSet.getString(1));
                stringList.add(resultSet.getString(2));
                stringList.add(resultSet.getString(3));
            }
            System.out.println("List "+stringList);
            //__________SELECT__________

            preparedStatement.close();
            resultSet.close();//************ close every time


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                connection.close(); //************ close every time
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
//        launch();

    }
}