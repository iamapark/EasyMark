package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.impl.Namespace;

public class MessageServer {
	private Vertx vt;
	private SocketIOServer io;
	private HashMap<String, SocketIOSocket> sockets; // a(사용자), socket 생기는 것 담기
	private static MessageServer server = null;
	
	public static MessageServer getInstance(){
		if(server == null)
			server = new MessageServer();
		return server;
	}
	
	private MessageServer(){
		this.vt = Vertx.newVertx();
		sockets = new HashMap<String, SocketIOSocket>();
		System.out.println("MessageServer Constructor!!");
	}
	
	public void start(){
		System.out.println("message 서버 시작!!!");
		HttpServer server = vt.createHttpServer();
		io = new DefaultSocketIOServer(vt, server);
		final Namespace message = io.of("/message");
		
		
		
		io.sockets().onConnection(new Handler<SocketIOSocket>(){
			@Override
			public void handle(final SocketIOSocket socket) {
				socket.on("userId", new Handler<JsonObject>(){ // userId 값이 넘어온다
					@Override
					public void handle(JsonObject data) {
						System.out.println(data);
						System.out.println("id는"+data.getString("id"));
						register(data.getString("id"), socket);
					}
				});
				
				socket.on("send", new Handler<JsonObject>(){
					public void handle(JsonObject data){
						System.out.println("socket.on?");
						System.out.println("message"+data.getString("message"));
						System.out.println("friendId:"+data.getString("friendId"));
						
						//sendMessage(data.getString("friendId"), data.getString("message"), data.getInteger("num"));

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
		
		server.listen(15002);
	}
	
	private void register(String id, SocketIOSocket socket){

		sockets.put(id,  socket);

		/*adminServer.pushLoginMemberInfo(id);*/
		//adminServer.pushLoginMemberCount(getLoginMemberCount());
		System.out.println("(등록)id:" + id);
		System.out.println("접속 회원 수: " + sockets.size());
	}
	
	public void sendMessage(String id, String friendId, String msg, int num){
		System.out.println("전송받는 아이디: " + id + ", 메시지 내용: " + msg);
		JsonObject data = new JsonObject();
	
		data.putString("msg", msg);
		data.putString("friend", friendId);
		data.putNumber("num", num);
		
		if(sockets.get(id) != null){
			System.out.println("보냈습니다.");
			sockets.get(id).emit("message", data);
		}
		
	}

	public boolean isContains(String userId) {
		return sockets.containsKey(userId);
	}
	
	public void removeMember(String userId){
		sockets.remove(userId);
	}

	public int getLoginMemberCount() {
		return sockets.size();
	}

	public String[] getLoginMemberList() {
		System.out.println("접속 중인 회원 수: " + sockets.size());
		String[] loginMemberIdArray = new String[sockets.size()];
		int count = 0;
		Set<String> set = sockets.keySet();
		Iterator<String> iter = set.iterator();

		while(iter.hasNext()){
			loginMemberIdArray[count++] = iter.next();
		}
		
		return loginMemberIdArray;
	}
	
}
