<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="membershipDiv">
<div>
	<ul class="breadcrumb">
		<li>
			<a href="#">Home</a> <span class="divider">/</span>
		</li>
		<li>
			<a href="#">Tables</a>
		</li>
	</ul>
</div>

<div class="row-fluid sortable">		
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> Members</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
			  <thead>
				  <tr>
				  	  <th>선택</th>
					  <th>user ID</th>
					  <th>Name</th>
					  <th>Date registered</th>
					  <th>E-mail</th>
					  <th>북마크 수</th>
					  <th>Actions</th>
				  </tr>
			  </thead>
			  <tbody id="MemberDataTable">
				<!-- 여기에 회원 정보가 들어간다. -->
			  </tbody>
		  </table>            
		</div>
	</div><!--/span-->

</div><!--/row-->
</div>

<!-- MODAL -->
<!-- 회원 정보 MODAL 회원 테이블에서 View 버튼을 누르면 화면에 뜬다. -->
	<jsp:include page="modal/memberInfo.jsp"></jsp:include>
<!-- MODAL END -->