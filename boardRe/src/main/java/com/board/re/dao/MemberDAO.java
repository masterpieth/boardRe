package com.board.re.dao;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.re.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	public MemberVO readMember(MemberVO vo) {
		MemberVO result = null;
		try {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			result = mapper.readMember(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int addMember(MemberVO vo) {
		int result = 0;
		try {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			result = mapper.addMember(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public MemberVO login(MemberVO vo, HttpSession session) {
		MemberVO result = null;
		try {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			result = mapper.readMember(vo);
			session.setAttribute("userid", result.getUserid());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}
}
