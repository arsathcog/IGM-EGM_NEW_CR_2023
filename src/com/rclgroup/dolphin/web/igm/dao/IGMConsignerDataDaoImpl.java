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

import com.rclgroup.dolphin.web.igm.vo.Consigner;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public class IGMConsignerDataDaoImpl extends AncestorJdbcDao implements IGMConsignerDataDao {

	public void saveConsignerData(List<Consigner> listOfConsigner, String blsInput, String procedureName)
			throws Exception {
		if (!CollectionUtils.isEmpty(listOfConsigner)) {

			ObjectMapper mapper = new ObjectMapper();
			String containeer = mapper.writeValueAsString(listOfConsigner);
			System.out.println("saveConsigneeData() started");
			String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
					{ KEY_IGM_CONSIGNER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

			objSP.execute();
		}
	}
	 
		public void setConsignerData(List<ImportGeneralManifestMod> listOfBL,
				String procedureName) throws Exception {
			Map<String, ImportGeneralManifestMod> mapBlWithContainerDetails = new HashMap<String, ImportGeneralManifestMod>();
			String blsInput = null;
			for (ImportGeneralManifestMod bl : listOfBL) {
				if (blsInput == null)
					blsInput = "'" + bl.getBl() + "'";
				else
					blsInput += ",'" + bl.getBl() + "'";
				mapBlWithContainerDetails.put(bl.getBl(), bl);
			}
			// TODO Auto-generated method stub

			System.out.println("#IGMLogger setConsigneeData() started..");

			

			String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
					{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
					{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

			};

			/* stored procedure object */
			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
					new IGMConsigneeMapper(), arrParam);

			/* Return Result from SP execute */
			if(blsInput== null) {
				return ;
			}
			Map mapResult = objSP.execute();

			/* Stores return error code from SP */
			String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
			/* If return error code is Failure, throw Business Exception */
			if (strRetError != null && isErrorCode(strRetError)) {
				System.out.println("Error while setConsigneeData igm data from DB : " + strRetError);
				throw ExceptionFactory.createApplicationException(strRetError);
			}
			List<Consigner> listOfContainer = (List<Consigner>) mapResult
					.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

			if (listOfContainer != null) {
				for (Consigner consigner : listOfContainer) {
					mapBlWithContainerDetails.get(consigner.getBlNO()).getConsigner().add(consigner);
				}

			}
			return ;

		}
		
		
		/**
		 * The Class ImportGeneralManifestRowMapper.
		 */
		private class IGMConsigneeMapper extends JdbcRowMapper {

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
			 */

			public Consigner mapRow(ResultSet rs, int row) throws SQLException {
				// System.out.println("#IGMLogger mapRow() started..");
				//ContainerDetails contDetails = new ContainerDetails();
				Consigner consigner = new Consigner();
				consigner.setBlNO(rs.getString("FK_BL_NO"));
				consigner.setCustomerCode(rs.getString("CONSIGNER_CODE"));
				consigner.setCustomerName(rs.getString("CONSIGNER_NAME"));
				consigner.setAddressLine1(rs.getString("ADDRESS_LINE_1"));
				consigner.setAddressLine2(rs.getString("ADDRESS_LINE_2"));
				consigner.setAddressLine3(rs.getString("ADDRESS_LINE_3"));
				consigner.setAddressLine4(rs.getString("ADDRESS_LINE_4"));
				consigner.setCity(rs.getString("CITY"));
				consigner.setState(rs.getString("STATE"));
				consigner.setCountryCode(rs.getString("DN_COUNTRY_CODE"));
				consigner.setZip(rs.getString("ZIP"));	
				consigner.setStateName(rs.getString("STATE_NAME"));	
				return consigner;
			}
		}


		@Override
		public void deleteConsignerData(List<Consigner> consigner, String blsInput, String procedureName) throws JsonProcessingException {
			if(blsInput!=null) {
			ObjectMapper mapper = new ObjectMapper();
			String containeer = mapper.writeValueAsString(consigner);
			System.out.println("saveConsigneeData() started");
			String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
					{ KEY_IGM_CONSIGNER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

			objSP.execute();
			}
		}

		@Override
		public void saveUnfetchedConsignerData(String blsInput, String procedureName)
				throws Exception {
			if(blsInput!=null) {
				System.out.println("saveUnfetchedConsigneeData() started");
				String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
						{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput }};

				JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

				objSP.execute();
			
			}
		}


}