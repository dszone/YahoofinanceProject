package com.mercury.daos;


import java.util.Set;

import com.mercury.beans.Stock;

public interface StockDao {
	public void addStock(Stock stock);
	public void deleteStock(Stock stock);
	public Stock getStockByStockID(int sid);	
	public Set<Stock> qureyAll();
}
