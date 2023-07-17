package com.yedam.web.board.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.web.board.service.BoardService;
import com.yedam.web.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//전체조회 : URI - boardList, RETURN - board/boardList
	//									: 모든 데이터의 게시글 번호, 제목, 작성자, 작성일(2023년07월17일)
	@GetMapping("/boardList")
	public String getBoardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/boardList";
	}
	
	//단건조회 : URI - boardInfo, RETURN - board/boardInfo
	//									: 번호, 제목, 작성자, 내용, 첨부이미지, 작성일(2023/07/17)
	//									: 첨부이미지 - webapp/resources/
	@GetMapping("/boardInfo")
	public String getBoardInfo(BoardVO boardVO, Model model) {  
		model.addAttribute("boardInfo", boardService.getBoardInfo(boardVO));
		return "board/boardInfo";
	}
	
	//등록 - 페이지 : URI - boardInsert, RETURN - board/boardInsert
	@GetMapping("/boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}
	
	//등록 - 처리 : URI - boardInsert, RETURN - 전체조회 다시 호출
	@PostMapping("/boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		boardService.insertBoardInfo(boardVO);
		return "redirect:boardList";		
	}
	
	//수정 - 페이지 : URI - boardUpdate, RETURN - board/boardUpdate
	@GetMapping("/boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		//이미 등록되어있는 데이터 조회해서 보여줘야함
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);	
		
		return "board/boardUpdate";
	}
	
	//수정 - 처리 : URI - boardUpdate, RETURN - 수정대상, 성공여부 반환
//	@PostMapping("/boardUpdate")
//	public String boardUpdateProcess(BoardVO boardVO, RedirectAttributes rtt) {
//		int bno = boardService.updateBoardInfo(boardVO);
//		String result = null;
//		String uri = null;
//		if(bno == -1) {
//			result = "수정 실패";
//			uri = "boardList";
//		}else {
//			result = bno + "수정 성공";
//			uri = "boardInfo?bno=" + bno;
//		}
//		
//		rtt.addFlashAttribute("result", result);
//		return "redirect:" + uri;		
//	}
	
	@PostMapping("/boardUpdate")
	@ResponseBody    //데이터를 반환하므로 반드시 @ResponseBody
	public Map<String, Object> boardUpdateProcess(BoardVO boardVO) {
		boolean result = false;
		int bno = boardService.updateBoardInfo(boardVO);
		if(bno > -1) {
			result = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("boardInfo", boardVO);
		return map;		
	}
	
	//삭제 - 처리 : URI - boardDelete, RETURN - 전체조회 다시 호출
	@GetMapping("/boardDelete")
	public String boardDeleteProcess(@RequestParam(defaultValue = "0") int bno) {   //int는 공백에 대해서 처리불가이므로 디폴트 값 넣어주기
		boardService.deleteBoardInfo(bno);
		return "redirect:boardList";    //여기서 return "board/boardList"로 주면 빈페이지가 호출됨 (Model객체로 데이터를 뷰로 넘기지 않았기 때문)
	}
}
