 var fill_friend = function(data){
			var textToInsert = [];
			var i = 0;
			$.each(data, function(count, item){
				textToInsert[i++] = '<tr id='+item.userId+'>';
					textToInsert[i++] = '<td>';
						textToInsert[i++] = '<input type="checkbox">';
					textToInsert[i++] = '</td>';
					
					textToInsert[i++] = '<td>';
						textToInsert[i++] = item.userId;
					textToInsert[i++] = '</td>';
											//,'+item.userId+','+item.friendId+'
					
				textToInsert[i++] = '</tr>';
			
			});
			$('#friend_list').after(textToInsert.join(''));
		};