(function($) {
	$.addtrlast=function(){
	     //$(tableid:tr:last);
	     alert(111);
	 };
})(jQuery)



/*在表格最后增加ID为newTrId的一行*/
function addLastTr(tableId,newTrId){
	var tr=$("#"+tableId+" tr:last");
	if(newTrId==null || newTrId==''){
		tr.after("<tr></tr>");
	}else{
		tr.after("<tr id='"+newTrId+"'></tr>");
	}	
}

/*在表格最前增加ID为newTrId的一行*/
function addFirstTr(tableId,newTrId){
	var tr=$("#"+tableId+" tr:first");
	if(newTrId==null || newTrId==''){
		tr.before("<tr></tr>");
	}else{
		tr.before("<tr id='"+newTrId+"'></tr>");
	}	
}

/*在表格的指定行数前增加ID为newTrId的一行,第一行n为1*/
function addBeforeTr(tableId,n,newTrId){
	var tr=$("#"+tableId+" tr:nth-child("+n+")");
	if(newTrId==null || newTrId==''){
		tr.before("<tr></tr>");
	}else{
		tr.before("<tr id='"+newTrId+"'></tr>");
	}	
}

/*在tr中增加ID为newTdId的一列*/
function addLastTd(trId,newTdId){
	var td=$("#"+trId+" td:last");
	if(newTdId==null || newTdId==''){
		td.after("<td></td>");
	}else{
		td.after("<td id='"+newTdId+"'></td>");
	}	
}

/*在TR最前增加ID为newTdId的一列*/
function addFirstTd(trId,newTdId){
	var td=$("#"+trId+" td:first");
	if(newTdId==null || newTdId==''){
		td.before("<td></td>");
	}else{
		td.before("<td id='"+newTdId+"'></td>");
	}	
}