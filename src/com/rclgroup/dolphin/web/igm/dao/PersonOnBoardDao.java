package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;
import java.util.Map;

import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;

public interface PersonOnBoardDao {
	/** The Constant SQL_GET_IGM_DATA. */
	public static final String RCL_IGM_SAVE_PERSON_DETAILES = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_SAVE_PERSON_DETAILES";

	public static final String RCL_IGM_SAVE_CREW_EFFECT_FORMATE = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_CREW_EFFECTS_FORMAT";

	public static final String RCL_IGM_SAVE_SHIP_STORE_FORMAT = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_SHIP_STORE_FORMAT";

	public static final String RCL_IGM_GET_SAVE_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_CONTAINOR";

	public static final String RCL_IGM_GET_DELETE_CSV_DATA = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_GET_DELETE_CSV_DATA";

	public static final String SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_GET_SAVE_PERSON_ON_BOARD";
	
	public static final String SQL_RCL_IGM_GET_SAVE_CREW_EFFECT = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_GET_SAVE_CREW_EFFECT";
	
	public static final String SQL_RCL_IGM_GET_SAVE_SHIP_STORE = "RCL_IGM_VESEEL_VOYOAGE_INFO.RCL_IGM_GET_SAVE_SHIP_STORE";
	

	public void savePersonOnBoard(List<IGMPersonOnBoardMod> personDetailes, String blsInput) throws Exception;

	public void saveCrewEfect(List<IGMCrewEfctMod> crewEfctModDetailes, String blsInput) throws Exception;

	public void saveShipStore(List<IGMShipStoresMod> storesModDetailes, String blsInput) throws Exception;

	public List<IGMPersonOnBoardMod> getPersonOnBoard(Map<String, String> amapParam, String procedureName)
			throws Exception;

	public List<IGMCrewEfctMod> getCrewEffect(Map<String, String> amapParam, String procedureName) throws Exception;

	public List<IGMShipStoresMod> getShipStore(Map<String, String> amapParam, String procedureName) throws Exception;

	public void deleteCsv(	ImportGeneralManifestUim objForm ) throws Exception;

}
