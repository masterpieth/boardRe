package com.board.re.dao;

import com.board.re.vo.ReplyVO;

public interface ReplyMapper {

	public int writeReply(ReplyVO vo);
	public int deleteReply(ReplyVO vo);
}
