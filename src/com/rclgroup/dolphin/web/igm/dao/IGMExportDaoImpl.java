
package com.rclgroup.dolphin.web.igm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.niit.control.common.exception.BusinessException;
import com.niit.control.common.exception.ExceptionFactory;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcRowMapper;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestExportMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

/**
 * The Class ImportGeneralManifestDaoImpl.
 */
public class IGMExportDaoImpl extends AncestorJdbcDao implements IGMExportDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rclgroup.dolphin.ezl.web.ell.dao.ImportGeneralManifestDao#getIGMData(java
	 * .util.Map)
	 */
	@Override
	public Map getIGMData(Map amapParam) throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getIGMData() started..");
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), SQL_GET_IGM_EXPORT_DATA,
				new ImportGeneralManifestRowMapperNew(), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		System.out.println("#IGMLogger END OF getIGMData() ");
		return mapResult;
	}

	private class ImportGeneralManifestRowMapperNew extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int row) throws SQLException {
			ImportGeneralManifestExportMod objMod = new ImportGeneralManifestExportMod();

			objMod.setService(rs.getString("SERVICE"));
			//objMod.setBl(rs.getString("BL_NO"));
			objMod.setPod(rs.getString("POD"));
			objMod.setPol(rs.getString("POL"));
			objMod.setPodTerminal(rs.getString("POD_TERMINAL"));
			objMod.setPolTerminal(rs.getString("POL_TERMINAL"));
			objMod.setVessel(rs.getString("VESSEL"));
			objMod.setVoyage(rs.getString("VOYAGE"));
			objMod.setTerminal(rs.getString("TERMINAL"));
			objMod.setCustomTerminalCode(rs.getString("CUSTOM_TERMINAL_CODE"));
			objMod.setDel(rs.getString("DEL_VLS"));
			objMod.setDepot(rs.getString("DEPOT_VLS"));
		
			return objMod;
		}
	}


	

}