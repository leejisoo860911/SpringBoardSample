package com.board.web.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.PageMaker;
import com.board.domain.PageVo;
import com.board.domain.ReplyVo;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	private static final Logger log = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService service;
	
	@RequestMapping(value="/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVo>> list(@PathVariable("bno") Integer bno) {
		ResponseEntity<List<ReplyVo>> entity = null;
		try {
			List<ReplyVo> list = service.selectListReply(bno);
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
		
		return entity;
	}
	
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page) {
	    ResponseEntity<Map<String, Object>> entity = null;
	    
	    try {
	    	PageVo pageVo = new PageVo(page);
	    	List<ReplyVo> listReply = service.selectListReply(bno, pageVo);
	    	int totalReplyCnt 		= service.selectReplyTotalCount(bno);
	    	PageMaker pageMaker = new PageMaker(pageVo, totalReplyCnt);

	        Map<String, Object> resMap = new HashMap<String, Object>();
	        resMap.put("list", listReply);
	        resMap.put("pageMaker", pageMaker);

	        entity = new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.OK);
	    }
		catch (Exception e) {
			e.printStackTrace();
		    entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	    
	    return entity;
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVo vo) {
		ResponseEntity<String> entity = null;
		try {
			int insertCnt = service.insertReply(vo);
			if(insertCnt <= 0) {
				new Exception("Reply Insert Faile");
			}
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
		return entity;
	}
	
	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVo vo) {
		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno);
			int updateCnt = service.updateReply(vo);
			if(updateCnt <= 0) {
				new Exception("Reply Update Faile");
			}
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
		
		return entity;
	}

	@RequestMapping(value = "/{rno}", method = { RequestMethod.DELETE })
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno, @RequestBody Map inMap) {
		ResponseEntity<String> entity = null;
		try {
			int deleteCnt = service.deleteReply(rno, (Integer)inMap.get("bno"));
			if(deleteCnt <= 0) {
				new Exception("Reply Delete Faile");
			}
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
		
		return entity;
	}
}