// ==UserScript==
// @name       forweiquan
// @namespace  http://use.i.E.your.homepage/
// @version    0.1
// @description  enter something useful
// @match      http://item.taobao.com/*
// @copyright  2012+, You
// ==/UserScript==



function sel(){
    var wwid=document.getElementsByClassName('hCard fn')[0].title;
	console.log('旺旺ID 1:'+wwid);

    var createstortime = document.getElementsByClassName('setuptime')[0].innerHTML;
		createstortime=createstortime.substr(18,10);
	console.log('创店时间 2:'+createstortime);

	var storurl = document.location.href;
	console.log('店铺url 3:'+storurl);

	var addr = document.getElementsByClassName('tb-location')[0].childNodes[0].innerHTML;
	console.log('地址 4：'+addr);
    
	console.log('商家级别 5：没有做');
    
 	var name = document.getElementsByClassName('tb-detail-hd')[0].childNodes[1].innerHTML;
	console.log('名称 6：'+name); 
    
    var imgsrc = document.getElementById('J_ImgBooth').src;
    console.log('图片地址 7：'+imgsrc);
    
	var sellsize = document.getElementsByClassName('J_TDealCount')[0].innerHTML;
    console.log('成交数量 8：'+sellsize);
}

	var price = document.getElementsByClassName('tb-rmb-num')[0].innerHTML;
	console.log('价位 9：'+price);

	setTimeout(sel,2000);


function sub(){

    
    document.getElementById('one').value=document.getElementsByClassName('hCard fn')[0].title ;
    var createstortime = document.getElementsByClassName('setuptime')[0].innerHTML;
	createstortime=createstortime.substr(18,10);
    document.getElementById('two').value= createstortime;
    document.getElementById('three').value= document.location.href;
    document.getElementById('four').value=document.getElementsByClassName('tb-location')[0].childNodes[0].innerHTML; 
    
    if(document.getElementsByClassName('rank')[0]!=null){
    	document.getElementById('five').value= document.getElementsByClassName('rank')[0].src;
    }else{
    	document.getElementById('five').value= 'q1q1q1q1q1';
    }
    
   
    document.getElementById('six').value= document.getElementsByClassName('tb-detail-hd')[0].childNodes[1].innerHTML;
    document.getElementById('seven').value=document.getElementById('J_ImgBooth').src; 
    document.getElementById('eight').value= document.getElementsByClassName('J_TDealCount')[0].innerHTML;
    document.getElementById('nine').value= document.getElementsByClassName('tb-rmb-num')[0].innerHTML;
    document.wq.submit();

}




	var nu = document.getElementById('header');
	var newInput = document.createElement("div");   

var html=[];
html.push('<form action="http://127.0.0.1:8080/TestHjpaSpringMVC/wq/add.do" method="post" name="wq" id="myform"> ');
html.push('<input type="hidden" value="" name="onee" id="one"/>');
html.push('<input type="hidden" value="" name="twoo" id="two"/>');
html.push('<input type="hidden" value="" name="threee" id="three"/>');
html.push('<input type="hidden" value="" name="fourr" id="four"/>');
html.push('<input type="hidden" value="" name="fivee" id="five"/>');
html.push('<input type="hidden" value="" name="sixx" id="six"/>');
html.push('<input type="hidden" value="" name="sevenn" id="seven"/>');
html.push('<input type="hidden" value="" name="eightt" id="eight"/>');
html.push('<input type="hidden" value="" name="ninee" id="nine"/>');
html.push('</form>');




newInput.innerHTML=html.join("");
nu.appendChild(newInput);

//---------------------------------

var site = document.getElementById('J_juValid');
console.log('site:'+site);
var button = document.createElement("div");   

var tj=[];

tj.push('<input type="button" value="提交" id="jb"/>');


button.innerHTML=tj.join("");
console.log('button:'+button.innerHTML);
site.appendChild(button);


var useIt=document.getElementById('jb');
useIt.addEventListener('click', sub, true);


    
    
    
    



