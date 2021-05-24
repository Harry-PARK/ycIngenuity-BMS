/**
 * 
 */



let remoteLightList = [];
let rltDisplayHTML = function(data){
	html = "<div>"
	
	html += "</div>"
}



$.ajax({
		type: 'GET',
		url:"/ycIngenuityBMS/restful/remotelight"
	}).done(function(data) {
		remoteLightList = jsonParse(data);
	  });



document.addEventListener('DOMContentLoaded', function () {
  var checkbox = document.querySelector('input[type="checkbox"]');

  checkbox.addEventListener('change', function () {
    if (checkbox.checked) {
      // do this
      console.log('Checked');
    } else {
      // do that
      console.log('Not checked');
    }
  });
});