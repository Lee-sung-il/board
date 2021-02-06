package member.service;

import member.dao.MemberDao;
import member.vo.MemberHistoryVo;
import member.vo.MemberVo;

import java.sql.Connection;

import static common.jdbcUtil.*;

public class MemberService {
    public int getCheckId(String id) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getCheckId(id);
        close(con);
        return count;
    }

    public boolean registerMember(MemberVo vo) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.registerMember(vo);
        boolean isSuccess = false;
        if (count > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSuccess;
    }

    public MemberVo getMemberInfo(String id) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVo vo = dao.getMemberInfo(id);
        close(con);
        return vo;
    }

    public void insertMemberHistory(MemberHistoryVo vo) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.insertMemeberHistory(vo);
        if (count > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        close(con);

    }

    public MemberVo getMemberDetail(int sq) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVo vo = dao.getMemberDetail(sq);
        close(con);
        return vo;
    }

    public boolean updateMemberInfo(MemberVo vo) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSuccess = false;
        int count = dao.updateMemberInfo(vo);
        if(count > 0 ) {
            commit(con);
            isSuccess = true;
        }else {
            rollback(con);
        }close(con);
        return isSuccess;

    }

    public boolean leaveMember(int sq) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSuccess = false;
        int count = dao.leaveMember(sq);
        if(count > 0 ) {
            commit(con);
            isSuccess = true;
        }else {
            rollback(con);
        }close(con);
        return isSuccess;
    }


    public boolean updatePassword(MemberVo vo1) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSuccess = false;
        int count = dao.updatePassword(vo1);
        if(count > 0 ) {
            commit(con);
            isSuccess = true;
        }else {
            rollback(con);
        }close(con);
        return isSuccess;
    }

    public String  getMemberId(MemberVo vo) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        String id = dao.getMemberId(vo);
        close(con);
        return id;
    }

    public int getMemberCount(MemberVo vo) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getMemberCount(vo);
        close(con);
        return count;
    }

    public boolean updateMemberPassword(MemberVo vo) {
        MemberDao dao = MemberDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSuccess = false;
        int count = dao.updateMemberPassword(vo);
        if(count > 0 ) {
            commit(con);
            isSuccess = true;
        }else {
            rollback(con);
        }close(con);
        return isSuccess;
    }
}
