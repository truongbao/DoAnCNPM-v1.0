<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   
   <script type="text/javascript">
   $(document).ready(function(){
			$("#frmLogin").validate({
				rules:{
					"username":{
						required: true,
						minlength: 6,
						maxlength: 32
					},
					"matKhau":{
						required: true,
						minlength: 6,
						maxlength: 20
					}
				},
				messages:{
					"username":{
						required: "<p> <span style='color:red' > Vui lòng nhập vào username</span> </p>",
						minlength:"<p><span style='color:red' >   username phải có ít nhất 6 ký tự  </span></p>",
						maxlength: "<p> <span style='color:red' >  username có nhiều nhất 32 ký tự  </span></p>"
					},
					"matKhau":{
						required: "<p> <span style='color:red' > Vui lòng nhập vào password </span> </p>",
						minlength:"<p><span style='color:red' > password phải có ít nhất 6 ký tự  </span></p>",
						maxlength: "<p> <span style='color:red' >  password có nhiều nhất 15 ký tự  </span></p>"
					}
				}
			});// ham tren
		});
		</script>
    

        <div class="content">
           <div class="container-fluid">
              <div class="row">
                 <div class="col-lg-12 col-md-12">
                     <div class="card col-md-8 col-md-offset-2" style="background-color: #C9C69D">
                       <div class="header">
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">LOGIN</h4>
                        </div>
                        
                        <div class="content">
                        
                           <form id="frmLogin" action="<%=request.getContextPath() %>/auth/admin/login" method="post">
                                
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
		                                     <input type="password" name="matKhau" class="form-control border-input" placeholder="Password" value="">
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
                          
 <div style="height: 100px"></div>
  <%@include file="/templates/admin/inc/footer.jsp" %>
       