package model;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport  {
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
	
	

	@Override
	public void validate() {
		if (username == null || username.length()==0){
			this.addFieldError("username", "請輸入帳號");
			
		}
		if (password == null || password.length()==0){
			this.addFieldError("password", "請輸入密碼");
			
		}

	}


	public String execute(){
	
		
	CustomerService cs = new CustomerService();
	CustomerBean cb=cs.login(username, password);
		
	System.out.println(password+username);
		if(cb==null){
			System.out.println("login failed");
			this.addFieldError("loginError", "帳號密碼don't match");
			return "login";
		}else{
			
			System.out.println("login successfully");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("user", cb);
			return "success";
		}
	
	}
}