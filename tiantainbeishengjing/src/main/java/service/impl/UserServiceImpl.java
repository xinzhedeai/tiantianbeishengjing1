package service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomUserInfoMapper;
import service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private CustomUserInfoMapper customUserInfoMapper;
	
	
	public int checkUser(Map map) throws Exception {
		return customUserInfoMapper.checkUser(map);
	}

	public int checkUser(String userId, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

}
