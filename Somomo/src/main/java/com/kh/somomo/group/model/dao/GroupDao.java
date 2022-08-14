package com.kh.somomo.group.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.somomo.common.model.vo.PageInfo;
import com.kh.somomo.common.model.vo.RegionCategory;
import com.kh.somomo.group.model.vo.GroupCategory;
import com.kh.somomo.group.model.vo.GroupMember;
import com.kh.somomo.group.model.vo.GroupRoom;

@Repository
public class GroupDao {
	
	public int selectGroupListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("groupMapper.selectGroupListCount");
	}
	
	public ArrayList<RegionCategory> selectRegionCategoryList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectRegionCategoryList");
	}
	
	public ArrayList<GroupCategory> selectGroupCategoryList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectGroupCategoryList");
	}

	public ArrayList<GroupRoom> selectList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectList");
	}
	
	public ArrayList<GroupRoom> myGroupList(SqlSessionTemplate sqlSession, String userId) {
		return (ArrayList)sqlSession.selectList("groupMapper.myGroupList", userId);
	}
	
	public int insertGroup(SqlSessionTemplate sqlSession, GroupRoom gr) {
		return sqlSession.insert("groupMapper.insertGroup", gr);
	}
	public ArrayList<GroupRoom> selectCategoryGroup(SqlSessionTemplate sqlSession, String keyword) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectCategoryGroup", keyword);
	}
	
	public ArrayList<GroupRoom> searchGroup(SqlSessionTemplate sqlSession, String search) {
		
		return (ArrayList)sqlSession.selectList("groupMapper.searchGroup", search);
	}

	public int insertMember(SqlSessionTemplate sqlSession, GroupMember gm) {
		return sqlSession.insert("groupMapper.insertGroupMember",gm);
	}
	
	public int insertCalendar(SqlSessionTemplate sqlSession) {
		return sqlSession.insert("groupMapper.insertCalendar");
	}

	public int getGroupNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("groupMapper.getGroupNo");
	}
	public GroupRoom selectGroup(SqlSessionTemplate sqlSession, int groupNo) {
		return sqlSession.selectOne("groupMapper.selectGroup", groupNo);
	}

	public ArrayList<GroupMember> selectMemberList(SqlSessionTemplate sqlSession, int groupNo) {
		return (ArrayList)sqlSession.selectList("groupMapper.selectMemberList", groupNo);
	}

	public int updateGroup(SqlSessionTemplate sqlSession, GroupRoom g) {
		return sqlSession.update("groupMapper.updateGroup", g);
	}

	public int deleteGroup(SqlSessionTemplate sqlSession, int groupNo) {
		return sqlSession.delete("groupMapper.deleteGroup", groupNo);
	}

	public int updateType(SqlSessionTemplate sqlSession, GroupRoom g) {
		return sqlSession.update("groupMapper.updateType", g);
	}


}
