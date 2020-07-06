package io.taher.models;

import javax.persistence.Entity;

@Entity(name = "user")
public class User extends ModelBase{

	private String name;
	private String email;
	private String password;
	private String mobile;
	private String type;
	private static final long serialVersionUID = 5926468583005150707L;
	public User() {

	}
	
	public User(int id,String name, String email, String password, String mobile,String type) {
		super(id);
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
