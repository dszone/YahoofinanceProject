package com.mercury.daos;

import java.sql.Date;
import java.sql.Timestamp;

import com.mercury.beans.Stock;
import com.mercury.beans.Transaction;
import com.mercury.beans.TransactionInfo;
import com.mercury.beans.User;

public interface TransactionDao {
	public void addTrans(Stock stock,User user,int amount);
	public TransactionInfo queryTransAll();
	public TransactionInfo queryTrans(User user);
	public TransactionInfo queryTrans(Stock stock);
	public TransactionInfo queryTrans(User user, Date date);
	public TransactionInfo queryTrans(User user, Stock stock);
	public TransactionInfo queryTrans(Date date, Stock stock);
	public TransactionInfo queryTrans(User user,Date date,Stock stock);
	public void deleteTrans(Transaction trans);
	public void deleteTrans(User user);
	public void deleteTrans(Stock stock);
	public void deleteTrans(User user,Stock stock);
}
