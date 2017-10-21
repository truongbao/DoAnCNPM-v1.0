package model.bean;

public class LinhVucNC {

	private int idLinhVucNghienCuu;
	private String tenLinhVucNghienCuu;
	
	public LinhVucNC() {
		super();
	}

	public LinhVucNC(int idLinhVucNghienCuu, String tenLinhVucNghienCuu) {
		super();
		this.idLinhVucNghienCuu = idLinhVucNghienCuu;
		this.tenLinhVucNghienCuu = tenLinhVucNghienCuu;
	}

	public int getIdLinhVucNghienCuu() {
		return idLinhVucNghienCuu;
	}

	public void setIdLinhVucNghienCuu(int idLinhVucNghienCuu) {
		this.idLinhVucNghienCuu = idLinhVucNghienCuu;
	}

	public String getTenLinhVucNghienCuu() {
		return tenLinhVucNghienCuu;
	}

	public void setTenLinhVucNghienCuu(String tenLinhVucNghienCuu) {
		this.tenLinhVucNghienCuu = tenLinhVucNghienCuu;
	}

	@Override
	public String toString() {
		return "LinhVucNC [idLinhVucNghienCuu=" + idLinhVucNghienCuu
				+ ", tenLinhVucNghienCuu=" + tenLinhVucNghienCuu + "]";
	}

	
	
	
	
}
