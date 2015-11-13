var id = -1;
function init(){
	var url = getProject();
	getId(url);
}

function getId(url) {
	$.getJSON("/v1/projects/url/" + url, function(data) {
		if (typeof data === "undefined") {
			$('#errorBox').text('Verify the username.');
		} else {
			id = data;
			setCookie('idProject', id, 265);
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
				$('#errorBox').text('Verify the username.');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#errorBox').text('Verify the username.');
		}
	});
}

function getCollabotators(){
	$.getJSON("../v1/members/project/"+ id, function(data) {
		var text = "";
		for ( var i = 0; i< data.length; ++i){
			text +=	"<li class='list-group-item'>   "  +
					"<span class='text-primary' data-toggle='collapse' href='#" +  i   +"'>" + data[i].username + "</span>" +
					"<div class='collapse'  id='" + i  + "'>"+
					"<span>" + data[i].firstname + " " + data[i].lastname + "</span><br/></div></li></ul>" ;
		}
		document.getElementById("result").innerHTML = text;
	});
}
