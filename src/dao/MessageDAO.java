package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Friendship;
import dto.Member;
import dto.Message;

public class MessageDAO {
	private static MessageDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static MessageDAO getInstance(){
		if(instance == null)
			instance = new MessageDAO();
		return instance;
	}

	private MessageDAO(){
		sqlMapper = DAOParser.getParser();
	}
	
	public ArrayList<Message> outBox(Message message){
		ArrayList<Message> outBoxList = null;
		try {
			outBoxList = (ArrayList<Message>) sqlMapper.queryForList("outBox", message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outBoxList;
	}
	
	public ArrayList<Message> inBox(Message message){
		ArrayList<Message> inBoxList = null;
		try {
			inBoxList = (ArrayList<Message>) sqlMapper.queryForList("inBox", message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inBoxList;
	}

	public Message getMessage(String contents){
		Message message = new Message();
		try {
			message = (Message) sqlMapper.queryForObject("getMessage", contents);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
		
	public boolean sendMessage(Message message) {
		boolean flag = false;
		try {
			sqlMapper.insert("sendMessage", message);
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public void deleteMessage(String messageId){
		try {
			sqlMapper.delete("deleteMessage", messageId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMessage(Message message){
		try {
			sqlMapper.update("updateMessage", message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Message> newMessageCount(Message message){
		ArrayList<Message> newMessage = new ArrayList<Message>();
		try {
			newMessage = (ArrayList<Message>) sqlMapper.queryForList("newMessageNum", message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newMessage; 
	}
}
