package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.QnaService;

@Controller
public class QnaController {
	@Resource(name="QnaService") 
	QnaService qs;
	
	@RequestMapping(value="qnaList.do")
	public String qnaList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("id", loginUser.get("ID").toString());
			
			qs.getQnaList(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			model.addAttribute("qnaList", list);
			return "qna/qnaList";
		}
	}
	
	@RequestMapping(value="qnaView.do")
	public String qnaView(HttpServletRequest request, Model model) {
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("qseq", qseq);
			
			qs.getQnaByQseq(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			model.addAttribute("qnaVO", list.get(0));
			return "qna/qnaView";
		}
	}
	
	@RequestMapping(value="qnaWriteForm.do")
	public String qnaWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/loginForm.do";
		}else {
			return "qna/qnaWrite";
		}
	}
	
	@RequestMapping(value="qnaWrite.do")
	public String qnaWrite(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/loginForm.do";
		}else {
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subject", subject);
			paramMap.put("content", content);
			paramMap.put("id", loginUser.get("ID").toString());
			
			qs.insertQna(paramMap);
			
			return "redirect:/qnaList.do";
		}
	}
}
