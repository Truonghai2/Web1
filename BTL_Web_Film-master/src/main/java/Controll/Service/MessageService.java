package Controll.Service;

import java.util.List;

import Controll.Entity.User;
import Controll.Entity.message;
import Controll.Entity.room;

public interface MessageService {
	
	List<message> getALLMessageByRoomid(int roomId);
	List<message> getPerPageMessageByRoomid(int roomId, int page , int size);
	
	message createMessage(User user, room room, String body, String link);
	message deleteMessage(int id);
	
}
