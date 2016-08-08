package cn.tf.service;

import java.util.List;

import cn.tf.domain.Function;
import cn.tf.domain.Role;
import cn.tf.domain.User;

public interface PrivilegeService {

	User login(String username, String password);
//查询用户的所有角色
	List<Role> findUserRoles(User user);

	List<Function> findRoleFunctions(Role role);


}
