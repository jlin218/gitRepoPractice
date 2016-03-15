<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../css/main.css" />

<title>Product</title>
<script type="text/javascript" src="../js/json2.js"></script>
<script type="text/javascript" src="../js/product.js"></script>
<script type="text/javascript">
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}	
/* 	var inputNode = document.createElement("input");
	inputNode.setAttribute("type", "submit");
	inputNode.setAttribute("value", "Click");
	var oldNode = document.getElementById("clearr");
	oldNode.appendChild(inputNode); */
}
var arry =["wahaha", "jiji","yoyoyo"];
function onMouseOver(){
	document.getElementById("prodId").style.display="none";

	
}
function onMouseOut(){
	document.getElementById("prodId2").style.color="green";
	document.getElementById("prodId").style.display="inline";
	
}
var request;
function buttonClicked(){
	document.getElementById("img1").style.display="inline";
	setTimeout(function(){
		document.getElementById("img1").style.display="none";
	},200);
/* 	request =  new XMLHttpRequest();
	request.open("GET","product.controller",false);
	request.send(); 
	
	+encodeURIComponent("xxx")*/
	var data = "id=1&name=jajajajajajj";
	request =  new XMLHttpRequest();
	request.open("GET","/beans/pages/product.controller?"+data,true);
	request.send(null); 
	/* request.open("POST","/beans/pages/product.controller",false);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send(data); */
}
function onFocus(){
	
	var nodes = document.getElementsByTagName("h3");

	var newTextNode0 = document.createTextNode(nodes[0].firstChild.nodeValue);
	var newTextNode1 = document.createTextNode(nodes[1].firstChild.nodeValue);
	nodes[0].replaceChild(newTextNode1,nodes[0].firstChild);
	nodes[1].replaceChild(newTextNode0,nodes[1].firstChild);

}
function buttonClicked1(){
	var inputNode = document.createElement("input");
	inputNode.setAttribute("type", "submit");
	inputNode.setAttribute("value", "hihi");
	inputNode.setAttribute("onmouseover", "buttonClicked1()");
	inputNode.setAttribute("class", "yo");
	var oldNode = document.getElementById("hi");
	oldNode.appendChild(inputNode);
}
function buttOnClick(){
	
	var nodes = document.getElementById("hi");
	while(nodes.hasChildNodes()){
		
		nodes.removeChild(nodes.childNodes[0]);
	}
} 
function doBlur(){
	document.getElementById("img1").style.display="inline";
	var id = document.getElementById("idid").value;
	var folder = "${pageContext.request.contextPath}";
	requestDoGet(folder+"/pages/product.view", id, "textText");
	//requestDoGet(folder+"/pages/product.view", id, "textXml");
	//requestDoGet(folder+"/pages/product.view", id, "textJson");
// 	requestDoGet(folder+"/pages/product.view", id, "textXml");


}
</script>
</head>
<body>
<h3>Welcome, </h3>

<h3 >Product Table</h3>

<form action="<%=request.getContextPath()%>/theNamespace/productAction.action">
<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" id="idid" name="id" value="${param.id}" onfocus="onFocus()" onblur="doBlur()"></td>
		<td class="error"><span>${errors.idError}</span></td>
		<td><img id="img1" src="../img/ajax-loader.gif" style="display:none"></td>
	</tr>
	<tr>
		<td>Name : </td>
		<td><input type="text" name="name" value="${param.name}"></td>
		<td><span></span></td>
	</tr>

	<tr>
		<td>Price : </td>
		<td><input type="text" name="price" value="${param.price}"></td>
		<td class="error"><span>${errors.priceError}</span></td>
	</tr>
	<tr>
		<td>Make : </td>
		<td><input type="text" name="make" value="${param.make}"></td>
		<td class="error"><span>${errors.dateError}</span></td>
	</tr>
	<tr>
		<td>Expire : </td>
		<td><input type="text" name="expire" value="${param.expire}"></td>
		<td class="error"><span>${errors.expireError}</span></td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="production" value="Insert">
			<input type="submit" name="production" value="Update">
		</td>
		<td>
			<input type="submit" name="production" value="Delete">
			<input type="submit" name="production" value="Select">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
		<td>${errors.msg}</td>
	</tr>
</table>

</form>
<input type="button" id="but" value="clickMe"  onclick="buttonClicked()"/>
	<img id="img1" src="../img/ajax-loader.gif" style="display:none">
	<input type="button" name="production" value="Delete">
	<input type="submit"  class="yo" value="hiii" onmouseover="buttonClicked1()" onclick="buttOnClick()" />
	<div id="hi"></div>
</body>
</html>