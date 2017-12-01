<%@page import="model.bean.CapDeTai"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.security.Timestamp"%>
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
<style>
   table {border-collapse:collapse; table-layout:fixed; width:100%;}
   table td, th {border:solid 1px black; width:100px; word-wrap:break-word;}
   </style>
</head>
<body>
	<%
	String tenCDT = (String) request.getAttribute("tenCDT");
	int year = (Integer)request.getAttribute("nam");
	String trangThai = (String) request.getAttribute("trangThai");
	String exportToWord = request.getParameter("exportToWord");
	if (exportToWord != null && exportToWord.toString().equalsIgnoreCase("YES")) {
		//response.setContentType("application/vnd.ms-word");
		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		response.setHeader("Content-Disposition", "inline; filename=" + "DanhSachDeXuat.doc");
		System.out.println("XUAT FILE OK");
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
	<div class="col-md-12" style="font-weight: bold; font-size: 22px; text-align: center;">
		DANH MỤC ĐỀ TÀI KH&CN <%if(trangThai != null){ %>  <span style="text-transform:uppercase;"><%=trangThai%></span><%}else{ %> ĐÃ ĐĂNG KÝ<%} %> <%if(tenCDT != null){ %>CẤP <span style="text-transform:uppercase;"><%= tenCDT %></span><%} %><%if(year != 0){ %> NĂM <%= year %> <%} %>
		<%if(request.getAttribute("khoa") != null){ %><div>Khoa: <%=request.getAttribute("khoa") %></div><%} %>
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
        <div class="row" style="font-size: 17px;">
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<span style="float: right; margin-right: 10%">Đà Nẵng, ngày&emsp;&emsp;tháng&emsp;&emsp;năm&emsp;&emsp; </span>
			<div class="cls"></div>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<span style="float: right; margin-right: 11%"><b>TRƯỞNG PHÒNG KHCN & HTQT</b></span>
			<div class="cls"></div>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<span style="float: right; margin-right: 17%">(Chữ ký, họ và tên)</span>
		</div>
        <div>
			<br>
			</div>
			
			<div>
			</div>
        <br><br><br><br>
        <%
		if (exportToWord == null) {
			
	%>
	<a href="<%=request.getContextPath() %>/admin/general-statistical?type=export&exportToWord=YES"><button
			class="btn btn-primary">Xuất file</button></a>
	<%
		}
	%>
	
</body>
</html>