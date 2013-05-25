package util;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import dto.Member;

import service.AdminServiceImpl;
import service.MembershipServiceImpl;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent sessionBindingEvent) {
		
		if(sessionBindingEvent.getName().equals("MEMBERID")){
			String userId = (String)sessionBindingEvent.getSession().getAttribute("MEMBERID");
			System.out.println("로그인: " + userId);
			new MembershipServiceImpl().loginCount(userId); // 로그인 카운트를 1 증가시킨다.
			
			SessionManager.getInstance().add(sessionBindingEvent.getSession());
			
			AdminServer.getInstance().pushLoginMemberCount(SessionManager.getInstance().count());
			
			AdminServer.getInstance().pushLoginMemberInfo(userId);
			
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sessionBindingEvent) {
		
			
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

}
