package ezen.main.web;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezen.main.dto.BoardVO;
import ezen.main.dto.MemberVO;
import ezen.main.service.BoardService;
import ezen.main.service.MainService;

@Controller
public class MainContorller {
	
	@Resource(name="MainService") MainService ms;
	@Resource(name="BoardService") BoardService bs;
	// @Service 가 달려있는 클래스의 이름은 MainServiceimpl 이지만, 어너테이션의 value 값이 MainService 이기 때문에
	// @Resource 를 이용하여 어너테이션값(value="MainService")을 검색하여 매칭합니다
	
	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request, Model model) {
		
		model.addAttribute("food" , "삼계탕");
		return "main";
	}
	
	@RequestMapping(value="/main2.do")
	public String main2(HttpServletRequest request, Model model) {
		String menu = request.getParameter("menu");
		
		model.addAttribute("food" , menu);
		return "main2";
	}
	
	
	@RequestMapping(value="/loginForm.do")
	public String loginForm(HttpServletRequest request, Model model) {
				
		return "loginForm";
	}
	
	@RequestMapping(value="/login.do")
	public String login(@RequestParam("pw") String pw, HttpServletRequest request, Model model) {
		String id = request.getParameter("id");

		MemberVO mvo = ms.getMember(id);
		
		if(mvo == null) {
			request.setAttribute("message", "아이디가 없어요.");
			return "loginForm";
		}else if(mvo.getPwd() == null) {
			request.setAttribute("message", "회원정보 오류. 관리자에게 문의하세요.");
			return "loginForm";
		}else if(!mvo.getPwd().equals(pw)) {
			request.setAttribute("message", "비밀번호가 틀려요.");
			return "loginForm";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/boardList.do";
		}
	}
	
	@RequestMapping(value="/boardList.do")
	public String boardList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			ArrayList<BoardVO> list = bs.getBoard();
		    
			model.addAttribute("boardList", list);
		    return "main";
		}
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		return "redirect:/loginForm.do";
	}
	
	@RequestMapping(value="boardView.do")
	public String boardView(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			BoardVO bvo = bs.getBoardOne(num);
			
			model.addAttribute("board", bvo);
			return "boardView";
		}
	}
}















