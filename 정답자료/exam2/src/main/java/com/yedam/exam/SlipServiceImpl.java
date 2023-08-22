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
	
	@Override
	public Map<String, Object> insertSlip(List<Slip> slipList) {
		
		Map<String, Object> message = new HashMap<>();
		Boolean result = false;
		
		int count = 0;
		List<String> empList = new ArrayList<>();
				
		for(Slip slip : slipList) {
			if(slip.getSlipAmount() <= 20000){
				slip.setSlipAmount(20000);
				
				String empInfo = slip.getCustomer().substring(0, 3);
				empList.add(empInfo);
			}
			count += dao.insertSlip(slip);
		}
		
		if(count > 0) {
			result = true;
		}
		
		message.put("result", result);
		message.put("total", count);
		message.put("empList", empList);
		
		return message; 
	}

}
