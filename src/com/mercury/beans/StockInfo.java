package com.mercury.beans;

public class StockInfo extends Stock {
	private double pricechange;
	private double currentPrice;
	private int currentVolume;

	public StockInfo() {
	}

	public StockInfo(Stock stock) {
		setSid(stock.getSid());
		setScode(stock.getScode());
		setStockName(stock.getStockName());
	}

	public String toString() {
		return getSid() + ":" + getStockName() + ":" + getScode() + ":"
				+ getCurrentVolume() + ":" + getCurrentPrice() + ":"
				+ getPricechange();
	}

	public double getPricechange() {
		return pricechange;
	}

	public void setPricechange(double pricechange) {
		this.pricechange = pricechange;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
}
