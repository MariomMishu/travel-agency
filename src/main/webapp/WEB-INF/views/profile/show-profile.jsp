<!-- GLOBAL HEADER -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../common/header.jsp" />

<!-- Main content -->
<section class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-5">

				<!-- Profile Image -->
				<div class="card card-primary card-outline">
					<div class="card-body box-profile">
						<div class="text-center">

							<img style="border-radius: 50%;"  class="img-circle" alt="Responsive Image" width="240" height="240" src="${pageContext.request.contextPath }${user.profilePic}" alt="User profile picture">
						</div>

						<h3 class="profile-username text-center">${user.fullName}</h3>

						<p class="text-muted text-center">Software Engineer</p>

						<ul class="list-group list-group-unbordered mb-3">
							<li class="list-group-item"><b>Email</b> <a
								class="float-right">${user.email}</a></li>
							<li class="list-group-item"><b>Department</b> <a
								class="float-right">${user.dept}</a></li>
							<li class="list-group-item"><b>User Name</b> <a
								class="float-right">${user.username}</a></li>
						</ul>

						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->

				<!-- /.card -->
			</div>
			<!-- /.col -->
			<div class="col-md-7">
				<div class="p-5 card card-primary card-outline">
						
					<!-- /.card-header -->
					<div class="text-center"><h5>Update Profile</h5>
						
								<form  class="user form-horizontal"
									action="${pageContext.request.contextPath }/profile/edit"
									enctype="multipart/form-data" method="POST">
									<div class="form-group row">
										<label align="left" class="col-sm-3" for="inputName" >
											Full Name</label>
										<div class="col-sm-9">
											<input type="text" name="id" value="${user.id}"
												hidden="hidden"> <input type="text" name="fullName"
												class="form-control form-control-user" value="${user.fullName}" id="inputName"
												placeholder="Full Name">
										</div>
									</div>
									<div class="form-group row">
										<label align="left"  class="col-sm-3" for="inputEmail" >Email</label>
										<div class="col-sm-9">
											<input type="text" name="email" class="form-control form-control-user"
												value="${user.email}" id="inputName" placeholder="Email">
										</div>
									</div>
									<div class="form-group row">
										<label align="left"  class="col-sm-3" for="inputName" >
											Department</label>
										<div class="col-sm-9">
											 <input type="text" name="dept"
												class="form-control form-control-user" value="${user.dept}" id="inputName"
												placeholder="Full Name">
										</div>
									</div>
									<div class="form-group row">
										<label align="left"  class="col-sm-3" for="inputName2" >User
											Name</label>
										<div class="col-sm-9"><input type="text" readonly="readonly" class="form-control form-control-user"
												value="${user.username}" ></div>
									</div>
									<div class="form-group row">
										<label align="left"  class="col-sm-3">Profile
											Picture </label>
										<div class="col-sm-9">
											<input type="file" name="file" />
										</div>
									</div>
									<div class="">
										<input type="submit" class="btn btn-primary" name="submit"
											value="Update">
									</div>
								</form>
							</div>
							<!-- /.tab-pane -->
						</div>
						<!-- /.tab-content -->
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.nav-tabs-custom -->
			</div>
			<!-- /.col -->
		
	<!-- /.container-fluid -->
</section>
<!-- /.content -->



<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />