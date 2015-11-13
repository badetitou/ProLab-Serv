var username = getCookie('log');
$.getJSON("../v1/members/" + username, function(data) {
		var text = "";
		for (var i = 0; i < data.length; ++i) {
			text += "<li class='list-group-item'><span class='badge'><a onclick='"
			+ data[i].idi + ")' class='bg-primary'></span></a>"
			+ "<span class='text-primary' data-toggle='collapse' href='#" + i + "'>"
			+ data[i].name + "</span>"
			+ "<div class='collapse'  id='" + i + "'>" + "<span>" + data[i].punchline
			+ "</span><br/><a onclick=projectCookie('"+data[i].id + "','" + data[i].url
			+"')>Page</a><br/>" + "</div></li></ul>";
		}
		document.getElementById("result").innerHTML = text;
	});
function projectCookie(id,url){
	setCookie('project', url, 265);
	setCookie('idProject', id, 265);
	window.location.href = "members.html"
}
