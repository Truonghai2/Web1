/**
 * 
 */
const auth_id = $('meta[name="auth_id"]').attr("content");
const messagesContainers = $('.messenger-layout .layout-main');
const messagesElements = messagesContainers.find('.messages');


 function openLayout(){
	 icon();
	 htmlLayout();
	 
	 fetchMess(true);
	 initializeScrollAction();	
	 adjustMainHeight();
	 resizeInputChat();
	 
	 localStorage.setItem("message", 1);
 }
 
 function hiddenLayout(){
	  htmlLayout();
	  icon();
	 localStorage.setItem("message", 0);
 }
 
 
 function icon(){
	 $('.btn-icon-messenger').toggle();
 }
 
 
 
 
 function htmlLayout(){
	 
	 $('.messenger-content').toggle();
	 
 }
 

 if (auth_id.length == 0) {
  
   $(".messenger-sendCard").hide();
   // add loading opacity to messages container
   messagesContainers.css("opacity", ".5");
   // disable message form fields
   $('.m-send').attr("readonly", "readonly");
   $("#message-form button").attr("disabled", "disabled");
   $(".upload-attachment").attr("disabled", "disabled");
 } else {
  
   // show send card
   $(".messenger-sendCard").show();
   // remove loading opacity   to messages container
   messagesContainers.css("opacity", "1");
   // enable message form fields
   $('.m-send').removeAttr("readonly");
   $("#message-form button").removeAttr("disabled");
   $(".upload-attachment").removeAttr("disabled");
 }
 


let pageFetch = 1;
let noMoreFetch = false;
function fetchMess(newFetch = false) {
    if (newFetch) {
        pageFetch = 1;
        noMoreFetch = false; // Reset noMoreFetch flag for a new fetch
    }

    if (!noMoreFetch) {
        console.log("Fetching page: " + pageFetch);
        $.ajax({
            url: "/BackEnd/fetchMessage",
            method: "GET",
            data: {
                page: pageFetch,
            },
            success: function(res) {
              
              if(pageFetch == 1){
				  if(res.total == 0){
					  
				  }
				  else{
					  
					  var html ="";
					  for (let i = res.messages.length - 1; i >= 0; i--) {
						    const item = res.messages[i];
						    if (item.userid == auth_id) {
						        html += appendMessengerUser(item);
						    } else {
						        html += appendMessengerOther(item);
						    }
						}
					  
					  $('.messages').html(html);
					  scrollToBottom(messagesContainers);
				  }
			  }
			  else{
				  var html ="";
					for (let i = res.messages.length - 1; i >= 0; i--) {
					    const item = res.messages[i];
					    if (item.userid == auth_id) {
					        html += appendMessengerUser(item);
					    } else {
					        html += appendMessengerOther(item);
					    }
					}
					  
					  const lastMsg = messagesElements.find(
                      messagesElements.find(".message-card")[0]
                  );
                  const curOffset = lastMsg.offset().top - messagesContainers.scrollTop();
                  messagesElements.prepend(html);
                  messagesContainers.scrollTop(lastMsg.offset().top - curOffset);
					  
					  
			  }
			  
			 
			  noMoreFetch = pageFetch >= res?.last_page;
			  if(!noMoreFetch){
				  pageFetch++;
			  }
			  
			  var scrolldiv = $('.messages-container');
			  scrolldiv.scrollTop = scrolldiv.scrollHeight;
			  
			  
            },
            error: function(err) {
                console.error("Error fetching messages:", err);
            }
        });
    }
}

function scrollToBottom(container) {
 $(container)
   .stop()
   .animate({
     scrollTop: $(container)[0].scrollHeight,
   });
}


function initializeScrollAction(){
    //Messages pagination
  actionOnScroll(
    ".m-body.messages-container",
    function () {
      fetchMess();
    },
    true
  );
}



function adjustMainHeight() {
   var resizeTimeout;
        window.visualViewport.addEventListener("resize", (e) => {
            clearTimeout(resizeTimeout);
            resizeTimeout = setTimeout(function() {
                const h = e.target.height;
                const top = $('.messenger-header').outerHeight();
                const f = $('.messenger-sendCard').outerHeight();
                const m = h - top - f;
                if (h) {
                    $(".messenger-messagingView").css({ height: h + "px" });
                    $('.messages-container').css({height: m + "px"});
                }
            }, 100);
    });


}

function resizeInputChat(){
        $(document).on('input', '.m-send', function(){
            var element = $(this); // Lưu lại tham chiếu đến phần tử đang kích hoạt sự kiện
			
            // Kiểm tra xem phần tử có focus và có giá trị không
            if (element.is(":focus") && element.val().length > 0) {
                $('.handleFIle').addClass('d-none');
            } else {
                $('.handleFIle').removeClass('d-none');
            }

            const h = $('.messenger-messagingView').outerHeight(); 
            const top = $('.messenger-header').outerHeight();
            const f = $('.messenger-sendCard').outerHeight();
            
            const m = h - top - f ;
            if (h) {
                $(".messenger-messagingView").css({ height: h + "px" });
                $('.messages-container').css({height: m + "px"});
            }
        });
    }

  
    
    
function actionOnScroll(selector, callback, topScroll = false) {
 $(selector).on("scroll", function () {
   let element = $(this).get(0);
   const condition = topScroll
     ? element.scrollTop == 0
     : element.scrollTop + element.clientHeight >= element.scrollHeight;
   
   if (condition) {
     callback();
   }
 });
}

