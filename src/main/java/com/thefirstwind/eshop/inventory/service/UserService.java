package com.thefirstwind.eshop.inventory.service;

import com.thefirstwind.eshop.inventory.model.User;

public interface UserService {
	public User findUserInfo();

	public User getCachedUserInfo();

}
