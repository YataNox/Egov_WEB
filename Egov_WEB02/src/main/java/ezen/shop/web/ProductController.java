package ezen.shop.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.ProductService;

@Controller
public class ProductController {
	@Resource(name="ProductService") 
	ProductService ps;
	// ps는 ProductSerivce 인터페이스의 레퍼런스 변수이고, 그 변수에 저장되는 인스턴스는 @Service 어노테이션이 있는
	// ProductServiceimpl 클래스의 인스턴스가 @Resource에 의해서 자동주입됩니다.
	// @Autowired는 클래스 이름으로 주동주입할 클래스를 검색하지만, @Resource는 어노테이션 value에 있는 이름으로 검색합니다.
	
	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request, Model model) {
		return "main";
	}
}
