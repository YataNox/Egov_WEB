package ezen.shop.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ezen.shop.dto.Paging;
import ezen.shop.service.AdminService;
import ezen.shop.service.ProductService;

@Controller
public class AdminController {
	@Resource(name="AdminService") 
	AdminService as;
	
	@Resource(name="ProductService") 
	ProductService ps;
	
	@RequestMapping(value="admin.do")
	public String admin(HttpServletRequest request, Model model) {
		
		return "admin/adminLogin";
	}
	
	@RequestMapping(value="adminLogin.do")
	public String adminLogin(HttpServletRequest request, Model model) {
		String workId = request.getParameter("workId");
		String workPwd = request.getParameter("workPwd");
				
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workId", workId);
		paramMap.put("ref_cursor", null);
		
		as.getAdmin(paramMap);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		
		if(list.size() == 0) {
			model.addAttribute("message", "해당 관리자 아이디가 없습니다.");
			return "admin/adminLogin";
		}else {
			HashMap<String, Object> avo = list.get(0);
			
			if(avo.get("PWD") == null) {
				model.addAttribute("message", "비밀번호 Essue");
				return "admin/adminLogin";
			}else if(!avo.get("PWD").equals(workPwd)) {
				model.addAttribute("message", "비밀번호가 틀렸습니다.");
				return "admin/adminLogin";
			}else if(avo.get("PWD").equals(workPwd)) {
				HttpSession session = request.getSession();
				session.setAttribute("loginAdmin", avo);
				return "redirect:/productList.do";
			}else {
				model.addAttribute("message", "기타 오류로 접속 실패");
				return "admin/adminLogin";
			}
		}
	}
	
	@RequestMapping(value="productList.do")
	public String productList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin = (HashMap<String, Object>)session.getAttribute("loginAdmin");
		if(loginAdmin == null)
			return "redirect:/admin.do";
		else {
			int page = 1;
			if(request.getParameter("first") != null && request.getParameter("first").equals("y")) {
				page = 1;
				session.removeAttribute("page");
			}
			else if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			}else {
				page = 1;
				session.removeAttribute("page");
			}
			
			String key = "";
			if(request.getParameter("first") != null && request.getParameter("first").equals("y")) {
				key = "";
				session.removeAttribute("key");
			}
			else if(request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else if(session.getAttribute("key") != null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cnt", 0);
			as.getAllCount(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("key", key);
			paramMap.put("ref_cursor", null);
			as.getProductList(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			request.setAttribute("paging", paging);
			request.setAttribute("key", key);
			model.addAttribute("productList", list);	
			
			return "admin/product/productList";
		}
	}
	
	@RequestMapping(value="adminLogout.do")
	public String adminLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/admin.do";
	}
	
	@RequestMapping(value="productWriteForm.do")
	public String productWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin = (HashMap<String, Object>)session.getAttribute("loginAdmin");
		if(loginAdmin == null)
			return "redirect:/admin.do";
		else {
			String[] kind = {"Heels", "Boots", "Sandals", "Sneakers", "Sleeper", "On sale"};
			
			model.addAttribute("kindList", kind);
			return "admin/product/productWriteForm";
		}
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup.do")
	@ResponseBody
	public HashMap<String, Object> fileup(HttpServletRequest request, Model model) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String savePath = context.getRealPath("/product_images");
		String filename="";
		try {
				MultipartRequest multi = new MultipartRequest(
				request, savePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()	
			);
			filename = multi.getFilesystemName("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		resultMap.put("STATUS", 1);
		resultMap.put("IMG", filename);
		resultMap.put("FILENAME", filename);
		return resultMap;
	}
	
	@RequestMapping(value="productWrite.do", method = RequestMethod.POST)
	public String productWrite(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin = (HashMap<String, Object>)session.getAttribute("loginAdmin");
		if(loginAdmin == null)
			return "redirect:/admin.do";
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("kind", request.getParameter("kind"));
			paramMap.put("name", request.getParameter("name"));
			paramMap.put("price1", request.getParameter("price1"));
			paramMap.put("price2", request.getParameter("price2"));
			paramMap.put("price3", request.getParameter("price3"));
			paramMap.put("content", request.getParameter("content"));
			paramMap.put("imgfilename", request.getParameter("image"));
			
			as.insertProduct(paramMap);
			
			return "redirect:/productList.do";
		}
	}
}
