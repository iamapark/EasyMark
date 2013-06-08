tipJS.controller({
	name : "EasyMark.fillBookmarkList",
	invoke:function(params){
		kaka = params;
		var _templateConfig = {
				url:"../EasyMark/templates/bookmarkList.tpl",
				renderTo:"bookmarkListView", 
				data:{
					bookmarkList:params // ["", "", ""]
				}
		};
		temp = this.renderTemplate(_templateConfig); // renderTo 에 넣는것..
	}
});

tipJS.controller({
	name : "EasyMark.fillMemberInfo",
	invoke:function(params){
		console.log('EasyMark.fillMemberInfo');
		var _templateConfig = {
				url:"EasyMark/templates/sendRequestTable.tpl",
				renderTo:"sendRequestTable", // tag id (div id=friendDataTable)
				data:{
					array:params // ["", "", ""]
				}
		};
		this.renderTemplate(_templateConfig); // renderTo 에 넣는것..
	}
});