package com.member.persistence;

import com.member.domain.MemberDTO;

public interface MemberMapper {

	public int insertMember(MemberDTO member);
	
	public int idpwCheck(MemberDTO member);
	
	public MemberDTO getMember(String id);
	
	public int updateMember(MemberDTO member);
	
	public int deleteMember(String id);
}
