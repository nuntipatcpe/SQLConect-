package com.example.demo2;

public class SowModel {

    private String tableName;
    private String columnPk;
    private String columnStatus;
    private String columnDate;


    public SowModel(String tableName, String columnPk, String columnStatus, String columnDate) {
        this.tableName = tableName;
        this.columnPk = columnPk;
        this.columnStatus = columnStatus;
        this.columnDate = columnDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnPk() {
        return columnPk;
    }

    public void setColumnPk(String columnPk) {
        this.columnPk = columnPk;
    }

    public String getColumnStatus() {
        return columnStatus;
    }

    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus;
    }

    public String getColumnDate() {
        return columnDate;
    }

    public void setColumnDate(String columnDate) {
        this.columnDate = columnDate;
    }

}
