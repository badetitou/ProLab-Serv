/*
	Create a new project
*/
function create() {
	var username = getCookie('log');
	var name = $('#pro-name').val();
	var description =  $('#pro-description').val();
	var url = $('#pro-name').val();
	var punchline =  $('#pro-punchline').val();

	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "v1/projects/" + username,
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"description" : description,
			"url" : url,
			"punchline" :  punchline,
		}),
		success : function(data, textStatus, jqXHR) {
				setCookie('project', url, 265);
				window.location.href = "members.html"
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#errorBox').text('Fail, retry.');
		}
	});
}
