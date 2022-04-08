package com.example.demo2.DAO;

import com.example.demo2.Database.DatabaseConnect;
import com.example.demo2.Model.SowModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SowDAO extends DAO<SowModel>{

    public SowDAO(DatabaseConnect databaseConnect) {
        super(databaseConnect);
    }

    @Override
    public SowModel setObjectResult(ResultSet resultSet) throws SQLException {
        SowModel sowModel = new SowModel();
        sowModel.setFile_name(resultSet.getString(1));
        sowModel.setStatus(resultSet.getString(2));
        sowModel.setPath_file(resultSet.getString(3));
        return sowModel;
    }

    @Override
    public void setValueInsert(SowModel sowModel, PreparedStatement preparedStatement) throws SQLException {//5
        preparedStatement.setString(1,sowModel.getFile_name());
        preparedStatement.setString(2,sowModel.getStatus());
        preparedStatement.setString(3,sowModel.getPath_file());
    }

    @Override
    public void setValueUpdate(SowModel sowModel, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setString(1,sowModel.getStatus());
        preparedStatement.setString(2,sowModel.getPath_file());
        preparedStatement.setString(3,sowModel.getFile_name());

    }

    @Override
    public void setValueDelete(SowModel sowModel, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,sowModel.getFile_name());

    }

    @Override
    public List<String> setId() {
        List<String> stringList = new ArrayList<>();
        stringList.add("file_name");
        return stringList;
    }

    @Override
    public List<String> columnAllList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("file_name");
        stringList.add("status");
        stringList.add("path_file");
        return  stringList;
    }

    @Override
    public String tableName() {
        return "statement_of_work";
    }
}
