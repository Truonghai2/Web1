package Controll.Dao.Impl;

import Controll.Dao.AbstactDao;
import Controll.Dao.RoomDAO;
import Controll.Entity.room;

public class RoomDAOImp extends AbstactDao<room> implements RoomDAO {
	
	
	@Override
	public room find(int id) {
		String sql = "SELECT o FROM room o WHERE o.id = ?0";
		return super.findOne(room.class, sql, id);
	}
	
	
}
