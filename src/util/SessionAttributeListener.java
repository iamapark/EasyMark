package util;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import service.MembershipServiceImpl;
import dto.Member;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent sessionBindingEvent) {
		
		if(sessionBindingEvent.getName().equals("MEMBERID")){
			String userId = (String)sessionBindingEvent.getSession().getAttribute("MEMBERID");
			SessionManager.getInstance().add(sessionBindingEvent.getSession());
			
			// login_time 테이블에 로그인 시간을 저장한다.
			new MembershipServiceImpl().loginCount(userId); 
			
			// 현재 로그인 중인 사용자 수를 관리자 페이지에 푸쉬한다.
			AdminServer.getInstance().pushLoginMemberCount(SessionManager.getInstance().count());
			
			// 로그인 한 사용자 정보를 관리자 페이지에 푸쉬한다.
			Member m = new MembershipServiceImpl().getMemberInfo(userId);
			AdminServer.getInstance().pushLoginMemberInfo(m);
			
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sessionBindingEvent) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}

}
