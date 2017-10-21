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
                 <div class="col-lg-12 col-md-12">
                    <div class="card">
                       <div class="header">
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">LOGIN</h4>
                        </div>
                        
                        <div class="content">
                        
                           <form id="formCat" action="<%=request.getContextPath() %>/auth/admin/login" method="post">
                                
		                             <div class="row">
		                             
		                               <div class="col-md-12" >
		                                 <div class="form-group" style="text-align: center;">
		                                     <input type="text" name="username" class="form-control border-input" placeholder="Username" value="">
		                                  </div>
		                               </div>
		                                 
		                             </div>
		                             
		                             <div class="row">
		                             
		                               <div class="col-md-12" >
		                                 <div class="form-group" style="text-align: center;">
		                                     <input type="password" name="password" class="form-control border-input" placeholder="Password" value="">
		                                  </div>
		                               </div>
		                                 
		                             </div>
		                             
		                             
                                    <div class="text-center">
                                        <input type="submit" class="btn btn-info btn-fill btn-wd" value="Login" />
                                    </div>
                                    <div class="clearfix"></div>
                               </form>
                               
                            </div>
                        
                        
                        
                    </div>
            	</div>
        	</div>
                          

  <%@include file="/templates/admin/inc/footer.jsp" %>
       