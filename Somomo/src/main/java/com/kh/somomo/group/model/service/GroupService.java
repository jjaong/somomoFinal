package com.kh.somomo.group.model.service;

import java.util.ArrayList;

import com.kh.somomo.group.model.vo.GroupBoard;
import com.kh.somomo.group.model.vo.GroupMember;
import com.kh.somomo.group.model.vo.GroupRoom;

public interface GroupService {
	
	//그룹방 리스트 조회
	ArrayList<GroupRoom> selectList();
	
	//그룹방 추가
	int insertGroup(GroupRoom gr);
	
	//그룹방 멤버 추가
	int insertMember(GroupMember gm);
	
	//그룹방 캘린더 추가
	int insertCalendar();
	
	//멤버 수 세기
	int memberCount(String count);
	
	//관리 그룹방 리스트
	ArrayList<GroupRoom> manageGroup();
	
	// 테스트
	
	// 그룹방
	GroupRoom selectGroup(int groupNo);
	
	int getGroupNo();
	
		
}
