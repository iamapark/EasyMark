<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="dashboardDiv">
<div class="sortable row-fluid">
	<a data-rel="tooltip" title="6 new members." class="well span3 top-block" href="#">
		<span class="icon32 icon-red icon-user"></span>
		<div>전체 회원 수</div>
		<div id="totalMemberCount"></div>
	</a>

	<a data-rel="tooltip" title="4 new pro members." class="well span3 top-block" href="#">
		<span class="icon32 icon-color icon-star-on"></span>
		<div>오늘 가입한 회원 수</div>
		<div id="todayRegisterCount"></div>
	</a>

	<a data-rel="tooltip" title="$34 new sales." class="well span3 top-block" href="#">
		<span class="icon32 icon-color icon-cart"></span>
		<div>현재 로그인 중인 사용자 수</div>
		<div id="loginMemberCount"></div>
	</a>
	
	<a data-rel="tooltip" title="12 new messages." class="well span3 top-block" href="#">
		<span class="icon32 icon-color icon-envelope-closed"></span>
		<div>Messages</div>
		<div id="messageCount"></div>
	</a>
</div>

<div class="row-fluid sortable">
			
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-list-alt"></i> Realtime Traffic</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div id="realtimeChart" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
	</div><!--/span-->
</div><!--/row-->
<div class="row-fluid sortable">
	<div class="box span6">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-list"></i> 접속 中 회원</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<ul class="dashboard-list" id="loginMemberListUl">
				
			</ul>
		</div>
	</div><!--/span-->
</div>
</div>