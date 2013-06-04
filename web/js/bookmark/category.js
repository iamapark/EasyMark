var currentPageCategoryId = 0; // 시작은 무조건 바탕화면에서부터
var selectedCategoryId = 0;

// setting 모달에서 카테고리 탭을 클릭했을 때
$('a[href="#setting_categories"]').click(function(e){
	$('#categoryOl').empty();
	$('#selectedCategory').text('바탕화면');
	
	$.ajax({
		url:'getCategoryTree',
		dataType:'json'
	}).done(function(data){
		kaka = data;
		var ol = makeChildren(data);
		
		$('#categoryOl').append($(ol));
	});

});

var makeChildren = function(ch){
	var str = '';
	
	if(ch.children){
		if(ch.categoryId == 0)
			str = "<ol class='tree'>";
		else
			str = "<ol>";
		
				str += "<li>";
					str += "<label onclick='categoryClick(this)' for='category-" + ch.categoryId + "'>" + ch.categoryName + "</label>";
					str += "<input type='checkbox' id='category-" + ch.categoryId + "'/>";
				
				str += makeChildren(ch.children);
			
				str += "</li>";
			str += '</ol>';
	}
		
	else{
		if(ch.length){
			if(ch.categoryId == 0)
				str = "<ol class='tree'>";
			else
				str = "<ol>";
			
			for(var i=0; i<ch.length; i++){
				str += "<li>";
					str += "<label onclick='categoryClick(this)' for='category-" + ch[i].categoryId + "'>" + ch[i].categoryName + "</label>";
					str += "<input type='checkbox' id='category-" + ch[i].categoryId + "' />";
						
						if(ch[i].children){
							str += makeChildren(ch[i].children);
						}
					
				str += "</li>";
			}
			
			str += '</ol>';
		}else{
			str = "<ol class='tree'>";
				str += "<li>";
					str += "<label onclick='categoryClick(this)' for='category-0'>바탕화면</label>";
					str += "<input type='checkbox' id='category-0'/>";
				str += "</li>";
			str += '</ol>';
		}
	}
	
	return str;

};

// 카테고리 폴더 목록에서 디렉토리 하나를 클릭했을 때
var categoryClick = function(li){
	
	$('.tree li label').removeClass('selectedLabel');
	$(li).addClass('selectedLabel');

	var categoryName = $(li).text();
	selectedCategoryId = $(li).attr('for').split('category-')[1];
	
	$('#selectedCategory').text(categoryName);
};

// 카테고리 추가 버튼을 클릭했을 때
$('#newCategoryButton').click(function(d){
	var categoryName = $('#newCategory').val();
	
	if(categoryName != ''){
		console.log(selectedCategoryId + "카테고리 밑에 " + categoryName + " 카테고리 생성");
	}
	
	$.ajax({
		url:'addCategory',
		dataType:'json',
		type:'POST',
		data:{
			parentId: selectedCategoryId,
			categoryName: categoryName
		}
	}).done(function(data){
		var label = '';
		var li = $("label[for='category-"+selectedCategoryId+"']").parent();
		
		if(li.find('ol').length > 0){ // 부모 카테고리에 이미 자식 카테고리들이 있음
			label += "<li>";
				label += "<label onclick='categoryClick(this)' for='category-" + data.categoryId + "'>" + categoryName + "</label>";
				label += "<input type='checkbox' id='category-" + data.categoryId + "' />";
			label += "</li>";
		
			li.find('ol').append(label);
			
		}else{ // 부모 카테고리에 자식 카테고리들이 없음.
			label = "<ol>";
				label += "<li>";
					label += "<label onclick='categoryClick(this)' for='category-" + data.categoryId + "'>" + categoryName + "</label>";
					label += "<input type='checkbox' id='category-" + data.categoryId + "' />";
				label += "</li>";
			label += "</ol>";
			li.append(label);
		}
		
	});
});

// 카테고리 삭제 버튼을 클릭했을 때
$('#deleteCategoryButton').click(function(e){
	
	var label = $("label[for='category-"+selectedCategoryId+"']").parent();
	var flag = false;
	
	if(label.find('ol').length > 0){ // 지우려는 카테고리에 자식 카테고리가 있을 때
		flag = confirm('해당 카테고리를 지우면 자식 카테고리까지 지워집니다. 정말 지우시겠습니까?');
		
	}else{ // 지우려는 카테고리에 자식 카테고리가 없을 때
		flag = true;
	}
	
	if(flag){
		$.ajax({
			url:'deleteCategory',
			dataType:'json',
			type:'POST',
			data:{
				categoryId: selectedCategoryId
			}
		}).done(function(data){
			$("label[for='category-"+selectedCategoryId+"']").parent().parent().remove()
		});
	}else{
		return false;
	}

});


//카테고리 더블클릭 시 해당하는 북마크 목록 보여준다
var viewCategory = function(category){
	newLi = '';
	console.log(category);
	kaka = category;
	
	var categoryId = $(category).parent().data('categoryid');
	console.log('categoryId: ' + categoryId + '로 접속합니다.');
	$.ajax({
		url:'viewCategory',
		dataType:'json',
		data:{
			categoryId: categoryId
		}
	}).done(function(data){
		kaka = data;
		gridster.remove_all_widgets();
		gridster.remove_change();
		// 상위 카테고리로 올라가는 아이콘, 부모 카테고리 아이디가 있을 경우에만 화면에 띄운다.
		if(data.parentId != null){
			newLi = '<li data-id="0" data-categoryId="' + data.parentId + '" data-toggle="tooltip" title="상위 카테고리로" data-row="1" data-col="1" data-sizex="1" data-sizey="1" class="bookmarkIcon">';
				newLi += '<img id="img" href="" src="images/uparrow.png" style="width:100%; height:100%;border-radius:20px;">';
				newLi += '<div class="bookmarkIconInfo">상위 카테고리로</div>';
			newLi += '</li>';
			gridster.add_widget(newLi, 1, 1);
		}
		
		for(var i=0;i<data.list.length;i++){
			var bookMark = data.list[i];
			newLi = '<li data-id="' + bookMark.bookMarkId + '" data-categoryId="' + bookMark.category + '" data-toggle="tooltip" title="'+bookMark.bookMarkName+'" data-row="'+bookMark.posX+'" data-col="'+bookMark.posY+'" data-sizex="1" data-sizey="1" class="bookmarkIcon">';
				newLi += '<img id="img" href="'+ bookMark.bookMarkUrl +'" src="'+ bookMark.imgUrl +'" style="width:100%; height:100%;border-radius:20px;">';
				newLi += '<div class="bookmarkIconInfo">' + bookMark.bookMarkName +'</div>';
			newLi += '</li>';
			gridster.add_widget(newLi, 1, 1, bookMark.posY, bookMark.posX);
		
		}/**/
		init();
	});
};