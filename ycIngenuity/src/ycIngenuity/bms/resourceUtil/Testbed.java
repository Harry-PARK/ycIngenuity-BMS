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
		int amountInt = 5;
		int i = 0;
		for(LogOne log : BMS_Container.getLogOneResoucrManager().getLogArchive()) {
			if(i++ < amountInt)System.out.println(log);
			else break;
		}
		System.out.println();
		for(LogOne log : BMS_Container.getLogOneResoucrManager().getLogArchive()) {
			System.out.println(log);
		}
	}
}
