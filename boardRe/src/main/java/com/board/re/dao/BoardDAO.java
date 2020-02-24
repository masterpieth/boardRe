package com.board.re.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.board.re.vo.BoardVO;
import com.board.re.vo.Criteria;
import com.board.re.vo.ReplyVO;

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
				uploadFile.transferTo(new File("C:/test/" + savedFileName));
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
		vo.setUserid((String) session.getAttribute("userid"));
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.updateHit(vo.getBoardnum());
			result = mapper.readBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBoard(BoardVO vo, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		vo.setUserid(userid);
		int result = 0;
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			BoardVO temp = mapper.readBoard(vo);
			if(temp.getOriginalFileName() != null) {
				File file = new File("C:/test/" + temp.getSavedFileName());
				file.delete();
			}
			result = mapper.deleteBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateBoard(BoardVO vo, HttpSession session, MultipartFile uploadFile) {
		String userid = (String)session.getAttribute("userid");
		vo.setUserid(userid);
		int result = 0;
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			if(!uploadFile.isEmpty()) {
				BoardVO temp = mapper.readBoard(vo);
				File file = new File("C:/test/"+temp.getSavedFileName());
				if(file.exists())file.delete();
				
				String originalFileName = uploadFile.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString();
				vo.setOriginalFileName(originalFileName);
				vo.setSavedFileName(savedFileName);
				try {
					uploadFile.transferTo(new File("C:/test/"+savedFileName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			result = mapper.updateBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void download(BoardVO vo, HttpServletResponse response, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		vo.setUserid(userid);
		System.out.println(vo.toString());
		BoardVO board = null;
		try {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			board = mapper.readBoard(vo);
			System.out.println(board.toString());
			File file = new File("C:/test/" + board.getSavedFileName());
			String originalFileName = board.getOriginalFileName();
			response.setHeader("Content-Disposition", 
					"attachment;filename=" + URLEncoder.encode(originalFileName, "UTF-8"));
			response.setContentLength((int)file.length());
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int writeReply(ReplyVO vo, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		vo.setUserid(userid);
		int result = 0;
		try {
			ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
			result = mapper.writeReply(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
