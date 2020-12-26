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

							<img style="border-radius: 50%;" class="img-circle"
								alt="Responsive Image" width="240" height="240"
								src="${pageContext.request.contextPath }${user.profilePic}"
								alt="User profile picture">
						</div>

						<h3 class="profile-username text-center">${user.fullName}</h3>

						<ul class="list-group list-group-unbordered mb-3">
							<li class="list-group-item"><b>Email</b> <a
								class="float-right">${user.email}</a></li>
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
					<div class="text-center">
						<h5>Update Password</h5>

						<form class="user form-horizontal"
							action="${pageContext.request.contextPath }/update-password"
							method="POST">

							<c:if test="${ok == 'false'}">
								<div class="alert alert-danger" role="alert">The Passwords
									didn't match.</div>
							</c:if>
							<c:if test="${ok == 'true'}">
								<div class="alert alert-danger" role="alert">
									Password updated successfully.<br>
									<p>
										<a class="small"
											href="${pageContext.request.contextPath}/login">Click
											here to go to the login page.</a>
									</p>
								</div>
							</c:if>
							<c:if test="${ok == 'false' || ok == ''}">
								
						
							<div class="form-group row">
								<label align="left" class="col-sm-3" for="inputName">
									Old Password</label>
								<div class="col-sm-9">
									<input type="text" name="id" value="${user.id}" hidden="hidden">
									<input type="password" name="old_password"
										class="form-control form-control-user" id="old_password"
										placeholder="Old Password">
								</div>
							</div>
							<div class="form-group row">
								<label align="left" class="col-sm-3" for="inputName">
									New Password</label>
								<div class="col-sm-9">
									<input type="password" name="password"
										class="form-control form-control-user" id="password"
										placeholder="Password">
								</div>
							</div>
							<div class="form-group row">
								<label align="left" class="col-sm-3" for="inputEmail">Re
									Password</label>
								<div class="col-sm-9">
									<input type="password" name="re_password"
										class="form-control form-control-user" id="re_password"
										placeholder="Re Enter New Password">
								</div>
							</div>

							<div >
								<input type="submit" class="btn btn-primary" name="submit"
									value="Update">
							</div>
								</c:if>
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