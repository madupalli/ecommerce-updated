package CustomerPack;
import User.User;

public class Customer extends User{
	public Customer(int user_id, String name, String password) {
		super(user_id, name, password);
		// TODO Auto-generated constructor stub
	}
	private String address;
	private int dob;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDob() {
		return dob;
	}
	public void setDob(int dob) {
		this.dob = dob;
	}

}
