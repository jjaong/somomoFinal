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
    <link rel="stylesheet" href="resources/css/header.css?ver=1.0.1">
    <link rel="stylesheet" href="resources/css/groupList.css?ver=1.3.3">
    <link rel="stylesheet" href="resources/css/choModal.css?ver=1.0.4">
    <!----------- 아이콘 CSS 링크 ------->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <script src="https://kit.fontawesome.com/567fbbaed5.js" crossorigin="anonymous"></script>
    <!----------- 아이콘 CSS 링크 version 2------->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <title>그룹리스트</title> 
</head>
<body>
    <div class="wrapper">
        <div>
            <header>
                <div class="logo-name">
                    <div class="logo-image">
                        <a href="main.fd"><img src="resources/img/web_logo.jpg" alt=""></a>
                    </div>
                    <span class="logo_name">SoMoMo</span>
                </div>
        
                <div class="top">
                    <form action="search.gr" method="get" id="search-form">
                        <div class="search-box">
                            <button type="submit"><i class="uil uil-search"></i></button>
                            <input type="text" name ="search" value="${search}" placeholder="검색">
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
                <h1 class="nav-title">Group</h1>
                <div class="nav-col-group">
                    <i class="fas fa-clipboard-list"></i>
                    <span>그룹 목록</span>
                </div>
                <div class="nav-col-group">
                    <i class="uil uil-search"></i>
                    <span>찾아보기</span>
                </div>
    
                <div class="create-newGroup">
                    <a href="insertForm.gr">
                        <button class="learn-more">
                            <i class="uil uil-plus"></i>
                            <span class="circle"></span>
                            <span class="button-text">새그룹 만들기</span>
                        </button>
                    </a>
                </div>

                <div class="horizontal"></div>

            </div>

            <div class="nav-col">
                <h2 class="m1">관리중인그룹</h2>
                <div class="adminGroup">  </div>
                
                <div class="horizontal"></div>

                <h2>가입한 그룹</h2>
                <div class="memberGroup">  </div>                
            </div>
        </nav>
		
		<c:forEach var="mg" items="${myGroupList}">
			<script> 
                var result = '<div class="nav-group-list">'
                                + '<input type="hidden" value="${mg.groupNo}">'
                                + '<img src="${mg.groupImg}" alt="">'
                                + '<span class="admingroup-name">${mg.groupName}</span>'
                            + '</div>';

                if('${mg.userRank}' == 'A'){
                    $('.adminGroup').append(result);
                } 
                else{
                    $('.memberGroup').append(result);
                }
            </script>
		</c:forEach>
		
        <script>
            
            $('.adminGroup').css({'disply':'flex', 'flex-direction':'column', 'width':'100%'})
            $('.memberGroup').css({'disply':'flex', 'flex-direction':'column', 'width':'100%'})
            
            var guide = "";

            if($('.adminGroup').html() == ""){
                guide = "<span>현재 관리중인 그룹이 없습니다.</span>";
                $('.adminGroup').append(guide);
            }
            else if($('.memberGroup').html() == ""){
                guide = "<span>가입한 그룹이 없습니다.</span>";
                $('.memberGroup').append(guide);
            }
        </script>
		
		<!-- 메인  (그룹방 리스트) -->
        <main class="content">
            <div class="list-outer">
                <div class="tag-group">
                    <ul class="tag-body">
                        <li class="all-category-list">그룹 전체</li>
                        <c:forEach var="gc" items="${cList}">
                        <li class="category-list" value="${gc.categoryNo}">${gc.categoryName}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="group-outer">
						
							
							
							
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
					</c:forEach>
					</div>
                </div>
        </main>
    
    <script>
    	<!--무한스크롤 페이징-->
	    $(function(){
			let currentPage = ${pi.currentPage};
			console.log('시작:'+currentPage);
			selectGroupList();
			
			//스크롤 할 때마다 호출되는 함수
			$(window).on('scroll', function(){
				if(${pi.maxPage eq 0}){
					return; //등록된 게시글이 없을 경우 종료
				}
				//위로 스크롤된 길이
				let scrollTop = $(window).scrollTop();
				//웹 브라우저 창의 높이
				let windowHeight = $(window).height();
				//문서 전체의 높이
				let documentHeight = $(document).height();
				//바닥까지 스크롤
				let isBottom = scrollTop + windowHeight  >= documentHeight;
				
				if(isBottom){
					if(currentPage == ${pi.maxPage}){
						return; //마지막 페이지라면 끝
					}
					
					//무한 스크롤은 나중에..으어어ㅓ어ㅓ
					
					//currentPage ++;//요청 페이지 번호 1증가
					
					//selectGroupList();
				
				}
            });
        });
	    
        
        <!-- 그룹 리스트 ajax -->
        function selectGroupList(){
            
           // console.log('요청'+currentPage);
            var keyword="";
            $('.tag-group ul li').on('click',function(){
            	
            	keyword=$(this).text();
            	console.log(keyword);
         
            	 $.ajax({
            		 url:'list.gr',
                     method:'POST',
                     data : {
                         userId : '${loginUser.userId}',
                         keyword : keyword
                         
                     },
                     success : function(data){
                    	var result = '';
                    	
                    	//이게 맞나....싶다...
                    	for(var g in data){
                    	 result += '<div class="group">'
                    		 		+ '<div class="group-main">'
                    		 		+'<input class="groupNo" type="hidden" value='+data[g].groupNo+'>'
                    	 			+ '<div class="group-header">'
                    	 			+ '<img src='+data[g].groupImg+' alt="" />'
                    	 			+ '</div>'
                    	 			+ '<div class="group-body">'
                    	 			+ '<span class="tag tag-development">'+data[g].categoryNo+'</span>'
                    	 			+ '<h4>'
                    	 			+  data[g].groupName+'</h4>'
                    	 			+ '<div class="group-info">'
                    	 			+ '<span class="group-member">멤버 '+data[g].groupName+'명</span>'
                    	 			+ '<span>'+data[g].groupType+'</span>'
                    	 			+ '</div>'
                    	 			+ '</div>'
                    	 			+ '</div>'
                    	 			+'<div class="group-foot">'
        							+'<div class="group-btn">'
        							+'<button class="apply">그룹 가입</button>'
        							+'</div>'
        							+'</div>'
        							+'</div>';
                    	 			
                    	//console.log(result);
                    	 
                    	}
                    	$('.group-outer').html(result);
                    	//console.log(data);
                         
                  	}
              });
            		 
            })
                
           
        }
        
        
        
        
    </script>
    
    <script>
        // 목록에 나오는 리스트 누르면 해당 그룹방으로 갈 수 있음
        $('.nav-group-list').click(function(){
            location.href = "detail.gr?gno=" + $(this).children().eq(0).val();
        })
    </script>
    
    
    <script>

        // modal창 관련
        $(function(){
            const modal = $('#applyModal');
    
            $('.apply').click(function(){
                
                // 그룹방 번호
                const gno = $(this).parents('.group-foot').siblings('.group-main').children('.groupNo').val();
                
                // 그룹방 제목
                const gname = $(this).parents('.group-foot').siblings('.group-main').children('.group-body').children('.groupName').text().trim()
                
                $('.md-groupNo').val(gno);
                $('.header-title__groupName').text(gname);
    
                modal.fadeIn(300);
                $('body').css({'overflow': 'hidden', 'height' : '100%'});
                
            })

            // 취소버튼 눌렀을때 모달 창 닫아주기
            $('.close').click(function(){
                modal.fadeOut(300);
                $('body').css({'overflow':'auto'});
            })
        })

        function apply(){

            const modal = $('#applyModal');
            
            const $groupNo = $('.md-groupNo').val();
            //console.log($('.md-groupNo').val());

            const $greeting = $('.greeting').val();
            // console.log($('.greeting').val());

            const $userId = '${loginUser.userId}';
            //console.log($userId);

            $.ajax({
                url : "apply.gr",
                data : {groupNo : $groupNo, userId : $userId, greeting : $greeting},
                type : "post",
                success : function(result){
                    console.log(result);

                    modal.hide();
                    
                }, error : function(){
                    console.log("통신 실패!");
                }
            })
        }
        
        // 각 그룹방 리스트들을 누르면 해당 방으로 이동
        $('.group-main').click(function(){
            location.href = "detail.gr?gno=" + $(this).children().eq(0).val();
        })
    </script>
    

	</section>
    </body>
</html>
