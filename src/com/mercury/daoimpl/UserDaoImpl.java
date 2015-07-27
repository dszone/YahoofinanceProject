package com.mercury.daoimpl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.User;
import com.mercury.daos.UserDao;

public class UserDaoImpl implements UserDao {
	private HibernateTemplate template;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}
	
	
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override

	public void addUser(User user) {

		// TODO Auto-generated method stub
		template.save(user);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub

		return (User)template.get(User.class, userId);

	}

	@Override
	public User getUserByUsername(String userName) {
		// TODO Auto-generated method stub

		String hql = "from User as user where user.userName ='" + userName + "'";
		@SuppressWarnings("unchecked")
		List<User> users = template.find(hql);
		return users.get(0);

	}


	@SuppressWarnings("unchecked")
	@Override
	public Set<User> queryAll() {
		// TODO Auto-generated method stub
		
		String hql = "from User";
		return new HashSet<User>(template.find(hql));
	}

}
