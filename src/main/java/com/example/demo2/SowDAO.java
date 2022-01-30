package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

public class SowDAO extends DAO{

    @Override
    List columnList() {
        List<String> list = new ArrayList<>();
        list.add("file_name");
        list.add("status");
        list.add("date");
        return list;
    }



    @Override
    String nameTable() {
        return "sow";
    }
}
