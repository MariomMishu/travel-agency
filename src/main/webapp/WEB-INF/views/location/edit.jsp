<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<a href="${pageContext.request.contextPath }/location/show-all"
	   class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fa-sm text-white-50"></i>Show All</a>
</div>
<div class="row">
	<div class="col-md-10">
		<div class="p-5 card card-primary card-outline">

			<!-- /.card-header -->
			<div class="text-center">
				<h5>Edit Location</h5>

				<form:form class="user form-horizontal" modelAttribute="locationRm" action="${pageContext.request.contextPath }/batch/edit" method="POST">
					<div class="form-group row">
						<label align="left" class="col-sm-3">Location Name</label>
						<div class="col-sm-9"><form:input path="id" value="${location.id}" hidden="hidden"/>
							<form:input type="text" path="locationName" class="form-control form-control-user"
										placeholder="Location Name" value="${location.locationName}" ></form:input>
						</div>
					</div>
					
					<div class="card-footer">
						<input type="submit" class="btn btn-primary" name="submit" value="Update">

					</div>
				</form:form>
			</div>
			<!-- /.tab-pane -->
		</div>
		<!-- /.tab-content -->
	</div>

</div>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$( function() {
		$( ".datepicker" ).datepicker({
			dateFormat : "dd/mm/yy"
		});
	} );
</script>