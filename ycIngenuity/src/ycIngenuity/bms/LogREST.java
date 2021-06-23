package ycIngenuity.bms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycIngenuity.bms.resourceUtil.BMS_Container;
import ycIngenuity.bms.resourceUtil.LogOne;

public class LogREST {
	static void getMain(HttpServletRequest req, HttpServletResponse resp, String[] command) throws IOException {
		PrintWriter out = resp.getWriter();
		
		//command[3] : required amount
		String amount = CommonRESTUtil.expect(command, 3);
		if(amount == null) {
			for(LogOne log : BMS_Container.getLogOneResoucrManager().getLogArchive()) {
				out.println(log);
			}
		}
		else {
			int amountInt = Integer.parseInt(amount);
			int i = 0;
			for(LogOne log : BMS_Container.getLogOneResoucrManager().getLogArchive()) {
				if(i++ < amountInt)out.println(log);
				else break;
			}
		}
	}
	
}
