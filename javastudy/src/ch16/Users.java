package ch16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//모델, Beans(콩)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	private int id;
	private String name;
	private String email;
	private String password;

}
