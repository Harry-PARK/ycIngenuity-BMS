package ycIngenuity.bms.resourceUtil;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;

public class RestHandler {
/*
 *RestHandler classifies the jobs by commandHandler(method)
 *it hands over to appropriate <Handler>
 *	
 *For example if the jobs is about 'control' units
 *'controlHandler'.class will get the jobs
 *
 *All Handler share 'command' and 'resultMap'.
 *[command]
 *[0]: "restful"
 *[1]: job
 *[2]: -extra
 *
 *<resultMap>
 *success : true
 *status : 200
 *message : option
 *cacheable : non-cacheable
 *
 */

	
	static String[] command = null;
	static HashMap<String, String> param = null;
	static HashMap<String, String> resultMap;
	
	
	public static String commandHandler(String[] command, HashMap<String, String> param) {
		/*
		 * restful
		 * /control
		 * /remotelight/lighton
		 * 
		 */
		String result = null;
		RestHandler.command = command;
		RestHandler.param = param;
		
		if(!command[0].equals("restful")) {
			return "Invalid RestAPI Request";
		}
		switch(command[1]) {
		case "control":
			controlHandler.commandHandler();
			break;
		}
		return result;
	}
	
	static void initResultMap(String success, String status, String message){
		//each component indicates json property
		resultMap = new HashMap<String, String>();
		resultMap.put("success", success);
		resultMap.put("status", status);
		resultMap.put("message", message);
		resultMap.put("cacheable", "non-cacheable");
	}
	
	static String mapJSON(HashMap<String, String> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		for(Map.Entry<String, String> set : map.entrySet()) {
			json.append(set.getKey());
			json.append(":");
			json.append(set.getValue());
			json.append(",");
		}
		json.append("}");
		return json.toString();
	}
	
	static class controlHandler{
	/*
	 * controlHandler helps to control units
	 * First, it checks the validity of account.
	 * check the target and call -controller method.
	 * 
	 * [command]
	 * [2]: unit 
	 * examples: remoteLight -> remoteLightController
	 * 
	 * 
	 * 
	 */
		static void commandHandler() {
			//It requires authorized account to control something
			if(!checkValidAccount()) {
				//failed to get authority
				String message = "Not a valid account";
				initResultMap("false", null, message);
				return;
			}
			switch(command[2]) {
			case "remotelight":
				remoteLgihtController();
				break;
			}
		}
		
		static Boolean checkValidAccount() {
			if(param.containsKey("email") && param.containsKey("pw")) {
				//return true for valid, or false for invalid
				return BMS_Container
						.getAccountRsourceManager()
						.checkIDPW(param.get("email"), param.get("pw"));
			}
			else {
				return false;
			}
		}
		
		static void remoteLgihtController() {
			/*
			 * controller for remoteLight
			 */
			
			String lightonString = "'Light On' executed";
			String lightoffString = "'Light Off' executed";
			MethodResult mr;
			switch(command[3]) {
			case "lighton":
				mr = BMS_Container.getRemoteLightManager().lightOn(param.get("device_id"));
				if(mr == null) {
					initResultMap("false", null, null);
				}
				else if (mr.getStatus() == 200) {
					initResultMap("true", String.valueOf(200), lightonString);
				}
				else {
					
					initResultMap("false", null, null);
				}
				break;
			case "lightoff":
				mr = BMS_Container.getRemoteLightManager().lightOff(param.get("device_id"));
				if(mr == null) {
					initResultMap("false", null, null);
				}
				else if (mr.getStatus() == 200) {
					initResultMap("true", String.valueOf(200), lightoffString);
				}
				else {
					initResultMap("false", null, null);
				}
				break;
			}
		}
	}

}