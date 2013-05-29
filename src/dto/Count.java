package dto;

public class Count {
	private String basis;
	private int countResult;
	private int totalConnection;
	private int avgConnection;
	private int totalBookMarkCount;
	private double avgBookMarkCount;
	
	public Count() {
		super();
	}

	public Count(String basis, int countResult) {
		super();
		this.basis = basis;
		this.countResult = countResult;
	}
	
	public Count(String basis, int countResult, int totalConnection,
			int avgConnection, int totalBookMarkCount, double avgBookMarkCount) {
		super();
		this.basis = basis;
		this.countResult = countResult;
		this.totalConnection = totalConnection;
		this.avgConnection = avgConnection;
		this.totalBookMarkCount = totalBookMarkCount;
		this.avgBookMarkCount = avgBookMarkCount;
	}
	
	public Count(int totalConnection, int avgConnection,
			int totalBookMarkCount, double avgBookMarkCount) {
		super();
		this.totalConnection = totalConnection;
		this.avgConnection = avgConnection;
		this.totalBookMarkCount = totalBookMarkCount;
		this.avgBookMarkCount = avgBookMarkCount;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public int getCountResult() {
		return countResult;
	}

	public void setCountResult(int countResult) {
		this.countResult = countResult;
	}

	public int getTotalConnection() {
		return totalConnection;
	}

	public void setTotalConnection(int totalConnection) {
		this.totalConnection = totalConnection;
	}

	public int getAvgConnection() {
		return avgConnection;
	}

	public void setAvgConnection(int avgConnection) {
		this.avgConnection = avgConnection;
	}

	public int getTotalBookMarkCount() {
		return totalBookMarkCount;
	}

	public void setTotalBookMarkCount(int totalBookMarkCount) {
		this.totalBookMarkCount = totalBookMarkCount;
	}

	public double getAvgBookMarkCount() {
		return avgBookMarkCount;
	}

	public void setAvgBookMarkCount(double avgBookMarkCount) {
		this.avgBookMarkCount = avgBookMarkCount;
	}

	
}
