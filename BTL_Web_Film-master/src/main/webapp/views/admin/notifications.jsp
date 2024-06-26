<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FilmViet - Danh sách thông báo</title>

    <%@ include file="/views/admin/common/head.jsp"%>
</head>

<style>
	
</style>
<body>
<div class="page-wrapper" id="main-wrapper" data-layout="vertical"
     data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed" data-header-position="fixed">

    <%@ include file="/views/admin/common/assied.jsp"%>

    <div class="body-wrapper">
        <%@ include file="/views/admin/common/header.jsp"%>

        <div class="container-fluid">
            <div class="card">
                <div class="card-body">
                    <button class="btn btn-primary float-end" id="modal-add-notif" role="button">
                        <i class="ti ti-file-plus"></i> Thông báo mới
                    </button>
                    <h5 class="card-title fw-semibold mb-4 mt-2">Danh Sách Thông báo</h5>
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">User id</th>
                                        <th scope="col">Họ tên</th>
                                        <th scope="col">Kiểu thông báo</th>
                                        <th scope="col">Nội dung</th>
                                        <th scope="col">Video</th>
                                        <th scope="col">Thời gian</th>
                                        <th scope="col">Hành động</th>
                                    </tr>
                                    </thead>

									<tbody class="new-noti"></tbody>
                                    <tbody class="render-new-noti">
									

                                    

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>



                </div>
            </div>
        </div>


    </div>
    <div class="modal" id="modal-create-noti">
        <div class="modal-content" style="
    max-width: 500px;
    margin: 0 auto;
    top: 50%;
    transform: translateY(-50%);
">
            <div class="modal-header d-flex align-items-center">
                <div class="modal-title text-center" style="flex: 5;">
                    Thêm thông báo
                </div>
                <div class="btn-close" style="flex: 0.2;" onclick="changeModal()"></div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="content-notification">Nội dung</label>
                    <textarea type="text" name="content-notification" class="w-100 form-control" style="height: 100px;" autocomplete="off" id="content-notification" placeholder="Nhập nội dung thông báo"></textarea>
                </div>
                <div class="form-group">
                    <label for="content-notification">Kiểu thông báo</label>
                    <select name="select-type" class="form-control" id="select-type">
                        <option value="0">Tất cả</option>
                        <option value="1">Đỗi tượng</option>
                    </select>
                </div>

                <div class="form-group" id="select-user-hidden" style="display: none;">
                    <label for="content-notification">Đối tượng</label>
                    <select name="select-user" class="form-control" id="select-user">
                        <option value="">Chọn người dùng</option>
                        <c:forEach var="item" items="${user}">
                            <option value="${item.id}">${item.fullname} - ${item.id}</option>
                        </c:forEach>
                    </select>
                </div>
               

            </div>
            <div class="modal-footer d-flex justify-content-end">
                <div class="btn-default btn" id="btn-close-modal" onclick="changeModal()">Hủy</div>
                <div class="btn btn-primary" id="save-modal">Lưu</div>
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
    				Xác nhận xóa thông báo. 
    			
    			</div>
    			<div class="btn-close" style="flex:0.2;" onclick="changeConfirm(null)"></div>
    		</div>
    		<div class="modal-body w-100 d-flex align-items-center justify-content-center" >
    			<div class="content badge w-100 text-black text-center" style="font-size:16px;">
    				Bạn có chắc chắn muốn xóa thông báo này?
    			</div>
    		</div>
    		<div class="modal-footer d-flex justify-content-end">
    			<div class="btn btn-default" onclick="changeConfirm(null)">Hủy</div>
    			<div class="btn btn-primary" id="save-confirm-modal" data-id="">Ok</div>
    		</div>
    	</div>
    </div>
</div>
<%@ include file="/views/admin/common/footer.jsp"%>

<script>

var websocket = new WebSocket("ws://localhost:8080/BackEnd/notifications");
websocket.onopen = function(message) {processOpen(message);};
websocket.onmessage = function(message) {processMessage(message);};
websocket.onclose = function(message) {processClose(message);};
websocket.onerror = function(message) {processError(message);};

function processOpen(message){
	console.log('connect');
}
function processMessage(message){
	
}

