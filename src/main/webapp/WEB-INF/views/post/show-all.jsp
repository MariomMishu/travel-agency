<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp" />

<!-- Main content -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<a href="${pageContext.request.contextPath }/post/add"
		class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
		class="fa-sm text-white-50"></i>Add New</a>
</div>
<!-- DataTales Example -->
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Show All Posts</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Location</th>
						<th>Visibility</th>
						<th>Actions</th>
						
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Location</th>
						<th>Visibility</th>
						<th>Actions</th>
					</tr>
				</tfoot>
				
				<tbody>
					<c:forEach items="${post_list}" var="post">
						<tr>
							<td>${ post.id }</td>
							<td>${ post.title }</td>
							<td>${ post.getLocation().getLocationName()}</td>
							
							<td>${ post.visibility }</td>
							<td><a href="edit?id=${ post.id }">Edit</a> ||
							<a href="delete?id=${ post.id }">Delete</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>


<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />
