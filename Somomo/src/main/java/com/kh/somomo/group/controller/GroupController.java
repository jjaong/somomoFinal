package com.kh.somomo.group.controller;


import static com.kh.somomo.common.template.FileRename.saveFile;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.somomo.common.model.vo.PageInfo;
import com.kh.somomo.common.template.Pagination;
import com.kh.somomo.group.model.service.GroupService;
import com.kh.somomo.group.model.vo.GroupMember;
import com.kh.somomo.group.model.vo.GroupRoom;
import com.kh.somomo.member.model.vo.Member;


@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	//그룹방 리스트 전체 화면
	@RequestMapping("groupRoom.gr")
	public ModelAndView selectGroupList(@RequestParam(value="cpage", defaultValue="1") int currentPage,
										HttpSession session, ModelAndView mv) throws ParseException {
		
		PageInfo pi = Pagination.getPageInfo(groupService.selectGroupListCount(), currentPage, 10, 9); // 페이징처리
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		ArrayList<GroupRoom> gList = groupService.selectList();
		mv.addObject("pi", pi)										  // 페이징
	      .addObject("list", gList)
		  .addObject("myGroupList", groupService.myGroupList(userId)) // 내가 관리/가입한 그룹 리스트
		  .addObject("cList", groupService.selectGroupCategoryList()) // 그룹카테고리
		  .setViewName("group/community");
		
		return mv;
	}
	
	//그룹방 리스트
	/*@RequestMapping(value="list.gr")
	public String groupList(@RequestParam(value="cpage", defaultValue="1") int currentPage,
				 			String keyword, Model model) throws ParseException {
		PageInfo pi = Pagination.getPageInfo(groupService.selectGroupListCount(), currentPage, 10, 9); // 페이징처리
		ArrayList<GroupRoom> gList = groupService.selectList(pi); // 전체 그룹 리스트
		model.addAttribute("list", gList); 
		
		return "group/groupList";
	}*/
	
	//그룹방 리스트 --페이징 아직 xxx
	@ResponseBody
	@RequestMapping(value="list.gr")
	public ArrayList<GroupRoom> groupList(String keyword) {
		return groupService.selectCategoryGroup(keyword); //작명 진짜 다 또이또이로 생각남...  작명센스 부족
	}
	
	//그룹방 검색 리스트
	@ResponseBody
	@RequestMapping(value="search.gr")
	public ModelAndView searchList(@RequestParam(value="cpage", defaultValue="1") int currentPage,
									HttpSession session, ModelAndView mv, String search) {
		ArrayList<GroupRoom> gList = groupService.searchGroup(search);
		PageInfo pi = Pagination.getPageInfo(groupService.selectGroupListCount(), currentPage, 10, 9); // 페이징처리
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		mv.addObject("list", gList)
		  .addObject("pi",pi)
		  .addObject("myGroupList", groupService.myGroupList(userId))
		  .setViewName("group/community");
		return mv;
	}
	
	// 그룹방 개설 페이지로 요청 처리
	@RequestMapping("insertForm.gr")
	public String insertForm(Model model) {
		model.addAttribute("rList",groupService.selectRegionCategoryList())
			 .addAttribute("cList", groupService.selectGroupCategoryList());
		return "group/createGroup";
	}
	
	/**
	 * 그룹방 개설 처리
	 * - 사용자정의 그룹이미지(upfile)가 없을경우 서버에 저장되어있는 기본 이미지중 하나가 자동 선택되고 gr.groupImg에 담겨 넘어옴
	 * - 3개의 insert문 실행 : GROUP_ROOM, GROUP_MEMBER, GROUP_CALENDAR
	 * - 성공적으로 그룹방 개설이 되면 상세페이지로 이동함
	 */
	@RequestMapping("insert.gr")
	public String insertGroup(GroupRoom gr, GroupMember gm, MultipartFile upfile, Model model, HttpSession session){
		
		// 사용자가 자기 사진을 추가
		// 기존의 profileImg에 등록된 사진을 변경해준다. 
		// => 사용자가 그룹방 메인 사진을 따로 추가 하지 않을 경우, 서버에서 임의로 제공하는 사진의 경로가 gr.groupImg에 들어가 있음
		if(!upfile.getOriginalFilename().equals("")) {
			HashMap<String, String> map = saveFile(upfile, session, "img/group/userGroupImg");
			
			gr.setGroupImg(map.get("changeName"));	
		}
		
		int result = groupService.insertGroup(gr);   // GROUP_ROOM
		int result2 = groupService.insertMember(gm); // GROUP_MEMBEr
		int result3 = groupService.insertCalendar(); // GROUP_CALENDAR
		
		int groupNo = groupService.getGroupNo();     // 상세페이지로 이동하기위해 식별값인 groupNo를 조회해서 가져옴
		
		if(result*result2*result3 > 0) {
			return "redirect:detail.gr?gno=" + groupNo; // 쿼리스트링으로 전달
		}else { 
			model.addAttribute("errorMsg","그룹방 추가 실패");
			return "common/errorPage";
		}
	}
	
	// 그룹방 상세
	// 상세보기 페이지에서 필요한 정보 : 해당 그룹방에 대한 정보, 가입된 회원들의 리스트
	@RequestMapping("detail.gr")
	public ModelAndView groupDetail(int gno, ModelAndView mv) {
		
		GroupRoom gr = groupService.selectGroup(gno); 	     			   // 특정 그룹방에대한 정보를 담음
		ArrayList<GroupMember> mList = groupService.selectMemberList(gno); // 가입된 회원리스트
		
		if (gr != null) {
			mv.addObject("g", gr)
			  .addObject("mList", mList)
			  .setViewName("group/groupDetail");
		} else {
			mv.addObject("errorMsg", "그딴 그룹방은 없는디요??").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 그룹방 상세
	// 그룹방 설정 변경 => 사이드바 메뉴에 표시될 정보를 위해 계속 정보 가져옴
	@RequestMapping("setting.gr")
	public ModelAndView setting(int groupNo, ModelAndView mv) {
		
		mv.addObject("g", groupService.selectGroup(groupNo))
		  .addObject("mList", groupService.selectMemberList(groupNo))
		  .setViewName("group/groupSetting");
		
		return mv;
	}
	
	@RequestMapping("updateForm.gr")
	public String updateGroup(int groupNo, Model model) {
		
		model.addAttribute("g", groupService.selectGroup(groupNo))
			 .addAttribute("rList",groupService.selectRegionCategoryList())
			 .addAttribute("cList", groupService.selectGroupCategoryList());;
		
		return "group/updateGroup";
	}
	
	@RequestMapping("update.gr")
	public String updateGroup(GroupRoom g, MultipartFile reupfile, String originFile, HttpSession session) {
		// 설정 변경에서 변경 가능한 정보들 : 지역/그룹 카테고리, 그룹명, 그룹 설명, 그룹사진
		
		// g.groupImg에는 들어있는 값 :
		// 	- 사이트 제공하는 기본 이미지 선택했을때는 해당 경로가..
		//  - 업로드 파일을 이용해 전송됐을때는 빈값...
		// originFile에는 이전에 사용중이던 대표이미지의 경로가 넘어옴
		
		// 수정 전 사용하던 그룹 대표 이미지가 사이트에서 제공하던 사진이였는지, 사용자가 직접 업로드한 사진이였는지 확인하는 메소드
		// true : 사이트에서 제공하는 사진, false : 사용자가 업로드한 파일
		boolean checkImg = checkDefaultImg(session, originFile); 
		
		// 새로운 업로드 파일이 있는지 확인
		if(!reupfile.getOriginalFilename().equals("")) {
			
			// 새로운 업로드 파일이 존재함 -> 기존의 사진이 사이트에서 제공한 기본 사진 이였는지 사용자가 업로드했던 사진인지 확인
			if(!checkImg) {
				// 사용자가 등록했던 사진 찾아서 삭제
				new File(session.getServletContext().getRealPath(originFile)).delete();
			} 
			// g.groupImg 필드에 새롭게 업로드된 파일경로 설정
			HashMap<String, String> map = saveFile(reupfile, session, "img/group/userGroupImg");
			g.setGroupImg(map.get("changeName"));	
			
		} else {
			// 새롭게 업로드된 파일이 없음 -> 그룹이미지가 변경 되었는지 확인해야됨
			// 사용자 지정 이미지의 경우 checkImg에 의해 걸러지기 때문에 사진을 따로 변경하지 않았을때는 지워주면 안됨
			if(!g.getGroupImg().equals(originFile)) {
				
				// 그룹이미지 정보가 변경됨 : 이경우에는 사용자가 업로드했던 파일을 내리고 사이트 제공 이미지를 재 선택함
				if (!checkImg) {
					// 사용자가 등록했던 사진은 삭제 해준다.
					new File(session.getServletContext().getRealPath(originFile)).delete();
				}				
			}
		}
		
		int result = groupService.updateGroup(g);
		
		return "redirect:setting.gr?groupNo=" + g.getGroupNo();
	}
	
	@RequestMapping("updateType.gr")
	public String updateType(GroupRoom g) {
		
		int result = groupService.updateType(g);
		
		return "redirect:setting.gr?groupNo=" + g.getGroupNo();
	}
	
	
	@RequestMapping("delete.gr")
	public String deleteGroup(int groupNo, HttpSession session) {
		
		GroupRoom g = groupService.selectGroup(groupNo);
		
		boolean checkImg = checkDefaultImg(session, g.getGroupImg());
		
		if(!checkImg) {
			// 사용자가 등록한 사진은 찾아서 삭제 해줌
			new File(session.getServletContext().getRealPath(g.getGroupImg())).delete();
		}
		
		int result = groupService.deleteGroup(groupNo);
		
		return "redirect:groupRoom.gr";
	}
	
	
	
	
	
	
	
	
	
	
	public boolean checkDefaultImg(HttpSession session, String originFile) {
		
		String IMG_DRIECTORY = session.getServletContext().getRealPath("/resources/img/group/defaultGroupImg");
		
		String checkImg = originFile.substring(36);
		
		File dir = new File(IMG_DRIECTORY);
		
		String[] defaultImgs = dir.list();
		
		for(String img : defaultImgs) {
			if (img.equals(checkImg)) {
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping("calendar.gr")
	public String calendarForm() {	
		return "group/groupCalendar";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 이거는 임시 입니다.
//	@RequestMapping("apply.gr")
//	@ResponseBody
//	public String applyGroup(GroupJoinApply applyInfo) {
//		
//		System.out.println(applyInfo);
//		
//		int result = groupService.applyGroup(applyInfo);
//		
//		if(result > 0) { // 그룹 가입 신청 완료
//			return "NNNNY";
//		} else {
//			return "NNNNN";
//		}
//	}
	
}



