package com.mercury.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.User;
import com.mercury.daos.TransactionDao;

@Controller
@SessionAttributes
public class HelloController {
	private TransactionDao td;
	public TransactionDao getTransDao() {
		return td;
	}
	public void setTransDao(TransactionDao transDao) {
		this.td = transDao;
	}
	
/*	@RequestMapping(value="/hello", method=RequestMethod.POST)
	public ModelAndView process(@ModelAttribute("user") 
			User user, BindingResult result) {
		UserInfo userInfo = hs.process(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPage);
		mav.addObject("userInfo", userInfo);
		return mav;
	}*/
	
	@RequestMapping("/main")
	@ResponseBody
	public String mainPage() {		
		
		
		
		
		return "hello";
	}
	
}
