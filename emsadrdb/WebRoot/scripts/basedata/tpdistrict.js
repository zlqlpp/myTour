//判断是否是直辖市
function isMunicipalities(name){
	if(name=='北京市'||name=='上海市'||name=='天津市'||name=='重庆市'){
		return true;
	}else{
		return false;
	}
}
function viewcity(provincename,provinceid,url){
    var params = {
                  provinceName: provincename
                 };
    $("#td_privince_content a").removeClass(); 
    $('#'+provinceid).addClass("curr");
    $('#hiddenprovinceid').val($('#'+provinceid).text());
    jQuery.post(url, params, callback_viewcity, 'json');
}
function callback_viewcity(data){
	var str="";
	var href_function_name="javascript:view_disricts";
	var d=data.orgDistricts;
	for(var i=0;i<d.length; i++ ){
		str+="<a id="+d[i].districtCode+" href="+href_function_name+"('"+d[i].countyName+"','"+d[i].districtCode+"')>"+d[i].countyName+"</a>&nbsp;&nbsp;";	
	}
	$('#td_city_content').addClass("js_left_txt");
	$('#td_city_content').append(str);
}