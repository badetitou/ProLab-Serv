function getProject()
{
	$.getJSON("../v1/projects", function(data) {
		var text = "";
		for ( var i = 0; i< data.length; ++i){
			text +=	"<li class='list-group-item'><span class='badge'><a onclick='addLike(" + data[i].idi + ")' class='bg-primary'></span></a>      "  +
					"<span class='text-primary' data-toggle='collapse' href='#" +  i   +"'>" + data[i].name + "</span>" +
					"<div class='collapse'  id='" + i  + "'>"+
					"<span>" + data[i].punchline + "</span><br/>" + "</div></li></ul>" ;
		}
		document.getElementById("result").innerHTML = text;
	});
}