<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${episode.title}</h1>
    <p>${episode.description}</p>
    <video src="${episode.videoUrl}" controls></video>
    <!-- Display comments and other details similar to video details -->

    
</body>
</html>