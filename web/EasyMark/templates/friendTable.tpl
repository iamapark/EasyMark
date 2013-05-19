<table class="table table-bordered" width="70%">
	<tr>	
		<th>UserID</th>
		<th>UserName</th>
		<th>E-mail</th>
		<th>Status</th>
	</tr>
	
	<@ for(var i=0; i < data.array.length; i++){ @>
		<tr>
			<td><@= data.array[i].userId @></td>
			<td><@= data.array[i].name @></td>
			<td><@= data.array[i].email @></td>
			<td>
				<button id="delete" class="btn btn-small btn btn-danger" type="button" onclick="deleteFriend('data.array[i].userId');">친구삭제</button>
			</td>	
		<tr>
	<@ } @> 
	
</table>