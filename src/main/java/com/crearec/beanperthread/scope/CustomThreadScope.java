package com.crearec.beanperthread.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomThreadScope implements Scope {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(128);
    private final ThreadLocal<Map<String, Object>> threadScope =
            new NamedThreadLocal<>("CustomThreadScope") {
                @Override
                protected Map<String, Object> initialValue() {
                    return new HashMap<>();
                }
            };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object result;
        if (Thread.currentThread().getName().equals(CustomRunnable.CUSTOM_THREAD_NAME)) {
            Map<String, Object> scope = threadScope.get();
            Object scopedObject = scope.get(name);
            if (scopedObject == null) {
                scopedObject = objectFactory.getObject();
                scope.put(name, scopedObject);
            }
            result = scopedObject;
        } else {
            Object singletonObject = singletonObjects.get(name);
            if (singletonObject == null) {
                singletonObject = objectFactory.getObject();
                singletonObjects.put(name, singletonObject);
            }
            result = singletonObject;
        }
        return result;
    }

    @Override
    public Object remove(String name) {
        return Thread.currentThread() instanceof ThreadScopeThread ? threadScope.get().remove(name) : singletonObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return Thread.currentThread().getName();
    }
}
