package com.board.web.board;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.BoardVo;
import com.board.domain.PageSearchVo;
import com.board.web.reply.ReplyDao;

@Service
public class BoardService {
	
	@Inject
	private BoardDao boardDao;
	
	@Inject
	private ReplyDao replyDao;
	
	public int insertBoard(BoardVo vo) throws Exception {
		return boardDao.insert(vo);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public BoardVo selectBoardByBno(Integer bno, boolean addViewCnt) throws Exception {
		if(addViewCnt) {
			boardDao.updateViewCnt(bno);
		}
		return boardDao.select(bno);
	}

	public void updateBoard(BoardVo vo) throws Exception {
		boardDao.update(vo);
	}
	
	@Transactional
	public void deleteBoard(Integer bno) throws Exception {
		replyDao.deleteByBno(bno);
		boardDao.delete(bno);
	}
	
	public Map<String, Object> getBoardList(PageSearchVo pageSearchVo) throws Exception {
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("totalCount",	boardDao.selectBoardTotalCnt(pageSearchVo));
		retMap.put("list", 			boardDao.selectList(pageSearchVo));
		return retMap; 
	}
	
}
