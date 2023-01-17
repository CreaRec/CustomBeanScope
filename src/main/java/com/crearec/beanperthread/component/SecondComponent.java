package com.crearec.beanperthread.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecondComponent extends BaseComponent {

    @Autowired
    public SecondComponent(DatabaseNameStorage databaseNameStorage) {
        super(databaseNameStorage);
        System.out.println("Second created");
    }

    public void print() {
        System.out.println("Reference: " + toString().substring(toString().indexOf('@')) + ", DB NAME: " + databaseNameStorage.getDatabaseName());
    }
}
