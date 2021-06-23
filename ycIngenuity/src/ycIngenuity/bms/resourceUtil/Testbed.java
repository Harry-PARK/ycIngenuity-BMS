package ycIngenuity.bms.resourceUtil;


public class Testbed {

	public static void main(String[] args) throws InterruptedException {
		//RemoteLightResourceManager temp = BMS_Container.getRemoteLightManager();
		//AccountResourceManager tempA = BMS_Container.getAccountRsourceManager();
		//RemoteLight a = temp.getResource().get(0);
		//System.out.println(a.toJSON());
		LogOneResourceManager logone = BMS_Container.getLogOneResoucrManager();
		System.out.println(logone.getResource().size());
		
		System.out.println();
		for (LogOne a : logone.getResource()) {
			System.out.println(a);
		}
	}
}
