function pagecx(totalCount,pageSize){
	var maxnum = 0;
	if(totalCount/pageSize==parseInt(totalCount/pageSize))  maxnum=totalCount/pageSize; 
	else maxnum=parseInt(totalCount/pageSize)+1;
	var tpagenum=$('#pagenum').val();
	var regEx = /[^0-9]+/gi ;
	if(regEx.test(tpagenum)){
		tpagenum=0;
	}
	if(tpagenum>maxnum){
		tpagenum=maxnum;
	}
	ajaxQueryPage(tpagenum);
}

function isMunicipalities(name){
	if(name=='北京市'||name=='上海市'||name=='天津市'||name=='重庆市'||name=='北京外市'){
		return true;
	}else{
		return false;
	}
}

function isMunicipalitiesID(id){
		if(id=='11'||id=='31'||id=='12'||id=='50'){
			return true;
		}else{
			return false;
		}
}

function isTrueDistID(id){
		if(id=='450700'||id=='450700'){
			return true;
		}else{
			return false;
		}
}

function getRulUsProvinceOfficeTF(rulLevel,usoffice,DIST_CD){
		if(rulLevel=='0' || rulLevel=='2'){
			return true;
		}
		if(isMunicipalitiesID(usoffice.substr(0,2))){
			if((rulLevel=='5' || rulLevel=='10' || rulLevel=='15' || rulLevel=='20' || rulLevel=='25') && usoffice.substr(0,2) == DIST_CD.substr(0,2)){
				return true;
			}
		}else{
			if(rulLevel=='5' && usoffice.substr(0,2) == DIST_CD.substr(0,2)){
				return true;
			}else if((rulLevel=='10' || rulLevel=='15' || rulLevel=='20' || rulLevel=='25') && usoffice.substr(0,4) == DIST_CD.substr(0,4)){
				return true;
			}
		}
		return false;
	}

function getUsWebRule(rulLevel){
		var RulUsWebRule = true;
		if(rulLevel=='0' || rulLevel=='2' || rulLevel=='5' || rulLevel=='10' || rulLevel=='15'){
			RulUsWebRule = true;
		}else if(rulLevel=='20' || rulLevel=='25'){
			RulUsWebRule = false;
		}
		return RulUsWebRule;
	}

function getUsWebAudit(DATA_FLAG){
		var RulUsWebAudit = true;
		if(DATA_FLAG=='6' || DATA_FLAG=='7' || DATA_FLAG=='8'){
			RulUsWebAudit = true;
		}else{
			RulUsWebAudit = false;
		}
		return RulUsWebAudit;
	}
	
function getUsWebPD(DATA_FLAG){
		var RulUsWebPD= true;
		if(DATA_FLAG=='6' || DATA_FLAG=='7' || DATA_FLAG=='8' || DATA_FLAG=='9'){
			RulUsWebPD = true;
		}else{
			RulUsWebPD = false;
		}
		return RulUsWebPD;
	}

function getPY(name){
	var tCode;
	var ABBR='';
	for(i=0;i<name.length;i++){
		tCode=getCharPos(name.substr(i,1));
		ABBR = ABBR + tCode;
	}	
	return ABBR;
}
function getCharPos(str){
	var b = ['吖','八','擦','咑','妸','发','旮','哈','','丌','咔','垃','妈','哪','噢','帊','亓','冄','仨','他','','','屲','夕','呀','咋'];
	var i, j = 0, f = false;
	for(i = 1; i < 26; i++){
		j = i-1;
		var v1 = b[j];
		var v2 = b[i];
		while(v2 == '') {
			v2 = b[i+1];
			i++;
		}
		if(v1.localeCompare(str) <= 0 && str.localeCompare(v2) < 0) {
			f = true;
			break;
		}
	}
	if(f) return String.fromCharCode(65 + j);
	else if(str.localeCompare(b[25]) > 0) return 'Z';
	else return str.toUpperCase();
}
