<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 전체 :  게시판 g.categoryNo가 1,2,3,4일때  -->
<!-- 나머지 : CategoryNo = 게시판 g.cateogoryNo -->
<c:forEach var="g" items="${list}">
	 <div class="group">
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
	          <div class="group-btn">
	              <button>그룹 가입</button>
	          </div>
	      </div>
	 </div>
</c:forEach>