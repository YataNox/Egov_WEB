package ezen.main.web;

import javax.servlet.http.HttpServletRequest;

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
}
