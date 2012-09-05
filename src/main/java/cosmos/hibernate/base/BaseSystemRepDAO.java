// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseSystemRepDAO.java

package cosmos.hibernate.base;

import cosmos.hibernate.SystemRep;
import cosmos.hibernate.dao._RootDAO;
import org.hibernate.*;

import com.zenred.data_access.SystemRepDAO;

public abstract class BaseSystemRepDAO extends _RootDAO
{

    public BaseSystemRepDAO()
    {
    }

    public static SystemRepDAO getInstance()
    {
        if(null == instance)
            instance = new SystemRepDAO();
        return instance;
    }

    public Class getReferenceClass()
    {
        return cosmos.hibernate.SystemRep.class;
    }

    public SystemRep load(SystemRep key)
        throws HibernateException
    {
        return (SystemRep)load(getReferenceClass(), ((java.io.Serializable) (key)));
    }

    public SystemRep load(SystemRep key, Session s)
        throws HibernateException
    {
        return (SystemRep)load(getReferenceClass(), ((java.io.Serializable) (key)), s);
    }

    public SystemRep loadInitialize(SystemRep key, Session s)
        throws HibernateException
    {
        SystemRep obj = load(key, s);
        if(!Hibernate.isInitialized(obj))
            Hibernate.initialize(obj);
        return obj;
    }

    public SystemRep save(SystemRep systemRep)
        throws HibernateException
    {
        return (SystemRep)super.save(systemRep);
    }

    public SystemRep save(SystemRep systemRep, Session s)
        throws HibernateException
    {
        return (SystemRep)super.save(systemRep, s);
    }

    public void saveOrUpdate(SystemRep systemRep)
        throws HibernateException
    {
        super.saveOrUpdate(systemRep);
    }

    public void saveOrUpdate(SystemRep systemRep, Session s)
        throws HibernateException
    {
        super.saveOrUpdate(systemRep, s);
    }

    public void update(SystemRep systemRep)
        throws HibernateException
    {
        super.update(systemRep);
    }

    public void update(SystemRep systemRep, Session s)
        throws HibernateException
    {
        super.update(systemRep, s);
    }

    public void delete(SystemRep systemRep)
        throws HibernateException
    {
        super.delete(systemRep);
    }

    public void delete(SystemRep systemRep, Session s)
        throws HibernateException
    {
        super.delete(systemRep, s);
    }

    public void refresh(SystemRep systemRep, Session s)
        throws HibernateException
    {
        super.refresh(systemRep, s);
    }

    public String getDefaultOrderProperty()
    {
        return null;
    }

    public static SystemRepDAO instance;
}
