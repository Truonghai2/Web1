package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.MessageDAO;
import Controll.Entity.message;

public class MessageDAOImp extends AbstactDao<message> implements MessageDAO{
	
	
	@Override
	public List<message> getALLMessageByRoomid(int roomId){
		String sql = "SELECT o FROM message o WHERE o.room.id = ?0 ORDER BY o.created_at DESC";
		return super.findMany(message.class, sql, roomId);
	}
	@Override
	public List<message> getALLMessageByRoomid(int roomId, int page, int size){
		String sql = "SELECT o FROM message o WHERE o.room.id = ?0 ORDER BY o.created_at DESC";
		
		return super.findAllMessage(message.class, sql, roomId, page, size);
	}
}
