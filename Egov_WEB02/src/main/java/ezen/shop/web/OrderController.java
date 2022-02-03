package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.OrderService;

@Controller
public class OrderController {
	@Resource(name="OrderService") 
	OrderService os;
	
	@RequestMapping(value="orderInsert.do")
	public String orderInsert(HttpServletRequest request, Model model) {
		int oseq = 0;
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID"));
			paramMap.put("oseq", 0);
			
			os.insertOrder(paramMap); // 아이디와 oseq를 갖고 프로시져에서 아이디로
			// 카트 검색, 검색 내용으로 orders와 order_detail 테이블에 레코드 추가
			
			oseq = Integer.parseInt(paramMap.get("oseq").toString());
		}
		return "redirect:/orderList.do?oseq=" + oseq;
	}
	
	@RequestMapping(value="orderList.do")
	public String orderList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("oseq", Integer.parseInt(request.getParameter("oseq")));
			paramMap.put("ref_cursor", null);
			
			os.selectOrder(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			int totalPrice = 0;
			for(HashMap<String, Object> ovo : list)
					totalPrice += Integer.parseInt(ovo.get("PRICE2").toString()) * Integer.parseInt(ovo.get("QUANTITY").toString());
			
			model.addAttribute("orderList", list);
			model.addAttribute("totalPrice", totalPrice);
			return "mypage/orderList";
		}
	}
}
