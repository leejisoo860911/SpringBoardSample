package com.board.web.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVo;
import com.board.domain.PageSearchVo;

@Repository
public class BoardDao {

	@Inject
	private SqlSession session;

	private static String namespace = "BoardMapper";

	public int insert(BoardVo vo) throws Exception {
		return session.insert(namespace + ".insert", vo);
	}

	public BoardVo select(Integer bno) throws Exception {
		return session.selectOne(namespace + ".select", bno);
	}
	
	public void update(BoardVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}
	
	public void updateReplyCnt(Map inMap) throws Exception {
		session.update(namespace + ".updateReplyCnt", inMap);
	}
	
	public void updateViewCnt(Integer bno) throws Exception {
		session.update(namespace + ".updateViewCnt", bno);
	}
	
	public void delete(Integer bno) throws Exception {
		session.delete(namespace + ".delete", bno);
	}

	public List<BoardVo> selectList(PageSearchVo pageSearchVo) throws Exception {
		return session.selectList(namespace + ".selectListPage", pageSearchVo);
	}
	
	public int selectBoardTotalCnt(PageSearchVo pageSearchVo) throws Exception {
		return session.selectOne(namespace + ".selectBoardTotalCnt", pageSearchVo);
	}

}