package com.cpst.core.utils;

import java.util.StringTokenizer;

public class StringBeanUtils {
	/**
	 * 去除所有的空格
	 */
	public static String removeSpaces(String s) {
		StringTokenizer st = new StringTokenizer(s, " ", false);
		String t = "";
		while (st.hasMoreElements()) {
			t += st.nextElement();
		}
		return t;
	}
	public static String[] StringToSingleString(String s) {
		s=removeSpaces(s);
		String single[]=new String[s.length()];;
		for(int i=0;i<s.length();i++){
			single[i]=s.substring(0+i, 1+i);
		}
		return single;
	}
	
	/**
	 * 
	 * @param rStr 源字符串
	 * @param rFix 要查找的字符串
	 * @param rRep 替换成的字符串
	 */
	public static String StrReplace(String rStr,String rFix,String rRep){
		if(rStr==null || rStr.equals("")){
			return rStr;
		}
		int l=0;
		String getRstr=rStr;
		do{
			l=rStr.indexOf(rFix, l);
			if (l==-1)break;
			getRstr=rStr.substring(0, l)+rRep+rStr.substring(l+1);
			l+=rRep.length();
			rStr=getRstr;
		}while(true);
		return getRstr.substring(0, getRstr.length());
	}

}
