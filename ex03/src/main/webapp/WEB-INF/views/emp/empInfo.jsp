<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	
	<!-- <form action="empUpdate" method="post"> -->
	<!-- form태그의 submit : 동기식으로 요청하는 것 -> json으로 통신 불가 -->
	<!-- 따라서 form태그는 비동기 통신에 많이 사용하지만 내부에 입력값을 하나로 묶어줄 때도 사용-->
	<form> 
		<!-- input태그의 name속성은 사원 정보 수정시에 server에서 값을 받을때 key값으로 이용, 단건 조회시에는 사실상 필요없음 -->
		<div>
			<!-- 수정되면 안되는 부분은 반드시 readonly지정 -->
			<label>id : <input type="number" name="employeeId" value="${empInfo.employeeId}" readonly></label> 
		</div>
		<div>
			<label>first_name : <input type="text" name="firstName" value="${empInfo.firstName}"></label>
		</div>
		<div>
			<label>last_name : <input type="text" name="lastName" value="${empInfo.lastName}"></label>
		</div>
		<div>
			<label>email : <input type="email" name="email" value="${empInfo.email}"></label>
		</div>
		<div>
			<label>job_id : <input type="text" name="jobId" value="${empInfo.jobId}"></label>
		</div>
		<div>
			<label>salary : <input type="number" name="salary" value="${empInfo.salary}"></label>
		</div>
		<button type="submit">수정</button> 		
		<button type="button" onclick="location.href='deptList'">취소</button>
	</form>
	<script>
		//수정 (제이쿼리 메소드 이용)
		//1) form태그의 submit 이벤트 동작하면 안됨 (ajax 이용할 것이기 때문) -> button에 클릭이벤트를 거는 것이 아님, submit이 막혀야함
		$('form').on('submit', ajaxUpdateEmp);
		
		//2) form태그 내부의 정보를 가져와야 함
		function serializeObject(){
			let formData = $('form').serializeArray();  
			//serializeArray() -> form내의 input태그 데이터를 객체 배열로 만듦  -> 객체 하나가 input태그 하나
			
			//객체 배열을 객체로 만듦
			let objectData = {};
			$.each(formData, function(idx, obj){
				objectData[obj.name] = obj.value;
			});
			
			return objectData;
		}
		//3) ajax를 이용해 통신을 요청해야 함
		function ajaxUpdateEmp(e){
			e.preventDefault();
			//제이쿼리에서는 return false 도 가능 -> preventDefault(), stopPropagation() 같이 적용됨
			
			let obj = serializeObject();
			
			$.ajax({
				url : 'empUpdate',
				type : 'post',
				contentType : 'application/json',
				data :  JSON.stringify(obj)
			})
			.done(data => { 
				if(data != null && data['결과'] == 'Success'){   //데이터의 key가 한글이라면 반드시 대괄호[''] 사용해야함
					alert('사원번호 : ' + data['사원번호'] + '의 정보가 수정되었습니다.');
				} else{
					alert('해당 사원의 정보가 정상적으로 수정되지 않았습니다.');
					//수정은 딱히 reload할 필요가 없음
				}   	
			})
			.fail(reject => console.log(reject));
		}
	</script>
</body>
</html>