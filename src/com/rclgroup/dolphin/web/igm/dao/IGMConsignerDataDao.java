package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rclgroup.dolphin.web.igm.vo.Consigner;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public interface IGMConsignerDataDao {
	
	public static final String RCL_IGM_SAVE_CONSIGNER = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_CONSIGNER_DATA"; 
	
	public static final String RCL_IGM_SAVE_UNFETCHED_CONSIGNER = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_UNFETCHED_CONSIGNER_DATA";
	
	public static final String RCL_IGM_SAVE_CONSIGNER_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_CONSIGNER_DATA";
	
	public static final String RCL_IGM_DELETE_CONSIGNER = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_CONSIGNER_DATA";
	
	public static final String RCL_IGM_DELETE_CONSIGNER_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_CONSIGNER_DATA";
	
	public static final String RCL_IGM_GET_MASTER_CONSIGNER = "RCL_IGM_BL_INFO.RCL_IGM_GET_MASTER_CONSIGNER";

	public static final String RCL_IGM_GET_MASTER_CONSIGNER_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_MASTER_CONSIGNER";
	
	public static final String RCL_IGM_GET_SAVE_CONSIGNER = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_CONSIGNER";
	
	public static final String RCL_IGM_GET_SAVE_CONSIGNER_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_CONSIGNER";
	
	public static final String KEY_IGM_CONSIGNER_DTLS = "P_I_V_CONSIGNER_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	
	public void saveConsignerData(List<Consigner> blsForSavingCont,String blsInput,String procedureName) throws Exception;
	
	public void setConsignerData(List<ImportGeneralManifestMod> listOfBL,
			String procedureName) throws Exception ;

	public void deleteConsignerData(List<Consigner> consigner, String blsInput, String procedureName) throws JsonProcessingException;
// Method for unfetched Bl 
	public void saveUnfetchedConsignerData(String blsInput,String procedureName) throws Exception;
	

}
