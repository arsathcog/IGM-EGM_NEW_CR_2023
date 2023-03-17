package com.rclgroup.dolphin.web.igm.dao;

import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public interface IGMVesselVoyageSaveDao {
	
	public static final String RCL_IGM_SAVE_VESSEL_VOYOAGE = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_SAVE_VESEEL_VOYOAGE_SAVE_DATA";
	
	public static final String RCL_IGM_SAVE_VESSEL_VOYOAGE_EXPORT = "RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT.RCL_IGM_SAVE_VESEEL_VOYOAGE_SAVE_DATA";
	
	public static final String RCL_IGM_APPLY_IGM = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_APPLY_IGM";
	
	public static final String KEY_IGM_VESSEL_VOYOAGE_DTLS = "P_I_V_VESSEL_VOYOAGE_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	
	public void saveVesselVoyageData(ImportGeneralManifestMod veseelSavingCont,String prsedureName) throws Exception;
	
	public void updateIgmdetails(ImportGeneralManifestUim objForm) throws Exception;
	
}
