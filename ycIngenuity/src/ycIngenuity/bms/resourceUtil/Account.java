package ycIngenuity.bms.resourceUtil;

public class Account {
	
	private int fieldNum = 2;
	
	private String email;
	private String pw;
	
	
	public Account() {
	}
	
	public Account(String[] data) {
		if(data.length == fieldNum) {
			setEmail(data[0]);
			setPw(data[1]);
		}
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String toJSON() {
		StringBuilder info = new StringBuilder();
		info.append("{");
		info.append(" Email:" + email + ",");
		info.append(" Pw:" + pw + ",");
		return info.toString();
	}
	
	@Override
	public String toString() {
		return toJSON();
	}
	
	public String toCSV() {
		StringBuilder csv = new StringBuilder();
		csv.append(email +",");
		csv.append(pw);
		return csv.toString();
	}
	
	
}
