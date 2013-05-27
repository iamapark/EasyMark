<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="statisticsDiv">
	<div class="sortable row-fluid">
		<div class="box span6">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-list"></i>접속 시간</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<a data-rel="tooltip" title="6 new members." class="well span6 top-block" href="#">
					<span class="icon32 icon-red icon-user"></span>
					<div>총 접속 시간</div>
					<div>1230</div>
				</a>
				<a data-rel="tooltip" title="6 new members." class="well span6 top-block" href="#">
					<span class="icon32 icon-red icon-user"></span>
					<div>평균 접속 시간</div>
					<div>30</div>
				</a>
			</div>
		</div>
		<div class="box span6">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-list"></i>북마크</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<a data-rel="tooltip" title="6 new members." class="well span6 top-block" href="#">
					<span class="icon32 icon-red icon-user"></span>
					<div>총 북마크 수</div>
					<div>507</div>
				</a>
				<a data-rel="tooltip" title="6 new members." class="well span6 top-block" href="#">
					<span class="icon32 icon-red icon-user"></span>
					<div>평균 북마크 수</div>
					<div>40.3</div>
				</a>
			</div>
		</div>
		</div>
		<div class="row-fluid">
		<div class="box">
			<div class="box-header well">
				<h2><i class="icon-list-alt"></i> 월 별 가입자 수</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<div class="control-group">
								<label class="control-label" for="selectError3">월 선택</label>
								<div class="controls">
								  <select id="monthSelectorForChart">
									<option value="1">1월</option>
									<option value="2">2월</option>
									<option value="3">3월</option>
									<option value="4">4월</option>
									<option value="5">5월</option>
									<option value="6">6월</option>
									<option value="7">7월</option>
									<option value="8">8월</option>
									<option value="9">9월</option>
									<option value="10">10월</option>
									<option value="11">11월</option>
									<option value="12">12월</option>
								  </select>
								</div>
							  </div>
				<div id="myfirstchart"></div>
			</div>
		</div>
		<div class="box">
			<div class="box-header well">
				<h2><i class="icon-list-alt"></i> 시간대 별 로그인 수</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<div id="sincos"  class="center" style="height:300px" ></div>
			</div>
		</div>
	</div>
</div>