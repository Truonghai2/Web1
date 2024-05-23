package Controll.DTO;

import Controll.Entity.User;

public class UserDTO {
	private Integer id;
	private String fullname;
	
	
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.fullname = user.getFullname();
	}
}
