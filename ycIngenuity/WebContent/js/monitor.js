var chart = null;
var dataPoints = [];
window.onload = function () {

	var a = {
		x: 21,
		y: 10
	}
	var b = {
		x: 22,
		y: 20
	}
	var c = {
		x: 23,
		y: 30
	}
	dataPoints.push(a);
	dataPoints.push(b);
	dataPoints.push(c);

	chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Daily Sales Data"
		},
		axisY: {
			title: "Units",
			titleFontSize: 24
		},
		data: [{
			type: "column",
			yValueFormatString: "#,### Units",
			dataPoints: dataPoints
		}]
	});



	chart.render();
}