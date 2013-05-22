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
		System.out.println("세션 count: " + SessionManager.getInstance().count());
		System.out.println("제거 아이디: " + paramSessionEvent.getSession().getAttribute("MEMBERID"));
		System.out.println("세션제거: " + paramSessionEvent.getSession().getId());
		SessionManager.getInstance().remove(paramSessionEvent.getSession());
		System.out.println("세션 count: " + SessionManager.getInstance().count());
		
		new MembershipServiceImpl().logoutCount(userId); // 로그인 카운트를 1 증가시킨다.
	}

}
