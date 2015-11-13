/* Log the user in */
function logIn() {
	var username = $('#username').val();
	var password =  $('#pwd').val();
	if(password == "")
		$('#errorBox').text('Enter a password, please.');
	else{
		$.getJSON("v1/users/" + username + "&" + password, function(data) {
			if (typeof data === "undefined") {
				$('#errorBox').text('Verify your username and password, please.');
			} else{
				setCookie('log', username, 265);
				window.location.href = "hub.html"
			}
		});
	}
}
