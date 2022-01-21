package ezen.main.dao;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.MemberVO;

@Mapper(value="MainDAO")
public interface MainDAO {
	MemberVO getMember(String id);

	int getID(String id);

	void insertMember(MemberVO mvo);

}
