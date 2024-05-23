package Controll.Dao;

import java.util.List;

import Controll.Entity.message;

public interface MessageDAO {
	
	List<message> getALLMessageByRoomid(int roomId);
	List<message> getALLMessageByRoomid(int roomId, int page, int size);
	
	
	message create(message message);
	message delete(message message);
}
