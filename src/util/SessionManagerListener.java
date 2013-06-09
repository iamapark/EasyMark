package util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import service.MembershipServiceImpl;

public class SessionManagerListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent paramSessionEvent) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent paramSessionEvent) {
		String userId = (String)paramSessionEvent.getSession().getAttribute("MEMBERID");
		System.out.println("로그아웃: " + userId);
		SessionManager.getInstance().remove(paramSessionEvent.getSession());
		
		new MembershipServiceImpl().logoutCount(userId); // 로그인 카운트를 1 증가시킨다.
		
		AdminServer.getInstance().pushLoginMemberCount(SessionManager.getInstance().count());
		
		AdminServer.getInstance().refreshLogoutMember(userId);
	}

}
