<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   
   <script type="text/javascript">
			     $(document).ready(function(){
						$("#frmUser").validate({
							rules:{
								"username":{
									required: true,
									minlength: 6,
									maxlength: 32
								},
								"password":{
									required: true,
									minlength: 6,
									maxlength: 15
								},
								"fullname":{
									required: true,
									minlength: 6,
									maxlength: 32
								},
								"email":{
									 required:true,
									  email:true
									
								}
							},
							messages:{
								"username":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào username</span> </p>",
									minlength:"<p><span style='color:red' >   username phải có ít nhất 6 ký tự  </span></p>",
									maxlength: "<p> <span style='color:red' >  username có nhiều nhất 32 ký tự  </span></p>"
								},
								"password":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào password </span> </p>",
									minlength:"<p><span style='color:red' > password phải có ít nhất 6 ký tự  </span></p>",
									maxlength: "<p> <span style='color:red' >  password có nhiều nhất 15 ký tự  </span></p>"
								},
								"fullname":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào fullname</span> </p>",
									minlength:"<p><span style='color:red' >  fullname phải có ít nhất 6 ký tự  </span></p>",
									maxlength: "<p> <span style='color:red' >  fullname có nhiều nhất 32 ký tự  </span></p>"
								},
								"email":{
								    required:"<p style='color:red'> Vui lòng nhập email </p>",	
			                        email: "<p style='color:red'> Vui lòng nhập đúng định dạng email : ai_do@example.com </p> "	
								}
							}
						});// ham tren
			     });
			</script>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                           <div class="header">
                  <h4 class="title">Quản lý danh mục</h4>
                  
                 
                  <a href="<%=request.getContextPath() %>/admin/cat/add" class="addtop">
                       <img src="<%=request.getContextPath() %>/templates/admin/img/add.png" alt="" />Thêm Danh Mục
			     </a>
                         
         <%
	        if(request.getParameter("msg")!=null){
	          int msg = Integer.parseInt( request.getParameter("msg") );
	          switch(msg){
	            case 1: out.print("<h4 style='color : white ;border: 1px solid;padding: 4px 6px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold'>Thêm thành công !!</h4> ");break;
	            case 2: out.print("<h4 style='color : white ;border: 1px solid;padding: 4px 6px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold'>Sửa thành công !!</h4> ");break;
	            case 3: out.print("<h4 style='color : white ;border: 1px solid;padding: 4px 6px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold'>Xóa thành công !!</h4> ");break;
	            case 0: out.print("<h4 style='color :red'>That Bai !!</h4> ");break;
	           	     }
	             }
	      %>       
                     </div>
                     
                      <%
                        CatDAO objDAO = new CatDAO() ;
                     
                     %>
                     
                             <div class="content table-responsive table-full-width">
                                <table class="table table-striped" width="100%">
                                    <thead>
                                        <th align="center"  width="10%">ID</th>
                                    	<th align="center"  width="30%">Tên Danh Mục</th>
                                    	<!-- <th align="center" width="8%">Trạng thái</th> -->
                                    	<th align="center"  width="20%">Chức năng</th>
                                    </thead>
                                    <tbody>
                                       <%
					                        if(request.getAttribute("listCatParent")!=null){
					                            ArrayList<Cat> listCatParent = (ArrayList<Cat>)request.getAttribute("listCatParent");
					                              if(listCatParent.size() > 0){
					                            	   for(Cat objCatParent : listCatParent){
					                            		   
					                     %>
					                      
                                        <tr>
                                           
                                        	<td><%=objCatParent.getIdCat() %></td>
                                        	
                                        	<td><%=objCatParent.getNameCat() %></td>
                                        	
                                        	<%-- <td align="center"><%=objCatParent.getActive_cat() %></td> --%>
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/cat/edit?cid=<%=objCatParent.getIdCat()%>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        		<a href="<%=request.getContextPath()%>/admin/cat/del?cid=<%=objCatParent.getIdCat()%>" onclick="return confirm('Ban thực muốn xóa không')" ><img src="<%=request.getContextPath() %>/templates/admin/img/del.gif" alt="" /> Xóa</a>
                                        	</td>
                                        </tr>
                                        
                                        
                                         <%
 									        if(objDAO.getItemsByChild( objCatParent.getIdCat() ).size() > 0 ){
 										       for(Cat c : objDAO.getItemsByChild(objCatParent.getIdCat() ) ){
 												
                                         %>
                                        <tr>
                                           
                                        	<td><%=c.getIdCat()%></td>
                                        	
                                        	<td>&nbsp;&nbsp;&nbsp;&nbsp; |---> <%=c.getNameCat() %></td>
                                        	
                                        	<%-- <td align="center"><%=c.getActive_cat() %></td> --%>
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/cat/edit?cid=<%=c.getIdCat()%>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        		<a href="<%=request.getContextPath()%>/admin/deleteCat?cid=<%=c.getIdCat()%>" onclick="return confirm('Ban thực sự muốn xóa không')" ><img src="<%=request.getContextPath() %>/templates/admin/img/del.gif" alt="" /> Xóa</a>
                                        	</td>
                                        </tr>
                                        
                                        
                                        <%}} %>
                                        
                                        
                                        
                                       <%}}} %>
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
										   <a href="<%=request.getContextPath() %>/admin/cat/index?page=<%=current_page-1%>">Back</a> 
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
										   <a <%=active %> href="<%=request.getContextPath() %>/admin/cat/index?page=<%=i%>"><%=i %></a>
										     	
										   <%}//for %> 	
										    	
										
										  <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage > 1){
										  %>
										  
										 	 <a href="<%=request.getContextPath() %>/admin/cat/index?page=<%=current_page+1%>">Next</a> 
										 	
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
                          

  <%@include file="/templates/admin/inc/footer.jsp" %>
       