<%@page import="model.bean.ThongBao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<script type="text/javascript">
	function validateForm() {
	    var a = document.forms["searchform"]["key_words"].value;
	    var b = document.forms["searchform"]["create_date"].value;
	    if (a == "" && b == "") {
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
                                <h4 class="title">Danh sách thông báo</h4>
                                <a class="addtop" href="<%=request.getContextPath()%>/admin/add-notif?type=load">Thêm thông báo</a>
                                <form action="<%=request.getContextPath()%>/admin/notif?type=search" method="post" name="searchform" onsubmit="return validateForm()">
                                	<div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                 <input type="text" name="key_words" class="form-control border-input" value=""  placeholder="Nhập từ khóa...">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input type="date" name="create_date" class="form-control border-input" />
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                        	<div class="form-group">
		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-default" />
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
										String filter_date = (String)request.getAttribute("filter_date");
										String filter_key_words = (String)request.getAttribute("filter_key_words");
										if(!"".equals(filter_key_words)){
									%>
										<span><%=filter_key_words %></span>	
									<%	} 
										if(!"".equals(filter_date)){
									%>
										<span><%=filter_date %></span>	
									<%	}}
									%>
								</div>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Đề tài</th>
                                    	<th>Chủ nhiệm</th>
                                    	<th>Ngày</th>
                                    </thead>
                                    <tbody>
                                    <% 
                                    	ArrayList<ThongBao> alItems = (ArrayList<ThongBao>) request.getAttribute("alItem");
	                                    for(ThongBao obj : alItems){ 
                                    %>
                                        <tr>
                                        	<td><%=obj.getIdThongBao() %></td>
                                        	<td><%=obj.getTenDeTai() %></td>
                                        	<td><%=obj.getTenCNDT() %></td>
                                        	<td><%=obj.getThoiGian() %></td>
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
											href="<%=request.getContextPath()%>/admin/notif?type=load&page=<%=i%>"><%=i%></a><li>
										<%
											}
											if (current_page == page_sum) {
												active = "class=\"current\"";
											} else {
												active = "";
											}
										%>
										<li><a <%=active%>
											href="<%=request.getContextPath()%>/admin/notif?type=load&page=<%=page_sum%>"><%=page_sum%></a></li>
									</ul>
								</div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

<%@include file="/templates/admin/inc/footer.jsp"%>
