function logIn() {
	var pseudo = $('#pseudo').val();
	var password =  $('#pwd').val();
	if(password == "")
	$('#errorBox').text('Enter a password');
	else{
		$.getJSON("v1/users/" + pseudo + "&" + password, function(data) {
			if (typeof data === "undefined") {
				$('#errorBox').text('Verify your username and password.');
			} else{
				setCookie('log', pseudo, 265);
				window.location.href = "hub.html"
			}
		});
	}
}

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires +";path=/";
}
