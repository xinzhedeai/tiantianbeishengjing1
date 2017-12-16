package service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomUserMapper;
import service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private CustomUserMapper customUserInfoMapper;

	@Override
	public int checkUser(Map map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
