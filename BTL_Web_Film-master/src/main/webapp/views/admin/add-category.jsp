<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hé - Danh Sách Category</title>

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
						<a class="btn btn-primary float-end" href="categoryadd" role="button">
							<i class="ti ti-file-plus"></i> Phim mới
						</a>
						<h5 class="card-title fw-semibold mb-4 mt-2">Danh Sách Category
							Đang Công Chiếu</h5>
						<div class="card">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col">Id</th>
												<th scope="col">Thể loại</th>
												<th scope="col">Hành động</th>
											</tr>
										</thead>
										<tbody>


											<c:forEach items="${categories}" var="category" varStatus="loop">
												<tr>
													<td scope="row">${category.id}</td>
													<td>${category.name}</td>
													<td>
														<div class="btn-group" role="group">
															<button class="btn btn-primary ms-2 rounded-2"
																onclick="editCategoryGetId('${category.id}')">Sửa</button>
															<button class="btn btn-danger ms-2 rounded-2"
																onclick="deleteCategory('${category.id}')">Xoá</button>
														</div>
													</td>
												</tr>

												<!-- Modal -->
												<form id="categoryForm" action="categorydelete" method="post">
													<input type="hidden" name="confirmation" id="confirmDelete"
														value="false" /> <input type="hidden" id="CategoryId"
														name="Id">
												</form>
												<form id="editForm" action="categoryedit" method="get">
													<input type="hidden" id="categoryEditId" name="Id">
												</form>

											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/views/admin/common/footer.jsp"%>

	<%
	Boolean addCategorySuccess = (Boolean) session.getAttribute("addCategorySuccess");

	if (addCategorySuccess != null) {
		if (addCategorySuccess) {
	%>
	<script>
		showSwalAlert('success', 'Thêm category thành công !');
	</script>
	<%
	} else {
	%>
	<script>
		showCenterAlert('error', 'Thất Bại !', 'Category đã tồn tại trong cơ sở dữ liệu !');
	</script>
	<%
	}
	session.removeAttribute("addCategorySuccess");
	}
	%>

	<script type="text/javascript">
		// lấy href	sử dụng cho edit category
		function editCategoryGetId(Id) {
			document.getElementById("categoryEditId").value = Id;
			document.getElementById("editForm").submit();
		}
		
		function deleteCategory(Id) {
	        Swal.fire({
	            title: 'Cảnh Báo !',
	            text: "Bạn có chắc chắn xóa thể loại không ?",
	            icon: 'warning',
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: 'Đồng ý !'
	        }).then((result) => {
	            if (result.isConfirmed) {
	                document.getElementById("confirmDelete").value = "true";
	                Swal.fire(
	                    'Thành công !',
	                    'Xóa thể loại thành công !',
	                    'success'
	                ).then(() => {
	                    document.getElementById("CategoryId").value = Id;
	                    document.getElementById("categoryForm").submit();
	                });
	            }
	        });

	        return false;
	    }
	</script>

</body>

</html>