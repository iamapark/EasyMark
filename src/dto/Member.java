package dto;

public class Member {
	private String userId = null;
	private String email = null;
	private String password = null;
	private String name = null;
	private String me2DayKey = null;
	private String imgUrl = null;
	private String bgImgUrl = null;
	
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
	
	public Member(String userId, String email, String password,
			String name, String me2DayKey, String imgUrl) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.me2DayKey = me2DayKey;
		this.imgUrl = imgUrl;
	}
	
	public Member(String userId, String email, String password,
			String name, String me2DayKey, String imgUrl, String bgImgUrl) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.me2DayKey = me2DayKey;
		this.imgUrl = imgUrl;
		this.bgImgUrl = bgImgUrl;
	}
	
	public Member(String userId, String bgImgUrl){
		this.userId = userId;
		this.bgImgUrl = bgImgUrl;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getBgImgUrl() {
		return bgImgUrl;
	}

	public void setBgImgUrl(String bgImgUrl) {
		this.bgImgUrl = bgImgUrl;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", email=" + email + ", password="
				+ password + ", name=" + name + ", me2DayKey=" + me2DayKey
				+ "imgUrl= " + imgUrl + " bgImgUrl= " + bgImgUrl + "]";
	}

	public boolean isContentEmpty() {
		return getUserId().equals("");
				//|| getName().equals("") 
				//|| getEmail().equals("") ;
				//|| getPassword().equals(""); 
	}
	
	
}
