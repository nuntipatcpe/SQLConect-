package com.example.demo2.Database.Type;

public class MariaDB implements DataType {
    @Override
    public String setDriver() {
        return "org.mariadb.jdbc.Driver";
    }

    @Override
    public String setDriverManagerUrl(String ip, String port, String nameDatabase) {
        return "jdbc:mariadb://"+ip+":"+port+"/"+nameDatabase;
    }

}
