package com.kh.bom.ship.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.bom.ship.model.vo.Ship;
@Repository
public class ShipDaoImpl implements ShipDao {

	@Override
	public List<Ship> selectShipList(SqlSession session,String memNo) {
		// TODO Auto-generated method stub
		return session.selectList("ship.selectShipList",memNo);
	}

	@Override
	public int insertShip(SqlSession session, Ship s) {
		// TODO Auto-generated method stub
		System.out.println(s);
		if(s.getShipYn().equals("on")) {
			int result=session.update("ship.updateShipYn",s.getMemNo());
			if(result>0) {
				return session.insert("ship.insertShip",s);
			}else {
				return 0;
			}
		}else {	
			return session.insert("ship.insertShip",s);
		}		
	}

}
