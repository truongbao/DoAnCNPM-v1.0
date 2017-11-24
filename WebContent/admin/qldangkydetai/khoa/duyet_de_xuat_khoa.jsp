<%@page import="library.LibraryConstant"%>
<%@page import="model.bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
	
	<script type="text/javascript">
		function validateForm() {
			var inputElements = $('.checkbox-action:checked');
			if (inputElements.length == 0) {
			    alert("Chọn ít nhất 1 đề tài để thực hiện!");
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
                                <form action="<%=request.getContextPath()%>/admin/qldangkydetai/duyet-de-xuat-khoa?type=search" method="post">
                                	<div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <input type="text" name="keyword" class="form-control border-input" value=""  placeholder="Nhập tên đề tài, tên chủ nhiệm cần tìm kiếm">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                        	<div class="form-group">
		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-primary btn-search" />
		                                        <input type="submit" name="reset" value="Hủy tìm kiếm" class="btn btn-primary btn-cancel-search" />
	                                        </div>
                                        </div>
                                    </div>
                                    
                                </form>
                                
                            </div>
                            <div class="text-center text-danger col-md-12" style="font-size: 18px;font-weight: bold;">
                            	 <%
							      if(request.getParameter("msg")!=null){
							          int msg = Integer.parseInt( request.getParameter("msg"));
							          switch(msg){
							            case 1: out.print("Xử lý thành công !!");break;
							            case 0: out.print("Không thành công vui lòng thử lại !!");break;
								        }
							      }
		     			       	%>  
                           	</div>
                            <div class="content table-responsive table-full-width">
                            	<h3>DANH SÁCH ĐỀ XUẤT CẦN DUYỆT</h3>
								<form method="post" action="<%=request.getContextPath()%>/admin/qldangkydetai/duyet-de-xuat-khoa?type=action" onsubmit="return validateForm()">
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên đề tài</th>
                                    	<th>Chủ nhiệm</th>
                                    	<th>Cấp đề tài</th>
                                    	<th>Trạng thái</th>
                                    	<th>Chọn</th>
                                    </thead>
                                    <tbody>
                                    <%
                                       if(request.getAttribute("listDuyetDeTaiByIdKhoa") != null) {
                                    	   ArrayList<DeTai> listDeTaiByIdKhoa = (ArrayList<DeTai>)request.getAttribute("listDuyetDeTaiByIdKhoa");
                                    	   if (listDeTaiByIdKhoa.size() > 0) {
                               		   %>
                          		  			
                               		   			<%
                                    		   for (DeTai objDeTai : listDeTaiByIdKhoa){
												%>
												<tr>
		                                        	<td><%=objDeTai.getIdDeTai() %></td>
		                                            <td><a href="<%=request.getContextPath()%>/admin/qldangkydetai/detail_duyet_dx_khoa?did=<%=objDeTai.getIdDeTai()%>"><%=objDeTai.getTenDeTai() %></a></td>
		                                            <td><%=objDeTai.getFullName() %></td>
		                                        	<td><%=objDeTai.getCapDeTai() %></td>
		                                        	<td><%=LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %></td>
		                                        	<td>
		                                        		<input type="checkbox" class="checkbox-action" name="idDeTai" value="<%=objDeTai.getIdDeTai() %>"> 
		                                        	</td>
		                                        </tr>
                                      			<%}%>
                                      <% }} %>
                                      <tr class="text-center text-danger mt-20 no-result-search" hidden>
                                             <td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
                                        </tr>                                 
                                    </tbody>
                                </table>
                            		<div class="col-md-2 col-offset-8">
                            			<div class="form-group">
                            				<select name="action">
                            					<option value="0">Không duyệt</option>
                            					<option value="1" selected="selected">Duyệt</option>
	                                        </select>
	                                    </div>
                                    </div>
                                    <div class="col-md-2 col-offset-10">
                                    	<div class="form-group">
                                       		<input type="submit" name="submit" value="Thực hiện" class="btn btn-primary btn-search" />
                                      	</div>
                                    </div>
                      		   	</form>
								<%	
									int page_sum = (Integer) request.getAttribute("page_sum"); 
									if(page_sum > 0){
								%>
								<div class="text-right pagination-div">
									<ul class="pagination">
										<%
											String type = (String)request.getParameter("type");
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
								<%} %>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

       <%@include file="/templates/admin/inc/footer.jsp" %>
