package com.kadylo.updater;

import java.io.*; 
import java.util.*;
import java.net.*;

// this class is used to poll filecrypter folders
// specified in FilecryptersToMaintain.txt
public class FilecrypterPoller implements Runnable{
	private static ArrayList <String> FilecryptersToMaintain;
	
	// a period on which poller sends requests to filecrypt API
	private static int period;
	private static String APIKey;
	
	//https://filecrypt.cc/api.php?api_key=&fn=containerV2&sub=info&container_id=3BAB732755
	FilecrypterPoller(ArrayList <String> FilecryptersToMaintain, int period, String APIKey){
		this.FilecryptersToMaintain = FilecryptersToMaintain;
		this.period = period;
		this.APIKey = APIKey;
		System.out.println("-->Created filecrypter poller " + APIKey + "with period " + period);
	}
	
	public void run() {
        System.out.println("-->Filecrypter poller started");
		while(true){
			//https://filecrypt.cc/api.php?api_key=74964b5f1a05312c84debe0a5701e45c118677ec&fn=containerV2&sub=status&container_id=998DCE6859
			//http://filecrypt.cc/Container/3BAB732755.html
			for (String filecrypter : FilecryptersToMaintain){
				try{
					Thread.currentThread().sleep(period);
					
					poll(buildStatus(filecrypter));
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
    }
	
	// builds status query to API from filecrypter address3
	private String buildStatus (String filecrypter){
		try{
			return "https://filecrypt.cc/api.php?api_key=" 
				+ APIKey 
				+ "&fn=containerV2&sub=status&container_id=" 
				+ (new URL (filecrypter)).getFile().substring(11, (new URL (filecrypter)).getFile().indexOf("."));
		} catch (MalformedURLException e){
			System.out.println("-->" + e.toString());
			return null;
		}
	}
	
	// builds info query to API from filecrypter address3
	private String buildInfo (String filecrypter){
		try{
			return "https://filecrypt.cc/api.php?api_key=" 
				+ APIKey 
				+ "&fn=containerV2&sub=info&container_id=" 
				+ (new URL (filecrypter)).getFile().substring(11, (new URL (filecrypter)).getFile().indexOf("."));
		} catch (MalformedURLException e){
			System.out.println("-->" + e.toString());
			return null;
		}
	}
	
	// simple requester
	private String getRequest(String address){
		String answer = null;
		try{
			URLConnection urlConnection = (new URL (address)).openConnection();
			urlConnection.connect();
			InputStream is = urlConnection.getInputStream();
			answer = org.apache.commons.io.IOUtils.toString(is, "UTF-8");
			is.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return answer;
	}
	
	// returns list of names of offline entities
	// if none, returns null
	private ArrayList<String> poll(String address){
		try{
			System.out.println("-->polling " + address);
			// obtaining answer
			
			
			// parsing
			System.out.println("-->answer: " + answer);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
}