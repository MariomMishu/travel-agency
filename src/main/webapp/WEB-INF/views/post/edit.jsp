<!-- GLOBAL HEADER -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../common/header.jsp" />

<!-- Page Heading -->
 <div class="d-sm-flex align-items-center justify-content-between mb-4">
	<a href="${pageContext.request.contextPath }/post/show-all"
		class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
		class="fa-sm text-white-50"></i>Show All</a>
</div>

<!-- Content Row -->
<div class="row">
	<div class="col-md-10">
		<div class="p-5 card card-primary card-outline">

			<!-- /.card-header -->
			<div class="text-center">
				<h5>Edit Post</h5>

				<form:form class="user form-horizontal" modelAttribute="postRm" enctype="multipart/form-data"
					action="${pageContext.request.contextPath }/post/edit" method="POST">
					
					
					<div class="form-group row">
						<label align="left" class="col-sm-3">Title</label>
						<div class="col-sm-9">
						<form:input path="id" value="${postRm.id}" hidden="hidden"/>
							<form:input type="text" path="title" 
								class="form-control"  value="${postRm.title}" placeholder="Title"></form:input>
						</div>
					</div>
					<div class="form-group row">
						<label align="left" class="col-sm-3">Status</label>
						<div class="col-sm-9">
							<form:textarea type="text" id="inputStatus" path="status"
								class="form-control form-control-user" value="${postRm.status}" placeholder="Status"></form:textarea>
						</div>
					</div>
					
				 <div class="form-group row">
                                <label for="status" class="col-sm-3">Choose Privacy</label>
                                <div class="col-sm-9">
                                  <%-- <ul style="list-style: none;">
											        <li  th:each="visibilities : ${visibilities}">
											            <input type="radio" th:field="*{visibilities}" th:value="${visibilities}" />
											            <label th:text="${visibilities}"></label>
											        </li>
											      </ul> --%>
                                  <ul style="list-style: none">
                                        <c:forEach var="item" items="${visibilities}">
                                            <li><form:radiobutton path="visibility" value="${item}" />${item} </li>
                                        </c:forEach>
                                    </ul>
                                    <form:errors path="visibility" class="visibility-invalid-message"></form:errors> 
                                </div>
                            </div>
                      <div class="form-group row">
						<label align="left" class="col-sm-3" class="control-label">Check In</label>
						<div class="col-sm-9">
							<form:select class="form-control" path="locationId"
								style="width: 100%; color: black;">
								<form:options items="${location_list}" itemLabel="locationName"
									itemValue="id"></form:options>

							</form:select>
						</div>
					</div>  
					

					<input type="submit" class="btn btn-primary" name="submit"
						value="Create">


				</form:form>
			</div>
			<!-- /.tab-pane -->
		</div>
		<!-- /.tab-content -->
	</div>

</div>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />
<script type="text/javascript">
		CKEDITOR.replace('inputStatus',
				{
					toolbar : [
							{
								name : 'styles',
								items : [ 'Styles', 'Format', 'Font',
										'FontSize' ]
							},
							{
								name : 'basicstyles',
								groups : [ 'basicstyles', 'cleanup' ],
								items : [ 'Bold', 'Italic', 'Underline',
										'Strike', 'Subscript', 'Superscript',
										'-', 'CopyFormatting', 'RemoveFormat' ]
							},
							{
								name : 'links',
								items : [ 'Link', 'Unlink', 'Anchor' ]
							},
							{
								name : 'colors',
								items : [ 'TextColor' ]
							},
							{
								name : 'paragraph',
								groups : [ 'list', 'indent', 'blocks', 'align',
										'bidi' ]
							}, {
								name : 'links'
							}, {
								name : 'insert'
							} ]
				});

		
	</script>


