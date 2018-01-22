package com.shiro.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.po.User;
import com.shiro.service.IUserService;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	
	//用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的
		// 第一步就是从token中取出身份信息
		String userCode =  (String) token.getPrincipal();
		// 第二步 根据用户身份信息查询数据库
		User user = null;
		SimpleAuthenticationInfo simpleAuthenticationInfo = null;
		try {
			user = userService.getUserInfo(userCode);
			String password = user.getPassword();
			String salt = user.getSalt();
			simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(salt), this.getName());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return simpleAuthenticationInfo;
	}
	
	// 用户授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// 从principals中获取主身份信息
		User user = (User)principals.getPrimaryPrincipal();
		// 根据身份信息获取权限信息 需要连接数据库进行查询
		List<String> permissions = null;
		SimpleAuthorizationInfo simpleAuthorizationInfo = null;
		try {
			permissions = userService.getPermissionInfo(user.getUserCode());
			simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			//添加权限信息
			simpleAuthorizationInfo.addStringPermissions(permissions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回授权信息
		return simpleAuthorizationInfo;
	}
}
