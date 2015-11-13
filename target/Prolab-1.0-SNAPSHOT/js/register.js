/*
	Register a new user
*/
function register() {
	var username = $('#username').val();
	var password =  $('#pwd').val();
	var password2 =  $('#pwd2').val();
	var email =  $('#email').val();
	var firstname =  $('#firstname').val();
	var lastname =   $('#lastname').val();

	if(password != password2){
		$('#errorBox').text('Passwords doesn\'t match.');
	} else if(password == "") {
		$('#errorBox').text('Enter a password.');
	} else {
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : "v1/users/",
			dataType : "json",
			data : JSON.stringify({
				"username" : username,
				"password" : password,
				"email" : email,
				"firstname" :  firstname,
				"lastname" : lastname,
			}),
			success : function(data, textStatus, jqXHR) {
				if (data.username == username) {
					setCookie('log', username, 265);
					window.location.href = "hub.html"
				} else {
					$('#errorBox').text('Username/Email already taken.');
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#errorBox').text('Username/Email already taken.');
			}
		});
	}
}
