<%@page import="java.security.Timestamp"%>
<%@page import="java.sql.Date"%>
<%@page import="model.bean.HopDong"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.DeTai"%>
<%@page import="model.dao.UserDAO"%>
<%@page import="model.dao.HopDongDAO"%>
<%@page import="model.dao.DetaiDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File hợp đồng</title>
<style type="text/css">
	html{
		width: 100%;
		margin-left: 0.5%
	}
	.header{
		width: 100%;
		float: left;
		/*text-align:center;*/
		margin-left: 23%;
	}
	.cls{
		clear:both;
	}
</style>
</head>
<body>
	<%
		String exportToWord = request.getParameter("exportToWord");
		if (exportToWord != null && exportToWord.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-word");
			response.setHeader("Content-Disposition", "inline; filename=" + "word.doc");

		}
	%>

	<div class="row" style="font-size: 16px">
			<div class="header">
				&emsp;&emsp;&emsp;ĐẠI HỌC ĐÀ NẴNG&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<span style="margin-left: 10%; display: inline; fon">CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM</span>
			</div>
			<div class="header" >
				&emsp;TRƯỜNG ĐẠI HỌC BÁCH KHOA&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<span style="margin-left: 11%; display: inline;">Độc lập - Tự do - Hạnh phúc</span>
			</div>
			<div class="cls"></div>
	</div>
	<div class="row">
		<div style="margin: auto; width: 50%;"><hr style=""></hr></div>
	</div>
	<br>
	<div class="col-md-12" style="font-weight: bold; font-size: 22px; text-align:center;">
		HỢP ĐỒNG THỰC HIỆN ĐỀ TÀI KHOA HỌC VÀ CÔNG NGHỆ CẤP TRƯỜNG CỦA ĐẠI HỌC BÁCH KHOA ĐÀ NẴNG
