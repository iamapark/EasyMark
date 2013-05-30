package dto;

import java.util.Date;

public class Message {
	private int messageId;
	private String userId;
	private String friendId;
	private String title;
	private String contents;
	private Date messageDate;
	private String messageDate2;
	
	public Message() {
		super();
	}
	public Message(int messageId, String userId, String friendId, String title,
			String contents, Date messageDate, String messageDate2) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.friendId = friendId;
		this.title = title;
		this.contents = contents;
		this.messageDate = messageDate;
		this.messageDate2 = messageDate2;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public String getMessageDate2() {
		return messageDate2;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public void setMessageDate2(String messageDate2) {
		this.messageDate2 = messageDate2;
	}
}
