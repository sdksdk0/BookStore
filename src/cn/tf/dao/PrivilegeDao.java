package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Function;
import cn.tf.domain.Role;
import cn.tf.domain.User;

public interface PrivilegeDao {

	User findUser(String username, String password);

	List<Role> findUserRoles(User user);

	List<Function> findRoleFunctions(Role role);

}
