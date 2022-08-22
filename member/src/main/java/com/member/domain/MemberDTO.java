package com.member.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String email;
	private Timestamp reg;

}
