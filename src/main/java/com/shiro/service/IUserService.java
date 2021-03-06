package com.shiro.service;

import java.util.List;

import com.shiro.po.User;

public interface IUserService {
	
	User getUserInfo(String userCode) throws Exception;
	List<String> getPermissionInfo(String userCode) throws Exception;
}
