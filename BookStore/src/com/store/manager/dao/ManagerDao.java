package com.store.manager.dao;

import com.store.manager.domain.Manager;

public interface ManagerDao {
	/**
	 * 判断数据库表manager中的管理员信息是否匹配，匹配成功则返回正确匹配的对象
	 * 否则对象为空
	 * @param name  管理员账号
	 * @param pwd   管理员密码
	 * @return 根据账号，及账号查询到的管理员对象
	 */
	Manager find(String name,String pwd);
}
