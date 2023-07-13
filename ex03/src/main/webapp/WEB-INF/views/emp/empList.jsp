<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<style type="text/css">
table, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<button type="button" onclick="location.href='empInsert'">등록</button>
	<table>
		<thead> 
			<tr>
				<th>employee_id</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>hire_date</th>
				<th>job_id</th>
				<th>salary</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empList}" var="emp">
				<tr onclick="location.href='empInfo?employeeId=${emp.employeeId}'">
					<td>${emp.employeeId}</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>
						<fmt:formatDate value="${emp.hireDate}" pattern="yyyy년 MM월 dd일"/>		
					</td>
					<td>${emp.jobId}</td>
					<td>
						<fmt:formatNumber value="${emp.salary}" pattern="$#,###"/>
					</td>
					<td><button type="button">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		//redirect시 동작		
		printMessage(`${result}`);      //자바스크립트에서 el태그 사용시 ``안에 쓸 것
		
		function printMessage(msg){
			if(msg == null || msg == '') return;
			alert(msg);
		}
		
		
		//삭제
		$('button:contains("삭제")').on('click', ajaxDeleteEmp);     //contains -> 제이쿼리에서만 사용가능
		 
		function ajaxDeleteEmp(e){
			let empId = e.currentTarget.closest('tr').firstElementChild.textContent;
			
			$.ajax({
				url : 'empDelete',
				type : 'post',
				data : { id : empId }    //json타입을 제외하고는 무조건 객체타입 -> controller의 RequestParam에 name속성으로 이름을 지정했을시 그 이름이 key값
			})
			.done( data => {
				if(data == 'Success'){
					//삭제시 reload하지 않고 해당 태그 삭제하는 방법
					let btn = e.currentTarget;
					$(btn).closest('tr').remove();
				} else{
					alert('삭제되지 않았습니다.');
				}
			})
			.fail( reject => console.log(reject));
			
			//button -> 이벤트 버블링 막아야함
			//e.stopPropagation();   
			return false;   //제이쿼리에서는 return false사용 가능
		}
		
	</script>
</body>
</html>