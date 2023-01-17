package com.crearec.beanperthread.component;

public class BaseComponent {

    protected DatabaseNameStorage databaseNameStorage;

    public BaseComponent(DatabaseNameStorage databaseNameStorage) {
        this.databaseNameStorage = databaseNameStorage;
    }

    public String getDatabaseName() {
        return databaseNameStorage.getDatabaseName();
    }

    public void setDatabaseNameStorage(String databaseName) {
        databaseNameStorage.setDatabaseName(databaseName);
    }
}
