package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.NotifyParty;
import com.rclgroup.dolphin.web.igm.vo.NotifyPartyTwo;

public interface IGMNodifyPartyDao {
	
	public static final String RCL_IGM_SAVE_NODIFY_PARTY_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_NODIFY_PARTY_DATA"; 
	
	public static final String RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DATA";
	
	public static final String RCL_IGM_SAVE_NODIFY_PARTY_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_NODIFY_PARTY_DATA";
	
	public static final String RCL_IGM_DELETE_NODIFY_PARTY_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_NODIFY_PARTY_DATA";
	
	public static final String RCL_IGM_DELETE_NODIFY_PARTY_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_NODIFY_PARTY_DATA";
	
	public static final String RCL_IGM_SAVE_NODIFY_PARTY_TWO_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_NODIFY_PARTY_TWO_DATA";
	
	public static final String RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_MASTER_NODIFY_PARTY_DATA";
	
	public static final String RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_MASTER_NODIFY_PARTY_DATA";
	
	public static final String RCL_IGM_MASTER_NODIFY_PARTY_TWO_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_MASTER_NODIFY_PARTY_TWO_DATA";
	
	public static final String RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_NOTIFYPARTY";
	
	public static final String RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_NOTIFYPARTY";
	
	public static final String RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_NOTIFYPARTY";
	
	public static final String RCL_IGM_GET_SAVE_NODIFY_PARTY_TWO_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_NODIFY_PARTY_TWO_DATA";
	
	public static final String KEY_IGM_NODIFY_PARTY_DTLS  = "P_I_V_NODIFY_PARTY_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	
	public void saveNodifyData(List<NotifyParty> blsForSavingCont, String blsInput,String procedureName) throws Exception;
	
	public void saveNodifyTwoData(List<NotifyPartyTwo> blsForSavingCont, String input,String procedureName) throws Exception;
	
	public void setNotifyPartyData(List<ImportGeneralManifestMod> listOfBL, String procedureName) throws Exception;
	
	public void setNotifyPartyTwoData(List<ImportGeneralManifestMod> listOfBL, String procedureName) throws Exception;

	public void deleteNodifyData(List<NotifyParty> notifyParty, String blsInput,
			String procedureName) throws JsonProcessingException;
	
//	Method for unfetched bl 
	public void saveUnfetchedNodifyData(String blsInput,String procedureName) throws Exception;
	
	
	
}
