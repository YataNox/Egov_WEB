package ezen.main.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ezen.main.dto.Paging;
import ezen.main.service.BoardService;
import ezen.main.service.MainService;

@Controller
public class MainContorller {
	
	@Autowired
	ServletContext context;
	
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

		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("id", id);
		
		ms.getMember(paramMap);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		/* TransferVO con = ms.getMember(id); */
		if(list.size() == 0) {
			model.addAttribute("message", "아이디가 없어요.");
			return "loginForm";
		}
		
		/* MemberVO mvo = ms.getMember(id); */
		/* MemberVO mvo = (MemberVO)con.getList().get(0); */
		HashMap<String, Object> mvo = list.get(0);
		/*if(mvo == null) {
			request.setAttribute("message", "아이디가 없어요.");
			return "loginForm";
		}else*/if(mvo.get("PWD") == null) {
			request.setAttribute("message", "회원정보 오류. 관리자에게 문의하세요.");
			return "loginForm";
		}else if(!mvo.get("PWD").equals(pw)) {
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
		
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			// TransferVO를 사용할 때는 멤버변수를 만들어야만 사용할 수 있는 제약이 있다면,
			// HashMap은 자유로운 key값 활용으로 제약없이 사용이 가능하다는 차이가 있습니다.
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
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
			
			paramMap.put("cnt", 0);
			bs.getAllCount(paramMap);
			int count = (int)paramMap.get("cnt");
			paging.setTotalCount(count);
			
			/* model.addAttribute("main"); */
			paramMap.put("ref_cursor", null);
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			
			/* ArrayList<BoardVO> list = bs.getBoard(); */
			bs.getBoard(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			model.addAttribute("paging", paging);
			model.addAttribute("boardList", list);
		    
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
		
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			/*
			 * BoardVO bvo = bs.getBoardOne(num);
			 * 
			 * model.addAttribute("board", bvo);
			 */
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("num", num);
			
			bs.getBoardOne(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			// BoardVO bvo  = (BoardVO)con.getList().get(0);
			
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ref_cursor", null);
			paramMap2.put("num", num);
			
			bs.getReplyList(paramMap2);
			
			ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap2.get("ref_cursor");
			//TransferVO2 con2 = bs.getReplyList(num);
			model.addAttribute("board", list);
			model.addAttribute("replyList", list2);
			//model.addAttribute("replyList", con2.getList());
			return "boardView";
		}
	}
	
	@RequestMapping(value="boardviewwithoutcount.do")
	public String boardviewwithoutcount(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("num", num);
			
			bs.getBoardOneNotReadCount(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			/* BoardVO bvo = bs.getBoardOne(num); */
			/* TransferVO con = bs.getBoardOneNotReadCount(num); */
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ref_cursor", null);
			paramMap2.put("num", num);
			
			bs.getReplyList(paramMap2);
			
			ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap2.get("ref_cursor");
			/* model.addAttribute("board", bvo); */
			/* model.addAttribute("board", con.getList().get(0)); */
			model.addAttribute("board", list);
			model.addAttribute("replyList", list2);
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
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("id", id);
		
		ms.getMember(paramMap);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		/* int result = ms.getID(id); */
		
		if(list.size()==0) {
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
		/*MemberVO mvo = new MemberVO();*/
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", request.getParameter("userid"));
		paramMap.put("pwd", request.getParameter("pw"));
		paramMap.put("name", request.getParameter("name"));
		paramMap.put("email", request.getParameter("email"));
		paramMap.put("phone", request.getParameter("phone"));
//		mvo.setId(request.getParameter("userid"));
//		mvo.setPwd(request.getParameter("pw"));
//		mvo.setName(request.getParameter("name"));
//		mvo.setEmail(request.getParameter("email"));
//		mvo.setPhone(request.getParameter("phone"));
		
		ms.insertMember(paramMap);
		
		return "redirect:/loginForm.do";
	}
	
	@RequestMapping(value="memberEditForm.do")
	public String memberEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
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
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			mvo.put("id", request.getParameter("userid"));
			mvo.put("PWD", request.getParameter("pwd"));
			mvo.put("PHONE", request.getParameter("phone"));
			mvo.put("NAME", request.getParameter("name"));
			mvo.put("EMAIL", request.getParameter("email"));
			
			ms.updateMember(mvo);
			
			session.setAttribute("loginUser", mvo);
			return "redirect:/boardList.do";
		}
	}
		
	@RequestMapping(value="boardWriteForm.do")
	public String boardWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			model.addAttribute("loginUser", mvo);
			return "boardWrite";
		}
	}
	
	@RequestMapping(value="boardWrite.do", method=RequestMethod.POST)
	public String boardWrite(HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			String savePath = context.getRealPath("/images");
			
			MultipartRequest multi = new MultipartRequest(
					request, savePath, 5*1024*1024 , "UTF-8", new DefaultFileRenamePolicy());
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pass", multi.getParameter("pass"));
			paramMap.put("userid", multi.getParameter("userid"));
			paramMap.put("title", multi.getParameter("title"));
			paramMap.put("content", multi.getParameter("content"));
			paramMap.put("email", multi.getParameter("email"));
			paramMap.put("imgfilename", multi.getFilesystemName("imgfilename"));
			
			bs.insertBoard(paramMap);
			
			return "redirect:/boardList.do";
		}
	}

	@RequestMapping(value="boardEditForm.do")
	public String boardEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			return "boardCheckPass";
		}
	}
	
	@RequestMapping(value="boardDeleteForm.do")
	public String boardDeleteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			return "boardCheckPass";
		}
	}
	
	@RequestMapping(value="boardCheckPass.do")
	public String boardCheckPass(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("num", request.getParameter("num"));
			
			bs.getBoardOneNotReadCount(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			/* BoardVO bvo = bs.getBoardOne(request.getParameter("num")); */
			HashMap<String, Object> bvo = list.get(0);
			String pass = request.getParameter("pass");
			if(!bvo.get("PASS").equals(pass)) {
				model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
				return "boardCheckPass";
			}else if(bvo.get("PASS").equals(pass)) {
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
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			/*
			 * BoardVO bvo = bs.getBoardOne(request.getParameter("num"));
			 * model.addAttribute("board", bvo);
			 */
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("num", request.getParameter("num"));
			
			bs.getBoardOneNotReadCount(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			model.addAttribute("board", list);
			return "boardUpdate";
		}
	}
	
	@RequestMapping(value="boardupdate.do")
	public String boardupdate(HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			String savePath = context.getRealPath("/images");
			MultipartRequest multi = new MultipartRequest(
					request, savePath, 5*1024*1024 , "UTF-8", new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multi.getParameter("num"));
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pass", multi.getParameter("pass"));
			paramMap.put("userid", multi.getParameter("userid"));
			paramMap.put("title", multi.getParameter("title"));
			paramMap.put("content", multi.getParameter("content"));
			paramMap.put("email", multi.getParameter("email"));
			paramMap.put("num", multi.getParameter("num"));
			if(multi.getFilesystemName("imgfilename") == null) {
				paramMap.put("imgfilename", multi.getParameter("oldfilename"));
			}else {
				paramMap.put("imgfilename", multi.getFilesystemName("imgfilename"));
			}
			
			bs.updateBoard(paramMap);
			return "redirect:/boardviewwithoutcount.do?num=" + num;
		}
	}
	
	@RequestMapping(value="boardDelete.do")
	public String boardDelete(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("num", Integer.parseInt(request.getParameter("num")));
			bs.deleteBoard(paramMap);
			return "redirect:/boardList.do";
		}
	}
	
	@RequestMapping(value="addReply.do", method=RequestMethod.POST)
	public String addReply(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", request.getParameter("userid"));
			paramMap.put("reply", request.getParameter("reply"));
			paramMap.put("boardnum", request.getParameter("boardnum"));
			
			bs.insertReply(paramMap);
			/*
			 * ReplyVO rvo = new ReplyVO(); rvo.setUserid((String)mvo.get("ID"));
			 * rvo.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
			 * rvo.setContent(request.getParameter("reply"));
			 * 
			 * bs.insertReply(rvo);
			 */
			return "redirect:/boardviewwithoutcount.do?num=" + request.getParameter("boardnum");
		}
	}
	
	@RequestMapping(value="deleteReply.do")
	public String deleteReply(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("num", request.getParameter("num"));
			paramMap.put("boardnum", request.getParameter("boardnum"));
			
			bs.deleteReply(paramMap);
			
			return "redirect:/boardviewwithoutcount.do?num=" + request.getParameter("boardnum");
		}
	}
}















