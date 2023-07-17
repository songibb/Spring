<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
<style>
table, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<div align="center">
		<form>
			<table>
				<tr>
					<th>번호</th>
					<td>${boardInfo.bno}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${boardInfo.title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${boardInfo.writer}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="3" cols="2" style="width:100px" readonly>${boardInfo.contents}</textarea></td>
				</tr>
				<tr>
					<th>첨부이미지</th>
					<c:choose>
						<c:when test="${ not empty boardInfo.image }">
							<!-- 이미지의 위치는 변하지 않음 -> 절대경로 사용 -->
							<!-- 
							<td><img src="${pageContext.request.contextPath}/resources/${boardInfo.image}"></td>
							 -->
							<!-- c:url 사용시 value로 들어온 값에 contextPath를 자동으로 붙여줌 -->
							<td><img src="<c:url value="/resources/${boardInfo.image}" />"></td>
						</c:when>
						<c:otherwise>
							<td>파일 없음</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>
						<fmt:formatDate value="${boardInfo.regdate}" pattern="yyyy/MM/dd"/>
					</td>
				</tr>
			</table>
			<button type="button" onclick="location.href='boardUpdate?bno=${boardInfo.bno}'">수정</button>
			<button type="button" onclick="location.href='boardDelete?bno=${boardInfo.bno}'">삭제</button>
		</form>
	</div>
</body>
</html>