function punchlineFill(idx){
  var value;
  switch(idx){
    case 1:
      value = $("#pro-name").val() + ", the new platform designed for...";
    break;
    case 2:
      value= "With " + $("#pro-name").val() + ", ...";
    break;
    case 3:
      value = "Template 3";
    break;
    default:
      value = "";
    break;
  }
  $("#pro-punchline").val(value);
}

$(document).ready(function() {
  $('#pro-name').bind('input', function() {
    var value = $(this).val();
    if(value.length < 2 || value.length > 20){
      $("#pro-named").attr("class", "has-error form-group ");
    } else {
      $("#pro-named").attr("class", "has-success form-group ");
    }
  });
});
