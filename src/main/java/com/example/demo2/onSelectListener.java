package com.example.demo2;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface onSelectListener {
    void resultSet(ResultSet resultSet) throws SQLException;

}
