package model.bean;

public class HocVi {

	private int idHocVi;
	private String tenHocVi;
	
	public HocVi() {
		super();
	}

	public HocVi(int idHocVi, String tenHocVi) {
		super();
		this.idHocVi = idHocVi;
		this.tenHocVi = tenHocVi;
	}

	public int getIdHocVi() {
		return idHocVi;
	}

	public void setIdHocVi(int idHocVi) {
		this.idHocVi = idHocVi;
	}

	public String getTenHocVi() {
		return tenHocVi;
	}

	public void setTenHocVi(String tenHocVi) {
		this.tenHocVi = tenHocVi;
	}

	@Override
	public String toString() {
		return "HocVi [idHocVi=" + idHocVi + ", tenHocVi=" + tenHocVi + "]";
	}
	
    	
	
	
	
}
