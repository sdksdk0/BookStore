package cn.tf.dao;

import cn.tf.domain.Customer;

public interface CustomerDao {

	void save(Customer customer);

	void update(Customer customer);

	Customer findByCode(String code);

	Customer find(String username, String password);

}
