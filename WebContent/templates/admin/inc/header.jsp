<%@page import="library.LibraryAuth"%>
<%@page import="model.bean.User"%>
<%@page import="library. LibraryConstant"%>
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
    <div class="sidebar" data-background-color="white" data-active-color="danger">
        <div class="sidebar-wrapper" style="background: #3c4252; color: white;">
        	<%
        	User sobjUser = new User();
        	boolean isAdmin = false;
        	boolean isQuanLyNCKHKhoa = false;
        	boolean isNhanVienQLNCKHTruong = false;
    	    if(session.getAttribute("admin")!=null){//login by admin
    	         sobjUser = (User)session.getAttribute("admin");
    	         isAdmin = true;    	    	
    	    }else if(session.getAttribute("quanLyNCKHKhoa")!=null){ //login by quanLyNCKHKhoa
    	    		sobjUser = (User)session.getAttribute("quanLyNCKHKhoa");
    	    		isQuanLyNCKHKhoa = true;
    	    }else if(session.getAttribute("nhanVienQLNCKHTruong")!=null){ //login by nhanVienQLNCKHTruong
    	    		sobjUser = (User)session.getAttribute("nhanVienQLNCKHTruong");
    	    		isNhanVienQLNCKHTruong = true;
    	    }
    	    %>
            <div class="logo">
                <a href="<%=request.getContextPath()%>/admin/user/show?uid=<%=sobjUser.getIdUser() %>" class="simple-text"><img src="<%=sobjUser.getAvt() != null? request.getContextPath() + "/templates/admin/img/users/" + sobjUser.getAvt() : request.getContextPath() + "/templates/admin/img/faces/face-3.jpg"%>"
									 class="img-circle" style="display:inline-block; width:130px;height:100px">
                	 <h5> <%=sobjUser.getFullName() %> </h5> 
                </a>
            </div>


            <ul class="nav">
            	<li class="">
                    <a href="<%=request.getContextPath() %>/admin">
                        <!-- <i class="ti-user"></i> -->
                        <p>Trang quản lý</p>
                    </a>
                </li>
                
                <li class="">
                    <a href="<%=request.getContextPath() %>/admin/cat/index">
                        <!-- <i class="ti-user"></i> -->
                        <p>Quản lý danh mục</p>
                    </a>
                </li>
                
                 <li class="">
                    <a href="<%=request.getContextPath() %>/admin/user/index">
                        <!-- <i class="ti-user"></i> -->
                        <p>Quản lý tài khoản</p>
                    </a>
                </li>
                <% if(isAdmin){ %>
                 <li class="">
                    <a href="<%= request.getContextPath() %>/admin/capdetai/index">
                        <!-- <i class="ti-view-list-alt"></i> -->
                        <p>Quản lý cấp đề tài</p>
                    </a>
                </li>
                <%} %>
                <% if(isAdmin){ %>
                 <li class="">
                    <a href="<%= request.getContextPath() %>/admin/thoigiandk/index">
                     <!-- <i class="ti-view-list-alt"></i> -->
                        <p>Quản lý thời gian đăng ký</p>
                    </a>
                </li>
                <%} %>
                <li class="">
                <% if (isQuanLyNCKHKhoa) {%> 
         				<a href="<%= request.getContextPath() %>/admin/qldetai/index-khoa?type=load">
         				 
    			<%} else{ %> 
    					<a href="<%= request.getContextPath() %>/admin/qldetai/index_nhanvien?type=load">
    			<%} %>
    				<!-- <i class="ti-view-list-alt"></i> -->
             				<p>Quản lý đề tài</p>
         				</a>
               </li>
               <li class="" >
               	<%if (isQuanLyNCKHKhoa) { %>
               			<a href="<%= request.getContextPath() %>/admin/qldangkydetai/index-khoa?type=load">
               	<%} else if (isNhanVienQLNCKHTruong) {%>
               			<a href="<%= request.getContextPath() %>/admin/qldangkydetai/nhanvien/index_nhanvien">
                <%} else if (isAdmin) { %>
               			<a href="<%= request.getContextPath() %>/admin/qldangkydetai/admin/index_admin">
               	<%} %>   
                        <!-- <i class="ti-user"></i> -->
                	<p>Quản lý đăng ký đề tài</p>
                    </a>
                </li>
                <li class="">
                <% if(isAdmin || isNhanVienQLNCKHTruong){ %>
                    <a href="<%=request.getContextPath() %>/admin/general-statistical?type=load">
                <% }else if(isQuanLyNCKHKhoa){ %>
                	<a href="<%=request.getContextPath() %>/admin/faculty-statistical?type=load">
                <%} %>
                        <!-- <i class="ti-stats-up"></i> -->
                	<p>Thống kê</p>
                    </a>
                </li>
                <li class="">
                    <a href="<%=request.getContextPath() %>/admin/notif?type=load">
                        <!-- <i class="ti-announcement"></i> -->
                		<p>Thông báo</p>
                    </a>
                </li>
                
            </ul>
        </div>
    </div> 
     <div class="main-panel">
        <nav class="navbar navbar-default">
            <div class="container-fluid" style="background: #2b2842;">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/admin" style="color: #c7c7c7;">Trang quản lý</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="<%=request.getContextPath()%>/auth/admin/logout">
                                <i class="ti-settings"></i>
                                <p>Đăng Xuất</p>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>
    

    