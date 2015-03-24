function init(){
  $("#time").text(new Date().toUTCString());
  document.getElementById('msg').onkeypress = function(e){
    if (!e) e = window.event;
    var keyCode = e.keyCode || e.which;
    if (keyCode == '13'){
      send();
      return false;
    }
  }
}

function send(){
  var msgstr = $("#msg").val();
  var authorstr = getCookie("log");
  var datestr = new Date().toUTCString();
  $("#msg").val("");

  var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/1");
  chat1.push(
    {
      author: authorstr,
      msg: msgstr,
      date: datestr
    });
  }

  var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/1");
  chat1.on("child_added", function(snapshot){
    var newMsg = snapshot.val();
    var final = ""
    + "<li class='msg list-group-item'>"
    + "<span class='badge'>" + newMsg.date + "</span>"
    + "<b>" + newMsg.author + "</b> " + newMsg.msg
    +"</li>";

    $("#printer").append(final);
  });
