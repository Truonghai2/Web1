<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Footer Section Begin -->
<footer class="footer">
	<div class="page-up">
		<a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="footer__logo">
					<a href="index.jsp"><img src="views/template/user/img/logo.png"
						alt=""></a>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="footer__nav">
					<ul>
						<li class="active"><a href="index">Trang Chủ</a></li>
						<li><a href="categories">Danh Sách Phim</a></li>
						<li><a href="introduce">Liên Hệ Với Tôi</a></li>
						<li><a href="profile">Trang Cá Nhân</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3">
				<p>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | Design and Developed <i class="fa fa-heart"
						aria-hidden="true"></i> by Quang Nhựt
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</p>

			</div>
		</div>
	</div>
</footer>
<!-- Footer Section End -->

<!-- Search model Begin -->
<div class="search-model">
	<div class="h-100 d-flex align-items-center justify-content-center">
		<div class="search-close-switch">
			<i class="icon_close"></i>
		</div>
		<form action="search" method="get" class="search-model-form"
			autocomplete="off">
			<input type="text" name="search" id="search-input"
				placeholder="Tìm kiếm.....">
		</form>
	</div>
</div>
<!-- Search model end -->

<div class="layout-messenger" style="z-index:100;">
	<div class="btn-icon-messenger position-fixed p-1" style="bottom:0px; right:20px; z-index:100;" onclick="openLayout()">
		<div class="icon p-2  mb-2" style="border-radius:50%;background-color:#ccc; ">
			<i class="fa-brands fa-facebook-messenger" style="font-size:40px;"></i>
		</div>
	</div>
	<div class="messenger-content position-fixed bg-white card messenger-layout messenger-messagingView scrollbutton-hidden" style="width:350px; max-height:470px; right:20px; bottom:0; display:none;z-index:100;">
		<div class="card-body p-0">
		
			<div class="messenger-header d-flex align-items-center justify-content-between p-2" style="border-bottom:1px solid #ccc;">
				<div class="messenger-title text-bold">
					Kênh thế giới
				</div>
				<div class="btn-close btn-hidden-layout" onclick="hiddenLayout()">
					<i class="fa-solid fa-minus"></i>
				</div>
			</div>
			
			<div class="layout-main m-body messages-container app-scroll p-2" style="opacity: 1; height: 508.8px; max-height: 369px; overflow: auto;">
                <div class="messages">
                
				</div>
			</div>
              
			
			<div class="messenger-footer messenger-sendCard p-2">
				<form id="message-form" class="d-flex w-100 align-items-center" method="POST" action="" enctype="multipart/form-data">
			        <label class="handleFIle">
			        	<span class="fas fa-plus-circle"></span>
			        	<input disabled='disabled' type="file" class="upload-attachment" name="file" accept="" hidden/>
			        </label>
			        <button class="emoji-button"></span><span class="fas fa-smile"></button>
			        <textarea readonly='readonly' id="textMessage" name="message" class="m-send app-scroll w-100" placeholder="Tin nhắn" style="background-color: #ccc;
					    border-radius: 20px;
					    max-height: 146px;
					    overflow: hidden;
					    overflow-wrap: break-word;
					    height: 44px;"></textarea>
			        <button disabled='disabled' class="send-button">
			        	<span class="fas fa-paper-plane"></span>
			        </button>
			    </form>
			</div>
		</div>
	</div>
	
	<div class="modal" id="modal-confirm" >
    	<div class="modal-content" style="
		    max-width: 500px;
		    margin: 0 auto;
		    top: 50%;
		    transform: translateY(-50%);
		">
    		<div class="modal-header">
    			<div class="modal-title text-center" style="flex:5; font-size:18px;">
    				Xác nhận xóa tin nhắn. 
    			
    			</div>
    			<div class="btn-close" style="flex:0.2;" onclick="changeConfirm(null)"></div>
    		</div>
    		<div class="modal-body w-100 d-flex align-items-center justify-content-center" >
    			<div class="content badge w-100 text-black text-center" style="font-size:16px;">
    				Bạn có chắc chắn muốn xóa tin nhắn này không?
    			</div>
    		</div>
    		<div class="modal-footer d-flex justify-content-end">
    			<div class="btn btn-default" onclick="changeConfirm(null)">Hủy</div>
    			<div class="btn btn-primary" id="save-confirm-modal" data-id="">Ok</div>
    		</div>
    	</div>
    </div>
    
    <div id="imageModalBox" class="imageModal">
	    <span class="imageModal-close">&times;</span>
	    <img loading="lazy" class="imageModal-content" id="imageModalBoxSrc">
	</div>
