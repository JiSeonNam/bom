package com.kh.bom.admin.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.bom.admin.model.vo.Event;

public interface AdminDao {
	List<Event> selectEvent(SqlSession session);
}