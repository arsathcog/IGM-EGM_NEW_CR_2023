
package com.rclgroup.dolphin.web.igm.action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.niit.control.common.exception.BusinessException;
import com.niit.control.web.action.BaseAction;
import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.dao.IGMBLDataDao;
import com.rclgroup.dolphin.web.igm.dao.IGMConsigneeDataDao;
import com.rclgroup.dolphin.web.igm.dao.IGMConsignerDataDao;
import com.rclgroup.dolphin.web.igm.dao.IGMContainerDao;
import com.rclgroup.dolphin.web.igm.dao.IGMContainerDaoImpl;
import com.rclgroup.dolphin.web.igm.dao.IGMDaoNew;
import com.rclgroup.dolphin.web.igm.dao.IGMMarksAndDescDao;
import com.rclgroup.dolphin.web.igm.dao.IGMNodifyPartyDao;
import com.rclgroup.dolphin.web.igm.dao.IGMPPreviousDeclarationDao;
import com.rclgroup.dolphin.web.igm.dao.IGMPreviousDeclarationDaoImpl;
import com.rclgroup.dolphin.web.igm.dao.IGMVesselVoyageSaveDao;
import com.rclgroup.dolphin.web.igm.dao.ImportGeneralManifestDao;
import com.rclgroup.dolphin.web.igm.dao.PersonOnBoardDao;
import com.rclgroup.dolphin.web.igm.jsonUtil.CreatingJSON;
import com.rclgroup.dolphin.web.igm.vo.BlId;
import com.rclgroup.dolphin.web.igm.vo.Consignee;
import com.rclgroup.dolphin.web.igm.vo.Consigner;
import com.rclgroup.dolphin.web.igm.vo.ContainerDetails;
import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestInput;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestResultSet;
import com.rclgroup.dolphin.web.igm.vo.MarksNumber;
import com.rclgroup.dolphin.web.igm.vo.NotifyParty;
import com.rclgroup.dolphin.web.igm.vo.PortMod;
import com.rclgroup.dolphin.web.igm.vo.PreviousDeclaration;
import com.rclgroup.dolphin.web.igm.vo.sam.ItnrySAM;
import com.rclgroup.dolphin.web.igm.vo.sce.ItnrySCE;
import com.rclgroup.dolphin.web.igm.vo.scx.ItnrySCX;
import com.rclgroup.dolphin.web.igm.vo.sdm.ItnrySDM;

public class IGMNewScreenSvc extends BaseAction implements Runnable {
	/** The Constant DAO_BEAN_ID Start. */
	private static final String DAO_BEAN_ID = "iGMDaoNew";

	private static final String DAO_BEAN_BL_ID = "igmBLDao";

	private static final String DAO_BEAN_PERSON = "personOnBoardDao";

	private static final String DAO_BEAN_VESSEL_VOYOGE_ID = "igmVesseVoyogeDao";

	private static final String DAO_BEAN_EXPORT = "iGMExportDaoImpl";

	private static final String DAO_BEAN_CONSIGNEE_ID = "igmConsigneeDao";

	private static final String DAO_BEAN_CONSIGNER_ID = "igmConsignerDao";

	private static final String DAO_BEAN_MARK_AND_DESC_ID = "igmMarksDao";

	private static final String DAO_BEAN_NODIFY_ID = "igmNodifyDao";

	private static final String DAO_BEAN_PREV_DECLARATION_DESC_ID = "igmPrevDeclarDao";

	private static final String DAO_BEAN_CONTAINER_ID = "igmContainerDao";
	/** The Constant DAO_BEAN_ID End. */

	/** Define Parameter Start. */
	private static final String GET_VESSEL_VOYAGE_DATA = "vesselVoyagData";

	private static final String VESSEL_VOYAGE_SAVE = "vesselVoyageSave";

	private static final String BL_DATA_SAVE = "blDataSave";

	private static final String UPLOAD = "csvFilePrsnOnBord";

	private static final String DELETE_CSV = "deleteCsvFile";

	private static final String DOWNLOAD_CSV = "downloadCsvFile";

	private static final String UPDATE_IGM_DETAILS = "updateIgmDetails";

	private static final String PORT_DETAILS = "portData";

	private static final String GET_PERSON_ONBOARD = "getPersonOnBoard";

	private static final String GET_CREW_EFFECT = "getCrewEffect";

	private static final String GET_SHIP_STOR = "getShipStor";

	private static final String GET_CAROGO_DETAILS = "getCarogoDetails";

	private static final String GET_CONTAINER_DETAILS = "getContainerDetails";

	private static final String GET_SELECT_ALL_OPTION = "getSelectAllOption";

	private static final String UPLOAD_GET_ACK = "ackFileUpload";

	private static final String GET_MORE = "getCarogoDetailsAndMore";

	private static final String GET_STOWAGE_IMPORT = "getStowageImport";

	private static final String GET_HBL_LIST = "getHblList";

	private static final String GET_CAROGODETAILS_HBL = "getCarogoDetailsHBL";

	/** Define Parameter End. */

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String strAction = mapping.getParameter();

