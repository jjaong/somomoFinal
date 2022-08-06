<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!----------- CSS --------------->
    <link rel="stylesheet" href="resources/css/header.css">
    <link rel="stylesheet" href="resources/css/groupList.css">
    <!----------- 아이콘 CSS 링크 ------->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <script src="https://kit.fontawesome.com/567fbbaed5.js" crossorigin="anonymous"></script>
    <!----------- 아이콘 CSS 링크 version 2------->

    <title>그룹리스트</title> 

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <div class="wrapper">
        <div>
            <header>
                <div class="logo-name">
                    <div class="logo-image">
                        <a href="index.html"><img src="resources/img/logo.jpg" alt=""></a>
                        
                    </div>
                    <span class="logo_name">SoMoMo</span>
                </div>
        
                <div class="top">
                    <form action="">
                        <div class="search-box">
                            <button type="submit"><i class="uil uil-search"></i></button>
                            <input type="text" placeholder="검색">
                        </div>
                    </form>
                </div>
    
                <ul class="menu-list">
                    <li><a href=""><i class="uil uil-bell"></i></a></li>
                    <li><a href=""><i class="uil uil-comment-dots"></i></a></li>
                    <li><a href=""><i class="uil uil-user"></i></a></li>
                </ul>
            </header>
        </div>
    </div>

    <section>
        <nav>
            <div class="nav-col">
                <h2>Group</h2>
                <div class="nav-col-group">
                    <i class="fas fa-clipboard-list"></i>
                    <span>그룹 목록</span>
                </div>
                <div class="nav-col-group">
                    <i class="uil uil-search"></i>
                    <span>찾아보기</span>
                </div>
    
                <div class="create-newGroup"><a href="insertForm.gr">+ 새 그룹 만들기</a></div>
    
                <div class="horizontal"></div>
            </div>
    
            <div class="nav-col">
                <h2>관리중인그룹</h2>
                <div>
                    <img src="resources/img/peach3.jpeg" alt="">
                    <span>KH - 모2조</span>
                </div>
                <div class="horizontal"></div>
    
                <h2>가입한 그룹</h2>
                <div>
                    <img src="resources/img/aju.png" alt="">
                    <span>아주대학교 커뮤니티</span>
                </div>
    
                <div>
                    <img src="resources/img/kh.jpeg" alt="">
                    <span>KH정보교육원</span>
                </div>
            </div>
        </nav>
		
        <main class="content">
            <div class="list-outer">
                <c:forEach var="g" items="${list}">
                	<div class="group">
	                    <div class="group-img">
	                        <img src="${g.groupImg}" alt="그룹대표이미지">
	                    </div>
	                    <div class="group-element">
	                        <div class="group-name">
	                            <span>${g.groupName}</span>
	                        </div>
	                        <div class="group-info">
	                            <span>멤버</span>
	                            <span>|</span>
	                            <c:choose>
	                            	<c:when test="${g.groupType eq 'A'}">
	                            		<span>공개</span>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<span>비공개</span>
	                            	</c:otherwise>
	                            </c:choose>
	                        </div>
	                        <div class="group-btn">
	                            <button>그룹 가입</button>
	                        </div>
	                    </div>
               		</div>
                </c:forEach>
            </div>
        </main>
    </section>
    
    </body>
</html>
