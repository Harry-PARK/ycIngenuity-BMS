package ycIngenuity.bms.resourceUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RemoteLightDBSystem implements ResourceSLU<RemoteLight> {
	
	@Override
	public List<RemoteLight> load_resource() {
		List<RemoteLight> list = new ArrayList<>();
		String query = "SELECT * FROM remotelight";
		try (Connection conn = BMS_Container.getDBConnection()) {
			Statement stmt= conn.createStatement();
			ResultSet  rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(new RemoteLight(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

}
