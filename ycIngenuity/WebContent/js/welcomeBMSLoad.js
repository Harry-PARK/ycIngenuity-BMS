/**
 * 
 */



let remoteLightList = [];
let rltDisplayHTML = function(data){
	html = "";
	html += "<li>";
	html += "	<div class='switchBox'> <!-- switch box -->";
	html += "		<img class='switchImg' src='resources/image/off.jpg' alt='bulb image'>		";	
	html += "		<div class='switchInfo'>";
	html += "		<p>#2</p>";
	html += "		<p>LAB3 (I203)</p>";
	html += "		<p>[Industry]</p>";
	html += "		<p class='lastup'>last updated : 1 min ago..</p>";
	html += "		<label class='switch'><input type='checkbox'><span class='slider round'></span></label>	";
	html += "		</div>";
	html += "	</div>";
	html += "	</li>";
	document.getElementById("switchList").innerHTML+=html;
	
}

rltDisplayHTML();


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