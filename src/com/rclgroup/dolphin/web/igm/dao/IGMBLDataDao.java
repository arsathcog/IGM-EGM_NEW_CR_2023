package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public interface IGMBLDataDao {
	
	public static final String RCL_IGM_SAVE_BL = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_BL_DATA_SUSHIL";
	
	public static final String RCL_IGM_SAVE_BL_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_BL_DATA_SUSHIL";
	
	public static final String RCL_IGM_DELETE_BL = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_BL_DATA";
	
	public static final String RCL_IGM_DELETE_BL_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_BL_DATA";
	
	public static final String KEY_IGM_BL_DTLS = "P_I_V_BL_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	
	public void saveBLData(List<ImportGeneralManifestMod> blsForSavingCont,String procedureName) throws Exception;

	public void deleteBLData(List<ImportGeneralManifestMod> deleteBL, String procedureName) throws CloneNotSupportedException, JsonProcessingException;

}
