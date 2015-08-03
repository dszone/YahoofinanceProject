package com.mercury.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.Ownership;
import com.mercury.beans.RequestInfo;
import com.mercury.beans.Stock;
import com.mercury.beans.StockInfo;
import com.mercury.beans.Transaction;
import com.mercury.beans.User;
import com.mercury.daos.StockDao;
import com.mercury.daos.TransactionDao;
import com.mercury.daos.UserDao;
import com.mercury.services.CustomerService;
import com.mercury.services.StockService;
import com.mercury.services.TransactionService;
import com.mercury.services.UserService;

@Controller
@SessionAttributes
public class CustomerController {
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
	@Autowired
	private CustomerService cs;
	@Autowired
	private UserService us;

	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	public ModelAndView transactionPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("transaction");
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User user = us.getUser(userName);
		List<RequestInfo> list = new ArrayList<RequestInfo>();
		Set<RequestInfo> set = new HashSet<RequestInfo>();
		try {
			set = cs.queryPending(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (set!=null){
			list = new ArrayList<RequestInfo>(set);
		}
		

		JSONArray ja = new JSONArray(list);
		mav.addObject("Requests", ja);
		System.out.println(ja);

		return mav;
	}

	@RequestMapping(value = "/marketdata", method = RequestMethod.GET)
	public ModelAndView marketdataPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("marketdata");
		List<StockInfo> list = new ArrayList<StockInfo>();
		Set<StockInfo> set = new HashSet<StockInfo>();
		try {
			set = ss.getInfo(ss.getAllStocks());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (set!=null){
			list = new ArrayList<StockInfo>(set);
		}
		
		JSONArray ja = new JSONArray(list);
		mav.addObject("Requests", ja);
		System.out.println(ja);
		return mav;
	}


	@RequestMapping("/checkStock")//for test
	public ModelAndView checkStock(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("piechart");
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User user = us.getUser(userName);
		Set<Transaction> trans = user.getTransactions();
		List<StockInfo> infos = new ArrayList<StockInfo>();

		for (Transaction t : trans) {
			StockInfo si = ss.getInfo(t.getStock());
			si.setAmount(t.getAmount());
			infos.add(si);
		}
		mav.addObject("stockInfos", infos);
		return mav;
	}

	@RequestMapping(value = "/addBal", method = RequestMethod.POST)
	@ResponseBody
	public BigDecimal addBalance(@FormParam("amount") BigDecimal amount) {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		// System.out.println(amount);
		// if()
		us.addBalance(userName, amount);
		return us.getBalance(userName);
	}

	@RequestMapping(value = "/buySub", method = RequestMethod.POST)
	@ResponseBody
	public String BuyingSubmmition(@FormParam("code") String code,
			@FormParam("amount") Integer amount) {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User user = us.getUser(userName);
		code = code.toUpperCase();
		double price = ss.getStockInfoBCode(code).getCurrentPrice();
		if (ss.getStockInfoBCode(code) == null) {
			return "The Stock is NOT in the System";

		} else if (amount * price > user.getBalance().doubleValue()) {
			return "Your credit is NOT enough";

		} else {
			StockInfo stockInfo = ss.getStockInfoBCode(code);
			stockInfo.setCurrentPrice(price);
			try {
				cs.requestExchange(stockInfo, user, amount);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Unable to add Transactions";
			}
			double balance = user.getBalance().doubleValue();
			user.setBalance(new BigDecimal(balance - price * amount));
			us.updateUser(user);
			return "Transaction Added";
		}

	}

	@RequestMapping(value = "/sellSub", method = RequestMethod.POST)
	@ResponseBody
	public String sellingSubmmition(@FormParam("code") String code,
			@FormParam("amount") Integer amount) {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User user = us.getUser(userName);
		code = code.toUpperCase();
		Set<Ownership> set = us.getOwnershipInfoByUser(user).getOwnerships();
		Stock stock = null;
		Ownership os = new Ownership();
		for (Ownership o : set) {
			if (o.getStock().getScode().equals(code)) {
				stock = o.getStock();
				os = o;
				break;
			}
		}
		if (stock == null) {
			return "You don't have This Stock";
		} else if (amount > os.getQuantity()) {
			return "You don't have that much of " + stock.getScode();

		} else {

			try {
				StockInfo stockInfo = ss.getStockInfoBCode(code);
				cs.requestExchange(stockInfo, user, -amount);
				return "Transaction Added";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Unable to add Transactions";
			}

		}

	}

}
