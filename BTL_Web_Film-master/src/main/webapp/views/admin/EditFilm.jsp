<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Chỉnh Sửa Movie</title>

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
						<h5 class="card-title fw-semibold mb-4 mt-2">Chỉnh Sửa Phim</h5>
						<div class="card">
							<div class="card-body">
								<form id="ConfirmEditForm" action="movieedit"
									onsubmit="return editMovie()" method="post">
									<input type="hidden" name="Id" value="${movie.id}">
									<div class="mb-3">
										<label for="title" class="form-label">Tên phim</label> <input
											type="text" class="form-control" value="${movie.title}"
											name="title" id="title">
									</div>
									<fieldset>
										<div class="mb-3">
											<label for="link-phim" class="form-label">Link phim1</label> <input
												type="text" class="form-control" value="${movie.href1}"
												name="href1" readonly>
										</div>
									</fieldset>
									<fieldset>
										<div class="mb-3">
											<label for="link-phim" class="form-label">Link phim2</label> <input
												type="text" class="form-control" value="${movie.href2}"
												name="href2" readonly>
										</div>
									</fieldset>
									<fieldset>
										<div class="mb-3">
											<label for="link-phim" class="form-label">Link phim3</label> <input
												type="text" class="form-control" value="${movie.href3}"
												name="href3" readonly>
										</div>
									</fieldset>
									<div class="mb-3">
										<label for="dao-dien" class="form-label">Đạo diễn</label> <input
											type="text" class="form-control" value="${movie.daodien}"
											name="daodien" id="dao-dien">
									</div>
									<div class="mb-3">
										<label for="dien-vien" class="form-label">Diễn viên</label> <input
											type="text" class="form-control" value="${movie.dienvien}"
											name="dienvien" id="dien-vien">
									</div>
									<div class="mb-3">
										<label for="categories">Categories:</label><br>
								        <select id="categories" name="categoryIds" multiple>
								            <c:forEach var="category" items="${categories}">
								                <option value="${category.id}">${category.name}</option>
								            </c:forEach>
								        </select><br>
									</div>
									<input type="hidden" class="form-control" value="${movie.poster} id="poster"
										name="poster">
									<div class="mb-3">
										<label for="mo-ta" class="form-label">Mô tả</label> <input
											type="text" class="form-control" value="${movie.mota}"
											name="mota" id="mo-ta">
									</div>
									<div class="mb-3">
										<label class="form-label">Giá</label> <input type="text"
											class="form-control" value="${formattedPrice}"
											oninput="formatPrice(this)" name="price" placeholder="Giá...">
									</div>
									<fieldset disabled>
										<div class="mb-3">
											<fmt:formatDate var="fmtDate" value="${movie.addDate}"
												pattern="dd/MM/yyyy HH:mm:ss" />
											<label class="form-label">Ngày thêm</label> <input
												type="text" class="form-control" value="${fmtDate}">
										</div>
									</fieldset>
									<div class="mb-3">
										<label for="noted" class="form-label">Nội dung</label>
										<textarea class="form-control" id="noted" name="noted"
											rows="5">${movie.description}</textarea>
									</div>

									<input type="hidden" id="confirmEdit" value="false" />

									<button type="submit" class="btn btn-success">Xác nhận</button>
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<a class="btn btn-danger float-end" href="movieviews"
										role="button">Trở về</a>

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