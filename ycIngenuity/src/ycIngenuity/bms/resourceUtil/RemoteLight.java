package ycIngenuity.bms.resourceUtil;

import java.util.Date;

public class RemoteLight {
	
	private int fieldNum = 10;
	
	//connection info
	private String device_id;
	private String connection_string;
	
	//control info
	private Boolean powerOn; //on off
	private Boolean online; // connected
	private Date last_updated;
	
	//install info
	private Date installed_date;
	private String building;
	private String floor;
	private String room_name;
	private String room_code;
	

	
	public RemoteLight(String[] data) {
		if(data.length == fieldNum) {
			setDevice_id(data[0]);
			setConnection_string(data[1]);
			setPowerOnByString(data[2]);
			setOnlineByString(data[3]);
			if(data[4].equals("0")) {
				//0 for new device registry
				last_updated = new Date();
			}
			else {
				setLast_updatedByString(data[4]);
			}
			if(data[5].equals("0")) {
				//0 for new device registry
				installed_date = new Date();
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
	public Boolean getPowerOn() {
		return powerOn;
	}
	public void setPowerOnByString(String powerOn) {
		if (powerOn.equals("true")) {
			this.powerOn = true;
		}
		else {
			this.powerOn = false;
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
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updatedByString(String last_updated) {
		Long stime = Long.parseLong(last_updated);
		this.last_updated = new Date(stime);
	}
	public Date getInstalled_date() {
		return installed_date;
	}
	public void setInstalled_dateByString(String installed_date) {
		Long stime = Long.parseLong(installed_date);
		this.installed_date = new Date(stime);
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
		info.append(" device_id:" + this.device_id + ",");
		info.append(" connection_string:" + this.connection_string + ",");
		info.append(" power_on:"+this.powerOn + ",");
		info.append(" online:" + this.online + ",");
		info.append(" last_updated:" + this.last_updated.getTime() + ",");
		info.append(" installed_date:" + this.installed_date.getTime() + ",");
		info.append(" building:" + this.building + ",");
		info.append(" floor:" + this.floor + ",");
		info.append(" room_name:" + this.room_name + ",");
		info.append(" room_code:" + this.room_code);
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
		csv.append(this.powerOn +",");
		csv.append(this.online +",");
		csv.append(this.last_updated.getTime() +",");
		csv.append(this.installed_date.getTime() +",");
		csv.append(this.building +",");
		csv.append(this.floor +",");
		csv.append(this.room_name +",");
		csv.append(this.room_code);
		return csv.toString();
	}

}
