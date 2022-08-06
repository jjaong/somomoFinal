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
    <link rel="stylesheet" href="resources/css/style2.css">
    <!----------- 아이콘 CSS 링크 ------->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <script src="https://kit.fontawesome.com/567fbbaed5.js" crossorigin="anonymous"></script>
    <!----------- 아이콘 CSS 링크 version 2------->
    <!-- Boxicons CSS -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>

    <title>소모모</title> 
</head>
<body>

<!--------------------- 헤더 ------------------------>

    <div class="wrapper">
        <div>
            <header>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="/src/img/logo.jpg" alt="">
                    </div>
                    <span class="logo_name">SoMoMo</span>
                </div>
        
                <div class="top">
                    <form action="">
                        <div class="search-box">
                            <i class="uil uil-search"></i>
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



    <!----------------------- 메인 컨텐츠 ------------------------------>


    <div class="main-section">


        <!-------------------- 메인 컨텐츠 헤더 --------------------->
        <div class="main-header-container">
            <div class="main-header">
                <ul class="main-header-bar">
                    <li class="forward">
                        <a href=""><span class="header-name">게시글</span></a>
                    </li>
                    <li class="forward">
                        <a href=""><span class="header-name">사진첩</span></a>
                    </li>
                    <li class="forward">
                        <a href=""><span class="header-name">일정</span></a>
                    </li>
                    <li class="forward">
                        <a href=""><span class="header-name">멤버</span></a>
                    </li>
                </ul>
            </div>
        </div>
          <!-------------------- 메인 컨텐츠 헤더 끝--------------------->





        <div class="main-left">
            <div class="group-profile">
                <div class="main-images">
                    <a href=""><img src="../src/img/study.png"></a>
                </div>
                <div class="profile-details">
                  <span class="group-name"><strong>JAVASCRIPT</strong></span>
                  <p>자바 스크립트 연구 개발하는 모임입니다. 제발 자바개발자 오지 마세요</p>
                
                </div>
                <div class="profile-member">      
                    <span class="profile-member-number">멤버: 3명</span>
                </div>
                <div class="profile-button">
                  <div class="button-layer"></div>
                  <button >글쓰기</button>
                </div>
              </div>

        </div>

        <div class="main-middle">

        
            
                
                <!---------------------- Modal 창 --------------------->


                <div class="popup-outer">
                    <div class="popup-box">
                      <i id="close" class='bx bx-x close'></i>
                        <div class="write-form-header">
                           <h1 class="write-form-title">글쓰기</h1>
                        </div>
                        <div class="textarea">
                            <textarea rows="15" id="content" placeholder="새로운 소식을 남겨보세요"></textarea>
                        </div>

                        <ul class="toolbarList">
                            <li><input type="file"><i class='bx bx-photo-album'></i></li>
                        </ul>

                        <div class="button">
                            <button id="close" class="send">게시</button>
                        </div>
                       
                    </div>
                </div>

            




            <!-- 나중에 jsp 파일에 if문 걸어서 groupDetail.html파일에 넣으면 된다. -->

            <div class="empty-content">
                <div class="group-board">
                    <i class="uil uil-clipboard-alt"></i>
                </div>
                <div class="board-text">
                    <h3>그룹게시판</h3>
                    <small>첫 게시글을 작성해보세요  <br>
                 나와 멤버가 쓴 글이 여기에 표시됩니다.</small>
                </div>
            </div>

            <!-- 나중에 jsp 파일에 if문 걸어서 groupDetail.html파일에 넣으면 된다. <끝> -->

        </div>





        <!------------------ 메인 컨텐츠 오른쪽 -------------->


        <div class="main-right">
            <div class="banner">
                <div class="member-banner">
                    <h2 class="tit">멤버</h2>
                    <div class="memberWrap">
                        <a href="">모두보기</a>
                    </div>

                </div>

                <div class="member-list">
                    <ul class="member-profile">
                        <li><a>
                            <span><img src="../src/img/kh.jpeg"></span>
                            <span class="member-detail">
                                <strong class="text">몽키스패너</strong>
                                <span class="member-grade">관리자</span>
                            </span>
                        </a></li>
                    </ul>
                </div>
            </div>






        </div>






    </div>
    <script src="GroupDetail.js"></script>
</body>
</html>
