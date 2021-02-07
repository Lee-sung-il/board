package board.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDao;
import board.vo.BoardVo;
import common.PageInfo;

import static common.jdbcUtil.*;

public class BoardService {
	private Connection con;
	
	private BoardDao setDao() {
		BoardDao dao = BoardDao.getInstance();
		this.con = getConnection();
		dao.setConnection(this.con);
		return dao;
	}
	
	// 글 총 개수를 불러온다.
	public int getTotalArticleCount(String query) {
		BoardDao dao = setDao();
		int count = dao.getTotalArticleCount(query);
		close(this.con);
		return count;
	}
	
	// 게시판의 글 목록을 불러온다.
	public ArrayList<BoardVo> getBoardList(PageInfo pi, String query) {
		BoardDao dao = setDao();
		ArrayList<BoardVo> list = dao.getBoardList(pi, query);
		close(this.con);
		return list;
	}
	
	// 글 정보를 불러온다.
	public BoardVo getArticleDetail(int sq) {
		BoardDao dao = setDao();
		BoardVo vo = dao.getArticleDetail(sq);
		close(this.con);
		return vo;
	}
	
	public boolean increseArticleHitCount(int sq) {
		BoardDao dao = setDao();
		int count = dao.increseArticleHitCount(sq);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false;
	}
	
	public boolean insertArticle(BoardVo vo) {
		BoardDao dao = setDao();
		int count = dao.insertArticle(vo);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false;
	}

    public boolean deleteArticle(int sq) {
		BoardDao dao = setDao();
		int count = dao.deleteArticle(sq);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false;
    }

    public boolean updateArticle(BoardVo vo1) {
		BoardDao dao = setDao();
		int count = dao.updateArticle(vo1);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false;

    }
}


















