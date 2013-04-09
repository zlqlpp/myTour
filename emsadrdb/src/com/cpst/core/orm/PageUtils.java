package com.cpst.core.orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页函数生产URL，目前采用这种方式分页，需要和Action中的一个参数配合basePageNo(参数名要相同)
 * @author PengYuLei
 *
 */
public class PageUtils {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String BPN="basePageNo";//Action中的一个参数,一定要同名
	private static final int SIZE=9;//最多显示的页数
	
	private static String getInputPara(String buttonName,String buttonValue,String formName,int pageNo){
		return "&nbsp;<input type=button name="+buttonName+" value="+buttonValue+" onclick=document.forms."+formName+"."+BPN+".value="+pageNo+";document.forms."+formName+".submit()>&nbsp;";
	}
	
	private static String getAjaxInputPara(String buttonName,String buttonValue){
		return "&nbsp;<input type=button name="+buttonName+" value="+buttonValue+" onclick=javascript:ajaxQueryPage(this.value)>&nbsp;";
	}
	
	public static <T> String getPageUrl(Page<T> page,String typeId) {
		int pageNo=page.getPageNo();//当前页号
		String formName=page.getFormName();//form名称
		String pageUrl = "<input type='hidden' name='"+BPN+"' value='"+pageNo+"'/>找到&nbsp;" + page.getTotalCount() + "&nbsp;条记录&nbsp;";
		//总页数
		Long totalCount=page.getTotalPages();
		if(totalCount==0){
			//总页数等于0
			pageUrl="找到0条相关数据";
		}else if(totalCount<=SIZE){
			//可以显示size页
			for(int i=1;i<=totalCount;i++){
				if(i!=pageNo){
					if(typeId.equals("AJAX")){
						pageUrl +=getAjaxInputPara("pageButton"+i,String.valueOf(i));
					}else{
						pageUrl +=getInputPara("pageButton"+i,String.valueOf(i),formName,i);
					}
				}else{
					pageUrl += "&nbsp;"+i+"&nbsp;";
				}
			}	
		}else if(totalCount>SIZE){
			//大于size页
			if(pageNo<=2 && pageNo>=1){
				for(int i=1;i<=3;i++){
					if(i!=pageNo){
						if(typeId.equals("AJAX")){
							pageUrl +=getAjaxInputPara("pageButton"+i,String.valueOf(i));
						}else{
							pageUrl += getInputPara("pageButton"+i,String.valueOf(i),formName,i);
						}
					}else{
						pageUrl += "&nbsp;"+i+"&nbsp;";
					}
				}
				if(typeId.equals("AJAX")){
					pageUrl+="....&nbsp;"+getAjaxInputPara("pageButton"+(totalCount-1),String.valueOf(totalCount-1));
					pageUrl+=getAjaxInputPara("pageButton"+(totalCount),String.valueOf(totalCount));
				}else{
					pageUrl+="....&nbsp;"+getInputPara("pageButton"+(totalCount-1),String.valueOf(totalCount-1),formName,pageNo);
					pageUrl+=getInputPara("pageButton"+(totalCount),String.valueOf(totalCount),formName,pageNo);
				}
			}else if(pageNo>=3 && pageNo<=5){
				for(int i=1;i<=pageNo+1;i++){
					if(i!=pageNo){
						if(typeId.equals("AJAX")){
							pageUrl +=getAjaxInputPara("pageButton"+i,String.valueOf(i));
						}else{
							pageUrl += getInputPara("pageButton"+i,String.valueOf(i),formName,i);
						}
					}else{
						pageUrl += "&nbsp;"+i+"&nbsp;";
					}
				}
				if(typeId.equals("AJAX")){
					pageUrl+="....&nbsp;"+getAjaxInputPara("pageButton"+(totalCount-1),String.valueOf(totalCount-1));
					pageUrl+=getAjaxInputPara("pageButton"+(totalCount),String.valueOf(totalCount));
				}else{
					pageUrl+="....&nbsp;"+getInputPara("pageButton"+(totalCount-1),String.valueOf(totalCount-1),formName,pageNo);
					pageUrl+=getInputPara("pageButton"+(totalCount),String.valueOf(totalCount),formName,pageNo);
				}
				
			}else if(pageNo>=6){
				if(typeId.equals("AJAX")){
					pageUrl+=getAjaxInputPara("pageButton"+(1),String.valueOf(1));
					pageUrl+=getAjaxInputPara("pageButton"+(2),String.valueOf(2));
				}else{
					pageUrl+=getInputPara("pageButton"+(1),String.valueOf(1),formName,pageNo);
					pageUrl+=getInputPara("pageButton"+(2),String.valueOf(2),formName,pageNo);
				}
				if(pageNo>=totalCount-1 && pageNo<=totalCount){
					for(int i=totalCount.intValue()-2;i<=totalCount;i++){
						if(i!=pageNo){
							if(typeId.equals("AJAX")){
								pageUrl += getAjaxInputPara("pageButton"+i,String.valueOf(i));
							}else{
								pageUrl += getInputPara("pageButton"+i,String.valueOf(i),formName,i);
							}
						}else{
							pageUrl += "&nbsp;"+i+"&nbsp;";
						}
					}
				}
				else if(pageNo>=totalCount-4 && pageNo<=totalCount-2){
					for(int i=pageNo-1;i<=totalCount;i++){
						if(i!=pageNo){
							if(typeId.equals("AJAX")){
								pageUrl += getAjaxInputPara("pageButton"+i,String.valueOf(i));
							}else{
								pageUrl += getInputPara("pageButton"+i,String.valueOf(i),formName,i);
							}
							
						}else{
							pageUrl += "&nbsp;"+i+"&nbsp;";
						}
					}
				}
				else{
					for(int i=pageNo-1;i<=pageNo+1;i++){
						if(i!=pageNo){
							if(typeId.equals("AJAX")){
								pageUrl += getAjaxInputPara("pageButton"+i,String.valueOf(i));
							}else{
								pageUrl += getInputPara("pageButton"+i,String.valueOf(i),formName,i);
							}
						}else{
							pageUrl += "&nbsp;"+i+"&nbsp;";
						}
					}
					if(typeId.equals("AJAX")){
						pageUrl+="....&nbsp;"+getAjaxInputPara("pageButton"+(totalCount-1),String.valueOf(totalCount-1));
						pageUrl+=getAjaxInputPara("pageButton"+(totalCount),String.valueOf(totalCount));
					}else{
						pageUrl+="....&nbsp;"+getInputPara("pageButton"+(totalCount-1),String.valueOf(totalCount-1),formName,pageNo);
						pageUrl+=getInputPara("pageButton"+(totalCount),String.valueOf(totalCount),formName,pageNo);
					}
					
				}
				
			}
		}
		return pageUrl;
	}

}
