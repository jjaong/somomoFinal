package com.kh.somomo.group.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.somomo.group.model.service.GroupService;
import com.kh.somomo.group.model.vo.GroupRoom;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("list.gr")
	public ModelAndView selectList(ModelAndView mv) {
		mv.addObject("list",groupService.selectList()).setViewName("group/community");
		return mv;
	}
	
	@RequestMapping("insertForm.gr")
	public String insertForm(ModelAndView mv) {
		return "group/createGroup";
	}
	
	
	@RequestMapping("insert.gr")
	public String insertGroup(GroupRoom gr, Model model, HttpSession session){
		int result = groupService.insertGroup(gr);
		
		if(result>0) {
			session.setAttribute("alertMsg", "그룹방 추가 성공");
			return "redirect:list.gr";
		}else { 
			model.addAttribute("errorMsg","그룹방 추가 실패");
			return "common/errorPage";
		}
	}
	
}


