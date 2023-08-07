<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 방법3) 메타태그에 트큰 넣기 -->
<sec:csrfMetaTags/>
<title>top</title>
</head>
<body>
	<h1>톱 페이지입니다.</h1>
	<ul>
		<li><a href="user/user.jsp">일반 사용자용 페이지로</a></li>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li><a href="admin/admin.jsp">관리자 전용 페이지로</a></li>
		</sec:authorize>
	</ul>
	
	<!-- 보여주거나 보여주지말아야 할 부분을 sec:authorize로 감싸기 -->
	<!-- 그 권한이 없으면 해당 태그는 생성되지 않음 -->
	<sec:authorize access="isAuthenticated()">
		<form action="logout" method="post">
			<!-- 방법1) 페이지를 생성할때마다 토큰 생성하도록 해야함. form사용시 내부에 csftInput태그를 사용 -->
			<sec:csrfInput/>
			<button>로그아웃</button>
		</form>
	</sec:authorize>
	
	<!-- 방법2) form 태그 사용안할 시 input 태그 따로 기입. name은 지정가능 -->
	<input type="hidden" name="csrf_name" value="${_csrf.parameterName}">
	<input type="hidden" name="csrf_value" value="${_csrf.token}">
</body>
</html>