<%@page import="model.bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <form action="<%=request.getContextPath()%>/admin/qldangkydetai/duyet-de-xuat-khoa?type=search" method="post">
                                	<div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <input type="text" name="keyword" class="form-control border-input" value=""  placeholder="Nhập tên đề tài, tên chủ nhiệm cần tìm kiếm">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                        	<div class="form-group">
		                                        <input type="submit" name="search" value="Tìm kiếm" class="is" />
		                                        <input type="submit" name="reset" value="Hủy tìm kiếm" class="is" />
	                                        </div>
                                        </div>
                                    </div>
                                    
                                </form>
                                
                            </div>
                            
                            <div class="content table-responsive table-full-width">
                            <h3>DANH SÁCH ĐỀ XUẤT CẦN DUYỆT</h3>
                            		
                            
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên đề tài</th>
                                    	<th>Chủ nhiệm</th>
                                    	<th>Cấp đề tài</th>
                                    	<th>Trạng thái</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                    <%
                                       if(request.getAttribute("listDuyetDeTaiByIdKhoa") != null) {
                                    	   ArrayList<DeTai> listDeTaiByIdKhoa = (ArrayList<DeTai>)request.getAttribute("listDuyetDeTaiByIdKhoa");
                                    	   if (listDeTaiByIdKhoa.size() > 0) {
                                    		   for (DeTai objDeTai : listDeTaiByIdKhoa){
                                    %>
                                        <tr>
                                        	<td><%=objDeTai.getIdDeTai() %></td>
                                            <td><a href="<%=request.getContextPath()%>/admin/qldangkydetai/detail_duyet_dx_khoa?did=<%=objDeTai.getIdDeTai()%>"><%=objDeTai.getTenDeTai() %></a></td>
                                            <td><%=objDeTai.getFullName() %></td>
                                        	<td><%=objDeTai.getCapDeTai() %></td>
                                        	<td><%=objDeTai.getTrangThai() %></td>
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/qldangkydetai/detail_duyet_dx_khoa?did=<%=objDeTai.getIdDeTai()%>"><img src="assets/img/edit.gif" alt="" /> Xem</a>
                                        		<a href="<%=request.getContextPath()%>/admin/qldangkydetai/detail_duyet_dx_khoa?did=<%=objDeTai.getIdDeTai()%>"><img src="assets/img/edit.gif" alt="" /> Duyệt</a>
                                        	</td>
                                        </tr>
                                          
                                          
                                          
                                      <%}}} %>                               
                                    </tbody>
                                </table>

								<div class="text-right pagination-div">
									<ul class="pagination">
										<%
											String type = (String)request.getParameter("type");
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
											href="<%=request.getContextPath()%>/admin/qldangkydetai/duyet-de-xuat-khoa?type=<%=type%>&page=<%=i%>"><%=i%></a><li>
										<%
											}
											if (current_page == page_sum) {
												active = "class=\"current\"";
											} else {
												active = "";
											}
										%>
										<li><a <%=active%>
											href="<%=request.getContextPath()%>/admin/qldangkydetai/duyet-de-xuat-khoa?type=<%=type%>&page=<%=page_sum%>"><%=page_sum%></a></li>
									</ul>
								</div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

       <%@include file="/templates/admin/inc/footer.jsp" %>
