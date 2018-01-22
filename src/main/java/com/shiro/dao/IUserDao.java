package com.shiro.dao;

import java.util.List;

import com.shiro.po.User;

public interface IUserDao {
	User getUserInfo(String userCode) throws Exception;
	List<String> getPermissionInfo(String userCode) throws Exception;
}
