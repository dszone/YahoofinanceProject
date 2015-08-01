package com.mercury.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.com.bytecode.opencsv.CSVReader;

import com.mercury.beans.Ownership;
import com.mercury.beans.Stock;
import com.mercury.beans.StockInfo;
import com.mercury.beans.Transaction;
import com.mercury.beans.User;
import com.mercury.services.AdminService;
import com.mercury.services.StockService;
import com.mercury.services.TransactionService;
import com.mercury.services.UserService;

@Controller
@SessionAttributes
public class MarketdataController {
	@Autowired
	private StockService ss;
	@Autowired
	private UserService us;
	@Autowired
	private TransactionService ts;

	private Set<StockInfo> stocks;	//collection autowire cannot work, need to use resource
	
	public StockService getSs() {
		return ss;
	}

	public void setSs(StockService ss) {
		this.ss = ss;
	}

	@RequestMapping(value="/market", method=RequestMethod.GET)
	@ResponseBody
	public Set<StockInfo> marketData() {
		stocks = ss.getInfo(ss.getAllStocks());
/*		for(StockInfo stock:stocks) {
			System.out.println(stock.getScode() + " " + stock.getCurrentPrice());
		}*/
		return stocks;
	}
	
	@RequestMapping(value="/addstock", method=RequestMethod.POST)
	@ResponseBody	//return a message instead of of view
	public String addstock(@ModelAttribute("stock") 
			Stock stock, BindingResult result) {
		stock.setScode(stock.getScode().toUpperCase());
		System.out.println(stock.toString());
		if(!(ss.isExisted(stock))) {
			ss.addStock(stock);
			return "Add Successfully";
		}
		else return "This stock is already added";
	}
	
	@RequestMapping(value="/removestock", method=RequestMethod.POST)
	@ResponseBody	//return a message instead of a view
	public String removestock(@ModelAttribute("stock") 
			Stock stock, BindingResult result) {
		stock.setScode(stock.getScode().toUpperCase());
		if(ss.isExisted(stock)) {
			//System.out.println(stock.toString());
			ss.deleteStock(ss.getStockByScode(stock.getScode()));
			return "Remove Successfully";
		}
		else return "This stock is not existed";
	}
	
	@RequestMapping(value="/parseCSV", method=RequestMethod.POST)
	@ResponseBody	//return a message instead of a view
	public String parseCSV() {
		System.out.println("abc");
		
		
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
			if (list.size()!=0) {
				User user = us.getUser(Integer.valueOf(list.get(1)[0]));
				
				for (String[] s : list) {
					
					Ownership os = new Ownership();
					os.setQuantity(Integer.valueOf(s[2]));
					os.setStock(ss.getStockInfoById(Integer.valueOf(s[1])));
					os.setUser(user);
					if (user.addOrUpdateOwnership(os)){
						user.setBalance(new BigDecimal((user.getBalance().doubleValue()-Integer.valueOf(s[2])*Double.valueOf(s[3]))));
					}
					
					us.updateUser(user);
					
					ts.addTransaction(
							user,
							ss.getStockInfoById(Integer.valueOf(s[1])),
							Integer.valueOf(s[2]),
							Double.valueOf(s[3]),
							(new Timestamp(Date.parse(s[4])))
							);
				}
			}
		}
		
		return "All transactions are committed";
	}
	
	
	
}
