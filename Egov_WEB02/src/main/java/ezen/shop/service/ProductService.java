package ezen.shop.service;

import java.util.HashMap;

public interface ProductService {

	void getBestList(HashMap<String, Object> paramMap1);

	void getNewList(HashMap<String, Object> paramMap2);

	void getKindList(HashMap<String, Object> paramMap1);

	void getProduct(HashMap<String, Object> paramMap);

}