		if (ONLOAD.equals(strAction)) {
			return onload(mapping, form, request, response);
		} else if (SEARCH.equals(strAction)) {
			return onSearch(mapping, form, request, response);
		} else if (GET_VESSEL_VOYAGE_DATA.equals(strAction)) {
			return getVesselVoyagData(mapping, form, request, response);
		} else if (VESSEL_VOYAGE_SAVE.equals(strAction)) {
			return vesselVoyagSave(mapping, form, request, response);
		} else if (BL_DATA_SAVE.equals(strAction)) {
			return blDataSave(mapping, form, request, response);
		} else if (DOWNLOAD.equals(strAction)) {
			return downloadJson(mapping, form, request, response);
		} else if (UPLOAD.equals(strAction)) {
			return onCsvUpload(mapping, form, request, response);
		} else if (DELETE_CSV.equals(strAction)) {
			return deleteCsvData(mapping, form, request, response);
		} else if (UPDATE_IGM_DETAILS.equals(strAction)) {
			return applyIgm(mapping, form, request, response);
		} else if (DOWNLOAD_CSV.equals(strAction)) {
			return downloadCSV(mapping, form, request, response);
		} else if (PORT_DETAILS.equals(strAction)) {
			return getPortDetails(mapping, form, request, response);
		} else if (GET_PERSON_ONBOARD.equals(strAction)) {
			return getPersonOnboard(mapping, form, request, response);
		} else if (GET_CREW_EFFECT.equals(strAction)) {
			return getCrewEffect(mapping, form, request, response);
		} else if (GET_SHIP_STOR.equals(strAction)) {
			return getShipStor(mapping, form, request, response);
		} else if (GET_CAROGO_DETAILS.equals(strAction)) {
			return getCarogoDetails(mapping, form, request, response);
		} else if (GET_CONTAINER_DETAILS.equals(strAction)) {
			return getContainerDetails(mapping, form, request, response);
		} else if (GET_SELECT_ALL_OPTION.equals(strAction)) {
			return getSelectAllOption(mapping, form, request, response);
		} else if (UPLOAD_GET_ACK.equals(strAction)) {
			return getUploadAckFile(mapping, form, request, response);
		} else if (GET_MORE.equals(strAction)) {
			return getCarogoDetailsAndMore(mapping, form, request, response);
		} else if (GET_STOWAGE_IMPORT.equals(strAction)) {
			return getStowageImport(mapping, form, request, response);
		} else if (GET_HBL_LIST.equals(strAction)) {
			return getHblListImport(mapping, form, request, response);
		} else if (GET_CAROGODETAILS_HBL.equals(strAction)) {
			return getCarogoDetailsHBL(mapping, form, request, response);
		}
		return null;
	}

	private ActionForward getCarogoDetailsHBL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  throws Exception{
		System.out.println("IGMNewScreenSvc getCarogoDetailsHBL() called.");
	return null;
	}

	private ActionForward getHblListImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException, IOException {

		System.out.println("IGMNewScreenSvc getHblListImport() Called[Started]");

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map mapResult = new HashMap<>();

		if (null != objForm.getItemNumber() && !objForm.getItemNumber().equals("")) {
			mapResult = objDao.getHblListJdbc(objForm, "saved");
		} else {
			mapResult = objDao.getHblListJdbc(objForm, "master");
		}
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("hblDetails", mapResult.get("P_O_REFIGMTABFIND"));
		jsonObj.write(response.getWriter());
		return null;
	}

	private ActionForward getStowageImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws DataAccessException, BusinessException, IOException {

		System.out.println("getStowageImport() Called.");

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		List<ImportGeneralManifestMod> listOfBL = new ArrayList<ImportGeneralManifestMod>();
		Map<String, String> mapParam = createHeaderParams(objForm);
		List<ImportGeneralManifestMod> blObj = null;

		Map<Object, Object> mapSaveBL = objDao.getStowageImport(mapParam, IGMDaoNew.SQL_STOWAGE_IMPORT);
		blObj = (List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("blDetails", blObj);
		jsonObj.write(response.getWriter());
		return null;

	}

	/**
	 * Onload.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 */
	// --------------------------------- we are getting some extra values here
	// -----------------------------------------------
	private ActionForward getCarogoDetailsAndMore(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getCarogoDetailsAndMore() Called.");

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		List<ImportGeneralManifestMod> listOfBL = new ArrayList<ImportGeneralManifestMod>();
		Map<String, String> mapParam = createHeaderParams(objForm);
		List<ImportGeneralManifestMod> blObj = null;

		Map<Object, Object> mapSaveBL1 = objDao.getOneBLDataNewFor(mapParam, IGMDaoNew.SQL_EXTRA);
		blObj = (List<ImportGeneralManifestMod>) mapSaveBL1.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("blDetails", blObj);
		jsonObj.write(response.getWriter());
		return null;

	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// ------------------------------------------reading and returnig the ACK json
	// file--------------------------------------
	private ActionForward getUploadAckFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException, IOException, ParseException {
		System.out.println(" getUploadAckFile starts.........");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
//    String 						   data 				= objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
//	ObjectMapper 				   mapper 				= new ObjectMapper();
//	ImportGeneralManifestInput     saveParam 			= mapper.readValue(data, ImportGeneralManifestInput.class);

		InputStream inputStream = objForm.getAckJson().getInputStream();
		// byte[] jsonData = objForm.getAckJson().getFileData();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			String str = null;
			String ackData = null;
			List<String> list = new ArrayList<String>();
			StringBuilder string = new StringBuilder();
			while ((str = reader.readLine()) != null) {

				System.out.println(str);

				string.append(str);
			}

			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			jsonObj = new net.sf.json.JSONObject();
			jsonObj.put("result", string.toString());
			jsonObj.write(response.getWriter());
		}
		return null;

	}

	private String JSONArray(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	private ActionForward onload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}

	// Update
	private ActionForward applyIgm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger ApplyIgm() is called..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		IGMVesselVoyageSaveDao vesselVoyageDao = (IGMVesselVoyageSaveDao) getDao(DAO_BEAN_VESSEL_VOYOGE_ID);
		vesselVoyageDao.updateIgmdetails(objForm);
		return null;
	}

	/**
	 * On search.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private ActionForward onSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger onSearch() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;

		Map<String, String> mapParam = createHeaderParams(objForm);
		IGMDaoNew objImportDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		Map<Object, Object> mapReturn = new HashMap<Object, Object>();
		mapReturn = objImportDao.getIGMData(mapParam);
		List<ImportGeneralManifestMod> result = (List<ImportGeneralManifestMod>) mapReturn
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();

//		 Map<Object, Object> mapReturnBL = objImportDao.getOneBLDataNewFor(mapParam, IGMDaoNew.SQL_EXTRA);
//		 List<ImportGeneralManifestMod> resultBL1 = (List<ImportGeneralManifestMod>) mapReturnBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", getUniqueRecords(result));
//		jsonObj.put("result", getUniqueRecords(resultBL1));
		jsonObj.write(response.getWriter());
		return null;

	}

	/**
	 * Creates the header params.
	 *
	 * @param aobjForm the aobj form
	 * @return the map
	 */
	private Map<String, String> createHeaderParams(ImportGeneralManifestUim aobjForm) {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, aobjForm.getBl());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, aobjForm.getPod());
		if (aobjForm.getIgmservice() != null && !aobjForm.getIgmservice().equals("")) {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getIgmservice());
		} else {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getService());
		}
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, aobjForm.getVessel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, aobjForm.getVoyage());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD_TERMINAL, aobjForm.getPodTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_FROM_DATE, aobjForm.getBlCreationDateFrom());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_TO_DATE, aobjForm.getBlCreationDateTo());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL_STATUS, aobjForm.getInStatus());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL, aobjForm.getPol());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL_TERMINAL, aobjForm.getPolTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DIRECTION, aobjForm.getDirection());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEPOT, aobjForm.getDepot());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEL, aobjForm.getDel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_COUNTRYCODE, aobjForm.getConsigneeCountryCode());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_STATE, aobjForm.getConsigneeState());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONTAINER_NUM, aobjForm.getContainer());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONTAINER_NUM, aobjForm.getPodScreen());
		return mapParam;
	}

	private Map<String, String> createHeaderParamsJson(ImportGeneralManifestMod aobjForm) {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, aobjForm.getBl());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, aobjForm.getPod());
		if (aobjForm.getService() != null && !aobjForm.getService().equals("")) {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getService());
		} else {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getService());
		}
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, aobjForm.getVessel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, aobjForm.getVoyage());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD_TERMINAL, aobjForm.getPodTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_FROM_DATE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_TO_DATE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL_STATUS, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL, aobjForm.getPol());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL_TERMINAL, aobjForm.getPolTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DIRECTION, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEPOT, aobjForm.getDepot());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEL, aobjForm.getDel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_COUNTRYCODE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_STATE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONTAINER_NUM, "");
		return mapParam;
	}

	/**
	 * Gets the unique records.
	 *
	 * @param list the list
	 * @return the unique records
	 */
	public List<ImportGeneralManifestMod> getUniqueRecords(List<ImportGeneralManifestMod> list) {
		Set<ImportGeneralManifestMod> uniqueRecords = new LinkedHashSet<>(list);
		list = new ArrayList<>(uniqueRecords);
		return list;
	}

	@SuppressWarnings("unchecked")
	private ActionForward vesselVoyagSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("#IGMLogger vesselVoyagSave() is called..");
		IGMVesselVoyageSaveDao vesselVoyageDao = (IGMVesselVoyageSaveDao) getDao(DAO_BEAN_VESSEL_VOYOGE_ID);

		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;

		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		ImportGeneralManifestMod service = mapper.readValue(data, ImportGeneralManifestMod.class);

		vesselVoyageDao.saveVesselVoyageData(service, IGMVesselVoyageSaveDao.RCL_IGM_SAVE_VESSEL_VOYOAGE);
		return null;
	}

	@SuppressWarnings("unchecked")
	private ActionForward blDataSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("#IGMLogger blDataSave() is called..");
		IGMBLDataDao 				   objBlDao 			= (IGMBLDataDao) getDao(DAO_BEAN_BL_ID);
		IGMConsigneeDataDao 		   objConsigneeDao  	= (IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMContainerDaoImpl 		   containerDao 		= (IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		IGMConsignerDataDao 		   objConsignerDao  	= (IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMNodifyPartyDao 			   objNodifyDao 		= (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao 			   objMarksDescDao  	= (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao     objPreviousDao   	= (IGMPreviousDeclarationDaoImpl) getDao(DAO_BEAN_PREV_DECLARATION_DESC_ID);
		ImportGeneralManifestUim 	   objForm 		    	= (ImportGeneralManifestUim) form;
		
		String 						   data 				= objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper 				   mapper 				= new ObjectMapper();
		ImportGeneralManifestInput     saveParam 			= mapper.readValue(data, ImportGeneralManifestInput.class);

		List<ImportGeneralManifestMod> deleteBL 			=  new ArrayList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> insertBL 			=  new ArrayList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> insertBLFetch 		=  new ArrayList<ImportGeneralManifestMod>();
		
		List<Consignee> 			   consignee 			= new ArrayList<>();
		List<Consigner> 			   consigner 			= new ArrayList<>();
		List<NotifyParty> 			   notifyParty 			= new ArrayList<>();
		List<MarksNumber> 			   marksNumber 			= new ArrayList<>();
		List<ContainerDetails> 		   containerDetailes    = new ArrayList<>();
		List<PreviousDeclaration> 	   previousDeclarations = new ArrayList<>();
		
		ImportGeneralManifestMod 		service 			= saveParam.getService();
		List<ImportGeneralManifestMod>  blList 				= saveParam.getBls();
	//	saveParam.getBls().get(0).getVoyage();
		
		getSaveDataList(blList,deleteBL,insertBL,insertBLFetch);
		
		String blsDeleteInput = null;
		for (ImportGeneralManifestMod mod : deleteBL) {
			 
			
				if (blsDeleteInput == null)
					blsDeleteInput = "'" + mod.getBl() + "'";
				else
					blsDeleteInput += ",'" + mod.getBl() + "'";

				if (!CollectionUtils.isEmpty(mod.getConsignee())) {
					consignee.addAll(mod.getConsignee());
				}
				if (!CollectionUtils.isEmpty(mod.getConsigner())) {
					consigner.addAll(mod.getConsigner());
				}
				if (!CollectionUtils.isEmpty(mod.getNotifyParty())) {
					notifyParty.addAll(mod.getNotifyParty());
				}
				if (!CollectionUtils.isEmpty(mod.getMarksNumber())) {
					marksNumber.addAll(mod.getMarksNumber());
				}
				if (!CollectionUtils.isEmpty(mod.getContainerDetailes())) {
					containerDetailes.addAll(mod.getContainerDetailes());
				}
				if (!CollectionUtils.isEmpty(mod.getPreviousDeclaration())) {
					previousDeclarations.addAll(mod.getPreviousDeclaration());
				}
		}
		if(deleteBL.size()>0) {
			objBlDao.deleteBLData(deleteBL,blsDeleteInput,IGMBLDataDao.RCL_IGM_DELETE_BL);
			containerDao.deleteContainer(containerDetailes, blsDeleteInput,IGMContainerDao.RCL_IGM_DELETE_CONTAINOR);
			objConsigneeDao.deleteConsigneeData(consignee, blsDeleteInput,IGMConsigneeDataDao.RCL_IGM_DELETE_CONSIGNEE);
			objConsignerDao.deleteConsignerData(consigner, blsDeleteInput,IGMConsignerDataDao.RCL_IGM_DELETE_CONSIGNER);
			objNodifyDao.deleteNodifyData(notifyParty, blsDeleteInput,IGMNodifyPartyDao.RCL_IGM_DELETE_NODIFY_PARTY_DESCRIPTION);
			objMarksDescDao.deleteMarkDescData(marksNumber, blsDeleteInput,IGMMarksAndDescDao.RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION);
			objPreviousDao.deletePreviousDeclData(previousDeclarations, blsDeleteInput,IGMPPreviousDeclarationDao.RCL_IGM_DELETE_PREV_DECLARATION);
		}
		
		String blsFetch = null;
		// for insert ROb bl
		String voyageRob = null;
		for (ImportGeneralManifestMod mod : insertBLFetch) {
		
			if (blsFetch == null) {
				blsFetch = "'" + mod.getBl() + "'";
			    voyageRob = "'" + mod.getVoyage()+"'";
			}
			else {
				blsFetch += ",'" + mod.getBl() + "'";
				voyageRob += "'" + mod.getVoyage() + "'";
			}
				

			if (!CollectionUtils.isEmpty(mod.getConsignee())) {
				consignee.addAll(mod.getConsignee());
			}
			if (!CollectionUtils.isEmpty(mod.getConsigner())) {
				consigner.addAll(mod.getConsigner());
			}
			if (!CollectionUtils.isEmpty(mod.getNotifyParty())) {
				notifyParty.addAll(mod.getNotifyParty());
			}
			if (!CollectionUtils.isEmpty(mod.getMarksNumber())) {
				marksNumber.addAll(mod.getMarksNumber());
			}
			if (!CollectionUtils.isEmpty(mod.getContainerDetailes())) {
				containerDetailes.addAll(mod.getContainerDetailes());
			}
			if (!CollectionUtils.isEmpty(mod.getPreviousDeclaration())) {
				previousDeclarations.addAll(mod.getPreviousDeclaration());
			}
		}
		
		if(insertBLFetch.size()>0) {
			objBlDao.saveBLData(insertBLFetch,blsFetch,IGMBLDataDao.RCL_IGM_SAVE_BL);
			containerDao.saveContainer(containerDetailes, blsFetch,IGMContainerDao.RCL_IGM_SAVE_CONTAINOR);
			objConsigneeDao.saveConsigneeData(consignee, blsFetch,IGMConsigneeDataDao.RCL_IGM_SAVE_CONSIGNEE);
			objConsignerDao.saveConsignerData(consigner, blsFetch,IGMConsignerDataDao.RCL_IGM_SAVE_CONSIGNER);
			objNodifyDao.saveNodifyData(notifyParty, blsFetch,IGMNodifyPartyDao.RCL_IGM_SAVE_NODIFY_PARTY_DESCRIPTION);
			objMarksDescDao.saveMarkDescData(marksNumber, blsFetch,IGMMarksAndDescDao.RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION);
			objPreviousDao.savePreviousDeclData(previousDeclarations, blsFetch,IGMPPreviousDeclarationDao.RCL_IGM_SAVE_PREV_DECLARATION);
			
			//objBlDao.saveBlDetails(insertBLFetch, blsFetch, IGMBLDataDao.RCL_SAVE_DETAILS);
		}
		
		String blsNotFetch = null;
		
 
		Map<String, String> mapParam = new HashMap<>();
		for (ImportGeneralManifestMod mod : insertBL) {
			
			if (blsNotFetch == null) {
				blsNotFetch = "'" + mod.getBl() + "'";
				voyageRob = "'" +mod.getVoyage()+"'";
			}
			else
			{
				blsNotFetch += ",'" + mod.getBl() + "'";
				voyageRob += ",'" + mod.getVoyage() + "'";
			}

			if (!CollectionUtils.isEmpty(mod.getConsignee())) {
				consignee.addAll(mod.getConsignee());
			}
			if (!CollectionUtils.isEmpty(mod.getConsigner())) {
				consigner.addAll(mod.getConsigner());
			}
			if (!CollectionUtils.isEmpty(mod.getNotifyParty())) {
				notifyParty.addAll(mod.getNotifyParty());
			}
			if (!CollectionUtils.isEmpty(mod.getMarksNumber())) {
				marksNumber.addAll(mod.getMarksNumber());
			}
			if (!CollectionUtils.isEmpty(mod.getContainerDetailes())) {
				containerDetailes.addAll(mod.getContainerDetailes());
			}
			if (!CollectionUtils.isEmpty(mod.getPreviousDeclaration())) {
				previousDeclarations.addAll(mod.getPreviousDeclaration());
			}
		}
		
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, service.getVessel());
	//	mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, service.getVoyage());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, voyageRob);
		if(insertBL.size()>0) {
			objBlDao.saveUnfetchedBlData(blsNotFetch,IGMBLDataDao.RCL_IGM_UNFETCHED_SAVE_BL,mapParam,insertBL);
			containerDao.saveUnfetchedContainer(blsNotFetch,IGMContainerDao.RCL_IGM_SAVE_UNFETCHED_CONTAINOR);
			objConsigneeDao.saveUnfetchedConsigneeData(blsNotFetch,IGMConsigneeDataDao.RCL_IGM_SAVE_UNFETCHED_CONSIGNEE);
			objConsignerDao.saveUnfetchedConsignerData(blsNotFetch,IGMConsignerDataDao.RCL_IGM_SAVE_UNFETCHED_CONSIGNER);
			objNodifyDao.saveUnfetchedNodifyData(blsNotFetch,IGMNodifyPartyDao.RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DESCRIPTION);
			objMarksDescDao.saveUnfetchedMarkDescData(blsNotFetch,IGMMarksAndDescDao.RCL_IGM_SAVE_UNFETCHED_MARKS_NUMBER_DESCRIPTION);
			objPreviousDao.saveUnfetchedPreviousDeclData(blsNotFetch,IGMPPreviousDeclarationDao.RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION);
		}
		
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", saveParam.getSaveBlPhase());
		jsonObj.write(response.getWriter());
		return null;
		 
	}

	private ActionForward getSelectAllOption(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		IGMConsignerDataDao objConsignerDao = (IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMConsigneeDataDao objConsigneeDao = (IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMNodifyPartyDao objNotifyPartyDao = (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao objMarksDescDao = (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao objPreviousDao = (IGMPreviousDeclarationDaoImpl) getDao(
				DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod> listOfBL = new ArrayList<ImportGeneralManifestMod>();
		ImportGeneralManifestMod manifestMod = new ImportGeneralManifestMod();
		IGMContainerDaoImpl containerDao = (IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		List<ContainerDetails> containerList = null;
		List<ImportGeneralManifestMod> blObj = new LinkedList<ImportGeneralManifestMod>();
		Map<String, String> paramVal = createHeaderParams(objForm);

		System.out.println("IGMNewScreenSvc getSelectAllOption()... [STARTED..]");

		int blNo = 5000;
		int threadCount = blNo / 100;
		SelectAllThread th[] = new SelectAllThread[threadCount - 1];
		for (int i = 0; i < threadCount; i++) {
			th[i].start();
		}

		if (objForm.getSavedBlList() != null && !objForm.getSavedBlList().equals("")) {
			Map<String, String> mapParam = new HashMap<>();
			String blsInput = null;
			Map<Object, Object> mapSaveBL = null;
			String blNos[] = objForm.getSavedBlList().split(",");
			int savedBlCount = 0;
			for (String bl : blNos) {
				savedBlCount++;
				if (blsInput == null)
					blsInput = "'" + bl + "'";
				else
					blsInput += ",'" + bl + "'";

			}
			if (savedBlCount > 999) {
				int modularOfBlCount1 = savedBlCount / 999;
				System.out.println(modularOfBlCount1);
				String[] blsInputArray = blsInput.split(",");
				for (int i = 0; i <= modularOfBlCount1; i++) {
					blsInput = "";
					for (int j = i * 900; j < (i * i + 1) * 900; j++) {
						if (StringUtils.isEmpty(blsInput))
							blsInput = blsInputArray[j];
						else
							blsInput += "," + blsInputArray[j];
					}

					List<ImportGeneralManifestMod> blObjTmp = new LinkedList<ImportGeneralManifestMod>();
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, objForm.getPod());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);

					mapSaveBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_NEW, true, false,blsInput);
					
					blObjTmp.addAll(
							(List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
					containerDao.setContainerDetails(blObjTmp, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR);
					objConsignerDao.setConsignerData(blObjTmp, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER);
					objConsigneeDao.setConsigneeData(blObjTmp, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE);
					objNotifyPartyDao.setNotifyPartyData(blObjTmp,
							IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
					objMarksDescDao.setMarksDescriptionData(blObj,
							IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION);
					objPreviousDao.setPreviousDeclData(blObjTmp,
							IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION);
					blObj.addAll(blObjTmp);
				}
			} else {
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, objForm.getPod());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);

				mapSaveBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_NEW, true, false,blsInput);
				blObj.addAll((List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
				containerDao.setContainerDetails(blObj, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR);
				objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER);
				objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE);
				objNotifyPartyDao.setNotifyPartyData(blObj,
						IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
				objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION);
				objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION);
			}
		}

		if (objForm.getUnSavedBlList() != null && !objForm.getUnSavedBlList().equals("")) {
			Map<String, String> mapParam = new HashMap<>();
			Map<Object, Object> mapReturnBL = null;
			String blsInput = null;
			String blNos[] = objForm.getUnSavedBlList().split(",");
			int unSaveBblcount = 0;
			for (String bl : blNos) {
				unSaveBblcount++;
				if (blsInput == null)
					blsInput = "'" + bl + "'";
				else
					blsInput += ",'" + bl + "'";
			}

			if (unSaveBblcount > 999) {
				int modularOfBlCount = unSaveBblcount / 999;
				System.out.println(modularOfBlCount);
				String[] blsInputArr = blsInput.split(",");
				for (int i = 0; i <= modularOfBlCount; i++) {
					blsInput = "";
					for (int j = i * 900; j < (i * i + 1) * 900; j++) {
						if (StringUtils.isEmpty(blsInput))
							blsInput = blsInputArr[j];
						else
							blsInput += "," + blsInputArr[j];
					}
					List<ImportGeneralManifestMod> blObjTmp = new LinkedList<ImportGeneralManifestMod>();

					mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, objForm.getPod());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);

					mapReturnBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA_NEW, true, true,blsInput);
					blObjTmp.addAll((List<ImportGeneralManifestMod>) mapReturnBL
							.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));

					containerDao.setContainerDetails(blObjTmp, IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR);
					objConsignerDao.setConsignerData(blObjTmp, IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER);
					objConsigneeDao.setConsigneeData(blObjTmp, IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE);
					objNotifyPartyDao.setNotifyPartyData(blObjTmp,
							IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION);
					objMarksDescDao.setMarksDescriptionData(blObjTmp,
							IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION);
					objPreviousDao.setPreviousDeclData(blObjTmp,
							IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION);
					blObj.addAll(blObjTmp);
				}
			} else {

				mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, objForm.getPod());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);

				mapReturnBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA_NEW, true, true,blsInput);
				blObj.addAll(
						(List<ImportGeneralManifestMod>) mapReturnBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));

				containerDao.setContainerDetails(blObj, IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR);
				objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER);
				objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE);
				objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION);
				objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION);
				objPreviousDao.setPreviousDeclData(blObj,
						IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION);

			}
			objDao.getBLDataNewForSome(paramVal, IGMDaoNew.SQL_EXTRA, blObj);
			objDao.getStowageImport(paramVal, IGMDaoNew.SQL_STOWAGE_IMPORT);
		}

		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("blDetails", blObj);
		jsonObj.write(response.getWriter());
		System.out.println("getContainerDetails() End.");

		return null;
	}

	/**
	 * On search.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private ActionForward getVesselVoyagData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		System.out.println("#IGMLogger getVesselVoyagData() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;

		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		objForm = mapper.readValue(data, ImportGeneralManifestUim.class);

		Map<String, String> mapParam = createHeaderParams(objForm);

		// get data form our local DB tabel
		Map<Object, Object> mapReturn = objDao.getVesselVoyagData(mapParam,
				IGMDaoNew.SQL_GET_SAVED_IGM_VESSLE_VOYAGE_DATA);
		List<ImportGeneralManifestMod> resultVesel = (List<ImportGeneralManifestMod>) mapReturn
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		if (resultVesel.size() == 0) {
			mapReturn = objDao.getVesselVoyagData(mapParam, IGMDaoNew.SQL_GET_IGM_VESSLE_VOYAGE_DATA);
			resultVesel = (List<ImportGeneralManifestMod>) mapReturn.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		}

		/* BL Start.. */
		Map<Object, Object> mapReturnBL = objDao.getBLDataNew(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MASTER_SAVE_DATA,
				false);
		List<ImportGeneralManifestMod> resultBL = (List<ImportGeneralManifestMod>) mapReturnBL
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		String blsInput = null;
		for (ImportGeneralManifestMod bl : resultBL) {
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
		}
		System.out.println(blsInput);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();

		List<ImportGeneralManifestResultSet> finalResult = getFinalData(resultVesel.get(0), 1, resultBL);

		List<IGMCrewEfctMod> crewEfctMod = new ArrayList();
		List<IGMPersonOnBoardMod> personOnBoardMod = new ArrayList();
		List<IGMShipStoresMod> shipStoresMod = new ArrayList();

		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", finalResult);
		jsonObj.put("DropDown", objDao.getroadCarrCodeData(mapParam));
		jsonObj.put("personOnBoardMod", personOnBoardMod);
		jsonObj.put("crewEfctMod", crewEfctMod);
		jsonObj.put("shipStoresMod", shipStoresMod);
		jsonObj.write(response.getWriter());
		return null;
	}

	private ActionForward getPersonOnboard(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getPersonOnboard() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map<String, String> mapParam = createHeaderParams(objForm);
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);

		List<IGMPersonOnBoardMod> personOnBoardMod = new ArrayList();
		personOnBoardMod = objPersonDao.getPersonOnBoard(mapParam,
				PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("personOnBoardMod", personOnBoardMod);
		jsonObj.write(response.getWriter());
		System.out.println("getPersonOnboard() end.");
		return null;
	}

	private ActionForward getShipStor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getShipStor() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map<String, String> mapParam = createHeaderParams(objForm);
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		List<IGMShipStoresMod> shipStoresMod = new ArrayList();
		shipStoresMod = objPersonDao.getShipStore(mapParam, PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_SHIP_STORE);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("shipStoresMod", shipStoresMod);
		jsonObj.write(response.getWriter());
		System.out.println("getShipStor() End.");
		return null;
	}

	private ActionForward getCrewEffect(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getCrewEffect() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map<String, String> mapParam = createHeaderParams(objForm);
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		List<IGMCrewEfctMod> crewEfctMod = new ArrayList();
		crewEfctMod = objPersonDao.getCrewEffect(mapParam, PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_CREW_EFFECT);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("crewEfctMod", crewEfctMod);
		jsonObj.write(response.getWriter());
		System.out.println("getCrewEffect() End.");
		return null;
	}

	private List<ImportGeneralManifestResultSet> getFinalData(ImportGeneralManifestMod vesselObj, int sqn,
			List<ImportGeneralManifestMod> uniqueRecords) {
		List<ImportGeneralManifestResultSet> finalResult = new ArrayList<>();

		List<ImportGeneralManifestMod> bls = new ArrayList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> withOutDischargeUniqueBlList = new ArrayList<ImportGeneralManifestMod>();
		uniqueRecords = uniqueRecords.stream()
				.filter(o -> o.getBlDischargedStatus() == null || o.getBlDischargedStatus().trim().equals("")
						|| !o.getBlDischargedStatus().trim().equals("BL DISCHARGED"))
				.collect(Collectors.toList());

		// first cargomovement to be sorted as per LC and TI
		List<ImportGeneralManifestMod> listWithItemNumbers = uniqueRecords.stream()
				.filter(o -> o.getItemNumber() != null && !o.getItemNumber().trim().equals(""))
				.collect(Collectors.toList());
		listWithItemNumbers.sort((o1, o2) -> {

			try {
				return Integer.valueOf(o1.getItemNumber()).compareTo(Integer.valueOf(o2.getItemNumber()));
			} catch (Exception e) {
				return o1.getItemNumber().compareTo(o2.getItemNumber());
			}

		});
		List<ImportGeneralManifestMod> listWithCargoMovement = uniqueRecords.stream()
				.filter(o -> o.getItemNumber() == null || o.getItemNumber().trim().equals(""))
				.collect(Collectors.toList());
		listWithCargoMovement.sort((c1, c2) -> {
			return c1.getCargoMovmnt().compareTo(c2.getCargoMovmnt());
		});
		// cargoMovmnt
		// Get BL#
		List<ImportGeneralManifestMod> listWithOutCargoMovementLC = listWithCargoMovement.stream()
				.filter(c -> c.getCargoMovmnt().trim().equals("LC")).collect(Collectors.toList());
		listWithOutCargoMovementLC.sort((c1, c2) -> {
			return c1.getBl().compareTo(c2.getBl());
		});
		System.out.println(listWithOutCargoMovementLC);
		List<ImportGeneralManifestMod> listWithOutCargoMovementTI = listWithCargoMovement.stream()
				.filter(c -> c.getCargoMovmnt().trim().equals("TI")).collect(Collectors.toList());
		listWithOutCargoMovementTI.sort((o1, o2) -> {
			return o1.getBl().compareTo(o2.getBl());
		});

		bls.clear();
		bls.addAll(listWithItemNumbers);
		bls.addAll(listWithOutCargoMovementLC);
		bls.addAll(listWithOutCargoMovementTI);

		finalResult.add(new ImportGeneralManifestResultSet(vesselObj, sqn, bls));

		return finalResult;
	}

	private void getSaveDataList(List<ImportGeneralManifestMod> blList, List<ImportGeneralManifestMod> deleteBL,
			List<ImportGeneralManifestMod> insertBL,List<ImportGeneralManifestMod> insertBLFtch) {

		System.out.println("getSaveDataList() Start.");
		List<ImportGeneralManifestMod> bl = new ArrayList<ImportGeneralManifestMod>();

		bl = blList.stream().filter(c -> c.getSaveFlags().trim().equals("D")).collect(Collectors.toList());
		deleteBL.addAll(bl);
		// need to insert deleted bl if it is selected & saved from UI
		// for this we need to update save flag as I
//		for(ImportGeneralManifestMod  sbl : deleteBL )
//		{
//			if(blList.contains(sbl))
//			{
//				int  i = blList.indexOf(sbl);
//				blList.get(i).setSaveFlags("I");
//			}
//			
//		}
		bl.clear();
		bl = blList.stream().filter(c -> (c.getSaveFlags().trim().equals("I") || c.getSaveFlags().trim().equals("U")) && (c.isFetch()==false) )
				.collect(Collectors.toList());
		insertBL.addAll(bl);
		bl.clear();
		bl = blList.stream().filter(c -> (c.getSaveFlags().trim().equals("I") || c.getSaveFlags().trim().equals("U")) && (c.isFetch()==true) )
				.collect(Collectors.toList());
		insertBLFtch.addAll(bl);
		bl.clear(); 
		System.out.println("getSaveDataList() end.");
	}

	private ActionForward getContainerDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getContainerDetails() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		IGMContainerDaoImpl containerDao = (IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		List<ImportGeneralManifestMod> listOfBL = new ArrayList<ImportGeneralManifestMod>();
		ImportGeneralManifestMod manifestMod = new ImportGeneralManifestMod();
		List<ContainerDetails> containerList = null;
		manifestMod.setBl(objForm.getBl());
		listOfBL.add(manifestMod);
		if (objForm.getIsBlSave().equals("false") || objForm.getItemNumber() == null
				|| objForm.getItemNumber().equals("")) {
			containerList = containerDao.setContainerDetailsNew(listOfBL, IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR);
		} else {
			containerList = containerDao.setContainerDetailsNew(listOfBL, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR);
		}
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("containerList", containerList);
		jsonObj.write(response.getWriter());
		System.out.println("getContainerDetails() End.");
		return null;
	}

	private ActionForward getCarogoDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getCarogoDetails() Called.");

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		IGMConsignerDataDao objConsignerDao = (IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMConsigneeDataDao objConsigneeDao = (IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMNodifyPartyDao objNotifyPartyDao = (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao objMarksDescDao = (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao objPreviousDao = (IGMPreviousDeclarationDaoImpl) getDao(
				DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod> listOfBL = new ArrayList<ImportGeneralManifestMod>();
		Map<String, String> mapParam = createHeaderParams(objForm);
		List<ImportGeneralManifestMod> blObj = null;

		if (objForm.getIsBlSave().equals("false") || objForm.getItemNumber() == null
				|| objForm.getItemNumber().equals("")) {

			Map<Object, Object> mapReturnBL = objDao.getBLCarogoDetails(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA);
			blObj = (List<ImportGeneralManifestMod>) mapReturnBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
			objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER);
			objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE);
			objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION);
			objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION);
			objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION);
		} else {
			Map<Object, Object> mapSaveBL = objDao.getBLCarogoDetails(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA);
			blObj = (List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

			objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER);
			objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE);
			objNotifyPartyDao.setNotifyPartyData(blObj,
					IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
			objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION);
			objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION);
			
		}
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("blDetails", blObj);
		jsonObj.write(response.getWriter());
		return null;
	}

	/**
	 * On save file.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @param getSeqNo
	 * @return the action forward
	 * @throws Exception the exception
	 */

	private ActionForward downloadJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("#IGMLogger downloadJson() started............");
		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
	    String voyage = objForm.getVoyage();
		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		String dataPersonOnBord = objForm.getPersonOnBoardMod();
		String dataCrewEfctMod = objForm.getCrewEfctMod();
		String dataShipStoresMod = objForm.getShipStoresMod();
		ObjectMapper mapper = new ObjectMapper();
		  String empJson ="";
		  String formated = "";
			 String formattedJson = "";
			 String senderId = "";

		ImportGeneralManifestInput saveParam = mapper.readValue(data, ImportGeneralManifestInput.class);
		ImportGeneralManifestMod service = saveParam.getService();

		List<ImportGeneralManifestMod> blList = saveParam.getBls();

		
		List<IGMPersonOnBoardMod> personOnBoardMod = mapper.readValue(dataPersonOnBord,
				new TypeReference<List<IGMPersonOnBoardMod>>() {
				});
		if (personOnBoardMod.isEmpty()) {
			Map<String, String> mapParam = createHeaderParamsJson(service);
			PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
			personOnBoardMod = objPersonDao.getPersonOnBoard(mapParam,
					PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD);
		}

		List<IGMCrewEfctMod> crewEfctMod = mapper.readValue(dataCrewEfctMod, new TypeReference<List<IGMCrewEfctMod>>() {
		});

		if (crewEfctMod.isEmpty()) {
			Map<String, String> mapParam = createHeaderParamsJson(service);
			PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
			crewEfctMod = objPersonDao.getCrewEffect(mapParam, PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_CREW_EFFECT);
		}

		List<IGMShipStoresMod> shipStoresMod = mapper.readValue(dataShipStoresMod,
				new TypeReference<List<IGMShipStoresMod>>() {
				});
		if (shipStoresMod.isEmpty()) {
			Map<String, String> mapParam = createHeaderParamsJson(service);
			PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
			shipStoresMod = objPersonDao.getShipStore(mapParam, PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_SHIP_STORE);
		}

		List<ImportGeneralManifestMod> blListNewSavedVal = new ArrayList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> blListNew = new ArrayList<ImportGeneralManifestMod>();

		for (int l = 0; l < blList.size(); l++) {
			ImportGeneralManifestMod obj = blList.get(l);
			if (obj.getIsBlSave().equals("true")) {
				blListNew.add(obj);
			}
		}
		blListNewSavedVal.addAll(getBlDetails(service, objForm, blList));
		
		    PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
	 
	 		 // for itrnry 
		 	List<ItnrySAM> itrnrySam = null;
		 	
		 	List<ItnrySCE> itrnrySce = null;
		 	
		    // for BlId 
