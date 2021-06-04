package ycIngenuity.bms.resourceUtil;

import java.time.LocalDateTime;
public class Testbed {

	public static void main(String[] args) throws InterruptedException {
		//RemoteLightResourceManager temp = BMS_Container.getRemoteLightManager();
		//AccountResourceManager tempA = BMS_Container.getAccountRsourceManager();
		/*
		RemoteLight a = temp.getResource().get(0);
		System.out.println(a.getDevice_id());
		temp.lightOn("lab1");
		*/
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		
		try {
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		System.out.println(now.toString());
		
		LocalDateTime late = LocalDateTime.parse(now.toString());
		System.out.println(late.toString());
		String version = System.getProperty("java.version");
		System.out.println(version);
		
	}
}
