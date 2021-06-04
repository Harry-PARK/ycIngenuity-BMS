package ycIngenuity.bms.resourceUtil;

import java.time.LocalDateTime;


public class RemoteLight {
	
	private int fieldNum = 10;
	
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
	private String floor;
	private String room_name;
	private String room_code;
	

	
	public RemoteLight(String[] data) {
		if(data.length == fieldNum) {
			setDevice_id(data[0]);
			setConnection_string(data[1]);
			setLightByString(data[2]);
			setOnlineByString(data[3]);
			if(data[4].equals("0")) {
				//0 for new device registry
				last_updated = LocalDateTime.now();
			}
			else {
				setLast_updatedByString(data[4]);
			}
			if(data[5].equals("0")) {
				//0 for new device registry
				installed_date = LocalDateTime.now();
			}
			else {
				setInstalled_dateByString(data[5]);
			}
			setBuilding(data[6]);
			setFloor(data[7]);
			setRoom_name(data[8]);
			setRoom_code(data[9]);
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
	public void setLightByString(String powerOn) {
		if (powerOn.equals("true")) {
			this.light = true;
		}
		else {
			this.light = false;
		}
	}
	public Boolean getOnline() {
		return online;
	}
	public void setOnlineByString(String online) {
		if (online.equals("true")) {
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
		this.last_updated = LocalDateTime.parse(last_updated);
	}
	public LocalDateTime getInstalled_date() {
		return installed_date;
	}
	public void setInstalled_dateByString(String installed_date) {
		this.installed_date = LocalDateTime.parse(installed_date);
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
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
		StringBuilder info = new StringBuilder();
		info.append("{");
		info.append("device_id:" + this.device_id + ",");
		info.append("connection_string:" + this.connection_string + ",");
		info.append("light:"+this.light + ",");
		info.append("online:" + this.online + ",");
		info.append("last_updated:" + this.last_updated.toString() + ",");
		info.append("installed_date:" + this.installed_date.toString() + ",");
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
