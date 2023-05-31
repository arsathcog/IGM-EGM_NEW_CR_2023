package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.niit.control.common.exception.BusinessException;
import com.rclgroup.dolphin.web.igm.vo.ContainerDetails;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public interface  IGMContainerDao {
	/** The Constant SQL_GET_IGM_DATA. */
	public static final String RCL_IGM_GET_MASTER_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_GET_MASTER_CONTAINOR";
	
	public static final String RCL_IGM_GET_MASTER_CONTAINOR_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_MASTER_CONTAINOR";
	
	public static final String RCL_IGM_GET_SAVE_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_CONTAINOR";
	
	public static final String RCL_IGM_GET_SAVE_CONTAINOR_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_CONTAINOR";
	
	public static final String RCL_IGM_SAVE_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_CONTAINOR_NEW";
	
	public static final String RCL_IGM_SAVE_UNFETCHED_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_UNFETCHED_CONTAINOR_NEW";
	
	public static final String RCL_IGM_SAVE_CONTAINOR_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_SAVE_CONTAINOR_NEW";
	
	public static final String RCL_IGM_DELETE_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_DELETE_CONTAINOR_NEW";
	
	public static final String RCL_IGM_DELETE_CONTAINOR_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_DELETE_CONTAINOR_NEW";
	
	public Map<String,List<ContainerDetails>>  setContainerDetails(List<ImportGeneralManifestMod> listOfBL,String procedureName) throws Exception ;
	
	public Map saveContainer(List<ContainerDetails> containerDetailes, String blsInput,String procedure) throws Exception;

	public List<ContainerDetails> setContainerDetailsNew(List<ImportGeneralManifestMod> listOfBL,String procedureName) throws Exception ;

	public void deleteContainer(List<ContainerDetails> containerDetailes, String blsInput, String rclIgmSaveContainor) throws JsonProcessingException;
//	Methods for unfetched bl 
	public Map saveUnfetchedContainer(String blsInput,String procedure) throws Exception;
}
