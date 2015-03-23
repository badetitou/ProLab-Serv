function create() {
	var name = $('#pro-name').val();
	var description =  $('#pro-description').val();
	var url = $('#pro-name').val();
	var punchline =  $('#pro-punchline').val();
	alert(name);
	alert(description);
	alert(url);
	alert(punchline);
	
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : "v1/projects/" + name,
			dataType : "json",
			data : JSON.stringify({
				"name" : name,
				"description" : description,
				"url" : url,
				"punchline" :  punchline,
			}),
			success : function(data, textStatus, jqXHR) {
					window.location.href = "hub.html"
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('FAIL');
			}
		});
	}
