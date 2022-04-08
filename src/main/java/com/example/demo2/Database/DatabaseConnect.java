package com.example.demo2.Database;

import com.example.demo2.Database.Type.DataType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private DataType dataType;
    private final String ip;
    private final String port;
    private final String nameDatabase;
    private final String user;
    private final String password;

    public DatabaseConnect(DataType dataType,String ip, String port, String nameDatabase, String user, String password) {
        this.dataType = dataType;
        this.ip = ip;
        this.port = port;
        this.nameDatabase = nameDatabase;
        this.user = user;
        this.password = password;
    }



    public Connection getConnection()  {

        try {
            Class.forName(this.dataType.setDriver());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("load driver fail");
        }

        try {
            return DriverManager.getConnection(
                    this.dataType.setDriverManagerUrl(this.ip,this.port,this.nameDatabase),
                    this.user,this.password
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Connect database fail check Username password");
            return null;
        }


    }


}
