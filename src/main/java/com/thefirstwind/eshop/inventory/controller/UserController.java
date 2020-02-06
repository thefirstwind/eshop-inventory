package com.thefirstwind.eshop.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstwind.eshop.inventory.model.User;
import com.thefirstwind.eshop.inventory.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/findUserInfo")
	public User findUserInfo() {
		User user = userService.findUserInfo();
		if (user != null) {

		}
		return user;
	}

	@RequestMapping("/getCachedUserInfo")
	public User getCachedUserInfo() {
		User user = userService.getCachedUserInfo();
		if (user != null) {

		}
		return user;
	}

}
