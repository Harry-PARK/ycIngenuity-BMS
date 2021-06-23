package ycIngenuity.bms.resourceUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDBSystem implements ResourceSLU<Account> {

	@Override
	public List<Account> load_resource() {
		List<Account> list = new ArrayList<>();
		String query = "SELECT * FROM account";
		try (Connection conn = BMS_Container.getDBConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet  rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(newAccount(rs));
			}
			BMS_Container.getLogOneResoucrManager().newLog(
					LocalDateTime.now(), 
					"AccountDBSystem.load_resource", 
					"Account loaded from DB successfully", 
					200, 
					null, 
					null);
		} catch (SQLException e) {
			e.printStackTrace();
			BMS_Container.getLogOneResoucrManager().newLog(
					LocalDateTime.now(), 
					"AccountDBSystem.load_resource", 
					e.getMessage(), 
					0, 
					null, 
					null);
		}
		return list;
	}

	@Override
	public Boolean save_resource(List<Account> resources) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update_resource(List<Account> resources) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Account newAccount(ResultSet rs) {
		Account act = new Account();
		try {
			act.setEmail(rs.getString("email"));
			act.setPassword(rs.getString("password"));
			act.setName(rs.getString("name"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return act;
	}

	
}
