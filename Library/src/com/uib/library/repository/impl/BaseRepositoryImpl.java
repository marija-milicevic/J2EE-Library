/**
 * 
 */
package com.uib.library.repository.impl;

import com.uib.library.repository.impl.*;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.uib.library.repository.BaseRepositoryApi;

/**
 * @author marija.milicevic
 *
 */
public class BaseRepositoryImpl extends HibernateDaoSupport implements BaseRepositoryApi{
	
	/* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#flush()
	 */
	public void flush() {
        getSession().flush();
    }


    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#get(java.lang.Class, java.io.Serializable)
	 */
    public Object get(Class<? extends Object> clazz, Serializable id) {
        if (id == null) {
            return null;
        }
        return getSession().get(clazz, id);
    }

 
    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#reattach(java.lang.Object)
	 */
    public void reattach(Object entity) {
        getSession().lock(entity, LockMode.READ);
    }


    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#refresh(java.lang.Object)
	 */
    public void refresh(Object entity) {
        getSession().refresh(entity);
    }


    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#save(java.lang.Object)
	 */
    public void save(Object entity) {
        getHibernateTemplate().save(entity);
    }


    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#saveOrUpdate(java.lang.Object)
	 */
    public void saveOrUpdate(Object entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }


    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#update(java.lang.Object)
	 */
    public void update(Object entity) {
        getHibernateTemplate().update(entity);
    }

    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#delete(java.lang.Object)
	 */
    public void delete(Object entity) {
        getHibernateTemplate().delete(entity);
    }

   

    /* (non-Javadoc)
	 * @see com.uib.library.repository.hb.BaseRepository#listAll(java.lang.Class)
	 */
    @SuppressWarnings("unchecked")
	public List<? extends Object> listAll(Class<? extends Object> clazz) {
        Criteria crit = getSession().createCriteria(clazz);

        crit.setCacheable(true);

        return crit.list();
    }


	@Override
	public void deleteAll(String domain){
		Session session = getSession();  
		
	    session.beginTransaction();
	    
	    String query = "delete from " + domain;
	 
	    session.createQuery(query).executeUpdate();
	 
	    session.getTransaction().commit();
	}

}
