package Controll.Service.Impl;

import Controll.Dao.RoomDAO;
import Controll.Dao.Impl.RoomDAOImp;
import Controll.Entity.room;
import Controll.Service.RoomService;

public class RoomServiceImp implements RoomService{
	private RoomDAO roomDao;
	
	public RoomServiceImp() {
		roomDao = new RoomDAOImp();
	}
	
	@Override
	public room findbyid(int id) {
		return roomDao.find(id);
	}
}
