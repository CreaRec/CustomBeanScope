package com.crearec.beanperthread.test;

import com.crearec.beanperthread.component.FirstComponent;
import com.crearec.beanperthread.component.SecondComponent;
import com.crearec.beanperthread.scope.CustomRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestBean {

    private FirstComponent firstComponent;
    private SecondComponent secondComponent;

    @Autowired
    public TestBean(FirstComponent firstComponent, SecondComponent secondComponent) {
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;
    }

    public void print() throws InterruptedException {
        System.out.println("1");
        firstComponent.print();
        secondComponent.print();
        Runnable customRunnable = new CustomRunnable(() -> {
            System.out.println("2");
            firstComponent.setDatabaseNameStorage("firstChangedDatabaseName");
            firstComponent.print();
            secondComponent.print();
        });
        Thread thread1 = new Thread(customRunnable);
        System.out.println("3");
        firstComponent.print();
        secondComponent.print();

        thread1.start();
        thread1.join();

        System.out.println("4");
        firstComponent.print();
        secondComponent.print();
    }
}
