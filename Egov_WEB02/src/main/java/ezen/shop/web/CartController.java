package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.CartService;

@Controller
public class CartController {
	@Resource(name="CartService") 
	CartService cs;
	
	@RequestMapping(value="cartInsert.do")
	public String cartInsert(HttpServletRequest request, Model model) {
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pseq", pseq);
			paramMap.put("quantity", quantity);
			paramMap.put("id", mvo.get("ID").toString());
			cs.insertCart(paramMap);
			
			return "redirect:/cartList.do";
		}
	}
	
	@RequestMapping(value="cartList.do")
	public String cartList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("id", mvo.get("ID"));
			
			cs.selectCart(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			int totalPrice = 0;
			for(HashMap<String, Object> cvo : list)
					totalPrice += Integer.parseInt(cvo.get("PRICE2").toString()) * Integer.parseInt(cvo.get("QUANTITY").toString());
			
			model.addAttribute("cartList", list);
			model.addAttribute("totalPrice", totalPrice);
			return "mypage/cartList";
		}
	}
}
