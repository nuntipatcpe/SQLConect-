package com.example.demo2.Task;

import com.example.demo2.DAO.DAO;
import com.example.demo2.Listener.onSelectListener;

public class SelectTask<T> implements Runnable {

DAO<T> dao;
private onSelectListener<T> listener;

    public SelectTask(DAO<T> dao, onSelectListener<T> listener) {
        this.dao = dao;
        this.listener = listener;
    }



    @Override
    public void run() {
            listener.setList(dao.getAll());
    }
}
