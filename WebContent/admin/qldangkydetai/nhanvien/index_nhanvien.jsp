<%@page import="library.LibraryConstant"%>
<%@page import="model.bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Khoa"%>
<%@page import="model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                               
                                <div class="col-md-12">
                                    <form action=""<%=request.getContextPath() %>" method="get">
                                    	<div class="row">
                                    		
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input type="text" 
	                                                    name="key" class="form-control border-input"
	                                                    value="<%=request.getParameter("key") != null ? request.getParameter("key") : "" %>" >
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <select name="khoa" class="form-control border-input">
                                                    	<option value="0">-- Tất cả các khoa--</option>
                                                    	<% 

	                                                    	UserDAO userDao = new UserDAO();
                                                    		
					                                    	ArrayList<Khoa> listKhoa = userDao.getListKhoa();
					                                    	for(int i = 0; i < listKhoa.size(); i++) {
					                                    		if (request.getParameter("khoa") != null) {
					                                    		if (Integer.parseInt(request.getParameter("khoa")) == listKhoa.get(i).getIdKhoa()) {
					                                    %>
					                                    <option value="<%= listKhoa.get(i).getIdKhoa() %>" selected><%= listKhoa.get(i).getTenKhoa() %></option>
                                                    	        <%} else {%>
                                                    	<option value="<%= listKhoa.get(i).getIdKhoa() %>"><%= listKhoa.get(i).getTenKhoa() %></option>
                                                    		<%	  }
					                                    	} else {%>
					                                    	<option value="<%= listKhoa.get(i).getIdKhoa() %>"><%= listKhoa.get(i).getTenKhoa() %></option>
                                                    		
					                                    	<%}
					                                    	}%>
                                                    </select>
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-3">
                                            	<div class="form-group">
    		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-primary btn-search" />
    		                                        <input type="submit" name="cancel" value="Hủy tìm kiếm" class="btn btn-primary btn-cancel-search" />
    	                                        </div>
                                            </div>
                                        </div>
                                        
                                    </form>
                                </div>
                            </div>
                            
                            <div class="content table-responsive table-full-width">
                            <div class="row"> 
                            		<div class="col-md-4"><h3>DANH SÁCH ĐỀ TÀI</h3></div>
                            		<div class="col-md-4"><a class="btn btn-info btn-fill btn-wd" style = "margin-top: 20px;" href="<%=request.getContextPath()%>/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv">Xem danh sách duyệt đề xuất</a>
                            		</div>
                            		<div class="col-md-4"><a class="btn btn-info btn-fill btn-wd" style = "margin-top: 20px;" href="<%=request.getContextPath()%>/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv">Xem danh sách duyệt thuyết minh</a>
                            		</div>
                            	</div>
                            
                                <table class="table table-striped" id="table-contain">
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
                                       if(request.getAttribute("listDeTaiNhanVien") != null) {
                                    	   ArrayList<DeTai> listDeTaiByIdKhoa = (ArrayList<DeTai>)request.getAttribute("listDeTaiNhanVien");
                                    	   if (listDeTaiByIdKhoa.size() > 0) {
                                    		   for (DeTai objDeTai : listDeTaiByIdKhoa){
                                    %>
                                        <tr>
                                        	<td><%=objDeTai.getIdDeTai() %></td>
                                            <td><a href="<%=request.getContextPath()%>/admin/qldangkydetai/khoa/xem_de_tai?did=<%=objDeTai.getIdDeTai()%>"><%=objDeTai.getTenDeTai() %></a></td>
                                            <td><%=objDeTai.getFullName() %></td>
                                        	<td><%=objDeTai.getCapDeTai() %></td>
                                        	<td><%=LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %></td>
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/qldangkydetai/khoa/xem_de_tai?did=<%=objDeTai.getIdDeTai()%>"><img src="assets/img/edit.gif" alt="" /> Xem</a>
                                        	</td>
                                        </tr>  
                                      <%}}} %>  
                                      <tr class="text-center text-danger mt-20 no-result-search" hidden>
                                             <td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
                                        </tr>                             
                                    </tbody>
                                </table>

								<div class="text-center">
								    <ul class="pagination">
								        <li><a href="?p=0" title="">1</a></li> 
								        <li><a href="?p=1" title="">2</a></li> 
								        <li><a href="?p=1" title="">3</a></li> 
								        <li><a href="?p=1" title="">4</a></li> 
								    </ul>
								</div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

       <%@include file="/templates/admin/inc/footer.jsp" %>