//	 	    List<BlId> blId = null;
	 	    if(objForm.getFileType().equals("SAM")) {
	 	    
			 	 for(int i =0;i<blListNewSavedVal.size();i++) {
			 		 
			 		itrnrySam = objPersonDao.getItrnrySam( blListNewSavedVal.get(i).getBl(),PersonOnBoardDao.SQL_RCL_GET_ITNRY_DATA);
				    blListNewSavedVal.get(i).setItnrySam(itrnrySam);
				    //FOR bl id
//				    blId = objPersonDao.getBlId( blListNewSavedVal.get(i).getBl(),PersonOnBoardDao.SQL_RCL_GET_BLID);
//				    blListNewSavedVal.get(i).setBlId(blId);
			 	 }
	 	    }
		 	 if(objForm.getFileType().equals("SCE")) {
		 	 
			 	for(int i =0;i<blListNewSavedVal.size();i++) {
			 		 
			 		itrnrySce = objPersonDao.getItrnrySce( blListNewSavedVal.get(i).getBl(),PersonOnBoardDao.SQL_RCL_GET_ITNRY_DATA);
				    blListNewSavedVal.get(i).setItnrySce(itrnrySce);
				  
			 	 }
		 	 }
	
		    
		    
		 
//		blListNewSavedVal.addAll(getBlDetails(service, objForm, blList));
		System.out.println("Object Done..... 0");

		int getSeqNo = objDao.getSeqNoJdbc(service, "IGM", objForm.getFileType());

		Object manifestFile = CreatingJSON.getJsonFile(blListNewSavedVal, objForm.getFileType(), service, personOnBoardMod,
				crewEfctMod, shipStoresMod, getSeqNo);
		objDao.updateSqnNoForJsonFile(service, getSeqNo, "IGM", objForm.getFileType());
		System.out.println("Object Done..... 1");
