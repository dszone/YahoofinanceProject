package com.mercury.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

import com.mercury.beans.Stock;

@Service
public class AdminService {
	@Autowired
	private TransactionService ts;

	private UserService us;

	private StockService ss;
	
	public void addStock(String name, String code){
		ss.addStock(new Stock(name,code));
	}
	
	public void deleteStock(String code){
		ss.deleteStock(code);
	}

	public void parseAllRequsets() {
		String target_dir = "D:/serverfiles";
		File dir = new File(target_dir);
		File[] files = dir.listFiles();
		for (File f : files) {
			List<String[]> list = new ArrayList<String[]>();
			try {
				CSVReader cr = new CSVReader(new FileReader(f));
				list = cr.readAll();
				cr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (list != null) {
				for (String[] s : list) {
					ts.addTransaction(us.getUser(Integer.valueOf(s[0])),
							ss.getStockInfoById(Integer.valueOf(s[1])),
							Integer.valueOf(s[2]), Double.valueOf(s[3]),
							Timestamp.valueOf(s[4]));
				}
			}
		}

	}

}
