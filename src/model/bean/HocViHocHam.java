package model.bean;

public class HocViHocHam {

	private int idHocViHocHam;
	private String tenHocViHocHam;
	
	public HocViHocHam() {
		super();
	}

	public HocViHocHam(int idHocViHocHam, String tenHocViHocHam) {
		super();
		this.idHocViHocHam = idHocViHocHam;
		this.tenHocViHocHam = tenHocViHocHam;
	}

	public int getIdHocViHocHam() {
		return idHocViHocHam;
	}

	public void setIdHocViHocHam(int idHocViHocHam) {
		this.idHocViHocHam = idHocViHocHam;
	}

	public String getTenHocViHocHam() {
		return tenHocViHocHam;
	}

	public void setTenHocViHocHam(String tenHocViHocHam) {
		this.tenHocViHocHam = tenHocViHocHam;
	}

	@Override
	public String toString() {
		return "HocViHocHam [idHocViHocHam=" + idHocViHocHam + ", tenHocViHocHam=" + tenHocViHocHam + "]";
	}
	
    	
	
	
	
}
