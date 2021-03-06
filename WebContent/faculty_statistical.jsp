<%@page import="model.bean.DeTai"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="header">
						<form action="<%=request.getContextPath()%>/admin/faculty-statistical?type=search" method="post">
							<div class="row">
								<div class="col-md-2">
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
											<option value="Đã hoàn thành">Thông kê đề tài hoàn
												thành</option>
											<option value="Đang thực thiện">Thống kê đề tài đang
												thực hiện</option>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<select name="type_detai" id="type_detai"
											class="form-control border-input">
											<option value="0">-- Chọn cấp đề tài --</option>
											<option value="Cấp cơ sở">Cơ sở</option>
											<option value="Đại học Đà Nẵng">Đại học Đà Nẵng</option>
											<option value="Bộ giáo dục">Bộ giáo dục</option>
										</select>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<input type="submit" name="search" value="Thống kê"
											class="btn btn-default" />
									</div>
								</div>
							</div>

						</form>

						<!-- <a href="edit.html" class="addtop"><img src="assets/img/add.png" alt="" /> Thêm</a> -->
					</div>
					<div class="content table-responsive table-full-width">
						<table class="table table-striped">
							<thead>
								<th>ID</th>
								<th>Tên đề tài</th>
								<th>Chủ nhiệm đề tài</th>
								<th>Trạng thái</th>
								<th>Cấp đề tài</th>
								<th>Ghi chú</th>
							</thead>
							<tbody>
							<%	
								int dt_hoanthanh = (Integer)request.getAttribute("dt_hoanthanh");
								int dt_dangthuchien =(Integer) request.getAttribute("dt_dangthuchien"); // so de tai dang thuc hien
								int dt_CCS = (Integer)request.getAttribute("dt_CCS"); // so detai cap co so
								int dt_DHDN =(Integer) request.getAttribute("dt_DHDN"); // so detai cap ĐH Da Nang
								int dt_BGD = (Integer)request.getAttribute("dt_BGD"); // so detai cap Bo GD
								ArrayList<DeTai> alItem = (ArrayList<DeTai>)request.getAttribute("alItem");
								for(DeTai obj:alItem){
									 
							%>
								<tr>
									<td><%=obj.getIdDeTai() %></td>
									<td><a href="#"><%= obj.getTenDeTai() %></a></td>
									<td><%= obj.getFullName() %></td>
									<td><%=obj.getTrangThai() %></td>
									<td><%=obj.getTenCapDeTai() %></td>
									<td></td>
								</tr>
							<%} %>
							</tbody>
						</table>
						<div class="text-right pagination-div">
							<ul class="pagination">
								<%
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
									href="<%=request.getContextPath()%>/admin/faculty-statistical?type=load&page=<%=i%>"><%=i%></a><li>
								<%
									}
									if (current_page == page_sum) {
										active = "class=\"current\"";
									} else {
										active = "";
									}
								%>
								<li><a <%=active%>
									href="<%=request.getContextPath()%>/admin/faculty-statistical?type=load&page=<%=page_sum%>"><%=page_sum%></a></li>
							</ul>
						</div>
					</div>
					<div>
						<ul>
							<li>Số đề tài đã hoàn thành: <%= dt_hoanthanh %></li>
							<li>Số đề tài đang thực hiện: <%= dt_dangthuchien %></li>
							<li>Số đề tài cấp cơ sở: <%= dt_CCS %></li>
							<li>Số đề tài cấp Đại học Đà Nẵng: <%=dt_DHDN %></li>
							<li>Số đề tài cấp bộ giáo dục: <%=dt_BGD %></li>
						</ul>
					</div>
					<div class="text-center">
						<button class="btn btn-default" type="button"
							style="margin-bottom: 20px;">In</button>
					</div>
				</div>
			</div>


		</div>
	</div>
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>
