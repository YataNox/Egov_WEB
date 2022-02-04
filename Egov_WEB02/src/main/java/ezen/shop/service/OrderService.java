package ezen.shop.service;

import java.util.HashMap;

public interface OrderService {

	void insertOrder(HashMap<String, Object> paramMap);

	void selectOrder(HashMap<String, Object> paramMap);

	void selectOrderResultOne(HashMap<String, Object> paramMap);

	void selectOrderAll(HashMap<String, Object> paramMap);

	void selectOseqById(HashMap<String, Object> oseq);

	void listOrderByOseq(HashMap<String, Object> paramMap2);

	void selectOrderDetailList(HashMap<String, Object> paramMap);

	void getOseqOrder(HashMap<String, Object> order);

}
