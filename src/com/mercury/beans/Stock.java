package com.mercury.beans;

import java.util.*;

public class Stock {
	private int sid;
	private String scode;
	private String stockName;
	private Set<User> users;
	
	public Stock() {
		this.users = new HashSet<User>();
	}
	public Stock(String stockName, String scode) {
		this();
		this.stockName = stockName;
		this.scode = scode;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		users.add(user);
	}
	public void removeUser(User user) {
		users.remove(user);
	}
}

