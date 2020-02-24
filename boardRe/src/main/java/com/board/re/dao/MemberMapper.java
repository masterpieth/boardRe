package com.board.re.dao;

import com.board.re.vo.MemberVO;

public interface MemberMapper {
	
	public MemberVO readMember(MemberVO vo);
	public int addMember(MemberVO vo);
}
