package util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionManagerListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent paramSessionEvent) {
		System.out.println("세션생성: " + paramSessionEvent.getSession().getId());
		SessionManager.getInstance().add(paramSessionEvent.getSession());
		System.out.println("세션 count: " + SessionManager.getInstance().count());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent paramSessionEvent) {
		System.out.println("세션제거: " + paramSessionEvent.getSession().getId());
		SessionManager.getInstance().remove(paramSessionEvent.getSession());
		System.out.println("세션 count: " + SessionManager.getInstance().count());
	}

}
