<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록 조회</title>
<style>
table, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<h3>도서 목록</h3>
	<table>
		<thead>
			<tr>
				<th>도서번호</th>
				<th>도서명</th>
				<th>표지</th>
				<th>출판일자</th>
				<th>금액</th>
				<th>출판사</th>
				<th>도서소개</th>
			</tr>
		</thead>		
		<tbody>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td>${book.bookNo}</td>
					<td>${book.bookName}</td>					
					<c:choose>
						<c:when test="${ not empty book.bookCoverimg }">
							<td><img src="<c:url value="/resources/images/${book.bookCoverimg}" />"></td>
						</c:when>
						<c:otherwise>
							<td>파일 없음</td>
						</c:otherwise>
					</c:choose>	
					<td>
						<fmt:formatDate value="${book.bookDate}" pattern="yyyy/MM/dd"/>
					</td>
					<td>
						<fmt:formatNumber value="${book.bookPrice}" maxFractionDigits="3"/></td>
					<td>${book.bookPublisher}</td>
					<td>${book.bookInfo}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>