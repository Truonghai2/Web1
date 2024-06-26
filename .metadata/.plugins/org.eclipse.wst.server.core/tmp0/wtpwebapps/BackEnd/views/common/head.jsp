<link rel="icon" href="<c:url value='/views/template/user/img/favicon.png'/>" type="image/x-icon">

<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">
	

<meta name="auth_id" content="${sessionScope.currentUser.id }">
 
<!-- Css Styles -->
<link rel="stylesheet" href="sweetalert2.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/bootstrap.min.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/font-awesome.min.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/elegant-icons.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/plyr.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/nice-select.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/owl.carousel.min.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/slicknav.min.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/views/template/user/css/style.css'/>" type="text/css">

<link rel="stylesheet" href="<c:url value='/views/template/user/css/messenger.css'/>" type="text/css">

<style>


.m-send{
	font-size: 14px;
    width: 100%;
    border: none;
    padding: 10px;
    outline: none;
    resize: none;
    
    font-family: sans-serif;
    height: 44px;
    max-height: 200px;
}

.handleFIle{
	border:none;
	padding:0;
}

.messenger-sendCard button, .messenger-sendCard button:active, .messenger-sendCard button:focus{
	    border: none;
    outline: none;
    background: none;
    padding: 0;
    margin: 0;
}

#message-form label{
	margin:0;
}

.wrapper{
	z-index:100;
}

.messenger-sendCard span{
	    margin: 9px 10px;
    color: #bdcbd6;
    cursor: pointer;
    font-size: 21px;
    transition: transform 0.15s;
}

.message-card.mc-sender .message-card-content{
		align-items: end;
    border-radius: 15px;
}

.message-card .message-card-content{
	display: flex;
    flex-direction: column;
    gap: 4px;
    max-width: 75%;
    border-radius: 15px;
}

.message-card div{
	    margin-top: 0p
}

.message-card .message{
	margin: 0;
    padding: 6px 15px;
    padding-bottom: 5px;
    width: fit-content;
    width: -webkit-fit-content;
    border-radius: 20px;
    word-break: break-word;
    display: table-cell;
    background-color: #ccc;
    
}

.message-card.mc-sender .message-card-content .message{
	background-color: #0084ff !important;
}

.message-card.mc-sender .message{
	direction: ltr;
    background: #ccc !important;
}


.message-card{
	display: flex;
    flex-direction: row;
    gap: 0.5rem;
    align-items: center;
    width: 100% !important;
    margin: 2px 5px;
    width: calc(100% - 30px);
    justify-content: flex-start;
    font-family: Arial, Helvetica, sans-serif !important;
}

.message-card.mc-sender{
	justify-content: flex-end;
}

.message-card:hover .actions .delete-btn{
	display:block;
}

.message-card .actions .delete-btn{
	cursor: pointer;
	display:none;
}

@media (min-width: 450px) and (max-width: 600px) {
   
    .messenger-content{
    	max-width:100% !important;
    	width:100% !important;
    	right:0 !important;
    	height:100% !important;
    	max-height:100% !important;
    }
    .layout-main{
    	max-height:100% !important;
    	
    }
    
    
    
    
}

.message-card .image-file, .attachment-preview .image-file{

cursor: pointer;
    width: 140px;
    height: 70px;
    border-radius: 6px;
    width: 260px;
    height: auto;
    overflow: hidden;
    background-color: #f7f7f7;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center center;
}

.attachment-preview > .cancel:first-child{
	position: absolute;
    background: rgba(0, 0, 0, 0.33);
    width: 20px;
    height: 20px;
    padding: 3px;
    border-radius: 100%;
    font-size: 16px;
    margin: 0;
    top: 10px;
    color: var(--primary-color);
}

.imageModal {
    display: none;
    position: fixed;
    z-index: 50;
    padding-top: 100px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0, 0, 0);
    background-color: rgba(0, 0, 0, 0.9);
    z-index: 200000001;
  }
  .imageModal-content {
    margin: auto;
    display: block;
    height: calc(100vh - 150px);
  }
  .imageModal-content {
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.15s;
    animation-name: zoom;
    animation-duration: 0.15s;
  }
  
  @-webkit-keyframes zoom {
    from {
      -webkit-transform: scale(0);
    }
    to {
      -webkit-transform: scale(1);
    }
  }
  @keyframes zoom {
    from {
      transform: scale(0);
    }
    to {
      transform: scale(1);
    }
  }
  
  .imageModal-close {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #f1f1f1 !important;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
  }
  
  .imageModal-close:hover,
  .imageModal-close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
  }
  

</style>