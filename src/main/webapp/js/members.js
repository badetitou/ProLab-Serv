var id = -1;
function init() {
	var url = getProject();
	getId(url);
}

function getId(url) {
	$.getJSON("/v1/projects/" + url, function(data) {
		if (typeof data === "undefined") {
			alert('bug')
			$('#errorBox').text('Verify your username and password.');
		} else {
			id = data;
		}
	});
}

function addMember(){
	var username = $('#username').val();
	var role = 0;
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "v1/members/",
		dataType : "json",
		data : JSON.stringify({
			"idProject" : id,
			"username" : username,
			"role" : role,
		}),
		success : function(data, textStatus, jqXHR) {
			if (data.username == username) {
				getCollabotators();
			} else {
				$('#errorBox').text('Username/Email already taken.');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('FAIL ADDMEMBERS')
			$('#errorBox').text('Username/Email already taken.');
		}
	});
}

function getCollabotators(){
	$.getJSON("../v1/members/project/"+ id, function(data) {
		alert(data.length);
		var text = "";
		for ( var i = 0; i< data.length; ++i){
			text +=	"<li class='list-group-item'>   "  +
					"<span class='text-primary' data-toggle='collapse' href='#" +  i   +"'>" + data[i].username + "</span>" +
					"<div class='collapse'  id='" + i  + "'>"+
					"<span>" + data[i].firstname + "</span><br/>" +
					"<span>" + data[i].firstname + "</span><br/></div></li></ul>" ;
		}
		alert('maj' + text)
		document.getElementById("result").innerHTML = text;
	});
}