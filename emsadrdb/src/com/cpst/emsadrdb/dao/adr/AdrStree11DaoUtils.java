package com.cpst.emsadrdb.dao.adr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cpst.emsadrdb.entity.adr.AdrStreeBean;

public class AdrStree11DaoUtils {
	
	@SuppressWarnings("unchecked")
	public static List<AdrStreeBean> fillAdrStreeBean(List adrStrees){
		Iterator it = adrStrees.iterator();
		List<AdrStreeBean> beans=new ArrayList<AdrStreeBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			AdrStreeBean bean = new AdrStreeBean();
			bean.setStrtId(((BigDecimal) obj[0]).longValue());
			bean.setDistCd(obj[1].toString());
			bean.setStrt1id(((BigDecimal) obj[2]).longValue());
			bean.setStrt1Name(obj[3].toString());
			if (obj[4] != null) {
				bean.setStrt2id(((BigDecimal) obj[4]).longValue());
			} else {
				bean.setStrt2id(null);
			}
			if (obj[5] != null) {
				bean.setStrt2Name(obj[5].toString());
			} else {
				bean.setStrt2Name("");
			}
			if (obj[6] != null) {
				bean.setStrt3id(((BigDecimal) obj[6]).longValue());
			} else {
				bean.setStrt3id(null);
			}
			if (obj[7] != null) {
				bean.setStrt3Name(obj[7].toString());
			} else {
				bean.setStrt3Name("");
			}
			if (obj[8] != null) {
				bean.setStrt4id(((BigDecimal) obj[8]).longValue());
			} else {
				bean.setStrt4id(null);
			}
			if (obj[9] != null) {
				bean.setStrt4Name(obj[9].toString());
			} else {
				bean.setStrt4Name("");
			}
			if (obj[10] != null) {
				bean.setStrt5id(((BigDecimal) obj[10]).longValue());
			} else {
				bean.setStrt5id(null);
			}
			if (obj[11] != null) {
				bean.setStrt5Name(obj[11].toString());
			} else {
				bean.setStrt5Name("");
			}
			if (obj[12] != null) {
				bean.setStrtAbbrName(obj[12].toString());
			} else {
				bean.setStrtAbbrName("");
			}
			if (obj[13] != null) {
				bean.setStrtAbbr(obj[13].toString());
			} else {
				bean.setStrtAbbr("");
			}
			if (obj[14] != null) {
				bean.setMinBgnNbr(((BigDecimal) obj[14]).longValue());
			} else {
				bean.setMinBgnNbr(null);
			}
			if (obj[15] != null) {
				bean.setStatCd(obj[15].toString());
			} else {
				bean.setStatCd("");
			}
			if (obj[16] != null) {
				bean.setSegNum(((BigDecimal) obj[14]).shortValue());
			} else {
				bean.setSegNum(null);
			}
			if (obj[17] != null) {
				bean.setUpdDate((Date)obj[17]);
			} else {
				bean.setUpdDate(null);
			}
			if (obj[18] != null) {
				bean.setEmpNbr(obj[18].toString());
			} else {
				bean.setEmpNbr("");
			}
			if (obj[19] != null) {
				bean.setStatFlag(obj[19].toString());
			} else {
				bean.setStatFlag("");
			}
			if (obj[20] != null) {
				bean.setDataDate((Date)obj[20]);
			} else {
				bean.setDataDate(null);
			}
			if (obj[21] != null) {
				bean.setDataFlag(obj[21].toString());
			} else {
				bean.setDataFlag("");
			}
			beans.add(bean);
		}
		return beans;
		
	}

}
