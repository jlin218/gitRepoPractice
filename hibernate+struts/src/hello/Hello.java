package hello;

import model.CustomerBean;
import model.CustomerService;

public class Hello {
	private String username;
	private String password;

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String execute(){
	CustomerService cs = new CustomerService();
	CustomerBean cb=cs.login(username, password);
		
	System.out.println(password+username);
		if(cb==null){
			System.out.println("login failed");
			return "login";
		}else{
			
			System.out.println("login successfully");
			
			return "success";
		}
	
	}
}
