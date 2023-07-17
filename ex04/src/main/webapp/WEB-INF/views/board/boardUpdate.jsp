<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form name="updateForm">
			<table>
				<tr>
					<th>번호</th>
					<td><input type="text" name="bno" value="${boardInfo.bno}" readonly></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="${boardInfo.title}"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="${boardInfo.writer}" readonly></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="contents" rows="3" cols="2" style="width:100px">${boardInfo.contents}</textarea></td>
				</tr>
				<tr>
					<th>첨부이미지</th>
					<td><input type="text" name="image" value="${boardInfo.image}"></td>
				</tr>
				<tr>
					<th>수정일자</th>
					<td>
						<!-- input 태그 value에 fmt:formatDate이용해서 포맷을 yyyy-MM-dd로 바꿔주기 -->
						<input type="date" name="updatedate" 
						value="<fmt:formatDate value="${boardInfo.updatedate}" pattern="yyyy-MM-dd"/>">
					</td>
				</tr>
			</table>
			<button type="submit">수정</button>
			<button type="button" onclick="location.href='boardInfo?bno=${boardInfo.bno}'">취소</button>
		</form>
	</div>
	
	<script>
		//제이쿼리 CDN은 layout.jsp에 한번에 모아놓음
		
		$('form').on('submit', ajaxBoardUpdate);
		
		function serializeObject(){
			let formData = $('form').serializeArray();
			
			let objectData = {};
			$.each(formData, function(idx, obj){
				objectData[obj.name] = obj.value;
			})
			
			return objectData;
		}
		
		function ajaxBoardUpdate(e){
			e.preventDefault();
			
			let updateObj = serializeObject();
			
			$.ajax({
				url : 'boardUpdate',
				type : 'post',
				data : updateObj
			})
			.done(data => {
				if(data.result){
					let message = '수정되었습니다.\n게시글 번호 : ' + data.boardInfo.bno;
					alert(message);
				} else{
					alert('수정되지 않았습니다.\n정보를 확인해주세요.');
				}
			})
			.fail(reject => console.log(reject));
		}		
		
// 교수님 방법		
// 		$('form').on('submit', function(e){			
// 			let objData = serializeObject();
			
// 			$.ajax({
// 				url : 'boardUpdate',
// 				method : 'post',
// 				data : objData
// 			})
// 			.done(data => {
// 				console.log(data);
// 			})
// 			.fail(reject => console.log(reject));
			
// 			return false;
// 		});
	
// 		function serializeObject(){
// 			let formData = $('form').serializeArray();
			
// 			let formObject = {};
// 			$.each(formData, function(idx, obj){
// 				let field = obj.name;
// 				let val = obj.value;
// 				formObject[field] = val;
// 			});
			
// 			return formObject;
// 		}
	</script>
</body>
</html>