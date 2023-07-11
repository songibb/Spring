<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원조회</title>
</head>
<body>
	<form action="">
		<div>
			<!-- input태그의 name속성은 사원 정보 수정시에 server에서 값을 받을때 key값으로 이용, 단건 조회시에는 사실상 필요없음 -->
			<label>id : <input type="number" name="" value="${empInfo.employeeId}"></label>
		</div>
		<div>
			<label>first_name : <input type="text" name="" value="${empInfo.firstName}"></label>
		</div>
		<div>
			<label>last_name : <input type="text" name="" value="${empInfo.lastName}"></label>
		</div>
		<div>
			<label>email : <input type="email" name="" value="${empInfo.email}"></label>
		</div>
		<div>
			<label>job_id : <input type="text" name="" value="${empInfo.jobId}"></label>
		</div>
		<div>
			<label>salary : <input type="number" name="" value="${empInfo.salary}"></label>
		</div>
		<button type="submit">수정</button> 
		<!-- submit : 동기식으로 요청하는 것 -> json으로 사용 불가 -->
		<button type="button">취소</button>
	</form>
</body>
</html>