//		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		 mapper.enable(SerializationFeature.INDENT_OUTPUT);
         empJson = mapper.writeValueAsString(manifestFile);
      
         if(null ==  objForm.getSenderId() || ("").equals(objForm.getSenderId() )) {
        	 senderId = "";
         }else {
        	 senderId =  objForm.getSenderId();
         }
         String FileName= "F_" + "SACHM23_"+ objForm.getFileType()+"_"+ senderId+
        		 "_"+getSeqNo+"_"+getTimeHeader()+"_"+"DEC"+".json";
         
         response.setContentType("application/octet-stream");
         response.setHeader("Content-Disposition", "attachment; filename=\"" + FileName + "\"");

//         try (ServletOutputStream outputStream = response.getOutputStream()) {
//        	 JsonFactory jsonFactory = new JsonFactory();
//        	 JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream);
//        	 jsonGenerator.writeStartArray(); 
//        	 jsonGenerator.writeRaw(empJson);
//        	 jsonGenerator.writeEndArray(); 
//        	 jsonGenerator.flush();
//        	 jsonGenerator.close();
//        	 outputStream.flush(); 
//        	 outputStream.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }	
         // Use a temporary byte array output stream to capture the JSON data
//         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//         byteArrayOutputStream.write(empJson.getBytes());
//         try (ServletOutputStream outputStream = response.getOutputStream()) {
//        	    outputStream.print(empJson);
//        	    outputStream.flush();
//        	} catch (IOException e) {
//        	    e.printStackTrace();
//        	}
//         try (ServletOutputStream outputStream = response.getOutputStream()) {
//        	 byteArrayOutputStream.writeTo(outputStream);
//        	 outputStream.flush();
//        	 outputStream.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
           // Write the formatted JSON string to a file
