package com.mercury.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.*;

import com.mercury.services.RegisterService;
import com.mercury.beans.*;

@Controller
@SessionAttributes
public class SecurityController {

	@Autowired
	private RegisterService rs;
	
	public RegisterService getRs() {
		return rs;
	}

	public void setRs(RegisterService rs) {
		this.rs = rs;
	}
	
	/*@RequestMapping("/login.htm")
	public String login(ModelMap model) {
		return "security/login";
	}*/
	
	/*@RequestMapping(value="/content/main.htm", method = RequestMethod.GET)
	public ModelAndView mainPage() {	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/hello");
		mav.addObject("title", "Hello, welcome to Customized Spring Security");
		return mav;
	}*/
	
	/*@RequestMapping("/register.htm")
	public String register(){
		return "security/register";
	}*/
	
	@RequestMapping(value="/register")
	public String enroll(HttpServletRequest  request){
		String username = request.getParameter("r_username");
		String password = request.getParameter("r_password");
		String email = request.getParameter("r_email");
		
		/*String firstname = request.getParameter("r_firstname");
		String lastname = request.getParameter("r_lastname");
		String phone = request.getParameter("r_phone");
		String address = request.getParameter("r_address");
		String city = request.getParameter("r_city");
		String home_state = request.getParameter("r_state");
		String zipcode = request.getParameter("r_zipcode");
		*/
		User user = new User(username, password);
		user.setEmail(email);
		user.setAuthority("ROLE_USER");
		user.setBalance(new BigDecimal(0));
		user.setEnable(1);
		
		/*trader.setPhone(phone);
		trader.setAddress(address);
		trader.setZipcode(zipcode);
		trader.setCity(city);
		trader.setHome_state(home_state);*/
		rs.register(user);
		rs.sendMail(username, email);
		return "success";
	}
}
