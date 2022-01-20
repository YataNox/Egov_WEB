package ezen.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
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
		
		if(id.equals("scott") && pw.equals("1234")) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", id);
			return "main";
		}else {
			model.addAttribute("message", "로그인 정보가 틀렸습니다.");
			return "loginForm";
		}
	}
}

