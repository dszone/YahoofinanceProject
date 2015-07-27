package com.mercury.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercury.beans.User;
import com.mercury.daos.UserDao;

public class UserService {
	@Autowired
	UserDao ud;
	
	public User getUser(int id){
		return ud.getUserById(id);
	}
	
	public User getUserEmail(String email){
		Set<User> set = ud.queryAll();
		for(User u:set){
			if (u.getEmail().equals(email))
			return u;
		}
		return null;
		
	}

}
