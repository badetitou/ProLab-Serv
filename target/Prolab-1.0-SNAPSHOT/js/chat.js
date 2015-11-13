/*
  Init the general chat window
*/
function init(){
  $("#time").text(new Date().toUTCString()); // Print the connection time

  // Send message when return key is pressed
  document.getElementById('msg').onkeypress = function(e){
    if (!e) e = window.event;
    var keyCode = e.keyCode || e.which;
    if (keyCode == '13'){
      send();
      return false;
    }
  }
}

/*
  Scroll the chat to the last message (bottom)
 */
function scrolldown(){
  var objDiv = document.getElementById("chatroom");
  objDiv.scrollTop = objDiv.scrollHeight;
}

/*
  Send a message to the prolab firebase
*/
function send(){
  var msgstr = $("#msg").val();
  var authorstr = getCookie("log");
  var ID = getCookie("idProject");
  var datestr = new Date().toUTCString();

  $("#msg").val(""); // empty the input

  // Get the Firebase server
  var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/"+ID);
  // Add a message to it
  chat1.push(
    {
      author: authorstr,
      msg: msgstr,
      date: datestr
    });
  }

  /* General stuff */
  init();

  var ID = getCookie("idProject");
  // Get the Firebase server
  var chat1 = new Firebase("https://prolab.firebaseio.com/Messages/"+ID);
  // When a new message is received on the Firebase server...
  chat1.on("child_added", function(snapshot){
    var newMsg = snapshot.val(); // Get the new item
    var msg = newMsg.msg;
    // Smileys are fun
    msg = msg.replace(":)", "<i class='fa fa-smile-o smiley'></i>");
    msg = msg.replace(":(", "<i class='fa fa-frown-o smiley'></i>");

    // Format the data
    var final = "<li class='msg list-group-item'>"
    + "<span class='badge'>" + newMsg.date + "</span>"
    + "<b>" + newMsg.author + " : </b> " + msg
    +"</li>";

    // Print the data
    $("#printer").append(final);
    // Linkify everything
    $('#printer').linkify();
    // Scroll down
    scrolldown();
  });
