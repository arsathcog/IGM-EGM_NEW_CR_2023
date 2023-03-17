package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.MarksNumber;

public interface IGMMarksAndDescDao {
	

public static final String RCL_IGM_GET_MASTER_MARKS_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_GET_MARKS_DESCRIPTION";

public static final String RCL_IGM_GET_MASTER_MARKS_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_MARKS_DESCRIPTION";
	
	public static final String RCL_IGM_GET_SAVE_MARKS_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION";
	
	public static final String RCL_IGM_GET_SAVE_MARKS_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION";
	
	public static final String RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA";
	
	public static final String RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA";
	
	public static final String RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION_DATA";
	
	public static final String RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION_DATA";
	
	public static final String KEY_IGM_MARKS_AND_DESC_DTLS  = "P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";
	
	public void saveMarkDescData(List<MarksNumber> blsForSavingCont, String input,String procedureName) throws Exception;
	
	public void setMarksDescriptionData(List<ImportGeneralManifestMod> listOfBL,
			String procedureName) throws Exception;

	public void deleteMarkDescData(List<MarksNumber> marksNumber, String blsInput,
			String procedureName) throws JsonProcessingException; 
}
