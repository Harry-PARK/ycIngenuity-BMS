package ycIngenuity.bms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import ycIngenuity.bms.resourceUtil.BMS_Container;

public class RemoteLightREST {
	
	static String lightonMessage = "'Light On' executed";
	static String lightoffMessage = "'Light Off' executed";
	static String invalidCommand = "Invalid Command"; 

	static void putMain(HttpServletRequest request, HttpServletResponse response, String[] command, HashMap<String, String> param) throws IOException {
		PrintWriter out = response.getWriter();
		HashMap<String, String> resultMap = new HashMap<>();
		/*
		 * controller for remoteLight
		 */
		//It requires authorized account to control something
		if(!CommonREST.checkValidAccount(param.get("email"), param.get("pw"))) {
			//failed to get authority
			
			String message = "Not a valid account";
			CommonREST.initResultMap(resultMap, "false", null, message);
			out.println(CommonREST.mapJSON(resultMap));
			return;
		}
		
		String device_id = command[3];
		MethodResult mr;
		switch(param.get("command")) {
		case "lighton":
			mr = BMS_Container.getRemoteLightManager().lightOn(device_id);
			if(mr == null) {
				CommonREST.initResultMap(resultMap, "false", null, null);
			}
			else if (mr.getStatus() == 200) {
				CommonREST.initResultMap(resultMap, "true", String.valueOf(200), lightonMessage);
			}
			else {
				
				CommonREST.initResultMap(resultMap, "false", null, null);
			}
			break;
		case "lightoff":
			mr = BMS_Container.getRemoteLightManager().lightOff(device_id);
			if(mr == null) {
				CommonREST.initResultMap(resultMap, "false", null, null);
			}
			else if (mr.getStatus() == 200) {
				CommonREST.initResultMap(resultMap, "true", String.valueOf(200), lightoffMessage);
			}
			else {
				CommonREST.initResultMap(resultMap, "false", null, null);
			}
			break;
		default:
			CommonREST.initResultMap(resultMap, "false", null, invalidCommand);
			break;
		}
		
		out.println(CommonREST.mapJSON(resultMap));
		
	}
	
	
	
}
