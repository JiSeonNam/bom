package com.kh.bom.order.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.bom.order.model.vo.Order;

public interface OrderDao {
	int insertOrder(SqlSession session,Order order);
}