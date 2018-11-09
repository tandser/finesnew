package ru.tandser.finesnew.util;

import org.hibernate.proxy.HibernateProxy;

public class EntityHelper {

    private EntityHelper() {
        // nothing
    }

    public static boolean isUninitialized(Object obj) {
        return obj instanceof HibernateProxy &&
                ((HibernateProxy) obj).getHibernateLazyInitializer().isUninitialized();
    }

    public static <T> T getEntityIfLoaded(T obj) {
        return isUninitialized(obj)
                ? null
                : obj;
    }
}