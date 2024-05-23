<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hé - Chỉnh Sửa Category</title>

<%@ include file="/views/admin/common/head.jsp"%>
</head>

<body>
	<!--  Body Wrapper -->
	<div class="page-wrapper" id="main-wrapper" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed">

		<!-- Sidebar Start -->
		<%@ include file="/views/admin/common/assied.jsp"%>
		<!--  Sidebar End -->

		<!--  Main wrapper -->
		<div class="body-wrapper">

			<!--  Header Start -->
			<%@ include file="/views/admin/common/header.jsp"%>
			<!--  Header End -->

			<div class="container-fluid">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title fw-semibold mb-4 mt-2">Chỉnh Sửa Category</h5>
						<div class="card">
							<div class="card-body">
								
							
								<form id="ConfirmEditForm" action="categoryedit" onsubmit="return editCategory()" method="post">
									<div class="mb-3">
										<label for="name" class="form-label">Tên thể loại</label> 
										<input type="text" class="form-control" value="${category.name}" name="name" id="name">
										<!-- Trường ẩn cho ID của Category -->
										<input type="hidden" name="Id" value="${category.id}">
									</div>
									<input type="hidden" id="confirmEdit" value="false" />
									<button type="submit" class="btn btn-success">Xác nhận</button>
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<a class="btn btn-danger float-end" href="categoryviews" role="button">Trở về</a>
								</form>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/views/admin/common/footer.jsp"%>

</body>

</html>