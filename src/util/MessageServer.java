package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import service.FriendshipServiceImpl;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.impl.Namespace;

import dto.Message;

public class MessageServer {
	private Vertx vt;
	private SocketIOServer io;
	private HashMap<String, SocketIOSocket> sockets; // a(사용자), socket 생기는 것 담기
	private static MessageServer server = null;
	private AdminServer adminServer = null;
	
	public static MessageServer getInstance(){
		if(server == null)
			server = new MessageServer();
		return server;
	}
	
	private MessageServer(){
		this.vt = Vertx.newVertx();
		sockets = new HashMap<String, SocketIOSocket>();
		adminServer = AdminServer.getInstance();
		System.out.println("MessageServer Constructor!!");
	}
	
	public void start(){
		HttpServer server = vt.createHttpServer();
		io = new DefaultSocketIOServer(vt, server);
		final Namespace message = io.of("/message");
		
		message.onConnection(new Handler<SocketIOSocket>(){
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
						
						sendMessage(data.getString("friendId"), data.getString("message"), data.getInteger("num"));
						/*for(int i=0; i<messageFriend.length; i++){
							
							messageFriend[i] = messageFriend[i].trim();	
							sendMessage(messageFriend[i], data.getString("message"), data.getInteger("num"));
						}*/
					}
				});
				
				socket.on("exit", new Handler<JsonObject>(){
					@Override
					public void handle(JsonObject data) {
						System.out.println(data.getString("id"));
						sockets.remove(data.getString("id"));
						System.out.println("exit");
						System.out.println("size: " + sockets.size());
						adminServer.pushLoginMemberCount(getLoginMemberCount());
						adminServer.refreshLogoutMember(data.getString("id"));
					}
				});
				
				socket.onDisconnect(new Handler<JsonObject>() {
					public void handle(JsonObject event) {
						System.out.println("disconnect");
					}
				});
			}
				
		});
		
		server.listen(9090);
	}
	
	private void register(String id, SocketIOSocket socket){

		sockets.put(id,  socket);

		/*adminServer.pushLoginMemberInfo(id);*/
		//adminServer.pushLoginMemberCount(getLoginMemberCount());
		System.out.println("(등록)id:" + id);
		System.out.println("접속 회원 수: " + sockets.size());
		System.out.println("등록된 socket: " + socket);
	}
	
	public void sendMessage(String id, String msg, int num){
		System.out.println("(전송)id: " + id + ", msg: " + msg);
		JsonObject data = new JsonObject();
	
		ArrayList<Message> newMessage = new ArrayList<Message>();
		Message message = new Message(0, id, "", null, "", new Date(), "", 0, "take");
		newMessage = new FriendshipServiceImpl().messageCount(message);
		
		data.putString("msg", msg);
		data.putString("friend", id);
		data.putNumber("num", newMessage.size());
		
		if(sockets.get(id) != null){
			sockets.get(id).emit("message", data);
		} else { }
		
	}

	public boolean isContains(String userId) {
		return sockets.containsKey(userId);
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
