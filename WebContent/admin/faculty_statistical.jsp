<%@page import="model.bean.CapDeTai"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="library.LibraryConstant"%>
<%@page import="model.bean.DeTai"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<script type="text/javascript">
	function validateForm() {
	    var a = document.forms["searchform"]["year_create"].value;
	    var b = document.forms["searchform"]["type_stat"].value;
	    var c = document.forms["searchform"]["type_detai"].value;
	    if (a == 0 && b == 0 && c == 0) {
	        alert("Chọn ít nhất 1 điều kiện!");
	        return false;
	    }
	}
</script>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="header">
						<form action="<%=request.getContextPath()%>/admin/faculty-statistical?type=search" method="post" name="searchform" onsubmit="return validateForm()">
							<div class="row">
								<div class="col-md-offset-1 col-md-2">
									<div class="form-group">
										<select name="year_create" class="form-control border-input">
											<%
												int year = Calendar.getInstance().get(Calendar.YEAR);
											%>
											<option value="0" selected>--Chọn năm--</option>
											<%
												for (int i = 0; i <= 20; i++) {
											%>
											<option value="<%=year - i%>">Năm
												<%=year - i%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<select name="type_stat" id="type_stat"
											class="form-control border-input" onchange="enableTypeDT()">
											<option value="0">-- Chọn kiểu thống kê --</option>
											<!-- Value = 1 to enable type_dt -->
											<option value="<%=LibraryConstant.DaHoanThanh%>">Thông kê đề tài hoàn
												thành</option>
											<option value="<%=LibraryConstant.DangThucHien%>">Thống kê đề tài đang
												thực hiện</option>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<select name="type_detai" id="type_detai"
											class="form-control border-input">
											<option value="0">-- Chọn cấp đề tài --</option>
											<%
												ArrayList<CapDeTai> alCDT = (ArrayList<CapDeTai>) request.getAttribute("alCDT");
												for(CapDeTai obj: alCDT){
											%>
											<option value="<%=obj.getIdCapDeTai()%>"><%=obj.getTenCapDeTai() %></option>
											<%	} %>
										</select>
									</div>
								</div>
								<div class="col-md-12 text-center">
									<div class="form-group">
										<input type="submit" name="search" value="Thống kê"
											class="btn btn-default" />
										<a href="<%=request.getContextPath()%>/admin/faculty-statistical?type=load" name="reset"
										class="btn btn-default">Hủy thống kê</a>
									</div>
								</div>
							</div>

						</form>

						<div class="filter-div">
							<%
								if("search".equals(request.getParameter("type"))){
							%>
									<strong>Filter:</strong> 
							<%
									int filter_year = (Integer) request.getAttribute("filter_year");
									String filter_type_stat = (String)request.getAttribute("filter_type_stat");
									String filter_type_detai = (String)request.getAttribute("filter_type_detai");
									if(filter_year != 0){
							%>
								<span><%=filter_year %></span>	
							<%	} 
								if(!"0".equals(filter_type_stat)){
							%>
								<span><%=LibraryConstant.ConvertTrangThai(filter_type_stat) %></span>	
							<%	} 
								if(filter_type_detai != null){
							%>
								<span class=""><%=filter_type_detai %></span>	
							<%	}}
							%>							
						</div>  
					</div>
					<div class="content table-responsive table-full-width">
						<table class="table table-striped">
							<thead>
								<th>ID</th>
								<th>Tên đề tài</th>
								<th>Chủ nhiệm đề tài</th>
								<th>Trạng thái</th>
								<th>Cấp đề tài</th>
								<th>Năm bắt đầu thực hiện</th>
								<th>Ghi chú</th>
							</thead>
							<tbody>
							<%	
								ArrayList<DeTai> alItem = (ArrayList<DeTai>)request.getAttribute("alItem");
								for(DeTai obj:alItem){
									String date = new SimpleDateFormat("dd-MM-yyyy").format(obj.getThoiGianBatDau());
							%>
								<tr>
									<td><%=obj.getIdDeTai() %></td>
									<td><a href="#"><%= obj.getTenDeTai() %></a></td>
									<td><%= obj.getFullName() %></td>
									<td><%=obj.getTrangThai() %></td>
									<td><%=obj.getTenCapDeTai() %></td>
									<td><%= date%></td>
									<td></td>
								</tr>
							<%} %>
							</tbody>
						</table>
						<div class="text-right pagination-div">
							<ul class="pagination">
								<%
									String type =  (String) request.getParameter("type");
									int page_sum = (Integer) request.getAttribute("page_sum");
									int current_page = (Integer) request.getAttribute("current_page");
									String active = "";
									for (int i = 1; i < page_sum; i++) {
										if (i == current_page) {
											active = "class=\"current\"";
										} else {
											active = "";
										}
								%>
								<li><a <%=active%>
									href="<%=request.getContextPath()%>/admin/faculty-statistical?type=<%=type%>&page=<%=i%>"><%=i%></a><li>
								<%
									}
									if (current_page == page_sum) {
										active = "class=\"current\"";
									} else {
										active = "";
									}
								%>
								<li><a <%=active%>
									href="<%=request.getContextPath()%>/admin/faculty-statistical?type=<%=type%>&page=<%=page_sum%>"><%=page_sum%></a></li>
							</ul>
						</div>
					</div>
					<div class="text-center">
						<a class="btn btn-default" style="margin-bottom: 20px;" href="<%=request.getContextPath()%>/admin/faculty-statistical?type=export"  target="_blank">In</a>
					</div>
				</div>
			</div>
		

		</div>
	</div>
</div>
<%@include file="/templates/admin/inc/footer.jsp"%>
