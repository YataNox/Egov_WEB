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
	
	@RequestMapping(value="myPage.do")
	public String myPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/loginForm.do";
		}else {
			// 최종 mypage.jsp 에 전달될 리스트
			ArrayList<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			// 현재 로그인 중인 유저의 진행중인 주문번호 리스트 조회
			paramMap1.put("id", loginUser.get("ID").toString());
			paramMap1.put("ref_cursor", null);
			os.selectOrderResultOne( paramMap1 );
			ArrayList<HashMap<String, Object>> oseqList = (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor");
			// 주문번호별 주문내역을 조회
			for( HashMap<String, Object> result : oseqList ) {
				int oseq = Integer.parseInt( result.get("OSEQ").toString() );
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("oseq", oseq);
				paramMap2.put("reg_cursor", null);
				os.listOrderByOseq(paramMap2);
	            
				ArrayList<HashMap<String, Object>> orderListByOseq = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
	            
				HashMap<String, Object> orderFirst = orderListByOseq.get(0);
				orderFirst.put("PNAME" , (String)orderFirst.get("PNAME") + "포함 " + orderListByOseq.size() + "건" );
	            
				int totalPrice = 0;
				for( HashMap<String, Object> order : orderListByOseq ) {
					totalPrice += Integer.parseInt( order.get("QUANTITY").toString() )
							* Integer.parseInt( order.get("PRICE2").toString() ); 
				}
				orderFirst.put("PRICE2", totalPrice);
				// 주번 번호별 대표 상품을 별도의 리스트로 모아서 model 에 저장
				finalList.add(orderFirst);
			}
			model.addAttribute("orderList", finalList);         
		}      
		model.addAttribute("title", " 진행중인 주문내역");
		return "mypage/mypage";
	}
	
	@RequestMapping(value="/orderAll.do")  
	public String orderAll( HttpServletRequest request, Model model ) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			return "member/login";
		}else {
			// 최종 mypage.jsp 에 전달될 리스트
			ArrayList<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			// 현재 로그인 중인 유저의 진행중인 주문번호 리스트 조회
			paramMap1.put("id", loginUser.get("ID").toString());
			paramMap1.put("ref_cursor", null);
			os.selectOseqById( paramMap1 );
			ArrayList<HashMap<String, Object>> oseqList = (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor");
			// 주문번호별 주문내역을 조회
			for( HashMap<String, Object> result : oseqList ) {
				int oseq = Integer.parseInt( result.get("OSEQ").toString() );
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("oseq", oseq);
				paramMap2.put("reg_cursor", null);

				os.listOrderByOseq(paramMap2);
	            
				ArrayList<HashMap<String, Object>> orderListByOseq = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
	            
				HashMap<String, Object> orderFirst = orderListByOseq.get(0);
				orderFirst.put("PNAME" , (String)orderFirst.get("PNAME") + "포함 " + orderListByOseq.size() + "건" );
	            
				int totalPrice = 0;
				for( HashMap<String, Object> order : orderListByOseq ) {
					totalPrice += Integer.parseInt( order.get("QUANTITY").toString() )
							* Integer.parseInt( order.get("PRICE2").toString() ); 
				}
				orderFirst.put("PRICE2", totalPrice);
				// 주번 번호별 대표 상품을 별도의 리스트로 모아서 model 에 저장
				finalList.add(orderFirst);
			}
			model.addAttribute("orderList", finalList);         
		}      
		model.addAttribute("title", " 총 주문내역");
		return "mypage/mypage";
	}
	 
	@RequestMapping(value="orderDetail.do")
	public String orderDetail(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
		if(mvo == null) {
			return "redirect:/loginForm.do";
		}else {
			HashMap<String, Object> order = new HashMap<String, Object>();
			order.put("oseq", Integer.parseInt(request.getParameter("oseq")));
			order.put("ref_cursor", null);
				
			os.getOseqOrder(order);
				
			ArrayList<HashMap<String, Object>> orderDetail = (ArrayList<HashMap<String, Object>>) order.get("ref_cursor");
				
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("oseq", Integer.parseInt(request.getParameter("oseq")));
			paramMap.put("ref_cursor", null);
				
			os.selectOrderDetailList(paramMap);
				
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
			int totalPrice = 0;
			for(HashMap<String, Object> ovo : list)
				totalPrice += Integer.parseInt(ovo.get("PRICE2").toString()) * Integer.parseInt(ovo.get("QUANTITY").toString());
				
			model.addAttribute("orderDetail", orderDetail.get(0));
			model.addAttribute("orderList", list);
			model.addAttribute("totalPrice", totalPrice);
			return "mypage/orderDetail";
		}
	}
}
