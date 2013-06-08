	<@ for(var i=0; i < data.bookmarkList.length; i++){ @>
		<li data-role="list-driver" data-theme="b">
			<@= data.bookmarkList[i].bookMarkName @>
			<span class="ui-li-count"><@=data.bookmarkList[i].frequency@></span>
		</li>
		<@ if(data.bookmarkList[i].status == 'category'){@>
			category
			<a href="category_page.html" data-categoryid='<@= data.bookmarkList[i].category @>'>
				<img src='../<@=data.bookmarkList[i].imgUrl@>' />
				<h3><@= data.bookmarkList[i].bookMarkUrl @></h3>
				<p>
					<@= data.bookmarkList[i].bookMarkDescript @>
				</p>
			</a>
		<@}@>
		
		<@ if(data.bookmarkList[i].status == 'bookmark'){@>
			<a target="_blank" href="<@= data.bookmarkList[i].bookMarkUrl @>">
				<img src="../<@= data.bookmarkList[i].imgUrl @>">
				<h3><@= data.bookmarkList[i].bookMarkUrl @></h3>
				<p>
					<@= data.bookmarkList[i].bookMarkDescript @>
				</p>
			</a>
		<@}@>
	<@ } @> 