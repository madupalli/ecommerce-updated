package User;

public class User {
	private int user_id;
	private String password;
	private String name;
	
	public User(int user_id, String name, String password) {
		
		this.user_id = user_id;
		this.name = name;
		this.password = password;
		// TODO Auto-generated constructor stub
		
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
