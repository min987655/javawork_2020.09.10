package catchcatch.models;

import lombok.Data;

@Data
public class Users {
	
	private int id;
	private String userName;
	private String password;
	
	public Users(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}	
	
	public Users(String userName, String password) {
		this.userName = userName;
		this.password = password;	
	}

	public Users(String userName) {
		this.userName = userName;
	}
		
	@Override
	public String toString() {
		return id + ":" + userName + ":" + password;
	}
}
