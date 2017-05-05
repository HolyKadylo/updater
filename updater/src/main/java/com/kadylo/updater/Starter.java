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
			filecryptersToMaintain = new Scanner(new File("res/FilecryptersToMaintain.txt")).useDelimiter("\\Z").next();
			filecryptersToMaintain = filecryptersToMaintain.replaceAll(System.getProperty("line.separator") ," ");
		} catch (Exception e){
			System.out.println( "Missing FilecryptersToMaintain.txt" );
			System.exit(-1);
		}
		Scanner scanner = new Scanner(filecryptersToMaintain);
		scanner.useDelimiter(" ");
		while (scanner.hasNext()){
			System.out.println(scanner.next());
		}
	}
}