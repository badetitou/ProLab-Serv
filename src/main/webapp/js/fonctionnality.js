function showValue(newValue) {
	document.getElementById("range").innerHTML = newValue + "%";
}

var idMember = -1;
function init(){
	getIdMember();
}

function addFonctionnality() {
	var name = $('#name').val();
	var description = $('#description').val();
	var avancement = $('#avancement').val();
	var deadLine = new Date($('#deadLine').val());
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "v1/fonctionnalities/" + idMember,
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"description" : description,
			"avancement" : avancement,
			"deadLine" : deadLine,
		}),
		success : function(data, textStatus, jqXHR) {
			if (data.name == name) {
				alert('GJ');
			} else {
				alert('FAIL NAME');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('FAIL');
		}
	});
}

function getIdMember() {
	var username = getCookie('log');
	var idproject = getCookie('idProject');
	$.getJSON("v1/members/" + username + "&" + idproject, function(data) {
		if (typeof data === "undefined") {
			alert('bug idMember');
		} else {
			idMember = data.idMember;
		}
	});
}