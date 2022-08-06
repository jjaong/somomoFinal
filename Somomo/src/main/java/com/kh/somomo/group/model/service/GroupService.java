package com.kh.somomo.group.model.service;

import java.util.ArrayList;

import com.kh.somomo.group.model.vo.GroupBoard;
import com.kh.somomo.group.model.vo.GroupRoom;

public interface GroupService {
	
	//그룹방 리스트 조회
		ArrayList<GroupRoom> selectList();

	//그룹방 상세 조회
	GroupRoom selectGroup(int groupNo);
	
	//그룹방 추가
	int insertGroup(GroupRoom gr);
		
		
}
