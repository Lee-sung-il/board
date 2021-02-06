package board.dao;

import board.vo.BoardVo;
import common.PageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static common.jdbcUtil.*;

public class BoardDao {
    private Connection con;

    private BoardDao() {

    }

    public static BoardDao getInstance() {
        return LazyHolder.INSTANCE;
    }




    private static class LazyHolder {
        private static final BoardDao INSTANCE = new BoardDao();
    }
    public void setConnection(Connection con) {
        this.con = con;
    }

    public ArrayList<BoardVo> getBoardList(PageInfo pi,String query) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BoardVo> list = new ArrayList<>();
        BoardVo vo = null;
        try {
            pstmt = con.prepareStatement("select kbt.bd_sq,kbt.bd_sub,date_format(kbt.bd_dttm,'%y-%m-%d %H:%i') bd_dttm,kbt.bd_hit ,kmt.mber_id from ki_board_tb kbt inner join ki_mber_tb kmt on kbt.bd_mber_sq = kmt.mber_sq"+query+" limit ?,?;");
            pstmt.setInt(1,pi.getStartArticleNumber());
            pstmt.setInt(2,pi.getArticleCountPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()){
                vo = new BoardVo();
                vo.setBd_sq(rs.getInt("bd_sq"));
                vo.setBd_sub(rs.getString("bd_sub"));
                vo.setBd_dttm(rs.getString("bd_dttm"));
                vo.setBd_hit(rs.getInt("bd_hit"));
                vo.setMber_id(rs.getString("mber_id"));
                list.add(vo);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                close(rs);
                close(pstmt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }
    public int getTotalArticleCount(String query) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count =0;
        try {
            pstmt = con.prepareStatement("select  count(*) from ki_board_tb kbt"+query);

            rs = pstmt.executeQuery();
            while (rs.next()){
             count = rs.getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                close(rs);
                close(pstmt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return count;

    }
    public int getBoardInsert(BoardVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("insert into ki_board_tb(bd_mber_sq,bd_sub,bd_cntnt) values (?,?,?)");
            pstmt.setInt(1,vo.getBd_mber_sq());
            pstmt.setString(2,vo.getBd_sub());
            pstmt.setString(3,vo.getBd_cntnt());

            count = pstmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                close(pstmt);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

}