//           String filePath = System.getProperty("user.home") + "\\Downloads" + File.separator + FileName;
//           try (FileWriter fileWriter = new FileWriter(filePath)) {
//               fileWriter.write(empJson);
//               System.out.println("JSON file downloaded successfully.");
//           } catch (IOException e) {
//               e.printStackTrace();
//           } catch (Exception e) {
//           e.printStackTrace();
//       }
		
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
//		String jsonStr = null;
//		try {
//			jsonStr = mapper.writeValueAsString(manifestFile);
//			Object json = mapper.readValue(jsonStr, Object.class);
//			  formattedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
//			  mapper.writeValue(
//                      new File(
//                          "D:"+File.separator+"map1.json"),
//                      formattedJson);
//             System.out.println("The hashSet object in json format:"+ jsonStr);
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	
//         System.out.println("manifestFile"+empJson);
     	try {
			LinkedHashMap<String, Object> jsonObj = new LinkedHashMap<>();
			jsonObj.put("jsonFile", manifestFile);
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(jsonObj);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonString.toString());
			System.out.println("#IGMLogger downloadJson() completed..");
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			Runtime.getRuntime().gc();
		}
		return null;
	}

	private List<ImportGeneralManifestMod> getBlDetails(ImportGeneralManifestMod mod, ImportGeneralManifestUim objForm,List<ImportGeneralManifestMod> blList)
			throws Exception {

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		IGMConsignerDataDao objConsignerDao = (IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMConsigneeDataDao objConsigneeDao = (IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMNodifyPartyDao objNotifyPartyDao = (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao objMarksDescDao = (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao objPreviousDao = (IGMPreviousDeclarationDaoImpl) getDao(
				DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod> listOfBL = new ArrayList<ImportGeneralManifestMod>();
		ImportGeneralManifestMod manifestMod = new ImportGeneralManifestMod();
		IGMContainerDaoImpl containerDao = (IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		List<ContainerDetails> containerList = null;
		List<ImportGeneralManifestMod> blObj = new LinkedList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> blObjForPod = new LinkedList<ImportGeneralManifestMod>();
		
			if (objForm.getSavedBlList() != null || !objForm.getSavedBlList().equals("")) {
				Map<String, String> mapParam = new HashMap<>();
				Map<Object, Object> mapSaveBL = null;
				String blsInput = null;
				String blsInputForBelow = null;
				
				String blNos[] = objForm.getSavedBlList().split(",");
				int savedBlCount = 0;
					for (String bl : blNos) {
				savedBlCount++;
				if (blsInputForBelow == null)
					blsInputForBelow = bl ;
				else
					blsInputForBelow += ","+ bl;

			}
					if (savedBlCount > 999) {
						int modularOfBlCount1 = savedBlCount / 999;
						System.out.println(modularOfBlCount1);
						String[] blsInputArray = objForm.getSavedBlList().split(",");
						for (int i = 0; i <= modularOfBlCount1; i++) {
							blsInput = "";	
							
							for (int j = i * 900; j < (i * i + 1) * 900; j++) {
								if (StringUtils.isEmpty(blsInput))
									blsInput = blsInputArray[j];
								else
									blsInput += "," + blsInputArray[j];
								
							}
							for (int l = 0; l < blList.size(); l++) {
								ImportGeneralManifestMod obj = blList.get(l);
									blObjForPod.add(obj);
									System.out.println();
									
							}
			    List<ImportGeneralManifestMod> blObjTmp = new LinkedList<ImportGeneralManifestMod>();
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, mod.getPod());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, mod.getService());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, mod.getVessel());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
				//	mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, mod.getVoyage());
					
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);

					List<ImportGeneralManifestMod> blObjTmp1 = new LinkedList<ImportGeneralManifestMod>();

				    mapSaveBL = objDao.getBLData(mapParam,
				           IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_NEW, true, false,blsInput);
					 
				    blObjTmp1.addAll(
							(List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
					containerDao.setContainerDetails(blObjTmp1, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR);
					objConsignerDao.setConsignerData(blObjTmp1, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER);
					objConsigneeDao.setConsigneeData(blObjTmp1, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE);
					objNotifyPartyDao.setNotifyPartyData(blObjTmp1,
							IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
					objMarksDescDao.setMarksDescriptionData(blObjTmp1,
							IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION);
					objPreviousDao.setPreviousDeclData(blObjTmp1,
							IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION);
					blObj.addAll(blObjTmp1);
						}
				}else {

					mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, mod.getPod());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, mod.getService());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, mod.getVessel());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
				//	mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, mod.getVoyage());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, objForm.getSavedBlList());
					mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD_SCREEN, objForm.getPodScreen());
				

					List<ImportGeneralManifestMod> blObjTmp = new LinkedList<ImportGeneralManifestMod>();

				    mapSaveBL = objDao.getBLData(mapParam,
				           IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_NEW, true, false,blsInputForBelow);
				    blObj.addAll(
							(List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
					containerDao.setContainerDetails(blObj, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR);
					objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER);
					objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE);
					objNotifyPartyDao.setNotifyPartyData(blObj,
							IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
					objMarksDescDao.setMarksDescriptionData(blObj,
							IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION);
					objPreviousDao.setPreviousDeclData(blObj,
							IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION);					
				}
					
			}

		return blObj;
	}

	/**
	 * onExcelUpload.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception
	 */
	private ActionForward onCsvUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("onCsvUpload() calling....");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		InputStream inputStream = objForm.getFileExl().getInputStream();
		List list = new ArrayList();

		String flage = null;
		if (objForm.getCheckCSV() != null && objForm.getCheckCSV().equals("P")) {
			flage = "P";
			list = new ArrayList<IGMPersonOnBoardMod>();
		} else if (objForm.getCheckCSV() != null && objForm.getCheckCSV().equals("C")) {
			flage = "C";
			list = new ArrayList<IGMCrewEfctMod>();
		} else {
			flage = "S";
			list = new ArrayList<IGMShipStoresMod>();
		}

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			String str = null;
			int i = 0;
			while ((str = reader.readLine()) != null) {
				if (i != 0) {
					createList(str, objForm.getBl(), objForm.getVessel(), objForm.getVoyage(), objForm.getPod(), flage,
							list);
				}
				i++;
			}
		}

		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		if (flage.equals("P"))
			objPersonDao.savePersonOnBoard(list, objForm);
		else if (flage.equals("S"))
			objPersonDao.saveShipStore(list, objForm);
		else
			objPersonDao.saveCrewEfect(list, objForm);

		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", list);
		jsonObj.write(response.getWriter());
		// System.out.println("#IGMLogger EXCEL JSON obj: " + jsonObj.write(new
		// StringWriter()));
		return null;
	}

	/**
	 * On Delete csv file.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private ActionForward deleteCsvData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger deleteCsvData() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		ImportGeneralManifestUim saveParam = mapper.readValue(data, ImportGeneralManifestUim.class);

		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		objPersonDao.deleteCsv(saveParam);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", "delete");
		jsonObj.write(response.getWriter());
		return null;

	}

	@SuppressWarnings("unchecked")
	private ActionForward downloadCSV(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger deleteCsvData() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		// String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		// ImportGeneralManifestUim saveParam = mapper.readValue(data,
		// ImportGeneralManifestUim.class);

		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		Map<String, String> mapParam = createHeaderParams(objForm);
		List<IGMPersonOnBoardMod> personOnBoardMod = new ArrayList();
		List<IGMCrewEfctMod> crewEfctMod = new ArrayList();
		List<IGMShipStoresMod> shipStoresMod = new ArrayList();
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();

		if (objForm.getCheckCSV() != null && objForm.getCheckCSV().equals("P")) {
			personOnBoardMod = objPersonDao.getPersonOnBoard(mapParam,
					PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD);
			jsonObj.put("result", personOnBoardMod);
		} else if (objForm.getCheckCSV() != null && objForm.getCheckCSV().equals("C")) {
			crewEfctMod = objPersonDao.getCrewEffect(mapParam, PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_CREW_EFFECT);
			jsonObj.put("result", crewEfctMod);
		} else {
			shipStoresMod = objPersonDao.getShipStore(mapParam, PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_SHIP_STORE);
			jsonObj.put("result", shipStoresMod);
		}

		jsonObj.write(response.getWriter());
		return null;

	}

	/*
	 * Use to map CSV file value to respective pojo class variables.
	 */
	public static void createList(String line, String bl, String vessel, String voyage, String pod, String flage,
			List list) throws Exception {
		String[] words = line.split(",");

		if (flage.equals("P") && words.length == 22) {
			IGMPersonOnBoardMod personOnBoardMod = new IGMPersonOnBoardMod();
			personOnBoardMod.setBlNo(bl);
			personOnBoardMod.setVessel(vessel);
			personOnBoardMod.setVoyage(voyage);
			personOnBoardMod.setPod(pod);
			personOnBoardMod.setPrsnTypCdd(words[1]);
			personOnBoardMod.setPrsnFamilyName(words[2]);
			personOnBoardMod.setPrsnGivenName(words[3]);
			personOnBoardMod.setPrsnNatnltyCdd(words[4]);
			personOnBoardMod.setPsngrInTransitIndctr(words[5]);
			personOnBoardMod.setCrewmemberRankOrRating(words[6]);
			personOnBoardMod.setCrewmemberRankOrRatingCdd(words[7]);
			personOnBoardMod.setPsngrPrtOfEmbrktnCdd(words[8]);
			personOnBoardMod.setPsngrPrtOfEmbrktnName(words[9]);
			personOnBoardMod.setPsngrPrtOfDsmbrktnCdd(words[10]);
			personOnBoardMod.setPsngrPrtOfDsmbrktnName(words[11]);
			personOnBoardMod.setPrsnGenderCdd(words[12]);
			personOnBoardMod.setPrsnDtOfBirth(words[13]);
			personOnBoardMod.setPrsnPlaceOfBirthName(words[14]);
			personOnBoardMod.setPrsnCntryOfBirthCdd(words[15]);
			personOnBoardMod.setPrsnIdDocExpiryDt(words[16]);
			personOnBoardMod.setPrsnIdOrTravelDocIssuingNationCdd(words[17]);
			personOnBoardMod.setPrsnIdOrTravelDocNmbr(words[18]);
			personOnBoardMod.setPrsnIdOrTravelDocTypCdd(words[19]);
			personOnBoardMod.setVisa(words[20]);
			personOnBoardMod.setPnrNumber(words[21]);
			list.add(personOnBoardMod);
		}
		if (flage.equals("C") && words.length == 7) {
			IGMCrewEfctMod crewEfctMod = new IGMCrewEfctMod();
			crewEfctMod.setBlNo(bl);
			crewEfctMod.setVessel(vessel);
			crewEfctMod.setVoyage(voyage);
			crewEfctMod.setPod(pod);
			crewEfctMod.setPersonOnBoardSequenceNo(words[0]);
			crewEfctMod.setSequenceNo(words[1]);
			crewEfctMod.setCrewEfctDescCdd(words[2]);
			crewEfctMod.setCrewEfctsDesc(words[3]);
			crewEfctMod.setCrewEfctQntyOnbrd(words[4]);
			crewEfctMod.setCrewEfctQntyCdOnbrd(words[5]);
			crewEfctMod.setCrewEfctsVsslSeqNmbr(words[6]);
			list.add(crewEfctMod);
		}
		if (flage.equals("S") && words.length == 7) {
			IGMShipStoresMod shipStoresMod = new IGMShipStoresMod();
			shipStoresMod.setBlNo(bl);
			shipStoresMod.setVessel(vessel);
			shipStoresMod.setVoyage(voyage);
			shipStoresMod.setPod(pod);
			shipStoresMod.setArticleNameCdd(words[1]);
			shipStoresMod.setArticleNameText(words[2]);
			shipStoresMod.setLocOnbrdText(words[3]);
			shipStoresMod.setQntyOnbrd(words[4]);
			shipStoresMod.setQntyCdOnbrd(words[5]);
			shipStoresMod.setVesselSrno(words[6]);
			list.add(shipStoresMod);
		}
	}

	private ActionForward getPortDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("getPortDetails() Called..");

		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		List<PortMod> list = objDao.getPortDetailsJdbc();

		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", list);
		jsonObj.write(response.getWriter());
		return null;
	}

	@Override
	public void run() {

	}
	public static String getTimeHeader() {
		/*
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		 * SimpleDateFormat sd = new SimpleDateFormat("HH:mm"); LocalDateTime now =
		 * LocalDateTime.now(); sd.setTimeZone(TimeZone.getTimeZone("IST")); // pending
		 * return (dtf.format(now));
		 * 
		 */
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		System.out.println( df.format(date));
		String isdDateAndTime = df.format(date);
		System.out.println(isdDateAndTime);
		return  isdDateAndTime;
	}
	
	

}
