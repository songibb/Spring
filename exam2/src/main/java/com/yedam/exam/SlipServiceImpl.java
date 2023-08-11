package com.yedam.exam;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlipServiceImpl implements SlipService{

	@Autowired	SlipDAO dao;


	@Override
	public int insertSlip(Slip slip) {
		int result = dao.insertSlip(slip);
		return result;
	}
	
//	@Override
//	public Map<String, String> insertSlip(Slip slip) {
//		Map<String, String> map = new HashMap<>();
//		int result = dao.insertSlip(slip);
//		if(result>0) {
//			map.put("result", "true");
//		} else {
//			map.put("result", "false");
//		}
//		return map;	// 처리내용 
//	}

}
