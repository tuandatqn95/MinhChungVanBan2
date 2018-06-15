<%@page import="model.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>

<
<script type="text/javascript">
	function DoSubmit() {
		var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(
				CryptoJS.enc.Hex);
		var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(
				CryptoJS.enc.Hex);

		var aesUtil = new AesUtil(128, 1000);
		//var ciphertext = aesUtil.encrypt(salt, iv, $('#key').text(), $scope.password);
		var ciphertext = aesUtil.encrypt(salt, iv, '01234567890', document.loginform.password.value);

		var aesPassword = (iv + "::" + salt + "::" + ciphertext);
		var password = btoa(aesPassword);
		document.loginform.password.value = password;
		return true;
	}
</script>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Đăng nhập</h3>
	</div>
	<div class="panel-body">

		<%
			ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
			if (errors != null) {
		%>
		<div class="alert alert-danger">
			<%
				for (String error : errors) {
						out.println(error);
						out.println("<div class=\"clearfix\"></div>");
					}
			%>

		</div>
		<%
			}
		%>

		<form action="LoginServlet" name="loginform" method="post" onsubmit="return DoSubmit();"
			class="form-horizontal">
			<div class="form-group">
				<label class="control-label  col-md-3">Tài khoản: </label>
				<div class="col-md-7">
					<input type="email" name="email" placeholder="Email"
						class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label  col-md-3">Mật khẩu: </label>
				<div class="col-md-7">
					<input type="password" name="password" placeholder="Mật khẩu"
						class="form-control">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-offset-3 col-md-3">
					<button type="submit" class="btn btn-link pull-left">Quên
						mật khẩu</button>
				</div>

				<div class="col-md-4">
					<button type="submit" class="btn btn-primary pull-right">Đăng
						Nhập</button>
				</div>
			</div>

		</form>
	</div>
</div>



<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>

