var _chart = null;
let _log_all = [];
let _log_recent = [];

let summary_usedHours_week = [0, 0, 0, 0, 0, 0, 0];
let chart_data = [];

var usedHour_weeklyGraph = function(log_list) {
	var now = new Date();
	var week = [];

	//filter light control logs in 7 dyas
	for (var i in log_list) {
		var log = log_list[i];
		var dataDate = new Date(Number(log.event_date));
		var diff = now.getTime() - dataDate.getTime();
		var daydiff = Math.floor(diff / (1000 * 60 * 60 * 24));
		if (daydiff > 7) break;
		if (
			(log.method_name == "lighton" || log.method_name == "lightoff")
			&& log.state == "200"
		) {
			week.push(log);
		}
	}

	//search lighton
	for (var i = week.length - 1; i >= 0; i--) {
		var target_log = week[i];
		if (target_log.method_name == "lighton") {

			//search lightoff
			for (var comp_i = i; comp_i >= 0; comp_i--) {
				var compare_log = week[comp_i];
				if (target_log.device_id == compare_log.device_id
					&& compare_log.method_name == "lightoff") {

					//check day change
					var target_log_date = new Date(Number(target_log.event_date));
					var compare_log_date = new Date(Number(compare_log.event_date));

					if (target_log_date.getDate() == compare_log_date.getDate()) {

						var usedHours = compare_log_date.getHours() - target_log_date.getHours();
						var diff = now.getTime() - target_log_date.getTime();
						var daydiff = Math.floor(diff / (1000 * 60 * 60 * 24));
						summary_usedHours_week[6 - daydiff] += usedHours;
					}
					else {
						on_day_used = 24 - target_log_date.getHours();
						off_day_used = compare_log_date.getHours();

						var diff = now.getTime() - target_log_date.getTime();
						var daydiff = Math.floor(diff / (1000 * 60 * 60 * 24));
						summary_usedHours_week[6 - daydiff] += on_day_used;

						var diff = now.getTime() - compare_log_date.getTime();
						var daydiff = Math.floor(diff / (1000 * 60 * 60 * 24));
						summary_usedHours_week[6 - daydiff] += off_day_used;
					}
					break;
				}
			}
		}
	}
	var init_chart_data = function() {
		for (var i = summary_usedHours_week.length - 1; i >= 0; i--) {
			var now = new Date();
			now.setDate(now.getDate() - i);
			var data = {
				x: now.getDate(),
				y: summary_usedHours_week[6 - i]
			}
			chart_data.push(data);
		}
	}
	init_chart_data();

	chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Used Hours"
		},
		axisY: {
			title: "Hour",
			titleFontSize: 24
		},
		data: [{
			type: "column",
			yValueFormatString: "# Hours",
			dataPoints: chart_data
		}]
	});

	chart.render();

} //usedHour_weeklyGraph end


var logDisplayHTML = function(log_list) {
		var html = "";
		html += "<tr>";
		html += "<th style='width: 200px;'>Date</th>";
		html += "<th style='width: 300px;'>Method Name</th>";
		html += "<th style='width: 600px;'>Message</th>";
		html += "<th style='width: 100px;'>State</th>";
		html += "<th style='width: 100px;'>Devide ID</th>";
		html += "</tr>";
		document.getElementById("logTable").innerHTML = html;
	for (var i = 0; i < log_list.length; i++) {
		var log = log_list[i];
		var html = "";
		html += "<tr>";
		html += "<td style='width: 200px;'>" + (new Date(Number(log.event_date))).toLocaleString() + "</th>";
		html += "<td style='width: 300px;'>" + log.method_name + "</th>";
		html += "<td style='width: 600px;'>" + log.message + "</th>";
		html += "<td style='width: 100px;'>" + log.state + "</th>";
		html += "<td style='width: 100px;'>" + log.device_id + "</th>";
		html += "</tr>";
		document.getElementById("logTable").innerHTML += html;
	}
}


$.ajax({
	type: 'GET',
	url: _host + "/restful/log"
}).done(function(data) {
	_log_all = jsonParse(data, "log");
	usedHour_weeklyGraph(_log_all.slice());
});

$.ajax({
	type: 'GET',
	url: _host + "/restful/log/15"
}).done(function(data) {
	_log_recent = jsonParse(data, "log");
	logDisplayHTML(_log_recent);
});

setInterval(function() {
	$.ajax({
		type: 'GET',
		url: _host + "/restful/log/15"
	}).done(function(data) {
		_log_recent = jsonParse(data, "log");
		logDisplayHTML(_log_recent);
	});
}, 1000);


