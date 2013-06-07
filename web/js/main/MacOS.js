function addPrevClass (e) {
	var target = e.target;
	if(target.getAttribute('src')) { // check if it is img
		var li = target.parentNode.parentNode;
		var prevLi = li.previousElementSibling;
		if(prevLi) {
			prevLi.className = 'prev';
		}
	
		target.addEventListener('mouseout', function(){
			if(prevLi) {
				prevLi.removeAttribute('class');
			}
		}, false);
	}
}
if (window.addEventListener) {
	//document.getElementById('dock').addEventListener('mouseover', addPrevClass, false);
}

$(document).ready(function(){
	$.ajax({
		url:'messageCount',
		dataType : 'json',
		type:'POST'
	}).done(function(data){
		console.log("data :"+data.toString());
		$('#messageCount').text("쪽지("+data.toString()+")");		
	});
});