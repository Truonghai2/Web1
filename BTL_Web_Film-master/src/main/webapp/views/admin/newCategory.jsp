<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>hê - Thêm Video</title>

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
						<h5 class="card-title fw-semibold mb-4 mt-2">Thêm Mới Thể Loại</h5>
						<div class="card">
							<div class="card-body">
								<form onsubmit="return validateNewCategory()" action="categoryadd"
									method="post">

									<div class="mb-3">
										<label for="title" class="form-label">Thể loại</label> <input
											type="text" class="form-control" name="name" id="name"
											placeholder="Thể loại...">
									<button type="submit" class="btn btn-success">Xác nhận</button>
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<a class="btn btn-danger float-end" href="categoryviews"
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

	<script type="text/javascript">
		
	</script>

</body>

</html>