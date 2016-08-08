package cn.tf.service.impl;

import java.util.List;

import cn.tf.dao.PrivilegeDao;
import cn.tf.dao.impl.PrivilegeDaoImpl;
import cn.tf.domain.Function;
import cn.tf.domain.Role;
import cn.tf.domain.User;
import cn.tf.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {

	private PrivilegeDao dao=new PrivilegeDaoImpl();
	@Override
	public User login(String username, String password) {
		return dao.findUser(username,password);
	}
	@Override
	public List<Role> findUserRoles(User user) {
		
		return dao.findUserRoles(user);
	}
	@Override
	public List<Function> findRoleFunctions(Role role) {
		return dao.findRoleFunctions(role);
	}

}
