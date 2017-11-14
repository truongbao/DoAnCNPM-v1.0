<%@page import="java.util.Calendar"%>
<%@page import="model.bean.CapDeTai"%>
<%@page import="model.dao.CapDeTaiDAO"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   
   <script type="text/javascript">
			     
			</script>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
	                            <h4 class="title" style="display: inline-block;width: 40%;">Danh sách cấp đề tài</h4>
	                        	<form action="<%=request.getContextPath() %>/admin/capdetai/add" method="post" style="display: inline-block;margin-left: 17%; width: 40%">
	                        		<div  style="width: 75% ; display: inline-block; margin-right: 4%" class="form-group">
	                        			<input name="name" type="text" placeholder="Nhập tên cấp đề tài cần thêm" class="form-control" style="border: 1px solid;background: #CB9E65;">
	                        		</div>
	                        		<button type="submit" class="btn btn-info">Thêm</button>
                            		
	                        	</form>
                                                               
                               <div class="text-center text-danger col-md-12" style="font-size: 18px;font-weight: bold;">
                            	 <%
							      if(request.getParameter("msg")!=null){
							          int msg = Integer.parseInt( request.getParameter("msg") );
							          switch(msg){
							            case 1: out.print("Xử lý thành công !!");break;
							            case 0: out.print("Không thành công vui lòng thử lại !!");break;
							            case 3: out.print("Tên cấp đề tài đã tồn tại !!");break;
							            case 4: out.print("Cấp đề tài không tồn tại !!");break;
								        }
							      }
		     			       	%>  
                            	</div>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped" id="table-contain">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên đề tài</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                    <% 
                                    if(request.getAttribute("listCapDeTai")!=null){
		                            	ArrayList<CapDeTai> listCapDeTai = (ArrayList<CapDeTai>)request.getAttribute("listCapDeTai");
                                    	for(int i = 0; i < listCapDeTai.size(); i++) {
                                    %>
                                        <tr id="<%= listCapDeTai.get(i).getIdCapDeTai() %>">
                                        	<td><%= listCapDeTai.get(i).getIdCapDeTai() %></td>
                                        	<td><%=listCapDeTai.get(i).getTenCapDeTai() %></td>
                                        	<td>
                                                <a class="edit-capdetai" 
                                                	data-idcdt="<%= listCapDeTai.get(i).getIdCapDeTai() %>"
                                                	data-namecdt="<%= listCapDeTai.get(i).getTenCapDeTai() %>"
                                                	data-url="<%=request.getContextPath() %>/admin/capdetai/edit?uid=<%=listCapDeTai.get(i).getIdCapDeTai() %>"
                                                	><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /></a> &nbsp;||&nbsp;
                                                <form id="form-xoa" method="post" class='<%=listCapDeTai.get(i).getTenCapDeTai().toLowerCase().equals("trường")?"disabled":"" %>' action="<%=request.getContextPath() %>/admin/capdetai/del?uid=<%=listCapDeTai.get(i).getIdCapDeTai() %>" style="display: inline" >
                                                	
						                           <a href="" class='link-xoa'
						                              data-title="Confirm deletion!"
						                              data-confirm="Are you sure you want to delete?">
						                              <img src="<%= request.getContextPath() %>/templates/admin/img/del.gif" alt="" />
						                            </a>
                         						 </form> 
                                            </td>
                                        </tr>
                    			<% } 
                    			}%>
                                        <tr class="text-center text-danger mt-20 no-result-search" hidden>
                                             <td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
                                        </tr>
                                    </tbody>
 
                                </table>

								<div class="text-center">
								    <ul class="pagination">
								       <li>
								    	<%
											int sumPage = (Integer) request.getAttribute("sumPage");
										    int current_page = (Integer) request.getAttribute("current_page");
										    int pageFirst = 0;
										    int pageEnd = 0;
										    int numFix = 5;
										    int move = (int)Math.ceil( (float)numFix / 2);
										    //nếu current_page > 1 và sumPage > 1 thì thêm nút back
										    if(current_page > 1 && sumPage > 1){
										 %>  
										   <a href="<%=request.getContextPath() %>/admin/capdetai/index?page=<%=current_page-1%>">Back</a> 
										 <%} %>  	
										    	
										    	
										 <%
										     //fix lại trang đầu và cuối
										     if(current_page >=numFix){
										    	 pageFirst = current_page-move;
										    	 if(sumPage > (current_page+move) ){
										    		 pageEnd = current_page+move;
										    	 }else if(current_page < sumPage && current_page > (sumPage-(numFix-1) ) ){
										    		 pageFirst = sumPage-(numFix-1);
										    		 pageEnd = sumPage;
										    	 }else{
										    		 pageEnd = sumPage;
										    	 }
										     }else{
												pageFirst=1;
												if(sumPage > numFix){
													pageEnd = numFix;
												}else{
													pageEnd = sumPage;
												}
										     }
										    //lặp khoản giữa và active trang người dùng click
										    String active="";
											for (int i = pageFirst; i <= pageEnd; i++){
												if(current_page==i){
													active=" style='color :red; font-weight: bold' ";
												}else{
													active="";
												}
										 
										 %>   	
										   <a <%=active %> href="<%=request.getContextPath() %>/admin/capdetai/index?page=<%=i%>"><%=i %></a>
										     	
										   <%}//for %> 	
										    	
										
										  <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage > 1){
										  %>
										  
										 	 <a href="<%=request.getContextPath() %>/admin/capdetai/index?page=<%=current_page+1%>">Next</a> 
										 	
										  <%} %>
								        
								        </li> 
								    </ul>
								</div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
                          

   <%@include file="/templates/admin/inc/modal.jsp" %>
  <%@include file="/templates/admin/inc/footer.jsp" %>
       