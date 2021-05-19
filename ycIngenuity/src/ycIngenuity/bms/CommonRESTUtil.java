package ycIngenuity.bms;

import java.util.HashMap;
import java.util.Map;

import ycIngenuity.bms.resourceUtil.BMS_Container;

public class CommonRESTUtil {
	
	static Boolean checkValidAccount(String email, String pw) {
		return BMS_Container.getAccountRsourceManager().checkIDPW(email, pw);
	}
	
	static void initResultMap(HashMap<String, String> resultMap, String success, String status, String message){
		//each component indicates json property
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
	
	static String expect(String[] command, int index) {
		return command.length>index?command[index]:null;
	}

}
