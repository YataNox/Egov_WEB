package ezen.main.dto;

import java.util.List;

// 파라미터가 될 수도 있고, result가 될수도 있는 클래스
public class TransferVO {
	private String id;
	private int num;
	private List list;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
