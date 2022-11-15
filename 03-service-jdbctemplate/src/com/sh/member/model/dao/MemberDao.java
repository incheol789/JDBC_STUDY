package com.sh.member.model.dao;

import static com.sh.member.common.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sh.member.model.dto.DeleteMember;
import com.sh.member.model.dto.Member;

public class MemberDao {

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = "insert into member values (?, ?, ?, ?, ?, default, default)";
		int result = 0;
		
		try {
			// 1. PreparedStatement생성 - 미완성쿼리&값대입 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			// 2. 실행
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e); // checked -> unchecked 
		} finally {
			// 3. 자원반납 (pstmt, rset)
			close(pstmt);
		}
		return result;
	}
	public List<Member> findByName(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where name like ?";
		List<Member> members = new ArrayList<>();
		
		try {
			// 1. PreparedStatment 생성 - 미완성쿼리 & 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			// 2. 실행 - ResultSet
			rset = pstmt.executeQuery();
			// 3. ResultSet -> List<Member>
			while(rset.next()) {
				members.add(new Member(
						rset.getString("id"), 
						rset.getString("name"),
						rset.getString("gender"),
						rset.getDate("birthday"),
						rset.getString("email"),
						rset.getInt("point"),
						rset.getTimestamp("reg_date")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 4. 자원반납 (pstmt, rset)
			close(rset);
			close(pstmt);
		} 
		return members;
	}

	public List<Member> findByAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member";
		List<Member> members = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				members.add(new Member(
						rset.getString("id"),
						rset.getString("name"),
						rset.getString("gender"),
						rset.getDate("birthday"),
						rset.getString("email"),
						rset.getInt("point"),
						rset.getTimestamp("reg_date")));
			}
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return members;
	}
	
	
	
	public Member findById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where id = ?";
		Member member = null;
		
		try {
			//1.PreparedStatement 생성 - 미완성쿼리 & 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//2.resultSet 반환
			rset = pstmt.executeQuery();
			if(rset.next()) {
				id = rset.getString("id");
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				int point = rset.getInt("point");
				Timestamp regdate = rset.getTimestamp("reg_date");
				member = new Member(id, name, gender, birthday, email, point, regdate);
	
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
		}
	public int updateMemberName(Connection conn, String id, String newName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member set name = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newName);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int updateMemberBirthday(Connection conn, String id, Date newBirthday) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member set birthday = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, newBirthday);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int updateMemberEmail(Connection conn, String id, String newEmail) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member set email =? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newEmail);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		String sql = "delete from member where id = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<DeleteMember> findDeleteMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member_del";
		List<DeleteMember> delMembers = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				delMembers.add(new DeleteMember(
						rset.getString("id"),
						rset.getString("name"),
						rset.getString("gender"),
						rset.getDate("birthday"),
						rset.getString("email"),
						rset.getInt("point"),
						rset.getTimestamp("reg_date"),
						rset.getTimestamp("del_date")));
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return delMembers;
	}
}