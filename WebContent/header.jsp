<%@page import="model.TaiKhoan"%>
<%@page import="dao.TaiKhoanDAO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Trang chủ - Website minh chứng văn bản</title>

<!-- Bootstrap Core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./css/style.css" rel="stylesheet">

<!-- jQuery -->
<script src="./js/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="./js/bootstrap.min.js"></script>
<script src="./js/AesUtil.js"></script>
<script src="./js/aes.js"></script>
<script src="./js/pbkdf2.js"></script>
<%
	response.setHeader("X-Frame-Options", "DENY");
	response.setHeader("X-XSS-Protection", "1");
	response.setHeader("X-Content-Type-Options", "nosniff");
%>

</head>

<body>
	<!-- Header -->
	<header>

		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<img class="logo img-responsive" src="images/logo.png">
				</div>

			</div>
		</div>
	</header>

	<!-- /Header -->

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<!-- Nav -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#menu">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<!-- Nav collapse -->
			<div class="collapse navbar-collapse" id="menu">
				<ul class="nav navbar-nav">
					<li class="active"><a href="./"><span
							class="glyphicon glyphicon-home"></span> Trang chủ</a></li>
					<li class=""><a href="tracuu.jsp"><span
							class="glyphicon glyphicon-file"></span> Minh chứng</a></li>
					<li class=""><a href="contact.jsp"><span
							class="glyphicon glyphicon-envelope"></span> Liên Hệ</a></li>
				</ul>

				<div class="navbar-right">
					<%
						TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
						String userEmail = null, userPass = null;
						TaiKhoan taiKhoan = null;
						Cookie[] cookies = request.getCookies();
						if (cookies != null) {
							for (Cookie cookie : cookies) {
								if (cookie.getName().equals("passUser")) {
									userPass = cookie.getValue();
								}

								if (cookie.getName().equals("loginUser")) {
									userEmail = cookie.getValue();
								}
							}
						}

						if ((userPass != null) && (taiKhoanDAO.checkUser(userEmail, userPass))) {
							taiKhoan = taiKhoanDAO.GetTaiKhoanByEmail(userEmail);
					%>
					<a href="profile.jsp" class="nounderline"><img
						src="<%=taiKhoan.getAnhDaiDien() == null ? "images/default-avatar.png"
						: "uploads/" + taiKhoan.getAnhDaiDien()%>"
						class="avatar img-thumbnail" width="32px">
						<button class="btn btn-link navbar-btn">
							<strong><%=userEmail%></strong>
						</button> </a>
					<%
						if ((taiKhoan != null) && (taiKhoan.getIDLoaiTK() > 1)) {
					%><a href="admin" class="btn btn-link navbar-btn">Manage</a>
					<%
						}
					%>
					<a href="logout.jsp" class="btn btn-link navbar-btn">Đăng xuất</a>

					<%
						} else {
					%>

					<a href="login.jsp" class="btn btn-info navbar-btn">Đăng nhập</a>
					<%
						}
					%>
				</div>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- /Navigation -->
	<div class="container">
		<div class="row">
			<div class="col-md-8">