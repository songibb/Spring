package com.yedam.exam;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SlipController {
	
	private static final Logger logger = LoggerFactory.getLogger(SlipController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@Autowired
	private SlipService slipService;
	
	// 비동기 통신을 처리하는 컨트롤러
	
	//내가 한 부분  
//	@PostMapping("insertSlipList")
//	@ResponseBody
//	public int insertSlipList(@RequestBody List<Slip> slip) {
//		int result = 0;
//		for(Slip s : slip) {
//			result = slipService.insertSlip(s);
//		}
//		return result;
//	};
	
	//컨트롤러는 중간역할 -> 코드가 많으면 안됨 -> 비즈니스로직은 serviceImpl에서 구현해야함 
	@PostMapping("insertSlipList")
	@ResponseBody    //ajax 자체가 데이터를 돌려준다는 의미이므로 반드시 데이터가 return됨 -> @ResponseBody가 필요
	public Map<String, Object> insertSlipList(@RequestBody List<Slip> slip) {   //ajax에서 json배열로 보내므로 배열로 받으니까 list로 받음
		return slipService.insertSlip(slip);
		
	}
	
}
