package com.mercury.controller;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.mercury.beans.Stock;
import com.mercury.beans.StockInfo;
import com.mercury.beans.User;

import com.mercury.beans.Transaction;
import com.mercury.daos.StockDao;
import com.mercury.daos.TransactionDao;
import com.mercury.daos.UserDao;
import com.mercury.services.StockService;
import com.mercury.services.TransactionService;

@Controller
@SessionAttributes
public class HelloController {
	@Autowired
	private TransactionDao td;
	@Autowired
	private StockDao sd;
	@Autowired
	private UserDao ud;
	@Autowired
	private TransactionService tranSS;
	@Autowired
	private StockService ss;

	public UserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	public TransactionDao getTd() {
		return td;
	}

	public void setTd(TransactionDao td) {
		this.td = td;
	}

	public StockDao getSd() {
		return sd;
	}

	public void setSd(StockDao sd) {
		this.sd = sd;
	}

	@RequestMapping("/writeTest")
	public String fileWriteTest() {
		String fileName = "D:/serverfiles/myfile.csv";
		CSVReader cr;
		List<String[]> list= new ArrayList<String[]>();
		try {
			cr = new CSVReader(new FileReader(fileName));
			list = cr.readAll();
			cr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		try {
			CSVWriter cw = new CSVWriter(new FileWriter(fileName));
			cw.writeAll(list);
			cw.flush();
			cw.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "alive";

	}

	@RequestMapping("/userTest")
	@ResponseBody
	public String testUserService() {

		// Stock stock = new Stock("NASDAQ Composite", "^IXIC");
		// sd.addStock(stock);
		Stock stock = sd.getStockByStockID(18);
		User user = ud.getUserById(16);
		String opStr = new String();

		// tranSS.addTransaction(user, stock, -500);

		StringBuffer sb = new StringBuffer();

		Set<Transaction> trans = new HashSet<Transaction>(td.queryTrans(user));

		Set<Stock> stocks = new HashSet<Stock>();
		for (Transaction t : trans) {
			stocks.add(t.getStock());
		}
		Set<StockInfo> stocksP = ss.getInfo(stocks);
		for (StockInfo s : stocksP) {
			sb.append(s.toString());
			sb.append(" <br />");
		}
		Set<Transaction> set = td.queryTransAll();
		for (Transaction t : set) {
			opStr = opStr
					+ (t.getAmount() + ";" + t.getTransid() + ";"
							+ t.getTimestamp() + ";" + t.getUnitprice())
					+ "<br/>";
			opStr = opStr + t.getStock().toString() + "<br/>";
			opStr = opStr + t.getUser().getUserName() + ":"
					+ t.getUser().getPassword() + "<br/>";

			opStr = opStr + "<br/><br/>";
		}

		return sb.toString() + "<br/>" + opStr;
	}

	@RequestMapping("/test")
	public String test() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alice");
		return "alive";
	}

	@RequestMapping("/main")
	@ResponseBody

	public String mainPage() {	
		
		/*Stock stock = new Stock("yahoo", "yo");
		sd.addStock(stock);
		

		User user = new User(10000,"qwer","123","abc@gmail.com",new BigDecimal(10),"admin",0);
		ud.save(user);
		Transaction tran = new Transaction(user,stock);
		td.addTrans(tran);*/

		User user = ud.getUserById(16);
		String opStr = new String();

		/* tranSS.addTransaction(user, stock, 30); */

		/*
		 * User user = new User(10000,"qwer","123","abc@gmail.com",new
		 * BigDecimal(10),"admin",0); ud.save(user); Transaction tran = new
		 * Transaction(user,stock); td.addTrans(tran);
		 */

		// User user = new User(10000,"qwer","123","abc@gmail.com",new
		// BigDecimal(10),"admin",0);

		/*
		 * User user1 = ud.getUserById(16); opStr = user1.getUserId() +
		 * user1.getUserName() + user1.getPassword() +"<br/>";
		 */

		Set<Transaction> set = td.queryTransAll();
		for (Transaction t : set) {
			opStr = opStr
					+ (t.getAmount() + ";" + t.getTransid() + ";"
							+ t.getTimestamp() + ";" + t.getUnitprice())
					+ "<br/>";
			opStr = opStr + t.getStock().toString() + "<br/>";
			opStr = opStr + t.getUser().getUserName() + ":"
					+ t.getUser().getPassword() + "<br/>" + ":"
					+ t.getUser().getUserId();

			opStr = opStr + "<br/><br/>";
		}

		return opStr;
	}

}
