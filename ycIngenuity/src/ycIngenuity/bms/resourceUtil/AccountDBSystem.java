package ycIngenuity.bms.resourceUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDBSystem implements ResourceSLU<Account> {

	@Override
	public List<Account> load_resource() {
		List<Account> list = new ArrayList<>();
		String query = "SELECT * FROM account";
		try (Connection conn = BMS_Container.getDBConnection()) {
			Statement stmt= conn.createStatement();
			ResultSet  rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(new Account(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

}
