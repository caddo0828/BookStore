package com.store.manager.service;

import com.store.manager.domain.Manager;

public interface ManagerService {
	Manager find(String name,String pwd);
}
