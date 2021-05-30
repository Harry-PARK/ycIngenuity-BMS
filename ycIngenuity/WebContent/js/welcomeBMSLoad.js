/**
 * 
 */

let remoteLightList = [];
const _email = "airroket@naver.com";
const _pw = "asdf";

const registerSwitchEvent = function() {
	var nodeList = document.querySelectorAll('input[type="checkbox"]');
	for (var node of nodeList) {
		node.addEventListener('change', (event) => {
			var now = event.target.checked;
			var _command = now == true ? "lighton" : "lightoff";
			$.ajax({
				type: 'PUT',
				//TODO : get id from html tag
				url: "/ycIngenuityBMS/restful/remotelight/azuretest",
				data: {
					email: _email,
					pw: _pw,
					command: _command
				},
			}).done(function(data) {
				var result = jsonParse(data)[0];
				if (result.success == "true") {
					if (_command == "lighton") {
						console.log(event.target.parentNode.parentNode.childNodes[3].textContent);
						console.log('Checked');
						console.log(event.target.parentNode.parentNode.previousSibling.previousSibling);
						event.target.parentNode.parentNode.previousSibling.previousSibling.src = "resources/image/on.jpg";
					}
					else {
						console.log('Not checked');
						event.target.parentNode.parentNode.previousSibling.previousSibling.src = "resources/image/off.jpg";
					}
				}
				else {
					event.target.checked = !now;
					console.log("failed : deviced is not responding");
				}
			});
			
		});
	}
}




const displayLastUpdated = function(time) {
	//time is string
	var now = new Date();
	var lu = new Date();
	lu.setTime(time);
	var diff = now.getTime() - lu.getTime();
	var sec = Math.floor(diff / 1000);
	var min = Math.floor(sec / 60);
	var hour = Math.floor(min / 60);
	var day = Math.floor(hour / 24);
	return day > 1 ? "" + day + " days ago.." : hour > 60 ? "" +   ur +   " hours ago.." : sec > 60  ? "" + min + " mins ago.." : "" + sec + " seconds ago..";
}


let rltDisplayHTML = function(data) {
	console.log(data);
	html = "";
	html += "<li>";
	html += "	<div class='switchBox'> <!-- switch box -->";
	if (data.light == "true") {
		html += "		<img class='switchImg' src='resources/image/on.jpg' alt='bulb image'>		";
	}
	else {
		html += "		<img class='switchImg' src='resources/image/off.jpg' alt='bulb image'>		";
	}
	html += "		<div class='switchInfo'>";
	html += "		<p>#" + data.floor + "</p>";
	html += "		<p>" + data.room_name + " (" + data.room_code + ")";
	var onlineColor = "DarkRed";
	if (data.online == "true") {
		onlineColor = "MediumSeaGreen";
	}
	html += "			<span class='dot' style='background-color:" + onlineColor + ";'></span></p>";
	html += "		<p>[" + data.building + "]</p>";
	html += "		<p class='lastup'>last updated : " + displayLastUpdated(data.last_updated) + "</p>";
	html += '		<label class="switch"><input type="checkbox"><span class="slider round"></span></label>	';
	html += "		</div>";
	html += "	</div>";
	html += "	</li>";
	document.getElementById("switchList").innerHTML += html;
}


//import remotelight switch panel
$.ajax({
	type: 'GET',
	url: "/ycIngenuityBMS/restful/remotelight"
}).done(function(data) {
	remoteLightList = jsonParse(data, "remotelight");
	console.log(data);
	for (var rl of remoteLightList) {
		rltDisplayHTML(rl);
	}
	registerSwitchEvent();
});




