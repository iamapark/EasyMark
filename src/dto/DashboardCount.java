package dto;

public class DashboardCount {
	private int memberCount;
	private int todayRegisterCount;
	private int messageCount;
	
	public DashboardCount() {
		super();
	}

	public DashboardCount(int memberCount, int todayRegisterCount,
			int messageCount) {
		super();
		this.memberCount = memberCount;
		this.todayRegisterCount = todayRegisterCount;
		this.messageCount = messageCount;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public int getTodayRegisterCount() {
		return todayRegisterCount;
	}

	public void setTodayRegisterCount(int todayRegisterCount) {
		this.todayRegisterCount = todayRegisterCount;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
}
