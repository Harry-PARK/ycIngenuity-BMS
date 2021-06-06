package ycIngenuity.bms.resourceUtil;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class RemoteLight {

	//connection info
	private String device_id;
	private String connection_string;
	
	//control info
	private Boolean light; //on off
	private Boolean online; // connected
	private LocalDateTime last_updated;
	
	//install info
	private LocalDateTime installed_date;
	private String building;
	private int floor;
	private String room_name;
	private String room_code;
	

	
	public RemoteLight(ResultSet rs) {
		try {
			setDevice_id(rs.getString("device_id"));
			setConnection_string(rs.getString("connection_string"));
			setLightByInteger(rs.getInt("light"));
			setOnlineByInteger(rs.getInt("online"));
			setLast_updatedByString(rs.getString("last_updated"));
			setInstalled_dateByString(rs.getString("installed_date"));
			setBuilding(rs.getString("building"));
			setFloor(rs.getInt("floor"));
			setRoom_name(rs.getString("room_name"));
			setRoom_code(rs.getString("room_code"));
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public String getDevice_id() {
		return device_id;
	}
	private void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getConnection_string() {
		return connection_string;
	}
	private void setConnection_string(String connection_string) {
		this.connection_string = connection_string;
	}
	public Boolean getLight() {
		return light;
	}
	public void setLight(Boolean light) {
		this.light = light;
	}
	public void setLightByInteger(int light) {
		if (light == 1) {
			this.light = true;
		}
		else {
			this.light = false;
		}
	}
	public Boolean getOnline() {
		return online;
	}
	public void setOnline(Boolean online) {
		this.online = online;
	}
	public void setOnlineByInteger(int online) {
		if (online == 1) {
			this.online = true;
		}
		else {
			this.online = false;
		}
	}
	public LocalDateTime getLast_updated() {
		return last_updated;
	}
	public void renewLast_updated() {
		this.last_updated = LocalDateTime.now();
	}
	public void setLast_updatedByString(String last_updated) {
		this.last_updated = LocalDateTime.parse(last_updated.replace(" ", "T"));
	}
	public LocalDateTime getInstalled_date() {
		return installed_date;
	}
	public void setInstalled_dateByString(String installed_date) {
		this.installed_date = LocalDateTime.parse(installed_date.replace(" ", "T"));
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_code() {
		return room_code;
	}
	public void setRoom_code(String room_code) {
		this.room_code = room_code;
	}
	
	public String toJSON(){
		long lu = (ZonedDateTime.of(last_updated, ZoneId.systemDefault())).toInstant().toEpochMilli();
		long insd = (ZonedDateTime.of(installed_date, ZoneId.systemDefault())).toInstant().toEpochMilli();
		StringBuilder info = new StringBuilder();
		info.append("{");
		info.append("device_id:" + this.device_id + ",");
		info.append("connection_string:" + this.connection_string + ",");
		info.append("light:"+this.light + ",");
		info.append("online:" + this.online + ",");
		info.append("last_updated:" + lu + ",");
		info.append("installed_date:" + insd + ",");
		info.append("building:" + this.building + ",");
		info.append("floor:" + this.floor + ",");
		info.append("room_name:" + this.room_name + ",");
		info.append("room_code:" + this.room_code);
		info.append("}");
		return info.toString();
	}

	@Override
	public String toString() {
		return toJSON();
		
	}
	
	public String toCSV() {
		StringBuilder csv = new StringBuilder();
		csv.append(this.device_id +",");
		csv.append(this.connection_string +",");
		csv.append(this.light +",");
		csv.append(this.online +",");
		csv.append(this.last_updated.toString() +",");
		csv.append(this.installed_date.toString() +",");
		csv.append(this.building +",");
		csv.append(this.floor +",");
		csv.append(this.room_name +",");
		csv.append(this.room_code);
		return csv.toString();
	}

}
