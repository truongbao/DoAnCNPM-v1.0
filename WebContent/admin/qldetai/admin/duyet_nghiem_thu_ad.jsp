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
                                <!-- <p class="category success">Thêm thành công</p> -->
                                <form action="<%=request.getContextPath()%>/admin/qldetai/duyet_nghiem_thu_ad?type=search" method="post">
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
                            <h3>DANH SÁCH ĐỀ TÀI CẦN DUYỆT</h3>
                            <form method="post" action="<%=request.getContextPath()%>/admin/qldetai/duyet_nghiem_thu_ad?type=action" onsubmit="return validateForm()">
                                <div class="row">
                                 	<div class="btn-action">
                                     		<input type="submit" name="submit" value="Thực hiện" class="btn btn-primary btn-search" />
                                    </div>
                            		<div class="btn-action" style="margin-right: 7px;width: 95px;margin-top: 5px;" >
                           				<select name="action" class="form-control border-input" style="font-size: 12px; padding: 0px; height: 24px;width: 95px;">
                           					<option value="0">Không duyệt</option>
                           					<option value="1" selected="selected">Duyệt</option>
                                        </select>
                                    </div>
                                </div>
                                <table class="table table-striped" id="table-contain">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên đề tài</th>
                                    	<th>Chủ nhiệm</th>
                                    	<th>Cấp đề tài</th>
                                    	<th>Trạng thái</th>
                                    	<th>Kết quả nghiệm thu</th>
                                    	<th>Chọn</th>
                                    </thead>
                                    <tbody>
                                    <%
                                       if(request.getAttribute("listDeTaiAdmin") != null) {
                                    	   ArrayList<DeTai> listDeTaiByIdKhoa = (ArrayList<DeTai>)request.getAttribute("listDeTaiAdmin");
                                    	   if (listDeTaiByIdKhoa.size() > 0) {
                                    		   for (DeTai objDeTai : listDeTaiByIdKhoa){
                                    %>
                                        <tr>
                                        	<td><%=objDeTai.getIdDeTai() %></td>
                                            <td><a href="<%=request.getContextPath()%>/admin/qldetai/chi-tiet-nghiem-thu?did=<%=objDeTai.getIdDeTai()%>"><%=objDeTai.getTenDeTai() %></a></td>
                                            <td><%=objDeTai.getFullName() %></td>
                                        	<td><%=objDeTai.getTenCapDeTai() %></td>
                                        	<td><%=LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %></td>
                                        	<td>
                                        		Điểm: <%=objDeTai.getDiem() %><br>
                                        		Xếp loại: <%=objDeTai.getXepLoai() %><br>
                                        		<a href="<%=request.getContextPath()%>/admin/qldetai/chi-tiet-nghiem-thu?did=<%=objDeTai.getIdDeTai()%>"><img src="assets/img/edit.gif" alt="" />Xem chi tiết</a>
                                        	</td>
                                        	<td><input type="checkbox" class="checkbox-action" name="idDeTai" value="<%=objDeTai.getIdDeTai() %>"></td>
                                        </tr>
                                      <%}}} %>
                                      <tr class="text-center text-danger mt-20 no-result-search"
											hidden>
											<td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
										</tr>                               
                                    </tbody>
                                </table>
								</form>
								<%	
									int page_sum = (Integer) request.getAttribute("page_sum"); 
									if(page_sum > 1){
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
											href="<%=request.getContextPath()%>/admin/qldetai/duyet_nghiem_thu_ad?type=<%=type%>&page=<%=i%>"><%=i%></a><li>
											<%
												}
												if (current_page == page_sum) {
													active = "class=\"current\"";
												} else {
													active = "";
												}
											%>
										<li><a <%=active%>
											href="<%=request.getContextPath()%>/admin/qldetai/duyet_nghiem_thu_ad?type=<%=type%>&page=<%=page_sum%>"><%=page_sum%></a></li>
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
