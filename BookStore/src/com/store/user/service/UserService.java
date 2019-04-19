package com.store.user.service;


import java.util.ArrayList;

import com.store.user.domain.User;

public interface UserService {
	String getNumber();
	
	boolean insert(User user);
	
	User search(String name,String pwd);
	
	boolean findByName(String name) ;
	 
	boolean findByEmail(String email);
	 
	boolean findByTel(String tel);
	 
	User findUserByCode(String code);
	 
	void setStatus(User user);
	 
	ArrayList<User> findAllUser() ;
	 
	boolean updateUser(User user);
	  
	boolean deleteUser(String id);
}
