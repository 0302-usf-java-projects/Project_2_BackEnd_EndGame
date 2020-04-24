package com.endgame.dao;



import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.endgame.model.User;

@Repository
@Transactional
public class UserDao implements DaoContact<User, Integer> {
	

	private SessionFactory sesfact;
	
	@Autowired
	  public void setSesFactory(SessionFactory sse) {
	    sesfact= sse;
	  }
	
	@Override
	public User insert(User t) {
		sesfact.openSession().save(t);
		return t;
		
	}

	@Override
	public User findById(Integer id) {
		return sesfact.openSession().get(User.class, id);
	}

	@Override
	public void deleteById(Integer id) {
		User t = this.findById(id);
		sesfact.getCurrentSession().delete(t);
//		Session session = sesfact.openSession();
//	    Transaction tx = session.beginTransaction();
//	    User t = this.findById(id);
//	    session.delete(t);
//	    tx.commit();
		
	}

	@Override
	public User update(User t) {
		sesfact.getCurrentSession().update(t);
		return t;
//		Session session = sesfact.openSession();
//	    Transaction tx = session.beginTransaction();
//	    session.update(t);
//	    tx.commit();
//	    return t;
	}

	
}
