<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Travel Agency</title>


  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath }/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <link href="${pageContext.request.contextPath }/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath }/css/sb-admin-2.min.css" rel="stylesheet">
  <!--    <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet"> -->
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
         
         
  <link href="${pageContext.request.contextPath }/lib/ckeditor/skins/moono-lisa/editor.css" rel="stylesheet" type="text/css">    
  <link href="${pageContext.request.contextPath }/lib/ckeditor/plugins/scayt/skins/moono-lisa/scayt.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath }/lib/ckeditor/plugins/scayt/dialogs/dialog.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath }/lib/ckeditor/plugins/tableselection/styles/tableselection.css" rel="stylesheet" >
  <link href="${pageContext.request.contextPath }/lib/ckeditor/plugins/wsc/skins/moono-lisa/wsc.css" rel="stylesheet" type="text/css">
</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class=""></i>
        </div>
        <div class="sidebar-brand-text mx-3">Travel Agency</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath }/post/view_all_posts">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Admin
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      
      <li class="nav-item">
        <a class="nav-link"  href="${pageContext.request.contextPath }/location/show-all">
          <i class="fas fa-fw fa-cog"></i>
          <span>Location</span>
        </a>
      </li>

     
  
      <!-- Divider -->
      <hr class="sidebar-divider">

      
      <!-- Heading -->
      <div class="sidebar-heading">
        Post
      </div>
       <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath }/post/view_all_posts">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>All Posts</span></a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath }/post/add">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Add New Post</span></a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath }/post/timeline">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Timeline</span></a>
      </li>
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
        

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
         

            <!-- Nav Item - Alerts -->
              <li class="nav-item dropdown no-arrow mx-1">
           
              <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath }/logout">
                 <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                 Logout</a>
          
            </li>
               <li class="nav-item dropdown no-arrow mx-1">
           
              <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath }/profile">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
          
            </li>
               <li class="nav-item dropdown no-arrow mx-1">
           
               <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath }/update-password">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
          
            </li>


            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${username}</span>
                <img class="img-profile rounded-circle" src="${pageContext.request.contextPath }${user.profilePic}">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="${pageContext.request.contextPath }/profile">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
                <a class="dropdown-item" href="${pageContext.request.contextPath }/update-password">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
                <div class="dropdown-divider"></div>
                 <a class="dropdown-item" href="${pageContext.request.contextPath }/logout">
                 <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                 Logout</a>
               
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">