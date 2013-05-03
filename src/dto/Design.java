package dto;

public class Design {
	private String userId;
	private String type;
	
	public Design() {
		super();
	}

	public Design(String userId, String type) {
		super();
		this.userId = userId;
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
