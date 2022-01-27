package ezen.main.service;

import java.util.HashMap;

import ezen.main.dto.MemberVO;
import ezen.main.dto.TransferVO;

public interface MainService {
	/* MemberVO getMember(String id); */
	void getMember(HashMap<String, Object> paramMap);

	int getID(String id);

	void insertMember(HashMap<String, Object> paramMap);

	void updateMember(HashMap<String, Object> paramMap);
	
}
