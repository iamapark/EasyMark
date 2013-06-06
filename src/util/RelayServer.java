package util;

import java.util.ArrayList;

import dto.Member;

public class RelayServer {
	private AdminServer adminServer = null;
	private MessageServer msgServer = null;
	public static RelayServer relayServer = null;
	
	public static RelayServer getInstance(){
		if(relayServer == null)
			relayServer = new RelayServer();
		return relayServer;
	}
	
	private RelayServer(){
		adminServer = AdminServer.getInstance();
		msgServer = MessageServer.getInstance();
	}
	
	public void adminServerStart(){
		adminServer.start();
	}
	
	public void msgServerStart(){
		msgServer.start();
	}
	
	public MessageServer getMessageServer(){
		return msgServer;
	}
	
	public AdminServer getAdminServer(){
		return adminServer;
	}
	
	/**
	 * 사용자가 소켓에 등록되어 있는지 판별하는 메소드
	 * @param userId 사용자 아이디
	 * @return 등록되어 있으면 true, 아니면 false
	 * */
	public boolean isContains(String userId){
		return msgServer.isContains(userId);
	}
	
	/**
	 * 현재 로그인 중인 사용자 수를 관리자 페이지로 푸쉬하는 메소드
	 * */
	public int pushLoginMemberCount(){
		return msgServer.getLoginMemberCount();
	}

	public void sendMessage(String friendId, String message, int count) {
		msgServer.sendMessage(friendId, message, count);
	}

	public String[] getLoginMemberList() {
		System.out.println("relayServer: getLoginMemberList");
		String loginMemberIdArray[] = new String[msgServer.getLoginMemberCount()];
		msgServer.getLoginMemberList();
		return loginMemberIdArray;
	}
}
