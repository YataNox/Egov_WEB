package ezen.main.service;

import ezen.main.dto.MemberVO;

public interface MainService {
	MemberVO getMember(String id);

	int getID(String id);

	void insertMember(MemberVO mvo);
	
}
