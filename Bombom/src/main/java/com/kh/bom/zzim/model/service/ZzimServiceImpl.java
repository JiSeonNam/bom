package com.kh.bom.zzim.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.bom.zzim.model.dao.ZzimDao;
@Service
public class ZzimServiceImpl implements ZzimService {
	@Autowired
	private SqlSession session;
	
	@Autowired
	private ZzimDao dao;
}