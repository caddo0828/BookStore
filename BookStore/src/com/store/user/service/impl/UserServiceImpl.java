package com.store.user.service.impl;

import java.util.ArrayList;
import java.util.Random;

import com.store.user.dao.UserDao;
import com.store.user.dao.impl.UserDaoImpl;
import com.store.user.domain.User;
import com.store.user.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	@Override
	public boolean insert(User user) {
		return dao.insert(user);
	}

	@Override
	public User search(String name, String pwd) {
		return dao.search(name, pwd);
	}

	@Override
	public boolean findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public boolean findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public boolean findByTel(String tel) {
		return dao.findByTel(tel);
	}

	@Override
	public User findUserByCode(String code) {
		return dao.findUserByCode(code);
	}

	@Override
	public void setStatus(User user) {
		dao.setStatus(user);
	}

	@Override
	public ArrayList<User> findAllUser() {
		return dao.findAllUser();
	}

	@Override
	public boolean updateUser(User user) {
		return dao.updateUser(user);
	}

	@Override
	public boolean deleteUser(String id) {
		return dao.deleteUser(id);
	}

	@Override
	public String getNumber() {
		Random random = new Random();
		//设置随机数范围
		String number = random.nextInt(9999)+"";
		
		//定义字符串缓冲区
		StringBuffer buffer = new StringBuffer();
			
	    //当生成的随机数不满足四位要求时，数值前位补0			
		for(int i=0;i<4-number.length();i++) {
			buffer.append("0");
		}
		
		//将随机数进行字符串拼接
		number = buffer.toString()+number;
		return number;
	}

}
