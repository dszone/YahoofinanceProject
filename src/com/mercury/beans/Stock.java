package com.mercury.beans;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="stocks")
public class Stock {

	private int sid;
	private String scode;
	private String stockName;
	private Set<Ownership> ownerships;
	

	public Stock() {
		ownerships = new HashSet<Ownership>();
	}
	public Stock(String stockName, String scode) {
		this();
		this.stockName = stockName;
		this.scode = scode;
	}
	@Id
	@GeneratedValue(generator = "stock_id_gen", 
    strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "stock_id_gen", 
       sequenceName = "seq_stoc_sid", 
       allocationSize = 1)
	@Column(name="sid")
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	@Column(name="scode")
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	@Column(name="stockname")
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	@OneToMany(mappedBy="stock")
	public Set<Ownership> getOwnerships() {
		return ownerships;
	}
	public void setOwnerships(Set<Ownership> ownerships) {
		this.ownerships = ownerships;
	}
	public String toString() {
		return scode + ": " + stockName;
	}
	public void addOwnership(Ownership ownership) {
		ownerships.add(ownership);
	}
	public void removeOwnership(Ownership ownership) {
		ownerships.remove(ownership);
	}
}

