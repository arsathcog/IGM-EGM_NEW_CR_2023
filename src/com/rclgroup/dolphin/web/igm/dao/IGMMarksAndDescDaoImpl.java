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
import com.rclgroup.dolphin.web.igm.vo.MarksNumber;

public class IGMMarksAndDescDaoImpl extends AncestorJdbcDao implements IGMMarksAndDescDao {

	public void saveMarkDescData(List<MarksNumber> listOfMarks, String blsInput, String procedureName)
			throws Exception {
		if (!CollectionUtils.isEmpty(listOfMarks)) {

			ObjectMapper mapper = new ObjectMapper();
			String containeer = mapper.writeValueAsString(listOfMarks);
			System.out.println("saveMarkDescData() started");
			String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
					{ KEY_IGM_MARKS_AND_DESC_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

			objSP.execute();
		}
	}

	@Override
	public void setMarksDescriptionData(List<ImportGeneralManifestMod> listOfBL, String procedureName)
			throws Exception {
		// TODO Auto-generated method stub


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
				new IGMMarksNumberMapper(), arrParam);

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
		List<MarksNumber> listOfContainer = (List<MarksNumber>) mapResult
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		if (listOfContainer != null) {
			for (MarksNumber marksNumber : listOfContainer) {
				mapBlWithContainerDetails.get(marksNumber.getBlNO()).getMarksNumber().add(marksNumber);
			}

		}
		return ;

	}
	
	
	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class IGMMarksNumberMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public MarksNumber mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			//ContainerDetails contDetails = new ContainerDetails();
			MarksNumber marksnumber = new MarksNumber();
			marksnumber.setBlNO(rs.getString("FK_BL_NO"));
			
//			marksnumber.setMarksNumbers(rs.getString("MARKS_NO"));
			String marksNo = "";
			String marksNoOnPkgs ="";
             String s2 = "(S)";
             try {
             if(rs.getString("MARKS_NO")!= null || !rs.getString("MARKS_NO").equals("null")) {
//            	 int i = rs.getString("MARKS_NO").lastIndexOf(s2);
     			if(rs.getString("MARKS_NO").lastIndexOf("(S)") != -1)
     			{
     				 int i = rs.getString("MARKS_NO").lastIndexOf(s2);
//     				System.out.println(rs.getString("MARKS_NO").substring(i+3));
//     				 marksnumber.setMarksNumbers(rs.getString("MARKS_NO").substring(i+3));
     				marksNo = rs.getString("MARKS_NO").substring(i+3);
     				if(marksNo.contains("\n")) {
     					marksNoOnPkgs =  marksNo.replaceAll("\\n+", "");
     					 marksnumber.setMarksNumbers(marksNoOnPkgs);
     				}else {
     					 marksnumber.setMarksNumbers(marksNo);
     				}
     				if(marksnumber.getMarksNumbers().contains("\r")) {
     					marksNoOnPkgs =  marksnumber.getMarksNumbers().replaceAll("\\r+", "");
     					 marksnumber.setMarksNumbers(marksNoOnPkgs);
     				}else {
     					marksnumber.setMarksNumbers(marksNoOnPkgs);
     				}
     			}else {
     				marksnumber.setMarksNumbers(rs.getString("MARKS_NO"));
     			}
     			
            }else {
            	marksnumber.setMarksNumbers(rs.getString("MARKS_NO"));
            }
             }catch (Exception e) {
            	 marksnumber.setMarksNumbers(rs.getString("MARKS_NO"));
			}
             String description = "";
             if (rs.getString("DESCRIPTION").contains("\r")) {
            	 description = rs.getString("DESCRIPTION").replaceAll("\\r+", "");
            	 marksnumber.setDescription(description);
             }else {
            	 marksnumber.setDescription(rs.getString("DESCRIPTION"));
             }  
             
             if(rs.getString("DESCRIPTION").contains("\n") || description.contains("\n")){
            	  description  = rs.getString("DESCRIPTION").replaceAll("\\n+", "");
            	  marksnumber.setDescription(description);
             }else {
            	 marksnumber.setDescription(rs.getString("DESCRIPTION"));
             }  
			marksnumber.setBldate(rs.getString("bldate"));
			if(rs.getString("REMARK")==null || rs.getString("REMARK").equals("")) {
				String remarkTemp="";
				remarkTemp=remarkTemp+rs.getString("FK_BL_NO")+"\r\n";
				remarkTemp=remarkTemp+rs.getString("bldate")+"\r\n";
				marksnumber.setDroRemarks(remarkTemp);
			}else {
				marksnumber.setDroRemarks(rs.getString("REMARK"));
			}
			return marksnumber;
		}
	}


	@Override
	public void deleteMarkDescData(List<MarksNumber> marksNumber, String blsInput, String procedureName) throws JsonProcessingException {
		if(blsInput!=null) {
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(marksNumber);
		System.out.println("saveMarkDescData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_MARKS_AND_DESC_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				arrParam);

		objSP.execute();
		}
	}

	@Override
	public void saveUnfetchedMarkDescData(String blsInput, String procedureName)
			throws Exception {
		System.out.println("saveUnfetchedMarkDescData() started");
		if(blsInput!=null) {
			String[][] arrParam = {{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput }};
			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);
			objSP.execute();
		}
	}
		


}
