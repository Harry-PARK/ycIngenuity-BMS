package ycIngenuity.bms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/restful/*")
public class RestHandler extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static String invalidCommand = "Invalid Command"; 

/*
 *
	get : read - read log, info
	post : create - make log, heartbeat
	put : update
		/restful
		
		/remotelight
		[ 
		lighton?deviceid=id,
		lightoff?deviceid=id
		]
	delete : delete
	  
	 */


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

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<p>test success</p>");
		out.println("<p>"+request.getRequestURL()+"</p>");
		out.println("<p>"+request.getParameter("id")+"+</p>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<p>test post success</p>");
		out.println("<p>"+request.getRequestURL()+"</p>");
		out.println("<p>"+request.getParameter("id")+"+</p>");
	}
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * [2]unit 		: ex) remotelight
		 * [3]id 		: id
		 */
		response.setContentType("text/html; charset=utf-8");

		HashMap<String, String> param = getPutParameter(request.getInputStream());
		String[] command = request.getRequestURI().substring(1).split("/"); //remove empty command
		
		switch(command[2]) {
		case "remotelight":
			RemoteLightREST.putMain(request, response, command, param);
			break;
		default :
			PrintWriter out = response.getWriter();
			HashMap<String, String> resultMap = new HashMap<>();
			CommonREST.initResultMap(resultMap, "false", null, invalidCommand);
			out.println(CommonREST.mapJSON(resultMap));
			break;
		}
		
		
	}

	
	private HashMap<String, String> getPutParameter(InputStream in) throws IOException{
		HashMap<String, String> param = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String data = br.readLine();
		data = URLDecoder.decode(data, "UTF-8");
		String[] tokens = data.split("&");
		for (String token : tokens) {
			String[] kv = token.split("=");
			param.put(kv[0], kv[1]);
		}
		return param;
	}
	



}