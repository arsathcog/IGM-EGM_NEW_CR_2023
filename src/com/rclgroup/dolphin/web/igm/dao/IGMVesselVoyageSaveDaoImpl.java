package com.rclgroup.dolphin.web.igm.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public class IGMVesselVoyageSaveDaoImpl extends AncestorJdbcDao implements IGMVesselVoyageSaveDao {

	public void saveVesselVoyageData(ImportGeneralManifestMod objVesselVoyage,String prsedureName) throws Exception {

		String blsInput = objVesselVoyage.getBl();
		 
		
		objVesselVoyage.setContainerDetailes(null);
		objVesselVoyage.setMarksNumber(null);
		objVesselVoyage.setConsignee(null);
		objVesselVoyage.setConsigner(null);
		objVesselVoyage.setNotifyParty(null);
		objVesselVoyage.setPreviousDeclaration(null);
		objVesselVoyage.setNotifyPartyTwo(null);

		ObjectMapper mapper = new ObjectMapper();
		String vesselVoyage = mapper.writeValueAsString(objVesselVoyage);
		System.out.println("saveVesselVoyageData() started");
		JdbcStoredProcedure objSP = null; 
		if(prsedureName.equals(RCL_IGM_SAVE_VESSEL_VOYOAGE)) {
			String[][] arrParam = { { "P_I_V_POD", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getPod()},
									{ "P_I_V_SERVICE", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getService() },
									{ "P_I_V_VESSEL", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getVessel() },
									{ "P_I_V_VOYAGE", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getVoyage() },
									{ "P_I_V_POD_TERMINAL", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getPodTerminal() },
									{ KEY_IGM_VESSEL_VOYOAGE_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, vesselVoyage }, };
			objSP = new JdbcStoredProcedure(getDataSource(), prsedureName, arrParam);

		}else {
			String[][] arrParam = { { "P_I_V_POL", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getPol()},
						{ "P_I_V_SERVICE", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getService() },
						{ "P_I_V_VESSEL", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getVessel() },
						{ "P_I_V_VOYAGE", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getVoyage() },
						{ "P_I_V_POL_TERMINAL", BLANK + ORACLE_VARCHAR, PARAM_IN, objVesselVoyage.getPolTerminal() },
						{ KEY_IGM_VESSEL_VOYOAGE_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, vesselVoyage }, };
			objSP = new JdbcStoredProcedure(getDataSource(), prsedureName, arrParam);

		}
		 
		objSP.execute();

	}

	public void updateIgmdetails(ImportGeneralManifestUim objForm) throws Exception {
		System.out.println("updateIgmdetails() started");
		
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getBl() } ,
				 { "P_I_V_IGM_NUMBER", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getIgmNo() },
				 { "P_I_V_IGM_DATE", BLANK + ORACLE_VARCHAR, PARAM_IN, objForm.getIgmDate() }
				 };
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), RCL_IGM_APPLY_IGM, arrParam);
		objSP.execute();
		 System.out.println("updateIgmdetails() end");
		 
	}
}
