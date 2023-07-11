<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단건 부서 조회</title>
</head>
<body>
	<form action="">
		<div>
			<label><input type="number" name="" value="${deptInfo.departmentId}"></label>
		</div>
		<div>
			<label><input type="text" name="" value="${deptInfo.departmentName}"></label>
		</div>
		<div>
			<label><input type="number" name="" value="${deptInfo.managerId}"></label>
		</div>
		<div>
			<label><input type="number" name="" value="${deptInfo.locationId}"></label>
		</div>
		<button type="submit">수정</button>
		<button type="button">취소</button>
	</form>
</body>
</html>