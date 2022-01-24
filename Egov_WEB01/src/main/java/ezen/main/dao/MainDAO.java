package ezen.main.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.MemberVO;
import ezen.main.dto.TransferVO;

@Mapper(value="MainDAO")
public interface MainDAO {
	/* MemberVO getMember(String id); */
	void getMember(TransferVO container);

	int getID(String id);

	void insertMember(MemberVO mvo);

	void updateMember(MemberVO mvo);

}
