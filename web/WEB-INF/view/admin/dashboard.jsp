<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="dashboardDiv">
<div class="sortable row-fluid">
	<a data-rel="tooltip" title="6 new members." class="well span3 top-block" href="#">
		<span class="icon32 icon-red icon-user"></span>
		<div>전체 회원 수</div>
		<div>507</div>
	</a>

	<a data-rel="tooltip" title="4 new pro members." class="well span3 top-block" href="#">
		<span class="icon32 icon-color icon-star-on"></span>
		<div>오늘 가입한 회원 수</div>
		<div>228</div>
	</a>

	<a data-rel="tooltip" title="$34 new sales." class="well span3 top-block" href="#">
		<span class="icon32 icon-color icon-cart"></span>
		<div>현재 로그인 중인 사용자 수</div>
		<div>1320</div>
	</a>
	
	<a data-rel="tooltip" title="12 new messages." class="well span3 top-block" href="#">
		<span class="icon32 icon-color icon-envelope-closed"></span>
		<div>Messages</div>
		<div>25</div>
		<span class="notification red">12</span>
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
			<div id="realtimechart" style="height:190px;"></div>
				<p class="clearfix">You can update a chart periodically to get a real-time effect by using a timer to insert the new data in the plot and redraw it.</p>
				<p>Time between updates: <input id="updateInterval" type="text" value="" style="text-align: right; width:5em"> milliseconds</p>
		</div>
	</div><!--/span-->
</div><!--/row-->
<div class="row-fluid sortable">
	<div class="box span4">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-list"></i> 최근 접속 회원</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<ul class="dashboard-list">
				<li>
					<a href="#">
						<i class="icon-arrow-up"></i>                               
						<span class="green">92</span>
						New Comments                                    
					</a>
				</li>
			  <li>
				<a href="#">
				  <i class="icon-arrow-down"></i>
				  <span class="red">15</span>
				  New Registrations
				</a>
			  </li>
			  <li>
				<a href="#">
				  <i class="icon-minus"></i>
				  <span class="blue">36</span>
				  New Articles                                    
				</a>
			  </li>
			  <li>
				<a href="#">
				  <i class="icon-comment"></i>
				  <span class="yellow">45</span>
				  User reviews                                    
				</a>
			  </li>
			  <li>
				<a href="#">
				  <i class="icon-arrow-up"></i>                               
				  <span class="green">112</span>
				  New Comments                                    
				</a>
			  </li>
			  <li>
				<a href="#">
				  <i class="icon-arrow-down"></i>
				  <span class="red">31</span>
				  New Registrations
				</a>
			  </li>
			  <li>
				<a href="#">
				  <i class="icon-minus"></i>
				  <span class="blue">93</span>
				  New Articles                                    
				</a>
			  </li>
			  <li>
				<a href="#">
				  <i class="icon-comment"></i>
				  <span class="yellow">254</span>
				  User reviews                                    
				</a>
			  </li>
			</ul>
		</div>
	</div><!--/span-->
</div>
</div>