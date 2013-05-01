package dto;

public class Member {
	String userId = null;
	String email = null;
	String password = null;
	String name = null;
	String me2DayKey = null;
	
	public Member() {
		super();
	}

	public Member(String userId, String email, String password,
			String name) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
	}



	public Member(String userId, String email, String password,
			String name, String me2DayKey) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.me2DayKey = me2DayKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;;
	}

	public String getMe2DayKey() {
		return me2DayKey;
	}

	public void setMe2DayKey(String me2DayKey) {
		this.me2DayKey = me2DayKey;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", email=" + email + ", password="
				+ password + ", name=" + name + ", me2DayKey=" + me2DayKey + "]";
	}
	
	
}
