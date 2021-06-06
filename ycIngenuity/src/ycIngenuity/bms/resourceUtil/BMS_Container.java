package ycIngenuity.bms.resourceUtil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BMS_Container {
	//Purpose of this class is to make static object
	
	//Database Info Configuration
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static String db_url = "jdbc:mysql://ycbmsdb.mysql.database.azure.com:3306/ycbms";
	private static String db_user = "jingyu@ycbmsdb";
	private static String db_pw = "Asdp1234";
	public static Connection getDBConnection() throws SQLException {
		System.out.println("Connecting to a ycbms database...");
		return DriverManager.getConnection(db_url, db_user, db_pw);
	}
	
	
	//RemoteLight Resource
	private static String shareAccessKeyToAzure = "HostName=ycbms.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=dIlYN60BHpa3HN/i+nXzgRy1LkfflxIcAw9leJIXbRY=";
	private static ResourceSLU<RemoteLight> RLDFS= new RemoteLightDBSystem();
	private static RemoteLightResourceManager rlm = new RemoteLightResourceManager(RLDFS, shareAccessKeyToAzure);
	public static RemoteLightResourceManager getRemoteLightManager(){
		return rlm;
	}

	
	//Account Resource
	private static ResourceSLU<Account> ADFS = new AccountDBSystem();
	private static AccountResourceManager am = new AccountResourceManager(ADFS);
	public static AccountResourceManager getAccountRsourceManager(){
		return am;
	}
	
}
