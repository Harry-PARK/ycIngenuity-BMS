package ycIngenuity.bms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycIngenuity.bms.resourceUtil.BMS_Container;
import ycIngenuity.bms.resourceUtil.LogOne;

public class LogREST {
	static void getMain(HttpServletRequest req, HttpServletResponse resp, String[] command) throws IOException {
		PrintWriter out = resp.getWriter();
		
		//command[3] : required amount
		String amount = CommonRESTUtil.expect(command, 3);
		List<LogOne> log_archive = BMS_Container.getLogOneResoucrManager().getLogArchive();
		if(amount == null) {
			for(int i = log_archive.size()-1; i >= 0; i-- ) {
				out.println(log_archive.get(i));
			}
		}
		else {
			int amountInt = Integer.parseInt(amount);
			amountInt = (amountInt < 0) ? 0 : amountInt;
			for(int i = log_archive.size()-1; i >= log_archive.size()-amountInt; i-- ) {
				out.println(log_archive.get(i));
			}
		}
	}
	
}
