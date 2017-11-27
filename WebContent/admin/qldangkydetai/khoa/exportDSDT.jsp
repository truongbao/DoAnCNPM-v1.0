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
<title>File Danh sách đề xuất</title>

<style>
   table {border-collapse:collapse; table-layout:fixed; width:100%;}
   table td, th {border:solid 1px black; width:100px; word-wrap:break-word;}
   </style>
</head>
<body>
	<%
	String exportToWord = request.getParameter("exportToWord");
	if (exportToWord != null && exportToWord.toString().equalsIgnoreCase("YES")) {
		//response.setContentType("application/vnd.ms-word");
		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		response.setHeader("Content-Disposition", "inline; filename=" + "DanhSachDeXuat.doc");
		System.out.println("XUAT FILE OK");
	}
	%>

	<div class="row" style="font-size: 16px">
			<div>
				ĐẠI HỌC ĐÀ NẴNG
			</div>
			<!--  &emsp;&emsp;&emsp;ĐẠI HỌC ĐÀ NẴNG&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM&emsp;TRƯỜNG ĐẠI HỌC BÁCH KHOA&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Độc lập - Tự do - Hạnh phúc&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;-->
	</div>
	<br>
	<div>
		<p style="margin: auto; width: 47%;">______________________________________________________________________________</p>
	</div>
	<br><br>
	<div class="row" style="font-size: 17px;">
		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Đà Nẵng, ngày&emsp;&emsp;tháng&emsp;&emsp;năm&emsp;&emsp; 
	</div>
	<br>
	<div class="col-md-12" style="font-weight: bold; font-size: 22px; text-align: center;">
		DANH MỤC ĐỀ XUẤT KH&CN CẤP CƠ SỞ NĂM 2017
</div>
	<br><br><br>
	 <table>
         <thead>
          <th>STT</th>
          <th>Tên đề tài</th>
          <th>Chủ nhiệm</th>
          <th>Tính cấp thiết</th>
           <th>Mục tiêu và nội dung chính</th>
          <th>Dự kiến kết quả đạt được</th>
          <th>Thời gian</th>
          <th>Kinh phí</th>
          <th>Ghi chú</th>
           </thead>
           <tbody>
			<%
				if(request.getAttribute("listDeTai") != null) {
					ArrayList<DeTai> listDeTai = (ArrayList<DeTai>) request.getAttribute("listDeTai");
					int stt = 1;
					if (listDeTai.size() > 0) {
						for (DeTai objDeTai : listDeTai) {
			%>
			<tr>
				<td><%=stt%></td>
				<td><%=objDeTai.getTenDeTai()%></td>
				<td><%=objDeTai.getFullName() %></td>
				<td><%=objDeTai.getTinhCapThiet() %></td>
				<td><%=objDeTai.getMucTieu() %></td>
				<td><%=objDeTai.getHieuQua() %></td>
				<td>1 năm</td>
				<td><%=objDeTai.getKinhPhiThucHien() %></td>
				<td>                                </td>
			</tr>
			<% stt++;
			}} %>
			 </tbody>
        </table>
        <br>
        <b>Danh sách này có <%=listDeTai.size()%> đề tài</b>
        <%} %>
        <br><br><br>
        <div>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<b>TRƯỞNG PHÒNG KHCN & HTQT</b><br>
			</div>
			
			<div>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;(Chữ ký, họ và tên)
			</div>
        <br><br><br><br>
        <%
		if (exportToWord == null) {
	%>
	<a href="<%=request.getContextPath() %>/admin/qldangkydetai/nhanvien/export?exportToWord=YES"><button
			class="btn btn-primary">Xuất file</button></a>
	<%
		}
	%>
	
</body>
</html>