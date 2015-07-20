package com.mercury.beans;

import java.math.BigDecimal;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private BigDecimal balance;
	private String authority;
	private int enable;
	private TransactionInfo transactions;
	private StockInfo stocks;
	
	public TransactionInfo getTransactions() {
		return transactions;
	}
	public void setTransactions(TransactionInfo transactions) {
		this.transactions = transactions;
	}
	public StockInfo getStocks() {
		return stocks;
	}
	public void setStocks(StockInfo stocks) {
		this.stocks = stocks;
	}
	public User () {}
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	
}
