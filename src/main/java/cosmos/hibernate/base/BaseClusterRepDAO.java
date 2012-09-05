// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseClusterRepDAO.java

package cosmos.hibernate.base;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.dao.ClusterRepDAO;
import cosmos.hibernate.dao._RootDAO;
import org.hibernate.*;

public abstract class BaseClusterRepDAO extends _RootDAO
{

    public BaseClusterRepDAO()
    {
    }

    public static ClusterRepDAO getInstance()
    {
        if(null == instance)
            instance = new ClusterRepDAO();
        return instance;
    }

    public Class getReferenceClass()
    {
        return cosmos.hibernate.ClusterRep.class;
    }

    public ClusterRep load(ClusterRep key)
        throws HibernateException
    {
        return (ClusterRep)load(getReferenceClass(), ((java.io.Serializable) (key)));
    }

    public ClusterRep load(ClusterRep key, Session s)
        throws HibernateException
    {
        return (ClusterRep)load(getReferenceClass(), ((java.io.Serializable) (key)), s);
    }

    public ClusterRep loadInitialize(ClusterRep key, Session s)
        throws HibernateException
    {
        ClusterRep obj = load(key, s);
        if(!Hibernate.isInitialized(obj))
            Hibernate.initialize(obj);
        return obj;
    }

    public ClusterRep save(ClusterRep clusterRep)
        throws HibernateException
    {
        return (ClusterRep)super.save(clusterRep);
    }

    public ClusterRep save(ClusterRep clusterRep, Session s)
        throws HibernateException
    {
        return (ClusterRep)super.save(clusterRep, s);
    }

    public void saveOrUpdate(ClusterRep clusterRep)
        throws HibernateException
    {
        super.saveOrUpdate(clusterRep);
    }

    public void saveOrUpdate(ClusterRep clusterRep, Session s)
        throws HibernateException
    {
        super.saveOrUpdate(clusterRep, s);
    }

    public void update(ClusterRep clusterRep)
        throws HibernateException
    {
        super.update(clusterRep);
    }

    public void update(ClusterRep clusterRep, Session s)
        throws HibernateException
    {
        super.update(clusterRep, s);
    }

    public void delete(ClusterRep clusterRep)
        throws HibernateException
    {
        super.delete(clusterRep);
    }

    public void delete(ClusterRep clusterRep, Session s)
        throws HibernateException
    {
        super.delete(clusterRep, s);
    }

    public void refresh(ClusterRep clusterRep, Session s)
        throws HibernateException
    {
        super.refresh(clusterRep, s);
    }

    public String getDefaultOrderProperty()
    {
        return null;
    }

    public static ClusterRepDAO instance;
}
