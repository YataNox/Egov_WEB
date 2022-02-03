package ezen.shop.service;

import java.util.HashMap;

public interface CartService {

	void insertCart(HashMap<String, Object> paramMap);

	void selectCart(HashMap<String, Object> paramMap);

}
