package com.board.re.dao;

import java.util.ArrayList;

import com.board.re.vo.BoardVO;
import com.board.re.vo.Criteria;

public interface BoardMapper {

	public int writeBoard(BoardVO vo);
	public ArrayList<BoardVO> boardList(Criteria cri);
	public int totalCount();
	public BoardVO readBoard(BoardVO vo);
	public void updateHit(int boardnum);
	public int deleteBoard(BoardVO vo);
	public int updateBoard(BoardVO vo);
}
