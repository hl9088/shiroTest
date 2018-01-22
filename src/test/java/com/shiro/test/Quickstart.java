package com.shiro.test;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

	private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);

	@Test
	public void test() {

		// 通过ini配置文件 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		// 创建SecurityManager工厂
		SecurityManager securityManager = factory.getInstance();
		
		// 将SecurityManager工厂设置在当前运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		
		// 从SecurityUtils中创建一个subject
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			
			// 令牌
			UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
			
			try {
				currentUser.login(token);
				log.info("****************认证通过****************");
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				log.info("****************认证失败****************");
			}
		}

		// 判断是否为某角色
		if (currentUser.hasRole("schwartz")) {
			log.info("有某个角色");
		} else {
			log.info("没有某个角色");
		}

		// 判断是否为某权限
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("有某个权限");
		} else {
			log.info("没有某个权限");
		}

		// log out!
		currentUser.logout();

		System.exit(0);
	}

	@Test
	public void test2() {

		// 通过ini配置文件 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");

		// 创建SecurityManager工厂
		SecurityManager securityManager = factory.getInstance();

		// 将SecurityManager工厂设置在当前运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils中创建一个subject
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(
					"lonestarr", "11111");
			try {
				// 执行认证提交
				currentUser.login(token);
				log.info("****************认证通过****************");
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				log.info("****************认证失败****************");
			}
		}

		// 判断是否为某角色
		if (currentUser.hasRole("schwartz")) {
			log.info("有某个角色");
		} else {
			log.info("没有某个角色");
		}

		// 判断是否为某权限
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("有某个权限");
		} else {
			log.info("没有某个权限");
		}

		// log out!
		currentUser.logout();

		System.exit(0);
	}
	
	@Test
	public void test3() {
		
		// 通过ini配置文件 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
		
		// 创建SecurityManager工厂
		SecurityManager securityManager = factory.getInstance();
		
		// 将SecurityManager工厂设置在当前运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		
		// 从SecurityUtils中创建一个subject
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(
					"admin", "11111");
			try {
				// 执行认证提交
				currentUser.login(token);
				log.info("****************认证通过****************");
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				log.info("****************认证失败****************");
			}
		}
		
		// 判断是否为某角色
		if (currentUser.hasRole("schwartz")) {
			log.info("有某个角色");
		} else {
			log.info("没有某个角色");
		}
		
		// 判断是否为某权限
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("有某个权限");
		} else {
			log.info("没有某个权限");
		}
		
		// log out!
		currentUser.logout();
		
		System.exit(0);
	}
	
	@Test
	public void testAuthorization() {
		
		// 通过ini配置文件 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		// 创建SecurityManager工厂
		SecurityManager securityManager = factory.getInstance();
		
		// 将SecurityManager工厂设置在当前运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		
		// 从SecurityUtils中创建一个subject
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(
					"admin", "11111");
			try {
				// 执行认证提交
				currentUser.login(token);
				log.info("****************认证通过****************");
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				log.info("****************认证失败****************");
			}
		}
		
		// 判断是否为某权限
		if (currentUser.isPermitted("user:create")) {
			log.info("有某个权限");
		} else {
			log.info("没有某个权限");
		}
		
		// log out!
		currentUser.logout();
		
		System.exit(0);
	}
}
