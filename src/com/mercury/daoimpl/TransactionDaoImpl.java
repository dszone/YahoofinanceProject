package com.mercury.daoimpl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.Stock;
import com.mercury.beans.Transaction;
import com.mercury.beans.User;
import com.mercury.daos.TransactionDao;

public class TransactionDaoImpl implements TransactionDao {
	private HibernateTemplate template;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public HashSet<Transaction> queryTransAll() {
		String hql = "from Transaction";
		return new HashSet<Transaction>(template.find(hql));
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Transaction> queryTrans(User user) {
		String hql = "from Transaction trans where trans.user.userId ="
				+ user.getUserId();
		return new HashSet<Transaction>(template.find(hql));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Transaction> queryTrans(Stock stock) {
		String hql = "from Transaction trans where trans.stock.sid="
				+ stock.getSid();
		return new HashSet<Transaction>(template.find(hql));
	}
	
	
	@Override
	public void addTran(Set<Transaction> trans) {
		for(Transaction t:trans){
			template.save(t);
		}
		
	}
	@Override
	public void addTran(Transaction tran) {
		// TODO Auto-generated method stub
		template.save(tran);
	}
	
	@Override
	public void deleteTrans(Set<Transaction> trans) {
		// TODO Auto-generated method stub
		for(Transaction tran:trans){
			template.delete(tran);
		}
	}
	@Override
	public void deleteTran(Transaction tran) {
		// TODO Auto-generated method stub
		template.delete(tran);
	}

	

	

	

	


}
