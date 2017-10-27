<%@page import="library.LibraryAuth"%>
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<script src="<%=request.getContextPath()%>/library/js/jquery-3.1.1.min.js"> </script>
	<script src="<%=request.getContextPath()%>/library/js/jquery.validate.min.js"> </script>
	<script src="<%=request.getContextPath()%>/library/ckeditor/ckeditor.js"> </script>
	<script src="<%=request.getContextPath() %>/ckfinder/ckfinder.js"> </script>
	<script src="<%=request.getContextPath() %>/templates/admin/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath() %>/templates/admin/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="../assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>AdminCP - VinaEnter</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath() %>/templates/admin/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/templates/admin/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/animate.min.css" rel="stylesheet"/>
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/demo.css" rel="stylesheet" />
    <!--  Paper Dashboard core CSS    -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/paper-dashboard.css" rel="stylesheet"/>
    <style type="text/css">
	        .dropdown-menu {
	          opacity: 1;
	          filter: alpha(opacity=100);
	          visibility: visible;
	}
	
	.modal-backdrop {
	  z-index: 0;
	}
	.datetimepicker {
		display:none;
	}
    </style>





    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath() %>/templates/admin/css/themify-icons.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">
   
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
                     <div class="card col-md-4 col-md-offset-4 login-div" style="background-color: #C9C69D">
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
       