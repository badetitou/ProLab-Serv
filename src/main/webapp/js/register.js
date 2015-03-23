function register() {
	var username = $('#username').val();
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "v1/users/",
		dataType : "json",
		data : JSON.stringify({
			"username" : $('#username').val(),
			"password" :  $('#pwd').val(),
			"email" :  $('#email').val(),
			"firstname" :  $('#firstname').val(),
			"lastname" : $('#lastname').val(),
		}),
		success : function(data, textStatus, jqXHR) {
			if (data.username == username) {
				setCookie('log', username, 265);
				console.log("User created");
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

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires +";path=/";
}
