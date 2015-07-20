package com.mercury.beans;

import java.util.List;

public class TransactionInfo {

	private List<Transaction> list;

	public List<Transaction> getList() {
		return list;
	}

	public void setList(List<Transaction> list) {
		this.list = list;
	}

	public void addTransaction(Transaction tran) {
			list.add(tran);
	}
	
	public void removeTransaction(Transaction tran){
		list.remove(tran);
	}
}