</div>
	<br>
		<div class="row" style="font-size: 17px;">
			&emsp;Căn cứ Quyết định số     /QĐ-ĐHCN ngày    tháng   năm    của Hiệu trưởng Trường Đại học Bách Khoa Đà Nẵng về việc giao kế hoạch nghiên cứu khoa học và công nghệ năm 20…;
			<br>&emsp;Hôm nay, ngày … tháng … năm 20…, tại Trường Đại học Bách Khoa Đà Nẵng, chúng tôi gồm:
	</div>
	<br>
	<%
   		UserDAO userDao = new UserDAO();
   		DetaiDAO detaiDao = new DetaiDAO();
   		HopDong objHopDong = (HopDong) request.getAttribute("objHopDong");
   		if (objHopDong != null) {
    %>
	<b>CHÚNG TÔI GỒM:</b>
		<div class="row" style="font-size: 17px;">
			<b>
			1. Bên đặt hàng (Bên A):</b> Trường Đại học Bách Khoa Đà Nẵng<br>
			<br>- Do Ông/Bà &emsp;&emsp;<%=userDao.getObjUser(objHopDong.getIdGiangVien()).getFullName()  %>
			<br>- Chức vụ: &emsp;&emsp;<%=userDao.getObjUser(objHopDong.getIdGiangVien()).getTenHocViHocHam() %>làm đại diện.
			<br>- Địa chỉ: &emsp;<%=objHopDong.getDiaChiKH() %>
			<br>- Điện thoại:&emsp;<%=objHopDong.getDienThoaiKH() %>Email:&emsp;<%=objHopDong.getEmailKH() %>
		</div>
		<div class="row" style="font-size: 17px;">
			<b>
			1. Bên nhận đặt hàng (Bên B):</b> Chủ nhiệm đề tài:<br>
			<br>- Ông/Bà &emsp;&emsp;<%=objHopDong.getTenKhachHang() %>
			<br>- Địa chỉ: &emsp;<%=objHopDong.getDiaChiKH() %>
			<br>- Điện thoại:&emsp;<%=objHopDong.getDienThoaiKH() %>Email:&emsp;<%=objHopDong.getEmailKH() %>
		</div>
		
		<div class="row" style="font-size: 17px;">
			Cùng thỏa thuận và thống nhất ký kết Hợp đồng thực hiện đề tài khoa học và công nghệ cấp trường (sau đây gọi tắt là Hợp đồng) với các điều khoản sau:
			<br>
		Bên A đặt hàng và Bên B nhận đặt hàng thực hiện đề tài khoa học và công nghệ cấp trường (sau đây gọi là đề tài) theo các nội dung trong Thuyết minh đề tài đã được phê duyệt (sau đây gọi tắt là Thuyết minh).
		<br>Thuyết minh là bộ phận không tách rời của Hợp đồng.
		</div>
		<%
		Date tbd = new Date (objHopDong.getThoiGianBatDau().getTime());
		Date tkt = new Date (objHopDong.getThoiGianKetThuc().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tbd);
		%>
		<div class="row" style="font-size: 17px;">
			<br><b>Điều 2. Thời gian thực hiện Hợp đồng</b><br> 
			Thời gian thực hiện đề tài là từ ngày: <%= tbd.getDay()+"/"+tbd.getMonth()+"/" +calendar.get(Calendar.YEAR) %> đến ngày: <% calendar.setTime(tbd); out.print(tkt.getDay()+"/"+tkt.getMonth()+"/" +calendar.get(Calendar.YEAR)); %> 
		</div>
		<div class="row" style="font-size: 17px;">
		<b>Điều 3. Kinh phí thực hiện đề tài cấp từ ngân sách nhà nước </b> <br>
		Kinh phí thực hiện đề tài cấp từ ngân sách nhà nước là <%=detaiDao.getObjDeTai(objHopDong.getIdDeTai()).getKinhPhiThucHien() %>  vnd. (bằng chữ .... ....... ..... ..... ..... ........ ..... ..... ..... ...........  ...... ..... ..... .....). 
		</div>
		<div class="row" style="font-size: 17px;">
		<b>Điều 4. Quyền và nghĩa vụ của các bên </b> <br>
		<b>1. Quyền và nghĩa vụ của Bên A</b> <br>
		<br>a) Cung cấp các thông tin cần thiết cho việc triển khai, thực hiện Hợp đồng;
		<br>b) Bố trí cho Bên B số kinh phí từ ngân sách nhà nước quy định tại Điều 3 Hợp đồng này theo tiến độ kế hoạch, tương ứng với các nội dung nghiên cứu được phê duyệt;
		<br>c) Tổ chức phê duyệt kế hoạch đấu thầu, mua sắm máy móc, thiết bị, nguyên vật liệu và dịch vụ của đề tài bằng kinh phí do Bên A cấp (nếu có) theo quy định;
		<br>d) Trước mỗi đợt cấp kinh phí, trên cơ sở báo cáo tình hình thực hiện đề tài của Bên B, Bên A căn cứ vào sản phẩm, khối lượng công việc đã hoàn thành theo Thuyết minh để cấp tiếp kinh phí thực hiện Hợp đồng. Bên A có quyền thay đổi tiến độ cấp hoặc ngừng cấp kinh phí nếu Bên B không hoàn thành công việc đúng tiến độ, đúng nội dung công việc được giao;
		<br>đ) Kiểm tra định kỳ hoặc đột xuất để đánh giá tình hình Bên B thực hiện đề tài theo Thuyết minh; 
		<br>e) Kịp thời xem xét, giải quyết theo thẩm quyền hoặc trình cấp có thẩm quyền giải quyết kiến nghị, đề xuất của Bên B về điều chỉnh nội dung chuyên môn, kinh phí và các vấn đề phát sinh khác trong quá trình thực hiện đề tài ;
		<br>g) Tổ chức đánh giá, nghiệm thu kết quả thực hiện đề tài của Bên B theo các yêu cầu, chỉ tiêu trong Thuyết minh; 
		<br>h) Có trách nhiệm cùng Bên B tiến hành thanh lý Hợp đồng theo quy định hiện hành;
		<br>i) Phối hợp cùng Bên B xử lý tài sản được mua sắm bằng ngân sách nhà nước hoặc được tạo ra từ kết quả nghiên cứu của đề tài sử dụng ngân sách nhà nước (nếu có) theo quy định của pháp luật;
		<br>k) Tiếp nhận kết quả thực hiện đề tài, bàn giao kết quả thực hiện đề tài cho tổ chức đề xuất đặt hàng hoặc tổ chức triển khai ứng dụng sau khi được nghiệm thu;
		<br>l) Có trách nhiệm hướng dẫn việc trả thù lao cho tác giả nếu có lợi nhuận thu được từ việc ứng dụng kết quả của đề tài và thông báo cho tác giả việc bàn giao kết quả thực hiện đề tài (nếu có);
		<br>m) Ủy quyền cho Bên B tiến hành đăng ký bảo hộ quyền sở hữu trí tuệ đối với kết quả thực hiện đề tài (nếu có) theo quy định hiện hành;
		<br>n) Thực hiện các quyền và nghĩa vụ khác theo quy định của Luật Khoa học và Công nghệ và các văn bản liên quan.</div> <br>
		<div class="row" style="font-size: 17px;">
		<b>2. Quyền và nghĩa vụ của Bên B</b> <br>
		<br>a) Tổ chức triển khai đầy đủ các nội dung nghiên cứu của đề tài đáp ứng các yêu cầu chất lượng, tiến độ và chỉ tiêu theo Thuyết minh; 
		<br>b) Cam kết thực hiện và bàn giao sản phẩm cuối cùng đáp ứng đầy đủ các tiêu chí đã được phê duyệt;
		<br>c) Được quyền tự chủ, tự quyết định việc sử dụng phần kinh phí để thực hiện đề tài theo dự toán kinh phí đề tài; 
		<br>d) Yêu cầu Bên A cung cấp thông tin cần thiết để triển khai thực hiện Hợp đồng; 
		<br>đ) Kiến nghị, đề xuất điều chỉnh các nội dung chuyên môn, kinh phí và thời hạn thực hiện Hợp đồng khi cần thiết; 
		<br>e) Yêu cầu Bên A cấp đủ kinh phí theo đúng tiến độ quy định trong Hợp đồng khi hoàn thành đầy đủ nội dung công việc theo tiến độ cam kết. Đảm bảo huy động đủ nguồn kinh phí khác theo cam kết. Sử dụng kinh phí đúng mục đích, đúng chế độ hiện hành và có hiệu quả; 
		<br>g) Xây dựng kế hoạch đấu thầu mua sắm máy móc, thiết bị, nguyên vật liệu và dịch vụ của đề tài bằng kinh phí do Bên A cấp (nếu có) để gửi Bên A phê duyệt và thực hiện mua sắm theo quy định của pháp luật;
		<br>h) Chấp hành các quy định pháp luật trong quá trình thực hiện Hợp đồng. Tạo điều kiện thuận lợi và cung cấp đầy đủ thông tin cho các cơ quan quản lý trong việc giám sát, kiểm tra, thanh tra đối với đề tài theo quy định của pháp luật;
		<br>i) Thực hiện việc tự đánh giá, nghiệm thu cấp cơ sở theo quy định hiện hành khi kết thúc đề tài . Sau khi đánh giá, nghiệm thu cấp cơ sở hoàn chỉnh lại hồ sơ theo kết luận của Hội đồng đánh giá cấp cơ sở, Bên B có trách nhiệm chuyển cho Bên A các hồ sơ để Bên A tiến hành việc đánh giá, nghiệm thu theo quy định;
		<br>k) Có trách nhiệm quản lý tài sản được mua sắm bằng ngân sách nhà nước hoặc được tạo ra từ kết quả nghiên cứu của đề tài sử dụng ngân sách nhà nước (nếu có). Chủ nhiệm đề tài có trách nhiệm bàn giao tài sản được mua sắm bằng ngân sách nhà nước hoặc được tạo ra từ kết quả nghiên cứu của đề tài cho cơ quan chủ trì đề tài để quản lý và sử dụng.
		<br>l) Có trách nhiệm cùng Bên A tiến hành thanh lý Hợp đồng theo quy định;
		<br>m) Thực hiện việc đăng ký bảo hộ quyền sở hữu trí tuệ theo ủy quyền của Bên A đối với kết quả nghiên cứu (nếu có);
		<br>n) Chủ nhiệm đề tài giao nộp kết quả thực hiện đề tài cho bộ phận lưu giữ thông tin của cơ quan chủ trì đề tài. Cơ quan chủ trì đề tài xác nhận việc giao nộp kết quả thực hiện đề tài cho chủ nhiệm đề tài.
		<br>o) Công bố kết quả thực hiện đề tài theo quy định hiện hành;
		<br>p) Chủ nhiệm đề tài cùng với các cá nhân trực tiếp sáng tạo ra kết quả nghiên cứu khoa học và phát triển công nghệ được đứng tên tác giả trong đề tài và hưởng quyền tác giả bao gồm cả các lợi ích thu được (nếu có) từ việc khai thác thương mại các kết quả thực hiện đề tài theo quy định pháp luật và các thỏa thuận khác (nếu có);
		<br>q) Có trách nhiệm trực tiếp hoặc tham gia triển khai ứng dụng kết quả nghiên cứu khoa học và phát triển công nghệ theo yêu cầu của Bên A hoặc tổ chức, cá nhân được Bên A giao quyền sở hữu, sử dụng kết quả thực hiện đề tài ; 
		<br>r) Thực hiện bảo mật các kết quả thực hiện đề tài theo quy định về bảo vệ bí mật của nhà nước;
		<br>s) Thực hiện các quyền và nghĩa vụ khác theo quy định Luật Khoa học và Công nghệ và các văn bản liên quan.
		</div>
		<div class="row" style="font-size: 17px;">
		<b>Điều 5. Chấm dứt Hợp đồng</b> <br>
		Hợp đồng này chấm dứt trong các trường hợp sau: 
		<br>1. Đề tài đã kết thúc và được nghiệm thu. 
		<br>2. Bên B bị chấm dứt hợp đồng thực hiện đề tài khi có đề nghị thanh lý Hợp đồng của Hội đồng thanh lý đề tài cấp trường.
		</div>
		<div class="row" style="font-size: 17px;">
		<b>Điều 6. Xử lý tài chính khi chấm dứt Hợp đồng </b> <br>
		<br>1. Đối với đề tài đã kết thúc và được nghiệm thu:
		<br>a) Đề tài đã kết thúc và đánh giá nghiệm thu từ mức “Đạt” trở lên thì Bên A thanh toán đầy đủ kinh phí cho Bên B theo quy định tại Hợp đồng này.
		<br>b) Đề tài đã kết thúc, nhưng nghiệm thu mức “không đạt” thì Bên B có trách nhiệm hoàn trả toàn bộ số kinh phí ngân sách nhà nước đã cấp nhưng chưa sử dụng. Bên B nộp hoàn trả ngân sách nhà nước .... tổng kinh phí ngân sách nhà nước đã sử dụng cho đề tài nếu do lỗi khách quan hoặc ..... tổng kinh phí ngân sách nhà đã sử dụng cho đề tài nếu do lỗi chủ quan. 
		<br>2. Đối với đề tài chấm dứt khi có căn cứ khẳng định không còn nhu cầu thực hiện:
		<br>a) Trường hợp Đề tài chấm dứt khi có căn cứ khẳng định không còn nhu cầu thực hiện thì hai bên cùng nhau xác định khối lượng công việc Bên B đã thực hiện để làm căn cứ thanh toán số kinh phí Bên B đã sử dụng nhằm thực hiện đề tài và thu hồi số kinh phí còn lại đã cấp cho Bên B.
		<br>b) Trường hợp hai bên thỏa thuận ký Hợp đồng mới để thay thế và kết quả nghiên cứu của Hợp đồng cũ là một bộ phận cấu thành kết quả nghiên cứu của Hợp đồng mới thì số kinh phí đã cấp cho Hợp đồng cũ được tính vào kinh phí cấp cho Hợp đồng mới và được tiếp tục thực hiện với Hợp đồng mới.
		<br>3. Đối với Đề tài bị đình chỉ theo quyết định của cơ quan có thẩm quyền hoặc Hợp đồng bị chấm dứt do Bên B không nộp hồ sơ để đánh giá, nghiệm thu Đề tài theo quy định pháp luật thì Bên B có trách nhiệm hoàn trả toàn bộ số kinh phí ngân sách nhà nước đã được cấp nhưng chưa sử dụng. Bên B nộp hoàn trả ngân sách nhà nước .... tổng kinh phí ngân sách nhà nước đã sử dụng cho Đề tài nếu do lỗi khách quan hoặc ..... tổng kinh phí ngân sách nhà đã sử dụng cho Đề tài nếu do lỗi chủ quan.
		<br>4. Đối với Đề tài không hoàn thành do lỗi của Bên A dẫn đến việc chấm dứt Hợp đồng thì Bên B không phải bồi hoàn số kinh phí đã sử dụng để thực hiện Đề tài, nhưng vẫn phải thực hiện việc quyết toán kinh phí theo quy định của pháp luật. 
		</div>
		<div class="row" style="font-size: 17px;">
		<b> Điều 7. Xử lý tài sản khi chấm dứt Hợp đồng</b> <br>
		<br>1. Khi chấm dứt Hợp đồng, việc xử lý tài sản được mua sắm hoặc được hình thành bằng ngân sách nhà nước cấp cho đề tài được thực hiện theo quy định pháp luật. 
		<br>2. Các sản phẩm vật chất của Đề tài sử dụng ngân sách nhà nước: nguồn thu khi các sản phẩm này được tiêu thụ trên thị trường sau khi trừ các khoản chi phí cần thiết, hợp lệ, được phân chia theo quy định pháp luật.
		</div>
		<div class="row" style="font-size: 17px;">
		<b>Điều 8. Điều khoản chung</b> <br>
		<br>1. Trong quá trình thực hiện Hợp đồng, nếu một trong hai bên có yêu cầu sửa đổi, bổ sung nội dung hoặc có căn cứ để chấm dứt thực hiện Hợp đồng thì phải thông báo cho bên kia ít nhất là 15 ngày làm việc trước khi tiến hành sửa đổi, bổ sung hoặc chấm dứt thực hiện Hợp đồng, xác định trách nhiệm của mỗi bên và hình thức xử lý. Các sửa đổi, bổ sung (nếu có) phải lập thành văn bản có đầy đủ chữ ký của các bên và được coi là bộ phận của Hợp đồng và là căn cứ để nghiệm thu kết quả của đề tài.
		<br>2. Khi một trong hai bên gặp phải trường hợp bất khả kháng dẫn đến việc không thể hoặc chậm thực hiện nghĩa vụ đã thỏa thuận trong Hợp đồng thì có trách nhiệm thông báo cho Bên kia trong 10 ngày làm việc kể từ ngày xảy ra sự kiện bất khả kháng. Hai bên có trách nhiệm phối hợp xác định nguyên nhân và báo cáo cơ quan quản lý nhà nước có thẩm quyền để giải quyết theo quy định của pháp luật.
		<br>3. Hai bên cam kết thực hiện đúng các quy định của Hợp đồng và có trách nhiệm hợp tác giải quyết các vướng mắc phát sinh trong quá trình thực hiện. Bên vi phạm các cam kết trong Hợp đồng phải chịu trách nhiệm theo quy định pháp luật. 
		<br>4. Mọi tranh chấp phát sinh trong quá trình thực hiện Hợp đồng do các bên thương lượng hoà giải để giải quyết. Trường hợp không hoà giải được thì một trong hai bên có quyền đưa tranh chấp ra để giải quyết theo quy định của pháp luật.
		</div>
		<div class="row" style="font-size: 17px;">
		<b> Điều 9. Hiệu lực của Hợp đồng</b> <br>
		<br>Hợp đồng này có hiệu lực từ ngày..................... Hợp đồng này được lập thành 05 bản và có giá trị như nhau, Bên A giữ 01 bản, Bên B giữ 04 bản.</div>
		
		<div class="row" style="font-size: 17px;">
			<div><b>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;BÊN A</b>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<b>BÊN B</b><br>
			</div>
			<div>&emsp;&emsp;&emsp;&emsp;&emsp;Bên đặt hàng&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Bên nhận đặt hàng
			</div>
			<div>&emsp;(Chữ ký, họ và tên và đóng dấu)&emsp;&emsp;&emsp;(Chữ ký, họ và tên và đóng dấu – nếu có)
			</div>
		</div>
	<%} %>
	<br><br><br><br><br><br>
	<%
		if (exportToWord == null) {
	%>
	<a href="<%=request.getContextPath() %>/admin/hopdong/export?uid=<%=objHopDong.getIdDeTai() %>&exportToWord=YES"><button
			class="btn btn-primary">Xuất file</button></a>
	<%
		}
	%>
</body>
</html>