$(document).ready(function() {

//-----------------------------------------------------------------------------------
//	2.	Fix Classes after Validate Login
//-----------------------------------------------------------------------------------

$('.submit').click(function() {
	var ValPassword = $('#password').val() === 'admin';
    if (ValPassword === true) {
		$('input[type=password]').addClass('valid');
		$('.tooltip-pass').hide();
		$('.submit').removeClass('submit').addClass('charge');
		$('#pageLogin').addClass('initLog').delay(1900).queue(function() { $(this).removeClass('initLog').addClass('initLogExit'); $(this).dequeue(); });;
		//$('#page, #head').delay(2500).queue(function() { $(this).addClass('vis'); $(this).dequeue(); });
		//$('.window').delay(3000).queue(function() { $(this).addClass('windows-vis'); $(this).dequeue(); });
		event.preventDefault();
    }
    else {
		$('.tooltip-pass').hide();
		$('input[type=password]').select();
    	$('.validate').addClass('error').delay(210).queue(function() { $(this).removeClass('error'); $(this).dequeue(); $('.tooltip-pass').show(); });
			return false;
    	}
});

}); 