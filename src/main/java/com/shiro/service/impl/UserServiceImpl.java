package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.IUserDao;
import com.shiro.po.User;
import com.shiro.service.IUserService;

@Service(value="userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User getUserInfo(String userCode) throws Exception {
		return userDao.getUserInfo(userCode);
	}
	
	@Override
	public List<String> getPermissionInfo(String userCode) throws Exception {
		return userDao.getPermissionInfo(userCode);
	}
}
