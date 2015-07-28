package com.mercury.beans;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
//import org.hibernate.annotations.GenericGenerator;

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
	private Set<Transaction> transactions;
	private Set<Ownership> ownerships;
	
	public User () {
		ownerships = new HashSet<Ownership>();
		transactions = new HashSet<Transaction>(); 
	}
	public User(String userName, String password) {
		this();
		this.userName = userName;
		this.password = password;
	}
	public User(String userName, String password, String email, BigDecimal balance, String authority, int enable) {
		this();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.balance = balance;
		this.authority = authority;
		this.enable = enable;
	}
	
	@Id
	@GeneratedValue(generator = "user_id_gen", 
    strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_id_gen", 
       sequenceName = "seq_user_userid", 
       allocationSize = 1)
	@Column(name="userid", unique = true, nullable = false, length = 5)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="balance")
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Column(name="authority")
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Column(name="enabled")
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade(CascadeType.SAVE_UPDATE)
	public Set<Ownership> getOwnerships() {
		return ownerships;
	}
	public void setOwnerships(Set<Ownership> ownerships) {
		this.ownerships = ownerships;
	}
	public void addOwnership(Ownership ownership) {
		ownerships.add(ownership);
	}
	public void removeOwnership(Ownership ownership) {
		ownerships.remove(ownership);
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade(CascadeType.SAVE_UPDATE)
	public Set<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	public void removeTransaction(Transaction transaction) {
		transactions.remove(transaction);
	}
	
}
