package ru.tandser.finesnew.util;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EntityHelperTest {

    @Mock private LazyInitializer initializer;
    @Mock private HibernateProxy proxy;

    @Test
    public void isUninitializedReturnTrue() {
        doReturn(initializer).when(proxy).getHibernateLazyInitializer();
        doReturn(Boolean.TRUE).when(initializer).isUninitialized();

        assertTrue(EntityHelper.isUninitialized(proxy));

        verify(proxy).getHibernateLazyInitializer();
        verify(initializer).isUninitialized();
    }

    @Test
    public void isUninitializedReturnFalse() {
        assertFalse(EntityHelper.isUninitialized(null));
        assertFalse(EntityHelper.isUninitialized(new Object()));

        doReturn(initializer).when(proxy).getHibernateLazyInitializer();
        doReturn(Boolean.FALSE).when(initializer).isUninitialized();

        assertFalse(EntityHelper.isUninitialized(proxy));

        verify(proxy).getHibernateLazyInitializer();
        verify(initializer).isUninitialized();
    }

    @Test
    public void getEntityIfLoadedReturnNotNull() {
        Object notProxy = new Object();

        assertSame(notProxy, EntityHelper.getEntityIfLoaded(notProxy));

        doReturn(initializer).when(proxy).getHibernateLazyInitializer();
        doReturn(Boolean.FALSE).when(initializer).isUninitialized();

        assertSame(proxy, EntityHelper.getEntityIfLoaded(proxy));

        verify(proxy).getHibernateLazyInitializer();
        verify(initializer).isUninitialized();
    }

    @Test
    public void getEntityIfLoadedReturnNull() {
        assertNull(EntityHelper.getEntityIfLoaded(null));

        doReturn(initializer).when(proxy).getHibernateLazyInitializer();
        doReturn(Boolean.TRUE).when(initializer).isUninitialized();

        assertNull(EntityHelper.getEntityIfLoaded(proxy));

        verify(proxy).getHibernateLazyInitializer();
        verify(initializer).isUninitialized();
    }
}