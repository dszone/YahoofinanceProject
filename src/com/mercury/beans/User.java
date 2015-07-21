package com.mercury.beans;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Users")
public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private BigDecimal balance;
	private String authority;
	private int enable;
	private TransactionInfo transactions;
	private Set<OwnerShip> ownerships;
	
	public User () {
		ownerships = new HashSet<OwnerShip>();
	}
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name ="generator", strategy = "increment")
	@Column(name="USERID")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="BALANCE")
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Column(name="AUTHORITY")
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Column(name="ENABLED")
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	
	public TransactionInfo getTransactions() {
		return transactions;
	}
	public void setTransactions(TransactionInfo transactions) {
		this.transactions = transactions;
	}
	
	public Set<OwnerShip> getOwnerships() {
		return ownerships;
	}
	public void setOwnerships(Set<OwnerShip> ownerships) {
		this.ownerships = ownerships;
	}
	public void addOwnership(Ownership ownership) {
		ownerships.add(ownership);
	}
	public void removeOwnership(Ownership ownership) {
		ownerships.remove(ownership);
	}
	
}
