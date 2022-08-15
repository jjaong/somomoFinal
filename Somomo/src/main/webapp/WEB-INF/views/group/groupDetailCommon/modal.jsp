<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <!---------------------- 글쓰기 Modal 창 --------------------->
	<div id = "writePopup" class="popup-outer ">
        <div class="popup-box">
            <i id="close" class='bx bx-x close'></i>
            <div class="write-form-header">
                <h1 class="write-form-title">글쓰기</h1>
            </div>

            <form action="insertBoard.gr" method="post" enctype="multipart/form-data">

                <!-- 히든으로 넘겨줘야할 정보 -->
                <input type="hidden" name="boardWriter" value="${loginUser.userId}">
                <input type="hidden" name="groupNo" value="${g.groupNo}">
                <input type="hidden" id="boardType" name="boardType" value="B">

                
                <div class="textarea">
                    <!-- contenteditable 사용하기 -->
                    <div id="editor" contenteditable="true">
                        
                    </div>
                </div>

                <textarea name="boardContent" id="boardContent" style="display: none;"></textarea>

                <ul class="toolbarList">
                    <li>
                        <input id="img-selector" type="file" accept="image/*">
                        <i class="uil uil-image-download" id="btn-image"></i>
                    </li>
                    <li><i class="uil uil-paperclip"></i></li>
                    <li><i class="uil uil-map-marker"></i></li>
                    <li><i class="uil uil-clipboard-notes"></i></li>
                </ul>

                <div class="button">
                    <button id="submit" onclick="getContent();" class="send">게시</button>
                </div>
            </form>

        </div>
    </div>

    <script>
        function getContent(){
            
            $('#boardContent').val($('#editor').html());

            alert($('#boardContent').val());

            
        
        }

    </script>
</body>
</html>