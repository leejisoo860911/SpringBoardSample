package com.board.web.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.PageVo;
import com.board.domain.ReplyVo;
import com.board.web.board.BoardDao;

@Service
public class ReplyService {
	
	@Inject
	private ReplyDao replyDao;
	
	@Inject
	private BoardDao boardDao;
	
	public List<ReplyVo> selectListReply(Integer bno) throws Exception {
		return replyDao.selectList(bno);
	}
	
	public List<ReplyVo> selectListReply(Integer bno, PageVo pageVo) throws Exception {
		return replyDao.selectList(bno, pageVo);
	}
	
	public int selectReplyTotalCount(Integer bno) throws Exception {
		return replyDao.selectReplyTotalCount(bno);
	}

	public int updateReply(ReplyVo vo) throws Exception {
		return replyDao.update(vo);
	}

	public int insertReply(ReplyVo vo) throws Exception {
		Map<String, Integer> inMap = new HashMap<>();
		inMap.put("bno", vo.getBno());
		inMap.put("amount", 1);
		boardDao.updateReplyCnt(inMap);
		return replyDao.insert(vo);
	}
	
	@Transactional
	public int deleteReply(Integer rno, Integer bno) throws Exception {
		Map<String, Integer> inMap = new HashMap<>();
		inMap.put("bno", bno);
		inMap.put("amount", -1);
		boardDao.updateReplyCnt(inMap);
		return replyDao.delete(rno);
	}

}
