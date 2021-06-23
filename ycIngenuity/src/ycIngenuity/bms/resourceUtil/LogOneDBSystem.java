package ycIngenuity.bms.resourceUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogOneDBSystem implements ResourceSLU<LogOne> {

	@Override
	public List<LogOne> load_resource() {
		List<LogOne> list = new ArrayList<>();
		String query = "SELECT * FROM logone ORDER BY event_date DESC LIMIT 100000";
		try(Connection conn = BMS_Container.getDBConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(newLogOne(rs));
			}
			//It can't use BMS_container.LogOneResourceManager in this method, because
			//it's before initiating LogOneResourceMaanger
			Collections.reverse(list);
			LogOne log = new LogOne();
			log.setEvent_date(LocalDateTime.now());
			log.setMethod_name("LogOneDBSystem.load_resource");
			log.setMessage("Log loaded from DB successfully");
			log.setStatus(200);
			list.add(log);
			System.out.println(log);
		} catch(SQLException e) {
			e.printStackTrace();
			LogOne log = new LogOne();
			log.setEvent_date(LocalDateTime.now());
			log.setMethod_name("LogOneDBSystem.load_resource");
			log.setMessage(e.getMessage());
			log.setStatus(0);
			list.add(log);
			System.out.println(log);
		}
		return list;
	}

	@Override
	public Boolean save_resource(List<LogOne> resources){
		Connection conn = null;
		try{
			conn = BMS_Container.getDBConnection();
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			for(LogOne lo : resources) {
				String query = "INSERT INTO ycbms.logone("
						+ "event_date, "
						+ "method_name, "
						+ "message, "
						+ "status, "
						+ "device_id, "
						+ "email) "
						+ "VALUES('"
						+ lo.getEvent_date()+"','"
						+ lo.getMethod_name()+"','"
						+ lo.getMessage()+"','"
						+ lo.getStatus()+"','"
						+ lo.getDevice_id()+"','"
						+ lo.getEmail()+"')";
				stmt.addBatch(query);
			}
			stmt.executeBatch();
			conn.commit();
			resources.clear();
			BMS_Container.getLogOneResoucrManager().newLog(
					LocalDateTime.now(), 
					"LogOneDBSystem.load_resource", 
					"Log saved from DB successfully", 
					200, 
					null, 
					null);
		} catch (SQLException e) {
			e.printStackTrace();
			BMS_Container.getLogOneResoucrManager().newLog(
					LocalDateTime.now(), 
					"LogOneDBSystem.load_resource", 
					e.getMessage(), 
					0, 
					null, 
					null);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean update_resource(List<LogOne> resources) {
		return null;
	}
	

	public LogOne newLogOne(ResultSet rs){
		//Mapping LogOne from ResultSet
		LogOne lo = new LogOne();
		try {
			lo.setEvent_dateByString(rs.getString("event_date"));
			lo.setMethod_name(rs.getString("method_name"));
			lo.setMessage(rs.getString("message"));
			lo.setStatus(rs.getInt("status"));
			lo.setDevice_id(rs.getString("device_id"));
			lo.setEmail(rs.getString("email"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return lo;
		
	}

}
