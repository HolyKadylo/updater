package com.kadylo.updater;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.util.*;

public class Starter extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.println("Doing Starter GET");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("You've accessed Starter servlet");
		
		String filecryptersToMaintain = null;
		try{
			InputStream is = getClass().getClassLoader().getResourceAsStream("FilecryptersToMaintain.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			filecryptersToMaintain = org.apache.commons.io.IOUtils.toString(br);
		} catch (Exception e){
			System.out.println( "Missing FilecryptersToMaintain.txt" );
			System.exit(-1);
		}
		filecryptersToMaintain = filecryptersToMaintain.replaceAll(System.getProperty("line.separator") ," ");
		Scanner scanner = new Scanner(filecryptersToMaintain);
		scanner.useDelimiter(" ");
		while (scanner.hasNext()){
			System.out.println(scanner.next());
		}
	}
}