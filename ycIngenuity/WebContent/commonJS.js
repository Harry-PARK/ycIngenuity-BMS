/**
 * 
 */
const RemoteLight = function(){};

const jsonParse = function(data){
	var jsonList = [];
	for(var i = 0; i < data.length; i++ ){
		var start;
		var end;
		if(data[i] == "{"){
			start = ++i;
			while(data[i] != "}"){
				i++
			}
			end = i++;
		}
		var temp = new RemoteLight();
		var item = data.substring(start, end);
		tokens = item.split(",");
		for(var token in tokens){
			token = tokens[token];
			var token = token.split(":");
			temp[token[0]] = token[1];
		}
		jsonList.push(temp);
	}
	return jsonList;
};
