package com.iTime.dao;

import com.iTime.model.User;

public interface UserDAO {

	public int createUser(User user);
	
	public User loginUser(User user);
	
}
