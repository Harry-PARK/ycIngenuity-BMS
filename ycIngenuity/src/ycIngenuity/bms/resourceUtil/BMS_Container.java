package ycIngenuity.bms.resourceUtil;

import java.nio.file.Path;
import java.nio.file.Paths;


public class BMS_Container {
	//Purpose of this class is to make static object
	
	//RemoteLight Resource
	private static Path remoteLightResourcePath = Paths.get("remoteLightResources.csv");
	private static String shareAccessKeyToAzure = "HostName=ycbms.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=dIlYN60BHpa3HN/i+nXzgRy1LkfflxIcAw9leJIXbRY=";
	private static ResourceSLU<RemoteLight> txtRLFS= new TextRemoteLightFileSystem(remoteLightResourcePath);
	private static RemoteLightResourceManager rlm = new RemoteLightResourceManager(txtRLFS, shareAccessKeyToAzure);
	public static RemoteLightResourceManager getRemoteLightManager(){
		return rlm;
	}

	
	//Account Resource
	private static Path AccountResourcePath = Paths.get("accountResources.csv");
	private static ResourceSLU<Account> txtAFS = new TextAccountFileSystem(AccountResourcePath);
	private static AccountResourceManager am = new AccountResourceManager(txtAFS);
	public static AccountResourceManager getAccountRsourceManager(){
		return am;
	}
	
	
}
