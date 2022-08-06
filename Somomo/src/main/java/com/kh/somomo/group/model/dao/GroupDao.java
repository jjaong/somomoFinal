package com.kh.somomo.group.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.somomo.group.model.vo.GroupBoard;
import com.kh.somomo.group.model.vo.GroupRoom;

@Repository
public class GroupDao {

	public ArrayList<GroupRoom> selectList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectList");
	}

	public GroupRoom selectGroup(SqlSessionTemplate sqlSession, int groupNo) {
		return sqlSession.selectOne("groupMapper.selectGroup", groupNo);
	}

	public int insertGroup(SqlSessionTemplate sqlSession, GroupRoom gr) {
		return sqlSession.insert("groupMapper.insertGroup", gr);
	}
	
	
}
