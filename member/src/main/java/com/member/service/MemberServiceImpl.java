package com.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.member.domain.MemberDTO;
import com.member.persistence.MemberMapper;

@Service	//service 는 비즈니스 영역을 담당하는 객체임을 표시해줌.
public class MemberServiceImpl implements MemberService {
	//Impl -> implement 구현했다. 인터페이스 구현
	
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public int addMember(MemberDTO member) {
		
		//DB에 회원 정보 insert 해주기.
		// mapper에 메서드 호출에서 DB에 insert 하고, 갱신된 레코드수 결과 리턴 받기
		int result = mapper.insertMember(member);
		// 갱신 레코드수 리턴받은 결과 서비스로 리턴하기.
		return result;
	}
	
	@Override
	public int idpwCheck(MemberDTO member) {
	//	int result = mapper.idpwCheck(member);
	//	return result;
		return mapper.idpwCheck(member);
	}
	
	@Override
	public MemberDTO getMember(String id) {
		//DB에 가서 id에 해당하는 회원정보 다 가져오는 처리
		return mapper.getMember(id);
	}
	
	@Override
	public int modifyMember(MemberDTO member) {
		//방법2.
		//session 꺼내기 -> session 필요 -> request 꺼내기 -> request 필요
		RequestAttributes ra =  RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sa = (ServletRequestAttributes) ra;
		HttpServletRequest request = sa.getRequest();
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("memId");
		
		member.setId(id);
		
		//id,pw 체크
		int checkResult = mapper.idpwCheck(member);
		//맞으면 수정
		if(checkResult==1) {
			//실제 삭제처리는 여기서...
			mapper.updateMember(member);

		}
		return checkResult;
	}
	
	@Override
	public int deleteMember(MemberDTO member) {
		
		//id, pw 체크하고 
		int result = mapper.idpwCheck(member);
		//일치하면 데이터 삭제, 로그아웃 처리
		if(result==1) {
			//데이터 삭제
			mapper.deleteMember(member.getId());
			//로그아웃 처리. 세션에서 memId 삭제
			RequestContextHolder.getRequestAttributes().removeAttribute("memId", RequestAttributes.SCOPE_SESSION);
			// session.removeAttribute("memId") 와 동일
			//getRequestAttributes - 속성 끄집어내서
			//removeAttribute - 삭제?
			
			// 세션 값 설정해주는 명령어
			//RequestContextHolder.getRequestAttributes().setAttribute(String_name(이름), Object_obj(값), int_Result);
			//RequestContextHolder.getRequestAttributes().setAttribute("sid", member.getId(), RequestAttributes.SCOPE_SESSION);
			// session.setAttribute("memId", member.getId()) 와 동일
			
			
			//값 꺼내주는 명령어
			//RequestContextHolder.getRequestAttributes().getAttribute("memId", RequestAttributes.SCOPE_SESSION)

		}
		return result;
	}
	

}
