package com.yedam.exam;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlipServiceImpl implements SlipService{

	@Autowired	SlipDAO dao;

//내가 한 부분
//	@Override
//	public int insertSlip(Slip slip) {
//		int result = dao.insertSlip(slip);
//		return result;
//	}
	
	
	@Override
	public Map<String, Object> insertSlip(List<Slip> slipList) {
		//통신을 할때는 Object가 값 자체만 json으로 알아서 변환하므로 Object타입 사용해도 무관 
		
		Map<String, Object> message = new HashMap<>();
		Boolean result = false;
		
		int count = 0;
		List<String> empList = new ArrayList<>();
		
		for(Slip s : slipList) {
			if(s.getSlipAmount() <= 20000) {
				//전표금액이 20000이하인 경우는 20000으로 처리
				s.setSlipAmount(20000);
				
				//20000 이하인 사원 return 위한 리스트
				String empInfo = s.getCustomer().substring(0,3);
				empList.add(empInfo);
			}
			
			//실행한 횟수를 누적시킴
			count += dao.insertSlip(s);
		}	

		if(count>0) {
			result = true;
		}
		
		message.put("result", result);
		message.put("total", count);
		message.put("empList", empList);
		
		return message;	// 처리내용 
	}


}
