package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.MemberService;

@Controller
public class MemberController {
	@Resource(name="MemberService") 
	MemberService ms;
	
	@RequestMapping(value="loginForm.do")
	public String loginForm() {
		return "member/loginForm";
	} // loginForm.do END
	
	@RequestMapping(value="login.do")
	public String login(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("id", id);
		
		ms.getMember(paramMap);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		if(list == null) {
			model.addAttribute("message", "해당 아이디가 없습니다.");
			return "member/loginForm";
		}
		
		HashMap<String, Object> mvo = list.get(0);
		if(!mvo.get("PWD").equals(pwd)) {
			model.addAttribute("message", "비밀번호가 틀렸습니다.");
			return "member/loginForm";
		}else if(mvo.get("PWD").equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/main.do";
		}else {
			model.addAttribute("message", "기타 오류로 로그인 실패");
			return "member/loginForm";
		}
		
	} // login.do END
	
	@RequestMapping(value="logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/main.do";
	} // logout.do END
	
	@RequestMapping(value="contract.do")
	public String contract() {
		return "member/contract";
	}// contract.do END
	
	
} // Controller END