</div>


<button onclick="playNotificationSound()" class="sound" hidden>Play Sound</button>
		
		



<script src="https://cdn.jsdelivr.net/npm/@joeattardi/emoji-button@3.0.3/dist/index.min.js"></script>



<!-- Js Plugins -->

<script>
	const messengerTheme = 1;
	const messageInput = document.querySelector("#message-form .m-send");
	const emojiButton = document.querySelector(".emoji-button");
	
	console.log(emojiButton);
	
	const emojiPicker = new EmojiButton({
	  theme: messengerTheme,
	  autoHide: false,
	  position: "top-start",
	});
	
	emojiButton.addEventListener("click", (e) => {
	  e.preventDefault();
	  emojiPicker.togglePicker(emojiButton);
	});
	
	emojiPicker.on("emoji", (emoji) => {
	  const el = messageInput;
	  const startPos = el.selectionStart;
	  const endPos = el.selectionEnd;
	  const value = el.value;
	
	  const newValue =
	    value.substring(0, startPos) +
	    emoji +
	    value.substring(endPos, value.length);
	
	  el.value = newValue;
	  el.selectionStart = el.selectionEnd = startPos + emoji.length;
	  el.focus();
	});


</script>

    <style>
        .notification {
            display: none;
            position: fixed;
            left: 20px;
            bottom: 20px;
            background-color: #444;
            color: #fff;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }
        .show {
            display: block;
        }
    </style>

    <div id="notification" class="notification p-2">
    	
    </div>




<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="<c:url value='/views/template/user/js/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/views/template/user/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/views/template/user/js/player.js'/>"></script>
<script src="<c:url value='/views/template/user/js/jquery.nice-select.min.js'/>"></script>
<script src="<c:url value='/views/template/user/js/mixitup.min.js'/>"></script>
<script src="<c:url value='/views/template/user/js/jquery.slicknav.js'/>"></script>
<script src="<c:url value='/views/template/user/js/owl.carousel.min.js'/>"></script>
<script src="<c:url value='/views/template/user/js/main.js'/>"></script>
<script src="<c:url value='/views/template/user/js/validateUser.js'/>"></script>



<script type="text/javascript" src="<c:url value='/views/template/user/js/chat.js'/>">

</script>

<script >


	var websocket = new WebSocket("ws://localhost:8080/BackEnd/chat/1");
	websocket.onopen = function(message) {processOpen(message);};
	websocket.onmessage = function(message) {processMessage(message);};
	websocket.onclose = function(message) {processClose(message);};
	websocket.onerror = function(message) {processError(message);};
	function processOpen(message) {
		console.log("Server connect... \n")
	}
	function processMessage(message) {
		
		var check = JSON.parse(message.data);
		var action = check.action;
		if(action == "send"){
			
			var html = '';
			if(check.id == auth_id){
				html = appendMessengerUser(check);
			}
			else{
				
				showNotification(check);
				playNotificationSound();
				html = appendMessengerOther(check);
			}
			
		    var appendHTML = document.querySelector('.messages');
		    
		   var scrolldiv = document.querySelector('.messages-container');
		   $('.messages').append(html);
		    if (appendHTML) {
		        scrolldiv.scrollTop = scrolldiv.scrollHeight;
		    } else {
		        console.error("Phần tử với class 'messages' không tồn tại.");
		    }
			
		}else if(action == 'delete'){
			
			const name = $('.username[data-id='+ check.id +']');
			name.remove();
			const $notificationRow = $('.message-card[data-id='+ check.id +']');
			$notificationRow.remove();
		}
		
		
	}
	function processClose(message) {
		console.log("Server Disconnect... \n");
	}
	function processError(message) {
		console.log("Error... " + message +" \n");
	}
	
	function sendMessage() {
		var content = textMessage.value.trim();
		
	    if (typeof websocket !== 'undefined' && websocket.readyState === WebSocket.OPEN && (content.length > 0 || $('.img-preview').attr('src') != null)) {
	    		if($('.img-preview').attr('src') == null){
	    			var message = {
	   		    	     action: 'send',
	   		    	     messages: textMessage.value,
	   		    	     file:"",
	   		    	};
	   	    		
	   		        websocket.send(JSON.stringify(message));
	   		        textMessage.value = "";
	   		        cancelAttachment();
	    		}
	    		else{
	    			sendfile($('.img-preview').attr('src'),textMessage.value);
	    		}
	    } 
	    textMessage.value = "";
	}

	
	
    $("#message-form").on("submit", (e) => {
        e.preventDefault();
        sendMessage();
        
        $('.messages-container').css({height: 369 + "px"});
        $('.handleFIle').removeClass('d-none');
    });

    
    $("#message-form .m-send").on("keyup", (e) => {
        
        if (e.which == 13 || e.keyCode == 13) {
           
            if (!e.shiftKey) {
            	
            	
                sendMessage();
                $('.messages-container').css({height: 369 + "px"});
                $('.handleFIle').removeClass('d-none');
            }
        }
    });
	
    
    function sendfile(file, content){
    	$.ajax({
    		url:"/BackEnd/sendfile",
    		method:"POST",
    		data:{
    			file: file,
    		},
    		success:function(res){
    			
    				var message = {
	   		    	     action: 'send',
	   		    	     messages: content ,
	   		    	     file: res.fileUrl,
	   		    	};
	   	    	
	   		        websocket.send(JSON.stringify(message));
	   		        textMessage.value = "";
	   		        cancelAttachment();
    		}
    	})
    }
	
	$(document).on('click', '.delete-btn', function() {
	    var messageId = $(this).data('id');
	    
	    changeConfirm(messageId);
	    
	});
    
    
    $("#save-confirm-modal").click(function(){
    	var data = $(this).attr("data-id");
    	
    	console.log(data);
    	changeConfirm(data);
    	 var message = {
    		 action: 'delete',
    		 id: data
    	};
    	websocket.send(JSON.stringify(message));
    })
    
    
    
   
    
    function changeConfirm(id){
    	
    	$('#modal-confirm').toggle();
    	
    	$("#save-confirm-modal").attr("data-id", id);
    	
    }
    
    
    
    var data = localStorage.getItem("message") ?? null;
    
    
    if(data == null){
    	localStorage.setItem("message", 0);
    	
    }else{
    	if(data == 1){
    		openLayout();
    		message_img();
    	}
    	
    }
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxUploadSize = 5 * 1024 * 1024; // 5MB
    
    function message_img(){
    	  $("body").on("change", ".upload-attachment", (e) => {
    	    let file = e.target.files[0];
    	    if (!attachmentValidates(file)) return false;
    	    let reader = new FileReader();
    	    let sendCard = $(".messenger-sendCard");
    	    reader.readAsDataURL(file);
    	    reader.addEventListener("loadstart", (e) => {
    	        $("#message-form").before(loadingSVG());
    	    });
    	    reader.addEventListener("load", (e) => {
    	        $(".messenger-sendCard").find(".loadingSVG").remove();
    	        if (!file.type.match("image.*")) {
    	           
    	            sendCard.find(".attachment-preview").remove(); // older one
    	            sendCard.prepend(attachmentTemplate("file", file.name));
    	        } else {
    	            
    	            sendCard.find(".attachment-preview").remove(); // older one
    	            sendCard.prepend(
    	                attachmentTemplate("image", file.name, e.target.result)
    	            );
    	          
    	            const h = $('.messenger-messagingView').height() || 0; 
    	            
    	         // Tạo một đối tượng ảnh mới
    	            const img = new Image();
					let b = 0; 
					
					// Gán src của ảnh mới bằng src của ảnh có class 'img-preview'
					img.src = $('.img-preview').attr('src');
					
					// Khi ảnh tải xong, hàm này sẽ được gọi
					img.onload = function() {
					  
					  const w = img.width / 260;
					  b = img.height / w;
					  const m = 368 -  b - 25;
					  
					 
					  if (h > 0) {
	    	                $(".messenger-messagingView").css({ height: h + "px" });
	    	                $('.messages-container').css({ height: m + "px" });
	    	            }
					};
    	            
    	            
    	          
    	            
    	        }
    	    });
    	    
    	    });
    	}

		    function attachmentValidates(file) {
		        const fileElement = $(".upload-attachment");
		        const { name: fileName, size: fileSize } = file;
		        const fileExtension = fileName.split(".").pop().toLowerCase();
		
		        if (!allowedExtensions.includes(fileExtension)) {
		            alert("File type not allowed");
		            fileElement.val("");
		            return false;
		        }
		
		        // Validate file size.
		        if (fileSize > maxUploadSize) {
		            alert("File is too large!");
		            fileElement.val("");
		            return false;
		        }
		        return true;
		    }
		    function loadingSVG(size = "25px", className = "", style = "") {
		    	 return `
		    	<svg style="${style}" class="loadingSVG ${className}" xmlns="http://www.w3.org/2000/svg" width="${size}" height="${size}" viewBox="0 0 40 40" stroke="var(--primary-color)fff">
		    	<g fill="none" fill-rule="evenodd">
		    	<g transform="translate(2 2)" stroke-width="3">
		    	<circle stroke-opacity=".1" cx="18" cy="18" r="18"></circle>
		    	<path d="M36 18c0-9.94-8.06-18-18-18" transform="rotate(349.311 18 18)">
		    	<animateTransform attributeName="transform" type="rotate" from="0 18 18" to="360 18 18" dur=".8s" repeatCount="indefinite"></animateTransform>
		    	</path>
		    	</g>
		    	</g>
		    	</svg>
		    	`;
		    }
		    
		    function attachmentTemplate(fileType, fileName, imgURL = null) {
		    	 if (fileType != "image") {
		    	   return (
		    	     `
		    	     <div class="attachment-preview">
		    	         <span class="fas fa-times cancel"></span>
		    	         <p style="padding:0px 30px;"><span class="fas fa-file">
		    	     </div>
		    	     `
		    	   );
		    	 } else {
		    	   const imageSrc = imgURL; 
		    	  
		    	   return (
		    	     '<div class="attachment-preview position-relative p-1"><span class="fas fa-times cancel text-white"></span><div class="image-file chat-image" ><img loading="lazy" class="img-preview" style="width: 100%;" src="'+imageSrc+'" /></div><p>'
		    	   );
		    	 }
		    	}
		    
		    
		    

 
var websockets = new WebSocket("ws://localhost:8080/BackEnd/notifications");
websockets.onopen = function(message) {processOpens(message);};
websockets.onmessage = function(message) {processMessages(message);};
websockets.onclose = function(message) {processCloses(message);};
websockets.onerror = function(message) {processErrors(message);};

function processOpens(message){
	
}
function processMessages(message){
	
	var object = JSON.parse(message.data);
	
	
	if(object.type == 0){
		
		Notification(object);
		playNotificationSound();
	}
	else{
		if(object.id == auth_id){
			Notification(object);
			playNotificationSound();
		}
	}
}

function processCloses(message){
	
}
function processErrors(message){
	
}



</script>
<!-- đăng nhập thành công -->
<%
Boolean loginSuccess = (Boolean) session.getAttribute("loginSuccess");
Boolean loginFail = (Boolean) session.getAttribute("loginFail");
if (loginSuccess != null) {
	if (loginSuccess) {
%>
<script>
	showSwalAlert('success', 'Đăng nhập thành công!');
</script>
<%
} else {
%>
<script>
	showSwalAlert('error', 'Sai thông tin người dùng !');
</script>
<%
}
session.removeAttribute("loginSuccess");
}

if (loginFail != null) {
if (loginFail) {
%>
<script>
	showSwalAlert('warning', 'Tài khoản không hoạt động !');
</script>
<%
}
session.removeAttribute("loginFail");
}
%>

<%
Boolean registerSuccess = (Boolean) session.getAttribute("registerSuccess");
if (registerSuccess != null) {
	if (registerSuccess) {
%>
<script>
	showCenterAlert('success', 'Thành công !',
			'Một email xác minh đã gửi đến địa chỉ email của bạn !');
</script>
<%
}
session.removeAttribute("registerSuccess");
}
%>
