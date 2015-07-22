package com.mercury.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="trade_history")
public class Transaction {
	private int transid;
	private User user;
	private Stock stock;
	private int userid;
	private int sid;
	private int amount;
	private BigDecimal unitprice;
	private Timestamp timestamp;
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name ="generator", strategy = "increment")
	@Column(name="transid")
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		this.userid = user.getUserId();
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sid")
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
		this.sid = stock.getSid();
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Column(name="sid")
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	@Column(name ="amount")
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Column(name = "uinitprice")
	public BigDecimal getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}
	@Column(name="timestamp")
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
