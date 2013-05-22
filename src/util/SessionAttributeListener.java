package util;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import service.MembershipServiceImpl;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent sessionBindingEvent) {
		
		if(sessionBindingEvent.getName().equals("MEMBERID")){
			String userId = (String)sessionBindingEvent.getSession().getAttribute("MEMBERID");
			new MembershipServiceImpl().loginCount(userId); // 로그인 카운트를 1 증가시킨다.
			
			System.out.println("생성 아이디: " + sessionBindingEvent.getSession().getAttribute("MEMBERID"));
			System.out.println("세션생성: " + sessionBindingEvent.getSession().getId());
			SessionManager.getInstance().add(sessionBindingEvent.getSession());
			System.out.println("세션 count: " + SessionManager.getInstance().count());
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
