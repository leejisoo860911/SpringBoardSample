package com.board.web.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.PageVo;
import com.board.domain.ReplyVo;

@Repository
public class ReplyDao {

	@Inject
	private SqlSession session;

	private static String namespace = "ReplyMapper";

	public List<ReplyVo> selectList(Integer bno) throws Exception {
		return session.selectList(namespace + ".select", bno);
	}
	
	public int insert(ReplyVo vo) throws Exception {
		return session.insert(namespace + ".insert", vo);
	}
	
	public int update(ReplyVo vo) throws Exception {
		return session.update(namespace + ".update", vo);
	}

	public int delete(Integer rno) throws Exception {
		return session.delete(namespace + ".delete", rno);
	}

	public List<ReplyVo> selectList(Integer bno,  PageVo pageVo) throws Exception {
		Map<String, Object> inMap = new HashMap<>();
		inMap.put("bno", bno);
		inMap.put("pageVo", pageVo);
		return session.selectList(namespace + ".selectListPage", inMap);
	}
	
	public int selectReplyTotalCount(Integer bno) throws Exception {
		return session.selectOne(namespace + ".selectTotalCount", bno);
	}

	public int deleteByBno(Integer bno) {
		return session.delete(namespace + ".deleteByBno", bno);
	}

	/*
	public List<BoardVo> selectList(PageVo pageVo) throws Exception {
		return session.selectList(namespace + ".selectListPage", pageVo);
	}
	
	public int selectBoardTotalCnt(PageVo pageVo) throws Exception {
		return session.selectOne(namespace + ".selectBoardTotalCnt", pageVo);
	}
	*/
	
}
