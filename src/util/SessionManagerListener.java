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
		SessionManager.getInstance().remove(paramSessionEvent.getSession());
		
		// login_time 테이블에 로그아웃 시간을 저장한다.
		new MembershipServiceImpl().logoutCount(userId); 
		
		// 현재 로그인 중인 사용자 수를 관리자 페이지에 푸쉬한다.
		AdminServer.getInstance().pushLoginMemberCount(SessionManager.getInstance().count());
		
		// 현재 로그인 중인 사용자 리스트를 관리자 페이지에 푸쉬한다.
		AdminServer.getInstance().refreshLogoutMember(userId);
	}

}
