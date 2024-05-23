package Controll.DTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Controll.Entity.User;

import Controll.Entity.room;

import Controll.Entity.message;


public class MessageDTO {

	
	private int id;
	private int roomid;
	private int userid;
	private String username;
	private String body;
	private String link;
	
	private Timestamp created_at;
	
	public MessageDTO(message message) {
		this.id = message.getId();
		this.userid = message.getUser().getId();
		this.username = message.getUser().getFullname();
		this.body = message.getBody();
		this.link = message.getLink();
		this.created_at = message.getAddDate();
	}
	
	
	public static List<MessageDTO> objectList(List<message> message){
		List<MessageDTO> messages = new ArrayList<>();
		
		for(message messenger : message) {
			messages.add(new MessageDTO(messenger));
			
		}
		
		return messages;
	}
}
