package com.mercury.daoimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.*;
import com.mercury.daos.StockDao;

public class StockDaoImpl implements StockDao {
	
	private HibernateTemplate template;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public void addStock(Stock stock) {
		// TODO Auto-generated method stub
		template.save(stock);
	}

	@Override
	public void deleteStock(Stock stock) {
		// TODO Auto-generated method stub
		template.delete(stock);
	}
	
	@Override
	public Stock getStockByStockID(int sid) {
		String hql = "from Stock as stock where stock.sid=" + sid;
		@SuppressWarnings("unchecked")
		List<Stock> stock = template.find(hql);
		return stock.get(0);
	}

	@Override
	public Set<Stock> queryAll() {
		// TODO Auto-generated method stub
		String hql = "from Stock";
		return new HashSet<Stock>(template.find(hql));
	}
}
