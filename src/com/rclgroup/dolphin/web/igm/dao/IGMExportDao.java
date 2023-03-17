package com.rclgroup.dolphin.web.igm.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.niit.control.common.exception.BusinessException;

/**
 * The Interface ImportGeneralManifestDao.
 */
public interface IGMExportDao {

	/** The Constant SQL_GET_IGM_DATA. */
	public static final String SQL_GET_IGM_EXPORT_DATA = "RCL_IGM_EXPORT_INFO.RCL_IGM_EXPORT_GET_DATA";
	/** The Constant SQL_GET_IGM_DATA. */
	public static final String SQL_GET_SAVED_IGM_VESSLE_VOYAGE_DATA = "RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT.RCL_IGM_GET_SAVED_VESEEL_VOYOGE_DATA";

	/** The Constant SQL_GET_IGM_DATA. */
	public static final String SQL_GET_IGM_BL_MSTR_DATA = "RCL_IGM_BL_INFO.RCL_IGM_GET_MASTER_BL_DATA";// janmejaya

	public static final String SQL_GET_IGM_BL_SAVE_DATA = "RCL_IGM_BL_INFO.RCL_IGM_GET_SAVE_BL_DATA";// janmejaya
	/** The Constant SQL_GET_IGM_DATA. */
	
	/** The Constant SQL_GET_IGM_DATA. */
	public static final String SQL_GET_IGM_BL_MSTR_DATA_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_MASTER_BL_DATA";// janmejaya

	public static final String SQL_GET_IGM_BL_SAVE_DATA_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_SAVE_BL_DATA";// janmejaya
	/** The Constant SQL_GET_IGM_DATA. */
	
	public static final String SQL_GET_IGM_BL_MASTER_SAVE_DATA_EXPORT = "RCL_IGM_BL_INFO_EXPORT.RCL_IGM_GET_BL_DATA_MASTER_SAVE_DATA";// janmejaya
	
	public static final String SQL_GET_IGM_VESSLE_VOYAGE_DATA = "RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT.RCL_IGM_VESSLE_VOYAGE_GET_MASTER_DATA";//
	
	/** The Constant SQL_GET_IGM_DATA. */
	public static final String SQL_GET_REFRESH_IGM_DATA = "RCL_IGM_INFO.RCL_IGM_GET_REFRESH_DATA";

	/** The Constant SQL_SAVE_IGM_DATA. */
	public static final String SQL_SAVE_IGM_DATA = "RCL_IGM_INFO_NEW.RCL_IGM_SAVE_BL_DATA";// Why this?


	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA_JOB = "P_O_REFIGMTABFIND_JOB";

	/** The Constant KEY_IGM_BL. */
	public static final String KEY_IGM_BL = "P_I_V_BL";

	/** The Constant KEY_IGM_POD. */
	public static final String KEY_IGM_POD = "P_I_V_POD";

	/** The Constant KEY_IGM_SERVICE. */
	public static final String KEY_IGM_SERVICE = "P_I_V_SERVICE";

	/** The Constant KEY_IGM_VESSEL. */
	public static final String KEY_IGM_VESSEL = "P_I_V_VESSEL";

	/** The Constant KEY_IGM_VOYAGE. */
	public static final String KEY_IGM_VOYAGE = "P_I_V_VOYAGE";

	/** The Constant KEY_IGM_POD_TERMINAL. */
	public static final String KEY_IGM_POD_TERMINAL = "P_I_V_POD_TERMINAL";

	/** The Constant KEY_IGM_FROM_DATE. */
	public static final String KEY_IGM_FROM_DATE = "P_I_V_FROM_DATE";

	/** The Constant KEY_IGM_TO_DATE. */
	public static final String KEY_IGM_TO_DATE = "P_I_V_TO_DATE";

	/** The Constant KEY_IGM_BL_STATUS. */
	public static final String KEY_IGM_BL_STATUS = "P_I_V_BL_STATUS";

	/** The Constant KEY_IGM_POL. */
	public static final String KEY_IGM_POL = "P_I_V_POL";

	/** The Constant KEY_IGM_DEL. */
	public static final String KEY_IGM_DEL = "P_I_V_DEL";

	/** The Constant KEY_IGM_DEPOT. */
	public static final String KEY_IGM_DEPOT = "P_I_V_DEPOT";

	/** The Constant KEY_IGM_POL_TERMINAL. */
	public static final String KEY_IGM_POL_TERMINAL = "P_I_V_POL_TERMINAL";

	/** The Constant KEY_IGM_DIRECTION. */
	public static final String KEY_IGM_DIRECTION = "P_I_V_DIRECTION";

	/** The Constant KEY_IGM_TOT_REC. */
	public static final String KEY_IGM_TOT_REC = "P_O_V_TOT_REC";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";

	/** The Constant KEY_IGM_PORT. */
	public static final String KEY_IGM_PORT = "P_I_V_PORT";

	/** The Constant KEY_IGM_TERMINAL. */
	public static final String KEY_IGM_TERMINAL = "P_I_V_TERMINAL";

	/** The Constant KEY_IGM_NEW_VESSEL. */
	public static final String KEY_IGM_NEW_VESSEL = "P_I_V_NEW_VESSEL";

	/** The Constant KEY_IGM_NEW_VOYAGE. */
	public static final String KEY_IGM_NEW_VOYAGE = "P_I_V_NEW_VOYAGE";

	/** The Constant KEY_IGM_SERIAL_NUMBER. */

	/**
	 * Gets the IGM data.
	 *
	 * @param amapParam the amap param
	 * @return the IGM data
	 * @throws BusinessException   the business exception
	 * @throws DataAccessException the data access exception
	 */
	public Map getIGMData(Map amapParam) throws BusinessException, DataAccessException;

}
