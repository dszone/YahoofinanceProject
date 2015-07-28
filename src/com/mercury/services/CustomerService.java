package com.mercury.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.mercury.beans.StockInfo;
import com.mercury.beans.Transaction;
import com.mercury.beans.User;
import com.mercury.daos.StockDao;
import com.mercury.daos.UserDao;


public class CustomerService {
	@Autowired
	private UserDao ud;
	@Autowired
	private StockDao sd;
	
	
	public Set<Transaction> queryPending(User user){
		Set<Transaction> set = new HashSet<Transaction>();
		
		List<String[]> list = new ArrayList<String[]>();
		File f = new File("D:/serverfiles/"+user.getUserId()+"_requests.csv");
		try {
			CSVReader cr = new CSVReader(new FileReader(f));
			list = cr.readAll();
			cr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (list != null) {
			for (String[] s : list) {
				set.add (new Transaction(ud.getUserById(Integer.valueOf(s[0])),
						sd.getStockByStockID(Integer.valueOf(s[1])),
						Integer.valueOf(s[2]), BigDecimal.valueOf(Double.valueOf(s[3])),
						Timestamp.valueOf(s[4])));
			}
			return set;
		}
		return null;
	}
	
	public void requestExchange(StockInfo stockInfo, User user, int amount) {
		String[] request = new String[5];
		String fileName = "D:/serverfiles/" + user.getUserId() + "_requests.csv";
		List<String[]> list = new ArrayList<String[]>();
		request[0] = String.valueOf(user.getUserId());
		request[1] = String.valueOf(stockInfo.getSid());
		request[2] = String.valueOf(amount);
		request[3] = String.valueOf(stockInfo.getCurrentPrice());
		request[4] = (new Timestamp((new java.util.Date()).getTime())).toString();
		
		try {
			CSVReader cr = new CSVReader(new FileReader(fileName));
			list = cr.readAll();
			cr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			CSVWriter cw = new CSVWriter(new FileWriter(fileName));
			cw.writeAll(list);
			cw.writeNext(request);
			cw.flush();
			cw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
