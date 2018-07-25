package com.board.web.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * GET /board/bno 전체조회
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getBoard(Model model) {
		log.info("getHome");
		return "redirect:/boards";
	}
}
