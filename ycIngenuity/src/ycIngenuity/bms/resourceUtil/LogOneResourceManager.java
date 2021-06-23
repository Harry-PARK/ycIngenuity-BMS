package ycIngenuity.bms.resourceUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class LogOneResourceManager extends ResourceManager<LogOne> {

	//resourceList in ResourceManager for new Log to save DB
	//log_archive is for inquiry
	private List<LogOne> log_archive = new ArrayList<>();
	
	public LogOneResourceManager(ResourceSLU<LogOne> rslu) {
		super(rslu);
		
		//move logs from resourceList to log_archive, and empty resourceList
		for(LogOne log : resourceList) {
			add_archive(log);
		}
		resourceList.clear();
		activateSaveThread();
	}
	
	public void newLog(
			LocalDateTime ldt,
			String method_name,
			String message,
			int status,
			String device_id,
			String email
			){
		LogOne log = new LogOne();
		log.setEvent_date(ldt);
		log.setMethod_name(method_name);
		log.setMessage(message);
		log.setState(status);
		log.setDevice_id(device_id);
		log.setEmail(email);
		newLogMethod(log);
	}
	
	public void newLog(LogOne log) {
		newLogMethod(log);
	}
	
	public void newLogMethod(LogOne log) {
		resourceList.add(log);
		add_archive(log);
		System.out.println(log);
	}
	
	private void add_archive(LogOne log) {
		if (log_archive.size() < 100000) {
			log_archive.add(log);
		}
		else {
			log_archive.remove(log_archive.size()-1);
			log_archive.add(log);
		}
	}
	public List<LogOne> getLogArchive(){
		return log_archive;
	}

	@Override
	public List<LogOne> getResource() {
		return log_archive;
	}
	
	private void activateSaveThread() {
		Thread saveThread = new Thread(()->{
			try {
				int hour = 3600*1000;
				while(true) {
					Thread.sleep(hour*1);
					save_resource();
				}
			}
			catch(Exception e) {
				newLog(LocalDateTime.now(), "LogOneResourceManager.activeSaveThread", e.getMessage(), 0, null, null);
			}
		});
		saveThread.start();
	}
	

}
