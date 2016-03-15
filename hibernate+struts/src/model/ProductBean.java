package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProductBean  implements Serializable{
	private Integer id;
	private String name;
	private Double price;
	private java.util.Date make;
	private Integer expire;
	public ProductBean() {
		
	}
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	public ProductBean(String[] temp) throws IllegalArgumentException {
		if(temp!=null) {
			if(temp.length==5) {
				id = Integer.parseInt(temp[0]);
				name = temp[1];
				price = Double.parseDouble(temp[2]);
				try {
					make = sFormat.parse(temp[3].trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				expire = Integer.parseInt(temp[4]);
			} else {
				throw new IllegalArgumentException("argument length error "+ temp.length);
			}
		}
	}
	@Override
	public String toString() {
		return "ProductBean [" + id + "," + name + "," + price + "," + make + "," + expire+ "]";
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
		this.make = make;
	}
	public Integer getExpire() {
		return expire;
	}
	public void setExpire(Integer expire) {
		this.expire = expire;
	}
}
