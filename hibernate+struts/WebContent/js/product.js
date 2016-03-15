var request;
function requestDoGet(url, id, action){

	request =  new XMLHttpRequest();
	request.onreadystatechange = demoCallBack; 
	var requestURL = url+"?" +"action="+action+"&id="+id+"&dummy="+new Date().getTime();
	request.open("GET",requestURL,true);
	console.log("jo"+requestURL);
	request.send(null); 
}
function requestDoPost(url, id, action){

	request =  new XMLHttpRequest();
	request.onreadystatechange = demoCallBack;
	var data= "action="+action+"&id="+id;
	request.open("POST",url,true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	console.log(url);
	console.log(data);
	request.send(data);
}
function processText(data){
	console.log(data);
	var index = data.indexOf(":");
	var show = data;

	if (index!=-1){
		show = data.substring(0,index);
		var array = data.substring(index+1).split(",");
		document.forms[0].id.value=array[0];
		document.forms[0].name.value=array[1];
		document.forms[0].price.value=array[2];
		document.forms[0].make.value=array[3];
		document.forms[0].expire.value=array[4];
	}else{
		document.forms[0].name.value="";
		document.forms[0].price.value="";
		document.forms[0].make.value="";
		document.forms[0].expire.value="";
	}
	
	
	var node = document.createTextNode(show);
	var oldNode =document.getElementsByClassName("error")[0];
	if (oldNode.hasChildNodes()){
		oldNode.removeChild(oldNode.childNodes[0]);
	}
	oldNode.appendChild(node);
}

function processXml(data){
	
	var itemExists = data.getElementsByTagName("result")[0];	
	var hasMoreData = itemExists.getElementsByTagName("hasMoreData")[0].firstChild.nodeValue;
	var show=document.createTextNode(itemExists.getElementsByTagName("text")[0].firstChild.nodeValue);
	console.log("hello333");
	if (hasMoreData =="true"){
		document.forms[0].id.value=itemExists.getElementsByTagName("id")[0].firstChild.nodeValue;
		document.forms[0].name.value = itemExists.getElementsByTagName("name")[0].firstChild.nodeValue;
		document.forms[0].price.value = itemExists.getElementsByTagName("price")[0].firstChild.nodeValue;
		document.forms[0].make.value = itemExists.getElementsByTagName("make")[0].firstChild.nodeValue;
		document.forms[0].expire.value = itemExists.getElementsByTagName("expire")[0].firstChild.nodeValue;
	}
	var oldNode = document.getElementsByClassName("error")[0];
	if(oldNode.hasChildNodes()){
		oldNode.removeChild(oldNode.childNodes[0]);
	}
	oldNode.appendChild(show);
	console.log("hello");

}

function processJson(data){
	console.log(data);
	var jsonData = JSON.parse(data);
	var show = jsonData[0].text;
	var node = document.createTextNode(show);
	var oldNode = document.getElementsByClassName("error")[0];
	if (oldNode.hasChildNodes()){
		oldNode.removeChild(oldNode.childNodes[0]);
	}
	oldNode.appendChild(node);
	if(jsonData[0].hasMoreData){
		document.forms[0].id.value=jsonData[1].id;
		document.forms[0].name.value=jsonData[1].name;
		document.forms[0].price.value=jsonData[1].price;
		document.forms[0].make.value=jsonData[1].make;
		document.forms[0].expire.value=jsonData[1].expire;
	}
}
function demoCallBack(){
	
	if(request.readyState==4){
		if(request.status==200){
			var text = request.responseText;
			var dom =request.responseXML;
			processText(request.responseText);
			//console.log(request.responseText);
			//processJson(request.responseText);
//			processXml(request.responseXML);
			
			document.getElementById("img1").style.display="none";
		}else{
			
			console.log("Error! Status Code = "+request.status +"jiji" + request.readyState);
			
		}
	}
}