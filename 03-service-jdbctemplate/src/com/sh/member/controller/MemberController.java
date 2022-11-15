package com.sh.member.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.sh.member.model.dto.DeleteMember;
import com.sh.member.model.dto.Member;
import com.sh.member.model.service.MemberService;

public class MemberController {
	
	private MemberService memberService = new MemberService();

	public int insertMember(Member member) {
		return memberService.insertMember(member);
	}

	public List<Member> findByName(String name) {
		return memberService.findByName(name);
	}

//	public List<Member> findByAll() {
//		return memberService.findByAll();
//	}

	public Member findById(String id) {
		Member member = memberService.findById(id);
		return member;
	}

	public int updateMemberName(String id, String newName) {
		return memberService.updateMemberName(id, newName);
	}

	public int updateMemberBirthday(String id, Date newBirthday) {
		return memberService.updateMemberBirthday(id, newBirthday);
	}

	public int updateMemberEmail(String id, String newEmail) {
		return memberService.updateMemberEmail(id, newEmail);
	}

	public int deleteMember(String id) {
		return memberService.deleteMember(id);
	}

	public List<DeleteMember> findDeleteMember() {
		return memberService.findDeleteMember();
	}

	public List<Member> findByAll() {
		return memberService.findByAll();
	}
	
	
}
