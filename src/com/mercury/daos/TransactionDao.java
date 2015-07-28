package com.mercury.daos;

import java.util.Set;

import com.mercury.beans.Stock;
import com.mercury.beans.Transaction;
import com.mercury.beans.User;

public interface TransactionDao {
	public void addTran(Transaction tran);
	public void addTran(Set<Transaction> trans);
	public Set<Transaction> queryTransAll();
	public Set<Transaction> queryTrans(User user);
	public Set<Transaction> queryTrans(Stock stock);
	public void deleteTrans(Set<Transaction> trans);
	public void deleteTran(Transaction tran);

}
