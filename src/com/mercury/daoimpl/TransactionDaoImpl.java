package com.mercury.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mercury.beans.Stock;
import com.mercury.beans.Transaction;
import com.mercury.beans.TransactionInfo;
import com.mercury.beans.User;
import com.mercury.daos.TransactionDao;

public class TransactionDaoImpl implements TransactionDao {
	private HibernateTemplate template;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public void addTrans(Stock stock, User user,int amount) {
		Transaction trans = new Transaction();
		trans.setStock(stock);
		trans.setUser(user);
		trans.setAmount(amount);
		template.save(trans);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TransactionInfo queryTransAll() {
		String hql = "from Transaction";
		return new TransactionInfo((List<Transaction>)template.find(hql));
		
	}

	@Override
	public TransactionInfo queryTrans(User user) {
		String hql= "from Transaction trans where trans.user.userid ="+user.getUserId();
		return new TransactionInfo((List<Transaction>)template.find(hql));
	}

	@Override
	public TransactionInfo queryTrans(Stock stock) {
		String hql= "from Transaction trans where trans.stock.sid="+stock.getSid();
		return new TransactionInfo((List<Transaction>)template.find(hql));
	}

	@Override
	public TransactionInfo queryTrans(User user, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionInfo queryTrans(User user, Stock stock) {
		String hql= "from Transaction trans where trans.stock.sid="+stock.getSid()+" and trans.user.userid = ";
		return null;
	}

	@Override
	public TransactionInfo queryTrans(Date date, Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionInfo queryTrans(User user, Date date, Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTrans(Transaction trans) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTrans(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTrans(Stock stock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTrans(User user, Stock stock) {
		// TODO Auto-generated method stub

	}

}
