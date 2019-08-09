<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 300px;
}
</style>
</head>
<body>
	<div class="container">
		<h2 class="text-center">Login</h2>
		<div class="row">
			<form method=post action="login_ok.jsp">
			<table class="table">
				<tr>
					<th class="text-right success" width=25%>아이디</th>
					<td class="text-left" width=75%>
						<input type=text name=id class="input-sm" size=15>
					</td>
				</tr>
				<tr>
					<th class="text-right success" width=25%>비밀번호</th>
					<td class="text-left" width=75%>
						<input type=password name=pwd class="input-sm" size=15>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit value="로그인" class="btn btn-sm btn-success">
						<input type=button value="취소" class="btn btn-sm btn-warning">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>