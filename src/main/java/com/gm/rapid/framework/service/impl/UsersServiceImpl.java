package com.gm.rapid.framework.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gm.rapid.framework.orm.dao.BaseDAO;
import com.gm.rapid.framework.orm.dao.impl.UsersDAOImpl;
import com.gm.rapid.framework.orm.model.Users;
import com.gm.rapid.framework.service.BaseServiceImpl;
import com.gm.rapid.framework.service.UsersService;
@Service
public class UsersServiceImpl extends BaseServiceImpl<Users> implements UsersService {
	@Inject
	private UsersDAOImpl userDAO;

	@Override
	protected BaseDAO<Users> getDAO(String who) {
		// TODO Auto-generated method stub
		if(who.equals("default"))
			return userDAO;
		else
			return null;
	}
}
