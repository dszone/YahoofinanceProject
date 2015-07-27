package com.mercury.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercury.beans.Stock;
import com.mercury.beans.StockInfo;
import com.mercury.daos.StockDao;

public class StockService {
	@Autowired
	private StockDao sd;
	
	public void addStock(Stock stock){
		sd.addStock(stock);
	}
	public void deleteStock(String code){
		for (Stock s:sd.qureyAll()){
			if (s.getScode().equalsIgnoreCase(code));
			sd.deleteStock(s);
		}
	}
	
	public StockInfo getStockInfoBCode(String code){
		Set<Stock> set = sd.qureyAll();
		for(Stock s : set){
			if(s.getScode().equals(code)){
				return getInfo(s);
			}
		}
		return null;
	}
	
	public StockInfo getStockInfoById(int id){
		return getInfo(sd.getStockByStockID(id));
	}

	public double getPrice(Stock stock){
		return getInfo(stock).getCurrentPrice();
	}

	public Set<StockInfo> getInfo(Set<Stock> stocks) {
		Set<StockInfo> sf = new HashSet<StockInfo>();
		for (Stock s : stocks) {
			sf.add(getInfo(s));
		}
		return sf;
	}

	public StockInfo getInfo(Stock stock) {

		String stockCode = stock.getScode();

		String yahoo_quote = "http://finance.yahoo.com/d/quotes.csv?s="
				+ stockCode + "&f=svc1l1&e=.c";
		StockInfo si = new StockInfo(stock);
		try {
			URL url = new URL(yahoo_quote);
			URLConnection urlconn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream()));
			String content = in.readLine();
			System.out.println(content);
			content = content.replace((char) 34, (char) 32);
			String[] tokens = content.split(",");
			si.setPricechange(Double.parseDouble(tokens[tokens.length - 2].trim()));
			si.setCurrentPrice(Double.parseDouble(tokens[tokens.length - 1].trim()));
			si.setCurrentVolume(Integer.parseInt(tokens[tokens.length - 3]
					.trim()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return si;
	}
}
