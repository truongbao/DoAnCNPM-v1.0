package constant;

public class define {
     
	  public final static int ROW_COUNT=5;
	  public final static int ROW_COUNT_CAT=3;
	  public final static int ROW_COUNT_NEWS=6;
	  public final static int ROW_COUNT_BaiVietMoi=3;
	  
	  public final static String DangChoDuyetCapKhoa = "1" ;
	  public final static String DangChoXetDeTai = "2" ;
	  public final static String KhoaDeXuatChinhSua = "3";
	  public final static String DangChoXetCapTruong = "4" ;
	  public final static String Huy = "5" ;
	  public final static String DangChoDuyetCapTruong = "6" ;
	  public final static String TruongDeXuatChinhSua = "7" ;
	  public final static String DangChoXetThuyetMinh = "8" ;
	  public final static String DangChoDuyetThuyetMinh = "9" ;
	  public final static String TruongDeXuatChinhSuaThuyetMinh = "10" ;
	  public final static String DaDuyet = "11" ;
	  public final static String DangThucHien = "12" ;
	  public final static String DangChoXetNghiemThu = "13" ;
	  public final static String DangChoDuyetNghiemThu = "14" ;
	  public final static String DaHoanThanh = "15" ;
	  public final static String KhongHoanThanh = "16" ;

	  
	  public static String ConvertTrangThai(String soDangChuoi){
		  String ketQua = null;
		  switch(soDangChuoi){
		  case "1":
			    ketQua = "Đang chờ duyệt cấp khoa";
			    break;
		  case "2":
			    ketQua = "Đang chờ xét đề tài";
			    break;
		  case "3":
			    ketQua = "Khoa đề xuất chỉnh sửa";
			    break;
		  case "4":
			    ketQua = "Đang chờ xét cấp trường ";
			    break;
		  case "5":
			    ketQua = "Hủy";
			    break;
		  case "6":
			    ketQua = "Đang chờ duyệt cấp trường";
			    break;
		  case "7":
			    ketQua = "Trường đề xuất chỉnh sửa";
			    break;
		  case "8":
			    ketQua = "Đang chờ xét thuyết minh ";
			    break;
		  case "9":
			    ketQua = "Đang chờ duyệt thuyết minh  ";
			    break;
		  case "10":
			    ketQua = "Trường đề xuất chỉnh sửa thuyết minh ";
			    break;
		  case "11":
			    ketQua = "Đã duyệt ";
			    break;
		  case "12":
			    ketQua = "Đang thực hiện";
			    break;
		  case "13":
			    ketQua = "Đang chờ xét nghiệm thu ";
			    break;
		  case "14":
			    ketQua = "Đang chờ duyệt nghiệm thu ";
			    break;
		  case "15":
			    ketQua = "Đã hoàn thành ";
			    break;
		  case "16":
			    ketQua = "Không hoàn thành ";
			    break;
		  default:
			    break;
			    
		  }
		  
		  return ketQua;
		  
	  }
	  
	  
	  
}
