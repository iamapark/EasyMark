package dto;

public class FriendStatus {
	private String userId;
	private String friendId;
	private String name;
	private String email;
	private String status;
	public FriendStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FriendStatus(String userId, String friendId, String name, String email, String status) {
		super();
		this.userId = userId;
		this.friendId = friendId;
		this.name = name;
		this.email = email;
		this.status = status;
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
	public String getName() {
		return name;
	}
	public void setFirstName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
