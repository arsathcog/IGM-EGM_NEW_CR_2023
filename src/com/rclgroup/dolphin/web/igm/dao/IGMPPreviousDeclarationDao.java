package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.PreviousDeclaration;

public interface IGMPPreviousDeclarationDao {

	// PreviousDeclarationProcedures
	public static final String RCL_IGM_GET_MASTER_PREV_DECLARATION = "RCL_IGM_BL_INFO.RCL_IGM_GET_PREV_DECLARATION";
	
	public static final String RCL_IGM_GET_MASTER_PREV_DECLARATION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_PREV_DECLARATION";
	
	public static final String RCL_IGM_GET_SAVE_PREV_DECLARATION = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_PREV_DECLARATION";
	
	public static final String RCL_IGM_GET_SAVE_PREV_DECLARATION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_PREV_DECLARATION";
	
	public static final String RCL_IGM_SAVE_PREV_DECLARATION = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_PREV_DECLARATION_DATA";
	
	public static final String RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION_DATA";
	
	public static final String RCL_IGM_SAVE_PREV_DECLARATION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_PREV_DECLARATION_DATA";
	
	public static final String RCL_IGM_DELETE_PREV_DECLARATION = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_PREV_DECLARATION_DATA";
	
	public static final String RCL_IGM_DELETE_PREV_DECLARATION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_PREV_DECLARATION_DATA";
	
	public static final String KEY_IGM_PREV_DECLARATION_DTLS = "P_I_V_PREV_DECLARATION_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";


	public void savePreviousDeclData(List<PreviousDeclaration> blsForSavingCont,String procedureName,String blsInput) throws Exception;
	public void setPreviousDeclData(List<ImportGeneralManifestMod> listOfBL, String procedureName) throws Exception;
	public void deletePreviousDeclData(List<PreviousDeclaration> previousDeclarations, String procedureName,
			String blsInput) throws JsonProcessingException;

//	method for unfetched BL 
	public void saveUnfetchedPreviousDeclData(String procedureName,String blsInput) throws Exception;

}
