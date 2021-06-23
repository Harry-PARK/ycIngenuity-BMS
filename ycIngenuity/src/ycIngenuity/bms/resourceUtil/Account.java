package ycIngenuity.bms.resourceUtil;

public class Account {
	
	private String email;
	private String password;
	private String name;
	
	
	public Account() {}

	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toJSON() {
		StringBuilder info = new StringBuilder();
		info.append("{");
		info.append("email:" + email + ",");
		info.append("pw:" + password + ",");
		info.append("name:"+name);
		info.append("}");
		return info.toString();
	}
	
	@Override
	public String toString() {
		return toJSON();
	}
	
	public String toCSV() {
		StringBuilder csv = new StringBuilder();
		csv.append(email +",");
		csv.append(password+",");
		csv.append(name);
		return csv.toString();
	}


	
	
}