function processClose(message){
	console.log("dis");
}
function processError(message){
	
}
	let page = 1;
	let noMoreNotification = false;
	getNotifications(true);
	
	var loop = 0;
	function getNotifications(newNoti = false){
		if(newNoti){
			page = 1;
			loop = 0;
			noMoreNotification = false;
		}
		
		if(!noMoreNotification){
			$.ajax({
				url:"/BackEnd/getNotifications",
				method:"GET",
				data:{
					page: page,
				},
				success:function(res){
					
					
					if(page == 1){
						
						res.notifications.forEach(function(item){
							renderNotifications(item);
						})
						
						
					}
					
					else{
					
						res.notifications.forEach(function(item){
							renderNotifications(item);
						})
						
					}
					
					noMoreNotification = page >= res?.last_page;
					if(!noMoreNotification){
						page++;
					}
				}
			})
		}
	}
	
	actionOnScrollBottom(window, () => {
	    getNotifications();
	});

	function actionOnScrollBottom(element, callback) {
        $(element).on('scroll', () => {
            var scrollBottom = $(window).scrollTop() + $(window).height() === $(document).height();
            
            if (scrollBottom) {
                callback();
            }
        });
    }

    $("#modal-add-notif").click(function (){
        changeModal();
    })

    $("#select-type").change(function() {
        var data = $(this).val();
        
        if (data == 1) {
            $("#select-user-hidden").css("display", "block");

        } else {
            $("#select-user-hidden").css("display", "none");
        }
    });

    function changeModal(){
        $("#modal-create-noti").toggle();
    }


    $('#save-modal').click(function (){
        var content = $('#content-notification').val();
        var type = $('#select-type').val();
        var user = $('#select-user').val();
        if(type == 1 && user == null){
        	return;
        }
        if(content != null && content.length > 0){
        	$('#content-notification').val("");
        	$('#select-type').val("0");
        	$('#select-user').val("")
        	changeModal();
            $.ajax({
                url: "/BackEnd/createNotifications",
                method: "POST",
                data:{
                    content: content,
                    type: type,
                    userid: user,
                },
                success:function (res){
                	const notifications = {
                		content: res.notification.content,
                		type: res.notification.type,
                		userId: res.notification.userId,
                		username: res.notification?.username 
                	};
                	websocket.send(JSON.stringify(notifications));
                    renderNewNotification(res);
                }
            })

        }
    })
    
  	function renderNotifications(notification, i) {
	   loop++;
	    const notificationRow = $('<tr>')
	        .attr('data-notification-id', notification.id)
	        .addClass('notification-item');
	
	    $('<td>').text(loop).appendTo(notificationRow);
	
	    if (notification.userId !== 0) {
	        $('<td>').text(notification.userId).appendTo(notificationRow);
	        $('<td>').text(notification.username).appendTo(notificationRow);
	    } else {
	        $('<td>').html('<span class="badge bg-success-subtle text-success">Null</span>').appendTo(notificationRow);
	        $('<td>').html('<span class="badge bg-success-subtle text-success">Null</span>').appendTo(notificationRow);
	    }
	
	    if (notification.type === 0) {
	        $('<td>').html('<span class="badge bg-success-subtle text-success">Tất cả</span>').appendTo(notificationRow);
	    } else {
	        $('<td>').html('<span class="badge bg-warning-subtle text-warning">Người dùng</span>').appendTo(notificationRow);
	    }
	
	    $('<td>').text(notification.content).appendTo(notificationRow);
	    if(notification.href != null){
	    	$('<td>').text(notification.href).appendTo(notificationRow);
	    }else{
	    	$('<td>').html('<span class="badge bg-success-subtle text-success">Null</span>').appendTo(notificationRow);
	    }
	   
	    $('<td>').text(notification.addDate).appendTo(notificationRow);
	
	    const actionTd = $('<td>');
	    const btnGroup = $('<div>').addClass('btn-group').attr('role', 'group');
	    const deleteButton = $('<button>')
	        .addClass('btn btn-danger ms-2 rounded-2')
	        .text('Xoá')
	        .attr('onclick', 'changeConfirm('+notification.id+')');
	
	    btnGroup.append(deleteButton);
	    actionTd.append(btnGroup);
	    notificationRow.append(actionTd);
	    $('.render-new-noti').append(notificationRow);
	}


    function renderNewNotification(data) {
    	
    	var notification = data.notification;
    	var user = data.user;
                const notificationRow = $('<tr>')
                    .attr('data-notification-id', notification.id)
                    .addClass('notification-item');

                $('<td >').text(notification.id).appendTo(notificationRow);
                
                if(user != null){
                	$('<td>').text(user.id).appendTo(notificationRow);
                	$('<td>').text(user.fullname).appendTo(notificationRow);
                }
                else{
                	$('<td>').html('<td><span class="badge bg-success-subtle text-success">Null</span></td>').appendTo(notificationRow);
                	$('<td>').html('<td><span class="badge bg-success-subtle text-success">Null</span></td>').appendTo(notificationRow);
                }
                
                if(notification.type == 0){
                	$('<td>').html('<td><span class="badge bg-success-subtle text-success">All</span></td>').appendTo(notificationRow);
                }
                else{
                	$('<td>').html('<td><span class="badge bg-warning-subtle text-warning">User</span></td>').appendTo(notificationRow);
                }
                
                $('<td>').text(notification.content).appendTo(notificationRow);
                if(notification.href != null){
        	    	$('<td>').text(notification.href).appendTo(notificationRow);
        	    }else{
        	    	$('<td>').html('<span class="badge bg-success-subtle text-success">Null</span>').appendTo(notificationRow);
        	    }
                $('<td>').text(notification.addDate).appendTo(notificationRow);

                const actionTd = $('<td>');
                const btnGroup = $('<div>').addClass('btn-group').attr('role', 'group');
                const deleteButton = $('<button>')
                    .addClass('btn btn-danger ms-2 rounded-2')
                    .text('Xoá')
                    .attr('onclick', 'changeConfirm('+notification.id+')');

                btnGroup.append(deleteButton);
                actionTd.append(btnGroup);
                notificationRow.append(actionTd);

                $('.new-noti').prepend(notificationRow);
     }
    
    
    $("#save-confirm-modal").click(function(){
    	var data = $(this).attr("data-id");
    	changeConfirm(data);
    	removeNotification(data);
    })
    
    function removeNotification(id){
    	
    	const $notificationRow = $('tr[data-notification-id='+id+']');
        console.log("Found row:", $notificationRow);
    	$.ajax({
    		url:"/BackEnd/deleteNotifications",
    		method: "POST",
    		data:{
    			id: id,
    		},
    		success:function(res){
    			
    			if(res.success){    				
    				$notificationRow.remove();
    			}
    			
    		}
    		
    	})
    }
    
   
    
    function changeConfirm(id){
    	
    	$('#modal-confirm').toggle();
    	
    	$("#save-confirm-modal").attr("data-id", id);
    }
</script>
</body>
</html>
