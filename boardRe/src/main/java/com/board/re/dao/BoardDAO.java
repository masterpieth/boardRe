package com.board.re.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.board.re.vo.BoardVO;
import com.board.re.vo.Criteria;

@Repository
public class BoardDAO {

	@Autowired
	SqlSession sqlSession;

	public int writeBoard(BoardVO vo, HttpSession session, MultipartFile uploadFile) {
		String userid = (String) session.getAttribute("userid");
		vo.setUserid(userid);
		int result = 0;
		if (!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String savedFileName = UUID.randomUUID().toString();
			vo.setOriginalFileName(originalFileName);
			vo.setSavedFileName(savedFileName);
			try {
				uploadFile.transferTo(new File("C:/text/" + savedFileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			result = mapper.writeBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<BoardVO> boardList(Criteria cri) {
		ArrayList<BoardVO> list = null;
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			list = mapper.boardList(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int totalCount() {
		int result = 0;
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			result = mapper.totalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public BoardVO readBoard(BoardVO vo, HttpSession session) {
		BoardVO result = null;
		vo.setUserid((String)session.getAttribute("userid"));
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.updateHit(vo.getBoardnum());
			result = mapper.readBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
