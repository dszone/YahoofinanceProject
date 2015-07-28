package com.mercury.services;

import java.util.Set;

import com.mercury.beans.OwnershipInfo;
import com.mercury.beans.User;
import com.mercury.daos.UserDao;

public class UserService {
	private UserDao ud;
	public UserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	public OwnershipInfo getOwnershipInfoByUser(User user) {
		// TODO Auto-generated method stub
		//ud.addUser(user);
		OwnershipInfo ownershipInfo = new OwnershipInfo();
		ownershipInfo.setMessage("Hello " + user.getUserName() + ", here are you owned stocks!");
		ownershipInfo.setOwnerships(user.getOwnerships());
		return ownershipInfo;
	}

	public OwnershipInfo getOwnershipInfoByUserId(int userId) {
		// TODO Auto-generated method stub
		User user = ud.getUserById(userId);
		OwnershipInfo ownershipInfo = new OwnershipInfo();
		ownershipInfo.setMessage("Hello " + user.getUserName() + ", here are you owned stocks!");
		ownershipInfo.setOwnerships(user.getOwnerships());
		return ownershipInfo;
	}

	public OwnershipInfo getOwnershipInfoByUserName(String userName) {
		// TODO Auto-generated method stub
		User user = ud.getUserByUsername(userName);
		OwnershipInfo ownershipInfo = new OwnershipInfo();
		ownershipInfo.setMessage("Hello " + user.getUserName() + ", here are you owned stocks!");
		ownershipInfo.setOwnerships(user.getOwnerships());
		return ownershipInfo;
	}
	
	public User getUser(int id){
		return ud.getUserById(id);
	}
	
	public User getUserByEmail(String email){
		Set<User> set = ud.queryAll();
		for(User u:set){
			if (u.getEmail().equals(email))
			return u;
		}
		return null;
		
	}


}
