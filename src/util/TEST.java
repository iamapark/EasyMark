package util;
 import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.deploy.Verticle;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.impl.Namespace;

    /**
     * @author Keesun Baik
     */
    public class TEST extends Verticle {
    	private Vertx vt;
    	
    	public static void main(String args[]) throws Exception{
    		MessageServer.getInstance().start();
    		//new TEST().start();
    		Thread.sleep(Long.MAX_VALUE);
    	}
    	
    	public TEST(){
    		this.vt = Vertx.newVertx();
    	}

        @Override
        public void start() throws Exception {
        	System.out.println("message 서버 시작!!!");
    		HttpServer server = vt.createHttpServer();
    		SocketIOServer io = new DefaultSocketIOServer(vt, server);
    		final Namespace message = io.of("/message");
    		
    		
    		
    		io.sockets().onConnection(new Handler<SocketIOSocket>(){
    			@Override
    			public void handle(final SocketIOSocket socket) {
    				socket.on("userId", new Handler<JsonObject>(){ // userId 값이 넘어온다
    					@Override
    					public void handle(JsonObject data) {
    						System.out.println(data);
    						System.out.println("id는"+data.getString("id"));
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
    						System.out.println("exit");
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
}