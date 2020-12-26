<!-- GLOBAL HEADER -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../common/header.jsp" />
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<a href="${pageContext.request.contextPath }/location/show-all" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fa-sm text-white-50"></i>Show All</a>
</div>

<!-- Content Row -->
<div class="row">
	<div class="col-md-10">
		<div class="p-5 card card-primary card-outline">

			<div class="text-center"><h5>Add New Location</h5>

				<form  class="user form-horizontal"
					   action="${pageContext.request.contextPath }/location/add"
					   method="POST">
					<div class="form-group row">
						<label align="left" class="col-sm-3" for="inputName" >
							Location Name</label>
						<div class="col-sm-9">
							<input type="text" name="locationName"
														   class="form-control form-control-user"  id="inputName"
														   placeholder="Location Name" required>
						</div>
					</div>
					
					<div class="card-footer"><input type="submit" class="btn btn-primary"
													name="submit" value="Add"></div>
				</form>
			</div>
			<!-- /.tab-pane -->
		</div>
		<!-- /.tab-content -->
	</div>

</div>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />
		