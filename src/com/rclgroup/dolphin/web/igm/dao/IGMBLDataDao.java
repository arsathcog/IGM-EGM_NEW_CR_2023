package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public interface IGMBLDataDao {
	
	public static final String RCL_IGM_SAVE_BL = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_BL_DATA_SUSHIL";
	
	public static final String RCL_IGM_UNFETCHED_SAVE_BL = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_UNFETCHED_BL_DATA_SUSHIL";
	
	public static final String RCL_IGM_UNFETCHED_SAVE_BL_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_UNFETCHED_BL_DATA_SUSHIL";
	
	public static final String RCL_IGM_SAVE_BL_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_BL_DATA_SUSHIL";
	
	public static final String RCL_IGM_DELETE_BL = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_BL_DATA";
	
	public static final String RCL_IGM_DELETE_BL_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_BL_DATA";
	
	public static final String KEY_IGM_BL_DTLS = "P_I_V_BL_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	
	/** The Constant KEY_IGM_BL. */
	public static final String KEY_IGM_BL = "P_I_V_BL";

	/** The Constant KEY_IGM_POD. */
	public static final String KEY_IGM_POD = "P_I_V_POD";

	public static final String KEY_IGM_BL_COUNT = "P_I_V_BL_COUNT";
	
	/** The Constant KEY_IGM_SERVICE. */
	public static final String KEY_IGM_SERVICE = "P_I_V_SERVICE";
	
	/** The Constant KEY_IGM_VESSEL. */
	public static final String KEY_IGM_VESSEL = "P_I_V_VESSEL";
	
	/** The Constant KEY_IGM_VOYAGE. */
	public static final String KEY_IGM_VOYAGE = "P_I_V_VOYAGE";
	
	/** The Constant KEY_IGM_POD_TERMINAL. */
	public static final String KEY_IGM_POD_TERMINAL = "P_I_V_POD_TERMINAL";

	/** The Constant KEY_IGM_POL. */
	public static final String KEY_IGM_POL = "P_I_V_POL";
	
	/** The Constant KEY_IGM_POL_TERMINAL. */
	public static final String KEY_IGM_POL_TERMINAL = "P_I_V_POL_TERMINAL";
	
	public static final String KEY_IGM_BL_JSON = "P_I_V_BL_JSON";
	
	public void saveBLData(List<ImportGeneralManifestMod> blsForSavingCont,String blsInput,String procedureName) throws Exception;

	public void saveUnfetchedBlData (String unFetchedinsertBLList,String procedureName,Map amapParam,List<ImportGeneralManifestMod> insertBL) throws Exception;
	
	public void deleteBLData(List<ImportGeneralManifestMod> deleteBL,String blsDeleteInput,String procedureName) throws CloneNotSupportedException, JsonProcessingException;

}
