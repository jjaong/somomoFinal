package com.kh.somomo.group.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupRoom {

	// 테이블 : GROUP (그룹방)
	private int groupNo;
	private int regionNo;
	private int categoryNo;
	private String groupName;
	private String groupImg;
	private String groupDetail;
	private String groupType;
	private String groupDate;
	private int memberCount; // 가입한 회원수를 받기 위한 필
	private String userRank; // GROUP_MEMBER 와 조인할때 반환되는 값을 받아주기 위한것
}
