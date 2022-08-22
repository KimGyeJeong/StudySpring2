package com.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.domain.MemberDTO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	// new 객체 생성 안하기 위해서
	// 다양성으로 받아줌. 부모이기 때문에 인터페이스로 받아줄수 있음.
	// MemberServiceImpl 도 받아도 괜찮지만 서비스는 MemberServiceImpl 가 실행
	@Autowired
	private MemberService service;

	// 회원가입 페이지
	@GetMapping("signup")
	public String signupForm() {

		return "member/signup";
	}
	/*
	 * public void signupForm() 해도 경로가 member/signup 이기때문에 사용 가능.
	 */

	// 회원가입 정보 들어오는 페이지
	@PostMapping("signupPro")
	public String signupPro(MemberDTO member, Model model) {

		log.info("memberDTO : " + member);

		// 여기는 컨트롤러단이기 때문에 회원가입 로직처리는 Service 에서 실행
		int result = service.addMember(member);

		// 처리된 결과 view에 전달
		model.addAttribute("result", result);

		return "member/signupPro";
	}

	@GetMapping("main")
	public void main() {

	}

	@GetMapping("login")
	public void login() {

	}

	@PostMapping("login")
	public String loginPro(MemberDTO member, String auto, Model model, HttpSession session) {
		// auto는 value 1 이었기 때문에 int auto 받아도 괜찮음

		log.info("member : " + member);
		log.info("auto : " + auto);

		// model.addAttribute("result", service.idpwCheck(member));

		int result = service.idpwCheck(member);

		if (result == 1) {
			session.setAttribute("memId", member.getId());
			return "redirect:/member/main"; // 로그인 성공시 메인으로 바로 이동.
		} else {
			model.addAttribute("result", result);
			return "member/loginPro";
		}
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {

		// session에 있는 값 삭제
		// 로그아웃 처리
		session.invalidate();

		return "redirect:/member/main";
	}

	// mypage
	@GetMapping("mypage")
	public void mypage() {

	}

	// 회원정보 수정
	@GetMapping("modify")
	public void modify(HttpSession session, Model model) {
		// DB에서 데이터를 들고와야 함

		// 회원 정보 가져와서 view에 전달한 후, 수정 폼 페이지로 이동

		String id = (String) session.getAttribute("memId");
		MemberDTO member = service.getMember(id);

		model.addAttribute("member", member);
	}

	// 회원정보 수정 진행
	@PostMapping("modify")
	public String modifyPro(Model model, MemberDTO member, String pwck, HttpSession session) {

		// 방법1. 세션에서 꺼내서 아이디 넣어주기
		// member.setId((String)session.getAttribute("memId"));

		// 방법2. 서비스의 modifyMember에 가서 꺼내주기

		// 비즈니스로직처리 : id, pw 일치하는지 체크한 후 일치하면 수정
		// id,pw 체크한 결과 1=일치, 0=불일치
		int result = service.modifyMember(member);

		model.addAttribute("result", result);

		return "member/modifyPro";
	}

	// 회원 탈퇴
	@GetMapping("delete")
	public void delete() {

	}

	@PostMapping("delete")
	public String deletePro(MemberDTO member, String pw, HttpSession session, Model model) {

		String id = (String) session.getAttribute("memId");
		member.setId(id);

		// 비지니스 로직처리
		// id, pw 주고 일치하면 데이터 삭제 + 로그아웃
		int result = service.deleteMember(member);
		model.addAttribute("result", result);

		return "member/deletePro";
	}

	// 0822 수업..

	// ajax로 id 중복여부 체크하는 요청 매핑 추가

	@PostMapping("idAvail")
	// @ResponseBody //jsp로 가는것이 아닌 데이터로 리턴해주기 위한 명령어
	public ResponseEntity<String> idAvail(MemberDTO member) {

		// String 이 아닌 위의방식으로 보내야 return 한글 했을대 한글이 깨지지 않음
		// public String idAvail(MemberDTO member) {
		// id값을 받아옴
		System.out.println("member : " + member.toString());

		int result = service.idpwCheck(member);
		// result == 1 이미 존재
		// result == 0 사용 가능
		String str = null;

		if (result > 0)
			// 인코딩되어있지않아서 한글이 깨짐
//			return "not available";
			str = "이미 사용중인ID입니다.";
		else
//			return "available";
			str = "사용가능한ID입니다";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain;charset=UTF-8");
//혹은	headers.add("Content-Type", "text/html;charset=UTF-8");
		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}

}
