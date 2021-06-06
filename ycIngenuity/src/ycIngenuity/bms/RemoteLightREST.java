package ycIngenuity.bms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import ycIngenuity.bms.resourceUtil.BMS_Container;
import ycIngenuity.bms.resourceUtil.RemoteLight;

public class RemoteLightREST {
	
	static String msg_lighton = "'Light On' executed";
	static String msg_lightoff = "'Light Off' executed";
	static String msg_invalidCommand = "Invalid Command"; 
	static String msg_invalidRESTCONKEY = "Invalid RESTCONKEY";

	static void putMain(HttpServletRequest req, HttpServletResponse resp, String[] command, HashMap<String, String> param) throws IOException {
		PrintWriter out = resp.getWriter();
		HashMap<String, String> resultMap = new HashMap<>();
		/*
		 * controller for remoteLight
		 */
		//It requires authorized account to control something
		if(!CommonRESTUtil.checkValidAccount(param.get("email"), param.get("pw"))) {
			//failed to get authority
			
			String message = "Not a valid account";
			CommonRESTUtil.initResultMap(resultMap, "false", null, message);
			out.println(CommonRESTUtil.mapJSON(resultMap));
			return;
		}
		
		try {
		String device_id = command[3];
		MethodResult mr;
		switch(param.get("command")) {
		case "lighton":
			mr = BMS_Container.getRemoteLightManager().lightOn(device_id);
			if(mr == null) {
				CommonRESTUtil.initResultMap(resultMap, "false", null, null);
			}
			else if (mr.getStatus() == 200) {
				CommonRESTUtil.initResultMap(resultMap, "true", String.valueOf(200), msg_lighton);
			}
			else {
				
				CommonRESTUtil.initResultMap(resultMap, "false", null, null);
			}
			break;
		case "lightoff":
			mr = BMS_Container.getRemoteLightManager().lightOff(device_id);
			if(mr == null) {
				CommonRESTUtil.initResultMap(resultMap, "false", null, null);
			}
			else if (mr.getStatus() == 200) {
				CommonRESTUtil.initResultMap(resultMap, "true", String.valueOf(200), msg_lightoff);
			}
			else {
				CommonRESTUtil.initResultMap(resultMap, "false", null, null);
			}
			break;
		case "update":
			if(param.get("RESTCONKEY").equals(RemoteLight.RESTCONKEY)) {
				int lightOnOff = Integer.parseInt(param.get("light"));
				BMS_Container.getRemoteLightManager().update(device_id, lightOnOff);
				CommonRESTUtil.initResultMap(resultMap, "true", String.valueOf(200), null);
				break;
			}
			else {
				CommonRESTUtil.initResultMap(resultMap, "false", null, msg_invalidRESTCONKEY);
			}
			break;
		default:
			CommonRESTUtil.initResultMap(resultMap, "false", null, msg_invalidCommand);
			break;
		}
		
		out.println(CommonRESTUtil.mapJSON(resultMap));
		}
		catch(Exception e) {
			CommonRESTUtil.initResultMap(resultMap, "false", null, e.getMessage());
		}
	}
	
	static void getMain(HttpServletRequest req, HttpServletResponse resp, String[] command) throws IOException {
		PrintWriter out = resp.getWriter();
		
		//command[3] : id
		String device_id = CommonRESTUtil.expect(command, 3);
		
		if(device_id == null) {
			for (RemoteLight rl : BMS_Container.getRemoteLightManager().getResource()) {
				out.println(rl.toJSON());
			}
		}
		else {
			RemoteLight rl = BMS_Container.getRemoteLightManager().findByDeviceID(device_id);
			out.println(rl.toJSON());
		}
		
		
		
		
	}
	
	

	
}
