package board.service;

import board.dao.BoardDao;
import board.vo.BoardVo;
import common.PageInfo;

import java.sql.Connection;
import java.util.ArrayList;

import static common.jdbcUtil.*;
import static common.jdbcUtil.*;
public class BoardService {

    public ArrayList<BoardVo> getBoardList(PageInfo pi, String query) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArrayList<BoardVo> list = dao.getBoardList(pi,query);
        close(con);
        return list;
    }

    public int getTotalArticleCount(String query) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getTotalArticleCount(query);
        close(con);
        return count;
    }


    public int getBoardInsert(BoardVo vo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getBoardInsert(vo);
        if (count > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        close(con);
        return count;
    }
}
