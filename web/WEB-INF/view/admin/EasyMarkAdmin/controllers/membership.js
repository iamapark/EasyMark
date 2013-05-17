tipJS.controller({
    name : "EasyMarkAdmin.fillMemberTable",
    invoke:function(params){
    	console.log('EasyMarkAdmin.fillMemberTable');
        var _templateConfig = {
            url:"view/EasyMarkAdmin/templates/memberTable.tpl",
            renderTo:"MemberDataTable",
            data:{
                userId:"iamapark",
                registerDate:"2013/04/12",
                email:'iamapark'
            }
        };
        this.renderTemplate(_templateConfig);
        $('.datatable').dataTable().fnAddData(['1', '2', '3', '4', '5']);
    }
});
