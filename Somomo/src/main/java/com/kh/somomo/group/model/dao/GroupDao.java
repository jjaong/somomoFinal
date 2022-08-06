package com.kh.somomo.group.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.somomo.group.model.vo.GroupBoard;
import com.kh.somomo.group.model.vo.GroupMember;
import com.kh.somomo.group.model.vo.GroupRoom;

@Repository
public class GroupDao {

	public ArrayList<GroupRoom> selectList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectList");
	}

	public int insertGroup(SqlSessionTemplate sqlSession, GroupRoom gr) {
		return sqlSession.insert("groupMapper.insertGroup", gr);
	}

	public int insertMember(SqlSessionTemplate sqlSession, GroupMember gm) {
		return sqlSession.insert("groupMapper.insertGroupMember",gm);
	}

	public int memberCount(SqlSessionTemplate sqlSession, String count) {
		return sqlSession.insert("groupMapper.memberCount", count);
	}

	public ArrayList<GroupRoom> manageGroup(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("groupMapper.manageGroup");
	}
	
}
