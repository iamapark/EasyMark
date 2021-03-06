package util;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

public class SessionManager {
	private static SessionManager sessionManager;
	private Hashtable<String, HttpSession> session;
	
	private SessionManager(){
		session = new Hashtable<String, HttpSession>();
	}
	
	public static SessionManager getInstance(){
		if(sessionManager == null){
			synchronized(SessionManager.class){
				if(sessionManager == null)
					sessionManager = new SessionManager();
			}
		}
		
		return sessionManager;
	}
	
	public void add(HttpSession httpSession){
		session.put(httpSession.getId(), httpSession);
	}
	
	public void remove(HttpSession httpSession){
		System.out.println("session remove");
		session.remove(httpSession.getId());
	}
	
	public HttpSession get(String id){
		return session.get(id);
	}
	
	public ArrayList<String> getLoginId(){
		ArrayList<String> ar = new ArrayList<String>();
		
		System.out.println("getLoginId");
		
		for(String key:session.keySet()){
			ar.add((String)session.get(key).getAttribute("MEMBERID"));
		}
		
		return ar;
	}
	
	public int count(){
		int count = 0;
		
		for(String key:session.keySet()){
			if(session.get(key) == null)
				continue;
			
			try{
				if((System.currentTimeMillis() - session.get(key).getLastAccessedTime()) / 1000 > 300){
					continue;
				}
			}catch(IllegalStateException e){
				continue;
			}
			count++;
		}
		
		return count;
	}
	
	public int totalCount(){
		return session.size();
	}
}
