package ycIngenuity.bms.resourceUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LogOne {
	private LocalDateTime event_date;
	private String method_name;
	private String message;
	private int state;
	private String device_id;
	private String email;
	
	
	public LogOne() {}
	
	
	public LocalDateTime getEvent_date() {
		return event_date;
	}
	public void setEvent_date(LocalDateTime event_date) {
		this.event_date = event_date;
	}
	public void setEvent_dateByString(String event_date) {
		this.event_date = LocalDateTime.parse(event_date.replace(" ", "T"));
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String toJSON() {
		long ed = (ZonedDateTime.of(event_date, ZoneId.systemDefault())).toInstant().toEpochMilli();
		StringBuilder info = new StringBuilder();
		info.append("{");
		info.append("event_date:"+ed+",");
		info.append("method_name:"+this.method_name+",");
		info.append("message:"+this.message+",");
		info.append("state:"+this.state+",");
		info.append("device_id:"+this.device_id+",");
		info.append("email:"+this.email);
		info.append("}");
		return info.toString();
	}
	@Override
	public String toString() {
		return toJSON();
	}













	



}
