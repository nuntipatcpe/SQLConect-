package com.example.demo2;

import java.util.List;

public abstract  class DAO<T> {
    abstract List columnList();
    abstract String nameTable();


}
