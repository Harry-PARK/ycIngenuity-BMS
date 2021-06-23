package ycIngenuity.bms;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import ycIngenuity.bms.resourceUtil.BMS_Container;
import ycIngenuity.bms.resourceUtil.LogOne;
import ycIngenuity.bms.resourceUtil.RemoteLight;

public class RemoteLightREST {
	
	static String msg_lighton = "Light On executed";
	static String msg_lightoff = "Light Off executed";
	static String msg_update = "Update executed";
	static String msg_invalidCommand = "Invalid Command"; 
	static String msg_invalidRESTCONKEY = "Invalid RESTCONKEY";
	static String msg_notValidAccount = "Not a valid account";

	static void putMain(HttpServletRequest req, HttpServletResponse resp, String[] command, HashMap<String, String> param) throws IOException {
		String method_name = param.get("command");
		String device_id = command[3];
		PrintWriter out = resp.getWriter();
		HashMap<String, String> resultMap = new HashMap<>();
		LogOne log = new LogOne();
		log.setEvent_date(LocalDateTime.now());
		log.setMethod_name(method_name);
		log.setDevice_id(device_id);
		log.setEmail(param.get("email"));
		/*
		 * controller for remoteLight
		 */
		//It requires authorized account to control something
		if(!CommonRESTUtil.checkValidAccount(param.get("email"), param.get("pw"))) {
			//failed to get authority
			CommonRESTUtil.initResultMap(resultMap, false, 0, msg_notValidAccount, log);
			out.println(CommonRESTUtil.mapJSON(resultMap));
			BMS_Container.getLogOneResoucrManager().newLog(log);
			return;
		}
		
		try {
		MethodResult mr;
		switch(method_name) {
		case "lighton":
			mr = BMS_Container.getRemoteLightManager().lightOn(device_id);
			if(mr == null) {
				CommonRESTUtil.initResultMap(resultMap, false, 0, null, log);
			}
			else if (mr.getStatus() == 200) {
				CommonRESTUtil.initResultMap(resultMap, true, 200, msg_lighton, log);
			}
			else {
				CommonRESTUtil.initResultMap(resultMap, false, 0, null, log);
			}
			break;
		case "lightoff":
			mr = BMS_Container.getRemoteLightManager().lightOff(device_id);
			if(mr == null) {
				CommonRESTUtil.initResultMap(resultMap, false, 0, null, log);
			}
			else if (mr.getStatus() == 200) {
				CommonRESTUtil.initResultMap(resultMap, true, 200, msg_lightoff, log);
			}
			else {
				CommonRESTUtil.initResultMap(resultMap, false, 0, null, log);
			}
			break;
		case "update":
			int lightOnOff = Integer.parseInt(param.get("light"));
			BMS_Container.getRemoteLightManager().update(device_id, lightOnOff);
			CommonRESTUtil.initResultMap(resultMap, true, 200, null, log);
			break;
		default:
			CommonRESTUtil.initResultMap(resultMap, false, 0, msg_invalidCommand, log);
			break;
		}
		
		out.println(CommonRESTUtil.mapJSON(resultMap));
		}
		catch(Exception e) {
			CommonRESTUtil.initResultMap(resultMap, false, 0, e.getMessage(), log);
		}
		BMS_Container.getLogOneResoucrManager().newLog(log);
	}
	
	static void getMain(HttpServletRequest req, HttpServletResponse resp, String[] command) throws IOException {
		PrintWriter out = resp.getWriter();
		
		//command[3] : id
		String device_id = CommonRESTUtil.expect(command, 3);
		
		if(device_id == null) {
			for (RemoteLight rl : BMS_Container.getRemoteLightManager().getResource()) {
				out.println(rl);
			}
		}
		else {
			RemoteLight rl = BMS_Container.getRemoteLightManager().findByDeviceID(device_id);
			out.println(rl);
		}
		
		
		
		
	}
	
	

	
}
