package com.crearec.beanperthread;

import com.crearec.beanperthread.test.TestBean;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanPerThreadApplication {

    @Autowired
    private TestBean testBean;

    public static void main(String[] args) {
        SpringApplication.run(BeanPerThreadApplication.class, args);
    }

    @PostConstruct
    private void test() throws InterruptedException {
        testBean.print();
    }
}
