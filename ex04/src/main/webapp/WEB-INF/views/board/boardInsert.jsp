<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<style>
table, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<div align="center">
		<form name="insertForm" action="boardInsert" method="post">
			<div>
				<h3>게시글 작성</h3>
			</div>
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="contents"></textarea></td>
				</tr>
				<tr>
					<th>첨부이미지</th>
					<td><input type="text" name="image"></td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td><input type="text" name="regdate"></td>
				</tr>
			</table>
			<button type="submit">등록</button>
			<button type="reset">취소</button>
		</form>
	</div>	
	
	<script>
		document.querySelector('form[name="insertForm"]')
				.addEventListener('submit', function(e){
					e.preventDefault();
					
					let title = document.getElementsByName('title')[0];
					let writer = document.getElementsByName('writer')[0];
					
					//title, writer : not null-> 필수로 작성해야함 -> if문 사용
					if(title.value == ''){
						alert('제목이 입력되지 않았습니다');
						title.focus();
						return;
					}
					if(writer.value == ''){
						alert('작성자가 입력되지 않았습니다');
						writer.focus();
						return;
					}
					
					//if문을 지났다면 submit 
					//form태그의 경우, name이 중복되지 않는다면 name속성을 이용해서 별도의 selector 사용하지 않고 호출 가능
					insertForm.submit();
				});
		
	</script>
</body>
</html>