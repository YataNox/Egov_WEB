package ezen.shop.service;

import java.util.HashMap;

public interface AdminService {

	void getAdmin(HashMap<String, Object> paramMap);

	void getProductList(HashMap<String, Object> paramMap);

	void getAllCount(HashMap<String, Object> paramMap);

	void insertProduct(HashMap<String, Object> paramMap);

	void getProductDetail(HashMap<String, Object> paramMap);

	void updateProduct(HashMap<String, Object> paramMap);
	
}
