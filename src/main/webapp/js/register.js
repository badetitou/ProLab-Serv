function register() {
	var username = $('#username').val();
	alert('test');
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
			"surname" : $('#lastname').val(),
		}),
		success : function(data, textStatus, jqXHR) {
			if (data.username == username) {
				alert('test2');
				console.log("User created");
				window.location.href = "app/hub.html"
			} else {
				alert("username déjà pris");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error Creat User");
		}
	});
}