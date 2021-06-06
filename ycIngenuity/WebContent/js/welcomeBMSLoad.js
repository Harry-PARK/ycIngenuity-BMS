/**
 * 
 */

let remoteLightList = [];
const _email = "airroket@naver.com";
const _pw = "asdf";
const _onImg = "resources/image/on.jpg";
const _offImg = "resources/image/off.jpg";
const _host = "/ycibms-2021";

const registerSwitchEvent = function() {
	var nodeList = document.querySelectorAll('input[type="checkbox"]');
	for (var node of nodeList) {
		node.addEventListener('change', (event) => {
			var now = event.target.checked;
			var _command = now == true ? "lighton" : "lightoff";
			var device_id = event.target.parentNode.parentNode.parentNode.getAttribute("id");
			console.log(device_id);
			$.ajax({
				type: 'PUT',
				//TODO : get id from html tag
				url: _host+"/restful/remotelight/"+device_id,
				data: {
					email: _email,
					pw: _pw,
					command: _command
				},
			}).done(data => {
				var result = jsonParse(data)[0];
				if (result.success == "true") {
					if (_command == "lighton") {
						console.log(event.target.parentNode.parentNode.childNodes[3].textContent);
						console.log('Checked');
						console.log(event.target.parentNode.parentNode.previousSibling.previousSibling);
						event.target.parentNode.parentNode.previousSibling.previousSibling.src = _onImg;
					}
					else {
						console.log('Not checked');
						event.target.parentNode.parentNode.previousSibling.previousSibling.src = _offImg;
					}
				}
				else {
					event.target.checked = !now;
					console.log("failed : deviced is not responding");
				}
			}).fail(
				()=>{
					event.target.checked = !now;
					console.log("failed : invalid command");
				}
			);

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
	
	var resultTime = "last updated : ";
	if(hour >= 24){
		resultTime += day+" days ago..";
	}
	else if (min >= 60){
		resultTime += hour+" hours ago..";
	}
	else if (sec >= 60){
		resultTime += min+" mins ago..";
	}
	else{
		resultTime += sec+" sec ago..";
	}

	return resultTime;
	
}


const rltDisplayHTML = function(remotelight) {
	console.log(remotelight);
	var rlImg = _offImg;
	if (remotelight.light == "true") {
		rlImg = _onImg;
	}
	var onlineColor = "DarkRed";
	if (remotelight.online == "true") {
		onlineColor = "MediumSeaGreen";
	}
	var check = "";
	if(remotelight.light == "true"){
		check = "checked";
	}
	html = "";
	html += "<li>";
	html += "	<div class='switchBox' id='"+remotelight.device_id+"'> <!-- switch box -->";
	
	html += "		<img class='switchImg' src='"+rlImg+"' alt='bulb image'>		";
	html += "		<div class='switchInfo'>";
	html += "		<p>#" + remotelight.floor + "</p>";
	html += "		<p>" + remotelight.room_name + " (" + remotelight.room_code + ")";
	
	html += "			<span class='dot' style='background-color:" + onlineColor + ";'></span></p>";
	html += "		<p>[" + remotelight.building + "]</p>";
	html += "		<p class='lastup'>" + displayLastUpdated(remotelight.last_updated) + "</p>";
	
	html += '		<label class="switch"><input class="toSwitch" type="checkbox" '+check+'><span class="slider round"></span></label>	';
	html += "		</div>";
	html += "	</div>";
	html += "	</li>";
	document.getElementById("switchList").innerHTML += html;
}


const updateSwitchHTML = function(remotelight){
	var id = remotelight.device_id;
	
	remotelight.light == "true" ? $("#"+id+" .switchImg").attr("src", _onImg) : $("#"+id+" .switchImg").attr("src", _offImg);
	remotelight.online  == "true" ? $("#"+id+" .dot").css("background-color", "MediumSeaGreen") : $("#"+id+" .dot").css("background-color", "DarkRed");
	$("#"+id+" .lastup").text(displayLastUpdated(remotelight.last_updated));
	remotelight.light == "true" ? $("#"+id+" .toSwitch").prop("checked", true) : $("#"+id+" .toSwitch").prop("checked", false);

}

 
const updateSwitchList = function() {
	$.ajax({
		type: 'GET',
		url: _host+"/restful/remotelight"
	}).done(function(data) {
		remoteLightList = jsonParse(data, "remotelight");
		
	});
}


//import remotelight switch panel
$.ajax({
	type: 'GET',
	url: _host+"/restful/remotelight"
}).done(function(data) {
	remoteLightList = jsonParse(data, "remotelight");
	for (var rl of remoteLightList) {
		rltDisplayHTML(rl);
	}
	registerSwitchEvent();
});


//To test updateSwitchHTML => looks good!
setInterval(function(){
		updateSwitchList();
		for(var rl of remoteLightList){
			updateSwitchHTML(rl);	
		}
	}, 1000);





