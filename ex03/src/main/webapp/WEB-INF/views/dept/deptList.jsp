<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 부서 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
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
				<th>Del</th>
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
		//redirect시 동작
		let msg = `${result}`;
		if(msg != null && msg != '') alert(msg);

		
		//삭제
		$('tbody > tr button[type="button"]').on('click', ajaxDeleteDept);
		
		function ajaxDeleteDept(e){
			let deptId = $(e.currentTarget).parent().siblings().eq(0).text();
			
			$.ajax({
				url : 'deptDelete',
				type : 'post',
				data : { departmentId : deptId }   //controller에서 RequestParam에 별도 이름을 지정하지 않으면, 매개변수 이름이 key값
			})
			.done( data => {
				if(data == '성공'){
					let btn = e.currentTarget;
					$(e.currentTarget).parent().parent().remove();
				} else{
					alert('삭제되지 않았습니다.');
				}
			})
			.fail( reject => console.log(reject));
			
			return false;
		}
		
	</script>
	
</body>
</html>