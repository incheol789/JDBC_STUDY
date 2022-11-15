package com.sh.member.model.service;

import static com.sh.member.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.sh.member.common.JdbcTemplate;
import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.dto.DeleteMember;
import com.sh.member.model.dto.Member;

/**
 * 
 * Service
 * 1. 클래스등록
 * 2. Connection생성
 * 3. Dao 위임
 * 4. 트랜잭션처리
 * 5. 자원반납 (conn)
 * 
 * Dao
 * 1. PreparedStatement생성 - 미완성쿼리&값대입 
 * 2. 실행
 * 3. 자원반납 (pstmt, rset)

 */
public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	String driverClass = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "student";
	String password = "student";

	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);			
		}
	
		return result;
	}

	/**
	 * 
	 * 1. Connection생성
	 * 2. PreparedStatment 생성 - 미완성쿼리 & 값대입
	 * 3. 실행 - ResultSet
	 * 4. ResultSet -> List<Member>
	 * 5. 자원반납
	 * 
	 * service
	 * 1. Connection생성
	 * 2. Dao 위임
	 * 3. 자원반납 (conn)
	 * 
	 * dao
	 * 1. PreparedStatment 생성 - 미완성쿼리 & 값대입
	 * 2. 실행 - ResultSet
	 * 3. ResultSet -> List<Member>
	 * 4. 자원반납 (pstmt, rset)
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public List<Member> findByName(String name) {
		Connection conn = getConnection();
		List<Member> members = memberDao.findByName(conn, name);
		close(conn);
		return members;
	}

//	public List<Member> findByAll() {
//		Connection conn = getConnection();
//		List<Member> members = memberDao.findByAll(conn);
//		close(conn);
//		return members;
//	}

	public Member findById(String id) {
		Connection conn = getConnection();
		Member member = memberDao.findById(conn, id);
		close(conn);
		return member;
	}
	
	public int updateMemberName(String id, String newName) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = memberDao.updateMemberName(conn, id, newName);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
}

	public int updateMemberBirthday(String id, Date newBirthday) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = memberDao.updateMemberBirthday(conn, id, newBirthday);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateMemberEmail(String id, String newEmail) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = memberDao.updateMemberEmail(conn, id, newEmail);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMember(String id) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = memberDao.deleteMember(conn, id);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public List<DeleteMember> findDeleteMember() {
		Connection conn = getConnection();
		List<DeleteMember> delMembers = memberDao.findDeleteMember(conn);
		close(conn);
		return delMembers;
	}
	
//	public List<Member> findByAll() {
//	Connection conn = getConnection();
//	List<Member> members = memberDao.findByAll(conn);
//	close(conn);
//	return members;
//}

	public List<Member> findByAll() {
		Connection conn = getConnection();
		List<Member> members = memberDao.findByAll(conn);
		close(conn);
		return members;
	}
	
	}