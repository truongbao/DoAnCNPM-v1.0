<%@page import="model.bean.CapDeTai"%>
<%@page import="library.LibraryConstant"%>
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
                                <!-- <p class="category success">Thêm thành công</p> -->
                                <form action="<%=request.getContextPath()%>/admin/qldangkydetai/index-khoa?type=search" method="post">
                                	<div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <input type="text" name="keyword" class="form-control border-input" value=""  placeholder="Nhập tên đề tài, tên chủ nhiệm cần tìm kiếm">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                        	<div class="form-group">
		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-primary btn-search" />
		                                        <input type="submit" name="cancel" value="Hủy tìm kiếm" class="btn btn-primary btn-cancel-search" onclick="window.location.href('<%=request.getContextPath()%>/admin/qldangkydetai/index-khoa?type=load')" />
	                                        </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                	<div class="col-md-3">
                                		<a class="btn btn-info btn-fill btn-wd"  href="<%=request.getContextPath()%>/admin/qldangkydetai/duyet-de-xuat-khoa?type=load">Xem danh sách duyệt đề xuất</a>
	                            	</div>
	                            	<div class="col-md-3">
										<button class="btn btn-info btn-fill btn-wd" onclick="showModal()">
											Xuất danh sách đề xuất</button>
									</div>
                                </div>
                                <div class="row">
                                	
                                </div>
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
	                            <div class="row text-center"> 
	                            		<div class=""><h3>DANH SÁCH ĐỀ TÀI ĐĂNG KÝ</h3></div>
	                            		
	                            </div>
                                <table class="table table-striped" id="table-contain">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên đề tài</th>
                                    	<th>Chủ nhiệm</th>
                                    	<th>Cấp đề tài</th>
                                    	<th>Trạng thái</th>
                                    </thead>
                                    <tbody>
                                    <%
                                       if(request.getAttribute("listDeTaiByIdKhoa") != null) {
                                    	   ArrayList<DeTai> listDeTaiByIdKhoa = (ArrayList<DeTai>)request.getAttribute("listDeTaiByIdKhoa");
                                    	   if (listDeTaiByIdKhoa.size() > 0) {
                                    		   for (DeTai objDeTai : listDeTaiByIdKhoa){
                                    %>
                                        <tr>
                                        	<td><%=objDeTai.getIdDeTai() %></td>
                                            <td><a href="<%=request.getContextPath()%>/admin/qldangkydetai/detail_duyet_dx_khoa?did=<%=objDeTai.getIdDeTai()%>"><%=objDeTai.getTenDeTai() %></a></td>
                                            <td><%=objDeTai.getFullName() %></td>
                                        	<td><%=objDeTai.getTenCapDeTai() %></td>
                                        	<td><%=LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %></td>
                                        </tr>    
                                      <%}}} %>
                                      <tr class="text-center text-danger mt-20 no-result-search" hidden>
                                             <td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
                                        </tr>                                 
                                    </tbody>
                                </table>
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
											href="<%=request.getContextPath()%>/admin/qldangkydetai/index-khoa?type=<%=type%>&page=<%=i%>"><%=i%></a><li>
											<%
												}
												if (current_page == page_sum) {
													active = "class=\"current\"";
												} else {
													active = "";
												}
											%>
										<li><a <%=active%>
											href="<%=request.getContextPath()%>/admin/qldangkydetai/index-khoa?type=<%=type%>&page=<%=page_sum%>"><%=page_sum%></a></li>
									</ul>
								</div>
								<%} %>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
		<div id="myModal" class="modal" >
                       	<!-- Modal content -->
			<div class="modal-content" style="width: 40%;">
				<div class="modal-header">
					<span class="close">&times;</span>
					<h4>Xuất danh sách đề tài đăng ký</h4>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/admin/qldangkydetai/khoa/export" method="post" name="formNotif" target="_blank">
                     	<div class="row">
                             <div class="col-md-12 text-center">
                             	Danh sách theo cấp đề tài:
                                 <div class="form-group" style="display: inline-block;">
                                    <select name="capDeTai" class="form-control border-input" >
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
                                	<input type="submit" name="submit" value="Xuất" class="btn btn-default" />
                             	</div>
                             </div>
                         </div>
                     </form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function showModal() {
				var modal = document.getElementById('myModal');
				var span = document.getElementsByClassName("close")[0];
				modal.style.display = "block";
				// When the user clicks on <span> (x), close the modal
				span.onclick = function() {
				    modal.style.display = "none";
				}

				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
				    if (event.target == modal) {
				        modal.style.display = "none";
				    }
				}
			}
		</script>
       <%@include file="/templates/admin/inc/footer.jsp" %>
