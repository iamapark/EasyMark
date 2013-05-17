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
		<div>전체 북마크 수</div>
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
	
	<div class="box span4">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-list"></i> Weekly Stat</h2>
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
			
	<div class="box span4">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> Member Activity</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div class="box-content">
				<ul class="dashboard-list">
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Usman" src="http://www.gravatar.com/avatar/f0ea51fa1e4fae92608d8affee12f67b.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Usman
						</a><br>
						<strong>Since:</strong> 17/05/2012<br>
						<strong>Status:</strong> <span class="label label-success">Approved</span>                                  
					</li>
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Sheikh Heera" src="http://www.gravatar.com/avatar/3232415a0380253cfffe19163d04acab.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Sheikh Heera
						</a><br>
						<strong>Since:</strong> 17/05/2012<br>
						<strong>Status:</strong> <span class="label label-warning">Pending</span>                                 
					</li>
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Abdullah" src="http://www.gravatar.com/avatar/46056f772bde7c536e2086004e300a04.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Abdullah
						</a><br>
						<strong>Since:</strong> 25/05/2012<br>
						<strong>Status:</strong> <span class="label label-important">Banned</span>                                  
					</li>
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Saruar Ahmed" src="http://www.gravatar.com/avatar/564e1bb274c074dc4f6823af229d9dbb.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Saruar Ahmed
						</a><br>
						<strong>Since:</strong> 17/05/2012<br>
						<strong>Status:</strong> <span class="label label-info">Updates</span>                                  
					</li>
				</ul>
			</div>
		</div>
	</div><!--/span-->
			
	<div class="box span4">
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
</div>