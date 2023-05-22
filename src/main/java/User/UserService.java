package User;

import StorageLayer.MysqlStorage;

import java.util.HashMap;

public class UserService {
	public UserService() {
		
	}

	MysqlStorage mysqlStorage = new MysqlStorage();
	 
	HashMap <Integer,String> userRecord = new HashMap<>();

	private User user;
	
	public  void signup(User user) {

		mysqlStorage.userSignUp(user);
		
	}
	
	public void login(User user ) {
		  mysqlStorage.userLogin(user);
		
		
		
	}
	
	public boolean logout(User user) {
		
		String storedPassword = userRecord.get(user.getUser_id());
		if(storedPassword.equals(user.getPassword())) {
		return true;
	}
		else {
			return false;
		}
	}
	

}
