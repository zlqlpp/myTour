package com.cpst.emsadrdb.web.clfw;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.clfw.ClfwConfigDao;
import com.cpst.emsadrdb.dao.clfw.ClfwQueryDao;
import com.cpst.emsadrdb.entity.clfw.CLFWBean;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;

@SuppressWarnings( { "serial" })
@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B09cud02clfwconfigAction extends BaseActionSupport {

	@Autowired
	private ClfwConfigDao clfwConfigDao;

	@Autowired
	private ClfwQueryDao clfwQueryDao;

	private String saveMessage;
	private CLFWBean clfwBean;

	private String OPE_REMARK;
	private String CLFWPC_SEQID;
	private String CLFWTDB_DISTCD;
	private String CLFWTDB_CLSX;
	private String DM_PK_CODE;
	private String CLFWTDB_SEQID;
	private String CLFWTDBCQ_SEQID;
	private String CLFWPC_MC;
	private String CLFWPC_SX;
	private String CLFWPC_SM;
	private String CLFWTDB_JLZX;
	private String CLFWTDB_SJYXSC;
	private String CLFWTDB_LSLZB;
	private String PG_PK_CODE;
	private String CLFWTDD_CLSX;
	private String CLFWPC_DISTCD;
	private String CLFWTDB_FW;
	private String CLFWPCSHENG_SHENGM;
	private String CLFWPCSHENG_SEQID;
	private String CLFWPCSHENG_SM;
	private String CLFWPCSHENG_DISTCD;
	private String CLFWPCSHI_SEQID;
	private String CLFWPCSHI_SM;
	private String CLFWPCSHI_SHIGM;
	private String CLFWPCSHI_DISTCD;
	private String CLFWKFFW_SEQID;
	private String CLFWKFFW_DISTCD;
	private String CLFWKFFW_YTEFW;
	private String CLFWKFFW_SWSFW;
	private String CLFWTDB_SHSX;
	
	private String CLFWYLXXB_SFJID;
	private String CLFWYLXXB_SJKCSJ;
	private String CLFWYLXXBZ_SEQID;
	private String CLFWYLXXB_LSH;
	private String CLFWYLXXB_CPH;
	private String CLFWYLXXB_SFJSM;
	private String CLFWYLXXB_ZDJID;
	private String CLFWYLXXB_SJDDSJ;
	private String CLFWYLXXB_ZDJSM;
	private String CLFWYLXXB_SEQID;
	private String CLFWYLXXB_XSBZ;
	private String CLFWYLXXB_QYRQ;
	private String CLFWRESORGPZ_SEQID;
	private String CLFWYLXXB_FLAG;
	private String CLFWYLXXB_CSRQ;
	
	private String CLFW_HBXXGL_CSRQ;
	private String CLFW_HBXXGL_HBH;
	private String CLFW_HBXXGL_ZBS;
	private String CLFW_HBXXGL_ZL;
	private String CLFW_HBXXGL_LDZBS;
	private String CLFW_HBXXGL_SSZBS;
	private String CLFW_HBXXGL_SFJID;
	private String CLFW_HBXXGL_ZDJID;
	private String CLFW_HBXXGL_SEQID;
	private String CLFW_HBXXGL_LDLSH;
	
	private String CLFWTDBNJJS_SEQID;
	private String CLFWTDBNJJS_SFNJ;
	
	private String CLFWYLXXB_SFJGH;
	private String CLFWYLXXB_ZDJGH;
	
	private String CLFW_HBXXLDBW_SEQID;
	private String CLFW_HBXXGL_XSBZ;
	private String CLFW_HBXXLDBW_AJTHZBS;
	private String CLFW_HBXXLDBW_LXZBS;
	private String CLFW_HBXXLDBW_SSZBS;
	private String CLFWYLXXB_SFJGHSM;
	private String CLFWPCSHENG_JGDM;
    private String CLFWPCSHI_JZSJ;
    private String CLFWPCSHI_JZYH;
	
	
	
	public String getCLFWPCSHENG_JGDM() {
		return CLFWPCSHENG_JGDM;
	}

	public void setCLFWPCSHENG_JGDM(String clfwpcsheng_jgdm) {
		CLFWPCSHENG_JGDM = ClfwCommon.sql_inj(clfwpcsheng_jgdm);
	}

	public String getCLFWPCSHI_JZSJ() {
		return CLFWPCSHI_JZSJ;
	}

	public void setCLFWPCSHI_JZSJ(String clfwpcshi_jzsj) {
		CLFWPCSHI_JZSJ = ClfwCommon.sql_inj(clfwpcshi_jzsj);
	}

	public String getCLFWPCSHI_JZYH() {
		return CLFWPCSHI_JZYH;
	}

	public void setCLFWPCSHI_JZYH(String clfwpcshi_jzyh) {
		CLFWPCSHI_JZYH = ClfwCommon.sql_inj(clfwpcshi_jzyh);
	}

	public String getCLFWYLXXB_SFJGHSM() {
		return CLFWYLXXB_SFJGHSM;
	}

	public void setCLFWYLXXB_SFJGHSM(String clfwylxxb_sfjghsm) {
		CLFWYLXXB_SFJGHSM = ClfwCommon.sql_inj(clfwylxxb_sfjghsm);
	}

	public String getCLFW_HBXXLDBW_SEQID() {
		return CLFW_HBXXLDBW_SEQID;
	}

	public void setCLFW_HBXXLDBW_SEQID(String clfw_hbxxldbw_seqid) {
		CLFW_HBXXLDBW_SEQID = ClfwCommon.sql_inj(clfw_hbxxldbw_seqid);
	}

	public String getCLFW_HBXXGL_XSBZ() {
		return CLFW_HBXXGL_XSBZ;
	}

	public void setCLFW_HBXXGL_XSBZ(String clfw_hbxxgl_xsbz) {
		CLFW_HBXXGL_XSBZ = ClfwCommon.sql_inj(clfw_hbxxgl_xsbz);
	}

	public String getCLFW_HBXXLDBW_AJTHZBS() {
		return CLFW_HBXXLDBW_AJTHZBS;
	}

	public void setCLFW_HBXXLDBW_AJTHZBS(String clfw_hbxxldbw_ajthzbs) {
		CLFW_HBXXLDBW_AJTHZBS = ClfwCommon.sql_inj(clfw_hbxxldbw_ajthzbs);
	}

	public String getCLFW_HBXXLDBW_LXZBS() {
		return CLFW_HBXXLDBW_LXZBS;
	}

	public void setCLFW_HBXXLDBW_LXZBS(String clfw_hbxxldbw_lxzbs) {
		CLFW_HBXXLDBW_LXZBS = ClfwCommon.sql_inj(clfw_hbxxldbw_lxzbs);
	}

	public String getCLFW_HBXXLDBW_SSZBS() {
		return CLFW_HBXXLDBW_SSZBS;
	}

	public void setCLFW_HBXXLDBW_SSZBS(String clfw_hbxxldbw_sszbs) {
		CLFW_HBXXLDBW_SSZBS = ClfwCommon.sql_inj(clfw_hbxxldbw_sszbs);
	}

	public String getCLFWYLXXB_SFJGH() {
		return CLFWYLXXB_SFJGH;
	}

	public void setCLFWYLXXB_SFJGH(String clfwylxxb_sfjgh) {
		CLFWYLXXB_SFJGH = ClfwCommon.sql_inj(clfwylxxb_sfjgh);
	}

	public String getCLFWYLXXB_ZDJGH() {
		return CLFWYLXXB_ZDJGH;
	}

	public void setCLFWYLXXB_ZDJGH(String clfwylxxb_zdjgh) {
		CLFWYLXXB_ZDJGH = ClfwCommon.sql_inj(clfwylxxb_zdjgh);
	}

	public String getCLFWTDBNJJS_SEQID() {
		return CLFWTDBNJJS_SEQID;
	}

	public void setCLFWTDBNJJS_SEQID(String clfwtdbnjjs_seqid) {
		CLFWTDBNJJS_SEQID = ClfwCommon.sql_inj(clfwtdbnjjs_seqid);
	}

	public String getCLFWTDBNJJS_SFNJ() {
		return CLFWTDBNJJS_SFNJ;
	}

	public void setCLFWTDBNJJS_SFNJ(String clfwtdbnjjs_sfnj) {
		CLFWTDBNJJS_SFNJ = ClfwCommon.sql_inj(clfwtdbnjjs_sfnj);
	}

	public String getCLFW_HBXXGL_LDLSH() {
		return CLFW_HBXXGL_LDLSH;
	}

	public void setCLFW_HBXXGL_LDLSH(String clfw_hbxxgl_ldlsh) {
		CLFW_HBXXGL_LDLSH = ClfwCommon.sql_inj(clfw_hbxxgl_ldlsh);
	}

	public String getCLFW_HBXXGL_CSRQ() {
		return CLFW_HBXXGL_CSRQ;
	}

	public void setCLFW_HBXXGL_CSRQ(String clfw_hbxxgl_csrq) {
		CLFW_HBXXGL_CSRQ = ClfwCommon.sql_inj(clfw_hbxxgl_csrq);
	}

	public String getCLFW_HBXXGL_HBH() {
		return CLFW_HBXXGL_HBH;
	}

	public void setCLFW_HBXXGL_HBH(String clfw_hbxxgl_hbh) {
		CLFW_HBXXGL_HBH = ClfwCommon.sql_inj(clfw_hbxxgl_hbh);
	}

	public String getCLFW_HBXXGL_ZBS() {
		return CLFW_HBXXGL_ZBS;
	}

	public void setCLFW_HBXXGL_ZBS(String clfw_hbxxgl_zbs) {
		CLFW_HBXXGL_ZBS = ClfwCommon.sql_inj(clfw_hbxxgl_zbs);
	}

	public String getCLFW_HBXXGL_ZL() {
		return CLFW_HBXXGL_ZL;
	}

	public void setCLFW_HBXXGL_ZL(String clfw_hbxxgl_zl) {
		CLFW_HBXXGL_ZL = ClfwCommon.sql_inj(clfw_hbxxgl_zl);
	}

	public String getCLFW_HBXXGL_LDZBS() {
		return CLFW_HBXXGL_LDZBS;
	}

	public void setCLFW_HBXXGL_LDZBS(String clfw_hbxxgl_ldzbs) {
		CLFW_HBXXGL_LDZBS = ClfwCommon.sql_inj(clfw_hbxxgl_ldzbs);
	}

	public String getCLFW_HBXXGL_SSZBS() {
		return CLFW_HBXXGL_SSZBS;
	}

	public void setCLFW_HBXXGL_SSZBS(String clfw_hbxxgl_sszbs) {
		CLFW_HBXXGL_SSZBS = ClfwCommon.sql_inj(clfw_hbxxgl_sszbs);
	}

	public String getCLFW_HBXXGL_SFJID() {
		return CLFW_HBXXGL_SFJID;
	}

	public void setCLFW_HBXXGL_SFJID(String clfw_hbxxgl_sfjid) {
		CLFW_HBXXGL_SFJID = ClfwCommon.sql_inj(clfw_hbxxgl_sfjid);
	}

	public String getCLFW_HBXXGL_ZDJID() {
		return CLFW_HBXXGL_ZDJID;
	}

	public void setCLFW_HBXXGL_ZDJID(String clfw_hbxxgl_zdjid) {
		CLFW_HBXXGL_ZDJID = ClfwCommon.sql_inj(clfw_hbxxgl_zdjid);
	}

	public String getCLFW_HBXXGL_SEQID() {
		return CLFW_HBXXGL_SEQID;
	}

	public void setCLFW_HBXXGL_SEQID(String clfw_hbxxgl_seqid) {
		CLFW_HBXXGL_SEQID = ClfwCommon.sql_inj(clfw_hbxxgl_seqid);
	}

	public String getCLFWYLXXB_CSRQ() {
		return CLFWYLXXB_CSRQ;
	}

	public void setCLFWYLXXB_CSRQ(String clfwylxxb_csrq) {
		CLFWYLXXB_CSRQ = ClfwCommon.sql_inj(clfwylxxb_csrq);
	}

	public String getCLFWYLXXB_FLAG() {
		return CLFWYLXXB_FLAG;
	}

	public void setCLFWYLXXB_FLAG(String clfwylxxb_flag) {
		CLFWYLXXB_FLAG = ClfwCommon.sql_inj(clfwylxxb_flag);
	}

	public String getCLFWRESORGPZ_SEQID() {
		return CLFWRESORGPZ_SEQID;
	}

	public void setCLFWRESORGPZ_SEQID(String clfwresorgpz_seqid) {
		CLFWRESORGPZ_SEQID = ClfwCommon.sql_inj(clfwresorgpz_seqid);
	}

	public String getCLFWYLXXB_XSBZ() {
		return CLFWYLXXB_XSBZ;
	}

	public void setCLFWYLXXB_XSBZ(String clfwylxxb_xsbz) {
		CLFWYLXXB_XSBZ = ClfwCommon.sql_inj(clfwylxxb_xsbz);
	}

	public String getCLFWYLXXB_QYRQ() {
		return CLFWYLXXB_QYRQ;
	}

	public void setCLFWYLXXB_QYRQ(String clfwylxxb_qyrq) {
		CLFWYLXXB_QYRQ = ClfwCommon.sql_inj(clfwylxxb_qyrq);
	}

	public String getCLFWYLXXB_SEQID() {
		return CLFWYLXXB_SEQID;
	}

	public void setCLFWYLXXB_SEQID(String clfwylxxb_seqid) {
		CLFWYLXXB_SEQID = ClfwCommon.sql_inj(clfwylxxb_seqid);
	}

	public String getCLFWYLXXB_SFJID() {
		return CLFWYLXXB_SFJID;
	}

	public void setCLFWYLXXB_SFJID(String clfwylxxb_sfjid) {
		CLFWYLXXB_SFJID = ClfwCommon.sql_inj(clfwylxxb_sfjid);
	}

	public String getCLFWYLXXB_SJKCSJ() {
		return CLFWYLXXB_SJKCSJ;
	}

	public void setCLFWYLXXB_SJKCSJ(String clfwylxxb_sjkcsj) {
		CLFWYLXXB_SJKCSJ = ClfwCommon.sql_inj(clfwylxxb_sjkcsj);
	}

	public String getCLFWYLXXBZ_SEQID() {
		return CLFWYLXXBZ_SEQID;
	}

	public void setCLFWYLXXBZ_SEQID(String clfwylxxbz_seqid) {
		CLFWYLXXBZ_SEQID = ClfwCommon.sql_inj(clfwylxxbz_seqid);
	}

	public String getCLFWYLXXB_LSH() {
		return CLFWYLXXB_LSH;
	}

	public void setCLFWYLXXB_LSH(String clfwylxxb_lsh) {
		CLFWYLXXB_LSH = ClfwCommon.sql_inj(clfwylxxb_lsh);
	}

	public String getCLFWYLXXB_CPH() {
		return CLFWYLXXB_CPH;
	}

	public void setCLFWYLXXB_CPH(String clfwylxxb_cph) {
		CLFWYLXXB_CPH = ClfwCommon.sql_inj(clfwylxxb_cph);
	}

	public String getCLFWYLXXB_SFJSM() {
		return CLFWYLXXB_SFJSM;
	}

	public void setCLFWYLXXB_SFJSM(String clfwylxxb_sfjsm) {
		CLFWYLXXB_SFJSM = ClfwCommon.sql_inj(clfwylxxb_sfjsm);
	}

	
	
	public String getCLFWYLXXB_ZDJID() {
		return CLFWYLXXB_ZDJID;
	}

	public void setCLFWYLXXB_ZDJID(String clfwylxxb_zdjid) {
		CLFWYLXXB_ZDJID = ClfwCommon.sql_inj(clfwylxxb_zdjid);
	}

	public String getCLFWYLXXB_SJDDSJ() {
		return CLFWYLXXB_SJDDSJ;
	}

	public void setCLFWYLXXB_SJDDSJ(String clfwylxxb_sjddsj) {
		CLFWYLXXB_SJDDSJ = ClfwCommon.sql_inj(clfwylxxb_sjddsj);
	}

	public String getCLFWYLXXB_ZDJSM() {
		return CLFWYLXXB_ZDJSM;
	}

	public void setCLFWYLXXB_ZDJSM(String clfwylxxb_zdjsm) {
		CLFWYLXXB_ZDJSM = ClfwCommon.sql_inj(clfwylxxb_zdjsm);
	}

	public String getCLFWTDB_SHSX() {
		return CLFWTDB_SHSX;
	}

	public void setCLFWTDB_SHSX(String clfwtdb_shsx) {
		CLFWTDB_SHSX = ClfwCommon.sql_inj(clfwtdb_shsx);
	}

	public String getCLFWKFFW_SEQID() {
		return CLFWKFFW_SEQID;
	}

	public void setCLFWKFFW_SEQID(String clfwkffw_seqid) {
		CLFWKFFW_SEQID = ClfwCommon.sql_inj(clfwkffw_seqid);
	}

	public String getCLFWKFFW_DISTCD() {
		return CLFWKFFW_DISTCD;
	}

	public void setCLFWKFFW_DISTCD(String clfwkffw_distcd) {
		CLFWKFFW_DISTCD = ClfwCommon.sql_inj(clfwkffw_distcd);
	}

	public String getCLFWKFFW_YTEFW() {
		return CLFWKFFW_YTEFW;
	}

	public void setCLFWKFFW_YTEFW(String clfwkffw_ytefw) {
		CLFWKFFW_YTEFW = ClfwCommon.sql_inj(clfwkffw_ytefw);
	}

	public String getCLFWKFFW_SWSFW() {
		return CLFWKFFW_SWSFW;
	}

	public void setCLFWKFFW_SWSFW(String clfwkffw_swsfw) {
		CLFWKFFW_SWSFW = ClfwCommon.sql_inj(clfwkffw_swsfw);
	}

	public String getCLFWPCSHI_DISTCD() {
		return CLFWPCSHI_DISTCD;
	}

	public void setCLFWPCSHI_DISTCD(String clfwpcshi_distcd) {
		CLFWPCSHI_DISTCD = ClfwCommon.sql_inj(clfwpcshi_distcd);
	}

	public String getCLFWPCSHI_SM() {
		return CLFWPCSHI_SM;
	}

	public void setCLFWPCSHI_SM(String clfwpcshi_sm) {
		CLFWPCSHI_SM = ClfwCommon.sql_inj(clfwpcshi_sm);
	}

	public String getCLFWPCSHI_SHIGM() {
		return CLFWPCSHI_SHIGM;
	}

	public void setCLFWPCSHI_SHIGM(String clfwpcshi_shigm) {
		CLFWPCSHI_SHIGM = ClfwCommon.sql_inj(clfwpcshi_shigm);
	}

	public String getCLFWPCSHI_SEQID() {
		return CLFWPCSHI_SEQID;
	}

	public void setCLFWPCSHI_SEQID(String clfwpcshi_seqid) {
		CLFWPCSHI_SEQID = ClfwCommon.sql_inj(clfwpcshi_seqid);
	}

	public String getCLFWPCSHENG_SHENGM() {
		return CLFWPCSHENG_SHENGM;
	}

	public void setCLFWPCSHENG_SHENGM(String clfwpcsheng_shengm) {
		CLFWPCSHENG_SHENGM = ClfwCommon.sql_inj(clfwpcsheng_shengm);
	}

	public String getCLFWPCSHENG_SEQID() {
		return CLFWPCSHENG_SEQID;
	}

	public void setCLFWPCSHENG_SEQID(String clfwpcsheng_seqid) {
		CLFWPCSHENG_SEQID = ClfwCommon.sql_inj(clfwpcsheng_seqid);
	}

	public String getCLFWPCSHENG_SM() {
		return CLFWPCSHENG_SM;
	}

	public void setCLFWPCSHENG_SM(String clfwpcsheng_sm) {
		CLFWPCSHENG_SM = ClfwCommon.sql_inj(clfwpcsheng_sm);
	}

	public String getCLFWPCSHENG_DISTCD() {
		return CLFWPCSHENG_DISTCD;
	}

	public void setCLFWPCSHENG_DISTCD(String clfwpcsheng_distcd) {
		CLFWPCSHENG_DISTCD = ClfwCommon.sql_inj(clfwpcsheng_distcd);
	}

	public String getCLFWTDB_FW() {
		return CLFWTDB_FW;
	}

	public void setCLFWTDB_FW(String clfwtdb_fw) {
		CLFWTDB_FW = ClfwCommon.sql_inj(clfwtdb_fw);
	}
	public String getCLFWPC_DISTCD() {
		return CLFWPC_DISTCD;
	}

	public void setCLFWPC_DISTCD(String clfwpc_distcd) {
		CLFWPC_DISTCD = ClfwCommon.sql_inj(clfwpc_distcd);
	}

	public String getPG_PK_CODE() {
		return PG_PK_CODE;
	}

	public void setPG_PK_CODE(String pg_pk_code) {
		PG_PK_CODE = ClfwCommon.sql_inj(pg_pk_code);
	}

	public String getCLFWTDD_CLSX() {
		return CLFWTDD_CLSX;
	}

	public void setCLFWTDD_CLSX(String clfwtdd_clsx) {
		CLFWTDD_CLSX = ClfwCommon.sql_inj(clfwtdd_clsx);
	}

	public String getCLFWTDB_SJYXSC() {
		return CLFWTDB_SJYXSC;
	}

	public void setCLFWTDB_SJYXSC(String clfwtdb_sjyxsc) {
		CLFWTDB_SJYXSC = ClfwCommon.sql_inj(clfwtdb_sjyxsc);
	}

	public String getCLFWTDB_LSLZB() {
		return CLFWTDB_LSLZB;
	}

	public void setCLFWTDB_LSLZB(String clfwtdb_lslzb) {
		CLFWTDB_LSLZB = ClfwCommon.sql_inj(clfwtdb_lslzb);
	}

	public String getCLFWTDB_JLZX() {
		return CLFWTDB_JLZX;
	}

	public void setCLFWTDB_JLZX(String clfwtdb_jlzx) {
		CLFWTDB_JLZX = ClfwCommon.sql_inj(clfwtdb_jlzx);
	}

	public String getCLFWPC_MC() {
		return CLFWPC_MC;
	}

	public void setCLFWPC_MC(String clfwpc_mc) {
		CLFWPC_MC = ClfwCommon.sql_inj(clfwpc_mc);
	}

	public String getCLFWPC_SX() {
		return CLFWPC_SX;
	}

	public void setCLFWPC_SX(String clfwpc_sx) {
		CLFWPC_SX = ClfwCommon.sql_inj(clfwpc_sx);
	}

	public String getCLFWPC_SM() {
		return CLFWPC_SM;
	}

	public void setCLFWPC_SM(String clfwpc_sm) {
		CLFWPC_SM = ClfwCommon.sql_inj(clfwpc_sm);
	}

	public String getCLFWTDBCQ_SEQID() {
		return CLFWTDBCQ_SEQID;
	}

	public void setCLFWTDBCQ_SEQID(String clfwtdbcq_seqid) {
		CLFWTDBCQ_SEQID = ClfwCommon.sql_inj(clfwtdbcq_seqid);
	}

	public String getCLFWTDB_SEQID() {
		return CLFWTDB_SEQID;
	}

	public void setCLFWTDB_SEQID(String clfwtdb_seqid) {
		CLFWTDB_SEQID = clfwtdb_seqid;
	}

	public String getSaveMessage() {
		return saveMessage;
	}

	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}

	public String getOPE_REMARK() {
		return OPE_REMARK;
	}

	public void setOPE_REMARK(String ope_remark) {
		OPE_REMARK = ClfwCommon.sql_inj(ope_remark);
	}

	public String getCLFWPC_SEQID() {
		return CLFWPC_SEQID;
	}

	public void setCLFWPC_SEQID(String clfwpc_seqid) {
		CLFWPC_SEQID = clfwpc_seqid;
	}

	public String getCLFWTDB_DISTCD() {
		return CLFWTDB_DISTCD;
	}

	public void setCLFWTDB_DISTCD(String clfwtdb_distcd) {
		CLFWTDB_DISTCD = clfwtdb_distcd;
	}

	public String getCLFWTDB_CLSX() {
		return CLFWTDB_CLSX;
	}

	public void setCLFWTDB_CLSX(String clfwtdb_clsx) {
		CLFWTDB_CLSX = clfwtdb_clsx;
	}

	public String getDM_PK_CODE() {
		return DM_PK_CODE;
	}

	public void setDM_PK_CODE(String dm_pk_code) {
		DM_PK_CODE = dm_pk_code;
	}

	public void configCLFWBean() {
		clfwBean = new CLFWBean();
		
		clfwBean.setOPE_REMARK(ClfwCommon.configcommon(OPE_REMARK));

		clfwBean.setCLFWPC_SEQID(ClfwCommon.configcommon(CLFWPC_SEQID));

		clfwBean.setCLFWTDB_DISTCD(ClfwCommon.configcommon(CLFWTDB_DISTCD));

		clfwBean.setCLFWTDB_CLSX(ClfwCommon.configcommon(CLFWTDB_CLSX));

		clfwBean.setDM_PK_CODE(ClfwCommon.configcommon(DM_PK_CODE));

		clfwBean.setCLFWTDB_SEQID(ClfwCommon.configcommon(CLFWTDB_SEQID));

		clfwBean.setCLFWTDBCQ_SEQID(ClfwCommon.configcommon(CLFWTDBCQ_SEQID));

		clfwBean.setCLFWPC_MC(ClfwCommon.configcommon(CLFWPC_MC));

		clfwBean.setCLFWPC_SM(ClfwCommon.configcommon(CLFWPC_SM));

		clfwBean.setCLFWPC_SX(ClfwCommon.configcommon(CLFWPC_SX));

		clfwBean.setCLFWTDB_JLZX(ClfwCommon.configcommon(CLFWTDB_JLZX));
		
		clfwBean.setCLFWTDB_SJYXSC(ClfwCommon.configcommon(CLFWTDB_SJYXSC));
		
		clfwBean.setCLFWTDB_LSLZB(ClfwCommon.configcommon(CLFWTDB_LSLZB));
		
		clfwBean.setPG_PK_CODE(ClfwCommon.configcommon(PG_PK_CODE));
		
		clfwBean.setCLFWTDD_CLSX(ClfwCommon.configcommon(CLFWTDD_CLSX));
		
		clfwBean.setCLFWTDB_FW(ClfwCommon.configcommon(CLFWTDB_FW));
		
		clfwBean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(CLFWPCSHENG_SHENGM));
		
		clfwBean.setCLFWPCSHENG_SEQID(ClfwCommon.configcommon(CLFWPCSHENG_SEQID));
		
		clfwBean.setCLFWPCSHENG_SM(ClfwCommon.configcommon(CLFWPCSHENG_SM));
		
		clfwBean.setCLFWPCSHENG_DISTCD(ClfwCommon.configcommon(CLFWPCSHENG_DISTCD));
		
		clfwBean.setCLFWPCSHI_SEQID(ClfwCommon.configcommon(CLFWPCSHI_SEQID));
		
		clfwBean.setCLFWPCSHI_SM(ClfwCommon.configcommon(CLFWPCSHI_SM));
		
		clfwBean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(CLFWPCSHI_SHIGM));
		
		clfwBean.setCLFWPCSHI_DISTCD(ClfwCommon.configcommon(CLFWPCSHI_DISTCD));
		
		clfwBean.setCLFWKFFW_SEQID(ClfwCommon.configcommon(CLFWKFFW_SEQID));
		clfwBean.setCLFWKFFW_DISTCD(ClfwCommon.configcommon(CLFWKFFW_DISTCD));
		clfwBean.setCLFWKFFW_YTEFW(ClfwCommon.configcommon(CLFWKFFW_YTEFW));
		clfwBean.setCLFWKFFW_SWSFW(ClfwCommon.configcommon(CLFWKFFW_SWSFW));
		
		clfwBean.setCLFWTDB_SHSX(ClfwCommon.configcommon(CLFWTDB_SHSX));
		
		clfwBean.setCLFWYLXXB_SFJID(ClfwCommon.configcommon(CLFWYLXXB_SFJID));
		clfwBean.setCLFWYLXXB_SJKCSJ(ClfwCommon.configcommon(CLFWYLXXB_SJKCSJ));
		clfwBean.setCLFWYLXXBZ_SEQID(ClfwCommon.configcommon(CLFWYLXXBZ_SEQID));
		clfwBean.setCLFWYLXXB_LSH(ClfwCommon.configcommon(CLFWYLXXB_LSH));
		clfwBean.setCLFWYLXXB_CPH(ClfwCommon.configcommon(CLFWYLXXB_CPH));
		clfwBean.setCLFWYLXXB_SFJSM(ClfwCommon.configcommon(CLFWYLXXB_SFJSM));
		clfwBean.setCLFWYLXXB_ZDJID(ClfwCommon.configcommon(CLFWYLXXB_ZDJID));
		clfwBean.setCLFWYLXXB_SJDDSJ(ClfwCommon.configcommon(CLFWYLXXB_SJDDSJ));
		clfwBean.setCLFWYLXXB_ZDJSM(ClfwCommon.configcommon(CLFWYLXXB_ZDJSM));
		clfwBean.setCLFWYLXXB_SEQID(ClfwCommon.configcommon(CLFWYLXXB_SEQID));
		
		clfwBean.setCLFWYLXXB_XSBZ(ClfwCommon.configcommon(CLFWYLXXB_XSBZ));
		clfwBean.setCLFWYLXXB_QYRQ(ClfwCommon.configcommon(CLFWYLXXB_QYRQ));
		
		clfwBean.setCLFWRESORGPZ_SEQID(ClfwCommon.configcommon(CLFWRESORGPZ_SEQID));
		clfwBean.setCLFWYLXXB_FLAG(ClfwCommon.configcommon(CLFWYLXXB_FLAG));
		clfwBean.setCLFWYLXXB_CSRQ(ClfwCommon.configcommon(CLFWYLXXB_CSRQ));
		
		clfwBean.setCLFW_HBXXGL_CSRQ(ClfwCommon.configcommon(CLFW_HBXXGL_CSRQ));
		clfwBean.setCLFW_HBXXGL_HBH(ClfwCommon.configcommon(CLFW_HBXXGL_HBH));
		clfwBean.setCLFW_HBXXGL_ZBS(ClfwCommon.configcommon(CLFW_HBXXGL_ZBS));
		clfwBean.setCLFW_HBXXGL_ZL(ClfwCommon.configcommon(CLFW_HBXXGL_ZL));
		clfwBean.setCLFW_HBXXGL_LDZBS(ClfwCommon.configcommon(CLFW_HBXXGL_LDZBS));
		clfwBean.setCLFW_HBXXGL_SSZBS(ClfwCommon.configcommon(CLFW_HBXXGL_SSZBS));
		clfwBean.setCLFW_HBXXGL_SFJID(ClfwCommon.configcommon(CLFW_HBXXGL_SFJID));
		clfwBean.setCLFW_HBXXGL_ZDJID(ClfwCommon.configcommon(CLFW_HBXXGL_ZDJID));
		clfwBean.setCLFW_HBXXGL_SEQID(ClfwCommon.configcommon(CLFW_HBXXGL_SEQID));
		clfwBean.setCLFW_HBXXGL_LDLSH(ClfwCommon.configcommon(CLFW_HBXXGL_LDLSH));
		
		clfwBean.setCLFWTDBNJJS_SEQID(ClfwCommon.configcommon(CLFWTDBNJJS_SEQID));
		clfwBean.setCLFWTDBNJJS_SFNJ(ClfwCommon.configcommon(CLFWTDBNJJS_SFNJ));

		clfwBean.setCLFWYLXXB_SFJGH(ClfwCommon.configcommon(CLFWYLXXB_SFJGH));
		clfwBean.setCLFWYLXXB_ZDJGH(ClfwCommon.configcommon(CLFWYLXXB_ZDJGH));

		clfwBean.setCLFW_HBXXLDBW_SEQID(ClfwCommon.configcommon(CLFW_HBXXLDBW_SEQID));
		clfwBean.setCLFW_HBXXGL_XSBZ(ClfwCommon.configcommon(CLFW_HBXXGL_XSBZ));
		clfwBean.setCLFW_HBXXLDBW_AJTHZBS(ClfwCommon.configcommon(CLFW_HBXXLDBW_AJTHZBS));
		clfwBean.setCLFW_HBXXLDBW_LXZBS(ClfwCommon.configcommon(CLFW_HBXXLDBW_LXZBS));
		clfwBean.setCLFW_HBXXLDBW_SSZBS(ClfwCommon.configcommon(CLFW_HBXXLDBW_SSZBS));
		clfwBean.setCLFWYLXXB_SFJGHSM(ClfwCommon.configcommon(CLFWYLXXB_SFJGHSM));
		
		
		clfwBean.setCLFWPCSHENG_JGDM(ClfwCommon.configcommon(CLFWPCSHENG_JGDM));
		clfwBean.setCLFWPCSHI_JZSJ(ClfwCommon.configcommon(CLFWPCSHI_JZSJ));
		clfwBean.setCLFWPCSHI_JZYH(ClfwCommon.configcommon(CLFWPCSHI_JZYH));
		
		
		clfwBean.setOPE_OPEID(getSessionUser().getUsLoginId());
		clfwBean.setOPE_OPENAME(getSessionUser().getUsName());
	}

	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}
	
	public String addclfwpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWPC where CLFWPC_MC = '" + CLFWPC_MC + "' and CLFWPC_SX = '" + CLFWPC_SX + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的频次名称,不能添加此数据!";
			return "json";
		}
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwpc(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}

	public String upclfwpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWPC where CLFWPC_MC = '" + CLFWPC_MC + "' and CLFWPC_SX = '" + CLFWPC_SX + "',' and  CLFWPC_SEQID != '" + CLFWPC_SEQID + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的频次名称,不能添加此数据!";
			return "json";
		}
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwpc(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}

	public String delclfwpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwpc(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	public String addclfwpcsheng() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWPC_SHENG where CLFWPC_SEQID||CLFWPCSHENG_DISTCD||CLFWPCSHENG_SHENGM = '" + CLFWPC_SEQID+CLFWPCSHENG_DISTCD+CLFWPCSHENG_SHENGM + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的配置名称,不能添加此数据!";
			return "json";
		}
		
		sql = "select count(*) from CLFW_CLFWPC_SHENG where CLFWPCSHENG_JGDM = '" + CLFWPCSHENG_JGDM + "' and CLFWPCSHENG_SHENGM != '" + CLFWPCSHENG_SHENGM + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的配置机构代码,不能添加此数据!";
			return "json";
		}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwpcsheng(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}
	
	public String upclfwpcsheng() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWPC_SHENG where CLFWPC_SEQID||CLFWPCSHENG_DISTCD||CLFWPCSHENG_SHENGM = '" + CLFWPC_SEQID+CLFWPCSHENG_DISTCD+CLFWPCSHENG_SHENGM + "' and  CLFWPCSHENG_SEQID != '" + CLFWPCSHENG_SEQID + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的配置名称,不能添加此数据!";
			return "json";
		}
		
		sql = "select count(*) from CLFW_CLFWPC_SHENG where CLFWPCSHENG_JGDM = '" + CLFWPCSHENG_JGDM + "' and  CLFWPCSHENG_SEQID != '" + CLFWPCSHENG_SEQID + "' and CLFWPCSHENG_SHENGM != '" + CLFWPCSHENG_SHENGM + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的配置机构代码,不能添加此数据!";
			return "json";
		}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwpcsheng(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}
	
	
	public String delclfwpcsheng() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwpcsheng(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	public String addclfwpcshi() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWPC_SHI where " +
				"CLFWPCSHENG_SEQID = '" + CLFWPCSHENG_SEQID + "' and CLFWPC_SEQID = '" + CLFWPC_SEQID + "' and CLFWPCSHI_DISTCD = '" + CLFWPCSHI_DISTCD + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的频次配置,不能添加此数据!";
			return "json";
		}
		
		/*
		if(CLFWPCSHI_JZYH.equals("1")){
			sql = "select count(*) from CLFW_CLFWPC_SHI where   CLFWPCSHI_JZYH = '1' and CLFWPCSHI_DISTCD = '" + CLFWPCSHI_DISTCD + "'";
			if (clfwQueryDao.getQueryCount(sql) > 0) {
				saveMessage = "已经存在选择同揽收邮航频次的频次配置,不能添加此数据!";
				return "json";
			}
		}
		*/
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwpcshi(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}

	public String upclfwpcshi() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWPC_SHI where " +
		"CLFWPCSHENG_SEQID = '" + CLFWPCSHENG_SEQID + "' and CLFWPC_SEQID = '" + CLFWPC_SEQID + "' and CLFWPCSHI_DISTCD = '" + CLFWPCSHI_DISTCD + "' " +
				" and CLFWPCSHI_SEQID != '" + CLFWPCSHI_SEQID + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的频次配置,不能添加此数据!";
			return "json";
		}
		
		/*
		if(CLFWPCSHI_JZYH.equals("1")){
			sql = "select count(*) from CLFW_CLFWPC_SHI where  CLFWPCSHI_JZYH = '1' and CLFWPCSHI_DISTCD = '" + CLFWPCSHI_DISTCD + "'" +
			" and CLFWPCSHI_SEQID != '" + CLFWPCSHI_SEQID + "'";
			if (clfwQueryDao.getQueryCount(sql) > 0) {
				saveMessage = "已经存在选择同揽收邮航频次的频次配置,不能添加此数据!";
				return "json";
			}
		}
		*/
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwpcshi(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}
	
	public String delclfwpcshi() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwpcshi(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	public String delclfwtdbcq() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwtdbcq(clfwBean);

		saveMessage = "配置成功";
		return "json";
	}

	public String addclfwtdbcq() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWTDBCQ where DM_PK_CODE = '"
			+ DM_PK_CODE + "' and  CLFWTDB_DISTCD = '" + CLFWTDB_DISTCD  + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的投递部属性配置,不能添加此数据!";
			return "json";
		}
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwtdbcq(clfwBean);

		saveMessage = "配置成功";
		return "json";
	}

	public String upclfwtdbcq() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWTDBCQ where DM_PK_CODE = '"
			+ DM_PK_CODE + "' and  CLFWTDB_DISTCD = '" + CLFWTDB_DISTCD + "' and  CLFWTDBCQ_SEQID != '" + CLFWTDBCQ_SEQID + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的投递部属性配置,不能添加此数据!";
			return "json";
		}
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwtdbcq(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}
	
	public String addclfwtdb() throws Exception {	
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_CLFWTDB where DM_PK_CODE in "
			+ DM_PK_CODE + " and  CLFWPCSHI_SEQID = '" + CLFWPCSHI_SEQID  + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的投递部属性配置,不能添加此数据!";
			return "json";
		}
		configCLFWBean();
		clfwBean.setCLFWTDB_SHSX("1");
		@SuppressWarnings("unused")
		boolean m=clfwConfigDao.addclfwtdb(clfwBean);
		
		saveMessage="新增成功";
		return "json";
	}
	
	public String upclfwtdb() throws Exception {	
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m=clfwConfigDao.upclfwtdb(clfwBean);
		
		saveMessage="修改成功";
		return "json";
	}
	
	public String bjclfwtdb() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		
		@SuppressWarnings("unused")
		boolean m=clfwConfigDao.bjclfwtdb(clfwBean);

		saveMessage="编辑成功";
		return "json";
	}
	
	public String shclfwtdb() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		
		@SuppressWarnings("unused")
		boolean m=clfwConfigDao.shclfwtdb(clfwBean);

		saveMessage="审核成功";
		return "json";
	}
	
	public String delclfwtdb() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m=clfwConfigDao.delclfwtdb(clfwBean);

		saveMessage="删除成功";
		return "json";
	}
	
	public String bjclfwtdd() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m=clfwConfigDao.bjclfwtdd(clfwBean);

		saveMessage="编辑成功";
		return "json";
	}
	
	public String addclfwkffw() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_KFFW where CLFWKFFW_DISTCD = '" + CLFWKFFW_DISTCD + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的行政区域范围配置!";
			return "json";
		}
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwkffw(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}
	
	public String upclfwkffw() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select count(*) from CLFW_KFFW where CLFWKFFW_DISTCD = '" + CLFWKFFW_DISTCD + "' and  CLFWKFFW_SEQID != '" + CLFWKFFW_SEQID + "'";
		if (clfwQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的行政区域范围配置!";
			return "json";
		}
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwkffw(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}
	
	
	public String delclfwkffw() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwkffw(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	public String addclfwhkwhcg() throws Exception {
		
		if(!userflag()){return "json";}

		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwhkwhcg(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}
	
	public String upclfwhkwhcg() throws Exception {
		
		if(!userflag()){return "json";}

		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwhkwhcg(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}

	public String addclfwhkwhjg() throws Exception {
		
		if(!userflag()){return "json";}

		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwhkwhjg(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}
	
	public String upclfwhkwhjg() throws Exception {
		
		if(!userflag()){return "json";}

		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwhkwhjg(clfwBean);

		saveMessage = "修改成功";
		return "json";
	}
	
	public String delclfwhkwh() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwhkwh(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	
	public String bjclfwwhkwhcg() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.bjclfwwhkwhcg(clfwBean);

		saveMessage = "清除成功";
		return "json";
	}
	
	public String bjclfwwhkwhjg() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.bjclfwwhkwhjg(clfwBean);

		saveMessage = "清除成功";
		return "json";
	}
	
	
	
	public String upclfwylxxbt() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwylxxbt(clfwBean);

		saveMessage = "配置成功";
		return "json";
	}
	
	
	public String addclfwresorgpz() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwresorgpz(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}
	
	
	public String delclfwresorgpz() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwresorgpz(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	public String addclfwtabnjjs() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.addclfwtabnjjs(clfwBean);

		saveMessage = "新增成功";
		return "json";
	}
	
	
	public String delclfwtabnjjs() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.delclfwtabnjjs(clfwBean);

		saveMessage = "删除成功";
		return "json";
	}
	
	public String bjclfwylxxclwt() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.bjclfwylxxclwt(clfwBean);

		saveMessage = "处理成功";
		return "json";
	}
	
	public String upclfwhkldwh() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.upclfwhkldwh(clfwBean);

		saveMessage = "配置成功";
		return "json";
	}
	
	public String bjupclfwhkldwh() throws Exception {
		
		if(!userflag()){return "json";}
		
		configCLFWBean();
		@SuppressWarnings("unused")
		boolean m = clfwConfigDao.bjupclfwhkldwh(clfwBean);

		saveMessage = "处理成功";
		return "json";
	}
}
