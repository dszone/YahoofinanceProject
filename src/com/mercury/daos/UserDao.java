package com.mercury.daos;

import java.util.Set;

import com.mercury.beans.User;

public interface UserDao {
	public void addUser(User user);
	public void updateUser(User user);
	public User getUserById(int userId);
	public User getUserByUsername(String userName);
	public Set<User> queryAll();
	
}
