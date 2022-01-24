package ezen.main.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezen.main.dto.BoardVO;
import ezen.main.dto.MemberVO;
import ezen.main.dto.Paging;
import ezen.main.dto.TransferVO;
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

		TransferVO con = ms.getMember(id);
		if(con.getList().size() == 0) {
			model.addAttribute("message", "아이디가 없어요.");
			return "loginForm";
		}
		
		/* MemberVO mvo = ms.getMember(id); */
		MemberVO mvo = (MemberVO)con.getList().get(0);
		
		/*if(mvo == null) {
			request.setAttribute("message", "아이디가 없어요.");
			return "loginForm";
		}else*/if(mvo.getPwd() == null) {
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
int page = 1;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			}else {
				page = 1;
				session.removeAttribute("page");
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = bs.getAllCount();
			paging.setTotalCount(count);
			
			model.addAttribute("paging", paging);
			model.addAttribute("main");
			
			/* ArrayList<BoardVO> list = bs.getBoard(); */
			TransferVO con = bs.getBoard(paging);
			model.addAttribute("boardList", con.getList());
		    
			/* model.addAttribute("boardList", list); */
		    return "main";
		}
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		return "redirect:/loginForm.do";
	}
	
	/*@RequestMapping(value="boardView.do")
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
	}*/
	
	@RequestMapping(value="boardView.do")
	public String boardView(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			/*
			 * BoardVO bvo = bs.getBoardOne(num);
			 * 
			 * model.addAttribute("board", bvo);
			 */
			
			TransferVO con = bs.getBoardOne(num);
			BoardVO bvo  = (BoardVO)con.getList().get(0);
			model.addAttribute("board", bvo);
			return "boardView";
		}
	}
	
	@RequestMapping(value="boardviewwithoutcount.do")
	public String boardviewwithoutcount(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			/* BoardVO bvo = bs.getBoardOne(num); */
			TransferVO con = bs.getBoardOneNotReadCount(num);
			
			/* model.addAttribute("board", bvo); */
			model.addAttribute("board", con.getList().get(0));
			return "boardView";
		}
	}
	
	@RequestMapping(value="joinForm.do")
	public String joinForm(HttpServletRequest request, Model model) {
		return "member/joinForm";
	}
	
	@RequestMapping(value="idcheck.do")
	public String idcheck(HttpServletRequest request, Model model) {
		String id = request.getParameter("userid");
		
		/* int result = ms.getID(id); */
		TransferVO con = ms.getMember(id);
		
		if(con.getList().size()==0) {
			model.addAttribute("userid", id);
			model.addAttribute("result", -1);
		}
		else {
			model.addAttribute("userid", id);
			model.addAttribute("result", 1);
		}
		return "member/idcheck";
	}
	
	@RequestMapping(value="join.do")
	public String join(HttpServletRequest request, Model model) {
		MemberVO mvo = new MemberVO();
		
		mvo.setId(request.getParameter("userid"));
		mvo.setPwd(request.getParameter("pw"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		
		ms.insertMember(mvo);
		
		return "redirect:/loginForm.do";
	}
	
	@RequestMapping(value="memberEditForm.do")
	public String memberEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			model.addAttribute("loginUser", mvo);
			return "member/editMemberForm";
		}
	}
	
	@RequestMapping(value="editMember.do")
	public String editMember(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			mvo.setPwd(request.getParameter("pwd"));
			mvo.setPhone(request.getParameter("phone"));
			mvo.setName(request.getParameter("name"));
			mvo.setEmail(request.getParameter("email"));
			
			ms.updateMember(mvo);
			
			session.setAttribute("loginUser", mvo);
			return "redirect:/boardList.do";
		}
	}
		
	@RequestMapping(value="boardWriteForm.do")
	public String boardWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			model.addAttribute("loginUser", mvo);
			return "boardWrite";
		}
	}
	
	@RequestMapping(value="boardWrite.do")
	public String boardWrite(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			BoardVO bvo = new BoardVO();
			bvo.setUserid(request.getParameter("userid"));
			bvo.setEmail(request.getParameter("email"));
			bvo.setPass(request.getParameter("pass"));
			bvo.setTitle(request.getParameter("title"));
			bvo.setContent(request.getParameter("content"));
			
			bs.insertBoard(bvo);
			
			return "redirect:/boardList.do";
		}
	}
		
	@RequestMapping(value="boardEditForm.do")
	public String boardEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			return "boardCheckPass";
		}
	}
	
	@RequestMapping(value="boardDeleteForm.do")
	public String boardDeleteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			return "boardCheckPass";
		}
	}
	
	@RequestMapping(value="boardCheckPass.do")
	public String boardCheckPass(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			TransferVO con = bs.getBoardOneNotReadCount(request.getParameter("num"));
			/* BoardVO bvo = bs.getBoardOne(request.getParameter("num")); */
			BoardVO bvo = (BoardVO)con.getList().get(0);
			String pass = request.getParameter("pass");
			if(!bvo.getPass().equals(pass)) {
				model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
				return "boardCheckPass";
			}else if(bvo.getPass().equals(pass)) {
				return "checkSuccess";
			}else {
				model.addAttribute("message", "기타 오류로 실패.");
				return "boardCheckPass";
			}
		}
	}
	
	@RequestMapping(value="boardUpdateForm.do")
	public String boardUpdateForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			/*
			 * BoardVO bvo = bs.getBoardOne(request.getParameter("num"));
			 * model.addAttribute("board", bvo);
			 */
			TransferVO con = bs.getBoardOneNotReadCount(request.getParameter("num"));
			model.addAttribute("board", con.getList().get(0));
			return "boardUpdate";
		}
	}
	
	@RequestMapping(value="boardupdate.do")
	public String boardupdate(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			TransferVO con = bs.getBoardOneNotReadCount(request.getParameter("num"));
			/* BoardVO bvo = bs.getBoardOne(request.getParameter("num")); */
			BoardVO bvo = (BoardVO)con.getList().get(0);
			bvo.setEmail(request.getParameter("email"));
			bvo.setPass(request.getParameter("pass"));
			bvo.setTitle(request.getParameter("title"));
			bvo.setContent(request.getParameter("content"));
			
			bs.updateBoard(bvo);
			
			return "redirect:/boardviewwithoutcount.do?num=" + bvo.getNum();
		}
	}
	
	@RequestMapping(value="boardDelete.do")
	public String boardDelete(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			bs.deleteBoard(request.getParameter("num"));
			return "redirect:/boardList.do";
		}
	}
}















