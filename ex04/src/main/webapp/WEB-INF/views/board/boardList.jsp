<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style>
table, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<div align="center">
		<button type="button" onclick="location.href='boardInsert'">등록</button>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardList}" var="board">
					<tr onclick="location.href='boardInfo?bno=${board.bno}'">
						<td>${board.bno}</td>
						<td>${board.title}</td>
						<td>${board.writer}</td>
						<td>
							<fmt:formatDate value="${board.regdate}" pattern="yyyy년MM월dd일"/>  <!-- MM(날짜)과 mm(시간)은 구분해줘야함 -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>