tipJS.controller({
	name : "EasyMark.fill",
	invoke:function(params){
		console.log('EasyMark.fill');
		var _templateConfig = {
				url:"EasyMark/templates/friendTable.tpl",
				renderTo:"friendTable", // tag id (div id=friendDataTable)
				data:{
					array:params // ["", "", ""]
				}
		};
		this.renderTemplate(_templateConfig); // renderTo 에 넣는것..
	}
});

tipJS.controller({
	name : "EasyMark.sendfill",
	invoke:function(params){
		console.log('EasyMark.sendfill');
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

tipJS.controller({
	name : "EasyMark.hello",
	invoke:function(params){
		console.log('EasyMark.hello');
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