package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Order;
import cn.tf.domain.OrderItem;

public interface OrderDao {

	void save(Order order);

	Order findByNum(String ordernum);

	void update(Order order);

	//订单号降序排序
	List<Order> findByCustomerId(String customerId);

	
	List<OrderItem> findOrderItem(String ordernum);

}
