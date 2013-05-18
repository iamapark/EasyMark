tipJS.controller({
	name : "EasyMark.fill",
	invoke:function(params){
		console.log('EasyMark.fill');
		var _templateConfig = {
				url:"WEB-INF/view/EasyMark/templates/friendTable.tpl",
				renderTo:"friendDateTable", // tag id (div id=friendDataTable)
				data:{
					userId:"iamapark",
					registerDate:"2013/04/12",
					email:'iamapark'
				}
		};
		this.renderTemplate(_templateConfig); // renderTo
		$('.datetable').dataTable().fnAddData(['1', '2', '3', '4', '5']);
	}
});