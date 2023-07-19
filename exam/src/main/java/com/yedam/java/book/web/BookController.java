package com.yedam.java.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.java.book.service.BookService;
import com.yedam.java.book.service.BookVO;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	//도서 정보 등록 - form
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		int bookNo = bookService.selectBookNo();
		model.addAttribute("bookNo", bookNo);
		return "book/bookInsert";
	}
	
	//도서 정보 등록 - 처리
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO, RedirectAttributes rtt) {
		int result = bookService.insertBookInfo(bookVO);
		String message = null;
		if(result == -1) {
			message = "도서가 등록되지 않았습니다.";
		} else {
			message = "도서등록이 완료 되었습니다.";
		}
		
		rtt.addAttribute("message", message);
		return "redirect:bookList";
	}
	
	//도서 목록 조회
	@GetMapping("bookList")
	public String getBookAll(Model model) {
		model.addAttribute("bookList", bookService.getBookAll());
		return "book/bookList";
	}
	
	//대여 현황 조회
	@GetMapping("rentList")
	public String getRentAll(Model model) {
		model.addAttribute("rentList", bookService.getRentAll());
		return "book/rentList";
	}
}
