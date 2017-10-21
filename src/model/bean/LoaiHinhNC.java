package model.bean;

public class LoaiHinhNC {
	
	private int idLoaiHinhNghienCuu;
	private String tenLoaiHinhNghienCuu;
	
	public LoaiHinhNC() {
		super();
	}

	public LoaiHinhNC(int idLoaiHinhNghienCuu, String tenLoaiHinhNghienCuu) {
		super();
		this.idLoaiHinhNghienCuu = idLoaiHinhNghienCuu;
		this.tenLoaiHinhNghienCuu = tenLoaiHinhNghienCuu;
	}

	public int getIdLoaiHinhNghienCuu() {
		return idLoaiHinhNghienCuu;
	}

	public void setIdLoaiHinhNghienCuu(int idLoaiHinhNghienCuu) {
		this.idLoaiHinhNghienCuu = idLoaiHinhNghienCuu;
	}

	public String getTenLoaiHinhNghienCuu() {
		return tenLoaiHinhNghienCuu;
	}

	public void setTenLoaiHinhNghienCuu(String tenLoaiHinhNghienCuu) {
		this.tenLoaiHinhNghienCuu = tenLoaiHinhNghienCuu;
	}

	@Override
	public String toString() {
		return "LoaiHinhNC [idLoaiHinhNghienCuu=" + idLoaiHinhNghienCuu
				+ ", tenLoaiHinhNghienCuu=" + tenLoaiHinhNghienCuu + "]";
	}

	
	
	

}
