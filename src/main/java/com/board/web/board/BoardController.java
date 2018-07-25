package com.board.web.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVo;
import com.board.domain.PageMaker;
import com.board.domain.PageSearchVo;

@Controller
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	/**************************************************************
	 * 단순 화면 OPEN 용 GET 메소드
	 **************************************************************/
	/**
	 * GET /board/
	 */
	@RequestMapping(value = "/boards/viewRegister", method = RequestMethod.GET)
	public String getBoardRegister(@ModelAttribute PageSearchVo pageSearchVo, Model model) {
		return "/boards/listPage";
	}
	
	@RequestMapping(value = "/boards/viewModify", method = RequestMethod.GET)
	public String getBoardModify(@RequestParam("bno") int bno, PageSearchVo pageSearchVo, Model model) throws Exception {
		model.addAttribute("boardVo", service.selectBoardByBno(bno, false));
		return "/boards/modify";
	}
	
	/**************************************************************
	 * RESTFULL API준수 메소드
	 **************************************************************/
	/*
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public String getBoardList(Model model) throws Exception {
		model.addAttribute("list", service.selectList());
		return "/board/listAll";
	}
	*/
	
	/*
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public String getBoardList(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="perPageNum", required=false) Integer perPageNum , Model model) throws Exception {
		PageVo pageVo = new PageVo(page, perPageNum);
		model.addAttribute("list", service.selectList(pageVo));
		return "/board/listAll";
	}
	*/
	
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public String getBoardList(@ModelAttribute PageSearchVo pageSearchVo, Model model) throws Exception {
		Map<String, Object> map = service.getBoardList(pageSearchVo);
		
		int totalCount = (int) map.get("totalCount");
		List<BoardVo> list = (List<BoardVo>) map.get("list");
		PageMaker pageMaker = new PageMaker(pageSearchVo, totalCount);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/boards/listPage";
	}
	
	@RequestMapping(value = "/boards/{bno}", method = RequestMethod.GET)
	public String getBoardByBno(@PathVariable Integer bno, @ModelAttribute PageSearchVo pageSearchVo, Model model) throws Exception {
		BoardVo boardVo = service.selectBoardByBno(bno, true);
		if(boardVo == null) {
			throw new Exception("bno에 해당하는 게시물이 미존재 합니다");
		}
		model.addAttribute("boardVo", boardVo);
		return "/boards/read";
	}
	
	@RequestMapping(value = "/boards", method = RequestMethod.POST)
	public String postBoard(@ModelAttribute BoardVo vo, RedirectAttributes redirectAttr) throws Exception {
		int insertCnt = service.insertBoard(vo);
		if(insertCnt < 1) {
			throw new Exception("Insert 실패");
		}
		return "redirect:/boards/" + vo.getBno();
	}
	
	@RequestMapping(value = "/boards/{bno}", method = RequestMethod.PUT)
	public String putBoard(@PathVariable Integer bno, @ModelAttribute BoardVo boardVo, @ModelAttribute PageSearchVo pageSearchVo, RedirectAttributes redirectAttr) throws Exception {
		boardVo.setBno(bno);
		service.updateBoard(boardVo);
		redirectAttr.addAttribute("page", pageSearchVo.getPage());
		redirectAttr.addAttribute("perPageNum", pageSearchVo.getPerPageNum());
		redirectAttr.addAttribute("searchType", pageSearchVo.getSearchType());
		redirectAttr.addAttribute("keyword", pageSearchVo.getKeyword());
		return "redirect:/boards/"+bno;
	}
	
	@RequestMapping(value = "/boards/{bno}", method = RequestMethod.DELETE)
	public String deleteBoard(@PathVariable Integer bno, @ModelAttribute PageSearchVo pageSearchVo, RedirectAttributes redirectAttr) throws Exception {
		service.deleteBoard(bno);
		redirectAttr.addAttribute("page", pageSearchVo.getPage());
		redirectAttr.addAttribute("perPageNum", pageSearchVo.getPerPageNum());
		redirectAttr.addAttribute("searchType", pageSearchVo.getSearchType());
		redirectAttr.addAttribute("keyword", pageSearchVo.getKeyword());
		return "redirect:/boards";
	}
}