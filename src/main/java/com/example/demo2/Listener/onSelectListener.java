package com.example.demo2.Listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface onSelectListener<T> {
    void resultSet(ResultSet resultSet) throws SQLException;
    void setList(List<T> list);

}
