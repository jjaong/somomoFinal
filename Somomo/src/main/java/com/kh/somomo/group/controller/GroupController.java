package com.kh.somomo.group.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.somomo.group.model.service.GroupService;
import com.kh.somomo.group.model.vo.GroupMember;
import com.kh.somomo.group.model.vo.GroupRoom;
import static com.kh.somomo.common.template.FileRename.*;


@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("list.gr")
	public ModelAndView selectList(ModelAndView mv, String count) {
		mv.addObject("list",groupService.selectList()).addObject("manageList",groupService.manageGroup()).setViewName("group/community");
		return mv;
	}
	
	@RequestMapping("insertForm.gr")
	public String insertForm() {
		return "group/createGroup";
	}
	
	
	@RequestMapping("insert.gr")
	public String insertGroup(GroupRoom gr, GroupMember gm, MultipartFile upfile, Model model, HttpSession session){
		
		if(!upfile.getOriginalFilename().equals("")) {
			// 사용자가 파일을 등록함 => 파일명 수정후 올리는거야
			// com.kh.common.template에 보면 유진님이 FileRename class 만들어 놓으신거 사용함!! -> 아마 보면 어떻게 사용하는지 알 수 있을겨
			// 마지막 매개변수는 저장경로에 들어가는 폴더명 : 사진 저장되는게 많아서 폴더별로 구분하려고!! 
			// 사용자가 저장한 그룹이미지는 resources/img/userGroupImg로 들어갑니다
			HashMap<String, String> map = saveFile(upfile, session, "img/userGroupImg");
			
			// changeName 키값에 수정된 파일명이 저장됨!! 그거 세터로 세팅
			gr.setGroupImg(map.get("changeName"));
		}
		
		int result = groupService.insertGroup(gr);
		int result2 = groupService.insertMember(gm);
		
		// 그룹방마다 캘린더도 만들어 줘야함!!
		int result3 = groupService.insertCalendar();
		
		// 그럴일은 없지만 3개중 하나라도 실패하면 에러페이지로 보내버리기
		// 사용자가 처음 생성하면 해당 그룹방으로 보내버린다. 그룹방 번호가 필요함
		// 우리 그룹방 식별자로 사용할 수 있는게 groupNo라 생성 시점에서 가져올수 있는 방법은 currval 밖에 없는거 같은디..
		// 졸려서 집중이 안된다... 이렇게 하는거 틀린걸수도 있어요.
		int groupNo = groupService.getGroupNo();
		System.out.println(groupNo);
		
		
		if(result*result2*result3 > 0) {
			return "redirect:groupDetail.gr?groupNo=" + groupNo;
		}else { 
			model.addAttribute("errorMsg","그룹방 추가 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("groupDetail.gr")
	public ModelAndView groupDetail(int groupNo, ModelAndView mv) {
		
		// 그룹방을 생성하고 왔다
		// 뿌려줘야할 정보 1) 그룹방의 정보
		GroupRoom gr = groupService.selectGroup(groupNo);
		
		System.out.println(gr);
		
		if (gr != null) {
			mv.addObject("gr", gr).setViewName("group/groupDetail");
		} else {
			mv.addObject("errorMsg", "그딴 그룹방은 없는디요??").setViewName("common/errorPage");
		}
		return mv;
	}
	
	
}


