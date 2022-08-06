package com.kh.somomo.group.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.somomo.group.model.service.GroupService;
import com.kh.somomo.group.model.vo.GroupRoom;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("list.gr")
	public String selectList(ModelAndView mv) {
		return "group/community";
	}
	
	
	@RequestMapping("insertForm.gr")
	public String insertForm(ModelAndView mv) {
		return "group/createGroup";
	}
	
}


