package com.rclgroup.dolphin.web.igm.dao;

import java.util.List;
import java.util.Map;

import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.vo.BlId;
import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;
import com.rclgroup.dolphin.web.igm.vo.sam.ItnrySAM;
import com.rclgroup.dolphin.web.igm.vo.sce.ItnrySCE;
import com.rclgroup.dolphin.web.igm.vo.scx.ItnrySCX;
import com.rclgroup.dolphin.web.igm.vo.sdm.ItnrySDM;

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
	
	public static final String SQL_RCL_GET_ITNRY_DATA = "select cam.POINT_NAME, dex.dn_load_port,cam.PK_POINT_CODE,dex.DN_DISCHARGE_PORT as discharge_port ,"
			+ "(select came.POINT_NAME from rcltbls.cam_point came "
			+ " where camE.PK_POINT_CODE = dex.DN_DISCHARGE_PORT  ) as point_name_2  from rcltbls.cam_point cam inner join "
			+ "RCLTBLS.DEX_BL_ROUTING dex on cam.PK_POINT_CODE = dex.DN_load_port  where "
			+ "dex.TRANSPORT_MODE not in ('R','T') and dex.fk_bl_no = ?  ORDER BY dex.VOYAGE_SEQ";
	
//	public static final String SQL_RCL_GET_BLID = "SELECT BLID FROM   RCLTBLS.DEX_BL_HEADER WHERE PK_BL_NO = ? ";


	public void savePersonOnBoard(List<IGMPersonOnBoardMod> personDetailes, ImportGeneralManifestUim objForm ) throws Exception;

	public void saveCrewEfect(List<IGMCrewEfctMod> crewEfctModDetailes, ImportGeneralManifestUim objForm) throws Exception;

	public void saveShipStore(List<IGMShipStoresMod> storesModDetailes, ImportGeneralManifestUim objForm) throws Exception;

	public List<IGMPersonOnBoardMod> getPersonOnBoard(Map<String, String> amapParam, String procedureName)
			throws Exception;

	public List<IGMCrewEfctMod> getCrewEffect(Map<String, String> amapParam, String procedureName) throws Exception;

	public List<IGMShipStoresMod> getShipStore(Map<String, String> amapParam, String procedureName) throws Exception;

	public void deleteCsv(	ImportGeneralManifestUim objForm ) throws Exception;
	
	public List<ItnrySDM> getItrnrySdm(String blNo, String procedureName) throws Exception;
	
	public List<ItnrySCX> getItrnryScx(String blNo, String procedureName) throws Exception;
	
	
    public List<ItnrySAM> getItrnrySam(String blNo, String procedureName) throws Exception;
	
	public List<ItnrySCE> getItrnrySce(String blNo, String procedureName) throws Exception;
	
	//public List<BlId> getBlId(String blNo, String procedureName) throws Exception;
	
	
		

}
