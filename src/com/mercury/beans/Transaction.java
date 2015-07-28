package com.mercury.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="trade_history")
public class Transaction {
	private int transid;
	private User user;
	private Stock stock;
//	private int userid;
//	private int sid;
	private int amount;
	private BigDecimal unitprice;
	private Timestamp timestamp;
	
	public Transaction(){
		this.timestamp=new Timestamp((new java.util.Date()).getTime());
		this.unitprice = new BigDecimal(0);
		this.amount = 0;
	} 
	
	public Transaction(int transid, User user, Stock stock, int amount,
			BigDecimal unitprice, Timestamp timestamp) {
		super();
		this.transid = transid;
		this.user = user;
		this.stock = stock;
		this.amount = amount;
		this.unitprice = unitprice;
		this.timestamp = timestamp;
	}
	public Transaction(User user, Stock stock, Integer amount,
			BigDecimal unitprice, Timestamp timestamp) {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(generator = "tran_id_gen", 
    strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "tran_id_gen", 
       sequenceName = "seq_trans_transid", 
       allocationSize = 1)
	@Column(name="transid")
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}

	@ManyToOne(fetch = FetchType.EAGER,targetEntity=User.class)
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=Stock.class)
	@JoinColumn(name="sid")
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
//	@Column(name = "userid")
//	public int getUserid() {
//		return userid;
//	}
//	public void setUserid(int userid) {
//		this.userid = userid;
//	}
//	@Column(name="sid")
//	public int getSid() {
//		return sid;
//	}
//	public void setSid(int sid) {
//		this.sid = sid;
//	}
	@Column(name ="amount")
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Column(name = "unitprice")
	public BigDecimal getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}
	@Column(name="transtime")
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
