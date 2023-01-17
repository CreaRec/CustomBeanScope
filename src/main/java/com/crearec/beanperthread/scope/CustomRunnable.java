package com.crearec.beanperthread.scope;

public class CustomRunnable implements Runnable {
    public static final String CUSTOM_THREAD_NAME = "CustomThreadName";

    private CustomRunnableLogic customRunnableLogic;

    public CustomRunnable(CustomRunnableLogic customRunnableLogic) {
        this.customRunnableLogic = customRunnableLogic;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(CUSTOM_THREAD_NAME);
        customRunnableLogic.run();
    }
}
