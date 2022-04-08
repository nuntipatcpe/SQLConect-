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

    //    private Connection connection() throws SQLException {
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return DriverManager.getConnection(
//                "jdbc:mariadb://127.0.1:3306/projectplan",
//                "design_view","1234"
//        );
//    }




}
