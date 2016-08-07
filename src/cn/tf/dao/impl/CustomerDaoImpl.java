package cn.tf.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.tf.dao.CustomerDao;
import cn.tf.domain.Customer;
import cn.tf.utils.C3P0Util;

public class CustomerDaoImpl implements CustomerDao {

	private QueryRunner  qr=new QueryRunner(C3P0Util.getDataSource());
	
	public void save(Customer customer) {
		try {
			qr.update("insert into customers (id,username,password,phone,address,email,code,actived) values (?,?,?,?,?,?,?,?)", 
					customer.getId(),customer.getUsername(),customer.getPassword(),customer.getPhone(),customer.getAddress(),
					customer.getEmail(),customer.getCode(),customer.isActived());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void update(Customer customer) {
		try {
			qr.update("update customers set username=?,password=?,phone=?,address=?,email=?,code=?,actived=? where id=?", 
					customer.getUsername(),customer.getPassword(),customer.getPhone(),customer.getAddress(),
					customer.getEmail(),customer.getCode(),customer.isActived(),customer.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Customer findByCode(String code) {
		try {
			return qr.query("select * from customers where code=?", new BeanHandler<Customer>(Customer.class),code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Customer find(String username, String password) {
		try {
			return qr.query("select * from customers where username=? and password=?", new BeanHandler<Customer>(Customer.class),username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
