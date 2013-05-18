<table class="table table-bordered" width="70%">
	<tr>
		<th>USER ID</th>
		<th>STATUS</th>
	</tr>	
		
	<@ for(var i=0; i < data.array.length; i++){ @>	
		<tr>
			<td><@= data.array[i].userId @></td>
		</tr>
	<@ } @> 		
</table>