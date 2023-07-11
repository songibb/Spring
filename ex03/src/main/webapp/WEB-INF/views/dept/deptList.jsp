<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 부서 조회</title>
<style type="text/css">
table, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<button type="button" onclick="location.href='deptInsert'">등록</button>
	<table>
		<thead>
			<tr>
				<th>department_id</th>
				<th>department_name</th>
				<th>manager_id</th>
				<th>location_id</th>
			</tr>
		</thead>	
		<tbody>
			<c:forEach items="${deptList}" var="dept">
				<tr onclick="location.href='deptInfo?departmentId=${dept.departmentId}'">
					<td>${dept.departmentId}</td>
					<td>${dept.departmentName}</td>
					<td>${dept.managerId}</td>
					<td>${dept.locationId}</td>
					<td><button type="button">삭제</button></td>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		printMessage(`${result}`);
		
		function printMessage(msg){
			if(msg == null || msg == '') return;
			alert(msg);
		}
	</script>
	
</body>
</html>