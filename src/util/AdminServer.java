package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.sf.json.JSONObject;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import service.MembershipServiceImpl;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.impl.Namespace;

import dto.Member;

public class AdminServer {
	private Vertx vt;
	private SocketIOServer io;
	private HashMap<String, SocketIOSocket> sockets;
	private static AdminServer server = null;
	
	public static AdminServer getInstance(){
		if(server == null)
			server = new AdminServer();
		return server;
	}
	
	private AdminServer(){
		this.vt = Vertx.newVertx();
		sockets = new HashMap<String, SocketIOSocket>();
		System.out.println("AdminServer Constructor!!");
	}
	
	public void start(){
		HttpServer server = vt.createHttpServer();
		io = new DefaultSocketIOServer(vt, server);
		final Namespace message = io.of("/admin");
		
		message.onConnection(new Handler<SocketIOSocket>(){
			@Override
			public void handle(final SocketIOSocket socket) {
				socket.on("adminId", new Handler<JsonObject>(){
					@Override
					public void handle(JsonObject data) {
						register(data.getString("id"), socket);
					}
				});
				
				socket.on("exit", new Handler<JsonObject>(){
					@Override
					public void handle(JsonObject data) {
						System.out.println(data.getString("id"));
						sockets.remove(data.getString("id"));
						System.out.println("exit");
						System.out.println("size: " + sockets.size());
					}
				});
				
				socket.onDisconnect(new Handler<JsonObject>() {
					public void handle(JsonObject event) {
						System.out.println("disconnect");
					}
				});
			}
		});
		
		server.listen(9091);
	}
	
	private void register(String id, SocketIOSocket socket){
		if(isContains(id))
			System.out.println("이미 등록된 아이디입니다.");
		sockets.put(id, socket);
		System.out.println("(등록)adminId:" + id + ", 크기: " + sockets.size());
	}

	private boolean isContains(String adminId) {
		return sockets.containsKey(adminId);
	}
	
	/**
	 * 사용자가 로그인 할때마다 현재 로그인 중인 사용자 수를 Admin 페이지에 푸쉬한다.*/
	public void pushLoginMemberCount(int count){
		System.out.println("pushLoginMemberCount");
		System.out.println("size: " + sockets.size());
		JsonObject data = new JsonObject();
		data.putNumber("count", count);
		
		push("loginMemberCount", data);
	}
	
	/**
	 * 사용자가 로그인 할때마다 사용자 계정에 관한 정보를 Admin 페이지에 푸쉬한다.*/
	public void pushLoginMemberInfo(String userId){
		System.out.println("pushLoginMemberInfo");
		Member member = new MembershipServiceImpl().getMemberInfo(userId);
		JsonObject data = new JsonObject();
		data.putString("userId", member.getUserId());
		data.putString("name", member.getName());
		data.putString("email", member.getEmail());
		data.putString("bgImgUrl", member.getBgImgUrl());
		data.putString("imgUrl", member.getImgUrl());
		
		push("loginMemberInfo", data);
	}

	/**사용자가 로그아웃 할 때마다 관리자 페이지로 아이디를 push 한다.*/
	public void refreshLogoutMember(String id) {
		JsonObject data = new JsonObject();
		data.putString("userId", id);
		
		push("refreshLogoutMember", data);
	}

	
	public void pushRegisterMemberCount(JSONObject dataJ){
		System.out.println("pushRegisterMemberCount");
		System.out.println(dataJ);

		JsonObject data = new JsonObject();
		data.putString("memberCount", Integer.toString((Integer)dataJ.get("memberCount")));
		data.putString("todayRegisterCount", Integer.toString((Integer)dataJ.get("todayRegisterCount")));
		push("pushRegisterMemberCount", data);
	}
	
	public void trafficCount(){
		JsonObject data = new JsonObject();
		data.putNumber("traffic", 1);
		push("trafficCount", data);
	}
	
	private void push(String eventHandler, JsonObject data){
		Set<String> set = sockets.keySet();
		Iterator<String> iter = set.iterator();
		
		while(iter.hasNext()){
			sockets.get(iter.next()).emit(eventHandler, data);
		}
	}

}
