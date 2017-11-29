package library;

public class LibraryConstant {
     
	  public final static int ROW_COUNT=5;
	  public final static int ROW_COUNT_CAT=3;
	  public final static int ROW_COUNT_NEWS=6;
	  public final static int ROW_COUNT_BaiVietMoi=3;
	  public static final int ROW_PUBLIC_COUNT = 7; 
	  public final static int ROW_COUNT_GV=8;
	  
	  public final static String TOPICTYPE_CAPCOSO = "Cấp cơ sở" ;
	  public final static String TOPICTYPE_CAPDHDN = "Cấp Đại học Đà Nẵng" ;
	  public final static String TOPICTYPE_CAPBGD = "Cấp Bộ giáo dục";
	  
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
	  public final static String ChoHuy = "17";
	  public final static String ChoDeNghiChinhSuaDeXuat = "18";
	  public final static String ChoHuyThuyetMinh = "19";
	  public final static String ChoDeNghiChinhSuaThuyetMinh = "20";
	  
	  public final static String pathAvt = "/home/yenhot/web/DoAnCNPM_SE03/WebContent/templates/admin/img/users" ;


	  
	  
	  public static String ConvertTrangThai(String soDangChuoi){
		  String ketQua = null;
		  switch(soDangChuoi){
		  case "1":
			    ketQua = "Đang chờ duyệt đề xuất cấp khoa";
			    break;
		  case "2":
			    ketQua = "Đang chờ xét đề tài";
			    break;
		  case "3":
			    ketQua = "Khoa đề nghị chỉnh sửa đề xuất";
			    break;
		  case "4":
			    ketQua = "Đang chờ xét đề xuất cấp trường";
			    break;
		  case "5":
			    ketQua = "Hủy";
			    break;
		  case "6":
			    ketQua = "Đang chờ duyệt đề xuất cấp trường";
			    break;
		  case "7":
			    ketQua = "Trường đề nghị chỉnh sửa đề xuất";
			    break;
		  case "8":
			    ketQua = "Đang chờ xét thuyết minh";
			    break;
		  case "9":
			    ketQua = "Đang chờ duyệt thuyết minh";
			    break;
		  case "10":
			    ketQua = "Trường đề nghị chỉnh sửa thuyết minh";
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
		  case "17":
			    ketQua = "Đang chờ huỷ đề xuất";
			    break;
		  case "18":
			    ketQua = "Đang chờ duyệt đề nghị chỉnh sửa đề xuất";
			    break;
		  case "19":
			    ketQua = "Đang chờ huỷ thuyết minh";
			    break;
		  case "20":
			    ketQua = "Đang chờ duyệt đề nghị chỉnh sửa thuyết minh";
			    break;
		  default:
			    break;
			    
		  }
		  
		  return ketQua;
		  
	  }
	  
	  
	  
}
