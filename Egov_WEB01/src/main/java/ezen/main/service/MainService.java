package ezen.main.service;

import ezen.main.dto.MemberVO;
import ezen.main.dto.TransferVO;

public interface MainService {
	/* MemberVO getMember(String id); */
	TransferVO getMember(String id);

	int getID(String id);

	void insertMember(MemberVO mvo);

	void updateMember(MemberVO mvo);
	
}