function appendMessengerOther(message) {

   	var lastCharacter = getLastWord(message.username);

    var html = `
	    <div class="username" data-id="${message.id}" style="font-size:12px;margin-left: 54px;">${lastCharacter}</div>
	    <div class="message-card" data-id="${message.id}">
	        <img src="views/template/user/img/anime/review-1.jpg" width="40px" style="border-radius:50%;" alt="">
	        <div class="message-card-content d-flex">
	            ${message.link ? `
	            <div class="image-file chat-image">
	                <img src="/BackEnd/${message.link}"  style="border-radius:10px;"/>
	            </div>
	            ` : ''}
	            ${message.body ? `
	            <div class="message">
	                ${message.body}
	                <span data-time="2024-04-07T11:33:47+07:00" class="message-time"></span>
	            </div>
	            ` : ''}
	        </div>
	    </div>`;
    return html;
}
function appendMessengerUser(message){
	
	var html =`<div class="message-card mc-sender" data-id="${message.id}">
    
			     <div class="actions">
		            <i class="fas fa-trash delete-btn" data-id="${message.id}"></i>
		        </div>
			        
			    <div class="message-card-content d-flex">
	            	${message.link ? `
		            <div class="image-file chat-image">
		                <img src="/BackEnd/${message.link}"  style="border-radius:10px;"/>
		            </div>
		            ` : ''}
		            ${message.body ? `
		            <div class="message">
		                ${message.body}
		                <span data-time="2024-04-07T11:33:47+07:00" class="message-time"></span>
		            </div>
		            ` : ''}
		        </div>
			</div>`;
    return html;
}


function handleEventMessage(message) {
		var object = JSON.parse(message.data);
		var html = '';
		if(object.id === auth_id){
			html = appendMessengerUser(object);
		}
		else{
			html = appendMessengerOther(object);
		}
		$('#textAreaMessage').append(html);
	}
function getLastWord(fullName) {

    const words = fullName.trim().split(' ');

    return words.length > 1 ? words[words.length - 1] : fullName;
}


function cancelAttachment() {
 $(".messenger-sendCard").find(".attachment-preview").remove();
 $(".upload-attachment").replaceWith(
   $(".upload-attachment").val("").clone(true)
 );
 
 const h = $('.messenger-messagingView').height(); 
    	    	    const top = $('.messenger-header').height();
    	    	    const f = $('.messenger-sendCard').height();
    	    	 
    	    	    const m = h - top - f - 32;
    	    	    if (h) {
    	    	                $(".messenger-messagingView").css({ height: h + "px" });
    	    	                $('.messages-container').css({height: m + "px"});
    	    	            }
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


 $("body").on("click", ".attachment-preview .cancel", () => {
   cancelAttachment();
 });



 // Image modal
 $("body").on("click", ".chat-image img", function () {

   let src = $(this).attr("src"); // Use .attr("src") to get the image source
   console.log(src);
   $("#imageModalBox").show();
   $("#imageModalBoxSrc").attr("src", src);
 });
 
 $(".imageModal-close").on("click", function () {
   $("#imageModalBox").hide();
 });

 // Search input on focus
 $(".messenger-search").on("focus", function () {
   $(".messenger-tab").hide();
   $('.messenger-tab[data-view="search"]').show();
 });
 $(".messenger-search").on("blur", function () {
   setTimeout(function () {
     $(".messenger-tab").hide();
     $('.messenger-tab[data-view="users"]').show();
   }, 200); 
 });
 
 

 
function playNotificationSound() {
	
const notificationSound = new Audio("views/template/user/sounds/chatify/new-message-sound.mp3");
    document.body.appendChild(notificationSound);
    notificationSound.play();
}

function showNotification(message) {
	var notification = $('#notification');
	notification.html(htmlNotifations(message));
	notification.addClass('show');

	setTimeout(function() {
	   notification.removeClass('show');
	}, 3000); 
}

function Notification(message) {
	var notification = $('#notification');
	notification.html(htmlNotification(message));
	notification.addClass('show');

	setTimeout(function() {
	   notification.removeClass('show');
	}, 3000); 
}

function htmlNotifations(message){
	var html= '';
	if(message.link != null){
		 html = `
	
    	<div class="content p-2 d-flex">
    		<div class="user d-flex align-items-center">
    			<div class="avatar">
    				<img src="views/template/user/img/anime/review-1.jpg" width="40px" style="border-radius:50%;" alt="">
    			</div>
    			<div class="imfor ml-2">
	    			<div class="username">${message.username}</div>
	    			<div class="content">
	    				Đã gửi một ảnh đến kênh chat.
	    			</div>
	    		</div>
    		</div>
    		
    	</div>`;
	}
	else{
		 html = `
		
	    	<div class="content p-2 d-flex">
	    		<div class="user d-flex align-items-center">
	    			<div class="avatar">
	    				<img src="views/template/user/img/anime/review-1.jpg" width="40px" style="border-radius:50%;" alt="">
	    			</div>
	    			<div class="imfor ml-2">
		    			<div class="username">${message.username}</div>
		    			<div class="content">
		    				${message.body}
		    			</div>
		    		</div>
	    		</div>
	    	</div>`;
	}
    	
    return html;
}


function htmlNotification(object){
	var html = `
		<div class="content p-2 d-flex">
		<div class="user d-flex align-items-center">
			<div class="imfor ml-2">
    			<div class="username">Thông báo</div>
    			<div class="content">
    				${object.body}
    			</div>
    		</div>
		</div>
	</div>`;
	return html;
}