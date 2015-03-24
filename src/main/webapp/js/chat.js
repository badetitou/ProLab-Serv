function init(){
  $("#time").text(new Date().toLocaleTimeString());
}

function send(){
  var msgstr = $("#msg").val();
  var authorstr = "Zeliaselena";
  var datestr = new Date().toLocaleTimeString();

  var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/1");
  chat1.push(
    {
      author: authorstr,
      msg: msgstr,
      date: datestr
    });
  }
  function initconnection(){
    var msgstr = "Connected.";
    var authorstr = "Zeliaselena";
    var datestr = new Date().toLocaleTimeString();

    var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/1");
    chat1.push(
      {
        author: authorstr,
        msg: msgstr,
        date: datestr
      });
  }

    var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/1");
    initconnection();
    chat1.on("child_added", function(snapshot){
      var newMsg = snapshot.val();
      var final = ""
        + "<li class='msg list-group-item'>"
        + "<span class='badge'>" + newMsg.date + "</span>"
        + "<b>" + newMsg.author + "</b> " + newMsg.msg
        +"</li>";

      $("#printer").append(final);
    });
