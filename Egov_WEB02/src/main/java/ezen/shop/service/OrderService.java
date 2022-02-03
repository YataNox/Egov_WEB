package ezen.shop.service;

import java.util.HashMap;

public interface OrderService {

	void insertOrder(HashMap<String, Object> paramMap);

	void selectOrder(HashMap<String, Object> paramMap);

	void selectOrderResultOne(HashMap<String, Object> paramMap);

}
