package com.rclgroup.dolphin.web.igm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.control.common.exception.ExceptionFactory;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcRowMapper;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;

public class PersonOnBoardDaoImpl extends AncestorJdbcDao implements PersonOnBoardDao {

	public static final String SQL_SAVE_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_CONTAINOR_NEW";

	/** The Constant KEY_IGM_CONTAINER_DTLS. */
	public static final String KEY_IGM_CONTAINER_DTLS = "P_I_V_CONTAINER_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";

	@Override
	public void savePersonOnBoard(List<IGMPersonOnBoardMod> personDetailes, String blsInput) throws Exception {
		// TODO Auto-generated method stub

		ObjectMapper mapper = new ObjectMapper();
		String person = mapper.writeValueAsString(personDetailes);
		System.out.println("savePersonOnBoard() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONTAINER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, person }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), RCL_IGM_SAVE_PERSON_DETAILES, arrParam);

		objSP.execute();

	}

	/*
	 * For delete csv data
	 */
	@Override
	public void deleteCsv(	ImportGeneralManifestUim objForm ) throws Exception {
		System.out.println("deleteCsv() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getBl() },
				{ "P_I_V_VESSEL", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getVessel() },
				{ "P_I_V_VOYAGE", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getVoyage() },
				{ "P_I_V_POD", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getPod()},
				{ "P_I_V_CSV_Check", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getCheckCSV()}};
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), RCL_IGM_GET_DELETE_CSV_DATA, arrParam);
		objSP.execute();
	}

	@Override
	public void saveCrewEfect(List<IGMCrewEfctMod> personDetailes, String blsInput) throws Exception {
		// TODO Auto-generated method stub

		ObjectMapper mapper = new ObjectMapper();
		String person = mapper.writeValueAsString(personDetailes);
		System.out.println("saveCrewEfect() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONTAINER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, person }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), RCL_IGM_SAVE_CREW_EFFECT_FORMATE,
				arrParam);

		objSP.execute();

	}

	public void saveShipStore(List<IGMShipStoresMod> personDetailes, String blsInput) throws Exception {
		// TODO Auto-generated method stub

		ObjectMapper mapper = new ObjectMapper();
		String person = mapper.writeValueAsString(personDetailes);
		System.out.println("saveShipStore() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONTAINER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, person }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), RCL_IGM_SAVE_SHIP_STORE_FORMAT, arrParam);

		objSP.execute();

	}

	@Override
	public List<IGMPersonOnBoardMod> getPersonOnBoard(Map<String, String> amapParam, String procedureName)
			throws Exception {

		System.out.println("#IGMLogger getPersonOnBoard() started..");

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_VESSEL", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_VESSEL") },
				{ "P_I_V_VOYAGE", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_VOYAGE") },
				{ "P_I_V_POD", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_POD") },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};
		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMPersonOnBoardMapper(), arrParam);

		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getPersonOnBoard from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}

		List<IGMPersonOnBoardMod> listOf = (List<IGMPersonOnBoardMod>) mapResult
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		return listOf;
	}

	/**
	 * The Class IGMPersonOnBoardMapper.
	 */
	private class IGMPersonOnBoardMapper extends JdbcRowMapper {

		public IGMPersonOnBoardMod mapRow(ResultSet rs, int row) throws SQLException {
			IGMPersonOnBoardMod personOnBoardMod = new IGMPersonOnBoardMod();
			personOnBoardMod.setSeqNo(row+1);
			personOnBoardMod.setBlNo(rs.getString("BL_NO_PK"));
			personOnBoardMod.setPrsnTypCdd(rs.getString("TYPE_CODE"));
			personOnBoardMod.setPrsnFamilyName(rs.getString("FAMILY_NAME"));
			personOnBoardMod.setPrsnGivenName(rs.getString("GIVEN_NAME"));
			personOnBoardMod.setPrsnNatnltyCdd(rs.getString("NATIONALITY_CODE"));
			personOnBoardMod.setPsngrInTransitIndctr(rs.getString("INTRANSIT_INDICATOR"));
			personOnBoardMod.setCrewmemberRankOrRatingCdd(rs.getString("CREW_MEMBER_RANKORRATING_CODE"));
			personOnBoardMod.setCrewmemberRankOrRating(rs.getString("CREW_MEMBER_RANKOR_RATING"));
			personOnBoardMod.setPsngrPrtOfEmbrktnCdd(rs.getString("PORTOFEMBARKATIONCODE"));
			personOnBoardMod.setPsngrPrtOfDsmbrktnCdd(rs.getString("PORT_OF_DISEMBARKATION_CODE"));
			personOnBoardMod.setPrsnGenderCdd(rs.getString("GENDER_CODE"));
			personOnBoardMod.setPrsnDtOfBirth(rs.getString("DATE_OF_BIRTH"));
			personOnBoardMod.setPrsnPlaceOfBirthName(rs.getString("PLACE_OF_BIRTHNAME"));
			personOnBoardMod.setPrsnCntryOfBirthCdd(rs.getString("COUNTRY_OF_BIRTH_CODE"));
			personOnBoardMod.setPrsnIdDocExpiryDt(rs.getString("IDENTITY_DOC_EXPIRY_DATE"));
			personOnBoardMod
					.setPrsnIdOrTravelDocIssuingNationCdd(rs.getString("IDENTITY_OR_TRAVEL_DOC_ISSUING_NATION_CODE"));
			personOnBoardMod.setPrsnIdOrTravelDocNmbr(rs.getString("IDENTITY_OR_TRAVEL_DOC_NMBR"));
			personOnBoardMod.setPrsnIdOrTravelDocTypCdd(rs.getString("IDENTITY_OR_TRAVEL_DOC_TYPE_CODE"));
			personOnBoardMod.setPsngrPrtOfEmbrktnName(rs.getString("PORT_OF_EMBARKATION_NAME"));
			personOnBoardMod.setVisa(rs.getString("VISA"));
			personOnBoardMod.setPnrNumber(rs.getString("PNR_NUMBER"));
			personOnBoardMod.setPsngrPrtOfDsmbrktnName(rs.getString("PORT_OF_DISEMBARKATION_NAME"));
			personOnBoardMod.setVessel(rs.getString("VESSEL"));
			personOnBoardMod.setVoyage(rs.getString("VOYAGE"));
			personOnBoardMod.setPod(rs.getString("POD"));
			return personOnBoardMod;
		}
	}

	@Override
	public List<IGMCrewEfctMod> getCrewEffect(Map<String, String> amapParam, String procedureName) throws Exception {

		System.out.println("#IGMLogger getCrewEffect() started..");

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_VESSEL", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_VESSEL") },
				{ "P_I_V_VOYAGE", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_VOYAGE") },
				{ "P_I_V_POD", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_POD") },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};
		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, new IGMCrewEffectMapper(),
				arrParam);

		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getCrewEffect from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}

		List<IGMCrewEfctMod> listOf = (List<IGMCrewEfctMod>) mapResult.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		return listOf;
	}

	/**
	 * The Class IGMCrewEffectMapper.
	 */
	private class IGMCrewEffectMapper extends JdbcRowMapper {

		public IGMCrewEfctMod mapRow(ResultSet rs, int row) throws SQLException {
			IGMCrewEfctMod crewEffectMod = new IGMCrewEfctMod();

			crewEffectMod.setBlNo(rs.getString("BL_NO_PK"));// BL_NO_PK
			crewEffectMod.setCrewEfctDescCdd(rs.getString("DESCRIPTION_CODE"));// DESCRIPTION_CODE
			crewEffectMod.setCrewEfctQntyCdOnbrd(rs.getString("QUANTITY_ON_BOARD_CODE"));// QUANTITY_ON_BOARD_CODE
			crewEffectMod.setCrewEfctQntyOnbrd(rs.getString("QUANTITY_ON_BOARD"));// QUANTITY_ON_BOARD
			crewEffectMod.setCrewEfctsDesc(rs.getString("DESCRIPTION"));// DESCRIPTION
			crewEffectMod.setCrewEfctsVsslSeqNmbr(rs.getString("VESSEL_SRNO"));// VESSEL_SRNO
			crewEffectMod.setVessel(rs.getString("VESSEL"));// VESSEL
			crewEffectMod.setVoyage(rs.getString("VOYAGE"));// VOYAGE
			crewEffectMod.setPod(rs.getString("POD"));// POD
			crewEffectMod.setSequenceNo(rs.getString("sequence_No")); 
			crewEffectMod.setPersonOnBoardSequenceNo(rs.getString("Person_on_Board_sequence_no"));// POD

			return crewEffectMod;
		}
	}

	@Override
	public List<IGMShipStoresMod> getShipStore(Map<String, String> amapParam, String procedureName) throws Exception {

		System.out.println("#IGMLogger getShipStore() started..");

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_VESSEL", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_VESSEL") },
				{ "P_I_V_VOYAGE", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_VOYAGE") },
				{ "P_I_V_POD", BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get("P_I_V_POD") },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};
		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, new IGMShipStoreMapper(),
				arrParam);

		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getShipStore from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}

		List<IGMShipStoresMod> listOf = (List<IGMShipStoresMod>) mapResult
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		return listOf;
	}

	/**
	 * The Class IGMShipStoreMapper.
	 */
	private class IGMShipStoreMapper extends JdbcRowMapper {

		public IGMShipStoresMod mapRow(ResultSet rs, int row) throws SQLException {
			IGMShipStoresMod shipStoresMod = new IGMShipStoresMod();

			shipStoresMod.setBlNo(rs.getString("BL_NO_PK"));
			shipStoresMod.setArticleNameCdd(rs.getString("ARTICLE_NAME_CODE"));
			shipStoresMod.setArticleNameText(rs.getString("ARTICE"));
			shipStoresMod.setLocOnbrdText(rs.getString("LOCATION"));
			shipStoresMod.setQntyCdOnbrd(rs.getString("QUANTITY_ON_BOARD_CODE"));
			shipStoresMod.setQntyOnbrd(rs.getString("QUANTITY_ON_BOARD"));
			shipStoresMod.setVessel(rs.getString("VESSEL"));
			shipStoresMod.setVoyage(rs.getString("VOYAGE"));
			shipStoresMod.setPod(rs.getString("POD"));
			shipStoresMod.setVesselSrno(rs.getString("VESSEL_SRNO"));
			return shipStoresMod;
		}
	}

}