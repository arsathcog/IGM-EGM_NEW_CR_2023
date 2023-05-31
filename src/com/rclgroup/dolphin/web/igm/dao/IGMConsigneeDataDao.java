package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.niit.control.common.exception.BusinessException;
import com.rclgroup.dolphin.web.igm.vo.Consignee;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public interface IGMConsigneeDataDao {
	
	public static final String RCL_IGM_SAVE_CONSIGNEE = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_CONSIGNEE_DATA"; 
	
	public static final String RCL_IGM_SAVE_UNFETCHED_CONSIGNEE = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_UNFETCHED_CONSIGNEE_DATA"; 
	
	public static final String RCL_IGM_SAVE_CONSIGNEE_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_CONSIGNEE_DATA";
	
	public static final String RCL_IGM_DELETE_CONSIGNEE = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_CONSIGNEE_DATA";
	
	public static final String RCL_IGM_DELETE_CONSIGNEE_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_CONSIGNEE_DATA";
	
    public static final String RCL_IGM_GET_MASTER_CONSIGNEE = "RCL_IGM_BL_INFO.RCL_IGM_GET_MASTER_CONSIGNEE";
    
    public static final String RCL_IGM_GET_MASTER_CONSIGNEE_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_MASTER_CONSIGNEE";
	
	public static final String RCL_IGM_GET_SAVE_CONSIGNEE = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_CONSIGNEE";
	
	public static final String RCL_IGM_GET_SAVE_CONSIGNEE_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_CONSIGNEE";
	
	public static final String KEY_IGM_CONSIGNEE_DTLS = "P_I_V_CONSIGNEE_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	public void saveConsigneeData(List<Consignee> blsForSavingCont,String blsInput,String procedureName) throws Exception;
	
	public void setConsigneeData(List<ImportGeneralManifestMod> listOfBL,
			String procedureName) throws Exception ;

	public void deleteConsigneeData(List<Consignee> consignee, String blsInput, String procedureName) throws JsonProcessingException;

//	method for unfetched bl 
	
	public void saveUnfetchedConsigneeData(String blsInput,String procedureName) throws Exception;
	
	
}
