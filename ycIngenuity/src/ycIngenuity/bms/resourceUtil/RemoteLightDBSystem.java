package ycIngenuity.bms.resourceUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RemoteLightDBSystem implements ResourceSLU<RemoteLight> {
	
	@Override
	public List<RemoteLight> load_resource() {
		List<RemoteLight> list = new ArrayList<>();
		String query = "SELECT * FROM remotelight";
		try (Connection conn = BMS_Container.getDBConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet  rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(newRemoteLight(rs));
			}
			BMS_Container.getLogOneResoucrManager().newLog(
					LocalDateTime.now(), 
					"RemoteLightDBSystem.load_resource", 
					"RemoteLight loaded from DB successfully", 
					200, 
					null, 
					null);
		} catch (SQLException e) {
			e.printStackTrace();
			BMS_Container.getLogOneResoucrManager().newLog(
					LocalDateTime.now(), 
					"RemoteLightDBSystem.load_resource", 
					e.getMessage(), 
					0, 
					null, 
					null);
		}
		return list;
	}

	@Override
	public Boolean save_resource(List<RemoteLight> resources) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update_resource(List<RemoteLight> resources) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RemoteLight newRemoteLight(ResultSet rs) {
		RemoteLight rl = new RemoteLight();
		try {
			rl.setDevice_id(rs.getString("device_id"));
			rl.setConnection_string(rs.getString("connection_string"));
			rl.setLightByInteger(rs.getInt("light"));
			rl.setOnlineByInteger(rs.getInt("online"));
			rl.setLast_updatedByString(rs.getString("last_updated"));
			rl.setInstalled_dateByString(rs.getString("installed_date"));
			rl.setBuilding(rs.getString("building"));
			rl.setFloor(rs.getInt("floor"));
			rl.setRoom_name(rs.getString("room_name"));
			rl.setRoom_code(rs.getString("room_code"));
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return rl;
	}

}
