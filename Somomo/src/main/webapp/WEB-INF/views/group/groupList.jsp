<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 전체 :  게시판 g.categoryNo가 1,2,3,4일때  -->
<!-- 나머지 : CategoryNo = 게시판 g.cateogoryNo -->
<c:forEach var="g" items="${list}">
	<div class="group">
		<div class="group-main">
			<input class="groupNo" type="hidden" value="${g.groupNo}">
			<div class="group-header">
				<img src="${g.groupImg}" alt="" />
			</div>
			<div class="group-body">
				<span class="tag tag-development">${g.categoryNo}</span>
				<h4>
					${g.groupName}
				</h4>
				<div class="group-info">
					<span class="group-member">멤버 ${g.memberCount}명</span>
					<span>${g.groupType}</span>
				</div>
			</div>
		</div>
		<div class="group-foot">
			<div class="group-btn">
				<button class="apply">그룹 가입</button>
			</div>
		</div>

		<div id="applyModal" class="modal">
			<!-- Modal content -->
			<div class="modal-content">
				<input type="hidden" class="md-groupNo" type="text" name="groupNo">
				<input type="hidden" name="userId" class="md-userId" value="${loginUser.userId}">

				<div class="modal-header">
					<div class="header-title">
						<div class="header-title__groupName"></div>
						<span>간단한 자기소개를 입력해주세요.</span>
					</div>
				</div>
				
				<div class="modal-body">
					<textarea name="greeting" class="greeting" placeholder="여기에 작성하세요."></textarea>
				</div>
				<div class="modal-foot">
					<button type="button" class="close">취소</button>
					<button class="disabled" onclick="apply();">가입하기</button>
				</div>
			</div>
		</div>

	</div>
</c:forEach>



    
