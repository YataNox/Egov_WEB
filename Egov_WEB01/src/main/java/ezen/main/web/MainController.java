package ezen.main.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.main.dto.MemberVO;
import ezen.main.service.MainService;

@Controller
public class MainController {
	@Resource(name="MainService")
	MainService ms;
	// @Service가 달려있는 클래스의 이름은 MainServiceimpl이지만, 어노테이션의 value 값이 MainService이기 때문에
	// @Resource를 이용하여 어노테이션 값(value="MainService")을 검색하여 매칭합니다.
	
	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request, Model model) {
		
		model.addAttribute("food", "삼계탕");
		return "main";
	}
	
	@RequestMapping(value="/main2.do")
	public String main2(HttpServletRequest request, Model model) {
		String menu = request.getParameter("menu");
		model.addAttribute("food", menu);
		return "main2";
	}
	
	@RequestMapping(value="/loginForm.do")
	public String loginForm(HttpServletRequest request, Model model) {
		
		return "loginForm";
	}
	
	@RequestMapping(value="/login.do")
	public String login(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberVO mvo = ms.getMember(id);
		
		if(id.equals("scott") && pw.equals("1234")) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "main";
		}else {
			model.addAttribute("message", "로그인 정보가 틀렸습니다.");
			return "loginForm";
		}
	}
}

