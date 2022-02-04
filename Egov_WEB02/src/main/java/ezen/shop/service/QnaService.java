package ezen.shop.service;

import java.util.HashMap;

public interface QnaService {

	void getQnaList(HashMap<String, Object> paramMap);

	void getQnaByQseq(HashMap<String, Object> paramMap);
	
}
