package com.rclgroup.dolphin.web.igm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.control.common.exception.ExceptionFactory;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcRowMapper;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.PreviousDeclaration;

public class IGMPreviousDeclarationDaoImpl extends AncestorJdbcDao implements IGMPPreviousDeclarationDao {
	@Override
	public void savePreviousDeclData(List<PreviousDeclaration> listOfPrevDeclaration,String procedureName , String blsInput) throws Exception {
		if(!CollectionUtils.isEmpty(listOfPrevDeclaration)) {
			
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(listOfPrevDeclaration);
		System.out.println("savePreviousDeclData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_PREV_DECLARATION_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);
		try {
				objSP.execute();
		}catch (Exception e) {
			// TODO: handle exception
		}
		}
	}

	@Override
	public void setPreviousDeclData(List<ImportGeneralManifestMod> listOfBL, String procedureName) throws Exception {
		System.out.println("#IGMLogger setPreviousDeclData() started..");

		Map<String, ImportGeneralManifestMod> mapBlWithContainerDetails = new HashMap<>();
		String blsInput = null;
		for (ImportGeneralManifestMod bl : listOfBL) {
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
			mapBlWithContainerDetails.put(bl.getBl(), bl);
		}

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMPreviousDeclarationMapper(), arrParam);

		/* Return Result from SP execute */
		if (blsInput == null) {
			return;
		}
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while setConsigneeData igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		List<PreviousDeclaration> listOfContainer = (List<PreviousDeclaration>) mapResult
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		if (listOfContainer != null) {
			for (PreviousDeclaration previousDeclaration : listOfContainer) {
				mapBlWithContainerDetails.get(previousDeclaration.getBlNo()).getPreviousDeclaration()
						.add(previousDeclaration);
			}

		}
		return;

	}

	/**
	 * The Class IGMPreviousDeclarationMapper.
	 */
	private class IGMPreviousDeclarationMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public PreviousDeclaration mapRow(ResultSet rs, int row) throws SQLException {
			PreviousDeclaration previousDeclaration = new PreviousDeclaration();
			previousDeclaration.setBlNo(rs.getString("BL_NO"));
			previousDeclaration.setPrevious_declaration(rs.getString("PREVIOUS_DECLARATION"));
			previousDeclaration.setSplit_indicator(rs.getString("SPLIT_INDICATOR"));
			previousDeclaration.setPrevious_pcin(rs.getString("PCIN"));
			previousDeclaration.setPrevious_mcin(rs.getString("MCIN"));
			previousDeclaration.setCsn_number(rs.getString("CSN_NO"));
			previousDeclaration.setCsn_date(rs.getString("CSN_DATE"));
			return previousDeclaration;
		}
	}

	@Override
	public void deletePreviousDeclData(List<PreviousDeclaration> previousDeclarations, String procedureName,
			String blsInput) throws JsonProcessingException {
		if(blsInput!=null) {
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(previousDeclarations);
		System.out.println("savePreviousDeclData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_PREV_DECLARATION_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();
		}
	}

	@Override
	public void saveUnfetchedPreviousDeclData( String procedureName,
			String blsInput) throws Exception {
		if(blsInput != null) {
			System.out.println("savePreviousDeclData() started");
			String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
					
					{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) blsInput },
					{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);
		}
		
	}

}
