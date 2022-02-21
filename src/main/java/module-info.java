module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
    exports com.example.demo2.Listener;
    opens com.example.demo2.Listener to javafx.fxml;
    exports com.example.demo2.Task;
    opens com.example.demo2.Task to javafx.fxml;
    exports com.example.demo2.DAO;
    opens com.example.demo2.DAO to javafx.fxml;
    exports com.example.demo2.Model;
    opens com.example.demo2.Model to javafx.fxml;
}