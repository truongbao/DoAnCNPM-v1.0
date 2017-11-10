package model.bean;

public class CapDeTai {

	private int idCapDeTai;
	private String tenCapDeTai;
	
	public CapDeTai() {
		super();
	}

	public CapDeTai(int idCapDeTai, String tenCapDeTai) {
		super();
		this.idCapDeTai = idCapDeTai;
		this.tenCapDeTai = tenCapDeTai;
	}

	public int getIdCapDeTai() {
		return idCapDeTai;
	}

	public void setIdCapDeTai(int idCapDeTai) {
		this.idCapDeTai = idCapDeTai;
	}

	public String getTenCapDeTai() {
		return tenCapDeTai;
	}

	public void setTenCapDeTai(String tenCapDeTai) {
		this.tenCapDeTai = tenCapDeTai;
	}

	@Override
	public String toString() {
		return "CapDeTai [idCapDeTai=" + idCapDeTai + ", tenCapDeTai=" + tenCapDeTai + "]";
	}
	
}
