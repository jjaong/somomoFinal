package com.kh.somomo.group.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.somomo.group.model.service.GroupService;
import com.kh.somomo.group.model.vo.GroupMember;
import com.kh.somomo.group.model.vo.GroupRoom;

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
	public String insertGroup(GroupRoom gr, GroupMember gm, Model model, HttpSession session){
		int result = groupService.insertGroup(gr);
		int result2 = groupService.insertMember(gm);
		
		if(result>0) {
			return "redirect:list.gr";
		}else { 
			model.addAttribute("errorMsg","그룹방 추가 실패");
			return "common/errorPage";
		}
	}
	
	
	
}


