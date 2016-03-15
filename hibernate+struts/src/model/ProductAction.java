package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;



public class ProductAction extends ActionSupport implements SessionAware{
	private Integer id;
	private String name;
	private Double price;
	private java.util.Date make;
	private Integer expire;
	private String production;
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public java.util.Date getMake() {
		return make;
	}
	public void setMake(java.util.Date make) {
		System.out.println("set");
		this.make = make;
	}
	public Integer getExpire() {
		return expire;
	}
	public void setExpire(Integer expire) {
		this.expire = expire;
	}
	private Map<String, Object> session =new HashMap<String,Object>();
	
	@Override
	public void validate() {
		System.out.println("validate");
		if (production!= null){
			if (production.equals("Insert")||production.equals("Update")||production.equals("Delete")){
				if(id==null||id<=0){
					this.addFieldError("idError","id 不是一個合法的數字");
				}
				if(price==null||price<=0){
					this.addFieldError("priceError", "price 不是一個合法的數字");
		
				}
				if(make==null){
					this.addFieldError("dateError", "Date 不是一個合法的日期");
				}
				if(expire==null||expire<0){
					this.addFieldError("expireError", "Expire 不是一個合法的日期");
				}
			}
		}
	}
	public String execute(){
		System.out.println("dsfdsf");
		System.out.println(make);
		System.out.println(name);
		
		ProductBean pb = new ProductBean();
		pb.setId(id);
		System.out.println("id:  "+id);
		pb.setName(name);
		pb.setExpire(expire);
		System.out.println("pb:expire"+pb.getExpire());
		pb.setPrice(price);
		pb.setMake(make);
		System.out.println(production);
		ProductService service = new ProductService();
		if (production!= null){
			if (production.equalsIgnoreCase("insert")){
				ProductBean successful = service.insert(pb);
				if(successful!=null){
					System.out.println("insert successfully");
					this.addFieldError("msg","insert successfully");
					return "success"; 
				}else{
					System.out.println("insert unsuccessfully");
					this.addFieldError("msg","insert unsuccessfully");
					return "input"; 
				}

				
			}else if(production.equalsIgnoreCase("Update")){
				System.out.println("make"+make);
				ProductBean successful = service.update(pb); 
				
				if(successful!=null){
					System.out.println("Update successfully");
					this.addFieldError("msg","Update successfully");
					return "success"; 
				}else{
					System.out.println("Update unsuccessfully");
					this.addFieldError("msg","Update unsuccessfully");
					return "input"; 
				}

				
			}else if(production.equalsIgnoreCase("Delete")){
				boolean successful = service.delete(pb);
				if(successful){
					System.out.println("Delete successfully");
					this.addFieldError("msg","Delete successfully");
					return "success"; 
				}else{
					System.out.println("Delete unsuccessfully");
					this.addFieldError("msg","Delete successfully");
					return "input"; 
				}
				
				
			}
			else if(production.equalsIgnoreCase("Select")){
				System.out.println("hellpo");

				List<ProductBean> list = service.select(pb);
				System.out.println(list);
				System.out.println(session);
				session.put("select", list);
				System.out.println(session);
				return "display";
			}
		}
		
		
		return "success";
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
		
	}

	
}
