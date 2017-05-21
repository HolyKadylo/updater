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
		
		Properties properties = new Properties();
		
		
		String filecryptersToMaintain = null;
		try{
			InputStream is = getClass().getClassLoader().getResourceAsStream("FilecryptersToMaintain.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			filecryptersToMaintain = org.apache.commons.io.IOUtils.toString(br);
			
			InputStream in = getClass().getClassLoader().getResourceAsStream("properties.PROPERTIES");
			properties.load(in);
			in.close();
			//System.out.println();
		} catch (Exception e){
			System.out.println( "Missing FilecryptersToMaintain.txt" );
			System.exit(-1);
		}
		filecryptersToMaintain = filecryptersToMaintain.replaceAll(System.getProperty("line.separator") ," ");
		Scanner scanner = new Scanner(filecryptersToMaintain);
		scanner.useDelimiter(" ");
		ArrayList <String> filecryptersList = new ArrayList<String>();
		while (scanner.hasNext()){
			filecryptersList.add(scanner.next());
		}
		FilecrypterPoller poller = new FilecrypterPoller (filecryptersList, 5000, properties.getProperty("FilecrypterAPIKey"));
		//poller.start();
		(new Thread(poller)).start();
	}
}