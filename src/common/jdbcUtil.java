package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class jdbcUtil {
	public static Connection getConnection() {
		
		Connection con = null;
		
		try {
//			컨텍스트 객체 초기화
			Context initCtx = new InitialContext();
//		 	context.xml 로드
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
//			context.xml에서 name이 jdbc/MariaDB 인 <context>로드
			DataSource ds = (DataSource) envCtx.lookup("jdbc/MariaDB");
//
			con = ds.getConnection();
			con.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return con;
		
	}

//	 Connection 객체 close(바가지 돌려줌)
	
	public static void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	//	 PreparedStatement 객체 close(바가지 돌려줌)
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//	 ResultSet 객체 close(바가지 돌려줌)
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
//	insert, update, delete 한 데이터 commit(실제 저장)
	public static void commit(Connection con) {
		if(con != null) {
			try {
				con.commit();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//	insert, update, delete 한 데이터 rollback(되돌아기)
	public static void rollback(Connection con) {
		if(con != null) {
			try {
				con.rollback();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
