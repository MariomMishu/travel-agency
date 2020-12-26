<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp" />

<!-- Main content -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<a href="${pageContext.request.contextPath }/post/add"
		class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
		class="fa-sm text-white-50"></i>Add New</a>
</div>

<div class="row">

	<!-- Area Chart -->
	<div class="col-xl-8 col-lg-7">
		<div class="card shadow mb-4">
			<!-- Card Header - Dropdown -->
			<c:forEach items="${post_list}" var="post">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<div class="row">
					<img alt="${user.fullName}"
									style="width: 30px; height: 30px; display: block;"
									class="img-profile rounded-circle"
									src="${pageContext.request.contextPath }${user.profilePic}">
					<h6 class="m-0 font-weight-bold text-primary">${ post.userId.fullName }
						Posted
					</h6>
	</div>
				</div>
				<!-- Card Body -->
				<div class="card-body">

					<div class="row"><i class="fas fa-edit"></i> ${ post.status }</div>
					<div class="row"> <i class="fa fa-map-marker" aria-hidden="true"></i>  ${ post.getLocation().getLocationName()}
</div>
									</div>
			</c:forEach>

		</div>
	</div>

	<!-- Pie Chart -->
	<div class="col-xl-4 col-lg-5">
		<div class="card shadow mb-4">
			<!-- Card Header - Dropdown -->
			<div
				class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				<h6 class="m-0 font-weight-bold text-primary">Profile</h6>
				<div class="dropdown no-arrow">
					<a class="dropdown-toggle" href="#" role="button"
						id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i
						class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
					</a>
					<div
						class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
						aria-labelledby="dropdownMenuLink">
						<div class="dropdown-header">Dropdown Header:</div>
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div>
				</div>
			</div>
			<!-- Card Body -->
			<div class="card-body">
				<div class="chart-pie pt-4 pb-2">
					<canvas id="myPieChart"></canvas>
				</div>
				<div class="mt-4 text-center small">
					<span class="mr-2"> <i class="fas fa-circle text-primary"></i>
						 Post
					</span> <span class="mr-2"> <i class="fas fa-circle text-success"></i>
						Issue
					</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
						Referral
					</span>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />
