package com.rclgroup.dolphin.web.igm.jsonUtil;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rclgroup.dolphin.web.igm.vo.Consignee;
import com.rclgroup.dolphin.web.igm.vo.Consigner;
import com.rclgroup.dolphin.web.igm.vo.ContainerDetails;
import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.MarksNumber;
import com.rclgroup.dolphin.web.igm.vo.NotifyParty;
import com.rclgroup.dolphin.web.igm.vo.saa.AuthPrsnSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.CrewEfctSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.DecRefSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.DigSignSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.HCAdtnlDecSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.HCCrgoSuprtDocsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.HCRefSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.HeaderFieldSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.HouseCargoDecSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.ItemDtlsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.ItnrySAA;
import com.rclgroup.dolphin.web.igm.vo.saa.JsonMainObjctSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.LocCstmSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.MCAdtnlDecSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.MCRefSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.MCSuprtDocsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.MasterSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.MastrCnsgmtDecSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.PrevRefSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.PrsnDtlsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.PrsnIdSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.PrsnOnBoardSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.ShipItnrySAA;
import com.rclgroup.dolphin.web.igm.vo.saa.ShipStoresSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.TmAdtnlDecSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.TmSuprtDocsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.TrnshprSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.TrnsprtDocMsrSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.TrnsprtDocSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.TrnsprtEqmtSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.VesselDtlsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.VoyageDtlsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.VoyageTransportEquipmentSAA;
import com.rclgroup.dolphin.web.igm.vo.sam.ArvlDtlsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.AuthPrsnSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.DecRefSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.HCPrevRefSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.HCRefSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.HeaderFieldSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.HouseCargoDecSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.ItemDtlsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.ItnrySAM;
import com.rclgroup.dolphin.web.igm.vo.sam.JsonMainObjctSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.LocCstmSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.MCAdtnlDecSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.MCRefSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.MCSuprtDocsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.MasterSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.MastrCnsgmtDecSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.PrevRefSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.PrsnDtlsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.PrsnIdSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.PrsnOnBoardSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.ShipItnrySAM;
import com.rclgroup.dolphin.web.igm.vo.sam.TrnshprSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.TrnsprtDocMsrSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.TrnsprtDocSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.TrnsprtEqmtSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.VesselDtlsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.VisaDtlsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.VoyageDtlsSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.VoyageTransportEquipmentSAM;
import com.rclgroup.dolphin.web.igm.vo.sca.AuthPrsnSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.DecRefSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.DigSignSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.HCAdtnlDecSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.HCCrgoSuprtDocsSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.HCRefSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.HeaderFieldSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.HouseCargoDecSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.ItemDtlsSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.ItnrySCA;
import com.rclgroup.dolphin.web.igm.vo.sca.JsonMainObjctSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.LocCstmSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.MCAdtnlDecSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.MCRefSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.MCSuprtDocsSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.MasterSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.MastrCnsgmtDecSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.PrevRefSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.PrsnDtlsSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.PrsnIdSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.PrsnOnBoardSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.ShipItnrySCA;
import com.rclgroup.dolphin.web.igm.vo.sca.ShipStoresSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.SupRefSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.TrnshprSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.TrnsprtDocMsrSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.TrnsprtDocSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.TrnsprtEqmtSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.VesselDtlsSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.VoyageDtlsSCA;
import com.rclgroup.dolphin.web.igm.vo.sca.VoyageTransportEquipmentSCA;
import com.rclgroup.dolphin.web.igm.vo.scc.AuthPrsnSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.DecRefSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.DigSignSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.HeaderFieldSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.ItemDtlsSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.JsonMainObjctSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.LocCstmSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.MCRefSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.MasterSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.MastrCnsgmtDecSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.PrsnDtlsSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.PrsnIdSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.PrsnOnBoardSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.TrnsprtEqmtSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.VesselDtlsSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.VoyageDtlsSCC;
import com.rclgroup.dolphin.web.igm.vo.scc.VoyageTransportEquipmentSCC;
import com.rclgroup.dolphin.web.igm.vo.scd.AuthPrsnSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.DecRefSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.DigSignSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.HCAdtnlDecSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.HCCrgoSuprtDocsSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.HCRefSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.HeaderFieldSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.HouseCargoDecSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.ItemDtlsSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.ItnrySCD;
import com.rclgroup.dolphin.web.igm.vo.scd.JsonMainObjctSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.LocCstmSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.MCAdtnlDecSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.MCRefSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.MCSuprtDocsSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.MasterSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.MastrCnsgmtDecSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.PrevRefSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.PrsnDtlsSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.PrsnIdSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.PrsnOnBoardSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.ShipItnrySCD;
import com.rclgroup.dolphin.web.igm.vo.scd.ShipStoresSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.TrnshprSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.TrnsprtDocMsrSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.TrnsprtDocSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.TrnsprtEqmtSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.VesselDtlsSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.VoyageDtlsSCD;
import com.rclgroup.dolphin.web.igm.vo.scd.VoyageTransportEquipmentSCD;
import com.rclgroup.dolphin.web.igm.vo.sce.AuthPrsnSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.DecRefSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.HCRefSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.HeaderFieldSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.HouseCargoDecSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.ItemDtlsSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.ItnrySCE;
import com.rclgroup.dolphin.web.igm.vo.sce.JsonMainObjctSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.LocCstmSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.MCAdtnlDecSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.MCRefSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.MCSuprtDocsSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.MasterSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.MastrCnsgmtDecSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.PrevRefSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.ShipItnrySCE;
import com.rclgroup.dolphin.web.igm.vo.sce.TrnshprSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.TrnsprtDocMsrSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.TrnsprtDocSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.TrnsprtEqmtSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.VesselDtlsSCE;
import com.rclgroup.dolphin.web.igm.vo.sce.VoyageDtlsSCE;
import com.rclgroup.dolphin.web.igm.vo.scu.AuthPrsnSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.DecRefSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.DigSignSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.HeaderFieldSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.ItemDtlsSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.ItnrySCU;
import com.rclgroup.dolphin.web.igm.vo.scu.JsonMainObjctSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.LocCstmSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.MCAdtnlDecSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.MCRefSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.MCSuprtDocsSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.MasterSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.MastrCnsgmtDecSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.PrevRefSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.PrsnDtlsSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.PrsnIdSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.PrsnOnBoardSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.ShipItnrySCU;
import com.rclgroup.dolphin.web.igm.vo.scu.SupRefSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.TrnshprSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.TrnsprtDocMsrSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.TrnsprtDocSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.TrnsprtEqmtSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.VesselDtlsSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.VoyageDtlsSCU;
import com.rclgroup.dolphin.web.igm.vo.scu.VoyageTransportEquipmentSCU;
import com.rclgroup.dolphin.web.igm.vo.scx.AuthPrsnSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.DecRefSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.HCAdtnlDecSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.HCCrgoSuprtDocsSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.HCRefSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.HeaderFieldSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.HouseCargoDecSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.ItemDtlsSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.ItnrySCX;
import com.rclgroup.dolphin.web.igm.vo.scx.JsonMainObjctSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.LocCstmSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.MCAdtnlDecSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.MCRefSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.MCSuprtDocsSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.MasterSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.MastrCnsgmtDecSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.PrevRefSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.PrsnDtlsSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.PrsnIdSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.ShipItnrySCX;
import com.rclgroup.dolphin.web.igm.vo.scx.TrnshprSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.TrnsprtDocMsrSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.TrnsprtDocSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.TrnsprtEqmtSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.VesselDtlsSCX;
import com.rclgroup.dolphin.web.igm.vo.scx.VoyageDtlsSCX;
import com.rclgroup.dolphin.web.igm.vo.sda.AuthPrsnSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.CrewEfctSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.DecRefSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.DigSignSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.HCAdtnlDecSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.HCCrgoSuprtDocsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.HCRefSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.HeaderFieldSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.HouseCargoDecSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.ItemDtlsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.ItnrySDA;
import com.rclgroup.dolphin.web.igm.vo.sda.JsonMainObjctSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.LocCstmSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.MCAdtnlDecSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.MCRefSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.MCSuprtDocsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.MasterSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.MastrCnsgmtDecSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.PrevRefSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.PrsnDtlsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.PrsnIdSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.PrsnOnBoardSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.ShipItnrySDA;
import com.rclgroup.dolphin.web.igm.vo.sda.ShipStoresSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.TmAdtnlDecSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.TmSuprtDocsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.TrnshprSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.TrnsprtDocMsrSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.TrnsprtDocSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.TrnsprtEqmtSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.VesselDtlsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.VoyageDtlsSDA;
import com.rclgroup.dolphin.web.igm.vo.sda.VoyageTransportEquipmentSDA;
import com.rclgroup.dolphin.web.igm.vo.sdm.AuthPrsnSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.CrewEfctSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.DecRefSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.HCAdtnlDecSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.HCCrgoSuprtDocsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.HCRefSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.HeaderFieldSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.HouseCargoDecSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.ItemDtlsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.ItnrySDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.JsonMainObjctSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.LocCstmSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.MCAdtnlDecSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.MCRefSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.MCSuprtDocsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.MasterSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.MastrCnsgmtDecSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.PrsnDtlsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.PrsnIdSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.PrsnOnBoardSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.ShipItnrySDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.ShipStoresSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.TrnsprtDocMsrSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.TrnsprtDocSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.TrnsprtEqmtSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.VesselDtlsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.VisaDtlsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.VoyageDtlsSDM;
import com.rclgroup.dolphin.web.igm.vo.sdm.VoyageTransportEquipmentSDM;
import com.rclgroup.dolphin.web.igm.vo.sdn.ArvlDtlsSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.AuthPrsnSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.DecRefSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.HeaderFieldSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.ItemDtlsSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.ItnrySDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.JsonMainObjctSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.LocCstmSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.MasterSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.TmAdtnlDecSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.TmSuprtDocsSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.TrnsprtDocMsrSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.TrnsprtDocSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.TrnsprtEqmtSDN;
import com.rclgroup.dolphin.web.igm.vo.sdn.VesselDtlsSDN;
import com.rclgroup.dolphin.web.igm.vo.sei.ArvlDtlsSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.AuthPrsnSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.DecRefSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.DigSignSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.HeaderFieldSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.ItemDtlsSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.ItnrySEI;
import com.rclgroup.dolphin.web.igm.vo.sei.JsonMainObjctSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.LocCstmSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.MCRefSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.MasterSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.PrsnOnBoardSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.ShipItnrySEI;
import com.rclgroup.dolphin.web.igm.vo.sei.TmAdtnlDecSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.TmSuprtDocsSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.TrnsprtDocMsrSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.TrnsprtDocSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.TrnsprtEqmtSEI;
import com.rclgroup.dolphin.web.igm.vo.sei.VesselDtlsSEI;

public class CreatingJSON {
	
	public static Object getJsonFile(List<ImportGeneralManifestMod> blList, String fileType,ImportGeneralManifestMod   service,List<IGMPersonOnBoardMod> personOnBoardMod,
			List<IGMCrewEfctMod> crewEfctMod,List<IGMShipStoresMod> shipStoresMod,int getSeqNo) throws Exception {
		String generatedFileNameOfJson = fileType;

		Object resultObj = new Object();

		if ("SAM".equals(fileType)) {
			resultObj = getSAM(blList,service,personOnBoardMod,getSeqNo);  // Done
		} else if ("SDM".equals(fileType)) {
			resultObj = getSDM(blList,service,personOnBoardMod,crewEfctMod,shipStoresMod,getSeqNo);
		} else if ("SDN".equals(fileType)) {
			resultObj = getSDN(blList,service);
		} else if ("SAA".equals(fileType)) {
			resultObj = getSAA(blList);
		} else if ("SEI".equals(fileType)) {
			resultObj = getSEI(blList,service,personOnBoardMod);    // Done
		}  else if ("SDA".equals(fileType)) {
			resultObj = getSDA(blList);
		} else if ("SCE".equals(fileType)) {
			resultObj = getSCE(blList, service, personOnBoardMod, shipStoresMod,getSeqNo);
		} else if ("SCX".equals(fileType)) {
			resultObj = getSCX(blList,service,getSeqNo);
		} else if ("SCD".equals(fileType)) {
			resultObj = getSCD(blList);
		} else if ("SCA".equals(fileType)) {
			resultObj = getSCA(blList);
		} else if ("SCU".equals(fileType)) {
			resultObj = getSCU(blList);
		} else if ("SCC".equals(fileType)) {
			resultObj = getSCC(blList);
		}
		// Creating Object of ObjectMapper define in Jakson Api
//				System.gc();
//				ObjectMapper objectMapper = new ObjectMapper();
//				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//				
//				String jsonStr = null;
//				try {	
//					jsonStr = objectMapper.writeValueAsString(resultObj);
//				}
//				catch (IOException e) {
//					e.printStackTrace();
//				}finally {
//					System.gc();
//				}
////				return jsonStr;
				return resultObj;
			}

/*
 * method that create  json
 *============================* SAM *==========================================================================
 */
	public static JsonMainObjctSAM getSAM(List<ImportGeneralManifestMod> blList,ImportGeneralManifestMod   service,List<IGMPersonOnBoardMod> personOnBoardMod,int getSeqNo) {

   		JsonMainObjctSAM org = new JsonMainObjctSAM();
   		MasterSAM mster = new MasterSAM();   		
		
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = "F";
		String rpngEvent = "SAM";
		int fromItemNoTemp =Integer.valueOf(service.getFromItemNo());
		int containerCount = 0;
		String voyage = isNull((String)service .getVoyage());
		String newVoyage = isNull((String) service.getNewVoyage());
		String pol = isNull((String) service.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage; 
		}
		String vessel = isNull((String) service.getVessel());
		String newVessel = isNull((String) service.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}

		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";
//===============================*HeaderFieldSAM*======================================================================
		HeaderFieldSAM headerFieldClassObj = new HeaderFieldSAM();
		headerFieldClassObj.setSenderID(settingLength(service.getSenderId().toString(),20));
		headerFieldClassObj.setReceiverID(service.getRecieverId());
		headerFieldClassObj.setVersionNo("SAM1102");
		headerFieldClassObj.setIndicator("T");
		headerFieldClassObj.setMessageID("SACHM23");
		headerFieldClassObj.setSequenceOrControlNumber(getSeqNo +1);// old screen String sId (
		headerFieldClassObj.setDate(getTimeHeader());
		headerFieldClassObj.setTime("T" + getIsdTime());
		headerFieldClassObj.setReportingEvent(rpngEvent);
		org.setHeaderField(headerFieldClassObj);
		headerFieldClassObj = null;
		
		
//		headerFieldClassObj.addField("SenderID", "ARSATH");
////		headerFieldClassObj.addField("ReceiverID", service.getRecieverId());
//		headerFieldClassObj.addField("Indicator", "New York");
//
//	        String json = headerFieldClassObj.toJson();
//	        System.out.println(json);
		//List<MCRefSAM> mCRef = new LinkedList<MCRefSAM>();

//	----------------------------------------------------------------------------------------------
		// Creating object of all class
		List<ShipItnrySAM> shipItnry = new LinkedList<ShipItnrySAM>();
		List<MCSuprtDocsSAM> mcSuprtDoc = new LinkedList<MCSuprtDocsSAM>();
		List<MCAdtnlDecSAM> mcAdtnlDec= new LinkedList<MCAdtnlDecSAM>();
		List<PrsnOnBoardSAM> prsnOnBoardList = new LinkedList<PrsnOnBoardSAM>(); 	
		List<MastrCnsgmtDecSAM> mastrCnsgmtDecList = null;
		mastrCnsgmtDecList	= new LinkedList<MastrCnsgmtDecSAM>();
		List<VoyageTransportEquipmentSAM> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSAM>();
		//fromItemNoTemp=Integer.valueOf(blList.);
// ==============================================================================

		for (ImportGeneralManifestMod blObj : blList) {
			if (blObj != null && "true".equalsIgnoreCase(blObj.getIsBlSave())) {
				blObj.setItemNumber(fromItemNoTemp+"");
			} else {
				continue;
			}
			fromItemNoTemp++;
			MastrCnsgmtDecSAM mastrCnsgmtDec = new MastrCnsgmtDecSAM();
			HouseCargoDecSAM houseCargoDecSAMObj = new HouseCargoDecSAM();
			
			List<ItemDtlsSAM> itemDtls = new LinkedList<ItemDtlsSAM>();
			List<TrnsprtEqmtSAM> trnsprtEqmt = new LinkedList<TrnsprtEqmtSAM>();
			List<LocCstmSAM> locCstm = new LinkedList<LocCstmSAM>();
			List<ItnrySAM> itnry = new LinkedList<ItnrySAM>();
			List<TrnsprtDocSAM> trnsprtDoc = new LinkedList<TrnsprtDocSAM>();
			List<TrnsprtDocMsrSAM> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSAM>();
			List<PrevRefSAM> prevRef = new LinkedList<PrevRefSAM>();
			List<TrnshprSAM> trnshpr = new LinkedList<TrnshprSAM>();
			List<ArvlDtlsSAM> arvlDtlsList = new LinkedList<ArvlDtlsSAM>(); 
//			============================house Cargo =======================
			
			List<HouseCargoDecSAM> houseCargoDec = new LinkedList<HouseCargoDecSAM>();
//			List<HCAdtnlDecSAM> hcAdtnlDec= new LinkedList<HCAdtnlDecSAM>();
//			List<HCPrevRefSAM> hcPrevRef= new LinkedList<HCPrevRefSAM>();
//			List<HCCrgoSuprtDocsSAM> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSAM>();
//			System.out.println("gstcode........." +blObj.getGstStateCode() + "... "+blObj.getPointName()+"..."+blObj.getPointName()
//			+".."+blObj.getPortName()+"..."+blObj.getDn_pld()+".."+blObj.getDn_plr());
//		--------------------------------------------------------------------------------------	
			List<NotifyParty> notifyPartyDetailes = blObj.getNotifyParty();
			List<Consignee> consigneeDtls = blObj.getConsignee();
			List<MarksNumber> marksNumberDtls = blObj.getMarksNumber();
			List<Consigner> consignerDtls = blObj.getConsigner();
			List<ContainerDetails> containerDtls = blObj.getContainerDetailes();
			
//		---------------------------------*MCRefSAM*---------------------------------------------	
			MCRefSAM mCRefClassObj = new MCRefSAM();
			mCRefClassObj.setLineNo(Integer.parseInt(blObj.getItemNumber())); // Line 60    
			mCRefClassObj.setMstrBlNo(settingLength(blObj.getBl(),20)); // Line 53
			mCRefClassObj.setMstrBlDt(removeSlash(blObj.getMasterBlDate())); // Line 53
			try {
				if( blObj.getHblArr().isEmpty() || blObj.isHbl()==false  ) {
					 mCRefClassObj.setConsolidatedIndctr("S");// Line 76   //TODO
				}else {
					 mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
				}
			}catch (Exception e) {
				mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
			}
			

			if((null == blObj.getMcin() || blObj.getMcin().equals("")) && (null == blObj.getPcin() || blObj.getPcin().equals(""))) {
				mCRefClassObj.setPrevDec("N");
			}else {
				mCRefClassObj.setPrevDec("Y");
			}
//			mCRefClassObj.setPrevDec(settingLength(blObj.getPrevious_declaration(),4)); // Line77				//TODO  guru	
			mCRefClassObj.setConsolidatorPan("PAN:"+settingLength(service.getAgentCode(),16)); // Line 78		
			
			mastrCnsgmtDec.setmCRef(mCRefClassObj);	
			
//		----------------------------*LocCstmSAM*---------------------------------------------------
			LocCstmSAM locCstmClassObj = new LocCstmSAM();
			locCstmClassObj.setFirstPrtOfEntry( service.getPortArrival());
//			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			
			if(blObj.getPortOfDestination() != null || service.getPortOfDestination() != "") {
//			System.out.println("port of destination" +service.getPortOfDestination());
//			System.out.println("port of destination" +blObj.getPod());
//			System.out.println("port of destination" +service.getPortOfDeschargedCfs());
				if(blObj.getPortOfDestination().equalsIgnoreCase(blObj.getPod()) ) {
					locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDeschargedCfs(),6));
				}else {
					locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),8));
				}
			}
			
			
			if(!blObj.getPortOfDestination().equals(blObj.getPod())) {
				locCstmClassObj.setNxtPrtOfUnlading (settingLength(blObj.getPortOfDestination(),6)); // New added		
			}else if(blObj.getPod().equals(blObj.getPortOfDestination())) {
				locCstmClassObj.setNxtPrtOfUnlading (settingLength(blObj.getPod(),6)); // New added	
			}
			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2)); // Line 90		
//				System.out.println(service.getPortOfDestination().substring(0, 2));
//				System.out.println( service.ge	tPortOfDeschargedCfs().substring(0, 2));
			
			if(blObj.getPortOfDestination().substring(0, 2).equals("IN") && blObj.getPod().substring(0, 2).equals("IN")){
				locCstmClassObj.setTypOfCrgo(settingLength("IM",2)); // if both value in india base
			}else if(! blObj.getPortOfDestination().substring(0, 2).equals("IN") && ! blObj.getPod().substring(0, 2).equals("IN")){
				locCstmClassObj.setTypOfCrgo(settingLength("TR",2)); // both value is not india base 
			}else if( !blObj.getPortOfDestination().substring(0, 2).equals("IN") &&  blObj.getPod().substring(0, 2).equals("IN")) {
				locCstmClassObj.setTypOfCrgo(settingLength("TR",2)); //if portOfdest in india base and portOfDis in foreign base then
			}

			locCstmClassObj.setItemTyp(settingLength("OT",2)); // Line 61
//			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4));// Line 57
			if(blObj.getPod() != null || !blObj.getPod().equals(" ") &&   blObj.getPortOfDestination() != null || 
					!blObj.getPortOfDestination().equals(" ")) {
				if((blObj.getPod().substring(0,2).equals("IN") && blObj.getPortOfDestination().substring(0, 2).equals("IN")) && (blObj.getPod().equals(blObj.getPortOfDestination()))) {
					locCstmClassObj.setCrgoMvmt(settingLength("LC",4));	
				}else if(blObj.getPod().substring(0,2).equals("IN") && blObj.getPortOfDestination().substring(0, 2).equals("IN") && ! blObj.getPod().equals(service.getPortOfDestination())&& blObj.getMode_of_transport()== "3"){
					locCstmClassObj.setCrgoMvmt(settingLength("TI",4));	
				}else if(! blObj.getPod().substring(0,2).equals("IN") && blObj.getPortOfDestination().substring(0, 2).equals("IN")) {
					locCstmClassObj.setCrgoMvmt(settingLength("DT",4));	
				}else if(!blObj.getPod().substring(0,2).equals("IN" ) && ! blObj.getPortOfDestination().substring(0, 2).equals("IN")) {
					locCstmClassObj.setCrgoMvmt(settingLength("FT",4));	
				}
			}
			
			locCstmClassObj.setNatrOfCrgo(settingLength("C",4));  // Line 59
//			locCstmClassObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4));
//			locCstmClassObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getNumber_of_packages(),16,6)); 
//			if(blObj.isHbl()==false) {
//				locCstmClassObj.setSplitIndctr(generatedFileNameOfJson);
//			}
//			locCstmClassObj.setTypOfCrgo(pol);
//			locCstmClassObj.setTypOfPackage(pol);
//			locCstmSAMList.add(locCstmClassObj);	
			mastrCnsgmtDec.setLocCstm(locCstmClassObj);
			houseCargoDecSAMObj.setLocCstm(locCstmClassObj);
			
//		------------------------------*TrnshprSAM*------------------------------------------	
			
			if(blObj.getPod().equals(service.getPod()) && !blObj.getPortOfDestination().equals(blObj.getPod()) 
					&& blObj.getTrshprFlag().equals("1")  ) {
				TrnshprSAM trnshprObj = new TrnshprSAM();
				if(blObj.getMode_of_transport()== "Rail" ) {
					trnshprObj.setTrnshprCode(blObj.getCarrierNo()); 
					trnshprObj.setTrnshprBond(settingLength(blObj.getTpBondNo(),10));	
				}else {
					
					if(null == blObj.getCarrierNo()) {
						trnshprObj.setTrnshprCode(""); 
					}else {
						trnshprObj.setTrnshprCode(blObj.getCarrierNo()); 
					}
					trnshprObj.setTrnshprBond(settingLength(blObj.getTpBondNo(),10));	
				}		 //TODO  guru		
				trnshpr.add(trnshprObj);
				mastrCnsgmtDec.setTrnshpr(trnshprObj);
			}
		
//_________________________________________________________________________________________________________			
			
//------------------------------------*TrnsprtDocSAM*----------------------------------------------------	

			TrnsprtDocSAM trnsprtDocClassObj = new TrnsprtDocSAM();
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));							//TODO  guru
			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getAcceptanceName(),256));			//TODO  guru
			trnsprtDocClassObj.setPrtOfReceiptCdd(settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getRecieptName(),256));	
//			trnsprtDocClassObj.setPrtOfReceiptCdd(settingLength(blObj.getPort_of_receipt(),10));
//			trnsprtDocClassObj.setTypOfCd(pol);
//			trnsprtDocClassObj.setUcrTyp(settingLength(blObj.getUcr_type(),3));	  Guru said to comment 		 								//TODO  guru
//			trnsprtDocClassObj.setUcrCd(settingLength(blObj.getUcr_code(),35));	 Guru said to comment
			for (Consigner cnsnerDtls : consignerDtls) {
				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					
					String add = cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ cnsnerDtls.getAddressLine3() +  cnsnerDtls.getAddressLine4();
					trnsprtDocClassObj.setCnsgnrsName( settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnrCity(settingLength(cnsnerDtls.getCity(),70));
//					trnsprtDocClassObj.setCnsgnrCntrySubDivName(settingLength(cnsnerDtls.getState(),35));  Guru said to comment
//					trnsprtDocClassObj.setCnsgnrsCd( settingLength(cnsnerDtls.getCustomerCode(),17)); Guru said to comment
					trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
//					trnsprtDocClassObj.setCnsgnrPstcd(settingLength(cnsnerDtls.getZip(),9)); guru said to comment
					
				}
			}
			for (Consignee cnsneeDtl : consigneeDtls) {
				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
				{
					String add =  cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+  cnsneeDtl.getAddressLine3() +  cnsneeDtl.getAddressLine4();
					trnsprtDocClassObj.setCnsgnesName(  settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,255));
					trnsprtDocClassObj.setCnsgneCity( settingLength(cnsneeDtl.getCity(),70));
					trnsprtDocClassObj.setCnsgneCntryCd( settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCntrySubDivName(settingLength(cnsneeDtl.getStateName(),35));
					if(cnsneeDtl.getState()== null)
					{
					   trnsprtDocClassObj.setCnsgneCntrySubDiv("");
					}
					else
					{
					if(null != blObj.getGstStateCode() || ("").equals(blObj.getGstStateCode())) {
						 trnsprtDocClassObj.setCnsgneCntrySubDiv(blObj.getGstStateCode());
					}else {
						trnsprtDocClassObj.setCnsgneCntrySubDiv("");
					}
					  
					}
					trnsprtDocClassObj.setCnsgnePstcd( settingLength(cnsneeDtl.getZip(),9));
					try {
					if(  cnsneeDtl.getConsigneIec()!= null || !cnsneeDtl.getConsigneIec().equals("") ) {
						trnsprtDocClassObj.setCnsgnesCd(settingLength(cnsneeDtl.getConsigneIec(),17));
					}else {
						for (NotifyParty notyObj : notifyPartyDetailes) {
						trnsprtDocClassObj.setCnsgnesCd(settingLength(notyObj.getNotifyIec(),17));
						}
					}
					}catch (Exception e) {
						for (NotifyParty notyObj : notifyPartyDetailes) {
							trnsprtDocClassObj.setCnsgnesCd(settingLength(notyObj.getNotifyIec(),17));
							}
					}
				
					
				}
			}
			
			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add =  notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+  notyObj.getAddressLine3() +  notyObj.getAddressLine4();
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,256));
					trnsprtDocClassObj.setNotfdPartyCity( settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName(settingLength(notyObj.getStateName(),45)); // will be provided by customer
					trnsprtDocClassObj.setNotfdPartyCntrySubDiv(settingLength(blObj.getGstStateCode(),35));
					trnsprtDocClassObj.setNotfdPartyPstcd( settingLength(notyObj.getZip(),9));
					try {
					if(!notyObj.getNotifyPan().equals("")) {
						trnsprtDocClassObj.setPanOfNotfdParty(settingLength(notyObj.getNotifyPan(),17));
						trnsprtDocClassObj.setTypOfNotfdPartyCd("PAN");
						trnsprtDocClassObj.setTypOfCd( settingLength("PAN",30));
					}else {
						for (Consignee cnsneeDtl : consigneeDtls) {
							trnsprtDocClassObj.setPanOfNotfdParty(settingLength(cnsneeDtl.getConsignePan(),17));
							trnsprtDocClassObj.setTypOfCd( settingLength("PAN",30));
						}
					}
				}catch (Exception e) {
					for (Consignee cnsneeDtl : consigneeDtls) {
						trnsprtDocClassObj.setPanOfNotfdParty(settingLength(cnsneeDtl.getConsignePan(),17));
						trnsprtDocClassObj.setTypOfNotfdPartyCd("IEC");
						trnsprtDocClassObj.setTypOfCd( settingLength("IEC",30));
					}
				}
				}
			}
//			trnsprtDoc.add(trnsprtDocClassObj);
			mastrCnsgmtDec.setTrnsprtDoc(trnsprtDocClassObj);
//_________________________________________________________________________________________________________			
			
			
//------------------------------------------------*TrnsprtDocMsrSAM*--------------------------------------------------	
			
			TrnsprtDocMsrSAM trnsprtDocMsrClassObj = new TrnsprtDocMsrSAM();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getTotal_number_of_packages(),9));
			if(null != blObj.getType_of_package()|| ("").equals(blObj.getType_of_package())) {
				trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			}else {
				trnsprtDocMsrClassObj.setTypsOfPkgs("");
			}
		
//			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512)); 
			if(null != blObj.getGrosWeight() || ("").equals(blObj.getGrosWeight())) {
				trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrosWeight(),12,3));
			}else {
				trnsprtDocMsrClassObj.setGrossWeight("");
			}   //TODO  	
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength("KGS",3));			 
//			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));				 //TODO  guru	
//			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); Guru said to comment // not cleared by Guru    //TODO  guru	
//			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3));      Guru said to comment	 
			
			
			if(blObj.getCargo_msmt() > 0) {
				trnsprtDocMsrClassObj.setGrossVolume(settingLengthForDouble(blObj.getGross_volume(),12,3));
	     	}
			if(! "".equals(trnsprtDocMsrClassObj.getGrossVolume())&& trnsprtDocMsrClassObj.getGrossVolume() != null ) {
				trnsprtDocMsrClassObj.setUnitOfVolume(settingLength("CBM",3));
			}
//_________________________________________________________________________________________________________			
			
//------------------------------------------*ItemDtlsSAM*----------------------------------------------

			if(mCRefClassObj.getConsolidatedIndctr().equals("S")) {
				ItemDtlsSAM itemDtlsClassObj = new ItemDtlsSAM();
				itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCommodity_seq()+"",5));				//TODO  guru
				itemDtlsClassObj.setHsCd(blObj.getCommdity_code());
				itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));					//TODO  guru
				if(blObj.getDgFlag() != null && StringUtils.isNotBlank(blObj.getDgFlag()) ) {
					itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
					itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));	
				}else {
						itemDtlsClassObj.setUnoCd( settingLength("ZZZZZ",5));
						itemDtlsClassObj.setImdgCd( settingLength("ZZZ",3));	
				}									
													//TODO  guru
				itemDtlsClassObj.setNmbrOfPkgs( settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6)); 
				
				itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getType_of_package(),3));
				

				itemDtls.add(itemDtlsClassObj);
				mastrCnsgmtDec.setItemDtls(itemDtlsClassObj);
			}else {
				if(blObj.isHbl()==true) {
					if(mCRefClassObj.getConsolidatedIndctr().equals("H")) {
						ItemDtlsSAM itemDtlsClassObj = new ItemDtlsSAM();
						// trnsprtEqmtClassObj.setHsCd((String)blObj.get(" ")); not cleared by guru
						itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCargo_item_sequence_no(),5));				//TODO  guru
						itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));					//TODO  guru
						itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));										
						itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));										//TODO  guru
						itemDtlsClassObj.setNmbrOfPkgs( settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6)); 
						itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getPackage_kind(),3));
						itemDtlsClassObj.setHsCd(blObj.getCommdity_code());

						itemDtls.add(itemDtlsClassObj);
					}
				}
			}
//_________________________________________________________________________________________________________			
		
//----------------------------------------*TrnsprtEqmtSAM*------------------------------------------------
			int j = 0 ;
			Set<String> containseSets= new HashSet<>();
			for (ContainerDetails ctnerDtl : containerDtls) {
				
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo()) && !containseSets.contains(ctnerDtl.getContainerNumber())) {
					j++;
					containseSets.add(ctnerDtl.getContainerNumber());
					TrnsprtEqmtSAM trnsprtEqmtClassObj = new TrnsprtEqmtSAM();
					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(j+"",5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3));// alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getIsoCode(),4)); // optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
//					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256)); guru said to comment
//					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256)); guru said to comment
//					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
//					trnsprtEqmtClassObj.setEventDt(""); guru said to comment
//					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10)); guru said to comment
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(service.getAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8)); //Guru said to comment
					trnsprtEqmt.add(trnsprtEqmtClassObj);
				}
			}  
			mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
//_________________________________________________________________________________________________________			
			
			//Dep ends on Rob
//-----------------------------------------*ItnrySAM*---------------------------------------------------------------------


			ItnrySAM itnryClassObj = new ItnrySAM();
			
			if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("1",5)); 
			}else if (blObj.getPortOfLoading()!= null && blObj.getPod()!= null && blObj.getPortOfDestination() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("2",5)); 		
			}else if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null && 
					blObj.getPortOfDestination() != null && blObj.getPortOfDeschargedCfs() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("3",5)); 
			}
			 //TODO  guru
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));    //TODO  guru
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getNext_port_of_call_name(),256));		//TODO  guru
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));			//TODO  guru
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getPod(),10));				//TODO  guru
			itnryClassObj.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1));
//			itnry.add(itnryClassObj);
			
			mastrCnsgmtDec.setItnry(itnryClassObj);
			houseCargoDecSAMObj.setItnry(itnry);
//_________________________________________________________________________________________________________			
			
//--------------------------------------*PrevRefSAM*-------------------------------------------------------
			try {
			if((!blObj.getMcin().equals(""))|| (!blObj.getPcin().equals(""))) {
			PrevRefSAM prevRefObj = new PrevRefSAM();
			if(blObj.getMcin() != null || blObj.getMcin() !="" ) {	
			prevRefObj.setCinTyp(settingLength(blObj.getMcin(),4));	
				}else if( blObj.getPcin() != null || blObj.getPcin() !="") {
					 prevRefObj.setCinTyp(settingLength(blObj.getPcin(),4));	
				}
			mastrCnsgmtDec.setPrevRef(prevRefObj);
			}
			}catch (Exception e) {
				System.out.println("blObj.getMcin getting null");
			}
//			prevRefObj.setCinTyp(settingLength(blObj.getMcinpcin(),4));	
			
//			prevRefObj.setMcinPcin(blObj.getMcin());
// 			prevRefObj.setCrgoMvmt(blObj.getCargoMovmnt());
//			prevRefObj.setCsnDt(blObj.getCsn_date()); 				
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); 
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4));							//TODO  guru	
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));						//TODO  guru	
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));		 				//TODO  guru	
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6));									//TODO  guru	
//			prevRefObj.setSplitIndctr(settingLength(blObj.getSplit_indicator_list(),2)); 
//			prevRefObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getNumber_of_packages(),16,6));							//TODO  	
//			prevRefObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4));

//			prevRef.add(prevRefObj);
//_________________________________________________________________________________________________________			
			
//----------------------------------*HCRefSAM*----------------------------------------------------------------			

			List<HCRefSAM> hCRef = new LinkedList<HCRefSAM>();
			HCRefSAM hCRefObj = new HCRefSAM();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength("H",4));   //TODO
			hCRefObj.setConsolidatorPan(settingLength("",16));  
			
			//hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			if(blObj.getMcin()!= null && blObj.getPcin() != null) {
				hCRefObj.setPrevDec("y");
			}else {
				hCRefObj.setPrevDec("N");
			}
			
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),5));
//			hCRef.add(hCRefObj);
			houseCargoDecSAMObj.sethCRef(hCRefObj);
			
			houseCargoDecSAMObj.setPrevRef(prevRef);
			houseCargoDecSAMObj.setTrnshpr(trnshpr);
			houseCargoDecSAMObj.setTrnsprtDocMsr(trnsprtDocMsr);
			houseCargoDecSAMObj.setItemDtls(itemDtls);
			
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {
				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl( settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsrClassObj);
			houseCargoDecSAMObj.setTrnsprtDocMsr(trnsprtDocMsr );
			
	
			
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSAMObj.setTrnsprtDoc(trnsprtDoc);
			houseCargoDecSAMObj.setTrnsprtEqmt(trnsprtEqmt);
			
//			HCCrgoSuprtDocsSAM crgoSuprtDocs = new HCCrgoSuprtDocsSAM(); 
//			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));       Guru said to comment  //TODO  guru
//			crgoSuprtDocs.setDocRefNmbr(settingLength("",17)); 	 Guru said to comment	//TODO  guru
//			crgoSuprtDocs.setDocTypCd(settingLength("",6));		 Guru said to comment		//TODO  guru
//			crgoSuprtDocs.setIcegateUserid(settingLength("",15));	 Guru said to comment	//TODO  guru	
//			crgoSuprtDocs.setIrnNmbr(settingLength("",14));	  Guru said to comment		//TODO  guru
//			crgoSuprtDocs.setRefSerialNo(settingLength("",5)); 	Guru said to comment	//TODO  guru
//			crgoSuprtDocs.setSubSerialNoRef(""); 	Guru said to comment	//TODO  guru	
//			crgoSuprtDocs.setTagRef(settingLength("",5));	Guru said to comment			//TODO  guru
//			crgoSuprtDoc.add(crgoSuprtDocs);
//			houseCargoDecSAMObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
			
//			HCAdtnlDecSAM adtnlDec = new HCAdtnlDecSAM();    //TODO  guru
//			adtnlDec.setTagRef(settingLength("",5)); Guru said to comment				
//			adtnlDec.setRefSerialNo(settingLength("",5));		Guru said to comment	
//			adtnlDec.setInfoCd(settingLength("",35)); 	Guru said to comment
//			adtnlDec.setInfoDt(""); Guru said to comment
//			adtnlDec.setInfoMsr(settingLength("",5)); Guru said to comment
//			adtnlDec.setInfoQualifier(settingLength("",10)); Guru said to comment
//			adtnlDec.setInfoText(settingLength("",100)); guru said to comment
//			adtnlDec.setInfoTyp(settingLength("",10)); 	Guru said to comment
//			hcAdtnlDec.add(adtnlDec);
//			houseCargoDecSAMObj.setHcAdtnlDec(hcAdtnlDec);
//			houseCargoDec.add(houseCargoDecSAMObj);
//_________________________________________________________________________________________________________			

//--------------------------------------------*HCPrevRefSAM*---------------------------------------------------------------	
			HCPrevRefSAM PrevRef =new HCPrevRefSAM();
			PrevRef.setCinTyp("");
//			PrevRef.setCsnDt(generatedFileNameOfJson);
//			PrevRef.setCsnNmbr(generatedFileNameOfJson);
			if(blObj.isHbl() == false) {
				PrevRef.setMcinPcin(blObj.getMcin());
			}else {
				PrevRef.setMcinPcin(blObj.getPcin());
			}
			
//			PrevRef.setNmbrOfPkgs(generatedFileNameOfJson);
//			PrevRef.setTypOfPackage(pol);
//			hcPrevRef.add(PrevRef);
//			houseCargoDecSAMObj.setHcPrevRef(hcPrevRef);	
//_________________________________________________________________________________________________________			
			
//------------------------------------------------------------------------------------------------------------	
//			MCSuprtDocsSAM mcSuprtDocs = new MCSuprtDocsSAM ();  		
//			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));  Guru said to comment
//			mcSuprtDocs.setDocRefNmbr(settingLength("",17));  Guru said to comment 
//			mcSuprtDocs.setDocTypCd(settingLength("",6));  Guru said to comment
//			mcSuprtDocs.setIcegateUserid(settingLength("",15)); Guru said to comment
//			mcSuprtDocs.setIrnNmbr(settingLength("",14));  Guru said to comment
//			mcSuprtDocs.setRefSerialNo(settingLength("",5)); Guru said to comment
//			mcSuprtDocs.setSubSerialNoRef(""); Guru said to comment
//			mcSuprtDocs.setTagRef(settingLength("",5)); Guru said to comment
//			mcSuprtDoc.add(mcSuprtDocs);	
			
			
//			MCAdtnlDecSAM mcAdtnlDecs = new MCAdtnlDecSAM();
//			mcAdtnlDecs.setTagRef(settingLength("",5)); Guru said to comment
//			mcAdtnlDecs.setRefSerialNo(settingLength("",5)); Guru said to comment
//			mcAdtnlDecs.setInfoCd(settingLength("",35)); 	Guru said to comment
//			mcAdtnlDecs.setInfoDt(""); Guru said to comment
//			mcAdtnlDecs.setInfoMsr(settingLength("",5)); Guru said to comment
//			mcAdtnlDecs.setInfoQualifier(settingLength("",10)); Guru said to comment
//			mcAdtnlDecs.setInfoText(settingLength("",100)); guru said to comment
//			mcAdtnlDecs.setInfoTyp(settingLength("",10)); 	Guru said to comment
//			mcAdtnlDec.add(mcAdtnlDecs);//TODO  guru				


		

// --------------------------------------*VoyageTransportEquipmentSAM*---------------------------------------------
			
			for (ContainerDetails cntnerDtl : containerDtls) {

//				System.out.println("   coneeDtls  " + i + (cntnerDtl.getContainerSealNumber()));
				containerCount++;
				VoyageTransportEquipmentSAM voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSAM();
				voyageTransportEquipmentClassObj.setEquipmentSequenceNo(containerCount+"");
				voyageTransportEquipmentClassObj.setEquipmentId(cntnerDtl.getContainerNumber());
				voyageTransportEquipmentClassObj.setEquipmentType("CN");
				voyageTransportEquipmentClassObj.setEquipmentSize(cntnerDtl.getIsoCode());
				voyageTransportEquipmentClassObj.setEquipmentLoadStatus(settingLength(cntnerDtl.getEquipmentLoadStatus(),3));
				voyageTransportEquipmentClassObj.setEquipmentSealNumber(cntnerDtl.getContainerSealNumber());
				voyageTransportEquipmentClassObj.setEquipmentSealType(cntnerDtl.getEquipment_seal_type());
				voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
				voyageTransportEquipmentClassObj.setContainerAgentCode(service.getAgentCode()); 
				
				voyageTransportEquipmentClassObj.setContainerWeight(cntnerDtl.getContainerWeight());
				voyageTransportEquipmentClassObj.setTotalNumberOfPackages(cntnerDtl.getTotalNumberOfPackagesInContainer());
//				voyageTransportEquipmentClassObj.setContainerBondFlag("D");
				if(null==blObj.getStowagePosition()) {
					voyageTransportEquipmentClassObj.setStowagePositionOfContainer("");
				}else {
					voyageTransportEquipmentClassObj.setStowagePositionOfContainer(blObj.getStowagePosition());
				}
				voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
			}
			mster.setVoyageTransportEquipment(voyageTransportEquipmentList);   // Correct
			
			
			
		
		
			mastrCnsgmtDecList.add(mastrCnsgmtDec);
			mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
			houseCargoDec.add(houseCargoDecSAMObj);
			if (blObj.isHbl()== true) {
				mastrCnsgmtDec.setHouseCargoDec(houseCargoDecSAMObj);
			}
			
			

		}
//_________________________________________________________________________________________________________			
		
//	======================== end of loop ======================================
		
		
		for(int g=0;g<personOnBoardMod.size();g++) {
			
			PrsnOnBoardSAM prsnOnBoard = new PrsnOnBoardSAM();
			
			PrsnDtlsSAM prsDtls = new PrsnDtlsSAM();
			if(personOnBoardMod.get(g).getPrsnTypCdd().equals("") && personOnBoardMod.get(g).getPrsnTypCdd() == null) {
				prsDtls.setPrsnTypCdd(settingLength("NA",3));
			}else {
				prsDtls.setPrsnTypCdd(settingLength(personOnBoardMod.get(g).getPrsnTypCdd(),3));
			}
			
			if(personOnBoardMod.get(g).getPrsnFamilyName().equals("") && personOnBoardMod.get(g).getPrsnFamilyName() == null) {
				prsDtls.setPrsnFamilyName(settingLength("NA",70));
			}else {
				prsDtls.setPrsnFamilyName(settingLength(personOnBoardMod.get(g).getPrsnFamilyName(),70));
			}
			
			if(personOnBoardMod.get(g).getPrsnGivenName().equals("") && personOnBoardMod.get(g).getPrsnGivenName() == null) {
				prsDtls.setPrsnGivenName(settingLength("NA",70));
			}else {
				prsDtls.setPrsnGivenName(settingLength(personOnBoardMod.get(g).getPrsnGivenName(),70));
			}
			
			if(personOnBoardMod.get(g).getPrsnNatnltyCdd().equals("") && personOnBoardMod.get(g).getPrsnNatnltyCdd()==null) {
				prsDtls.setPrsnNatnltyCdd(settingLength("NA",2));
			}else {
				prsDtls.setPrsnNatnltyCdd(settingLength(personOnBoardMod.get(g).getPrsnNatnltyCdd(),2));
			}
			
			if(personOnBoardMod.get(g).getPsngrInTransitIndctr().equals("") && personOnBoardMod.get(g).getPsngrInTransitIndctr() == null) {
				prsDtls.setPsngrInTransitIndctr(settingLength("NA",1));
			}else {
				prsDtls.setPsngrInTransitIndctr(settingLength(personOnBoardMod.get(g).getPsngrInTransitIndctr(),1));
			}
			
			if(personOnBoardMod.get(g).getCrewmemberRankOrRatingCdd().equals("")|| personOnBoardMod.get(g).getCrewmemberRankOrRatingCdd()==null) {
				prsDtls.setCrewmemberRankOrRatingCdd("NA");
			}else {
				prsDtls.setCrewmemberRankOrRatingCdd(personOnBoardMod.get(g).getCrewmemberRankOrRatingCdd());
			}
			
			if(personOnBoardMod.get(g).getCrewmemberRankOrRating().equals("")||personOnBoardMod.get(g).getCrewmemberRankOrRating()== null) {
				prsDtls.setCrewmemberRankOrRatingName(settingLength("NA",10));
			}else {
				prsDtls.setCrewmemberRankOrRatingName(settingLength(personOnBoardMod.get(g).getCrewmemberRankOrRating(),10));
			}
			
			if(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnCdd().equals("") || personOnBoardMod.get(g).getPsngrPrtOfEmbrktnCdd()== null) {
				prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("NA",5));
			}else {
				prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnCdd(),5));
			}
			
			if(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnName().equals("")|| personOnBoardMod.get(g).getPsngrPrtOfEmbrktnName()== null) {
				prsDtls.setPsngrPrtOfEmbrktnName(settingLength("NA",256));
			}else {
				prsDtls.setPsngrPrtOfEmbrktnName(settingLength(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnName(),256));
			}
		
			if(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd().equals("") || personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd()==null) {
				prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd(),5));
			}else {
				prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd(),5));
			}
			
			if(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnName().equals("") || personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnName()==null) {
				prsDtls.setPsngrPrtOfDsmbrktnName(settingLength("NA",256));
			}else {
				prsDtls.setPsngrPrtOfDsmbrktnName(settingLength(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnName(),256));
			}
			
			if(personOnBoardMod.get(g).getPrsnGenderCdd().equals("")|| personOnBoardMod.get(g).getPrsnGenderCdd()==null) {
				prsDtls.setPrsnGenderCdd(settingLength("NA",3));
			}else {
				prsDtls.setPrsnGenderCdd(settingLength(personOnBoardMod.get(g).getPrsnGenderCdd(),3));
			}
		
			if(personOnBoardMod.get(g).getPrsnDtOfBirth() .equals("") && personOnBoardMod.get(g).getPrsnDtOfBirth()==null ) {
				prsDtls.setPrsnDtOfBirth(personOnBoardMod.get(g).getPrsnDtOfBirth());
			}else {
				prsDtls.setPrsnDtOfBirth(personOnBoardMod.get(g).getPrsnDtOfBirth());
			}
			
			if(personOnBoardMod.get(g).getPrsnPlaceOfBirthName().equals("") || personOnBoardMod.get(g).getPrsnPlaceOfBirthName() == null) {
				prsDtls.setPrsnPlaceOfBirthName(settingLength("NA",35));
			}else {
				prsDtls.setPrsnPlaceOfBirthName(settingLength(personOnBoardMod.get(g).getPrsnPlaceOfBirthName(),35));
			}
			if(personOnBoardMod.get(g).getPrsnCntryOfBirthCdd().equals("") || personOnBoardMod.get(g).getPrsnCntryOfBirthCdd()== null) {
				prsDtls.setPrsnCntryOfBirthCdd(settingLength("NA",2));
			}else {
				prsDtls.setPrsnCntryOfBirthCdd(settingLength(personOnBoardMod.get(g).getPrsnCntryOfBirthCdd(),2));
			}
			
//		---------------------------------------------------------------------------------------------------------	
			PrsnIdSAM prsnIdclassObj = new PrsnIdSAM();
			if(personOnBoardMod.get(g).getPrsnIdDocExpiryDt().equals("") || personOnBoardMod.get(g).getPrsnIdDocExpiryDt()== null) {
				prsnIdclassObj.setPrsnIdDocExpiryDt(personOnBoardMod.get(g).getPrsnIdDocExpiryDt());
			}else {
				prsnIdclassObj.setPrsnIdDocExpiryDt(personOnBoardMod.get(g).getPrsnIdDocExpiryDt());
			}
		
			if(personOnBoardMod.get(g).getPrsnIdOrTravelDocIssuingNationCdd()== null ||personOnBoardMod.get(g).getPrsnIdOrTravelDocIssuingNationCdd().equals("") ) {
				prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("NA", 4));
			}else {
				prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength(personOnBoardMod.get(g).getPrsnIdOrTravelDocIssuingNationCdd(),4));
			}
			if(personOnBoardMod.get(g).getPrsnIdOrTravelDocNmbr()==null || personOnBoardMod.get(g).getPrsnIdOrTravelDocNmbr().equals("")){
				prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("NA",70));
			}else {
				prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength(personOnBoardMod.get(g).getPrsnIdOrTravelDocNmbr(),70));
			}
			if(personOnBoardMod.get(g).getPrsnIdOrTravelDocTypCdd()== null || personOnBoardMod.get(g).getPrsnIdOrTravelDocTypCdd().equals("")) {
				prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("NA",4));
			}else {
				prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength(personOnBoardMod.get(g).getPrsnIdOrTravelDocTypCdd(),4));
			}
			
//		-----------------------------------------------------------------------------------------------	
			VisaDtlsSAM visaDtlsSAMObj= new VisaDtlsSAM();
			if(personOnBoardMod.get(g).getPnrNumber() != null || !personOnBoardMod.get(g).getPnrNumber().equals("")) {
				visaDtlsSAMObj.setPnrNmbr(settingLength(personOnBoardMod.get(g).getPnrNumber(),20));
			}else {
				visaDtlsSAMObj.setPnrNmbr(settingLength(personOnBoardMod.get(g).getPnrNumber(),20));
			}
			if(personOnBoardMod.get(g).getVisa()==null || personOnBoardMod.get(g).getVisa().equals("")) {
				visaDtlsSAMObj.setPrsnVisa(settingLength(personOnBoardMod.get(g).getVisa(),70));
			}else {
				visaDtlsSAMObj.setPrsnVisa(settingLength(personOnBoardMod.get(g).getVisa(),70));
			}
			
			
			prsnOnBoard.setPrsnDtls(prsDtls);
			prsnOnBoard.setPrsnId(prsnIdclassObj);
			prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength(g+1+"",5));
			prsnOnBoard.setVisaDtls(visaDtlsSAMObj);
//			prsnOnBoard.setPrsnTypCdd(generatedFileNameOfJson);
			
			prsnOnBoardList.add(prsnOnBoard);
			}
		
//		====================	ShipItnry ========================
		
		ShipItnrySAM shipItny3 = new ShipItnrySAM();
		String prtOfCallCdd = null;
		String prtOfCallNm = null;
		String itnrySeq = null;
		if (service.getLastPort1() == null || service.getLastPort1().equals("") ) {
			prtOfCallCdd = null;
			prtOfCallNm = null;
			itnrySeq = null;
		} else {
			itnrySeq = "-3";
			prtOfCallCdd = service.getLastPort1();
			prtOfCallNm = service.getPort_of_call_name_last1();
		}
		shipItny3.setShipItnrySeq(settingLength(itnrySeq,6));// if not null -3
		shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItny3.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItny3);
		
		if (service.getLastPort2() == null || service.getLastPort2().equals("")) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
			
		} else {
			itnrySeq = "-2";
			prtOfCallCdd = service.getLastPort2();
			prtOfCallNm = service.getPort_of_call_name_last2();
		}
		ShipItnrySAM shipItnry2 = new ShipItnrySAM();
		shipItnry2.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry2.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItnry2);

		if (service.getLastPort3() == "" || service.getLastPort3().equals("") ) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
		} else {
			itnrySeq = "-1";

			prtOfCallCdd = service.getLastPort3();
			prtOfCallNm = service.getPort_of_call_name_last3();
		}
		ShipItnrySAM shipItnry1 = new ShipItnrySAM();
		shipItnry1.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry1.setPrtOfCallName(settingLength(prtOfCallNm,256));

		
		shipItnry.add(shipItnry1);

		ShipItnrySAM shipItnry0 = new ShipItnrySAM();
		shipItnry0.setShipItnrySeq(settingLength("0",6));
		shipItnry0.setPrtOfCallCdd(settingLength(service.getPortArrival(),10)); // blObj.get("Port of call sequence numbe"));
		shipItnry0.setPrtOfCallName(settingLength(service.getPort_of_call_name_portArrival(),256));
		shipItnry.add(shipItnry0);

		if (service.getNextport1() == null || service.getNextport1().equals("")) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
		} else {
			itnrySeq = "1";
			prtOfCallCdd = service.getNextport1();
			prtOfCallNm = service.getPort_of_call_name_nextport1();
			
		}
		ShipItnrySAM shipItnry11 = new ShipItnrySAM();
		shipItnry11.setShipItnrySeq(settingLength(itnrySeq , 6));
		shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry11.setPrtOfCallName(settingLength(prtOfCallNm,256));
		
		shipItnry.add(shipItnry11);

		if (service.getNextport2() == null || service.getNextport2().equals("") ) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm  =null;
		} else {
			itnrySeq = "2";
			prtOfCallCdd = service.getNextport2();
			prtOfCallNm = service.getPort_of_call_name_nextport2();
		}
		ShipItnrySAM shipItnry22 = new ShipItnrySAM();
		shipItnry22.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry22.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItnry22);
		
		if (service.getNextport3() == null || service.getNextport3().equals("")) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
		} else {
			itnrySeq = "3";
			prtOfCallCdd = service.getNextport3();
			prtOfCallNm = service.getPort_of_call_name_nextport3();
		}
		ShipItnrySAM shipItnry33 = new ShipItnrySAM();
		shipItnry33.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry33.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItnry33);
//		====================	ShipItnry ========================
		
		
		mster.setPrsnOnBoard(prsnOnBoardList); 
		
		DecRefSAM decRefClaObj = new DecRefSAM();
	    decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
	    decRefClaObj.setPrtofRptng(settingLength(service.getCustomCode(),10));
	    decRefClaObj.setJobNo(getSeqNo +1);
	    decRefClaObj.setJobDt(currDate);
	    decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
	    decRefClaObj.setMnfstNoRotnNo(settingLength(service.getRotnNo(),7));
	    decRefClaObj.setMnfstDtRotnDt(service.getRotnDate());
	    decRefClaObj.setVesselTypMvmt(settingLength("FI",2));
	    mster.setDecRef(decRefClaObj);
	    decRefClaObj = null;
		
		AuthPrsnSAM authPrsClassObj = new AuthPrsnSAM();
		authPrsClassObj.setSbmtrTyp(settingLength(service.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(service.getAgentCode(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength(service.getAuthReprsntvCd(),10)); //newly added field
		authPrsClassObj.setShpngLineCd(settingLength(service.getLineCode(),10)); 
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(service.getAgentCode(),10)); // LinNo:-211
		authPrsClassObj.setMasterName(settingLength(service.getMasterName(),30));// 21
		authPrsClassObj.setTrmnlOprtrCd(settingLength(service.getPodTerminalPort(),10)); // LinNo:-132
		authPrsClassObj.setShpngLineBondNmbr(settingLength(service.getShipping_line_bond_no_r(),10));
		mster.setAuthPrsn(authPrsClassObj);      // Correct
		authPrsClassObj = null;
		
		VoyageDtlsSAM voyageDtlsClassObj = new VoyageDtlsSAM();
		voyageDtlsClassObj.setVoyageNo(settingLength(service.getVoyage() , 10)); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(settingLength(service.getViaVcn(),35)); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted( settingLength(containerCount+"",5)); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd("3"); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(settingLength("GENERAL",30)); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(settingLength(service.getTotalItem() ,5)); // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(removeSlash(service.getArrivalDate() + "T" + getTime(service.getArrivalTime())));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(settingLength("0",4)); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(service.getNoOfCrew());
		mster.setVoyageDtls(voyageDtlsClassObj);  // Correct 
		voyageDtlsClassObj.setShipItnry(shipItnry);
		voyageDtlsClassObj = null;
		
		VesselDtlsSAM vesselDtls = new VesselDtlsSAM();
		vesselDtls.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1)); // Line 191
		if(service.getTypeTransportMeans().equals("imovsl")) {
			vesselDtls.setTypOfTrnsprtMeans(" "); // not found
		}else {
			vesselDtls.setTypOfTrnsprtMeans(settingLength(service.getTypeTransportMeans(),25));
		}
		vesselDtls.setTrnsprtMeansId(settingLength(service.getImoCode(),25));
		vesselDtls.setPurposeOfCall(settingLength( "1",3 )); // always hard coded
		vesselDtls.setShipTyp(settingLength("50",3)); // Line 192
		
		//keep it
//		vesselDtls.setVesselCd(newVessel);// keep it
//		vesselDtls.setGrossTonnage(generatedFileNameOfJson);
//		vesselDtls.setNatnltyOfShip(generatedFileNameOfJson);
//		vesselDtls.setNetTonnage(generatedFileNameOfJson);
//		vesselDtls.setPrtOfRegistry(generatedFileNameOfJson);
//		vesselDtls.setRegistryNmbr(generatedFileNameOfJson);
//		vesselDtls.setRegistryDt(generatedFileNameOfJson);

		mster.setVesselDtls(vesselDtls);  // Correct
		vesselDtls = null;
		
//		
//        ArvlDtlsSAM  arvlDtls = new ArvlDtlsSAM();
//        arvlDtls.setNmbrOfCrew(generatedFileNameOfJson);
//        arvlDtls.setNmbrOfPsngrs(generatedFileNameOfJson);
//        arvlDtls.setTotalNmbrOfPrsnsOnBoard(generatedFileNameOfJson);
//        arvlDtls.setTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr(generatedFileNameOfJson);
//        arvlDtls.setTotalNoOfCntrsLanded(generatedFileNameOfJson);
//        arvlDtls.setTotalNoOfCntrsLoaded(generatedFileNameOfJson);
//        arvlDtls.setTotalNoOfTrnsprtEqmtRprtdOnArvlDptr(generatedFileNameOfJson);
//		
//    	mster.setArvlDtls(arvlDtlsList);  // Correct 
//    	arvlDtls = null;
//    	
        
		
//		mastrCnsgmtDec.setItemDtls(itemDtls);
//		mastrCnsgmtDec.setItnry(itnry);
//		mastrCnsgmtDec.setLocCstm(locCstmClassObj);
//		mastrCnsgmtDec.setmCRef(mCRefClassObj);
//		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDocClassObj);
//		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsrClassObj);
//		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
//		mastrCnsgmtDec.setPrevRef(prevRef);
//		mastrCnsgmtDec.setTrnshpr(trnshpr);
//		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
//		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
//		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
//		List<MastrCnsgmtDecSAM> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSAM>();
//		mastrCnsgmtDecList.add(mastrCnsgmtDec);
//		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);  // Correct
		
		   // Correct


//  	TmSuprtDocsSAM tmSuprtDocs = new TmSuprtDocsSAM (); 
//		tmSuprtDocs.setBnefcryCdpublic(settingLength("",35));  
//		tmSuprtDocs.setDocRefNmbr(settingLength("",17));  Guru said to comment
//		tmSuprtDocs.setDocTypCd(settingLength("",6)); Guru said to comment
//		tmSuprtDocs.setIcegateUserid(settingLength("",15)); Guru said to comment
//		tmSuprtDocs.setIrnNmbr(settingLength("",14));  Guru said to comment
//		tmSuprtDocs.setRefSerialNo(settingLength("",5)); Guru said to comment
//		tmSuprtDocs.setSubSerialNoRef(""); Guru said to comment
//		tmSuprtDocs.setTagRef(settingLength("",5)); Guru said to comment
//		List<TmSuprtDocsSAM> tmSuprtDocsList = new LinkedList<TmSuprtDocsSAM>();
//		tmSuprtDocsList.add(tmSuprtDocs);
//		mster.setTmSuprtDocs(tmSuprtDocsList);
		
//		TmAdtnlDecSAM tmAdtnlDec = new TmAdtnlDecSAM();
//		tmAdtnlDec.setTagRef(settingLength("",5)); Guru said to comment
//		tmAdtnlDec.setRefSerialNo(settingLength("",5)); Guru said to comment
//		tmAdtnlDec.setInfoCd(settingLength("",35)); 	Guru said to comment
//		tmAdtnlDec.setInfoDt(""); Guru said to comment
//		tmAdtnlDec.setInfoMsr(settingLength("",5)); Guru said to comment
//		tmAdtnlDec.setInfoQualifier(settingLength("",10)); Guru said to comment
//		tmAdtnlDec.setInfoText(settingLength("",100)); guru said to comment
//		tmAdtnlDec.setInfoTyp(settingLength("",10)); 	Guru said to comment
//		List<TmAdtnlDecSAM> tmAdtnlDecList= new LinkedList<TmAdtnlDecSAM>();
//		tmAdtnlDecList.add(tmAdtnlDec);
//		mster.setTmAdtnlDec(tmAdtnlDecList);
//		
		org.setMaster(mster);
		return org;
	}
	
	public static String checkNull(String value ) {
		if( value == null) {
			return "";
		}
		return value;
	}
	
	public static String settingLength (String str, int num) {
		if(str == null || str.equals("")) {
			return "";
		}
		if(str.length()<=num){
			return str;
		}else{
			return str.substring(0,num);
		}
		
	}
	public static String settingLengthForDouble(String aField, int aintDigits,int aintDecimals){
        String val = aField+"";
        int dotIndex=val.indexOf('.');
        if(dotIndex > 0){
        	String cutDecimal = "";
            String fullValue = val.substring(0,dotIndex);  
            String decimalValue = val.substring(dotIndex+1);
            if(decimalValue.length()<3) {
            	cutDecimal = decimalValue.substring(0,decimalValue.length());
            }else {
            	cutDecimal = decimalValue.substring(0,aintDecimals);
            }
            aField = fullValue+"."+cutDecimal;
        }
    return aField;
}
	

	public static JsonMainObjctSDM getSDM(List<ImportGeneralManifestMod> blList,ImportGeneralManifestMod service,List<IGMPersonOnBoardMod> personOnBoardMod,
			List<IGMCrewEfctMod> crewEfctMod,List<IGMShipStoresMod> shipStoresMod,int getSeqNo) {

		 
		MasterSDM mster = new MasterSDM();
		
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = "F";
		String rpngEvent = "SDM";
		int i = 0;
		int containerCount = 0; 
		String voyage = isNull((String) service.getVoyage());
		String newVoyage = isNull((String) service.getNewVoyage());
		String pol = isNull((String) service.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) service.getVessel());
		String newVessel = isNull((String) service.getNewVessel());
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		
		List<MCRefSDM> mCRef = new LinkedList<MCRefSDM>();

	
		
		List<ShipItnrySDM> shipItnry = new LinkedList<ShipItnrySDM>();
		
//		TrnshprSDM TrnshprObj = new TrnshprSDM(); // New added
		List<VoyageTransportEquipmentSDM> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSDM>();
	
		List<PrsnOnBoardSDM> prsnOnBoardList = new LinkedList<PrsnOnBoardSDM>();
		List<CrewEfctSDM> cewEfctList = null;
		List<ShipStoresSDM> shipStoresList = new LinkedList<ShipStoresSDM>();
		List<MastrCnsgmtDecSDM> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSDM>();
		
		//===============================================
		for (ImportGeneralManifestMod blObj : blList) { 
			if (blObj != null && "true".equalsIgnoreCase(blObj.getIsBlSave())) {
				 System.out.println("Checked .. ");
			} else {
				continue;
			}
			MastrCnsgmtDecSDM mastrCnsgmtDec = new MastrCnsgmtDecSDM(); 
			HouseCargoDecSDM houseCargoDecSDMObj = new HouseCargoDecSDM();
			
//			List<ShipItnrySDM> shipItnry = new LinkedList<ShipItnrySDM>();
			List<ItnrySDM> itnry = new LinkedList<ItnrySDM>();
			List<TrnsprtDocSDM> trnsprtDoc = new LinkedList<TrnsprtDocSDM>();
		
//			List<PrevRefSDM> prevRef = new LinkedList<PrevRefSDM>();
			List<ItemDtlsSDM> itemDtls = new LinkedList<ItemDtlsSDM>();
			List<TrnsprtEqmtSDM> trnsprtEqmt = new LinkedList<TrnsprtEqmtSDM>();
			List<LocCstmSDM> locCstm = new LinkedList<LocCstmSDM>();
//			List<TrnshprSDM> trnshpr = new LinkedList<TrnshprSDM>();
			List<HouseCargoDecSDM> houseCargoDec = new LinkedList<HouseCargoDecSDM>();
			List<MCSuprtDocsSDM> mcSuprtDoc = new LinkedList<MCSuprtDocsSDM>();
			List<MCAdtnlDecSDM> mcAdtnlDec= new LinkedList<MCAdtnlDecSDM>();
			List<HCAdtnlDecSDM> hcAdtnlDec= new LinkedList<HCAdtnlDecSDM>();
			List<HCCrgoSuprtDocsSDM> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSDM>();
			List<TrnsprtDocMsrSDM> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSDM>();

			
			List<NotifyParty> notifyPartyDetailes = blObj.getNotifyParty();
			List<Consignee> consigneeDtls = blObj.getConsignee();
			List<MarksNumber> marksNumberDtls = blObj.getMarksNumber();
			List<Consigner> consignerDtls = blObj.getConsigner();
			List<ContainerDetails> containerDtls = blObj.getContainerDetailes();
			
			
			

			//===============================================
			List<HCRefSDM> hCRef = new LinkedList<HCRefSDM>();
			HCRefSDM hCRefObj = new HCRefSDM();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));
			hCRefObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); 
			hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),5));
			hCRef.add(hCRefObj);
			houseCargoDecSDMObj.sethCRef(hCRef);
			hCRefObj = null;

			//===============================================
			MCRefSDM mCRefClassObj = new MCRefSDM();
			mCRefClassObj.setLineNo(Integer.parseInt(blObj.getItemNumber())); // Line 60
			mCRefClassObj.setMstrBlNo(settingLength(blObj.getBl(),20));// Line 53
			mCRefClassObj.setMstrBlDt(removeSlash(blObj.getMasterBlDate())); // Line 53
			
//			mCRefClassObj.setConsolidatedIndctr(blObj.getConsolidated_indicator());// Line 76
//			mCRefClassObj.setPrevDec(settingLength(blObj.getPrevious_declaration(),4)); // Line77
			try {
				if( blObj.getHblArr().isEmpty() || blObj.isHbl()==false  ) {
					 mCRefClassObj.setConsolidatedIndctr("S");// Line 76   //TODO
				}else {
					 mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
				}
			}catch (Exception e) {
				mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
			}
			if(blObj.getHblCount() != 0) {
				mCRefClassObj.setPrevDec(("N"));
			}else {
				mCRefClassObj.setPrevDec(settingLength("S",4));
			}
			
//			mCRefClassObj.setPrevDec(settingLength(blObj.getPrevious_declaration(),4)); // Line77				//TODO  guru	
			mCRefClassObj.setConsolidatorPan("PAN:"+settingLength(service.getAgentCode(),16)); // Line 78		
			mastrCnsgmtDec.setmCRef(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
//			PrevRefSDM prevRefObj = new PrevRefSDM();
//			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
//			prevRefObj.setCrgoMvmt(blObj.getCargoMovmnt());
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4)); guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
//			prevRefObj.setSplitIndctr(settingLength(blObj.getSplit_indicator_list(),2)); guru said to comment
//			prevRefObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getNumber_of_packages(),16,6)); Guru said to comment
//			prevRefObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4)); Guru said to comment
			
			if(blObj.isHbl()==true) {
		//		prevRefObj.setCsnDt(blObj.getCsn_date());
		//		prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); 
//				prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
				if(blObj.getMcin() != null || blObj.getMcin() !="") {
		//			prevRefObj.setCinTyp(settingLength(blObj.getMcin(),4));	
				}else {
					if(blObj.getPcin() != null || blObj.getPcin() !="") {
		//				prevRefObj.setCinTyp(settingLength(blObj.getPcin(),4));		
					}
				}
				
		//		prevRefObj.setMcinPcin(generatedFileNameOfJson);
		//		prevRefObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6));
		//		prevRefObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4)); 
		
//			prevRef.add(prevRefObj);
//			houseCargoDecSDMObj.setPrevRef(prevRef);
			}

			//===============================================
			LocCstmSDM locCstmClassObj = new LocCstmSDM();
			
			locCstmClassObj.setFirstPrtOfEntry(blObj.getPod());
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6));// New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength(blObj.getPortOfDestination(),6));  // New added
			
			if(blObj.getPortOfDestination().substring(0, 2).equals("IN") && blObj.getPod().substring(0, 2).equals("IN")){
				locCstmClassObj.setTypOfCrgo(settingLength("EX",2)); // if both value in india base
			}

			locCstmClassObj.setItemTyp(settingLength("OT",2)); // Line 61
			locCstmClassObj.setCrgoMvmt(settingLength("TC",4)); // Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength("C",4)); // Line 59
			
	//		if(blObj.isHbl()==true) {
	//		locCstmClassObj.setNmbrOfPkgs(blObj.getTotal_number_of_packages());
	//		locCstmClassObj.setTypOfPackage(pol);
	//		}
			locCstm.add(locCstmClassObj);
			mastrCnsgmtDec.setLocCstm(locCstmClassObj);
			houseCargoDecSDMObj.setLocCstm(locCstm); 

//=========================================================================================================================
			TrnsprtDocSDM trnsprtDocClassObj = new TrnsprtDocSDM();
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));							//TODO  guru
			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getAcceptanceName(),256));			//TODO  guru
			trnsprtDocClassObj.setPrtOfReceiptCdd(settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getRecieptName(),256));		
//				trnsprtDocClassObj.setUcrTyp(settingLength(blObj.getUcr_type(),3));  Guru said to comment 
//				trnsprtDocClassObj.setUcrCd( settingLength(blObj.getUcr_code(),35));	 Guru said to comment

				for (NotifyParty notyObj : notifyPartyDetailes) {

					if ((blObj.getBl()).equals(notyObj.getBlNo())) {
						String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
								+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
						trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,70));
						trnsprtDocClassObj.setNotfdPartyCity( settingLength(notyObj.getCity(),70));
//						trnsprtDocClassObj.setNotfdPartyCntrySubDivName( settingLength(notyObj.getState(),35));
						trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
//						trnsprtDocClassObj.setNotfdPartyPstcd( settingLength(notyObj.getZip(),9));
//						trnsprtDocClassObj.setTypOfNotfdPartyCd( settingLength(notyObj.getCostumerCode(),3));
	      				trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(notyObj.getCostumerName());
					}
				}
//				trnsprtDocClassObj.setPanOfNotfdParty( settingLength(blObj.getPan_of_notified_party(),17));

//==================================================================================================================
		
//===============================================================================================================
				for (Consignee cnsneeDtl : consigneeDtls) {

					if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					{
						String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
								+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
						trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,120));
						trnsprtDocClassObj.setCnsgnesName( settingLength(cnsneeDtl.getCustomerName(),70));
						trnsprtDocClassObj.setCnsgneCity( settingLength(cnsneeDtl.getCity(),70));
//						trnsprtDocClassObj.setCnsgneCntrySubDivName(  settingLength(cnsneeDtl.getState(),35));
						trnsprtDocClassObj.setCnsgneCntryCd( settingLength(cnsneeDtl.getCountryCode(),2));
//						trnsprtDocClassObj.setCnsgnePstcd( settingLength(cnsneeDtl.getZip(),9));
//						trnsprtDocClassObj.setCnsgnesCd( settingLength(cnsneeDtl.getCustomerCode(),17));
//						trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
						
					}
				}

				//===============================================
				for (Consigner cnsnerDtls : consignerDtls) {

					if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
						String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
								+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
						// set all values in TrnsprtDoc Class Obj cnsgnrsName;
						trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
						trnsprtDocClassObj.setCnsgnrsName(settingLength(cnsnerDtls.getCustomerName(),70));
						trnsprtDocClassObj.setCnsgnrCity( settingLength(cnsnerDtls.getCity(),70));
						trnsprtDocClassObj.setCnsgnrCntrySubDivName( settingLength(cnsnerDtls.getState(),35));  //Guru said to comment
						trnsprtDocClassObj.setCnsgnrsCd( settingLength(cnsnerDtls.getCustomerCode(),17));
						trnsprtDocClassObj.setCnsgnrCdTyp("");
						 trnsprtDocClassObj.setCnsgnrCntrySubDivCd(blObj.getGstStateCode());
						trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
						trnsprtDocClassObj.setCnsgnrPstcd( settingLength(cnsnerDtls.getZip(),9)); 
//						trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
					}
				}
				trnsprtDoc.add(trnsprtDocClassObj);
				mastrCnsgmtDec.setTrnsprtDoc(trnsprtDocClassObj);
				houseCargoDecSDMObj.setTrnsprtDoc(trnsprtDoc);
				
//			
//			TrnshprObj.setTrnshprCd(settingLength("",10));
//			TrnshprObj.setTrnshprBond(settingLength("",10));
//			trnshpr.add(TrnshprObj);
//			houseCargoDecSDMObj.setTrnshpr(trnshpr);

			//===============================================
			TrnsprtDocMsrSDM trnsprtDocMsrClassObj = new TrnsprtDocMsrSDM();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getTotal_number_of_packages(),8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getPackage_kind());
			if(null != blObj.getGrosWeight() || ("").equals(blObj.getGrosWeight())) {
				trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrosWeight(),12,3));
			}else {
				trnsprtDocMsrClassObj.setGrossWeight("");
			}
			
//			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength("KGS",3));
//			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); Guru said to comment //not cleared by Guru
//			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3));  Guru said to comment // not cleared by Guru
//			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			if(blObj.getCargo_msmt()> 0) {
			trnsprtDocMsrClassObj.setGrossVolume (settingLengthForDouble(blObj.getGross_volume(),12,3));
     		}
			if(! "".equals(trnsprtDocMsrClassObj.getGrossVolume())&&  trnsprtDocMsrClassObj.getGrossVolume() != null ) {
				trnsprtDocMsrClassObj.setUnitOfVolume(settingLength("CBM",3));
			}
			
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl( settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsrClassObj);
			houseCargoDecSDMObj.setTrnsprtDocMsr(trnsprtDocMsr);
			
			//===============================================
		if(mCRefClassObj.getConsolidatedIndctr().equals("S")) {
			ItemDtlsSDM itemDtlsClassObj = new ItemDtlsSDM();
			itemDtlsClassObj.setHsCd(blObj.getCommdity_code());
		//	itemDtlsClassObj.setCrgoItemSeqNmbr(blObj.getCommodity_seq()+"");
			itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCommodity_seq()+"",5));	
			itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
//			if(blObj.getUno_code()) {
//				
//			}
			if(blObj.getDgFlag() != null && StringUtils.isNotBlank(blObj.getDgFlag()) ) {
				itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
				itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));	
			}else {
					itemDtlsClassObj.setUnoCd( settingLength("ZZZZZ",5));
					itemDtlsClassObj.setImdgCd( settingLength("ZZZ",3));	
			}
			itemDtlsClassObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6)); 
			itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getPackage_kind(),3));
			itemDtls.add(itemDtlsClassObj);
			mastrCnsgmtDec.setItemDtls(itemDtlsClassObj);
		
			}else {
				if(blObj.getHblCount()!= 0) {
					if(blObj.getConsolidatedIndicator().equals("H")) {
						ItemDtlsSDM itemDtlsClassObj = new ItemDtlsSDM();
						itemDtlsClassObj.setHsCd(blObj.getCommdity_code());
						//	itemDtlsClassObj.setCrgoItemSeqNmbr(blObj.getCommodity_seq()+"");
							itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCommodity_seq()+"",5));	
							itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
							itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
							itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),4));
							itemDtlsClassObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6)); 
							itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getPackage_kind(),3));
							itemDtls.add(itemDtlsClassObj);
							mastrCnsgmtDec.setItemDtls(itemDtlsClassObj);
						houseCargoDecSDMObj.setItemDtls(itemDtls);
					}
				}
				
			}
		
			//===============================================
			
			int j = 0 ;
			Set<String> containseSets= new HashSet<>();
			for (ContainerDetails ctnerDtl : containerDtls) {
				
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo()) && !containseSets.contains(ctnerDtl.getContainerNumber())) {
					j++;
					TrnsprtEqmtSDM trnsprtEqmtClassObj = new TrnsprtEqmtSDM();
					containseSets.add(ctnerDtl.getContainerNumber());
					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(j+"",5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3)); // alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getIsoCode(),4)); // optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
//					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256)); guru said to comment
//					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256)); guru said to comment
//					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
//					trnsprtEqmtClassObj.setEventDt(""); guru said to comment
//					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10)); guru said to comment
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(service.getAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
					trnsprtEqmt.add(trnsprtEqmtClassObj);
				}
			} // add to trnsprtDocMsr List
			mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
			houseCargoDecSDMObj.setTrnsprtEqmt(trnsprtEqmt);

			//===============================================
			ItnrySDM itnryClassObj = new ItnrySDM();
			if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("1",5)); 
			}else if (blObj.getPortOfLoading()!= null && blObj.getPod()!= null && blObj.getPortOfDestination() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("2",5)); 		
			}else if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null && 
					blObj.getPortOfDestination() != null && blObj.getPortOfDeschargedCfs() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("3",5)); 
			}
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));    //TODO  guru
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getNext_port_of_call_name(),256));		//TODO  guru
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));			//TODO  guru
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPod(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),4));
//			itnry.add(itnryClassObj);
			mastrCnsgmtDec.setItnry(itnryClassObj);
			houseCargoDecSDMObj.setItnry(itnry);
//			itnryClassObj = null;
			
//===================================================================================================================
			//HCCrgoSuprtDocsSDM crgoSuprtDocs = new HCCrgoSuprtDocsSDM(); 
//			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));  Guru said to comment
//			crgoSuprtDocs.setDocRefNmbr(settingLength("",17));  Guru said to comment
//			crgoSuprtDocs.setDocTypCd(settingLength("",6));  Guru said to comment
//			crgoSuprtDocs.setIcegateUserid(settingLength("",15)); Guru said to comment
//			crgoSuprtDocs.setIrnNmbr(settingLength("",14));  Guru said to comment
//			crgoSuprtDocs.setRefSerialNo(settingLength("",5)); Guru said to comment
//			crgoSuprtDocs.setSubSerialNoRef(""); Guru said to comment
//			crgoSuprtDocs.setTagRef(settingLength("",5)); Guru said to comment
//			crgoSuprtDoc.add(null);
//			houseCargoDecSDMObj.sethCCrgoSuprtDocs(null);

			//===============================================
//			HCAdtnlDecSDM adtnlDec = new HCAdtnlDecSDM();
//			adtnlDec.setTagRef(settingLength("",5)); Guru said to comment
//			adtnlDec.setRefSerialNo(settingLength("",5)); Guru said to comment
//			adtnlDec.setInfoCd(settingLength("",35)); 	Guru said to comment
//			adtnlDec.setInfoDt(""); Guru said to comment
//			adtnlDec.setInfoMsr(settingLength("",5)); Guru said to comment
//			adtnlDec.setInfoQualifier(settingLength("",10)); Guru said to comment
//			adtnlDec.setInfoText(settingLength("",100)); guru said to comment
//			adtnlDec.setInfoTyp(settingLength("",10)); 	Guru said to comment
			
//			hcAdtnlDec.add(null);
//			houseCargoDecSDMObj.setHcAdtnlDec(null);

			//===============================================
//			MCSuprtDocsSDM mcSuprtDocs = new MCSuprtDocsSDM (); 
//			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));  Guru said to comment
//			mcSuprtDocs.setDocRefNmbr(settingLength("",17));  Guru said to comment
//			mcSuprtDocs.setDocTypCd(settingLength("",6));  Guru said to comment
//			mcSuprtDocs.setIcegateUserid(settingLength("",15)); Guru said to comment
//			mcSuprtDocs.setIrnNmbr(settingLength("",14));
//			mcSuprtDocs.setRefSerialNo(settingLength("",5)); Guru said to comment
//			mcSuprtDocs.setSubSerialNoRef(""); Guru said to comment
//			mcSuprtDocs.setTagRef(settingLength("",5)); Guru said to comment
//			mcSuprtDoc.add(null);

			//===============================================
//			MCAdtnlDecSDM mcAdtnlDecs = new MCAdtnlDecSDM();
//			mcAdtnlDecs.setTagRef(settingLength("",5)); Guru said to comment
//			mcAdtnlDecs.setRefSerialNo(settingLength("",5)); Guru said to comment
//			mcAdtnlDecs.setInfoCd(settingLength("",35));	Guru said to comment
//			mcAdtnlDecs.setInfoDt(""); Guru said to comment
//			mcAdtnlDecs.setInfoMsr(settingLength("",5)); Guru said to comment
//			mcAdtnlDecs.setInfoQualifier(settingLength("",10)); Guru said to comment
//			mcAdtnlDecs.setInfoText(settingLength("",100)); guru said to comment
//			mcAdtnlDecs.setInfoTyp(settingLength("",10)); 	Guru said to comment
			
//			mcAdtnlDec.add(null);
			
			//===============================================
			houseCargoDec.add(houseCargoDecSDMObj);
//===============================================================================================================================
			for (ContainerDetails cntnerDtl : containerDtls) {

//				System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));
				containerCount++;
				VoyageTransportEquipmentSDM voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSDM();
				
				voyageTransportEquipmentClassObj.setEquipmentSequenceNo(containerCount+"");
				voyageTransportEquipmentClassObj.setEquipmentId(settingLength(cntnerDtl.getContainerNumber(),17));
				voyageTransportEquipmentClassObj.setEquipmentType(settingLength("CN",3));
				voyageTransportEquipmentClassObj.setEquipmentSize(settingLength(cntnerDtl.getIsoCode(),4));
		
				voyageTransportEquipmentClassObj.setEquipmentLoadStatus(settingLength(cntnerDtl.getEquipmentLoadStatus(),3));
//				voyageTransportEquipmentClassObj.setAdditionalEquipmentHold(settingLength("",256)); Guru said to comment
//				voyageTransportEquipmentClassObj.setFinalDestinationLocation(settingLength("",10)); Guru said to comment
//				voyageTransportEquipmentClassObj.setEventDt("");  Guru said to comment
				
				voyageTransportEquipmentClassObj.setEquipmentSealType(settingLength(cntnerDtl.getEquipment_seal_type(),5));
				voyageTransportEquipmentClassObj.setEquipmentSealNumber(settingLength(cntnerDtl.getContainerSealNumber(),15));
//				voyageTransportEquipmentClassObj.setOtherEquipmentId(settingLength("",256));
//				voyageTransportEquipmentClassObj.setEquipmentStatus(settingLength("",3));  
				
				voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
				
				voyageTransportEquipmentClassObj.setContainerAgentCode(settingLength(service.getAgentCode(),17));
				voyageTransportEquipmentClassObj.setContainerWeight(cntnerDtl.getContainerWeight());
				voyageTransportEquipmentClassObj.setTotalNumberOfPackages(settingLength(cntnerDtl.getTotalNumberOfPackagesInContainer(),8));
				voyageTransportEquipmentClassObj.setStowagePositionOfContainer(settingLength(service.getStowagePosition(),17));
//				voyageTransportEquipmentClassObj.setContainerBondFlag(settingLength("",1)); guru said to comment
				
				voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
			}
			mster.setVoyageTransportEquipment(voyageTransportEquipmentList);   // Correct
			//===============================================
			

			
			mastrCnsgmtDecList.add(mastrCnsgmtDec);
			mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
			houseCargoDec.add(houseCargoDecSDMObj);
			if (blObj.isHbl()== true) {
				mastrCnsgmtDec.setHouseCargoDec(houseCargoDecSDMObj);
			}
			
		}
		
//	==============================End of loop=======================================	
	
		for(int g = 0 ;g<personOnBoardMod.size();g++) {
			
			PrsnOnBoardSDM prsnOnBoard = new PrsnOnBoardSDM();
			prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength(g+1+"",5));
			
			PrsnDtlsSDM prsDtls = new PrsnDtlsSDM();
			
			if(personOnBoardMod.get(g).getPrsnTypCdd()=="" && personOnBoardMod.get(g).getPrsnTypCdd() == null) {
				prsDtls.setPrsnTypCdd(settingLength("NA",3));
			}else {
				prsDtls.setPrsnTypCdd(settingLength(personOnBoardMod.get(g).getPrsnTypCdd(),3));
			}
			
			if(personOnBoardMod.get(g).getPrsnFamilyName()=="" && personOnBoardMod.get(g).getPrsnFamilyName() == null) {
				prsDtls.setPrsnFamilyName(settingLength("NA",70));
			}else {
				prsDtls.setPrsnFamilyName(settingLength(personOnBoardMod.get(g).getPrsnFamilyName(),70));
			}
			
			if(personOnBoardMod.get(g).getPrsnGivenName()== "" && personOnBoardMod.get(g).getPrsnGivenName() == null) {
				prsDtls.setPrsnGivenName(settingLength("NA",70));
			}else {
				prsDtls.setPrsnGivenName(settingLength(personOnBoardMod.get(g).getPrsnGivenName(),70));
			}
			
			if(personOnBoardMod.get(g).getPrsnNatnltyCdd()=="" && personOnBoardMod.get(g).getPrsnNatnltyCdd()==null) {
				prsDtls.setPrsnNatnltyCdd(settingLength("NA",2));
			}else {
				prsDtls.setPrsnNatnltyCdd(settingLength(personOnBoardMod.get(g).getPrsnNatnltyCdd(),2));
			}
			
			if(personOnBoardMod.get(g).getPsngrInTransitIndctr()=="" && personOnBoardMod.get(g).getPsngrInTransitIndctr() == null) {
				prsDtls.setPsngrInTransitIndctr(settingLength("NA",1));
			}else {
				prsDtls.setPsngrInTransitIndctr(settingLength(personOnBoardMod.get(g).getPsngrInTransitIndctr(),1));
			}
			
			if(personOnBoardMod.get(g).getCrewmemberRankOrRatingCdd()=="" || personOnBoardMod.get(g).getCrewmemberRankOrRatingCdd()==null) {
				prsDtls.setCrewmemberRankOrRatingCdd("NA");
			}else {
				prsDtls.setCrewmemberRankOrRatingCdd(personOnBoardMod.get(g).getCrewmemberRankOrRatingCdd());
			}
			
			if(personOnBoardMod.get(g).getCrewmemberRankOrRating()==""||personOnBoardMod.get(g).getCrewmemberRankOrRating()== null) {
				prsDtls.setCrewmemberRankOrRatingName(settingLength("NA",10));
			}else {
				prsDtls.setCrewmemberRankOrRatingName(settingLength(personOnBoardMod.get(g).getCrewmemberRankOrRating(),10));
			}
			
			if(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnCdd()=="" || personOnBoardMod.get(g).getPsngrPrtOfEmbrktnCdd()== null) {
				prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("NA",5));
			}else {
				prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnCdd(),5));
			}
			
			if(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnName()=="" || personOnBoardMod.get(g).getPsngrPrtOfEmbrktnName()== null) {
				prsDtls.setPsngrPrtOfEmbrktnName(settingLength("NA",256));
			}else {
				prsDtls.setPsngrPrtOfEmbrktnName(settingLength(personOnBoardMod.get(g).getPsngrPrtOfEmbrktnName(),256));
			}
		
			if(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd()=="" || personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd()==null) {
				prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd(),5));
			}else {
				prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnCdd(),5));
			}
			
			if(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnName()=="" || personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnName()==null) {
				prsDtls.setPsngrPrtOfDsmbrktnName(settingLength("NA",256));
			}else {
				prsDtls.setPsngrPrtOfDsmbrktnName(settingLength(personOnBoardMod.get(g).getPsngrPrtOfDsmbrktnName(),256));
			}
			
			if(personOnBoardMod.get(g).getPrsnGenderCdd()== "" || personOnBoardMod.get(g).getPrsnGenderCdd()==null) {
				prsDtls.setPrsnGenderCdd(settingLength("NA",3));
			}else {
				prsDtls.setPrsnGenderCdd(settingLength(personOnBoardMod.get(g).getPrsnGenderCdd(),3));
			}
		
			if(personOnBoardMod.get(g).getPrsnDtOfBirth() =="" && personOnBoardMod.get(g).getPrsnDtOfBirth()==null ) {
				prsDtls.setPrsnDtOfBirth(personOnBoardMod.get(g).getPrsnDtOfBirth());
			}else {
				prsDtls.setPrsnDtOfBirth(personOnBoardMod.get(g).getPrsnDtOfBirth());
			}
			
			if(personOnBoardMod.get(g).getPrsnPlaceOfBirthName()=="" || personOnBoardMod.get(g).getPrsnPlaceOfBirthName() == null) {
				prsDtls.setPrsnPlaceOfBirthName(settingLength("NA",35));
			}else {
				prsDtls.setPrsnPlaceOfBirthName(settingLength(personOnBoardMod.get(g).getPrsnPlaceOfBirthName(),35));
			}
			if(personOnBoardMod.get(g).getPrsnCntryOfBirthCdd() == "" || personOnBoardMod.get(g).getPrsnCntryOfBirthCdd()== null) {
				prsDtls.setPrsnCntryOfBirthCdd(settingLength("NA",2));
			}else {
				prsDtls.setPrsnCntryOfBirthCdd(settingLength(personOnBoardMod.get(g).getPrsnCntryOfBirthCdd(),2));
			}
			prsnOnBoard.setPrsnDtls(prsDtls);
			
			//=================================================================================================
			PrsnIdSDM prsnIdclassObj = new PrsnIdSDM();
			
			if(personOnBoardMod.get(g).getPrsnIdDocExpiryDt()== null || personOnBoardMod.get(g).getPrsnIdDocExpiryDt()== " ") {
				prsnIdclassObj.setPrsnIdDocExpiryDt(personOnBoardMod.get(g).getPrsnIdDocExpiryDt());
			}else {
				prsnIdclassObj.setPrsnIdDocExpiryDt(personOnBoardMod.get(g).getPrsnIdDocExpiryDt());
			}
		
			if(personOnBoardMod.get(g).getPrsnIdOrTravelDocIssuingNationCdd()== null ||personOnBoardMod.get(g).getPrsnIdOrTravelDocIssuingNationCdd()== " " ) {
				prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("NA",2));
			}else {
				prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength(personOnBoardMod.get(g).getPrsnIdOrTravelDocIssuingNationCdd(),2));
			}
			if(personOnBoardMod.get(g).getPrsnIdOrTravelDocNmbr()==null || personOnBoardMod.get(g).getPrsnIdOrTravelDocNmbr()==" "){
				prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("NA",70));
			}else {
				prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength(personOnBoardMod.get(g).getPrsnIdOrTravelDocNmbr(),70));
			}
			if(personOnBoardMod.get(g).getPrsnIdOrTravelDocTypCdd()== null || personOnBoardMod.get(g).getPrsnIdOrTravelDocTypCdd()==" ") {
				prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("NA",3));
			}else {
				prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength(personOnBoardMod.get(g).getPrsnIdOrTravelDocTypCdd(),3));
			}
			prsnOnBoard.setPrsnId(prsnIdclassObj);
//-------------------------------------------------------------------------------------------------------------------------------------------			
			VisaDtlsSDM dtlsSDM = new VisaDtlsSDM();
			if(personOnBoardMod.get(g).getPnrNumber()== null || personOnBoardMod.get(g).getPnrNumber()== " ") {
				dtlsSDM.setPnrNmbr(settingLength(personOnBoardMod.get(g).getPnrNumber(),20));
			}else {
				dtlsSDM.setPnrNmbr(settingLength(personOnBoardMod.get(g).getPnrNumber(),20));
			}
			if(personOnBoardMod.get(g).getVisa()==null || personOnBoardMod.get(g).getVisa()==" ") {
				dtlsSDM.setPrsnVisa(settingLength(personOnBoardMod.get(g).getVisa(),70));
			}else {
				dtlsSDM.setPrsnVisa(settingLength(personOnBoardMod.get(g).getVisa(),70));
			}
			prsnOnBoard.setVisaDtls(dtlsSDM);
//-------------------------------------------------------------------------------------------------------------------------------------------			
			for(int l = 0;l<crewEfctMod.size();l++) {
			
					cewEfctList = new LinkedList<CrewEfctSDM>();
					CrewEfctSDM cewEfct = new CrewEfctSDM();
					cewEfct.setCrewEfctDescCdd(settingLength(crewEfctMod.get(l).getCrewEfctDescCdd(),3));
					cewEfct.setCrewEfctQntyOnbrd(settingLengthForDouble(crewEfctMod.get(l).getCrewEfctQntyOnbrd(),16,6));
					cewEfct.setCrewEfctQntyOnbrdCd(settingLength(crewEfctMod.get(l).getCrewEfctQntyCdOnbrd(),3));
					cewEfct.setCrewEfctsDesc(settingLength(crewEfctMod.get(l).getCrewEfctsDesc(),256));
					cewEfct.setCrewEfctsSeqNmbr(settingLength(l+1+"",5));
					cewEfctList.add(cewEfct);
					if(g==l) {
					prsnOnBoard.setCrewEfct(cewEfct);
					break;
					}
			}		
			
			prsnOnBoardList.add(prsnOnBoard);
		} 
//		
		for(int l = 0;l<shipStoresMod.size();l++) {
			ShipStoresSDM shipStores = new ShipStoresSDM();
			shipStores.setSeqNmbr(settingLength(l+1+"",5));
			shipStores.setArticleNameCdd(settingLength(shipStoresMod.get(l).getArticleNameCdd(),18));
			shipStores.setArticleNameText(settingLength(shipStoresMod.get(l).getArticleNameText(),512));
			shipStores.setLocOnbrdText(settingLength(shipStoresMod.get(l).getLocOnbrdText(),256));
			shipStores.setQntyCdOnbrd( settingLength(shipStoresMod.get(l).getQntyCdOnbrd(),3));
			shipStores.setQntyOnbrd(settingLengthForDouble(shipStoresMod.get(l).getQntyOnbrd(),16,6));
			shipStoresList.add(shipStores);
		}
		
		ShipItnrySDM shipItny3 = new ShipItnrySDM();
		String prtOfCallCdd = null;
		String prtOfCallNm = null;
		String itnrySeq = null;
		if (service.getLastPort1() == null || service.getLastPort1().equals("") ) {
			prtOfCallCdd = null;
			prtOfCallNm = null;
			itnrySeq = null;
		} else {
			itnrySeq = "-3";
			prtOfCallCdd = service.getLastPort1();
			prtOfCallNm = service.getPort_of_call_name_last1();
		}
		shipItny3.setShipItnrySeq(settingLength(itnrySeq,6));// if not null -3
		shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItny3.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItny3);
		
		if (service.getLastPort2() == null || service.getLastPort2().equals("")) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
			
		} else {
			itnrySeq = "-2";
			prtOfCallCdd = service.getLastPort2();
			prtOfCallNm = service.getPort_of_call_name_last2();
		}
		ShipItnrySDM shipItnry2 = new ShipItnrySDM();
		shipItnry2.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry2.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItnry2);

		if (service.getLastPort3() == "" || service.getLastPort3().equals("") ) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
		} else {
			itnrySeq = "-1";

			prtOfCallCdd = service.getLastPort3();
			prtOfCallNm = service.getPort_of_call_name_last3();
		}
		ShipItnrySDM shipItnry1 = new ShipItnrySDM();
		shipItnry1.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry1.setPrtOfCallName(settingLength(prtOfCallNm,256));

		
		shipItnry.add(shipItnry1);

		ShipItnrySDM shipItnry0 = new ShipItnrySDM();
		shipItnry0.setShipItnrySeq(settingLength("0",6));
		shipItnry0.setPrtOfCallCdd(settingLength(service.getPortArrival(),10)); // blObj.get("Port of call sequence numbe"));
		shipItnry0.setPrtOfCallName(settingLength(service.getPort_of_call_name_portArrival(),256));
		shipItnry.add(shipItnry0);

		if (service.getNextport1() == null || service.getNextport1().equals("")) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
		} else {
			itnrySeq = "1";
			prtOfCallCdd = service.getNextport1();
			prtOfCallNm = service.getPort_of_call_name_nextport1();
			
		}
		ShipItnrySDM shipItnry11 = new ShipItnrySDM();
		shipItnry11.setShipItnrySeq(settingLength(itnrySeq , 6));
		shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry11.setPrtOfCallName(settingLength(prtOfCallNm,256));
		
		shipItnry.add(shipItnry11);

		if (service.getNextport2() == null || service.getNextport2().equals("") ) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm  =null;
		} else {
			itnrySeq = "2";
			prtOfCallCdd = service.getNextport2();
			prtOfCallNm = service.getPort_of_call_name_nextport2();
		}
		ShipItnrySDM shipItnry22 = new ShipItnrySDM();
		shipItnry22.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry22.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItnry22);
		
		if (service.getNextport3() == null || service.getNextport3().equals("")) {
			prtOfCallCdd = null;
			itnrySeq = null;
			prtOfCallNm = null;
		} else {
			itnrySeq = "3";
			prtOfCallCdd = service.getNextport3();
			prtOfCallNm = service.getPort_of_call_name_nextport3();
		}
		ShipItnrySDM shipItnry33 = new ShipItnrySDM();
		shipItnry33.setShipItnrySeq(settingLength(itnrySeq,6));
		shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
		shipItnry33.setPrtOfCallName(settingLength(prtOfCallNm,256));
		shipItnry.add(shipItnry33);
		
//	--------------------------------------------------------------------------------------------------	
		
		mster.setPrsnOnBoard(prsnOnBoardList);
		mster.setShipStore(shipStoresList);
		
		
		// now add all List to relevant class
		DecRefSDM decRefClaObj = new DecRefSDM();
	    decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
	    decRefClaObj.setPrtofRptng(settingLength(service.getCustomCode(),10));
	    decRefClaObj.setJobNo(getSeqNo +1);
	    decRefClaObj.setJobDt(currDate);
	    decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
	    decRefClaObj.setMnfstNoRotnNo(settingLength(service.getRotnNo(),7));
	    decRefClaObj.setMnfstDtRotnDt(service.getRotnDate()	);
	    decRefClaObj.setVesselTypMvmt(settingLength("FI",2));
	    mster.setDecRef(decRefClaObj);
	    decRefClaObj = null;
		
	
//		mastrCnsgmtDec.setItemDtls(itemDtls);
//		mastrCnsgmtDec.setItnry(itnry);
//		mastrCnsgmtDec.setLocCstm(locCstmClassObj);
//		mastrCnsgmtDec.setmCRef(mCRefClassObj);
//		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDocClassObj);
//		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsrClassObj);
//		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
//		mastrCnsgmtDec.setPrevRef(prevRefObj);
//		mastrCnsgmtDec.setTrnshpr(trnshpr);
//		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
//		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
//		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
		
//		mastrCnsgmtDecList.add(mastrCnsgmtDec);
//		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);

		VoyageDtlsSDM voyageDtlsClassObj = new VoyageDtlsSDM();
		voyageDtlsClassObj.setVoyageNo(settingLength(service.getVoyage() , 10)); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(settingLength(service.getViaVcn(),35)); // Line 193
		voyageDtlsClassObj.setTotalNmbrOfLines(service.getTotalNmbrOfLines()); //newly added field
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted( settingLength(containerCount+" ",5)); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd("3"); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(settingLength("GENERAL",30)); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(settingLength(service.getTotalItem() ,5));  // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfDptr(removeSlash(service.getDepartureDate() + "T" + getTime(service.getDepartureTime())));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(settingLength("0",4)); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(service.getNoOfCrew());
		voyageDtlsClassObj.setShipItnry(shipItnry);
		mster.setVoyageDtls(voyageDtlsClassObj);
		 
		
		// ---------------------------------------------------
		VesselDtlsSDM vesselDtls = new VesselDtlsSDM();
		vesselDtls.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1)); // Line 191
		if(service.getTypeTransportMeans().equals("imovsl")) {
			vesselDtls.setTypOfTrnsprtMeans(" "); // not found
		}else {
			vesselDtls.setTypOfTrnsprtMeans(settingLength(service.getTypeTransportMeans(),25));
		}
//		vesselDtls.setTypOfTrnsprtMeans(settingLength(service.getTypeTransportMeans(),25)); // not found
		vesselDtls.setTrnsprtMeansId(settingLength(service.getImoCode(),25));
		vesselDtls.setShipTyp(settingLength("50",3)); // Line 192
		vesselDtls.setPurposeOfCall(settingLength( "1",3 )); // always hard coded
		mster.setVesselDtls(vesselDtls); 
		
		// ----------------------------
		AuthPrsnSDM authPrsClassObj = new AuthPrsnSDM();
		authPrsClassObj.setSbmtrTyp(settingLength(service.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(service.getAgentCode(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength(service.getAuthReprsntvCd(),10)); //
		authPrsClassObj.setShpngLineCd(settingLength(service.getLineCode(),10)); // VALUE AL WAYS RCL
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(service.getAgentCode(),10)); // LinNo:-211
		authPrsClassObj.setMasterName(settingLength(service.getMasterName(),30));// 21
		authPrsClassObj.setShpngLineBondNmbr(settingLength(service.getShipping_line_bond_no_r(),10)); // LinNo:-190
		authPrsClassObj.setTrmnlOprtrCd(settingLength(service.getPolTerminalPort(),10)); // LinNo:-132	- polTerminalPort
        mster.setAuthPrsn(authPrsClassObj);		
	 
        
		//===============================================
		
		
		
		//===============================================
//		TmSuprtDocsSDM tmSuprtDocs = new TmSuprtDocsSDM (); 
//		List<TmSuprtDocsSDM> tmSuprtDocsList = new LinkedList<TmSuprtDocsSDM>();
//		tmSuprtDocs.setBnefcryCdpublic(settingLength("",35));  Guru said to comment
//		tmSuprtDocs.setDocRefNmbr(settingLength("",17));  Guru said to comment
//		tmSuprtDocs.setDocTypCd(settingLength("",6));  Guru said to comment
//		tmSuprtDocs.setIcegateUserid(settingLength("",15)); Guru said to comment
//		tmSuprtDocs.setIrnNmbr(settingLength("",14));  Guru said to comment
//		tmSuprtDocs.setRefSerialNo(settingLength("",5)); Guru said to comment
//		tmSuprtDocs.setSubSerialNoRef(""); Guru said to comment
//		tmSuprtDocs.setTagRef(settingLength("",5)); Guru said to comment
		
//		tmSuprtDocsList.add(null);
//		mster.setTmSuprtDocs(null);
//		
		//===============================================
//		TmAdtnlDecSDM tmAdtnlDec = new TmAdtnlDecSDM();
//		tmAdtnlDec.setTagRef(settingLength("",5)); Guru said to comment
//		tmAdtnlDec.setRefSerialNo(settingLength("",5)); Guru said to comment
//		tmAdtnlDec.setInfoCd(settingLength("",35)); 	Guru said to comment
//		tmAdtnlDec.setInfoDt(""); Guru said to comment
//		tmAdtnlDec.setInfoMsr(settingLength("",5)); Guru said to comment
//		tmAdtnlDec.setInfoQualifier(settingLength("",10)); Guru said to comment
//		tmAdtnlDec.setInfoText(settingLength("",100));guru said to comment
//		tmAdtnlDec.setInfoTyp(settingLength("",10)); 	Guru said to comment
//		List<TmAdtnlDecSDM> tmAdtnlDecList= new LinkedList<TmAdtnlDecSDM>();
//		tmAdtnlDecList.add(null);
//		mster.setTmAdtnlDec(null);
		
		//===============================================
		//mster.setVoyageDtls(voyageDtlsList);
		//mster.setVesselDtls(vesselDtlsList);
		//mster.setAuthPrsn(authPrsnList);
		//mster.setDecRef(decRefList);
//		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
		//mster.setShipStores(shipStoresList);
	 
		
		//===============================================
		HeaderFieldSDM headerFieldClassObj = new HeaderFieldSDM();
		headerFieldClassObj.setSenderID(settingLength(service.getSenderId(),20));
		headerFieldClassObj.setReceiverID(settingLength(service.getSenderId(),20));
		/* headerFieldClassObj.setReceiverID(service.getPod()); */
		headerFieldClassObj.setVersionNo("SDM1102");
		headerFieldClassObj.setIndicator("T");
		headerFieldClassObj.setMessageID("SACHM22");
		headerFieldClassObj.setSequenceOrControlNumber(getSeqNo+1);// old screen String sId (
		headerFieldClassObj.setDate(getTimeHeader());
		headerFieldClassObj.setTime("T" + getIsdTime());
		headerFieldClassObj.setReportingEvent("SDM");
		
		// -------------------------------------------------
	
		JsonMainObjctSDM org = new JsonMainObjctSDM();
		org.setHeaderField(headerFieldClassObj);
		org.setMaster(mster);
		
		return org;
	
	}
	
	public static JsonMainObjctSEI getSEI(List<ImportGeneralManifestMod> blList,ImportGeneralManifestMod service,List<IGMPersonOnBoardMod> personOnBoardMod) {

		ImportGeneralManifestMod objForm = blList.get(0);
		MasterSEI mster = new MasterSEI();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";

		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SEI";

		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}

		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSEI> itemDtls = new LinkedList<ItemDtlsSEI>();
		List<TrnsprtEqmtSEI> trnsprtEqmt = new LinkedList<TrnsprtEqmtSEI>();
		List<LocCstmSEI> locCstm = new LinkedList<LocCstmSEI>();
		List<MCRefSEI> mCRef = new LinkedList<MCRefSEI>();
		List<TrnsprtDocMsrSEI> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSEI>();
		List<ShipItnrySEI> shipItnry = new LinkedList<ShipItnrySEI>();
		List<ItnrySEI> itnry = new LinkedList<ItnrySEI>();
		List<TrnsprtDocSEI> trnsprtDoc = new LinkedList<TrnsprtDocSEI>();
		
		// =============================================================
		for (ImportGeneralManifestMod blObj : blList) {
			if (blObj != null && "true".equalsIgnoreCase(blObj.getIsBlSave())) {
				 System.out.println("checked.. ");
			} else {
				continue;
			}
		VesselDtlsSEI vesselDtls = new VesselDtlsSEI();
		vesselDtls.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),1)); // Line 191
		vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25)); // not found
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		vesselDtls.setShipTyp(service.getShip_type()); // Line 192
		mster.setVesselDtls(vesselDtls);
		
		// =============================================================
		
		ArvlDtlsSEI arvlDtls = new ArvlDtlsSEI(); // Write by nitun

		//arvlDtls.setLightHouseDues(service.getLightDue()); // Line 191
		arvlDtls.setNmbrOfPsngrs(service.getNoOfPassenger()); // not found
		arvlDtls.setTotalNmbrOfPrsnsOnBoard(service.getPassengerList());
		arvlDtls.setNmbrOfCrew(service.getNoOfCrew());
		//arvlDtls.setTotalNoOfCntrsLanded("");
		//arvlDtls.setTotalNoOfCntrsLoaded("");
		arvlDtls.setTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr(service.getTotal_no_of_tran_s_cont_repo_on_ari_dep());
		arvlDtls.setTotalNoOfTrnsprtEqmtRprtdOnArvlDptr(
				service.getTotal_no_of_transport_equipment_reported_on_arrival_departure());
		mster.setArvlDtls(arvlDtls);
		
		// =============================================================
		
		AuthPrsnSEI authPrsClassObj = new AuthPrsnSEI();
		authPrsClassObj.setSbmtrTyp(settingLength(service.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(service.getSubmitter_code(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength(service.getAuthReprsntvCd(),10));  //
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(service.getAuthorized_sea_carrier_code(),10)); // LinNo:-211
		authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
		authPrsClassObj.setTrmnlOprtrCd(settingLength(service.getCustomTerminalCode(),10)); // LinNo:-132
		mster.setAuthPrsn(authPrsClassObj);

		// =============================================================
		
		DecRefSEI decRefClaObj = new DecRefSEI();
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(service.getPod(),10)); // value from old screen [Pod]
		decRefClaObj.setJobNo(settingLength(service.getJobNo(),7));
		decRefClaObj.setJobDt(service.getJobDate());
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength(service.getRotnNo(),7)); //
		decRefClaObj.setMnfstDtRotnDt(service.getRotnDate()); //
		decRefClaObj.setVesselTypMvmt(settingLength(service.getVessel_type_movement(),2)); //
		//List<DecRefSEI> decRefList = new LinkedList<DecRefSEI>();
		//decRefList.add(decRefClaObj);
		mster.setDecRef(decRefClaObj);

		// =============================================================
		 
		List<PrsnOnBoardSEI> prsnOnBoardList = new LinkedList<PrsnOnBoardSEI>();
		for (int i =0; i<personOnBoardMod.size();i++) {
			if(personOnBoardMod.get(i).getBlNo().equals(blObj.getBl())) {
				PrsnOnBoardSEI prsnOnBoard = new PrsnOnBoardSEI();
				prsnOnBoard.setPnrNmbr(settingLength(personOnBoardMod.get(i).getPnrNumber(),20));
				prsnOnBoard.setPrsnVisa(settingLength(personOnBoardMod.get(i).getVisa(),70));
				prsnOnBoardList.add(prsnOnBoard);
				mster.setPrsnOnBoard(prsnOnBoardList);
			}
		}
		 
		// =============================================================
		
		/*int i = 0;
		List<VoyageTransportEquipmentSEI> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSEI>();
		for (ContainerDetails cntnerDtl : containerDtls) {

			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

			VoyageTransportEquipmentSEI voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSEI();
			voyageTransportEquipmentClassObj.setQuipmentSequenceNo(((String) cntnerDtl.getContainerSealNumber()));
			voyageTransportEquipmentClassObj.setQuipmentId(((String) cntnerDtl.getContainerNumber()));
			voyageTransportEquipmentClassObj.setQuipmentType("CN");
			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(((String) cntnerDtl.getEquipmentLoadStatus()));
			voyageTransportEquipmentClassObj.setSocFlag((String) cntnerDtl.getSoc_flag());

			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
		}*/

		TmSuprtDocsSEI tmSuprtDocs = new TmSuprtDocsSEI (); 
		tmSuprtDocs.setBnefcryCdpublic(settingLength("",35)); 
		tmSuprtDocs.setDocRefNmbr(settingLength("",17));
		tmSuprtDocs.setDocTypCd(settingLength("",6));
		tmSuprtDocs.setIcegateUserid(settingLength("",15));
		tmSuprtDocs.setIrnNmbr(settingLength("",14));
		tmSuprtDocs.setRefSerialNo(settingLength("",5));
		tmSuprtDocs.setSubSerialNoRef("");
		tmSuprtDocs.setTagRef(settingLength("",5));
		List<TmSuprtDocsSEI> tmSuprtDocsList = new LinkedList<TmSuprtDocsSEI>();
		tmSuprtDocsList.add(tmSuprtDocs);
		mster.setTmSuprtDocs(tmSuprtDocsList);
		
		TmAdtnlDecSEI tmAdtnlDec = new TmAdtnlDecSEI();
		tmAdtnlDec.setTagRef(settingLength("",5));
		tmAdtnlDec.setRefSerialNo(settingLength("",5));
		tmAdtnlDec.setInfoCd(settingLength("",35));
		tmAdtnlDec.setInfoDt("");
		tmAdtnlDec.setInfoMsr(settingLength("",5));
		tmAdtnlDec.setInfoQualifier(settingLength("",10));
//		tmAdtnlDec.setInfoText(settingLength("",100)); guru said to comment
		tmAdtnlDec.setInfoTyp(settingLength("",10));
		List<TmAdtnlDecSEI> tmAdtnlDecList= new LinkedList<TmAdtnlDecSEI>();
		tmAdtnlDecList.add(tmAdtnlDec);
		mster.setTmAdtnlDec(tmAdtnlDecList);

		// =============================================================
		
	
		
	 

		// =============================================================
		
		/* mster.setMastrCnsgmtDec(mastrCnsgmtDecList); // Nitun not Requeird json
		   mster.setVoyageDtls(voyageDtlsList); //Nitun Not requeird json
		   mster.setPrsnOnBoard(prsnOnBoardList);
		   mster.setVoyageTransportEquipment(voyageTransportEquipmentList);*/

		// =============================================================
		
		
		
		}
		HeaderFieldSEI headerFieldClassObj = new HeaderFieldSEI();

		headerFieldClassObj.setSenderID(senderId);
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber("");
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		headerFieldClassObj.setReportingEvent(rpngEvent);
		
		DigSignSEI digSignClassObj = new DigSignSEI();
		digSignClassObj.setSignerVersion("");
		digSignClassObj.setStartCertificate("");
		digSignClassObj.setStartSignature("");
		
		JsonMainObjctSEI org = new JsonMainObjctSEI();
		 
		org.setHeaderField(headerFieldClassObj);
		org.setDigSign(digSignClassObj);
		org.setMaster(mster);
		
		return org;
	}

	public static JsonMainObjctSDA getSDA(List<ImportGeneralManifestMod> blList) {

		ImportGeneralManifestMod objForm = blList.get(0);

		List<NotifyParty> notifyPartyDetailes = objForm.getNotifyParty();
		List<Consignee> consigneeDtls = objForm.getConsignee();
		List<MarksNumber> marksNumberDtls = objForm.getMarksNumber();
		List<Consigner> consignerDtls = objForm.getConsigner();
		List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SDA";
		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSDA> itemDtls = new LinkedList<ItemDtlsSDA>();
		List<TrnsprtEqmtSDA> trnsprtEqmt = new LinkedList<TrnsprtEqmtSDA>();
		List<LocCstmSDA> locCstm = new LinkedList<LocCstmSDA>();
		List<MCRefSDA> mCRef = new LinkedList<MCRefSDA>();
		List<TrnsprtDocMsrSDA> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSDA>();
		List<ShipItnrySDA> shipItnry = new LinkedList<ShipItnrySDA>();
		List<ItnrySDA> itnry = new LinkedList<ItnrySDA>();
		List<TrnsprtDocSDA> trnsprtDoc = new LinkedList<TrnsprtDocSDA>();
		List<PrevRefSDA> prevRef = new LinkedList<PrevRefSDA>();
		List<TrnshprSDA> trnshpr = new LinkedList<TrnshprSDA>();
		List<HouseCargoDecSDA> houseCargoDec = new LinkedList<HouseCargoDecSDA>();
		List<MCSuprtDocsSDA> mcSuprtDoc = new LinkedList<MCSuprtDocsSDA>();
		List<MCAdtnlDecSDA> mcAdtnlDec= new LinkedList<MCAdtnlDecSDA>();
		List<HCAdtnlDecSDA> hcAdtnlDec= new LinkedList<HCAdtnlDecSDA>();
		List<HCCrgoSuprtDocsSDA> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSDA>();
		
		// ===================

		for (ImportGeneralManifestMod blObj : blList) {
			HouseCargoDecSDA houseCargoDecSDAObj = new HouseCargoDecSDA();
			ItnrySDA itnryClassObj = new ItnrySDA();
			itnryClassObj.setPrtOfCallSeqNmbr(settingLength(blObj.getPort_of_call_sequence_number(),5));
		    // itnryClassObj.setModeOfTrnsprt(blObj.getCargoMovmnt());
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getNext_port_of_call_name(),256));
			itnryClassObj.setPrtOfCallName(blObj.getPort_of_call_name());
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPort_of_call_cod(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),1));
			itnry.add(itnryClassObj);
			houseCargoDecSDAObj.setItnry(itnry);
			// --------------------------------------------------------
			
			List<HCRefSDA> hCRef = new LinkedList<HCRefSDA>();

			HCRefSDA hCRefObj = new HCRefSDA();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));
			hCRefObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); 
			hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),4));
			hCRef.add(hCRefObj);
			houseCargoDecSDAObj.sethCRef(hCRef);
		//======================================================================================	

			
			// ----------------------------------------
			MCRefSDA mCRefClassObj = new MCRefSDA();
			mCRefClassObj.setLineNo(blObj.getItemNumber()); // Line 60
			mCRefClassObj.setMstrBlNo(blObj.getBl()); // Line 53
			mCRefClassObj.setMstrBlDt(blObj.getBlDate()); // Line 53
			mCRefClassObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4)); // Line 76
			mCRefClassObj.setPrevDec(blObj.getPrevious_declaration()); // Line77
			mCRefClassObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); // Line 78

			mCRef.add(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
			PrevRefSDA prevRefObj = new PrevRefSDA();
			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			prevRefObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4));
//			prevRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			//prevRefObj.setSplitIndctr(settingLength(blObj.getSplit_indicator_list(),2));
			//prevRefObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getNumber_of_packages(),16,6));
			prevRefObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4));

			prevRef.add(prevRefObj);
			houseCargoDecSDAObj.setPrevRef(prevRef);
		
			// ----------------------------
			LocCstmSDA locCstmClassObj = new LocCstmSDA();

			locCstmClassObj.setFirstPrtOfEntry((String) settingLength(blObj.getFirst_port_of_entry_last_port_of_departure(),6));
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength("",6));  // New added
			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2));  // Line 90
			locCstmClassObj.setItemTyp(settingLength(blObj.getItemType(),2));// Line 61
			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4)); // Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength(blObj.getCargoNature(),4));  // Line 59
			locCstm.add(locCstmClassObj);
			houseCargoDecSDAObj.setLocCstm(locCstm);
		
			// ------------------------------------------
			TrnshprSDA TrnshprObj = new TrnshprSDA(); // New added
			TrnshprObj.setTrnsprtDoc("");
			TrnshprObj.setTrnshprBond(settingLength("",10));
			trnshpr.add(TrnshprObj);
			houseCargoDecSDAObj.setTrnshpr(trnshpr);
			
			// ---------------------------------------------------------
			TrnsprtDocMsrSDA trnsprtDocMsrClassObj = new TrnsprtDocMsrSDA();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getNumber_of_packages(),8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrossCargoWeightBLlevel(),12,3));
			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength(blObj.getUnit_of_weight(),3));
			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); // not cleared by Guru
			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3)); // not cleared by Guru
			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			trnsprtDocMsrClassObj.setGrossVolume(settingLengthForDouble(blObj.getVolume(),12,3));
			trnsprtDocMsrClassObj.setUnitOfVolume(settingLength(blObj.getUnit_of_volume(),3));
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSDAObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ------------------------------------------------------
			ItemDtlsSDA itemDtlsClassObj = new ItemDtlsSDA();
			// trnsprtEqmtClassObj.setHSDA((String)blObj.get(" ")); not cleared by guru
			itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCargo_item_sequence_no(),5));
			itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
			itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
			itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));
			itemDtlsClassObj.setNmbrOfPkgs(settingLength("",8));
			itemDtlsClassObj.setTypOfPkgs(settingLength("",3));
			itemDtls.add(itemDtlsClassObj);
			houseCargoDecSDAObj.setItemDtls(itemDtls);
			// ------------------------------------------------------
			TrnsprtDocSDA trnsprtDocClassObj = new TrnsprtDocSDA();

			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getPort_of_acceptance_name(),256));
			trnsprtDocClassObj.setPrtOfReceiptName(  settingLength(blObj.getPort_of_receipt_name(),256));
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));
			trnsprtDocClassObj.setPrtOfReceiptCdd( settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setUcrTyp(settingLength(blObj.getUcr_type(),3));
			trnsprtDocClassObj.setUcrCd( settingLength(blObj.getUcr_code(),35));	

			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setNotfdPartyCity((String) settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName((String) settingLength(notyObj.getStateName(),35));
					// trnsprtDocClassObj.setNotfdPartyCntrySubDiv((String) notyObj.get(""));
					trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyPstcd( settingLength(notyObj.getZip(),9));
					trnsprtDocClassObj.setTypOfNotfdPartyCd( settingLength(notyObj.getCostumerCode(),3));
				}
			}
			trnsprtDocClassObj.setPanOfNotfdParty( settingLength(blObj.getPan_of_notified_party(),17));
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl( settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			houseCargoDecSDAObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ---------------------------------------------------

			// for (Object ctnerDtls: containeerDtls) {
			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,70));;
					trnsprtDocClassObj.setCnsgnesName( settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCity(settingLength(cnsneeDtl.getCity(),70));
					trnsprtDocClassObj.setCnsgneCntrySubDivName( settingLength(cnsneeDtl.getState(),35));
					// trnsprtDocClassObj.setCnsgneCntrySubDiv((String) cnsneeDtl.get(""));
					trnsprtDocClassObj.setCnsgneCntryCd(settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnePstcd(settingLength(cnsneeDtl.getZip(),9));
					trnsprtDocClassObj.setCnsgnesCd(settingLength(cnsneeDtl.getCustomerCode(),17));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnrsName(settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrCity(settingLength(cnsnerDtls.getCity(),70));
					trnsprtDocClassObj.setCnsgnrCntrySubDivName(settingLength(cnsnerDtls.getState(),35));
					trnsprtDocClassObj.setCnsgnrsCd(settingLength(cnsnerDtls.getCustomerCode(),17));
					// trnsprtDocClassObj.setCnsgnrCntrySubDivCd((String) cnsnerDtls.get(""));
					trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnrPstcd(settingLength(cnsnerDtls.getZip(),9));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
					
				}
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			houseCargoDecSDAObj.setTrnsprtDoc(trnsprtDoc);
			// ------------------------------------------------

			TrnsprtEqmtSDA trnsprtEqmtClassObj = new TrnsprtEqmtSDA();
			int seq = 0;
			for (ContainerDetails ctnerDtl : containerDtls) {
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo())) {
					seq++;
					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(seq+"",5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3));// alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getContainerSize(),4)); // optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength( ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256));
					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256));
					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
					trnsprtEqmtClassObj.setEventDt("");
					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10));
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(ctnerDtl.getContainerAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
				}
			} // add to trnsprtDocMsr List
			trnsprtEqmt.add(trnsprtEqmtClassObj);
			houseCargoDecSDAObj.setTrnsprtEqmt(trnsprtEqmt);
			//============================================================
			HCCrgoSuprtDocsSDA crgoSuprtDocs = new HCCrgoSuprtDocsSDA(); 
			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			crgoSuprtDocs.setDocRefNmbr(settingLength("",17));
			crgoSuprtDocs.setDocTypCd(settingLength("",6));
			crgoSuprtDocs.setIcegateUserid(settingLength("",15));
			crgoSuprtDocs.setIrnNmbr(settingLength("",14));
			crgoSuprtDocs.setRefSerialNo(settingLength("",5));
			crgoSuprtDocs.setSubSerialNoRef("");
			crgoSuprtDocs.setTagRef(settingLength("",5));
			crgoSuprtDoc.add(crgoSuprtDocs);
			houseCargoDecSDAObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
			//==========================================================
			HCAdtnlDecSDA adtnlDec = new HCAdtnlDecSDA();
			adtnlDec.setTagRef(settingLength("",5));
			adtnlDec.setRefSerialNo(settingLength("",5));
			adtnlDec.setInfoCd(settingLength("",35));
			adtnlDec.setInfoDt("");
			adtnlDec.setInfoMsr(settingLength("",5));
			adtnlDec.setInfoQualifier(settingLength("",10));
			adtnlDec.setInfoText(settingLength("",100));
			adtnlDec.setInfoTyp(settingLength("",10));
			
			hcAdtnlDec.add(adtnlDec);
			houseCargoDecSDAObj.setHcAdtnlDec(hcAdtnlDec);
			//----------------------------------------------------------------
			MCSuprtDocsSDA mcSuprtDocs = new MCSuprtDocsSDA (); 
			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			mcSuprtDocs.setDocRefNmbr(settingLength("",17));
			mcSuprtDocs.setDocTypCd(settingLength("",6));
			mcSuprtDocs.setIcegateUserid(settingLength("",15));
			mcSuprtDocs.setIrnNmbr(settingLength("",14));
			mcSuprtDocs.setRefSerialNo(settingLength("",5));
			mcSuprtDocs.setSubSerialNoRef("");
			mcSuprtDocs.setTagRef(settingLength("",5));
			mcSuprtDoc.add(mcSuprtDocs);
			//------------------------------------------------------------
			MCAdtnlDecSDA mcAdtnlDecs = new MCAdtnlDecSDA();
			mcAdtnlDecs.setTagRef(settingLength("",5));
			mcAdtnlDecs.setRefSerialNo(settingLength("",5));
			mcAdtnlDecs.setInfoCd(settingLength("",35));
			mcAdtnlDecs.setInfoDt("");
			mcAdtnlDecs.setInfoMsr(settingLength("",5));
			mcAdtnlDecs.setInfoQualifier(settingLength("",10));
			mcAdtnlDecs.setInfoText(settingLength("",100));
			mcAdtnlDecs.setInfoTyp(settingLength("",10));
			
			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
			ShipItnrySDA shipItny3 = new ShipItnrySDA();
			String prtOfCallCdd = null;
			String itnrySeq = null;
			// all value set
			if (objForm.getNextport1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-3";
				prtOfCallCdd = objForm.getLastPort1();
			}
			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport2() == null || objForm.getNextport2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySDA shipItnry2 = new ShipItnrySDA();
			shipItnry2.setShipItnrySeq(itnrySeq);
			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-1";

				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySDA shipItnry1 = new ShipItnrySDA();
			shipItnry1.setShipItnrySeq(itnrySeq);
			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			ShipItnrySDA shipItnry0 = new ShipItnrySDA();
			shipItnry0.setShipItnrySeq("0");
			shipItnry0.setPrtOfCallCdd(settingLength(objForm.getPod(),10)); // blObj.get("Port of call sequence numbe"));
			shipItnry0.setPrtOfCallName(settingLength(  blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "1";
				prtOfCallCdd = objForm.getLastPort1();
			}
			ShipItnrySDA shipItnry11 = new ShipItnrySDA();
			shipItnry11.setShipItnrySeq(itnrySeq);
			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			if (objForm.getLastPort2() == null || objForm.getLastPort2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySDA shipItnry22 = new ShipItnrySDA();
			shipItnry22.setShipItnrySeq(itnrySeq);
			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry22.setPrtOfCallName(settingLength(  blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "3";
				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySDA shipItnry33 = new ShipItnrySDA();
			shipItnry33.setShipItnrySeq(itnrySeq);
			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry33.setPrtOfCallName(settingLength( blObj.getPort_of_call_sequence_number(),256));
			shipItnry.add(shipItny3);
			shipItnry.add(shipItnry2);
			shipItnry.add(shipItnry1);
			shipItnry.add(shipItnry0);
			shipItnry.add(shipItnry11);
			shipItnry.add(shipItnry22);
			shipItnry.add(shipItnry33);
			// ------------------------------------------------------
			houseCargoDec.add(houseCargoDecSDAObj);
		}
		
		// now add all List to relevant class

		MastrCnsgmtDecSDA mastrCnsgmtDec = new MastrCnsgmtDecSDA();
		mastrCnsgmtDec.setItemDtls(itemDtls);
		mastrCnsgmtDec.setItnry(itnry);
		mastrCnsgmtDec.setLocCstm(locCstm);
		mastrCnsgmtDec.setmCRef(mCRef);
		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
		mastrCnsgmtDec.setPrevRef(prevRef);
		mastrCnsgmtDec.setTrnshpr(trnshpr);
		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
		List<MastrCnsgmtDecSDA> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSDA>();
		mastrCnsgmtDecList.add(mastrCnsgmtDec);
		

		VoyageDtlsSDA voyageDtlsClassObj = new VoyageDtlsSDA();
		voyageDtlsClassObj.setVoyageNo(voyage); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(objForm.getConveyance_reference_no()); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted(objForm.getCargoDeclaration()); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(""); // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
		voyageDtlsClassObj.setShipItnry(shipItnry);

		List<VoyageDtlsSDA> voyageDtlsList = new LinkedList<VoyageDtlsSDA>();
		voyageDtlsList.add(voyageDtlsClassObj);
		// ---------------------------------------------------
		VesselDtlsSDA vesselDtls = new VesselDtlsSDA();

		vesselDtls.setModeOfTrnsprt(settingLength(objForm.getMode_of_transport(),1)); // Line 191
		vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25)); // not found
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
		vesselDtls.setPurposeOfCall("1"); // always hard coded
		List<VesselDtlsSDA> vesselDtlsList = new LinkedList<VesselDtlsSDA>();
		vesselDtlsList.add(vesselDtls);
		// ----------------------------
		AuthPrsnSDA authPrsClassObj = new AuthPrsnSDA();
		authPrsClassObj.setSbmtrTyp(settingLength(objForm.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(objForm.getSubmitter_code(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength( objForm.getAuthoriz_rep_code(),10)); //
		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(),10));; // LinNo:-211
		authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
		authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(),10)); // LinNo:-190
		authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(),10)); // LinNo:-132

		List<AuthPrsnSDA> authPrsnList = new LinkedList<AuthPrsnSDA>();
		authPrsnList.add(authPrsClassObj);
		// ----------------------------
		DecRefSDA decRefClaObj = new DecRefSDA();

		// decRefClaObj.setMsgTyp(objForm.getMesstype());
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(objForm.getPod(),10));// value from old screen [Pod]
		// decRefClaObj.setJobNo(objForm.getJobNum()); // sid will give this Number
		decRefClaObj.setJobNo(settingLength(jobNum,7));
		// decRefClaObj.setJobDt(objForm.getJobDate()); //sid told me to keep crunt date
		decRefClaObj.setJobDt(currDate);
		// decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength(objForm.getManifest_no_csn_no(),7)); //
		decRefClaObj.setMnfstDtRotnDt(objForm.getManifest_date_csn_date()); //
		decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
		// #
		// * decRefClaObj.setDptrMnfstNo(); //
		// *decRefClaObj.setDptrMnfstDt(""); //
		// #
		List<DecRefSDA> decRefList = new LinkedList<DecRefSDA>();
		decRefList.add(decRefClaObj);
		// ----------------------------
		PrsnDtlsSDA prsDtls = new PrsnDtlsSDA();
		prsDtls.setPrsnTypCdd(settingLength("",3));
		prsDtls.setPrsnFamilyName(settingLength("",70));
		prsDtls.setPrsnGivenName(settingLength("",70));
		prsDtls.setPrsnNatnltyCdd(settingLength("",2));
		prsDtls.setPsngrInTransitIndctr(settingLength("",1));
		prsDtls.setCrewmemberRankOrRatingCdd("");
		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
		prsDtls.setPrsnGenderCdd(settingLength("",3));
		prsDtls.setPrsnDtOfBirth("");
		prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
		prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
		// -----------------------------------------

		PrsnIdSDA prsnIdclassObj = new PrsnIdSDA();
		prsnIdclassObj.setPrsnIdDocExpiryDt("");
		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
		List<PrsnIdSDA> prsnIdList = new LinkedList<PrsnIdSDA>();
		prsnIdList.add(prsnIdclassObj);
		//===============================================
		CrewEfctSDA cewEfct = new CrewEfctSDA();
		cewEfct.setCrewEfctDescCdd(settingLength("",3));
		cewEfct.setCrewEfctQntyOnbrd(settingLengthForDouble("",16,6));
		cewEfct.setCrewEfctQntyOnbrdCd(settingLength("",3));
		cewEfct.setCrewEfctsDesc(settingLength("",256));
		cewEfct.setCrewEfctsSeqNmbr(settingLength("",5));
		List<CrewEfctSDA> cewEfctList = new LinkedList<CrewEfctSDA>();
		cewEfctList.add(cewEfct);
		// ---------------
		List<PrsnDtlsSDA> prsnDtlsList = new LinkedList<PrsnDtlsSDA>();
		prsnDtlsList.add(prsDtls);
		PrsnOnBoardSDA prsnOnBoard = new PrsnOnBoardSDA();
		prsnOnBoard.setPrsnDtls(prsnDtlsList);
		prsnOnBoard.setPrsnId(prsnIdList);
		prsnOnBoard.setCrewEfct(cewEfctList);
		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));

		List<PrsnOnBoardSDA> prsnOnBoardList = new LinkedList<PrsnOnBoardSDA>();
		prsnOnBoardList.add(prsnOnBoard);
		// --------------------------------------------------------
		ShipStoresSDA shipStores = new ShipStoresSDA();
		shipStores.setSeqNmbr("");
		shipStores.setArticleNameCdd(settingLength("",18));
		shipStores.setArticleNameText(settingLength("",512));
		shipStores.setLocOnbrdText(settingLength("",256));
		shipStores.setQntyCdOnbrd(settingLength("",3));
		shipStores.setQntyOnbrd(settingLengthForDouble("",16,6));
		List<ShipStoresSDA> shipStoresList = new LinkedList<ShipStoresSDA>();
		shipStoresList.add(shipStores);
		//----------------------------------------------------------------
		TmSuprtDocsSDA tmSuprtDocs = new TmSuprtDocsSDA (); 
		tmSuprtDocs.setBnefcryCdpublic(settingLength("",35));
		tmSuprtDocs.setDocRefNmbr(settingLength("",17));
		tmSuprtDocs.setDocTypCd(settingLength("",6));
		tmSuprtDocs.setIcegateUserid(settingLength("",15));
		tmSuprtDocs.setIrnNmbr(settingLength("",14));
		tmSuprtDocs.setRefSerialNo(settingLength("",5));
		tmSuprtDocs.setSubSerialNoRef("");
		tmSuprtDocs.setTagRef(settingLength("",5));
		List<TmSuprtDocsSDA> tmSuprtDocsList = new LinkedList<TmSuprtDocsSDA>();
		tmSuprtDocsList.add(tmSuprtDocs);
		//------------------------------------------------------------
		TmAdtnlDecSDA tmAdtnlDec = new TmAdtnlDecSDA();
		tmAdtnlDec.setTagRef(settingLength("",5));
		tmAdtnlDec.setRefSerialNo(settingLength("",5));
		tmAdtnlDec.setInfoCd(settingLength("",35));
		tmAdtnlDec.setInfoDt("");
		tmAdtnlDec.setInfoMsr(settingLength("",5));
		tmAdtnlDec.setInfoQualifier(settingLength("",10));
		tmAdtnlDec.setInfoText(settingLength("",100));
		tmAdtnlDec.setInfoTyp(settingLength("",10));
		List<TmAdtnlDecSDA> tmAdtnlDecList= new LinkedList<TmAdtnlDecSDA>();
		tmAdtnlDecList.add(tmAdtnlDec);
		//-------------------------------------------------------
		int i = 0;
		List<VoyageTransportEquipmentSDA> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSDA>();
		for (ContainerDetails cntnerDtl : containerDtls) {

			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

			VoyageTransportEquipmentSDA voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSDA();
			voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
			voyageTransportEquipmentClassObj.setQuipmentType("CN");
			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(settingLength(cntnerDtl.getEquipmentLoadStatus(),3));
			voyageTransportEquipmentClassObj.setSocFlag( settingLength(cntnerDtl.getSoc_flag(),1));
			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
		}
		
		// ------------------------------------------------------------
		DigSignSDA digSignClassObj = new DigSignSDA();
		digSignClassObj.setStartSignature("");
		digSignClassObj.setStartCertificate("");
		digSignClassObj.setSignerVersion("");

		List<DigSignSDA> digSignList = new LinkedList<DigSignSDA>();
		digSignList.add(digSignClassObj);
		// ----------------
		MasterSDA mster = new MasterSDA();
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		mster.setVoyageDtls(voyageDtlsList);
		mster.setVesselDtls(vesselDtlsList);
		mster.setAuthPrsn(authPrsnList);
		mster.setDecRef(decRefList);
	    mster.setPrsnOnBoard(prsnOnBoardList);
		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
		mster.setShipStores(shipStoresList);
		mster.setTmSuprtDocs(tmSuprtDocsList);
		mster.setTmAdtnlDec(tmAdtnlDecList);
		// ----------------------------------
		HeaderFieldSDA headerFieldClassObj = new HeaderFieldSDA();

		headerFieldClassObj.setSenderID(senderId);
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		// "Default value: IECHE01"
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber("");// old screen String sId (
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		// "Default value: ES"
		headerFieldClassObj.setReportingEvent("ES");
		// -------------------------------------------------

		List<MasterSDA> masterList = new LinkedList<MasterSDA>();
		masterList.add(mster);

		List<HeaderFieldSDA> headerFieldList = new LinkedList<HeaderFieldSDA>();
		headerFieldList.add(headerFieldClassObj);

		JsonMainObjctSDA org = new JsonMainObjctSDA();
		// org.setHeaderField(headerFieldList);
		org.setHeaderField(headerFieldList);
		 org.setDigSign(digSignList);
		org.setMaster(masterList);
		return org;
	
	}

	public static JsonMainObjctSAA getSAA(List<ImportGeneralManifestMod> blList) {

		ImportGeneralManifestMod objForm = blList.get(0);

		List<NotifyParty> notifyPartyDetailes = objForm.getNotifyParty();
		List<Consignee> consigneeDtls = objForm.getConsignee();
		List<MarksNumber> marksNumberDtls = objForm.getMarksNumber();
		List<Consigner> consignerDtls = objForm.getConsigner();
		List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SAA";
		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSAA> itemDtls = new LinkedList<ItemDtlsSAA>();
		List<TrnsprtEqmtSAA> trnsprtEqmt = new LinkedList<TrnsprtEqmtSAA>();
		List<LocCstmSAA> locCstm = new LinkedList<LocCstmSAA>();
		List<MCRefSAA> mCRef = new LinkedList<MCRefSAA>();
		List<TrnsprtDocMsrSAA> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSAA>();
		List<ShipItnrySAA> shipItnry = new LinkedList<ShipItnrySAA>();
		List<ItnrySAA> itnry = new LinkedList<ItnrySAA>();
		List<TrnsprtDocSAA> trnsprtDoc = new LinkedList<TrnsprtDocSAA>();
		List<PrevRefSAA> prevRef = new LinkedList<PrevRefSAA>();
		List<TrnshprSAA> trnshpr = new LinkedList<TrnshprSAA>();
		List<HouseCargoDecSAA> houseCargoDec = new LinkedList<HouseCargoDecSAA>();
		List<MCSuprtDocsSAA> mcSuprtDoc = new LinkedList<MCSuprtDocsSAA>();
		List<MCAdtnlDecSAA> mcAdtnlDec= new LinkedList<MCAdtnlDecSAA>();
		List<HCAdtnlDecSAA> hcAdtnlDec= new LinkedList<HCAdtnlDecSAA>();
		List<HCCrgoSuprtDocsSAA> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSAA>();
		
		// ===================

		for (ImportGeneralManifestMod blObj : blList) {
			HouseCargoDecSAA houseCargoDecSAAObj = new HouseCargoDecSAA();
			ItnrySAA itnryClassObj = new ItnrySAA();
			itnryClassObj.setPrtOfCallSeqNmbr(settingLength(blObj.getPort_of_call_sequence_number(),5));
		    // itnryClassObj.setModeOfTrnsprt(blObj.getCargoMovmnt());
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPort_of_call_coded(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),1));
			itnry.add(itnryClassObj);
			houseCargoDecSAAObj.setItnry(itnry);
			// --------------------------------------------------------
			
			List<HCRefSAA> hCRef = new LinkedList<HCRefSAA>();

			HCRefSAA hCRefObj = new HCRefSAA();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));
			hCRefObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); 
			hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),4));
			hCRef.add(hCRefObj);
			houseCargoDecSAAObj.sethCRef(hCRef);
		//======================================================================================	

			
			// ----------------------------------------
			MCRefSAA mCRefClassObj = new MCRefSAA();
			mCRefClassObj.setLineNo(blObj.getItemNumber()); // Line 60
			mCRefClassObj.setMstrBlNo(blObj.getBl()); // Line 53
			mCRefClassObj.setMstrBlDt(blObj.getBlDate()); // Line 53
			mCRefClassObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));// Line 76
			mCRefClassObj.setPrevDec(blObj.getPrevious_declaration()); // Line77
			mCRefClassObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16));  // Line 78

			mCRef.add(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
			PrevRefSAA prevRefObj = new PrevRefSAA();
			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			prevRefObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4));
//			prevRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4)); guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			prevRefObj.setSplitIndctr(settingLength(blObj.getSplit_indicator_list(),2));
			prevRefObj.setNmbrOfPkgs(settingLength(blObj.getNumber_of_packages(),8));
			prevRefObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4));

			prevRef.add(prevRefObj);
			houseCargoDecSAAObj.setPrevRef(prevRef);
		
			// ----------------------------
			LocCstmSAA locCstmClassObj = new LocCstmSAA();

			locCstmClassObj.setFirstPrtOfEntry(settingLength(blObj.getFirst_port_of_entry_last_port_of_departure(),6));
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength("",6)); ; // New added
			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2));  // Line 90
			locCstmClassObj.setItemTyp(settingLength(blObj.getItemType(),2)); // Line 61
			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4)); // Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength(blObj.getCargoNature(),4)); // Line 59
			locCstm.add(locCstmClassObj);
			houseCargoDecSAAObj.setLocCstm(locCstm);
		
			// ------------------------------------------
			TrnshprSAA TrnshprObj = new TrnshprSAA(); // New added
			TrnshprObj.setTrnsprtDoc("");
			TrnshprObj.setTrnshprBond(settingLength("",10));
			trnshpr.add(TrnshprObj);
			houseCargoDecSAAObj.setTrnshpr(trnshpr);
			
			// ---------------------------------------------------------
			TrnsprtDocMsrSAA trnsprtDocMsrClassObj = new TrnsprtDocMsrSAA();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getNumber_of_packages(),8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrossCargoWeightBLlevel(),12,3));
			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength(blObj.getUnit_of_weight(),3));
			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); // not cleared by Guru
			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3)); // not cleared by Guru
//			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			trnsprtDocMsrClassObj.setGrossVolume(settingLengthForDouble(blObj.getGross_volume(),12,3));
			trnsprtDocMsrClassObj.setUnitOfVolume(settingLength(blObj.getUnit_of_volume(),3));
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSAAObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ------------------------------------------------------
			ItemDtlsSAA itemDtlsClassObj = new ItemDtlsSAA();
			// trnsprtEqmtClassObj.setHSAA((String)blObj.get(" ")); not cleared by guru
			itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCargo_item_sequence_no(),5));
			itemDtlsClassObj.setCrgoItemDesc(settingLength(blObj.getCargo_item_description(),256));
			itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
			itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));
			itemDtlsClassObj.setNmbrOfPkgs(settingLength("",8));
			itemDtlsClassObj.setTypOfPkgs(settingLength("",3));
			itemDtls.add(itemDtlsClassObj);
			houseCargoDecSAAObj.setItemDtls(itemDtls);
			// ------------------------------------------------------
			TrnsprtDocSAA trnsprtDocClassObj = new TrnsprtDocSAA();

			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getPort_of_acceptance_name(),256));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getPort_of_receipt_name(),256));
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));
			trnsprtDocClassObj.setPrtOfReceiptCdd( settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setUcrCd( settingLength(blObj.getUcr_code(),35));	

			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setNotfdPartyCity(settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName(settingLength(notyObj.getStateName(),35));
					// trnsprtDocClassObj.setNotfdPartyCntrySubDiv((String) notyObj.get(""));
					trnsprtDocClassObj.setNotfdPartyCntryCd(settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyPstcd(settingLength(notyObj.getZip(),9));
					trnsprtDocClassObj.setTypOfNotfdPartyCd(settingLength(notyObj.getCostumerCode(),3));
				}
			}
			trnsprtDocClassObj.setPanOfNotfdParty (settingLength(blObj.getPan_of_notified_party(),17));
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl(settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			houseCargoDecSAAObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ---------------------------------------------------

			// for (Object ctnerDtls: containeerDtls) {
			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnesName(settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCity(  settingLength(cnsneeDtl.getCity(),70));
					// trnsprtDocClassObj.setCnsgneCntrySubDiv((String) cnsneeDtl.get(""));
					trnsprtDocClassObj.setCnsgneCntryCd( settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnePstcd( settingLength(cnsneeDtl.getZip(),9));
					trnsprtDocClassObj.setCnsgnesCd( settingLength(cnsneeDtl.getCustomerCode(),17));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnrsName( settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrCity( settingLength(cnsnerDtls.getCity(),70));
					trnsprtDocClassObj.setCnsgnrCntrySubDivName((String)settingLength(cnsnerDtls.getState(),35));
					trnsprtDocClassObj.setCnsgnrsCd( settingLength(cnsnerDtls.getCustomerCode(),17));
					// trnsprtDocClassObj.set((String) cnsnerDtls.get(""));
					trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnrPstcd( settingLength(cnsnerDtls.getZip(),9));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
				}
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			houseCargoDecSAAObj.setTrnsprtDoc(trnsprtDoc);
			// ------------------------------------------------

			TrnsprtEqmtSAA trnsprtEqmtClassObj = new TrnsprtEqmtSAA();

			for (ContainerDetails ctnerDtl : containerDtls) {
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo())) {

					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(ctnerDtl.getContainerAgentCode(),5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3)); // alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getContainerSize(),4)); // optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256));
					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256));
					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
					trnsprtEqmtClassObj.setEventDt("");
					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10));
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(ctnerDtl.getContainerAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
				}
			} // add to trnsprtDocMsr List
			trnsprtEqmt.add(trnsprtEqmtClassObj);
			houseCargoDecSAAObj.setTrnsprtEqmt(trnsprtEqmt);
			//============================================================
			HCCrgoSuprtDocsSAA crgoSuprtDocs = new HCCrgoSuprtDocsSAA(); 
			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			crgoSuprtDocs.setDocRefNmbr(settingLength("",17));
			crgoSuprtDocs.setDocTypCd(settingLength("",6));
			crgoSuprtDocs.setIcegateUserid(settingLength("",15));
			crgoSuprtDocs.setIrnNmbr(settingLength("",14));
			crgoSuprtDocs.setRefSerialNo(settingLength("",5));
			crgoSuprtDocs.setSubSerialNoRef("");
			crgoSuprtDocs.setTagRef(settingLength("",5));
			crgoSuprtDoc.add(crgoSuprtDocs);
			houseCargoDecSAAObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
			//==========================================================
			HCAdtnlDecSAA adtnlDec = new HCAdtnlDecSAA();
			adtnlDec.setTagRef(settingLength("",5));
			adtnlDec.setRefSerialNo(settingLength("",5));
			adtnlDec.setInfoCd(settingLength("",35));
			adtnlDec.setInfoDt("");
			adtnlDec.setInfoMsr(settingLength("",5));
			adtnlDec.setInfoQualifier(settingLength("",10));
			adtnlDec.setInfoText(settingLength("",100));
			adtnlDec.setInfoTyp(settingLength("",10));
			
			hcAdtnlDec.add(adtnlDec);
			houseCargoDecSAAObj.setHcAdtnlDec(hcAdtnlDec);
			//----------------------------------------------------------------
			MCSuprtDocsSAA mcSuprtDocs = new MCSuprtDocsSAA (); 
			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			mcSuprtDocs.setDocRefNmbr(settingLength("",17));
			mcSuprtDocs.setDocTypCd(settingLength("",6));
			mcSuprtDocs.setIcegateUserid(settingLength("",15));
			mcSuprtDocs.setIrnNmbr(settingLength("",14));
			mcSuprtDocs.setRefSerialNo(settingLength("",5));
			mcSuprtDocs.setSubSerialNoRef("");
			mcSuprtDocs.setTagRef(settingLength("",5));
			mcSuprtDoc.add(mcSuprtDocs);
			//------------------------------------------------------------
			MCAdtnlDecSAA mcAdtnlDecs = new MCAdtnlDecSAA();
			mcAdtnlDecs.setTagRef(settingLength("",5));
			mcAdtnlDecs.setRefSerialNo(settingLength("",5));
			mcAdtnlDecs.setInfoCd(settingLength("",35));
			mcAdtnlDecs.setInfoDt("");
			mcAdtnlDecs.setInfoMsr(settingLength("",5));
			mcAdtnlDecs.setInfoQualifier(settingLength("",10));
			mcAdtnlDecs.setInfoText(settingLength("",100));
			mcAdtnlDecs.setInfoTyp(settingLength("",10));
			
			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
			ShipItnrySAA shipItny3 = new ShipItnrySAA();
			String prtOfCallCdd = null;
			String itnrySeq = null;
			// all value set
			if (objForm.getNextport1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-3";
				prtOfCallCdd = objForm.getLastPort1();
			}
			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport2() == null || objForm.getNextport2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySAA shipItnry2 = new ShipItnrySAA();
			shipItnry2.setShipItnrySeq(itnrySeq);
			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-1";

				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySAA shipItnry1 = new ShipItnrySAA();
			shipItnry1.setShipItnrySeq(itnrySeq);
			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			ShipItnrySAA shipItnry0 = new ShipItnrySAA();
			shipItnry0.setShipItnrySeq("0");
			shipItnry0.setPrtOfCallCdd(settingLength(objForm.getPod(),10)); // blObj.get("Port of call sequence numbe"));
			shipItnry0.setPrtOfCallName(settingLength( blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "1";
				prtOfCallCdd = objForm.getLastPort1();
			}
			ShipItnrySAA shipItnry11 = new ShipItnrySAA();
			shipItnry11.setShipItnrySeq(itnrySeq);
			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getLastPort2() == null || objForm.getLastPort2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySAA shipItnry22 = new ShipItnrySAA();
			shipItnry22.setShipItnrySeq(itnrySeq);
			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry22.setPrtOfCallName(settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "3";
				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySAA shipItnry33 = new ShipItnrySAA();
			shipItnry33.setShipItnrySeq(itnrySeq);
			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry33.setPrtOfCallName( settingLength(blObj.getPort_of_call_sequence_number(),256));
			shipItnry.add(shipItny3);
			shipItnry.add(shipItnry2);
			shipItnry.add(shipItnry1);
			shipItnry.add(shipItnry0);
			shipItnry.add(shipItnry11);
			shipItnry.add(shipItnry22);
			shipItnry.add(shipItnry33);
			// ------------------------------------------------------
			houseCargoDec.add(houseCargoDecSAAObj);
		}
		
		// now add all List to relevant class

		MastrCnsgmtDecSAA mastrCnsgmtDec = new MastrCnsgmtDecSAA();
		mastrCnsgmtDec.setItemDtls(itemDtls);
		mastrCnsgmtDec.setItnry(itnry);
		mastrCnsgmtDec.setLocCstm(locCstm);
		mastrCnsgmtDec.setmCRef(mCRef);
		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
		mastrCnsgmtDec.setPrevRef(prevRef);
		mastrCnsgmtDec.setTrnshpr(trnshpr);
		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
		List<MastrCnsgmtDecSAA> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSAA>();
		mastrCnsgmtDecList.add(mastrCnsgmtDec);
		

		VoyageDtlsSAA voyageDtlsClassObj = new VoyageDtlsSAA();
		voyageDtlsClassObj.setVoyageNo(voyage); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(objForm.getConveyance_reference_no()); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted(objForm.getCargoDeclaration()); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(""); // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
		voyageDtlsClassObj.setShipItnry(shipItnry);

		List<VoyageDtlsSAA> voyageDtlsList = new LinkedList<VoyageDtlsSAA>();
		voyageDtlsList.add(voyageDtlsClassObj);
		// ---------------------------------------------------
		VesselDtlsSAA vesselDtls = new VesselDtlsSAA();

		vesselDtls.setModeOfTrnsprt(settingLength(objForm.getMode_of_transport(),1)); // Line 191
		vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25)); // not found
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
		vesselDtls.setPurposeOfCall("1"); // always hard coded
		List<VesselDtlsSAA> vesselDtlsList = new LinkedList<VesselDtlsSAA>();
		vesselDtlsList.add(vesselDtls);
		// ----------------------------
		AuthPrsnSAA authPrsClassObj = new AuthPrsnSAA();
		authPrsClassObj.setSbmtrTyp(settingLength(objForm.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(objForm.getSubmitter_code(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength( objForm.getAuthoriz_rep_code(),10)); //
		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(),10)); // LinNo:-211
		authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
		authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(),10)); // LinNo:-190
		authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(),10));
		List<AuthPrsnSAA> authPrsnList = new LinkedList<AuthPrsnSAA>();
		authPrsnList.add(authPrsClassObj);
		// ----------------------------
		DecRefSAA decRefClaObj = new DecRefSAA();

		// decRefClaObj.setMsgTyp(objForm.getMesstype());
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(objForm.getPod(),10));// value from old screen [Pod]
		// decRefClaObj.setJobNo(objForm.getJobNum()); // sid will give this Number
		decRefClaObj.setJobNo(settingLength(jobNum,7));
		// decRefClaObj.setJobDt(objForm.getJobDate()); //sid told me to keep crunt date
		decRefClaObj.setJobDt(currDate);
		// decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength(objForm.getManifest_no_csn_no(),7)); //
		decRefClaObj.setMnfstDtRotnDt(objForm.getManifest_date_csn_date()); //
		decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
		// #
		// * decRefClaObj.setDptrMnfstNo(); //
		// *decRefClaObj.setDptrMnfstDt(""); //
		// #
		List<DecRefSAA> decRefList = new LinkedList<DecRefSAA>();
		decRefList.add(decRefClaObj);
		// ----------------------------
		PrsnDtlsSAA prsDtls = new PrsnDtlsSAA();
		prsDtls.setPrsnTypCdd(settingLength("",3));
		prsDtls.setPrsnFamilyName(settingLength("",70));
		prsDtls.setPrsnGivenName(settingLength("",70));
		prsDtls.setPrsnNatnltyCdd(settingLength("",2));
		prsDtls.setPsngrInTransitIndctr(settingLength("",1));
		prsDtls.setCrewmemberRankOrRatingCdd("");
		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
		prsDtls.setPrsnGenderCdd(settingLength("",3));
		prsDtls.setPrsnDtOfBirth("");
		prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
		prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
		// -----------------------------------------

		PrsnIdSAA prsnIdclassObj = new PrsnIdSAA();
		prsnIdclassObj.setPrsnIdDocExpiryDt("");
		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
		List<PrsnIdSAA> prsnIdList = new LinkedList<PrsnIdSAA>();
		prsnIdList.add(prsnIdclassObj);
		//===============================================
		CrewEfctSAA cewEfct = new CrewEfctSAA();
		cewEfct.setCrewEfctDescCdd(settingLength("",3));
		cewEfct.setCrewEfctQntyOnbrd(settingLengthForDouble("",16,6));
		cewEfct.setCrewEfctQntyOnbrdCd(settingLength("",3));
		cewEfct.setCrewEfctsDesc(settingLength("",256));
		cewEfct.setCrewEfctsSeqNmbr(settingLength("",5));
		List<CrewEfctSAA> cewEfctList = new LinkedList<CrewEfctSAA>();
		cewEfctList.add(cewEfct);
		// ------------------------------------
		List<PrsnDtlsSAA> prsnDtlsList = new LinkedList<PrsnDtlsSAA>();
		prsnDtlsList.add(prsDtls);
		PrsnOnBoardSAA prsnOnBoard = new PrsnOnBoardSAA();
		prsnOnBoard.setPrsnDtls(prsnDtlsList);
		prsnOnBoard.setPrsnId(prsnIdList);
		prsnOnBoard.setCrewEfct(cewEfctList);
		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));

		List<PrsnOnBoardSAA> prsnOnBoardList = new LinkedList<PrsnOnBoardSAA>();
		prsnOnBoardList.add(prsnOnBoard);
		// --------------------------------------------------------
		ShipStoresSAA shipStores = new ShipStoresSAA();
		shipStores.setSeqNmbr(settingLength("",5));
		shipStores.setArticleNameCdd(settingLength("",18));
		shipStores.setArticleNameText(settingLength("",512));
		shipStores.setLocOnbrdText(settingLength("",256));
		shipStores.setQntyCdOnbrd(settingLength("",3));
		shipStores.setQntyOnbrd(settingLengthForDouble("",16,6));
		List<ShipStoresSAA> shipStoresList = new LinkedList<ShipStoresSAA>();
		shipStoresList.add(shipStores);
		//----------------------------------------------------------------
		TmSuprtDocsSAA tmSuprtDocs = new TmSuprtDocsSAA (); 
		tmSuprtDocs.setBnefcryCdpublic(settingLength("",35));
		tmSuprtDocs.setDocRefNmbr(settingLength("",17));
		tmSuprtDocs.setDocTypCd(settingLength("",6));
		tmSuprtDocs.setIcegateUserid(settingLength("",15));
		tmSuprtDocs.setIrnNmbr(settingLength("",14));
		tmSuprtDocs.setRefSerialNo(settingLength("",5));
		tmSuprtDocs.setSubSerialNoRef("");
		tmSuprtDocs.setTagRef(settingLength("",5));
		List<TmSuprtDocsSAA> tmSuprtDocsList = new LinkedList<TmSuprtDocsSAA>();
		tmSuprtDocsList.add(tmSuprtDocs);
		//------------------------------------------------------------
		TmAdtnlDecSAA tmAdtnlDec = new TmAdtnlDecSAA();
		tmAdtnlDec.setTagRef(settingLength("",5));
		tmAdtnlDec.setRefSerialNo(settingLength("",5));
		tmAdtnlDec.setInfoCd(settingLength("",35));
		tmAdtnlDec.setInfoDt("");
		tmAdtnlDec.setInfoMsr(settingLength("",5));
		tmAdtnlDec.setInfoQualifier(settingLength("",10));
		tmAdtnlDec.setInfoText(settingLength("",100));
		tmAdtnlDec.setInfoTyp(settingLength("",10));
		List<TmAdtnlDecSAA> tmAdtnlDecList= new LinkedList<TmAdtnlDecSAA>();
		tmAdtnlDecList.add(tmAdtnlDec);
		//-------------------------------------------------------
		int i = 0;
		List<VoyageTransportEquipmentSAA> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSAA>();
		for (ContainerDetails cntnerDtl : containerDtls) {

			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

			VoyageTransportEquipmentSAA voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSAA();
			voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
			voyageTransportEquipmentClassObj.setQuipmentType("CN");
			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(settingLength(cntnerDtl.getEquipmentLoadStatus(),3));
			voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
		}
		
		// ------------------------------------------------------------
		DigSignSAA digSignClassObj = new DigSignSAA();
		digSignClassObj.setStartSignature("");
		digSignClassObj.setStartCertificate("");
		digSignClassObj.setSignerVersion("");

		List<DigSignSAA> digSignList = new LinkedList<DigSignSAA>();
		digSignList.add(digSignClassObj);
		// ----------------
		MasterSAA mster = new MasterSAA();
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		mster.setVoyageDtls(voyageDtlsList);
		mster.setVesselDtls(vesselDtlsList);
		mster.setAuthPrsn(authPrsnList);
		mster.setDecRef(decRefList);
	    mster.setPrsnOnBoard(prsnOnBoardList);
		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
		mster.setShipStores(shipStoresList);
		mster.setTmSuprtDocs(tmSuprtDocsList);
		mster.setTmAdtnlDec(tmAdtnlDecList);
		// ----------------------------------
		HeaderFieldSAA headerFieldClassObj = new HeaderFieldSAA();

		headerFieldClassObj.setSenderID(senderId);
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		// "Default value: IECHE01"
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber("");// old screen String sId (
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		// "Default value: ES"
		headerFieldClassObj.setReportingEvent("ES");
		// -------------------------------------------------

		List<MasterSAA> masterList = new LinkedList<MasterSAA>();
		masterList.add(mster);

		List<HeaderFieldSAA> headerFieldList = new LinkedList<HeaderFieldSAA>();
		headerFieldList.add(headerFieldClassObj);

		JsonMainObjctSAA org = new JsonMainObjctSAA();
		// org.setHeaderField(headerFieldList);
		org.setHeaderField(headerFieldList);
		 org.setDigSign(digSignList);
		org.setMaster(masterList);
		return org;
	
	}

	

	public static JsonMainObjctSDN getSDN(List<ImportGeneralManifestMod> blList,ImportGeneralManifestMod   service) {

		ImportGeneralManifestMod objForm = blList.get(0);
		MasterSDN mster = new MasterSDN();
		
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute()).toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SDN";

		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}

		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSDN> itemDtls = new LinkedList<ItemDtlsSDN>();
		List<TrnsprtEqmtSDN> trnsprtEqmt = new LinkedList<TrnsprtEqmtSDN>();
		List<LocCstmSDN> locCstm = new LinkedList<LocCstmSDN>();
		List<TrnsprtDocMsrSDN> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSDN>();
		List<ItnrySDN> itnry = new LinkedList<ItnrySDN>();
		List<TrnsprtDocSDN> trnsprtDoc = new LinkedList<TrnsprtDocSDN>();

		DecRefSDN decRefClaObj = new DecRefSDN();
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(service.getPod(),10)); // value from old screen [Pod]
		decRefClaObj.setJobNo(settingLength(service.getJobNo(),7));
		decRefClaObj.setJobDt(service.getJobDate());
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength( objForm.getRotnNo(),7)); //
		decRefClaObj.setMnfstDtRotnDt(objForm.getRotnDate()); //
		decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
		mster.setDecRef(decRefClaObj);
		
		AuthPrsnSDN authPrsClassObj = new AuthPrsnSDN();
		authPrsClassObj.setSbmtrTyp(settingLength(service.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(service.getSubmitter_code(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength(service.getAuthoriz_rep_code(),10)); //
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(service.getAuthorized_sea_carrier_code(),10)); // LinNo:-211
		authPrsClassObj.setTrmnlOprtrCd(settingLength(service.getCustomTerminalCode(),10)); // LinNo:-132
		mster.setAuthPrsn(authPrsClassObj);
		
		VesselDtlsSDN vesselDtls = new VesselDtlsSDN();
		vesselDtls.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1)); // Line 191
		vesselDtls.setShipTyp(service.getShip_type()); // Line 192
		vesselDtls.setTypOfTrnsprtMeans(settingLength(service.getTypeTransportMeans(),25));
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		mster.setVesselDtls(vesselDtls);
		
		ArvlDtlsSDN arvlDtls = new ArvlDtlsSDN(); // Write by nitun
		arvlDtls.setNmbrOfPsngrs(service.getNoOfPassenger()); // not found
		arvlDtls.setTotalNmbrOfPrsnsOnBoard(service.getPassengerList());
		arvlDtls.setNmbrOfCrew(service.getNoOfCrew());
		arvlDtls.setTotalNoOfCntrsLanded(""); // json not required
		arvlDtls.setTotalNoOfCntrsLoaded(""); // json not required
		arvlDtls.setTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr(service.getTotal_no_of_tran_s_cont_repo_on_ari_dep());
		arvlDtls.setTotalNoOfTrnsprtEqmtRprtdOnArvlDptr(
				service.getTotal_no_of_transport_equipment_reported_on_arrival_departure());
		mster.setArvlDtls(arvlDtls);
		
		TmSuprtDocsSDN  tmSuprtDocs = new TmSuprtDocsSDN();
		tmSuprtDocs.setTagRef(settingLength("",5));
		tmSuprtDocs.setRefSerialNo(settingLength("",5));
		tmSuprtDocs.setBnefcryCdpublic(settingLength("",35));
		tmSuprtDocs.setDocRefNmbr(settingLength("",17));
		tmSuprtDocs.setDocTypCd(settingLength("",6));
		tmSuprtDocs.setIcegateUserid(settingLength("",15));
		tmSuprtDocs.setIrnNmbr(settingLength("",14));		
		tmSuprtDocs.setDocRefNmbr(settingLength("",17));
		tmSuprtDocs.setSubSerialNoRef("");
		List<TmSuprtDocsSDN> tmSuprtDocsSDNList = new LinkedList<TmSuprtDocsSDN>();
		tmSuprtDocsSDNList.add(tmSuprtDocs);
		mster.setTmSuprtDocs(tmSuprtDocsSDNList);
		
		TmAdtnlDecSDN tmAdtnlDec =  new TmAdtnlDecSDN();
		tmAdtnlDec.setTagRef(settingLength("",5));
		tmAdtnlDec.setRefSerialNo(settingLength("",5));
		tmAdtnlDec.setInfoCd(settingLength("",35));
		tmAdtnlDec.setInfoDt("");
		tmAdtnlDec.setInfoMsr(settingLength("",5));
		tmAdtnlDec.setInfoQualifier(settingLength("",10));
		tmAdtnlDec.setInfoText(settingLength("",100));
		tmAdtnlDec.setInfoTyp(settingLength("",10));
		List<TmAdtnlDecSDN> tmAdtnlDecSDNList = new LinkedList<TmAdtnlDecSDN>();
		tmAdtnlDecSDNList.add(tmAdtnlDec);
		mster.setTmAdtnlDec(tmAdtnlDecSDNList);
		
		// ----------------------------------
		HeaderFieldSDN headerFieldClassObj = new HeaderFieldSDN();

		headerFieldClassObj.setSenderID(senderId);
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber(serialNumber);// old screen String sId (
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		headerFieldClassObj.setReportingEvent("SDN");
		
		// -------------------------------------------------
		JsonMainObjctSDN org = new JsonMainObjctSDN();
		org.setHeaderField(headerFieldClassObj);
		org.setMaster(mster);

		return org;
	}

	public static JsonMainObjctSCX getSCX(List<ImportGeneralManifestMod> blList,ImportGeneralManifestMod service,int getSeqNo) {

		ImportGeneralManifestMod objForm = blList.get(0);
   		JsonMainObjctSCX org = new JsonMainObjctSCX();
   		MasterSCX mster = new MasterSCX();
   		
		
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		int fromItemNoTemp =Integer.valueOf(service.getFromItemNo());
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = "F";
		String rpngEvent = "SCX";
		int containerCount = 0;
		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class

		List<ShipItnrySCX> shipItnry = new LinkedList<ShipItnrySCX>();
		
		List<MastrCnsgmtDecSCX> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSCX>();
		
		List<HouseCargoDecSCX> houseCargoDec = new LinkedList<HouseCargoDecSCX>();
		List<MCSuprtDocsSCX> mcSuprtDoc = new LinkedList<MCSuprtDocsSCX>();
		List<MCAdtnlDecSCX> mcAdtnlDec = new LinkedList<MCAdtnlDecSCX>();
		List<HCAdtnlDecSCX> hcAdtnlDec = new LinkedList<HCAdtnlDecSCX>();
		List<HCCrgoSuprtDocsSCX> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSCX>();

		// ===================
//		JsonMainObjctSCX org = new JsonMainObjctSCX();
		for (ImportGeneralManifestMod blObj : blList) {
			if (blObj != null && "true".equalsIgnoreCase(blObj.getIsBlSave())) {
				blObj.setItemNumber(fromItemNoTemp+"");
			} else {
				continue;
			}
			
			MastrCnsgmtDecSCX mastrCnsgmtDec = new MastrCnsgmtDecSCX();
			HouseCargoDecSCX houseCargoDecSCXObj = new HouseCargoDecSCX();
			
			List<NotifyParty> notifyPartyDetailes = objForm.getNotifyParty();
			List<Consignee> consigneeDtls = objForm.getConsignee();
			List<MarksNumber> marksNumberDtls = objForm.getMarksNumber();
			List<Consigner> consignerDtls = objForm.getConsigner();
			List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
			
			List<ItemDtlsSCX> itemDtls = new LinkedList<ItemDtlsSCX>();
			List<TrnsprtEqmtSCX> trnsprtEqmt = new LinkedList<TrnsprtEqmtSCX>();
			List<LocCstmSCX> locCstm = new LinkedList<LocCstmSCX>();
			List<MCRefSCX> mCRef = new LinkedList<MCRefSCX>();
			List<TrnsprtDocMsrSCX> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSCX>();
			List<ItnrySCX> itnry = new LinkedList<ItnrySCX>();
			List<TrnsprtDocSCX> trnsprtDoc = new LinkedList<TrnsprtDocSCX>();
			List<PrevRefSCX> prevRef = new LinkedList<PrevRefSCX>();
			List<TrnshprSCX> trnshpr = new LinkedList<TrnshprSCX>();
			
			
			ItnrySCX itnryClassObj = new ItnrySCX();
			if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("1",5)); 
			}else if (blObj.getPortOfLoading()!= null && blObj.getPod()!= null && blObj.getPortOfDestination() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("2",5)); 		
			}else if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null && 
					blObj.getPortOfDestination() != null && blObj.getPortOfDeschargedCfs() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("3",5)); 
			}
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));    //TODO  guru
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getNext_port_of_call_name(),256));		//TODO  guru
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));			//TODO  guru
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPod(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),4));
			itnry.add(itnryClassObj);
			mastrCnsgmtDec.setItnry(itnryClassObj);
			houseCargoDecSCXObj.setItnry(itnry);
			// --------------------------------------------------------

			List<HCRefSCX> hCRef = new LinkedList<HCRefSCX>();

			HCRefSCX hCRefObj = new HCRefSCX();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(), 20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(), 4));
			hCRefObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(), 16));
			hCRefObj.setPrevDec("S");
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(), 4));
			hCRef.add(hCRefObj);
			houseCargoDecSCXObj.sethCRef(hCRef);
//======================================================================================================

// ------------------------------------MCRefSCX---------------------------------------------------------
			MCRefSCX mCRefClassObj = new MCRefSCX();
			mCRefClassObj.setLineNo(Integer.parseInt(blObj.getItemNumber()));   // Line 60
			mCRefClassObj.setMstrBlNo(settingLength(blObj.getBl(),20)); // Line 53
			mCRefClassObj.setMstrBlDt(removeSlash(blObj.getMasterBlDate()));// Line 53
			
			try {
				if( blObj.getHblArr().isEmpty() || blObj.isHbl()==false  ) {
					 mCRefClassObj.setConsolidatedIndctr("S");// Line 76   //TODO
				}else {
					 mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
				}
			}catch (Exception e) {
				mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
			}
			if(blObj.getHblCount() != 0) {
				mCRefClassObj.setPrevDec(("N"));
			}else  {
				mCRefClassObj.setPrevDec(settingLength("S",4));
			} // Line77
			
			mCRefClassObj.setConsolidatorPan("PAN:"+settingLength(service.getAgentCode(),16)); // Line 78

			mCRef.add(mCRefClassObj);
			mastrCnsgmtDec.setmCRef(mCRefClassObj);

			// ---------------------------------------- Writing a new nitun
			if(blObj.isHbl()== true) {
			PrevRefSCX prevRefObj = new PrevRefSCX();
			if(blObj.isHbl()==true) {
				prevRefObj.setCsnDt(blObj.getCsn_date());
				prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); 
//				prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
				if(blObj.getMcin() != null || blObj.getMcin() !="") {
					prevRefObj.setCinTyp(settingLength(blObj.getMcin(),4));	
				}else {
					if(blObj.getPcin() != null || blObj.getPcin() !="") {
						prevRefObj.setCinTyp(settingLength(blObj.getPcin(),4));		
					}
				}
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	

			prevRef.add(prevRefObj);
			houseCargoDecSCXObj.setPrevRef(prevRef);
			}
			}

			// ----------------------------
			LocCstmSCX locCstmClassObj = new LocCstmSCX();

			locCstmClassObj.setFirstPrtOfEntry(settingLength(blObj.getPod(), 6));
//			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(), 6));// New added
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6));// New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength(blObj.getPortOfDestination(),6));  // New added
			if(blObj.getPortOfLoading().substring(0, 2).equals("IN") && blObj.getPod().substring(0, 2).equals("IN")){
				locCstmClassObj.setTypOfCrgo(settingLength("EX",2)); // if both value in india base
			}
			
			locCstmClassObj.setItemTyp(settingLength("OT", 2)); // Line 61
			locCstmClassObj.setCrgoMvmt(settingLength("TC", 4));// Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength("C", 4)); // Line 59
			locCstm.add(locCstmClassObj);
			mastrCnsgmtDec.setLocCstm(locCstmClassObj);
			houseCargoDecSCXObj.setLocCstm(locCstm);

			// ------------------------------------------
//		TrnshprSCX TrnshprObj = new TrnshprSCX(); // New added
//			TrnshprObj.setTrnsprtDoc("");
//			TrnshprObj.setTrnshprBond(settingLength("", 10));
//			trnstrnsprtDochpr.add(TrnshprObj);
//			houseCargoDecSCXObj.setTrnshpr(trnshpr);

			// ---------------------------------------------------------
			TrnsprtDocMsrSCX trnsprtDocMsrClassObj = new TrnsprtDocMsrSCX();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getTotal_number_of_packages(), 8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getPackage_kind());
			trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrosWeight(), 12, 3));
//			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(), 12, 3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength("KGS", 3));
//			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(), 16, 2));// not																										// Guru
//			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(), 3)); // not cleared by Guru
//			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("", 512));
//			if("".equals(blObj.getVolume()) || blObj.getVolume().isEmpty()) {
			if(blObj.getCargo_msmt() > 0) {
				trnsprtDocMsrClassObj.setGrossVolume (settingLengthForDouble(blObj.getGross_volume(),12,3));
	     		}
				if(! "".equals(trnsprtDocMsrClassObj.getGrossVolume())&& trnsprtDocMsrClassObj.getGrossVolume() != null ) {
					trnsprtDocMsrClassObj.setUnitOfVolume(settingLength("CBM",3));
				}
				
			
//			if(blObj.isHbl()==true) {
//				trnsprtDocMsrClassObj.setNetWeight(generatedFileNameOfJson);
//			}	
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSCXObj.setTrnsprtDocMsr(trnsprtDocMsr);
			
			// ------------------------------------------------------
			if(mCRefClassObj.getConsolidatedIndctr().equals("S")) {
			ItemDtlsSCX itemDtlsClassObj = new ItemDtlsSCX();
			itemDtlsClassObj.setHsCd(blObj.getCommdity_code());
			itemDtlsClassObj.setCrgoItemSeqNmbr(settingLength(blObj.getCommodity_seq()+"", 5));
			itemDtlsClassObj.setCrgoItemDesc(settingLength(blObj.getCargo_item_description(), 256));
			if(blObj.getDgFlag() != null && StringUtils.isNotBlank(blObj.getDgFlag()) ) {
				itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
				itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));	
			}else {
					itemDtlsClassObj.setUnoCd( settingLength("ZZZZZ",5));
					itemDtlsClassObj.setImdgCd( settingLength("ZZZ",3));	
			}
			itemDtlsClassObj.setNmbrOfPkgs(settingLength(blObj.getTotal_number_of_packages(), 8));
			itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getPackage_kind(), 3));	
			itemDtls.add(itemDtlsClassObj);
			mastrCnsgmtDec.setItemDtls(itemDtlsClassObj);
			houseCargoDecSCXObj.setItemDtls(itemDtls);
			}else {
				if(blObj.isHbl()==true) {
					if(mCRefClassObj.getConsolidatedIndctr().equals("H")) {
						ItemDtlsSCX itemDtlsClassObj = new ItemDtlsSCX();
						itemDtlsClassObj.setHsCd(blObj.getCommdity_code());
						//	itemDtlsClassObj.setCrgoItemSeqNmbr(blObj.getCommodity_seq()+"");
							itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCommodity_seq()+"",5));	
							itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
							itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
							itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),4));
							itemDtlsClassObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6)); 
							itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getPackage_kind(),3));
							itemDtls.add(itemDtlsClassObj);
							mastrCnsgmtDec.setItemDtls(itemDtlsClassObj);
							houseCargoDecSCXObj.setItemDtls(itemDtls);
				
			}
				}
			}
			// ------------------------------------------------------
	
			TrnsprtDocSCX trnsprtDocClassObj = new TrnsprtDocSCX();
//			trnsprtDocClassObj.setUcrTyp(settingLength(blObj.getUcr_type(), 3));
//			trnsprtDocClassObj.setUcrCd(settingLength(blObj.getUcr_code(), 35));
			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getAcceptanceName(),256));			//TODO  guru
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getRecieptName(),256));				
			trnsprtDocClassObj.setPrtOfReceiptCdd(settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));	
			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add, 70));
					trnsprtDocClassObj.setNotfdPartyCity(settingLength(notyObj.getCity(), 70));
//					trnsprtDocClassObj.setNotfdPartyCntrySubDivName(settingLength(notyObj.getState(), 35));
					// trnsprtDocClassObj.setNotfdPartyCntrySubDiv((String) notyObj.get(""));
					trnsprtDocClassObj.setNotfdPartyCntryCd(settingLength(notyObj.getCountryCode(), 2));
//					trnsprtDocClassObj.setNotfdPartyPstcd(settingLength(notyObj.getZip(), 9));
//					trnsprtDocClassObj.setTypOfNotfdPartyCd(settingLength(notyObj.getCostumerCode(), 3));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(notyObj.getCostumerName());
				}
			}
//			trnsprtDocClassObj.setPanOfNotfdParty(settingLength(blObj.getPan_of_notified_party(), 17));
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(), 512));
					trnsprtDocClassObj.setGoodsDescAsPerBl(settingLength(marksAndNumberDtls.getDescription(), 512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsrClassObj);
			houseCargoDecSCXObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ---------------------------------------------------

			// for (Object ctnerDtls: containeerDtls) {
			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add, 70));
					trnsprtDocClassObj.setCnsgnesName(settingLength(cnsneeDtl.getCustomerName(), 70));
					trnsprtDocClassObj.setCnsgneCity(settingLength(cnsneeDtl.getCity(), 70));
//					trnsprtDocClassObj.setCnsgneCntrySubDivName(settingLength(cnsneeDtl.getState(), 35));
					// trnsprtDocClassObj.setCnsgneCntrySubDiv((String) cnsneeDtl.get(""));
					trnsprtDocClassObj.setCnsgneCntryCd(settingLength(cnsneeDtl.getCountryCode(), 2));
//					trnsprtDocClassObj.setCnsgnePstcd(settingLength(cnsneeDtl.getZip(), 9));
					trnsprtDocClassObj.setCnsgnrsCd(settingLength(cnsneeDtl.getCustomerCode(), 17));
//					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(), 70));
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add, 70));
					trnsprtDocClassObj.setCnsgnrsName(settingLength(cnsnerDtls.getCustomerName(), 70));
					trnsprtDocClassObj.setCnsgnrCity(settingLength(cnsnerDtls.getCity(), 70));
					trnsprtDocClassObj.setCnsgnrCntrySubDivName(settingLength(cnsnerDtls.getState(), 35));
					trnsprtDocClassObj.setCnsgnrsCd(settingLength(cnsnerDtls.getCustomerCode(), 17));
					 trnsprtDocClassObj.setCnsgnrCntrySubDivCd(blObj.getGstStateCode());
					trnsprtDocClassObj.setCnsgnrCntryCd(settingLength(cnsnerDtls.getCountryCode(), 2));
					trnsprtDocClassObj.setCnsgnrPstcd(settingLength(cnsnerDtls.getZip(), 9));
//					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(), 70));
				}		
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			mastrCnsgmtDec.setTrnsprtDoc(trnsprtDocClassObj);
			houseCargoDecSCXObj.setTrnsprtDoc(trnsprtDoc);
			// ------------------------------------------------

			
			int j = 0 ;
			Set<String> containseSets= new HashSet<>();
			for (ContainerDetails ctnerDtl : containerDtls) {
				
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo()) && !containseSets.contains(ctnerDtl.getContainerNumber())) {
					j++;
					containseSets.add(ctnerDtl.getContainerNumber());
					TrnsprtEqmtSCX trnsprtEqmtClassObj = new TrnsprtEqmtSCX();
					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(j+"",5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(), 17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN", 3)); // alway CN hard codded
																			// customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getIsoCode(),4));// optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(), 3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(), 5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(), 15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(), 1));
//					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("", 256));
//					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("", 256));
//					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
//					trnsprtEqmtClassObj.setEventDt("");
//					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("", 10));
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(service.getAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(), 14, 2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(), 8));
					trnsprtEqmt.add(trnsprtEqmtClassObj);
					}
				}  
				mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
			houseCargoDecSCXObj.setTrnsprtEqmt(trnsprtEqmt);
//			---------------------------------------------------------------------------
					for (ContainerDetails cntnerDtl : containerDtls) {

//						System.out.println("   coneeDtls  " + i + (cntnerDtl.getContainerSealNumber()));
						containerCount++;
					}
			// ============================================================
//		HCCrgoSuprtDocsSCX crgoSuprtDocs = new HCCrgoSuprtDocsSCX();
//			crgoSuprtDocs.setBnefcryCdpublic(settingLength("", 35));
//			crgoSuprtDocs.setDocRefNmbr(settingLength("", 17));
//			crgoSuprtDocs.setDocTypCd(settingLength("", 6));
//			crgoSuprtDocs.setIcegateUserid(settingLength("", 15));
//			crgoSuprtDocs.setIrnNmbr(settingLength("", 14));
//			crgoSuprtDocs.setRefSerialNo(settingLength("", 5));
//			crgoSuprtDocs.setSubSerialNoRef("");
//			crgoSuprtDocs.setTagRef(settingLength("", 5));
//			crgoSuprtDoc.add(crgoSuprtDocs);
//			houseCargoDecSCXObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
			// ==========================================================
//			HCAdtnlDecSCX adtnlDec = new HCAdtnlDecSCX();
//			adtnlDec.setTagRef(settingLength("", 5));
//			adtnlDec.setRefSerialNo(settingLength("", 5));
//			adtnlDec.setInfoCd(settingLength("", 35));
//			adtnlDec.setInfoDt("");
//			adtnlDec.setInfoMsr(settingLength("", 5));
//			adtnlDec.setInfoQualifier(settingLength("", 10));
//			adtnlDec.setInfoText(settingLength("", 100));
//			adtnlDec.setInfoTyp(settingLength("", 10));
//
//			hcAdtnlDec.add(adtnlDec);
//			houseCargoDecSCXObj.setHcAdtnlDec(hcAdtnlDec);
			// ----------------------------------------------------------------
//			MCSuprtDocsSCX mcSuprtDocs = new MCSuprtDocsSCX();
//			mcSuprtDocs.setBnefcryCdpublic(settingLength("", 35));
//			mcSuprtDocs.setDocRefNmbr(settingLength("", 17));
//			mcSuprtDocs.setDocTypCd(settingLength("", 6));
//			mcSuprtDocs.setIcegateUserid(settingLength("", 15));
//			mcSuprtDocs.setIrnNmbr(settingLength("", 14));
//			mcSuprtDocs.setRefSerialNo(settingLength("", 5));
//			mcSuprtDocs.setSubSerialNoRef("");
//			mcSuprtDocs.setTagRef(settingLength("", 5));
//			mcSuprtDoc.add(mcSuprtDocs);
			// ------------------------------------------------------------
//			MCAdtnlDecSCX mcAdtnlDecs = new MCAdtnlDecSCX();
//			mcAdtnlDecs.setTagRef(settingLength("", 5));
//			mcAdtnlDecs.setRefSerialNo(settingLength("", 5));
//			mcAdtnlDecs.setInfoCd(settingLength("", 35));
//			mcAdtnlDecs.setInfoDt("");
//			mcAdtnlDecs.setInfoMsr(settingLength("", 5));
//			mcAdtnlDecs.setInfoQualifier(settingLength("", 10));
//			mcAdtnlDecs.setInfoText(settingLength("", 100));
//			mcAdtnlDecs.setInfoTyp(settingLength("", 10));
//
//			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
//			ShipItnrySCX shipItny3 = new ShipItnrySCX();
//			String prtOfCallCdd = null;
//			String itnrySeq = null;
//			// all value set
//			if (objForm.getNextport1() == null || objForm.getLastPort1() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "-3";
//				prtOfCallCdd = objForm.getLastPort1();
//			}
//			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
//			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd, 10));
//
//			if (objForm.getNextport2() == null || objForm.getNextport2() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "-2";
//				prtOfCallCdd = objForm.getLastPort2();
//			}
//			ShipItnrySCX shipItnry2 = new ShipItnrySCX();
//			shipItnry2.setShipItnrySeq(itnrySeq);
//			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd, 10));
//
//			if (objForm.getNextport3() == null || objForm.getLastPort3() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "-1";
//
//				prtOfCallCdd = objForm.getLastPort3();
//			}
//			ShipItnrySCX shipItnry1 = new ShipItnrySCX();
//			shipItnry1.setShipItnrySeq(itnrySeq);
//			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd, 10));
//
//			ShipItnrySCX shipItnry0 = new ShipItnrySCX();
//			shipItnry0.setShipItnrySeq("0");
//			shipItnry0.setPrtOfCallCdd(settingLength(objForm.getPod(), 10)); // blObj.get("Port of call sequence
//																				// numbe"));
//			shipItnry0.setPrtOfCallName(settingLength(blObj.getPort_of_call_coded(), 256));
//
//			if (objForm.getLastPort1() == null || objForm.getLastPort1() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "1";
//				prtOfCallCdd = objForm.getLastPort1();
//			}
//			ShipItnrySCX shipItnry11 = new ShipItnrySCX();
//			shipItnry11.setShipItnrySeq(itnrySeq);
//			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd, 10));
//
//			if (objForm.getLastPort2() == null || objForm.getLastPort2() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "2";
//				prtOfCallCdd = objForm.getLastPort2();
//			}
//			ShipItnrySCX shipItnry22 = new ShipItnrySCX();
//			shipItnry22.setShipItnrySeq(itnrySeq);
//			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd, 10));
//			shipItnry22.setPrtOfCallName(settingLength(blObj.getPort_of_call_coded(), 256));
//
//			if (objForm.getLastPort3() == null || objForm.getLastPort3() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "3";
//				prtOfCallCdd = objForm.getLastPort3();
//			}
//			ShipItnrySCX shipItnry33 = new ShipItnrySCX();
//			shipItnry33.setShipItnrySeq(itnrySeq);
//			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd, 10));
//			shipItnry33.setPrtOfCallName(settingLength(blObj.getPort_of_call_sequence_number(), 256));
//			shipItnry.add(shipItny3);
//			shipItnry.add(shipItnry2);
//			shipItnry.add(shipItnry1);
//			shipItnry.add(shipItnry0);
//			shipItnry.add(shipItnry11);
//			shipItnry.add(shipItnry22);
//			shipItnry.add(shipItnry33);
//			// ------------------------------------------------------
//			houseCargoDec.add(houseCargoDecSCXObj);
//		}

		// now add all List to relevant class

//		
//		mastrCnsgmtDec.setItemDtls(itemDtls);
//		mastrCnsgmtDec.setItnry(itnry);
//		mastrCnsgmtDec.setLocCstm(locCstm);
//		mastrCnsgmtDec.setmCRef(mCRef);
//		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
//		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
//		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
//		mastrCnsgmtDec.setPrevRef(prevRef);
//		mastrCnsgmtDec.setTrnshpr(trnshpr);
//		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec);
//		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
//		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
		
		mastrCnsgmtDecList.add(mastrCnsgmtDec);

		// ----------------------------
		PrsnDtlsSCX prsDtls = new PrsnDtlsSCX();
		prsDtls.setPrsnTypCdd(settingLength("", 3));
		prsDtls.setPrsnFamilyName(settingLength("", 70));
		prsDtls.setPrsnGivenName(settingLength("", 70));
		prsDtls.setPrsnNatnltyCdd(settingLength("", 2));
		prsDtls.setPsngrInTransitIndctr(settingLength("", 1));
		prsDtls.setCrewmemberRankOrRatingCdd("");
		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("", 5));
		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("", 5));
		prsDtls.setPrsnGenderCdd(settingLength("", 3));
		prsDtls.setPrsnDtOfBirth("");
		prsDtls.setPrsnPlaceOfBirthName(settingLength("", 35));
		prsDtls.setPrsnCntryOfBirthCdd(settingLength("", 2));
		// -----------------------------------------

		PrsnIdSCX prsnIdclassObj = new PrsnIdSCX();
		prsnIdclassObj.setPrsnIdDocExpiryDt("");
		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("", 2));
		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("", 70));
		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("", 3));
		List<PrsnIdSCX> prsnIdList = new LinkedList<PrsnIdSCX>();
		prsnIdList.add(prsnIdclassObj);

		// ---------------
		List<PrsnDtlsSCX> prsnDtlsList = new LinkedList<PrsnDtlsSCX>();
		prsnDtlsList.add(prsDtls);
//		PrsnOnBoardSCX prsnOnBoard = new PrsnOnBoardSCX();
//		prsnOnBoard.setPrsnDtls(prsnDtlsList);
//		prsnOnBoard.setPrsnId(prsnIdList);
//		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("", 5));

//		List<PrsnOnBoardSCX> prsnOnBoardList = new LinkedList<PrsnOnBoardSCX>();
//		prsnOnBoardList.add(prsnOnBoard);
		// --------------------------------------------------------
//		ShipStoresSCX shipStores = new ShipStoresSCX();
//		shipStores.setSeqNmbr(settingLength("", 5));
//		shipStores.setArticleNameCdd(settingLength("", 18));
//		shipStores.setArticleNameText(settingLength("", 512));
//		shipStores.setLocOnbrdText(settingLength("", 256));
//		shipStores.setQntyCdOnbrd(settingLength("", 3));
//		shipStores.setQntyOnbrd(settingLengthForDouble("", 16, 6));
//		List<ShipStoresSCX> shipStoresList = new LinkedList<ShipStoresSCX>();
//		shipStoresList.add(shipStores);
		// ----------------------------------------------------------------
//		 ArvlDtlsSCX arvlDtls = new ArvlDtlsSCX();
//		 arvlDtls.setNmbrOfCrew(generatedFileNameOfJson);
//		 arvlDtls.setNmbrOfPsngrs(generatedFileNameOfJson);
//		 arvlDtls.setTotalNmbrOfPrsnsOnBoard(generatedFileNameOfJson);
//		 arvlDtls.setTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr(generatedFileNameOfJson);
//		 arvlDtls.setTotalNoOfCntrsLoaded(generatedFileNameOfJson);
//		 arvlDtls.setTotalNoOfTrnsprtEqmtRprtdOnArvlDptr(generatedFileNameOfJson);
//		 List<ArvlDtlsSCE> arvlDtlsList = new LinkedList<ArvlDtlsSCE>();
//		 arvlDtlsList.add(arvlDtls);
		
		//------------------------------------------------------------------------

//		int i = 0;
//		List<VoyageTransportEquipmentSCX> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSCX>();
//		for (ContainerDetails cntnerDtl : containerDtls) {
//
//			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));
//
//			if(blObj.isHbl()== false) {
//			VoyageTransportEquipmentSCX voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSCX();
//			voyageTransportEquipmentClassObj.setQuipmentSequenceNo(cntnerDtl.getContainerSealNumber());
//			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
//			voyageTransportEquipmentClassObj.setQuipmentType("CN");
//			voyageTransportEquipmentClassObj
//					.setQuipmentLoadStatus(settingLength(cntnerDtl.getEquipmentLoadStatus(), 3));
//			voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(), 1));
//			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
//			}
//		}

		// ------------------------------------------------------------
//		DigSignSCX digSignClassObj = new DigSignSCX();
//		digSignClassObj.setStartSignature("");
//		digSignClassObj.setStartCertificate("");
//		digSignClassObj.setSignerVersion("");
//
//		List<DigSignSCX> digSignList = new LinkedList<DigSignSCX>();
//		digSignList.add(digSignClassObj);
		// ----------------
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		houseCargoDec.add(houseCargoDecSCXObj);
		if (blObj.isHbl()== true) {
			mastrCnsgmtDec.setHouseCargoDec(houseCargoDec);
		}
	}
//		---------------------end of loop---------------------
		HeaderFieldSCX headerFieldClassObj = new HeaderFieldSCX();

		headerFieldClassObj.setSenderID(settingLength(service.getSenderId(),20));
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(settingLength(service.getRecieverId(),20));
		headerFieldClassObj.setVersionNo("SCX1102");
		headerFieldClassObj.setIndicator("T");
		headerFieldClassObj.setMessageID("SACHM22");
		headerFieldClassObj.setSequenceOrControlNumber(getSeqNo+1);// old screen String sId (
		headerFieldClassObj.setDate(getTimeHeader());
		headerFieldClassObj.setTime("T" + getIsdTime());
		headerFieldClassObj.setReportingEvent("SCX");
		// -------------------------------------------------

		VoyageDtlsSCX voyageDtlsClassObj = new VoyageDtlsSCX();
//		voyageDtlsClassObj.setVoyageNo(voyage); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(settingLength(service.getViaVcn(),35)); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted( settingLength(containerCount+"",5));// Line:-46
//		voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
//		voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(settingLength(service.getTotalItem() ,5));// Line38 (objForm.getTotalItem()); nitun
//		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));
//		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
//		voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
//		voyageDtlsClassObj.setShipItnry(shipItnry);
//-------------------------------------------------------------------------------------------------
				VesselDtlsSCX vesselDtls = new VesselDtlsSCX();

				vesselDtls.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1));// Line 191
				if(service.getTypeTransportMeans().equals("imovsl")) {
					vesselDtls.setTypOfTrnsprtMeans(" "); // not found
				}else {
					vesselDtls.setTypOfTrnsprtMeans(settingLength(service.getTypeTransportMeans(),25));
				}
				vesselDtls.setTrnsprtMeansId(settingLength(service.getImoCode(),25));
//				vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
//				vesselDtls.setPurposeOfCall("1"); // always hard coded
				List<VesselDtlsSCX> vesselDtlsList = new LinkedList<VesselDtlsSCX>();
				vesselDtlsList.add(vesselDtls);
				
				// ----------------------------
				AuthPrsnSCX authPrsClassObj = new AuthPrsnSCX();
				authPrsClassObj.setSbmtrTyp(settingLength(service.getSubmitter_type(),4)); //
				authPrsClassObj.setSbmtrCd(settingLength(service.getAgentCode(),15)); //
				authPrsClassObj.setAuthReprsntvCd(settingLength(service.getAuthReprsntvCd(),10));   //
//		 		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
//				authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(), 10)); // LinNo:-211
//				authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(), 30));// 21
//				authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(), 10)); // LinNo:-190
//				authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(), 10));// LinNo:-132

				List<AuthPrsnSCX> authPrsnList = new LinkedList<AuthPrsnSCX>();
				authPrsnList.add(authPrsClassObj);
				// ----------------------------
				DecRefSCX decRefClaObj = new DecRefSCX();

				  decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
				    decRefClaObj.setPrtofRptng(settingLength(service.getCustomCode(),10));
				    decRefClaObj.setJobNo(getSeqNo +1);
				    decRefClaObj.setJobDt(currDate);
				    decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
//				    decRefClaObj.setMnfstNoRotnNo(settingLength(service.getRotnNo(),7));
//				    decRefClaObj.setMnfstDtRotnDt(service.getRotnDate()	);
//				    decRefClaObj.setVesselTypMvmt(settingLength("FI",2));
				    mster.setDecRef(decRefClaObj);
				    decRefClaObj = null;
				List<DecRefSCX> decRefList = new LinkedList<DecRefSCX>();
				decRefList.add(decRefClaObj);
				List<VoyageDtlsSCX> voyageDtlsList = new LinkedList<VoyageDtlsSCX>();
				voyageDtlsList.add(voyageDtlsClassObj);
//		---------------------------------------------------------------------------------		
		
		
				mster.setVoyageDtls(voyageDtlsClassObj);
				mster.setVesselDtls(vesselDtls);
				mster.setAuthPrsn(authPrsClassObj);
//				mster.setDecRef(decRefList);
//				mster.setPrsnOnBoard(prsnOnBoardList);	
//		mster.setPrsnOnBoard(prsnOnBoardList);
//		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
//		mster.setShipStores(shipStoresList);
		org.setHeaderField(headerFieldClassObj);
		org.setMaster(mster);
		return org;
		
	}

	public static JsonMainObjctSCD getSCD(List<ImportGeneralManifestMod> blList) {

		ImportGeneralManifestMod objForm = blList.get(0);

		List<NotifyParty> notifyPartyDetailes = objForm.getNotifyParty();
		List<Consignee> consigneeDtls = objForm.getConsignee();
		List<MarksNumber> marksNumberDtls = objForm.getMarksNumber();
		List<Consigner> consignerDtls = objForm.getConsigner();
		List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SCD";
		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSCD> itemDtls = new LinkedList<ItemDtlsSCD>();
		List<TrnsprtEqmtSCD> trnsprtEqmt = new LinkedList<TrnsprtEqmtSCD>();
		List<LocCstmSCD> locCstm = new LinkedList<LocCstmSCD>();
		List<MCRefSCD> mCRef = new LinkedList<MCRefSCD>();
		List<TrnsprtDocMsrSCD> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSCD>();
		List<ShipItnrySCD> shipItnry = new LinkedList<ShipItnrySCD>();
		List<ItnrySCD> itnry = new LinkedList<ItnrySCD>();
		List<TrnsprtDocSCD> trnsprtDoc = new LinkedList<TrnsprtDocSCD>();
		List<PrevRefSCD> prevRef = new LinkedList<PrevRefSCD>();
		List<TrnshprSCD> trnshpr = new LinkedList<TrnshprSCD>();
		List<HouseCargoDecSCD> houseCargoDec = new LinkedList<HouseCargoDecSCD>();
		List<MCSuprtDocsSCD> mcSuprtDoc = new LinkedList<MCSuprtDocsSCD>();
		List<MCAdtnlDecSCD> mcAdtnlDec= new LinkedList<MCAdtnlDecSCD>();
		List<HCAdtnlDecSCD> hcAdtnlDec= new LinkedList<HCAdtnlDecSCD>();
		List<HCCrgoSuprtDocsSCD> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSCD>();
		
		// ===================

		for (ImportGeneralManifestMod blObj : blList) {
			HouseCargoDecSCD houseCargoDecSCDObj = new HouseCargoDecSCD();
			ItnrySCD itnryClassObj = new ItnrySCD();
			itnryClassObj.setPrtOfCallSeqNmbr(settingLength(blObj.getPort_of_call_sequence_number(),5));
		    // itnryClassObj.setModeOfTrnsprt(blObj.getCargoMovmnt());
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPort_of_call_cod(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),1));// Line
			itnry.add(itnryClassObj);
			houseCargoDecSCDObj.setItnry(itnry);
			// --------------------------------------------------------
			
			List<HCRefSCD> hCRef = new LinkedList<HCRefSCD>();

			HCRefSCD hCRefObj = new HCRefSCD();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));
			hCRefObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); 
			hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),4));
			hCRef.add(hCRefObj);
			houseCargoDecSCDObj.sethCRef(hCRef);
		//======================================================================================	

			
			// ----------------------------------------
			MCRefSCD mCRefClassObj = new MCRefSCD();
			mCRefClassObj.setLineNo(blObj.getItemNumber()); // Line 60
			mCRefClassObj.setMstrBlNo(settingLength(blObj.getBl(),20)); // Line 53
			mCRefClassObj.setMstrBlDt(blObj.getBlDate()); // Line 53
			mCRefClassObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4)); // Line 76
			mCRefClassObj.setPrevDec(blObj.getPrevious_declaration()); // Line77
			mCRefClassObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16));  // Line 78

			mCRef.add(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
			PrevRefSCD prevRefObj = new PrevRefSCD();
			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			prevRefObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4));
//			prevRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			prevRefObj.setSplitIndctr(settingLength(blObj.getSplit_indicator_list(),2));
			prevRefObj.setNmbrOfPkgs(settingLengthForDouble(blObj.getTotal_number_of_packages(),16,6));
			prevRefObj.setTypOfPackage(settingLength(blObj.getType_of_package(),4));

			prevRef.add(prevRefObj);
			houseCargoDecSCDObj.setPrevRef(prevRef);
		
			// ----------------------------
			LocCstmSCD locCstmClassObj = new LocCstmSCD();

			locCstmClassObj.setFirstPrtOfEntry( settingLength(blObj.getFirst_port_of_entry_last_port_of_departure(),6));
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength("",6));  // New added
			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2)); // Line 90
			locCstmClassObj.setItemTyp(settingLength(blObj.getItemType(),2)); // Line 61
			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4)); // Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength(blObj.getCargoNature(),4));  // Line 59
			locCstm.add(locCstmClassObj);
			houseCargoDecSCDObj.setLocCstm(locCstm);
		
			// ------------------------------------------
			TrnshprSCD TrnshprObj = new TrnshprSCD(); // New added
			TrnshprObj.setTrnsprtDoc("");
			TrnshprObj.setTrnshprBond(settingLength("",10));
			trnshpr.add(TrnshprObj);
			houseCargoDecSCDObj.setTrnshpr(trnshpr);
			
			// ---------------------------------------------------------
			TrnsprtDocMsrSCD trnsprtDocMsrClassObj = new TrnsprtDocMsrSCD();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getTotal_number_of_packages(),8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrossCargoWeightBLlevel(),12,3));
			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength(blObj.getUnit_of_weight(),3));
			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt (settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); // not cleared by Guru
			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3));  // not cleared by Guru
			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			trnsprtDocMsrClassObj.setGrossVolume(settingLengthForDouble(blObj.getVolume(),12,3));
			trnsprtDocMsrClassObj.setUnitOfVolume(settingLength(blObj.getUnit_of_volume(),3));
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSCDObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ------------------------------------------------------
			ItemDtlsSCD itemDtlsClassObj = new ItemDtlsSCD();
			// trnsprtEqmtClassObj.setHsCd((String)blObj.get(" ")); not cleared by guru
			itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCargo_item_sequence_no(),5));
			itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
		
				itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
			itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));
			itemDtlsClassObj.setNmbrOfPkgs(settingLength( "",8));
			itemDtlsClassObj.setTypOfPkgs(settingLength("",3));
			itemDtls.add(itemDtlsClassObj);
			houseCargoDecSCDObj.setItemDtls(itemDtls);
			// ------------------------------------------------------
			TrnsprtDocSCD trnsprtDocClassObj = new TrnsprtDocSCD();

			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getPort_of_acceptance_name(),256));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getPort_of_receipt_name(),256));
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));
			trnsprtDocClassObj.setPrtOfReceiptCdd( settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setUcrTyp( settingLength(blObj.getUcr_type(),3));
			trnsprtDocClassObj.setUcrCd( settingLength(blObj.getUcr_code(),35));	

			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setNotfdPartyCity( settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName( settingLength(notyObj.getStateName(),35));
					// trnsprtDocClassObj.setNotfdPartyCntrySubDiv((String) notyObj.get(""));
					trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyPstcd(settingLength(notyObj.getZip(),9));
					trnsprtDocClassObj.setTypOfNotfdPartyCd( settingLength(notyObj.getCostumerCode(),3));
				}
			}
			trnsprtDocClassObj.setPanOfNotfdParty( settingLength(blObj.getPan_of_notified_party(),17));
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl(settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			houseCargoDecSCDObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ---------------------------------------------------

			// for (Object ctnerDtls: containeerDtls) {
			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnesName(  settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCity( settingLength(cnsneeDtl.getCity(),70));
					trnsprtDocClassObj.setCnsgneCntrySubDivName( settingLength(cnsneeDtl.getState(),35));
					// trnsprtDocClassObj.setCnsgneCntrySubDiv((String) cnsneeDtl.get(""));
					trnsprtDocClassObj.setCnsgneCntryCd(settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnePstcd( settingLength(cnsneeDtl.getZip(),9));
					trnsprtDocClassObj.setCnsgnrsCd(settingLength(cnsneeDtl.getCustomerCode(),17));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnrsName( settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrCity( settingLength(cnsnerDtls.getCity(),70));
					trnsprtDocClassObj.setCnsgnrCntrySubDivName( settingLength(cnsnerDtls.getState(),35));
					trnsprtDocClassObj.setCnsgnrsCd( settingLength(cnsnerDtls.getCustomerCode(),17));
					// trnsprtDocClassObj.setCnsgnrCntrySubDivCd((String) cnsnerDtls.get(""));
					trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnrPstcd(settingLength(cnsnerDtls.getZip(),9));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
				}
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			houseCargoDecSCDObj.setTrnsprtDoc(trnsprtDoc);
			// ------------------------------------------------

			TrnsprtEqmtSCD trnsprtEqmtClassObj = new TrnsprtEqmtSCD();

			for (ContainerDetails ctnerDtl : containerDtls) {
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo())) {

					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(ctnerDtl.getContainerAgentCode(),5));
					trnsprtEqmtClassObj.setEqmtId(  settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3));// alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getContainerSize(),4)); // optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256));
					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256));
					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
					trnsprtEqmtClassObj.setEventDt("");
					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10));
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(ctnerDtl.getContainerAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
				}
			} // add to trnsprtDocMsr List
			trnsprtEqmt.add(trnsprtEqmtClassObj);
			houseCargoDecSCDObj.setTrnsprtEqmt(trnsprtEqmt);
			//============================================================
			HCCrgoSuprtDocsSCD crgoSuprtDocs = new HCCrgoSuprtDocsSCD(); 
			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			crgoSuprtDocs.setDocRefNmbr(settingLength("",17));
			crgoSuprtDocs.setDocTypCd(settingLength("",6));
			crgoSuprtDocs.setIcegateUserid(settingLength("",15));
			crgoSuprtDocs.setIrnNmbr(settingLength("",14));
			crgoSuprtDocs.setRefSerialNo(settingLength("",5));
			crgoSuprtDocs.setSubSerialNoRef("");
			crgoSuprtDocs.setTagRef(settingLength("",5));
			crgoSuprtDoc.add(crgoSuprtDocs);
			houseCargoDecSCDObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
			//==========================================================
			HCAdtnlDecSCD adtnlDec = new HCAdtnlDecSCD();
			adtnlDec.setTagRef(settingLength("",5));
			adtnlDec.setRefSerialNo(settingLength("",5));
			adtnlDec.setInfoCd(settingLength("",35));
			adtnlDec.setInfoDt("");
			adtnlDec.setInfoMsr(settingLength("",5));
			adtnlDec.setInfoQualifier(settingLength("",10));
			adtnlDec.setInfoText(settingLength("",100));
			adtnlDec.setInfoTyp(settingLength("",10));
			
			hcAdtnlDec.add(adtnlDec);
			houseCargoDecSCDObj.setHcAdtnlDec(hcAdtnlDec);
			//----------------------------------------------------------------
			MCSuprtDocsSCD mcSuprtDocs = new MCSuprtDocsSCD (); 
			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			mcSuprtDocs.setDocRefNmbr(settingLength("",17));
			mcSuprtDocs.setDocTypCd(settingLength("",6));
			mcSuprtDocs.setIcegateUserid(settingLength("",15));
			mcSuprtDocs.setIrnNmbr(settingLength("",14));
			mcSuprtDocs.setRefSerialNo(settingLength("",5));
			mcSuprtDocs.setSubSerialNoRef("");
			mcSuprtDocs.setTagRef(settingLength("",5));
			mcSuprtDoc.add(mcSuprtDocs);
			//------------------------------------------------------------
			MCAdtnlDecSCD mcAdtnlDecs = new MCAdtnlDecSCD();
			mcAdtnlDecs.setTagRef(settingLength("",5));
			mcAdtnlDecs.setRefSerialNo(settingLength("",5));
			mcAdtnlDecs.setInfoCd(settingLength("",35));
			mcAdtnlDecs.setInfoDt("");
			mcAdtnlDecs.setInfoMsr(settingLength("",5));
			mcAdtnlDecs.setInfoQualifier(settingLength("",10));
			mcAdtnlDecs.setInfoText(settingLength("",100));
			mcAdtnlDecs.setInfoTyp(settingLength("",10));
			
			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
			ShipItnrySCD shipItny3 = new ShipItnrySCD();
			String prtOfCallCdd = null;
			String itnrySeq = null;
			// all value set
			if (objForm.getNextport1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-3";
				prtOfCallCdd = objForm.getLastPort1();
			}
			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport2() == null || objForm.getNextport2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySCD shipItnry2 = new ShipItnrySCD();
			shipItnry2.setShipItnrySeq(itnrySeq);
			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-1";

				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySCD shipItnry1 = new ShipItnrySCD();
			shipItnry1.setShipItnrySeq(itnrySeq);
			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			ShipItnrySCD shipItnry0 = new ShipItnrySCD();
			shipItnry0.setShipItnrySeq("0");
			shipItnry0.setPrtOfCallCdd(settingLength(objForm.getPod(),10)); // blObj.get("Port of call sequence numbe"));
			shipItnry0.setPrtOfCallName(  settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "1";
				prtOfCallCdd = objForm.getLastPort1();
			}
			ShipItnrySCD shipItnry11 = new ShipItnrySCD();
			shipItnry11.setShipItnrySeq(itnrySeq);
			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getLastPort2() == null || objForm.getLastPort2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySCD shipItnry22 = new ShipItnrySCD();
			shipItnry22.setShipItnrySeq(itnrySeq);
			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry22.setPrtOfCallName( settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "3";
				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySCD shipItnry33 = new ShipItnrySCD();
			shipItnry33.setShipItnrySeq(itnrySeq);
			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry33.setPrtOfCallName( settingLength(blObj.getPort_of_call_sequence_number(),256));
			shipItnry.add(shipItny3);
			shipItnry.add(shipItnry2);
			shipItnry.add(shipItnry1);
			shipItnry.add(shipItnry0);
			shipItnry.add(shipItnry11);
			shipItnry.add(shipItnry22);
			shipItnry.add(shipItnry33);
			// ------------------------------------------------------
			houseCargoDec.add(houseCargoDecSCDObj);
		}
		
		// now add all List to relevant class

		MastrCnsgmtDecSCD mastrCnsgmtDec = new MastrCnsgmtDecSCD();
		mastrCnsgmtDec.setItemDtls(itemDtls);
		mastrCnsgmtDec.setItnry(itnry);
		mastrCnsgmtDec.setLocCstm(locCstm);
		mastrCnsgmtDec.setmCRef(mCRef);
		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
		mastrCnsgmtDec.setPrevRef(prevRef);
		mastrCnsgmtDec.setTrnshpr(trnshpr);
		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
		List<MastrCnsgmtDecSCD> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSCD>();
		mastrCnsgmtDecList.add(mastrCnsgmtDec);
		

		VoyageDtlsSCD voyageDtlsClassObj = new VoyageDtlsSCD();
		voyageDtlsClassObj.setVoyageNo(voyage); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(objForm.getConveyance_reference_no()); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted(objForm.getTotalNmbrOfLines()); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(objForm.getTotalIteamNo()); // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
		voyageDtlsClassObj.setShipItnry(shipItnry);

		List<VoyageDtlsSCD> voyageDtlsList = new LinkedList<VoyageDtlsSCD>();
		voyageDtlsList.add(voyageDtlsClassObj);
		// ---------------------------------------------------
		VesselDtlsSCD vesselDtls = new VesselDtlsSCD();

		vesselDtls.setModeOfTrnsprt(settingLength(objForm.getMode_of_transport(),1));// Line// Line 191
		vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25));// not found
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
		vesselDtls.setPurposeOfCall("1"); // always hard coded
		List<VesselDtlsSCD> vesselDtlsList = new LinkedList<VesselDtlsSCD>();
		vesselDtlsList.add(vesselDtls);
		// ----------------------------
		AuthPrsnSCD authPrsClassObj = new AuthPrsnSCD();
		authPrsClassObj.setSbmtrTyp(settingLength(objForm.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(objForm.getSubmitter_code(),15));//
		authPrsClassObj.setAuthReprsntvCd(settingLength( objForm.getAuthoriz_rep_code(),10)); //
		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(),10));// LinNo:-211
		authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
		authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(),10)); // LinNo:-190
		authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(),10)); // LinNo:-132

		List<AuthPrsnSCD> authPrsnList = new LinkedList<AuthPrsnSCD>();
		authPrsnList.add(authPrsClassObj);
		// ----------------------------
		DecRefSCD decRefClaObj = new DecRefSCD();

		// decRefClaObj.setMsgTyp(objForm.getMesstype());
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(objForm.getPod(),10)); // value from old screen [Pod]
		// decRefClaObj.setJobNo(objForm.getJobNum()); // sid will give this Number
		decRefClaObj.setJobNo(settingLength(jobNum,7));
		// decRefClaObj.setJobDt(objForm.getJobDate()); //sid told me to keep crunt date
		decRefClaObj.setJobDt(currDate);
		// decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength(objForm.getManifest_no_csn_no(),7)); //
		decRefClaObj.setMnfstDtRotnDt(objForm.getManifest_date_csn_date()); //
		decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
		// #
		// * decRefClaObj.setDptrMnfstNo(); //
		// *decRefClaObj.setDptrMnfstDt(""); //
		// #
		List<DecRefSCD> decRefList = new LinkedList<DecRefSCD>();
		decRefList.add(decRefClaObj);
		// ----------------------------
		PrsnDtlsSCD prsDtls = new PrsnDtlsSCD();
		prsDtls.setPrsnTypCdd(settingLength("",3));
		prsDtls.setPrsnFamilyName(settingLength("",70));
		prsDtls.setPrsnGivenName(settingLength("",70));
		prsDtls.setPrsnNatnltyCdd(settingLength("",2));
		prsDtls.setPsngrInTransitIndctr(settingLength("",1));
		prsDtls.setCrewmemberRankOrRatingCdd("");
		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
		prsDtls.setPrsnGenderCdd(settingLength("",3));
		prsDtls.setPrsnDtOfBirth("");
		prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
		prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
		// -----------------------------------------

		PrsnIdSCD prsnIdclassObj = new PrsnIdSCD();
		prsnIdclassObj.setPrsnIdDocExpiryDt("");
		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
		List<PrsnIdSCD> prsnIdList = new LinkedList<PrsnIdSCD>();
		prsnIdList.add(prsnIdclassObj);

		// ---------------
		List<PrsnDtlsSCD> prsnDtlsList = new LinkedList<PrsnDtlsSCD>();
		prsnDtlsList.add(prsDtls);
		PrsnOnBoardSCD prsnOnBoard = new PrsnOnBoardSCD();
		prsnOnBoard.setPrsnDtls(prsnDtlsList);
		prsnOnBoard.setPrsnId(prsnIdList);
		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));

		List<PrsnOnBoardSCD> prsnOnBoardList = new LinkedList<PrsnOnBoardSCD>();
		prsnOnBoardList.add(prsnOnBoard);
		// --------------------------------------------------------
		ShipStoresSCD shipStores = new ShipStoresSCD();
		shipStores.setSeqNmbr(settingLength("",5));
		shipStores.setArticleNameCdd(settingLength("",18));
		shipStores.setArticleNameText(settingLength("",512));
		shipStores.setLocOnbrdText(settingLength("",256));
		shipStores.setQntyCdOnbrd(settingLength("",3));
		shipStores.setQntyOnbrd(settingLengthForDouble("",16,6));
		List<ShipStoresSCD> shipStoresList = new LinkedList<ShipStoresSCD>();
		shipStoresList.add(shipStores);
		//----------------------------------------------------------------
		
		//-------------------------------------------------------
		int i = 0;
		List<VoyageTransportEquipmentSCD> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSCD>();
		for (ContainerDetails cntnerDtl : containerDtls) {

			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

			VoyageTransportEquipmentSCD voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSCD();
			voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
			voyageTransportEquipmentClassObj.setQuipmentType("CN");
			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(cntnerDtl.getEquipmentLoadStatus());
			voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
		}
		
		// ------------------------------------------------------------
		DigSignSCD digSignClassObj = new DigSignSCD();
		digSignClassObj.setStartSignature("");
		digSignClassObj.setStartCertificate("");
		digSignClassObj.setSignerVersion("");

		List<DigSignSCD> digSignList = new LinkedList<DigSignSCD>();
		digSignList.add(digSignClassObj);
		// ----------------
		MasterSCD mster = new MasterSCD();
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		mster.setVoyageDtls(voyageDtlsList);
		mster.setVesselDtls(vesselDtlsList);
		mster.setAuthPrsn(authPrsnList);
		mster.setDecRef(decRefList);
	    mster.setPrsnOnBoard(prsnOnBoardList);
		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
		mster.setShipStores(shipStoresList);
		
		// ----------------------------------
		HeaderFieldSCD headerFieldClassObj = new HeaderFieldSCD();

		headerFieldClassObj.setSenderID(senderId);
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		// "Default value: IECHE01"
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber(serialNumber);// old screen String sId (
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		// "Default value: ES"
		headerFieldClassObj.setReportingEvent("ES");
		// -------------------------------------------------

		List<MasterSCD> masterList = new LinkedList<MasterSCD>();
		masterList.add(mster);

		List<HeaderFieldSCD> headerFieldList = new LinkedList<HeaderFieldSCD>();
		headerFieldList.add(headerFieldClassObj);

		JsonMainObjctSCD org = new JsonMainObjctSCD();
		// org.setHeaderField(headerFieldList);
		org.setHeaderField(headerFieldList);
		 org.setDigSign(digSignList);
		org.setMaster(masterList);
		return org;
	
		
	}

	public static JsonMainObjctSCA getSCA(List<ImportGeneralManifestMod> blList) {

		ImportGeneralManifestMod objForm = blList.get(0);

		List<NotifyParty> notifyPartyDetailes = objForm.getNotifyParty();
		List<Consignee> consigneeDtls = objForm.getConsignee();
		List<MarksNumber> marksNumberDtls = objForm.getMarksNumber();
		List<Consigner> consignerDtls = objForm.getConsigner();
		List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SCA";
		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSCA> itemDtls = new LinkedList<ItemDtlsSCA>();
		List<TrnsprtEqmtSCA> trnsprtEqmt = new LinkedList<TrnsprtEqmtSCA>();
		List<LocCstmSCA> locCstm = new LinkedList<LocCstmSCA>();
		List<MCRefSCA> mCRef = new LinkedList<MCRefSCA>();
		List<TrnsprtDocMsrSCA> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSCA>();
		List<ShipItnrySCA> shipItnry = new LinkedList<ShipItnrySCA>();
		List<ItnrySCA> itnry = new LinkedList<ItnrySCA>();
		List<TrnsprtDocSCA> trnsprtDoc = new LinkedList<TrnsprtDocSCA>();
		List<PrevRefSCA> prevRef = new LinkedList<PrevRefSCA>();
		List<SupRefSCA> supRef = new LinkedList<SupRefSCA>();
		List<TrnshprSCA> trnshpr = new LinkedList<TrnshprSCA>();
		List<HouseCargoDecSCA> houseCargoDec = new LinkedList<HouseCargoDecSCA>();
		List<MCSuprtDocsSCA> mcSuprtDoc = new LinkedList<MCSuprtDocsSCA>();
		List<MCAdtnlDecSCA> mcAdtnlDec= new LinkedList<MCAdtnlDecSCA>();
		List<HCAdtnlDecSCA> hcAdtnlDec= new LinkedList<HCAdtnlDecSCA>();
		List<HCCrgoSuprtDocsSCA> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSCA>();
		
		// ===================

		for (ImportGeneralManifestMod blObj : blList) {
			HouseCargoDecSCA houseCargoDecSCAObj = new HouseCargoDecSCA();
			ItnrySCA itnryClassObj = new ItnrySCA();
			itnryClassObj.setPrtOfCallSeqNmbr(settingLength(blObj.getPort_of_call_sequence_number(),5));
		    // itnryClassObj.setModeOfTrnsprt(blObj.getCargoMovmnt());
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPort_of_call_cod(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),1));// Line
			itnry.add(itnryClassObj);
			houseCargoDecSCAObj.setItnry(itnry);
			// --------------------------------------------------------
			
			List<HCRefSCA> hCRef = new LinkedList<HCRefSCA>();

			HCRefSCA hCRefObj = new HCRefSCA();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));
			hCRefObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); 
			hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),4));
			hCRef.add(hCRefObj);
			houseCargoDecSCAObj.sethCRef(hCRef);
		//======================================================================================	

			
			// ----------------------------------------
			MCRefSCA mCRefClassObj = new MCRefSCA();
			mCRefClassObj.setLineNo(blObj.getItemNumber()); // Line 60
			mCRefClassObj.setMstrBlNo(settingLength(blObj.getBl(),20));; // Line 53
			mCRefClassObj.setMstrBlDt(blObj.getBlDate()); // Line 53
			mCRefClassObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));// Line 76
			mCRefClassObj.setPrevDec(blObj.getPrevious_declaration()); // Line77
			mCRefClassObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); // Line 78

			mCRef.add(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
			PrevRefSCA prevRefObj = new PrevRefSCA();
			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			prevRefObj.setMcinPcin(settingLength(blObj.getMcin(),20));
//			prevRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment 
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			
			prevRef.add(prevRefObj);
			houseCargoDecSCAObj.setPrevRef(prevRef);
      // ---------------------------------------- Writing a new nitun
			SupRefSCA supRefObj = new SupRefSCA();
			supRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			supRefObj.setMcinPcin(settingLength(blObj.getMcin(),20));
//			supRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			supRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			supRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			supRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			supRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			supRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			supRefObj.setAmendment("");
			supRef.add(supRefObj);
			houseCargoDecSCAObj.setSupRef(supRef);
		
       // ----------------------------
			LocCstmSCA locCstmClassObj = new LocCstmSCA();

			locCstmClassObj.setFirstPrtOfEntry(settingLength(blObj.getFirst_port_of_entry_last_port_of_departure(),6));
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength("",6));  // New added
			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2));  // Line 90
			locCstmClassObj.setItemTyp(settingLength(blObj.getItemType(),2)); // Line 61
			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4)); // Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength(blObj.getCargoNature(),4)); // Line 59
			locCstm.add(locCstmClassObj);
			houseCargoDecSCAObj.setLocCstm(locCstm);
		
			// ------------------------------------------
			TrnshprSCA TrnshprObj = new TrnshprSCA(); // New added
			TrnshprObj.setTrnsprtDoc("");
			TrnshprObj.setTrnshprBond(settingLength("",10));
			trnshpr.add(TrnshprObj);
			houseCargoDecSCAObj.setTrnshpr(trnshpr);
			
			// ---------------------------------------------------------
			TrnsprtDocMsrSCA trnsprtDocMsrClassObj = new TrnsprtDocMsrSCA();
			trnsprtDocMsrClassObj.setNmbrOfPkgs(settingLength(blObj.getNumber_of_packages(),8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrossCargoWeightBLlevel(),12,3));
			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength(blObj.getUnit_of_weight(),3));
			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); // not cleared by Guru
			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3));  // not cleared by Guru
			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			trnsprtDocMsrClassObj.setGrossVolume(settingLengthForDouble(blObj.getVolume(),12,3));
			trnsprtDocMsrClassObj.setUnitOfVolume(settingLength(blObj.getUnit_of_volume(),3));
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSCAObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ------------------------------------------------------
			ItemDtlsSCA itemDtlsClassObj = new ItemDtlsSCA();
			// trnsprtEqmtClassObj.setHSCA((String)blObj.get(" ")); not cleared by guru
			itemDtlsClassObj.setCrgoItemSeqNmbr( settingLength(blObj.getCargo_item_sequence_no(),5));
			itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
			itemDtlsClassObj.setUnoCd(settingLength(blObj.getUno_code(),5));
			itemDtlsClassObj.setImdgCd(settingLength(blObj.getImdg_code(),3));
			itemDtlsClassObj.setNmbrOfPkgs( settingLength("",8));
			itemDtlsClassObj.setTypOfPkgs(settingLength("",3));
			itemDtls.add(itemDtlsClassObj);
			houseCargoDecSCAObj.setItemDtls(itemDtls);
			// ------------------------------------------------------
			TrnsprtDocSCA trnsprtDocClassObj = new TrnsprtDocSCA();

			trnsprtDocClassObj.setPrtOfAcptName(settingLength(blObj.getPort_of_acceptance_name(),256));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getPort_of_receipt_name(),256));
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));
			trnsprtDocClassObj.setPrtOfReceiptCdd(settingLength(blObj.getPort_of_receipt(),10)); 
			trnsprtDocClassObj.setUcrTyp( settingLength(blObj.getUcr_type(),3));
			trnsprtDocClassObj.setUcrCd(settingLength(blObj.getUcr_code(),35));	

			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setNotfdPartyCity(settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName( settingLength(notyObj.getState(),35));
					// trnsprtDocClassObj.setNotfdPartyCntrySubDiv((String) notyObj.get(""));
					trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyPstcd(settingLength(notyObj.getZip(),9));
					trnsprtDocClassObj.setTypOfNotfdPartyCd( settingLength(notyObj.getCostumerCode(),3));
				}
			}
			trnsprtDocClassObj.setPanOfNotfdParty( settingLength(blObj.getPan_of_notified_party(),17));
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl(settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			houseCargoDecSCAObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ---------------------------------------------------

			// for (Object ctnerDtls: containeerDtls) {
			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnesName( settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCity( settingLength(cnsneeDtl.getCity(),70));
					trnsprtDocClassObj.setCnsgneCntrySubDivName( settingLength(cnsneeDtl.getState(),35));
					// trnsprtDocClassObj.setCnsgneCntrySubDiv((String) cnsneeDtl.get(""));
					trnsprtDocClassObj.setCnsgneCntryCd( settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnePstcd( settingLength(cnsneeDtl.getZip(),9));
					trnsprtDocClassObj.setCnsgnesCd( settingLength(cnsneeDtl.getCustomerCode(),17));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));;
					trnsprtDocClassObj.setCnsgnrsName(settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrCity(settingLength(cnsnerDtls.getCity(),70));
					trnsprtDocClassObj.setCnsgnrCntrySubDivName( settingLength(cnsnerDtls.getState(),35));
					trnsprtDocClassObj.setCnsgnrCdTyp( settingLength(cnsnerDtls.getCustomerCode(),3));
					 trnsprtDocClassObj.setCnsgnrCntrySubDivCd(blObj.getGstStateCode());
					trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnrPstcd(settingLength(cnsnerDtls.getZip(),9));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
				}
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			houseCargoDecSCAObj.setTrnsprtDoc(trnsprtDoc);
			// ------------------------------------------------

			TrnsprtEqmtSCA trnsprtEqmtClassObj = new TrnsprtEqmtSCA();

			for (ContainerDetails ctnerDtl : containerDtls) {
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo())) {

					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(ctnerDtl.getContainerAgentCode(),5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3));// alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getContainerSize(),4)); // optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getEquipment_seal_type(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256));
					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256));
					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
					trnsprtEqmtClassObj.setEventDt("");
					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10));
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(ctnerDtl.getContainerAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
				}
			} // add to trnsprtDocMsr List
			trnsprtEqmt.add(trnsprtEqmtClassObj);
			houseCargoDecSCAObj.setTrnsprtEqmt(trnsprtEqmt);
			//============================================================
			HCCrgoSuprtDocsSCA crgoSuprtDocs = new HCCrgoSuprtDocsSCA(); 
			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			crgoSuprtDocs.setDocRefNmbr(settingLength("",17));
			crgoSuprtDocs.setDocTypCd(settingLength("",6));
			crgoSuprtDocs.setIcegateUserid(settingLength("",15));
			crgoSuprtDocs.setIrnNmbr(settingLength("",14));
			crgoSuprtDocs.setRefSerialNo(settingLength("",5));
			crgoSuprtDocs.setSubSerialNoRef("");
			crgoSuprtDocs.setTagRef(settingLength("",5));
			crgoSuprtDoc.add(crgoSuprtDocs);
			houseCargoDecSCAObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
			//==========================================================
			HCAdtnlDecSCA adtnlDec = new HCAdtnlDecSCA();
			adtnlDec.setTagRef(settingLength("",5));
			adtnlDec.setRefSerialNo(settingLength("",5));
			adtnlDec.setInfoCd(settingLength("",35));
			adtnlDec.setInfoDt("");
			adtnlDec.setInfoMsr(settingLength("",5));
			adtnlDec.setInfoQualifier(settingLength("",10));
			adtnlDec.setInfoText(settingLength("",100));
			adtnlDec.setInfoTyp(settingLength("",10));
			
			hcAdtnlDec.add(adtnlDec);
			houseCargoDecSCAObj.setHcAdtnlDec(hcAdtnlDec);
			//----------------------------------------------------------------
			MCSuprtDocsSCA mcSuprtDocs = new MCSuprtDocsSCA (); 
			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));
			mcSuprtDocs.setDocRefNmbr(settingLength("",17));
			mcSuprtDocs.setDocTypCd(settingLength("",6));
			mcSuprtDocs.setIcegateUserid(settingLength("",15));
			mcSuprtDocs.setIrnNmbr(settingLength("",14));
			mcSuprtDocs.setRefSerialNo(settingLength("",5));
			mcSuprtDocs.setSubSerialNoRef("");
			mcSuprtDocs.setTagRef(settingLength("",5));
			mcSuprtDoc.add(mcSuprtDocs);
			//------------------------------------------------------------
			MCAdtnlDecSCA mcAdtnlDecs = new MCAdtnlDecSCA();
			mcAdtnlDecs.setTagRef(settingLength("",5));
			mcAdtnlDecs.setRefSerialNo(settingLength("",5));
			mcAdtnlDecs.setInfoCd(settingLength("",35));
			mcAdtnlDecs.setInfoDt("");
			mcAdtnlDecs.setInfoMsr(settingLength("",5));
			mcAdtnlDecs.setInfoText(settingLength("",100));
			mcAdtnlDecs.setInfoTyp(settingLength("",10));
			
			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
			ShipItnrySCA shipItny3 = new ShipItnrySCA();
			String prtOfCallCdd = null;
			String itnrySeq = null;
			// all value set
			if (objForm.getNextport1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-3";
				prtOfCallCdd = objForm.getLastPort1();
			}
			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport2() == null || objForm.getNextport2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySCA shipItnry2 = new ShipItnrySCA();
			shipItnry2.setShipItnrySeq(itnrySeq);
			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-1";

				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySCA shipItnry1 = new ShipItnrySCA();
			shipItnry1.setShipItnrySeq(itnrySeq);
			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			ShipItnrySCA shipItnry0 = new ShipItnrySCA();
			shipItnry0.setShipItnrySeq("0");
			shipItnry0.setPrtOfCallCdd(settingLength(objForm.getPod(),10)); // blObj.get("Port of call sequence numbe"));
			shipItnry0.setPrtOfCallName(  settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "1";
				prtOfCallCdd = objForm.getLastPort1();
			}
			ShipItnrySCA shipItnry11 = new ShipItnrySCA();
			shipItnry11.setShipItnrySeq(itnrySeq);
			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getLastPort2() == null || objForm.getLastPort2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySCA shipItnry22 = new ShipItnrySCA();
			shipItnry22.setShipItnrySeq(itnrySeq);
			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry22.setPrtOfCallName( settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "3";
				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySCA shipItnry33 = new ShipItnrySCA();
			shipItnry33.setShipItnrySeq(itnrySeq);
			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry33.setPrtOfCallName( settingLength(blObj.getPort_of_call_sequence_number(),256));
			shipItnry.add(shipItny3);
			shipItnry.add(shipItnry2);
			shipItnry.add(shipItnry1);
			shipItnry.add(shipItnry0);
			shipItnry.add(shipItnry11);
			shipItnry.add(shipItnry22);
			shipItnry.add(shipItnry33);
			// ------------------------------------------------------
			houseCargoDec.add(houseCargoDecSCAObj);
		}
		
		// now add all List to relevant class

		MastrCnsgmtDecSCA mastrCnsgmtDec = new MastrCnsgmtDecSCA();
		mastrCnsgmtDec.setItemDtls(itemDtls);
		mastrCnsgmtDec.setItnry(itnry);
		mastrCnsgmtDec.setLocCstm(locCstm);
		mastrCnsgmtDec.setmCRef(mCRef);
		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
		mastrCnsgmtDec.setPrevRef(prevRef);
		mastrCnsgmtDec.setSupRef(supRef);
		mastrCnsgmtDec.setTrnshpr(trnshpr);
		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
		mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
		List<MastrCnsgmtDecSCA> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSCA>();
		mastrCnsgmtDecList.add(mastrCnsgmtDec);
		

		VoyageDtlsSCA voyageDtlsClassObj = new VoyageDtlsSCA();
		voyageDtlsClassObj.setVoyageNo(voyage); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(objForm.getConveyance_reference_no()); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted(objForm.getCargoDeclaration()); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(""); // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
		voyageDtlsClassObj.setShipItnry(shipItnry);

		List<VoyageDtlsSCA> voyageDtlsList = new LinkedList<VoyageDtlsSCA>();
		voyageDtlsList.add(voyageDtlsClassObj);
		// ---------------------------------------------------
		VesselDtlsSCA vesselDtls = new VesselDtlsSCA();

		vesselDtls.setModeOfTrnsprt(settingLength(objForm.getMode_of_transport(),1)); // Line 191
		vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25));// not found
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
		vesselDtls.setPurposeOfCall("1"); // always hard coded
		List<VesselDtlsSCA> vesselDtlsList = new LinkedList<VesselDtlsSCA>();
		vesselDtlsList.add(vesselDtls);
		// ----------------------------
		AuthPrsnSCA authPrsClassObj = new AuthPrsnSCA();
		authPrsClassObj.setSbmtrTyp(settingLength(objForm.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(objForm.getSubmitter_code(),15));//
		authPrsClassObj.setAuthReprsntvCd(settingLength(objForm.getAuthoriz_rep_code(),10)); //
		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(),10));// LinNo:-211
		authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
		authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(),10)); // LinNo:-190
		authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(),10)); // LinNo:-132

		List<AuthPrsnSCA> authPrsnList = new LinkedList<AuthPrsnSCA>();
		authPrsnList.add(authPrsClassObj);
		// ----------------------------
		DecRefSCA decRefClaObj = new DecRefSCA();

		// decRefClaObj.setMsgTyp(objForm.getMesstype());
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(objForm.getPod(),10)); // value from old screen [Pod]
		// decRefClaObj.setJobNo(objForm.getJobNum()); // sid will give this Number
		decRefClaObj.setJobNo(settingLength(jobNum,7));
		// decRefClaObj.setJobDt(objForm.getJobDate()); //sid told me to keep crunt date
		decRefClaObj.setJobDt(currDate);
		// decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength(objForm.getManifest_no_csn_no(),7)); //
		decRefClaObj.setMnfstDtRotnDt(objForm.getManifest_date_csn_date()); //
		decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
		// #
		// * decRefClaObj.setDptrMnfstNo(); //
		// *decRefClaObj.setDptrMnfstDt(""); //
		// #
		List<DecRefSCA> decRefList = new LinkedList<DecRefSCA>();
		decRefList.add(decRefClaObj);
		// ----------------------------
		PrsnDtlsSCA prsDtls = new PrsnDtlsSCA();
		prsDtls.setPrsnTypCdd(settingLength("",3));
		prsDtls.setPrsnFamilyName(settingLength("",70));
		prsDtls.setPrsnGivenName(settingLength("",70));
		prsDtls.setPrsnNatnltyCdd(settingLength("",2));
		prsDtls.setPsngrInTransitIndctr(settingLength("",1));
		prsDtls.setCrewmemberRankOrRatingCdd("");
		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
		prsDtls.setPrsnGenderCdd(settingLength("",3));
		prsDtls.setPrsnDtOfBirth("");
		prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
		prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
		// -----------------------------------------

		PrsnIdSCA prsnIdclassObj = new PrsnIdSCA();
		prsnIdclassObj.setPrsnIdDocExpiryDt("");
		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
		List<PrsnIdSCA> prsnIdList = new LinkedList<PrsnIdSCA>();
		prsnIdList.add(prsnIdclassObj);

		// ---------------
		List<PrsnDtlsSCA> prsnDtlsList = new LinkedList<PrsnDtlsSCA>();
		prsnDtlsList.add(prsDtls);
		PrsnOnBoardSCA prsnOnBoard = new PrsnOnBoardSCA();
		prsnOnBoard.setPrsnDtls(prsnDtlsList);
		prsnOnBoard.setPrsnId(prsnIdList);
		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));

		List<PrsnOnBoardSCA> prsnOnBoardList = new LinkedList<PrsnOnBoardSCA>();
		prsnOnBoardList.add(prsnOnBoard);
		// --------------------------------------------------------
		ShipStoresSCA shipStores = new ShipStoresSCA();
		shipStores.setSeqNmbr(settingLength("",5));
		shipStores.setArticleNameCdd(settingLength("",18));
		shipStores.setArticleNameText(settingLength("",512));
		shipStores.setLocOnbrdText(settingLength("",256));
		shipStores.setQntyCdOnbrd(settingLength("",3));
		shipStores.setQntyOnbrd(settingLengthForDouble("",16,6));
		List<ShipStoresSCA> shipStoresList = new LinkedList<ShipStoresSCA>();
		shipStoresList.add(shipStores);
		//----------------------------------------------------------------
		
		int i = 0;
		List<VoyageTransportEquipmentSCA> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSCA>();
		for (ContainerDetails cntnerDtl : containerDtls) {

			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

			VoyageTransportEquipmentSCA voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSCA();
			voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
			voyageTransportEquipmentClassObj.setQuipmentType("CN");
			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(cntnerDtl.getEquipmentLoadStatus());
			voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
		}
		
		// ------------------------------------------------------------
		DigSignSCA digSignClassObj = new DigSignSCA();
		digSignClassObj.setStartSignature("");
		digSignClassObj.setStartCertificate("");
		digSignClassObj.setSignerVersion("");

		List<DigSignSCA> digSignList = new LinkedList<DigSignSCA>();
		digSignList.add(digSignClassObj);
		// ----------------
		MasterSCA mster = new MasterSCA();
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		mster.setVoyageDtls(voyageDtlsList);
		mster.setVesselDtls(vesselDtlsList);
		mster.setAuthPrsn(authPrsnList);
		mster.setDecRef(decRefList);
	    mster.setPrsnOnBoard(prsnOnBoardList);
		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
		mster.setShipStores(shipStoresList);
		
		// ----------------------------------
		HeaderFieldSCA headerFieldClassObj = new HeaderFieldSCA();

		headerFieldClassObj.setSenderID(senderId);
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		// "Default value: IECHE01"
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber(serialNumber);// old screen String sId (
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		// "Default value: ES"
		headerFieldClassObj.setReportingEvent("ES");
		// -------------------------------------------------

		List<MasterSCA> masterList = new LinkedList<MasterSCA>();
		masterList.add(mster);

		List<HeaderFieldSCA> headerFieldList = new LinkedList<HeaderFieldSCA>();
		headerFieldList.add(headerFieldClassObj);

		JsonMainObjctSCA org = new JsonMainObjctSCA();
		// org.setHeaderField(headerFieldList);
		org.setHeaderField(headerFieldList);
		 org.setDigSign(digSignList);
		org.setMaster(masterList);
		return org;
	
	}

	public static JsonMainObjctSCU getSCU(List<ImportGeneralManifestMod> blList) {

ImportGeneralManifestMod objForm = blList.get(0);


		List<NotifyParty> notifyPartyDetailes = objForm.getNotifyParty();
		List<Consignee> consigneeDtls = objForm.getConsignee();
		List<MarksNumber> marksNumberDtls = objForm.getMarksNumber();
		List<Consigner> consignerDtls = objForm.getConsigner();
		List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SCU";
		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		List<ItemDtlsSCU> itemDtls = new LinkedList<ItemDtlsSCU>();
		List<TrnsprtEqmtSCU> trnsprtEqmt = new LinkedList<TrnsprtEqmtSCU>();
		List<LocCstmSCU> locCstm = new LinkedList<LocCstmSCU>();
		List<MCRefSCU> mCRef = new LinkedList<MCRefSCU>();
		List<TrnsprtDocMsrSCU> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSCU>();
		List<ShipItnrySCU> shipItnry = new LinkedList<ShipItnrySCU>();
		List<ItnrySCU> itnry = new LinkedList<ItnrySCU>();
		List<TrnsprtDocSCU> trnsprtDoc = new LinkedList<TrnsprtDocSCU>();
		List<PrevRefSCU> prevRef = new LinkedList<PrevRefSCU>();
		List<SupRefSCU> supRef = new LinkedList<SupRefSCU>();
		List<TrnshprSCU> trnshpr = new LinkedList<TrnshprSCU>();
		List<MCSuprtDocsSCU> mcSuprtDoc = new LinkedList<MCSuprtDocsSCU>();
		List<MCAdtnlDecSCU> mcAdtnlDec= new LinkedList<MCAdtnlDecSCU>();


		for (ImportGeneralManifestMod blObj : blList) {
			
			ItnrySCU itnryClassObj = new ItnrySCU();
			itnryClassObj.setPrtOfCallSeqNmbr(settingLength(blObj.getPort_of_call_sequence_number(),5));
		    // itnryClassObj.setModeOfTrnsprt(blObj.getCargoMovmnt());
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getPort_of_call_cod(),10));
			itnryClassObj.setModeOfTrnsprt(settingLength(blObj.getMode_of_transport(),1));
			itnry.add(itnryClassObj);
	
		//======================================================================================	
			MCRefSCU mCRefClassObj = new MCRefSCU();
			mCRefClassObj.setLineNo(blObj.getItemNumber()); // Line 60
			mCRefClassObj.setMstrBlNo(blObj.getBl()); // Line 53
			mCRefClassObj.setMstrBlDt(blObj.getBlDate()); // Line 53
			mCRefClassObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4)); // Line 76
			mCRefClassObj.setPrevDec(blObj.getPrevious_declaration()); // Line77
			mCRefClassObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16)); // Line 78
			mCRef.add(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
			PrevRefSCU prevRefObj = new PrevRefSCU();
			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			prevRefObj.setMcinPcin(settingLength(blObj.getMcin(),20));
//			prevRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy(  settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
		
			prevRef.add(prevRefObj);
			
      // ---------------------------------------- Writing a new nitun
			SupRefSCU supRefObj = new SupRefSCU();
			supRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
			supRefObj.setMcinPcin(settingLength(blObj.getMcin(),20));
//			supRefObj.setCsnDt(blObj.getCsn_date()); guru said to comment
//			supRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); guru said to comment
//			supRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			supRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20));  guru said to comment
//			supRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4));  guru said to comment
//			supRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			supRef.add(supRefObj);
			
		
       // ----------------------------
			LocCstmSCU locCstmClassObj = new LocCstmSCU();

			locCstmClassObj.setFirstPrtOfEntry((String)  settingLength(blObj.getFirst_port_of_entry_last_port_of_departure(),6));
			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			locCstmClassObj.setNxtPrtOfUnlading(settingLength("",6)); // New added
			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2)); // Line 90
			locCstmClassObj.setItemTyp(settingLength(blObj.getItemType(),2)); // Line 61
			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4)); // Line 57
			locCstmClassObj.setNatrOfCrgo(settingLength(blObj.getCargoNature(),4));  // Line 59
			locCstm.add(locCstmClassObj);
			
			// ------------------------------------------
			TrnshprSCU TrnshprObj = new TrnshprSCU(); // New added
			TrnshprObj.setTrnsprtDoc("");
			TrnshprObj.setTrnshprBond(settingLength("",10));
			trnshpr.add(TrnshprObj);
			
			// ---------------------------------------------------------
			TrnsprtDocMsrSCU trnsprtDocMsrClassObj = new TrnsprtDocMsrSCU();
			trnsprtDocMsrClassObj.setNmbrOfPkgs( settingLength(blObj.getNumber_of_packages(),8));
			trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			trnsprtDocMsrClassObj.setGrossWeight(settingLength(blObj.getGrossCargoWeightBLlevel(),21));
			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength(blObj.getUnit_of_weight(),3));
			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2));// not cleared by Guru
			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3));  // not cleared by Guru
			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			if("".equals(blObj.getVolume()) || blObj.getVolume().isEmpty()) {
			trnsprtDocMsrClassObj.setGrossVolume(settingLengthForDouble(blObj.getVolume(),12,3));
			}
			trnsprtDocMsrClassObj.setUnitOfVolume(settingLength(blObj.getUnit_of_volume(),3));
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			
			// ------------------------------------------------------
			ItemDtlsSCU itemDtlsClassObj = new ItemDtlsSCU();
			// trnsprtEqmtClassObj.setHSCU((String)blObj.get(" ")); not cleared by guru
			itemDtlsClassObj.setCrgoItemSeqNmbr((String) settingLength(blObj.getCargo_item_sequence_no(),5));
			itemDtlsClassObj.setCrgoItemDesc( settingLength(blObj.getCargo_item_description(),256));
			itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
			itemDtlsClassObj.setImdgCd(settingLength(blObj.getImdg_code(),3));
			itemDtlsClassObj.setNmbrOfPkgs( settingLength("",8));
			itemDtlsClassObj.setTypOfPkgs(settingLength("",3));
			itemDtls.add(itemDtlsClassObj);
		
			// ------------------------------------------------------
			TrnsprtDocSCU trnsprtDocClassObj = new TrnsprtDocSCU();

			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getPort_of_acceptance_name(),256));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getPort_of_receipt_name(),256));
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));
			trnsprtDocClassObj.setPrtOfReceiptCdd( settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setUcrTyp(settingLength(blObj.getUcr_type(),3));
			trnsprtDocClassObj.setUcrCd(settingLength(blObj.getUcr_code(),35));	

			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					String add = (String) notyObj.getAddressLine1() + notyObj.getAddressLine2()
							+ (String) notyObj.getAddressLine3() + (String) notyObj.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setNotfdPartyCity( settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName( settingLength(notyObj.getState(),35));
					// trnsprtDocClassObj.setNotfdPartyCntrySubDiv((String) notyObj.get(""));
					
					trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyPstcd( settingLength(notyObj.getZip(),9));
					trnsprtDocClassObj.setTypOfNotfdPartyCd( settingLength(notyObj.getCostumerCode(),3));
				}
			}
			trnsprtDocClassObj.setPanOfNotfdParty( settingLength(blObj.getPan_of_notified_party(),17));
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl(settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			// ---------------------------------------------------

			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnesName(  settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCity( settingLength(cnsneeDtl.getCity(),70));
					trnsprtDocClassObj.setCnsgneCntrySubDivName(cnsneeDtl.getState());
					// trnsprtDocClassObj.setCnsgneCntrySubDiv((String) cnsneeDtl.get(""));
					trnsprtDocClassObj.setCnsgneCntryCd( settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnePstcd(settingLength(cnsneeDtl.getZip(),9));
					trnsprtDocClassObj.setCnsgnesCd( settingLength(cnsneeDtl.getCustomerCode(),17));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsneeDtl.getCustomerName(),70));
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add = (String) cnsnerDtls.getAddressLine1() + cnsnerDtls.getAddressLine2()
							+ (String) cnsnerDtls.getAddressLine3() + (String) cnsnerDtls.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnrsName( settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrCity( settingLength(cnsnerDtls.getCity(),70));
					trnsprtDocClassObj.setCnsgnrCntrySubDivName(settingLength(cnsnerDtls.getState(),35));
					trnsprtDocClassObj.setCnsgnrCdTyp( settingLength(cnsnerDtls.getCustomerCode(),3));
					// trnsprtDocClassObj.setCnsgnrCntrySubDivCd((String) cnsnerDtls.get(""));
					trnsprtDocClassObj.setCnsgnrCntryCd(  settingLength(cnsnerDtls.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnrPstcd( settingLength(cnsnerDtls.getZip(),9));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
				}
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			// ------------------------------------------------

			TrnsprtEqmtSCU trnsprtEqmtClassObj = new TrnsprtEqmtSCU();

			for (ContainerDetails ctnerDtl : containerDtls) {
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo())) {

					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(ctnerDtl.getContainerAgentCode(),5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3));// alway CN hard codded customerCodecontainer
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getContainerSize(),4));// optonal
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256));
					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256));
					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
					trnsprtEqmtClassObj.setEventDt("");
					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10));
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(ctnerDtl.getContainerAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength( ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
				}
			} // add to trnsprtDocMsr List
			trnsprtEqmt.add(trnsprtEqmtClassObj);
		
			//============================================================
			//------------------------------------------------------------
			MCAdtnlDecSCU mcAdtnlDecs = new MCAdtnlDecSCU();
			mcAdtnlDecs.setTagRef(settingLength("",5));
			mcAdtnlDecs.setRefSerialNo(settingLength("",5));

			mcAdtnlDecs.setInfoCd(settingLength("",35));
			mcAdtnlDecs.setInfoDt("");
			mcAdtnlDecs.setInfoMsr(settingLength("",5));
			mcAdtnlDecs.setInfoQualifier(settingLength("",10));
			mcAdtnlDecs.setInfoText(settingLength("",100));
			mcAdtnlDecs.setInfoTyp(settingLength("",10));
			
			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
			ShipItnrySCU shipItny3 = new ShipItnrySCU();
			String prtOfCallCdd = null;
			String itnrySeq = null;
			// all value set
			if (objForm.getNextport1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-3";
				prtOfCallCdd = objForm.getLastPort1();
				
			}
			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport2() == null || objForm.getNextport2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySCU shipItnry2 = new ShipItnrySCU();
			shipItnry2.setShipItnrySeq(itnrySeq);
			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getNextport3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "-1";

				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySCU shipItnry1 = new ShipItnrySCU();
			shipItnry1.setShipItnrySeq(itnrySeq);
			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			ShipItnrySCU shipItnry0 = new ShipItnrySCU();
			shipItnry0.setShipItnrySeq("0");
			shipItnry0.setPrtOfCallCdd(settingLength(objForm.getPod(),10)); // blObj.get("Port of call sequence numbe"));
			shipItnry0.setPrtOfCallName(settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort1() == null || objForm.getLastPort1() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "1";
				prtOfCallCdd = objForm.getLastPort1();
			}
			ShipItnrySCU shipItnry11 = new ShipItnrySCU();
			shipItnry11.setShipItnrySeq(itnrySeq);
			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));

			if (objForm.getLastPort2() == null || objForm.getLastPort2() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "2";
				prtOfCallCdd = objForm.getLastPort2();
			}
			ShipItnrySCU shipItnry22 = new ShipItnrySCU();
			shipItnry22.setShipItnrySeq(itnrySeq);
			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry22.setPrtOfCallName( settingLength(blObj.getPort_of_call_coded(),256));

			if (objForm.getLastPort3() == null || objForm.getLastPort3() == "") {
				prtOfCallCdd = null;
				itnrySeq = null;
			} else {
				itnrySeq = "3";
				prtOfCallCdd = objForm.getLastPort3();
			}
			ShipItnrySCU shipItnry33 = new ShipItnrySCU();
			shipItnry33.setShipItnrySeq(itnrySeq);
			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
			shipItnry33.setPrtOfCallName( settingLength(blObj.getPort_of_call_sequence_number(),256));
			shipItnry.add(shipItny3);
			shipItnry.add(shipItnry2);
			shipItnry.add(shipItnry1);
			shipItnry.add(shipItnry0);
			shipItnry.add(shipItnry11);
			shipItnry.add(shipItnry22);
			shipItnry.add(shipItnry33);
			// ------------------------------------------------------
		}
		
		// now add all List to relevant class

		MastrCnsgmtDecSCU mastrCnsgmtDec = new MastrCnsgmtDecSCU();
		mastrCnsgmtDec.setItemDtls(itemDtls);
		mastrCnsgmtDec.setItnry(itnry);
		mastrCnsgmtDec.setLocCstm(locCstm);
		mastrCnsgmtDec.setmCRef(mCRef);
		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
		mastrCnsgmtDec.setPrevRef(prevRef);
		mastrCnsgmtDec.setSupRef(supRef);
		List<MastrCnsgmtDecSCU> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSCU>();
		mastrCnsgmtDecList.add(mastrCnsgmtDec);
		

		VoyageDtlsSCU voyageDtlsClassObj = new VoyageDtlsSCU();
		voyageDtlsClassObj.setVoyageNo(voyage); // Line10
		voyageDtlsClassObj.setCnvnceRefNmbr(objForm.getConveyance_reference_no()); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted(objForm.getCargoDeclaration()); // Line:-46
		voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
		voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
		voyageDtlsClassObj.setTotalNmbrOfLines(""); // Line38 (objForm.getTotalItem()); nitun
		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));
		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
		voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
		voyageDtlsClassObj.setShipItnry(shipItnry);

		List<VoyageDtlsSCU> voyageDtlsList = new LinkedList<VoyageDtlsSCU>();
		voyageDtlsList.add(voyageDtlsClassObj);
		// ---------------------------------------------------
		VesselDtlsSCU vesselDtls = new VesselDtlsSCU();

		vesselDtls.setModeOfTrnsprt(settingLength(objForm.getMode_of_transport(),1)); // Line 191
		vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25)); // not found
		vesselDtls.setTrnsprtMeansId(settingLength("",25));
		vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
		vesselDtls.setPurposeOfCall("1"); // always hard coded
		List<VesselDtlsSCU> vesselDtlsList = new LinkedList<VesselDtlsSCU>();
		vesselDtlsList.add(vesselDtls);
		// ----------------------------
		AuthPrsnSCU authPrsClassObj = new AuthPrsnSCU();
		authPrsClassObj.setSbmtrTyp(settingLength(objForm.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(objForm.getSubmitter_code(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength(objForm.getAuthoriz_rep_code(),10)); //
		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
		authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(),10)); // LinNo:-211
		authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
		authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(),10));// LinNo:-190
		authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(),10)); // LinNo:-132

		List<AuthPrsnSCU> authPrsnList = new LinkedList<AuthPrsnSCU>();
		authPrsnList.add(authPrsClassObj);
		// ----------------------------
		DecRefSCU decRefClaObj = new DecRefSCU();

		// decRefClaObj.setMsgTyp(objForm.getMesstype());
		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(objForm.getPod(),10)); // value from old screen [Pod]
		// decRefClaObj.setJobNo(objForm.getJobNum()); // sid will give this Number
		decRefClaObj.setJobNo(settingLength(jobNum,7));
		// decRefClaObj.setJobDt(objForm.getJobDate()); //sid told me to keep crunt date
		decRefClaObj.setJobDt(currDate);
		// decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
		decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
		decRefClaObj.setMnfstNoRotnNo(settingLength(objForm.getManifest_no_csn_no(),7)); //
		decRefClaObj.setMnfstDtRotnDt(objForm.getManifest_date_csn_date()); //
		decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
		// #
		// * decRefClaObj.setDptrMnfstNo(); //
		// *decRefClaObj.setDptrMnfstDt(""); //
		// #
		List<DecRefSCU> decRefList = new LinkedList<DecRefSCU>();
		decRefList.add(decRefClaObj);
		// ----------------------------
		PrsnDtlsSCU prsDtls = new PrsnDtlsSCU();
		prsDtls.setPrsnTypCdd(settingLength("",3));
		prsDtls.setPrsnFamilyName(settingLength("",70));
		prsDtls.setPrsnGivenName(settingLength("",70));
		prsDtls.setPrsnNatnltyCdd(settingLength("",2));
		prsDtls.setPsngrInTransitIndctr(settingLength("",1));
		prsDtls.setCrewmemberRankOrRatingCdd("");
		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
		prsDtls.setPrsnGenderCdd(settingLength("",3));
		prsDtls.setPrsnDtOfBirth("");
		prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
		prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
		// -----------------------------------------

		PrsnIdSCU prsnIdclassObj = new PrsnIdSCU();
		prsnIdclassObj.setPrsnIdDocExpiryDt("");
		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
		List<PrsnIdSCU> prsnIdList = new LinkedList<PrsnIdSCU>();
		prsnIdList.add(prsnIdclassObj);

		// ---------------
		List<PrsnDtlsSCU> prsnDtlsList = new LinkedList<PrsnDtlsSCU>();
		prsnDtlsList.add(prsDtls);
		PrsnOnBoardSCU prsnOnBoard = new PrsnOnBoardSCU();
		prsnOnBoard.setPrsnDtls(prsnDtlsList);
		prsnOnBoard.setPrsnId(prsnIdList);
		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));

		List<PrsnOnBoardSCU> prsnOnBoardList = new LinkedList<PrsnOnBoardSCU>();
		prsnOnBoardList.add(prsnOnBoard);
		
		//-------------------------------------------------------
		int i = 0;
		List<VoyageTransportEquipmentSCU> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSCU>();
		for (ContainerDetails cntnerDtl : containerDtls) {

			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

			VoyageTransportEquipmentSCU voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSCU();
			voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
			voyageTransportEquipmentClassObj.setQuipmentType("CN");
			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(cntnerDtl.getEquipmentLoadStatus());
			voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
		}
		
		// ------------------------------------------------------------
		DigSignSCU digSignClassObj = new DigSignSCU();
		digSignClassObj.setStartSignature("");
		digSignClassObj.setStartCertificate("");
		digSignClassObj.setSignerVersion("");

		List<DigSignSCU> digSignList = new LinkedList<DigSignSCU>();
		digSignList.add(digSignClassObj);
		// ----------------
		MasterSCU mster = new MasterSCU();
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		mster.setVoyageDtls(voyageDtlsList);
		mster.setVesselDtls(vesselDtlsList);
		mster.setAuthPrsn(authPrsnList);
		mster.setDecRef(decRefList);
	    mster.setPrsnOnBoard(prsnOnBoardList);
		mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
		
		// ----------------------------------
		HeaderFieldSCU headerFieldClassObj = new HeaderFieldSCU();

		headerFieldClassObj.setSenderID(senderId);
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(objForm.getPod());
		headerFieldClassObj.setVersionNo("1.0");
		headerFieldClassObj.setIndicator("T");
		// "Default value: IECHE01"
		headerFieldClassObj.setMessageID("IECHE01");
		headerFieldClassObj.setSequenceOrControlNumber(serialNumber);// old screen String sId (
		headerFieldClassObj.setDate(currDate);
		headerFieldClassObj.setTime("T" + getTimeHeader());
		// "Default value: ES"
		headerFieldClassObj.setReportingEvent("ES");
		// -------------------------------------------------

		List<MasterSCU> masterList = new LinkedList<MasterSCU>();
		masterList.add(mster);

		List<HeaderFieldSCU> headerFieldList = new LinkedList<HeaderFieldSCU>();
		headerFieldList.add(headerFieldClassObj);

		JsonMainObjctSCU org = new JsonMainObjctSCU();
		// org.setHeaderField(headerFieldList);
		org.setHeaderField(headerFieldList);
		 org.setDigSign(digSignList);
		org.setMaster(masterList);
		return org;
	}

	public static JsonMainObjctSCC getSCC(List<ImportGeneralManifestMod> blList) {

		ImportGeneralManifestMod objForm = blList.get(0);
		
		List<ContainerDetails> containerDtls = objForm.getContainerDetailes();
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";

		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SCC";

		String voyage = isNull((String) objForm.getVoyage());
		String newVoyage = isNull((String) objForm.getNewVoyage());
		String pol = isNull((String) objForm.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) objForm.getVessel());
		String newVessel = isNull((String) objForm.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}

		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
				List<ItemDtlsSCC> itemDtls = new LinkedList<ItemDtlsSCC>();
				List<TrnsprtEqmtSCC> trnsprtEqmt = new LinkedList<TrnsprtEqmtSCC>();
				List<LocCstmSCC> locCstm = new LinkedList<LocCstmSCC>();
				List<MCRefSCC> mCRef = new LinkedList<MCRefSCC>();
				
				for (ImportGeneralManifestMod blObj : blList) {

					MCRefSCC mCRefClassObj = new MCRefSCC();
					mCRefClassObj.setLineNo(blObj.getItemNumber()); // Line 60
					mCRefClassObj.setMstrBlNo(blObj.getBl()); // Line 53
					mCRefClassObj.setMstrBlDt(blObj.getBlDate()); // Line 53
					mCRefClassObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4)); // Line 76
					mCRefClassObj.setPrevDec(blObj.getPrevious_declaration()); // Line77
					mCRefClassObj.setConsolidatorPan(settingLength(blObj.getConsolidator_pan(),16));  // Line 78
					mCRef.add(mCRefClassObj);
				//====================================================================

					TrnsprtEqmtSCC trnsprtEqmtClassObj = new TrnsprtEqmtSCC();

					for (ContainerDetails ctnerDtl : containerDtls) {
						if ((blObj.getBl()).equals(ctnerDtl.getBlNo())) {

							trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(ctnerDtl.getContainerAgentCode(),5));
							trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
							trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3)); // alway CN hard codded customerCodecontainer
							trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getContainerSize(),4)); // optonal
							trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
							trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
							trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
							trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
							trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256));
							trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256));
							trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus());
							trnsprtEqmtClassObj.setEventDt("");
							trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10));
							trnsprtEqmtClassObj.setCntrAgntCd(settingLength(ctnerDtl.getContainerAgentCode(),17));
							trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
							trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
						}
					} // add to trnsprtDocMsr List
					trnsprtEqmt.add(trnsprtEqmtClassObj);
				}
					//============================================================

				MastrCnsgmtDecSCC mastrCnsgmtDec = new MastrCnsgmtDecSCC();
				mastrCnsgmtDec.setmCRef(mCRef);
				mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
				List<MastrCnsgmtDecSCC> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSCC>();
				mastrCnsgmtDecList.add(mastrCnsgmtDec);
				

				VoyageDtlsSCC voyageDtlsClassObj = new VoyageDtlsSCC();
				voyageDtlsClassObj.setVoyageNo(voyage); // Line10
				voyageDtlsClassObj.setCnvnceRefNmbr(objForm.getConveyance_reference_no()); // Line 193
				voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted(objForm.getCargoDeclaration()); // Line:-46
				voyageDtlsClassObj.setCrgoDescCdd(objForm.getCargoDeclaration()); // Line:-195
				voyageDtlsClassObj.setBriefCrgoDesc(objForm.getBrief_cargo_des()); // Line:-195
				voyageDtlsClassObj.setTotalNmbrOfLines(""); // Line38 (objForm.getTotalItem()); nitun
				voyageDtlsClassObj.setExptdDtAndTimeOfArvl(objForm.getArrivalDate() + "T" + getTime(objForm.getArrivalTime()));
				// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
				// getTime(objForm.getArrivalTime()));
				voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); // NotFound
				voyageDtlsClassObj.setNmbrOfCrewMnfsted(objForm.getCrewListDeclaration());
			
				List<VoyageDtlsSCC> voyageDtlsList = new LinkedList<VoyageDtlsSCC>();
				voyageDtlsList.add(voyageDtlsClassObj);
				// ---------------------------------------------------
				VesselDtlsSCC vesselDtls = new VesselDtlsSCC();

				vesselDtls.setModeOfTrnsprt(settingLength(objForm.getMode_of_transport(),1)); // Line 191
				vesselDtls.setTypOfTrnsprtMeans(settingLength(objForm.getImoCode(),25)); // not found
				vesselDtls.setTrnsprtMeansId(settingLength("",25));
				vesselDtls.setShipTyp(objForm.getShip_type()); // Line 192
				vesselDtls.setPurposeOfCall("1"); // always hard coded
				List<VesselDtlsSCC> vesselDtlsList = new LinkedList<VesselDtlsSCC>();
				vesselDtlsList.add(vesselDtls);
				// ----------------------------
				AuthPrsnSCC authPrsClassObj = new AuthPrsnSCC();
				authPrsClassObj.setSbmtrTyp(settingLength(objForm.getSubmitter_type(),4)); //
				authPrsClassObj.setSbmtrCd(settingLength(objForm.getSubmitter_code(),15));//
				authPrsClassObj.setAuthReprsntvCd(settingLength(objForm.getAuthoriz_rep_code(),10)); //
				authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL
				authPrsClassObj.setAuthSeaCarrierCd(settingLength(objForm.getAuthorized_sea_carrier_code(),10)); // LinNo:-211
				authPrsClassObj.setMasterName(settingLength(objForm.getMasterName(),30));// 21
				authPrsClassObj.setShpngLineBondNmbr(settingLength(objForm.getShipping_line_bond_no_r(),10));// LinNo:-190
				authPrsClassObj.setTrmnlOprtrCd(settingLength(objForm.getCustomTerminalCode(),10)); // LinNo:-132

				List<AuthPrsnSCC> authPrsnList = new LinkedList<AuthPrsnSCC>();
				authPrsnList.add(authPrsClassObj);
				// ----------------------------
				DecRefSCC decRefClaObj = new DecRefSCC();

				// decRefClaObj.setMsgTyp(objForm.getMesstype());
				decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
				decRefClaObj.setPrtofRptng(settingLength(objForm.getPod(),10)); // value from old screen [Pod]
				// decRefClaObj.setJobNo(objForm.getJobNum()); // sid will give this Number
				decRefClaObj.setJobNo(settingLength(jobNum,7));
				// decRefClaObj.setJobDt(objForm.getJobDate()); //sid told me to keep crunt date
				decRefClaObj.setJobDt(currDate);
				// decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
				decRefClaObj.setRptngEvent(settingLength(rpngEvent,4));
				decRefClaObj.setMnfstNoRotnNo(settingLength(objForm.getManifest_no_csn_no(),7)); //
				decRefClaObj.setMnfstDtRotnDt(objForm.getManifest_date_csn_date()); //
				decRefClaObj.setVesselTypMvmt(settingLength(objForm.getVessel_type_movement(),2)); //
				// #
				// * decRefClaObj.setDptrMnfstNo(); //
				// *decRefClaObj.setDptrMnfstDt(""); //
				// #
				List<DecRefSCC> decRefList = new LinkedList<DecRefSCC>();
				decRefList.add(decRefClaObj);
				// ----------------------------
				PrsnDtlsSCC prsDtls = new PrsnDtlsSCC();
				prsDtls.setPrsnTypCdd(settingLength("",3));
				prsDtls.setPrsnFamilyName(settingLength("",70));
				prsDtls.setPrsnGivenName(settingLength("",70));
				prsDtls.setPrsnNatnltyCdd(settingLength("",2));
				prsDtls.setPsngrInTransitIndctr(settingLength("",1));
				prsDtls.setCrewmemberRankOrRatingCdd("");
				prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
				prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
				prsDtls.setPrsnGenderCdd(settingLength("",3));
				prsDtls.setPrsnDtOfBirth("");
				prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
				prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
				// -----------------------------------------

				PrsnIdSCC prsnIdclassObj = new PrsnIdSCC();
				prsnIdclassObj.setPrsnIdDocExpiryDt("");
				prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
				prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
				prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
				List<PrsnIdSCC> prsnIdList = new LinkedList<PrsnIdSCC>();
				prsnIdList.add(prsnIdclassObj);

				// ---------------
				List<PrsnDtlsSCC> prsnDtlsList = new LinkedList<PrsnDtlsSCC>();
				prsnDtlsList.add(prsDtls);
				PrsnOnBoardSCC prsnOnBoard = new PrsnOnBoardSCC();
				prsnOnBoard.setPrsnDtls(prsnDtlsList);
				prsnOnBoard.setPrsnId(prsnIdList);
				prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));

				List<PrsnOnBoardSCC> prsnOnBoardList = new LinkedList<PrsnOnBoardSCC>();
				prsnOnBoardList.add(prsnOnBoard);
				
				//-------------------------------------------------------
				int i = 0;
				List<VoyageTransportEquipmentSCC> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSCC>();
				for (ContainerDetails cntnerDtl : containerDtls) {

					System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));

					VoyageTransportEquipmentSCC voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSCC();
					voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
					voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
					voyageTransportEquipmentClassObj.setQuipmentType("CN");
					voyageTransportEquipmentClassObj.setQuipmentLoadStatus(cntnerDtl.getEquipmentLoadStatus());
					voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
					voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
				}
				
				// ------------------------------------------------------------
				DigSignSCC digSignClassObj = new DigSignSCC();
				digSignClassObj.setStartSignature("");
				digSignClassObj.setStartCertificate("");
				digSignClassObj.setSignerVersion("");

				List<DigSignSCC> digSignList = new LinkedList<DigSignSCC>();
				digSignList.add(digSignClassObj);
				// ----------------
				MasterSCC mster = new MasterSCC();
				mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
				mster.setVoyageDtls(voyageDtlsList);
				mster.setVesselDtls(vesselDtlsList);
				mster.setAuthPrsn(authPrsnList);
				mster.setDecRef(decRefList);
			    mster.setPrsnOnBoard(prsnOnBoardList);
				mster.setVoyageTransportEquipment(voyageTransportEquipmentList);
				
				// ----------------------------------
				HeaderFieldSCC headerFieldClassObj = new HeaderFieldSCC();

				headerFieldClassObj.setSenderID(senderId);
				// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
				// from old disSCCtions
				headerFieldClassObj.setReceiverID(objForm.getPod());
				headerFieldClassObj.setVersionNo("1.0");
				headerFieldClassObj.setIndicator("T");
				// "Default value: IECHE01"
				headerFieldClassObj.setMessageID("IECHE01");
				headerFieldClassObj.setSequenceOrControlNumber(serialNumber);// old screen String sId (
				headerFieldClassObj.setDate(currDate);
				headerFieldClassObj.setTime("T" + getTimeHeader());
				// "Default value: ES"
				headerFieldClassObj.setReportingEvent("ES");
				// -------------------------------------------------

				List<MasterSCC> masterList = new LinkedList<MasterSCC>();
				masterList.add(mster);

				List<HeaderFieldSCC> headerFieldList = new LinkedList<HeaderFieldSCC>();
				headerFieldList.add(headerFieldClassObj);

				JsonMainObjctSCC org = new JsonMainObjctSCC();
				// org.setHeaderField(headerFieldList);
				org.setHeaderField(headerFieldList);
				 org.setDigSign(digSignList);
				org.setMaster(masterList);
				return org;
	}

	public static JsonMainObjctSCE getSCE(List<ImportGeneralManifestMod> blList,ImportGeneralManifestMod service,List<IGMPersonOnBoardMod> personOnBoardMod ,List<IGMShipStoresMod> shipStoresMod,int getSeqNo)  {

	//	ImportGeneralManifestMod objForm = (ImportGeneralManifestMod) blList;
		JsonMainObjctSCE org = new JsonMainObjctSCE();
		MasterSCE mster = new MasterSCE();
		
		String msgID = "msgID";
		String serialNumber = "";
		String jobID = "";
		String currDate = LocalDate.now().toString().replaceAll("-", "");
		String currTime = new StringBuffer().append(LocalTime.now().getHour()).append(LocalTime.now().getMinute())
				.toString();
		
		int fromItemNoTemp =Integer.valueOf(service.getFromItemNo());
		System.out.println("currTime = " + currTime);
		String decHeader = "Declaration";
		String senderId = "ICEGATEID";
		File jsonFile = null;
		String jobNum = "";
		String msgTyp = null;
		String rpngEvent = "SCE";
		int containerCount = 0;
		String voyage = isNull((String) service.getVoyage());
		String newVoyage = isNull((String) service.getNewVoyage());
		String pol = isNull((String) service.getPol());
		if (newVoyage != null && !newVoyage.trim().equals("")) {
			voyage = newVoyage;
		}
		String vessel = isNull((String) service.getVessel());
		String newVessel = isNull((String) service.getNewVessel());
		// JSONObject marksNumberDtls = (JSONObject)marksNumberDtlstls;
		if (newVessel != null && !newVessel.trim().equals("")) {
			vessel = newVessel;
		}
		String generatedFileNameOfJson = null;
		generatedFileNameOfJson = msgTyp + "_" + msgID + "_" + rpngEvent + "_" + senderId + "_" + jobID + "_" + currDate
				+ "_" + decHeader + ".json";

		// Creating object of all class
		
		List<ShipItnrySCE> shipItnry = new LinkedList<ShipItnrySCE>();
		List<MCSuprtDocsSCE> mcSuprtDoc = new LinkedList<MCSuprtDocsSCE>();
		List<MCAdtnlDecSCE> mcAdtnlDec= new LinkedList<MCAdtnlDecSCE>();
		List<MastrCnsgmtDecSCE> mastrCnsgmtDecList = new LinkedList<MastrCnsgmtDecSCE>();
		
	
		
		// ===================

		for (ImportGeneralManifestMod blObj : blList) {
			if (blObj != null && "true".equalsIgnoreCase(blObj.getIsBlSave())) {
				blObj.setItemNumber(fromItemNoTemp+"");
			} else {
				continue;
			}
			fromItemNoTemp++;
			List<NotifyParty> notifyPartyDetailes = blObj.getNotifyParty();
			List<Consignee> consigneeDtls = blObj.getConsignee();
			List<MarksNumber> marksNumberDtls = blObj.getMarksNumber();
			List<Consigner> consignerDtls = blObj.getConsigner();
			List<ContainerDetails> containerDtls = blObj.getContainerDetailes();
			
			MastrCnsgmtDecSCE mastrCnsgmtDec = new MastrCnsgmtDecSCE();
			HouseCargoDecSCE houseCargoDecSCEObj = new HouseCargoDecSCE();
		
			List<ItemDtlsSCE> itemDtls = new LinkedList<ItemDtlsSCE>();
			List<TrnsprtEqmtSCE> trnsprtEqmt = new LinkedList<TrnsprtEqmtSCE>();
			List<LocCstmSCE> locCstm = new LinkedList<LocCstmSCE>();
			List<MCRefSCE> mCRef = new LinkedList<MCRefSCE>();
			List<TrnsprtDocMsrSCE> trnsprtDocMsr = new LinkedList<TrnsprtDocMsrSCE>();
			List<ItnrySCE> itnry = new LinkedList<ItnrySCE>();
			List<TrnsprtDocSCE> trnsprtDoc = new LinkedList<TrnsprtDocSCE>();
			List<PrevRefSCE> prevRef = new LinkedList<PrevRefSCE>();
			List<TrnshprSCE> trnshpr = new LinkedList<TrnshprSCE>();
			List<HouseCargoDecSCE> houseCargoDec = new LinkedList<HouseCargoDecSCE>();
//			List<HCAdtnlDecSCE> hcAdtnlDec= new LinkedList<HCAdtnlDecSCE>();
//			List<HCCrgoSuprtDocsSCE> crgoSuprtDoc = new LinkedList<HCCrgoSuprtDocsSCE>();
			
			
			
			ItnrySCE itnryClassObj = new ItnrySCE();
			if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("1",5)); 
			}else if (blObj.getPortOfLoading()!= null && blObj.getPod()!= null && blObj.getPortOfDestination() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("2",5)); 		
			}else if(blObj.getPortOfLoading()!= null && blObj.getPod()!= null && 
					blObj.getPortOfDestination() != null && blObj.getPortOfDeschargedCfs() != null ) {
				itnryClassObj.setPrtOfCallSeqNmbr(settingLength("3",5)); 
			}
			itnryClassObj.setPrtOfCallCdd(settingLength(blObj.getNext_port_of_call_coded(),10));    //TODO  guru
			itnryClassObj.setPrtOfCallName(settingLength(blObj.getNext_port_of_call_name(),256));		//TODO  guru
			itnryClassObj.setNxtPrtOfCallName(settingLength(blObj.getPort_of_call_name(),256));			//TODO  guru
			itnryClassObj.setNxtPrtOfCallCdd(settingLength(blObj.getPod(),10));							//TODO  guru
			itnryClassObj.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1));
			itnry.add(itnryClassObj);
			mastrCnsgmtDec.setItnry(itnryClassObj);
			houseCargoDecSCEObj.setItnry(itnry);
			// --------------------------------------------------------
			
			List<HCRefSCE> hCRef = new LinkedList<HCRefSCE>();
			
			HCRefSCE hCRefObj = new HCRefSCE();
			hCRefObj.setBlDt(blObj.getBlDate());
			hCRefObj.setBlNo(settingLength(blObj.getBl(),20));
			hCRefObj.setConsolidatedIndctr(settingLength(blObj.getConsolidated_indicator(),4));
			hCRefObj.setConsolidatorPan( settingLength(blObj.getConsolidator_pan(),16));
			if(blObj.getMcin()!= null && blObj.getPcin() != null) {
				hCRefObj.setPrevDec("y");
			}else {
				hCRefObj.setPrevDec("N");
			}
//			hCRefObj.setPrevDec(blObj.getPrevious_declaration());
			hCRefObj.setSubLineNo(settingLength(blObj.getSubLineNumber(),4));
			hCRef.add(hCRefObj);
			houseCargoDecSCEObj.sethCRef(hCRef);
		//======================================================================================	

			
			// ----------------------------------------
			MCRefSCE mCRefClassObj = new MCRefSCE();
			mCRefClassObj.setLineNo(Integer.parseInt(blObj.getItemNumber())); // Line 60
			mCRefClassObj.setMstrBlNo(settingLength(blObj.getBl(),20)); // Line 53
			mCRefClassObj.setMstrBlDt(removeSlash(blObj.getMasterBlDate())); // Line 53
			mCRefClassObj.setConsolidatorPan("PAN:"+settingLength(blObj.getAgentCode(),4));// Line 76
			
			if( null== blObj.getMcin() ||blObj.getMcin().equals("") && null== blObj.getPcin() || blObj.getPcin().equals("")) {
				mCRefClassObj.setPrevDec("N");
			}else {
				mCRefClassObj.setPrevDec("Y");
			}
			try {
				if( blObj.getHblArr().isEmpty() || blObj.isHbl()==false  ) {
					 mCRefClassObj.setConsolidatedIndctr("S");// Line 76   //TODO
				}else {
					 mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
				}
			}catch (Exception e) {
				mCRefClassObj.setConsolidatedIndctr("C");// Line 76 
			}
		    mCRefClassObj.setConsolidatorPan("PAN:"+settingLength(service.getAgentCode(),16));
			mastrCnsgmtDec.setmCRef(mCRefClassObj);
			mCRef.add(mCRefClassObj);
			
			// ---------------------------------------- Writing a new nitun
//		PrevRefSCE prevRefObj = new PrevRefSCE();
//			prevRefObj.setCinTyp(settingLength(blObj.getCin_type(),4));
//			prevRefObj.setCsnDt(blObj.getCsn_date()); 
//			prevRefObj.setCsnNmbr(settingLength(blObj.getCsn_number(),7)); 
//			prevRefObj.setMcinPcin(generatedFileNameOfJson);
//			prevRefObj.setNmbrOfPkgs(generatedFileNameOfJson);
//			prevRefObj.setTypOfPackage(pol);
//			prevRefObj.setCsnRptngTyp(settingLength(blObj.getCsn_reporting_type(),4)); guru said to comment
//			prevRefObj.setCsnSbmtdBy( settingLength(blObj.getCsn_submitted_by(),20)); guru said to comment
//			prevRefObj.setCsnSbmtdTyp(settingLength(blObj.getCsn_submitted_type(),4)); guru said to comment
//			prevRefObj.setCsnSiteId(settingLength(blObj.getCsn_site_id(),6)); guru said to comment	
			
//			prevRef.add(prevRefObj);
//			houseCargoDecSCEObj.setPrevRef(prevRef);
		
			// ----------------------------
			LocCstmSCE locCstmClassObj = new LocCstmSCE();

			locCstmClassObj.setFirstPrtOfEntry( service.getPortArrival());
			
			if(!blObj.getPortOfDestination().equals(blObj.getPod())) {
				  locCstmClassObj.setNxtPrtOfUnlading (settingLength(blObj.getPortOfDestination(),6)); // New added		
				}else if(blObj.getPod().equals(blObj.getPortOfDestination())) {
					locCstmClassObj.setNxtPrtOfUnlading (settingLength(blObj.getPod(),6)); // New added	
				}
//			locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),6)); // New added
			
			if(blObj.getPortOfDestination().equalsIgnoreCase(blObj.getPod()) ) {
				locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDeschargedCfs(),6));
			}else {
				locCstmClassObj.setDestPrt(settingLength(blObj.getPortOfDestination(),8));
			}

//			locCstmClassObj.setTypOfCrgo(settingLength(blObj.getType_of_cargo(),2)); // Line 90
			if(blObj.getPortOfDestination() != null || !blObj.getPortOfDestination().equals(" ") &&
					blObj.getPod() != null ||  !blObj.getPod().equals(" ")) {
//				System.out.println(service.getPortOfDestination().substring(0, 2));
//				System.out.println( service.getPortOfDeschargedCfs().substring(0, 2));
				
			if(blObj.getPortOfDestination().substring(0, 2).equals("IN") && blObj.getPod().substring(0, 2).equals("IN")){
				locCstmClassObj.setTypOfCrgo(settingLength("IM",2)); // if both value in india base
			}else if(! blObj.getPortOfDestination().substring(0, 2).equals("IN") && ! blObj.getPod().substring(0, 2).equals("IN")){
				locCstmClassObj.setTypOfCrgo(settingLength("TR",2)); // both value is not india base 
			}else if( !blObj.getPortOfDestination().substring(0, 2).equals("IN") &&  blObj.getPod().substring(0, 2).equals("IN")) {
				locCstmClassObj.setTypOfCrgo(settingLength("TR",2)); //if portOfdest in india base and portOfDis in foreign base then
			}
			}
			locCstmClassObj.setItemTyp(settingLength(blObj.getItemType(),2)); // Line 61
//			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4)); // Line 57
			//condition added as per discussion
//			locCstmClassObj.setCrgoMvmt(settingLength(blObj.getCargoMovmnt(),4));// Line 57
			if(blObj.getPod() != null || !blObj.getPod().equals(" ") &&   blObj.getPortOfDestination() != null || 
					!blObj.getPortOfDestination().equals(" ")) {
			if((blObj.getPod().substring(0,2).equals("IN") && blObj.getPortOfDestination().substring(0, 2).equals("IN")) && (blObj.getPod().equals(blObj.getPortOfDestination()))) {
				locCstmClassObj.setCrgoMvmt(settingLength("LC",4));	
			}else if(blObj.getPod().substring(0,2).equals("IN") && blObj.getPortOfDestination().substring(0, 2).equals("IN") && ! blObj.getPod().equals(service.getPortOfDestination())&& blObj.getMode_of_transport()== "3"){
				locCstmClassObj.setCrgoMvmt(settingLength("TI",4));	
			}else if(! blObj.getPod().substring(0,2).equals("IN") && blObj.getPortOfDestination().substring(0, 2).equals("IN")) {
				locCstmClassObj.setCrgoMvmt(settingLength("DT",4));	
			}else if(!blObj.getPod().substring(0,2).equals("IN" ) && ! blObj.getPortOfDestination().substring(0, 2).equals("IN")) {
				locCstmClassObj.setCrgoMvmt(settingLength("FT",4));	
			}
			}
			
			locCstmClassObj.setNatrOfCrgo(settingLength("C",4));  // Line 59
			locCstm.add(locCstmClassObj);
			
			mastrCnsgmtDec.setLocCstm(locCstmClassObj);

			houseCargoDecSCEObj.setLocCstm(locCstm);
		
			// ------------------------------------------
			if(blObj.getPod().equals(service.getPod()) && !blObj.getPortOfDestination().equals(blObj.getPod()) && blObj.getTrshprFlag().equals("1")  ) {
			TrnshprSCE TrnshprObj = new TrnshprSCE(); // New added
			if(blObj.getMode_of_transport()== "Rail" ) {
				TrnshprObj.setTrnsprtCod(blObj.getCarrierNo()); 
				TrnshprObj.setTrnshprBond(settingLength(blObj.getTpBondNo(),10));	
			}else {
			TrnshprObj.setTrnsprtCod(blObj.getCarrierNo()); 
			TrnshprObj.setTrnshprBond(settingLength(blObj.getTpBondNo(),10));	
			}
//			if(blObj.getRoadCarrCodeVVS()== null && blObj.getTpbondnoVVS()==null) {
			
//			}
			trnshpr.add(TrnshprObj);
			mastrCnsgmtDec.setTrnshpr(TrnshprObj);
			houseCargoDecSCEObj.setTrnshpr(trnshpr);
			}
			// ---------------------------------------------------------
			TrnsprtDocMsrSCE trnsprtDocMsrClassObj = new TrnsprtDocMsrSCE();
			trnsprtDocMsrClassObj.setNmbrOfPkgs( settingLength(blObj.getTotal_number_of_packages(),8)); 
			if(null != blObj.getType_of_package()|| ("").equals(blObj.getType_of_package())) {
				trnsprtDocMsrClassObj.setTypsOfPkgs(blObj.getType_of_package());
			}else {
				trnsprtDocMsrClassObj.setTypsOfPkgs("");
			}
			if(null != blObj.getGrosWeight() || ("").equals(blObj.getGrosWeight())) {
				trnsprtDocMsrClassObj.setGrossWeight(settingLengthForDouble(blObj.getGrosWeight(),12,3));
			}else {
				trnsprtDocMsrClassObj.setGrossWeight("");
			}
//			trnsprtDocMsrClassObj.setNetWeight(settingLengthForDouble(blObj.getNetWeight(),12,3));  no need
			trnsprtDocMsrClassObj.setUnitOfWeight(settingLength("KGS",3));
//			trnsprtDocMsrClassObj.setInvoiceValueOfCnsgmt(settingLengthForDouble(blObj.getInvoiceValueFc(),16,2)); // not cleared by Guru
//			trnsprtDocMsrClassObj.setCrncyCd(settingLength(blObj.getCurrency(),3));  // not cleared by Guru
			trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength("",512));
			if(blObj.getCargo_msmt() > 0) {
				trnsprtDocMsrClassObj.setGrossVolume (settingLengthForDouble(blObj.getGross_volume(),12,3));
	     		}
				if(! "".equals(trnsprtDocMsrClassObj.getGrossVolume())&&  trnsprtDocMsrClassObj.getGrossVolume() != null ) {
					trnsprtDocMsrClassObj.setUnitOfVolume(settingLength("CBM",3));
				}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj); // below in mark nad no loop
			houseCargoDecSCEObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ------------------------------------------------------
			if(mCRefClassObj.getConsolidatedIndctr().equals("S")) {
			ItemDtlsSCE itemDtlsClassObj = new ItemDtlsSCE();
			// trnsprtEqmtClassObj.setHSCE((String)blObj.get(" ")); not cleared by guru
			itemDtlsClassObj.setCrgoItemSeqNmbr(blObj.getCommodity_seq()+"");
			itemDtlsClassObj.setCrgoItemDesc(settingLength(blObj.getCargo_item_description(),256));
			if(blObj.getDgFlag() != null && StringUtils.isNotBlank(blObj.getDgFlag()) ) {
				itemDtlsClassObj.setUnoCd( settingLength(blObj.getUno_code(),5));
				itemDtlsClassObj.setImdgCd( settingLength(blObj.getImdg_code(),3));	
			}else {
					itemDtlsClassObj.setUnoCd( settingLength("ZZZZZ",5));
					itemDtlsClassObj.setImdgCd( settingLength("ZZZ",3));	
			}
			itemDtlsClassObj.setNmbrOfPkgs(settingLength(blObj.getTotal_number_of_packages(),8)); 
			itemDtlsClassObj.setTypOfPkgs(settingLength(blObj.getType_of_package(),3));
			itemDtlsClassObj.setHsCd(blObj.getCommdity_code());
			itemDtls.add(itemDtlsClassObj);
			mastrCnsgmtDec.setItemDtls(itemDtlsClassObj);
			}
			houseCargoDecSCEObj.setItemDtls(itemDtls);
			// ------------------------------------------------------
			TrnsprtDocSCE trnsprtDocClassObj = new TrnsprtDocSCE();
			trnsprtDocClassObj.setPrtOfAcptCdd( settingLength(blObj.getPort_of_acceptance(),6));							//TODO  guru
			trnsprtDocClassObj.setPrtOfAcptName( settingLength(blObj.getAcceptanceName(),256));			//TODO  guru
			trnsprtDocClassObj.setPrtOfReceiptCdd(settingLength(blObj.getPort_of_receipt(),10));
			trnsprtDocClassObj.setPrtOfReceiptName( settingLength(blObj.getRecieptName(),256));	
			trnsprtDocClassObj.setCnsgnrsName(generatedFileNameOfJson);	
//			trnsprtDocClassObj.setUcrTyp( settingLength(blObj.getUcr_type(),3)); not required
//			trnsprtDocClassObj.setUcrCd(settingLength(blObj.getUcr_code(),35));	 not required
			//new added
//			trnsprtDocClassObj.setCnsgnrsCd(generatedFileNameOfJson); not required
			
		
//============================================================================				
				
			for (NotifyParty notyObj : notifyPartyDetailes) {

				if ((blObj.getBl()).equals(notyObj.getBlNo())) {
					
					String add =  checkNull(notyObj.getAddressLine1()) +checkNull( notyObj.getAddressLine2())
							+ checkNull( notyObj.getAddressLine3()) + checkNull( notyObj.getAddressLine4());
					trnsprtDocClassObj.setNotfdPartyStreetAddress(settingLength(add,256));
					trnsprtDocClassObj.setNotfdPartyCity( settingLength(notyObj.getCity(),70));
					trnsprtDocClassObj.setNotfdPartyCntrySubDiv(settingLength(blObj.getGstStateCode(),35));
					trnsprtDocClassObj.setNotfdPartyCntrySubDivName( settingLength(notyObj.getStateName(),35)); // will be provided by customer
					trnsprtDocClassObj.setNotfdPartyCntryCd( settingLength(notyObj.getCountryCode(),2));
					trnsprtDocClassObj.setNotfdPartyPstcd( settingLength(notyObj.getZip(),9));
					try {
					if(!notyObj.getNotifyPan().equals("")) {
						trnsprtDocClassObj.setPanOfNotfdParty(settingLength(notyObj.getNotifyPan(),17));
						trnsprtDocClassObj.setTypOfNotfdPartyCd( settingLength("PAN",30));
						trnsprtDocClassObj.setTypOfCd( settingLength("PAN",30));
					}else {
						for (Consignee cnsneeDtl : consigneeDtls) {
							trnsprtDocClassObj.setPanOfNotfdParty(settingLength(cnsneeDtl.getConsignePan(),17));
							trnsprtDocClassObj.setTypOfNotfdPartyCd("PAN");
							trnsprtDocClassObj.setTypOfCd( settingLength("PAN",30));
						}
						
					}
				}catch (Exception e) {
					for (Consignee cnsneeDtl : consigneeDtls) {
						trnsprtDocClassObj.setPanOfNotfdParty(settingLength(cnsneeDtl.getConsignePan(),17));
						trnsprtDocClassObj.setTypOfNotfdPartyCd("IEC");
						trnsprtDocClassObj.setTypOfCd( settingLength("IEC",30));
					}
				  }
				}
			}
			
//			trnsprtDocClassObj.setPanOfNotfdParty( settingLength(blObj.getPan_of_notified_party(),17));
//			trnsprtDocClassObj.setTypOfCd(pol);
			// ------------------------------------------------------------------
			for (MarksNumber marksAndNumberDtls : marksNumberDtls) {

				if ((blObj.getBl()).equals(marksAndNumberDtls.getBlNO())) {
					trnsprtDocMsrClassObj.setMarksNoOnPkgs(settingLength(marksAndNumberDtls.getMarksNumbers(),512));
					trnsprtDocClassObj.setGoodsDescAsPerBl( settingLength(marksAndNumberDtls.getDescription(),512));
				}
			}
			trnsprtDocMsr.add(trnsprtDocMsrClassObj);
			mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsrClassObj);
			houseCargoDecSCEObj.setTrnsprtDocMsr(trnsprtDocMsr);
			// ---------------------------------------------------

			// for (Object ctnerDtls: containeerDtls) {
			for (Consignee cnsneeDtl : consigneeDtls) {

				if ((blObj.getBl()).equals(cnsneeDtl.getBlNO()))
					;
				{
					String add = (String) cnsneeDtl.getAddressLine1() + cnsneeDtl.getAddressLine2()
							+ (String) cnsneeDtl.getAddressLine3() + (String) cnsneeDtl.getAddressLine4();
					// set all values in TrnsprtDoc Class Obj
					trnsprtDocClassObj.setCnsgneStreetAddress(settingLength(add,120));
					trnsprtDocClassObj.setCnsgnesName( settingLength(cnsneeDtl.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgneCity(  settingLength(cnsneeDtl.getCity(),70));
					trnsprtDocClassObj.setCnsgneCntrySubDivName(settingLength(cnsneeDtl.getStateName(),35));
					trnsprtDocClassObj.setCnsgneCntrySubDiv(blObj.getGstStateCode());
					trnsprtDocClassObj.setCnsgneCntryCd(settingLength(cnsneeDtl.getCountryCode(),2));
					trnsprtDocClassObj.setCnsgnePstcd( settingLength(cnsneeDtl.getZip(),9));
					try {
						if(cnsneeDtl.getConsigneIec()!= null || !cnsneeDtl.getConsigneIec().equals("") ) {
							trnsprtDocClassObj.setCnsgnesCd(settingLength(cnsneeDtl.getConsigneIec(),17));
						}else {
							for (NotifyParty notyObj : notifyPartyDetailes) {
							trnsprtDocClassObj.setCnsgnesCd(settingLength(notyObj.getNotifyIec(),17));
							}
						}
						}catch (Exception e) {
							for (NotifyParty notyObj : notifyPartyDetailes) {
								trnsprtDocClassObj.setCnsgnesCd(settingLength(notyObj.getNotifyIec(),17));
								}
						}
				}
			}

			// --------------------------------------------------------
			for (Consigner cnsnerDtls : consignerDtls) {

				if ((blObj.getBl()).equals(cnsnerDtls.getBlNO())) {
					String add =checkNull ((String) cnsnerDtls.getAddressLine1()) +checkNull ( cnsnerDtls.getAddressLine2())
							+ checkNull ((String) cnsnerDtls.getAddressLine3()) + checkNull ((String) cnsnerDtls.getAddressLine4());
					// set all values in TrnsprtDoc Class Obj cnsgnrsName;
					trnsprtDocClassObj.setCnsgnrStreetAddress(settingLength(add,70));
					trnsprtDocClassObj.setCnsgnrsName( settingLength(cnsnerDtls.getCustomerName(),70));
					trnsprtDocClassObj.setCnsgnrCity( settingLength(cnsnerDtls.getCity(),70));
//					trnsprtDocClassObj.setCnsgnrCntrySubDivName( settingLength(cnsnerDtls.getState(),35));
//					 trnsprtDocClassObj.setCnsgnrCntrySubDivCd(blObj.getGstStateCode());
					
//					trnsprtDocClassObj.setCnsgnrsCd( settingLength(cnsnerDtls.getCustomerCode(),17));
					// trnsprtDocClassObj.setCnsgnrCntrySubDivCd((String) cnsnerDtls.get(""));
					trnsprtDocClassObj.setCnsgnrCntryCd( settingLength(cnsnerDtls.getCountryCode(),2));
//					trnsprtDocClassObj.setCnsgnrPstcd( settingLength(cnsnerDtls.getZip(),9));
					trnsprtDocClassObj.setNameOfAnyOtherNotfdParty(settingLength(cnsnerDtls.getCustomerName(),70));
				}
			}
			trnsprtDoc.add(trnsprtDocClassObj);
			mastrCnsgmtDec.setTrnsprtDoc(trnsprtDocClassObj);
			houseCargoDecSCEObj.setTrnsprtDoc(trnsprtDoc);
			// ------------------------------------------------
			int j = 0 ;
			Set<String> containseSets= new HashSet<>();
			for (ContainerDetails ctnerDtl : containerDtls) {
				
				if ((blObj.getBl()).equals(ctnerDtl.getBlNo()) && !containseSets.contains(ctnerDtl.getContainerNumber())) {
					j++;
					containseSets.add(ctnerDtl.getContainerNumber());
					TrnsprtEqmtSCE trnsprtEqmtClassObj = new TrnsprtEqmtSCE();
					trnsprtEqmtClassObj.setEqmtSeqNo(settingLength(j+"",5));
					trnsprtEqmtClassObj.setEqmtId(settingLength(ctnerDtl.getContainerNumber(),17));
					trnsprtEqmtClassObj.setEqmtTyp(settingLength("CN",3)); // optonal
					trnsprtEqmtClassObj.setEqmtSize(settingLength(ctnerDtl.getIsoCode(),4)); //new added
					trnsprtEqmtClassObj.setEqmtLoadStatus(settingLength(ctnerDtl.getEquipmentLoadStatus(),3));
					trnsprtEqmtClassObj.setEqmtSealTyp(settingLength(ctnerDtl.getEquipment_seal_type(),5));
					trnsprtEqmtClassObj.setEqmtSealNmbr(settingLength(ctnerDtl.getContainerSealNumber(),15));
					trnsprtEqmtClassObj.setSocFlag(settingLength(ctnerDtl.getSoc_flag(),1));
//					trnsprtEqmtClassObj.setAdtnlEqmtHold(settingLength("",256)); not required(on hold)
//					trnsprtEqmtClassObj.setOtherEqmtId(settingLength("",256)); //not required
//					trnsprtEqmtClassObj.setEqmtStatus(ctnerDtl.getStatus()); //not required
// 					trnsprtEqmtClassObj.setEventDt(""); not required
// 					trnsprtEqmtClassObj.setFinalDestLoc(settingLength("",10)); //not required
					trnsprtEqmtClassObj.setCntrAgntCd(settingLength(service.getAgentCode(),17));
					trnsprtEqmtClassObj.setCntrWeight(settingLengthForDouble(ctnerDtl.getContainerWeight(),14,2));
					trnsprtEqmtClassObj.setTotalNmbrOfPkgs(settingLength(ctnerDtl.getTotalNumberOfPackagesInContainer(),8));
					trnsprtEqmt.add(trnsprtEqmtClassObj);
				}
			}  
			mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
			houseCargoDecSCEObj.setTrnsprtEqmt(trnsprtEqmt);
			//============================================================
//			HCCrgoSuprtDocsSCE crgoSuprtDocs = new HCCrgoSuprtDocsSCE(); 
//			crgoSuprtDocs.setBnefcryCdpublic(settingLength("",35));
//			crgoSuprtDocs.setDocRefNmbr(settingLength("",17));
//			crgoSuprtDocs.setDocTypCd(settingLength("",6));
//			crgoSuprtDocs.setIcegateUserid(settingLength("",15));
//			crgoSuprtDocs.setIrnNmbr(settingLength("",14));
//			crgoSuprtDocs.setRefSerialNo(settingLength("",5));
//
//			crgoSuprtDocs.setSubSerialNoRef("");
//			crgoSuprtDocs.setTagRef(settingLength("",5));
//			crgoSuprtDoc.add(crgoSuprtDocs);
//			houseCargoDecSCEObj.sethCCrgoSuprtDocs(crgoSuprtDoc);
//			//==========================================================
//			HCAdtnlDecSCE adtnlDec = new HCAdtnlDecSCE();
//			adtnlDec.setTagRef(settingLength("",5));
//			adtnlDec.setRefSerialNo(settingLength("",5));
//			adtnlDec.setInfoCd(settingLength("",35));
//			adtnlDec.setInfoDt("");
//			adtnlDec.setInfoMsr(settingLength("",5));
//			adtnlDec.setInfoQualifier(settingLength("",10));
//			adtnlDec.setInfoText(settingLength("",100));
//			adtnlDec.setInfoTyp(settingLength("",10));
//			
//			hcAdtnlDec.add(adtnlDec);
//			houseCargoDecSCEObj.setHcAdtnlDec(hcAdtnlDec);
			//----------------------------------------------------------------
//			MCSuprtDocsSCE mcSuprtDocs = new MCSuprtDocsSCE (); 
//			mcSuprtDocs.setBnefcryCdpublic(settingLength("",35));
//			mcSuprtDocs.setDocRefNmbr(settingLength("",17));
//			mcSuprtDocs.setDocTypCd(settingLength("",6));
//			mcSuprtDocs.setIcegateUserid(settingLength("",15));
//			mcSuprtDocs.setIrnNmbr(settingLength("",14));
//			mcSuprtDocs.setRefSerialNo(settingLength("",5));
//			mcSuprtDocs.setSubSerialNoRef("");
//			mcSuprtDocs.setTagRef(settingLength("",5));
//			mcSuprtDoc.add(mcSuprtDocs);
			//------------------------------------------------------------
//			MCAdtnlDecSCE mcAdtnlDecs = new MCAdtnlDecSCE();
//			mcAdtnlDecs.setTagRef(settingLength("",5));
//			mcAdtnlDecs.setRefSerialNo(settingLength("",35));
//			mcAdtnlDecs.setInfoCd(settingLength("",35));
//			mcAdtnlDecs.setInfoDt("");
//			mcAdtnlDecs.setInfoMsr(settingLength("",5));
//			mcAdtnlDecs.setInfoQualifier(settingLength("",10));
//			mcAdtnlDecs.setInfoText(settingLength("",100));
//			mcAdtnlDecs.setInfoTyp(settingLength("",10));
//			
//			mcAdtnlDec.add(mcAdtnlDecs);
			// ------------------------------------------------
//			ShipItnrySCE shipItny3 = new ShipItnrySCE();
//			String prtOfCallCdd = null;
//			String prtOfCallNm = null;
//			String itnrySeq = null;
//			// all value set
//			if (service.getNextport1() == null || service.getLastPort1() == "") {
//				prtOfCallCdd = null;
//				prtOfCallNm = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "-3";
//				prtOfCallCdd = service.getLastPort1();
//			}
//			shipItny3.setShipItnrySeq(itnrySeq);// if not null -3
//			shipItny3.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
//
//			if (service.getNextport2() == null || service.getNextport2() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "-2";
//				prtOfCallCdd = service.getLastPort2();
//			}
//			ShipItnrySCE shipItnry2 = new ShipItnrySCE();
//			shipItnry2.setShipItnrySeq(itnrySeq);
//			shipItnry2.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
//
//			if (service.getNextport3() == null || service.getLastPort3() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "-1";
//
//				prtOfCallCdd = service.getLastPort3();
//			}
//			ShipItnrySCE shipItnry1 = new ShipItnrySCE();
//			shipItnry1.setShipItnrySeq(itnrySeq);
//			shipItnry1.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
//
//			ShipItnrySCE shipItnry0 = new ShipItnrySCE();
//			shipItnry0.setShipItnrySeq("0");
//			shipItnry0.setPrtOfCallCdd(settingLength(service.getPod(),10)); // blObj.get("Port of call sequence numbe"));
//			shipItnry0.setPrtOfCallName(settingLength(service.getPort_of_call_coded(),256));
//
//			if (service.getLastPort1() == null || service.getLastPort1() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "1";
//				prtOfCallCdd = service.getLastPort1();
//			}
//			ShipItnrySCE shipItnry11 = new ShipItnrySCE();
//			shipItnry11.setShipItnrySeq(itnrySeq);
//			shipItnry11.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
//
//			if (service.getLastPort2() == null || service.getLastPort2() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "2";
//				prtOfCallCdd = service.getLastPort2();
//			}
//			ShipItnrySCE shipItnry22 = new ShipItnrySCE();
//			shipItnry22.setShipItnrySeq(itnrySeq);
//			shipItnry22.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
//			shipItnry22.setPrtOfCallName( settingLength(blObj.getPort_of_call_coded(),256));
//
//			if (service.getLastPort3() == null || service.getLastPort3() == "") {
//				prtOfCallCdd = null;
//				itnrySeq = null;
//			} else {
//				itnrySeq = "3";
//				prtOfCallCdd = service.getLastPort3();
//			}
//			ShipItnrySCE shipItnry33 = new ShipItnrySCE();
//			shipItnry33.setShipItnrySeq(itnrySeq);
//			shipItnry33.setPrtOfCallCdd(settingLength(prtOfCallCdd,10));
//			shipItnry33.setPrtOfCallName(settingLength(blObj.getPort_of_call_sequence_number(),256));
//			shipItnry.add(shipItny3);
//			shipItnry.add(shipItnry2);
//			shipItnry.add(shipItnry1);
//			shipItnry.add(shipItnry0);
//			shipItnry.add(shipItnry11);
//			shipItnry.add(shipItnry22);
//			shipItnry.add(shipItnry33);
//			// ------------------------------------------------------
//			houseCargoDec.add(houseCargoDecSCEObj);
			for (ContainerDetails cntnerDtl : containerDtls) {

//				System.out.println("   coneeDtls  " + i + (cntnerDtl.getContainerSealNumber()));
				containerCount++;
			}
			
			mastrCnsgmtDecList.add(mastrCnsgmtDec);
			mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
			houseCargoDec.add(houseCargoDecSCEObj);
			if (blObj.isHbl()== true) {
				mastrCnsgmtDec.setHouseCargoDec(houseCargoDecSCEObj);
			}
	}
		
		// now add all List to relevant class

		
//		mastrCnsgmtDec.setItemDtls(itemDtls);
//		mastrCnsgmtDec.setItnry(itnry);
//		mastrCnsgmtDec.setLocCstm(locCstm);
//		mastrCnsgmtDec.setmCRef(mCRef);
//		mastrCnsgmtDec.setTrnsprtDoc(trnsprtDoc);
//		mastrCnsgmtDec.setTrnsprtDocMsr(trnsprtDocMsr);
//		mastrCnsgmtDec.setTrnsprtEqmt(trnsprtEqmt);
//		mastrCnsgmtDec.setPrevRef(prevRef);
//		mastrCnsgmtDec.setTrnshpr(trnshpr);
//		mastrCnsgmtDec.setHouseCargoDec(houseCargoDec); 
//		mastrCnsgmtDec.setmCSuprtDocs(mcSuprtDoc);
//		//mastrCnsgmtDec.setmCAdtnlDec(mcAdtnlDec);
//		
//		mastrCnsgmtDecList.add(mastrCnsgmtDec);
		VoyageDtlsSCE voyageDtlsClassObj = new VoyageDtlsSCE();
//		voyageDtlsClassObj.setVoyageNo(voyage); // Line10 not required 
		voyageDtlsClassObj.setCnvnceRefNmbr(settingLength(service.getViaVcn(),35)); // Line 193
		voyageDtlsClassObj.setTotalNoOfTrnsprtEqmtMnfsted( settingLength(containerCount+"",5)); // Line:-46
//		voyageDtlsClassObj.setCrgoDescCdd(service.getCargoDeclaration()); // Line:-195 not required 
//		voyageDtlsClassObj.setBriefCrgoDesc(service.getBrief_cargo_des()); // Line:-195 not required 
		voyageDtlsClassObj.setTotalNmbrOfLines(settingLength(service.getTotalItem() ,5));// Line38 (objForm.getTotalItem()); nitun
//		voyageDtlsClassObj.setExptdDtAndTimeOfArvl(service.getArrivalDate() + "T" + getTime(service.getArrivalTime())); not required 
		// voyageDtlsClassObj.setExptdDtAndTimeOfDptr(objForm.getArrivalDate() + "T" +
		// getTime(objForm.getArrivalTime()));  not required 
//		voyageDtlsClassObj.setNmbrOfPsngrsMnfsted(" "); //not required 
//		voyageDtlsClassObj.setNmbrOfCrewMnfsted(service.getCrewListDeclaration()); not required 
//		voyageDtlsClassObj.setShipItnry(shipItnry); not required 

	
		mster.setVoyageDtls(voyageDtlsClassObj);
		
		// ---------------------------------------------------
		VesselDtlsSCE vesselDtls = new VesselDtlsSCE();

		vesselDtls.setModeOfTrnsprt(settingLength(service.getMode_of_transport(),1));// Line 191
		if(service.getTypeTransportMeans().equals("imovsl")) {
			vesselDtls.setTypOfTrnsprtMeans(" "); // not found
		}else {
			vesselDtls.setTypOfTrnsprtMeans(settingLength(service.getTypeTransportMeans(),25));
		}
		
		vesselDtls.setTrnsprtMeansId(settingLength(service.getImoCode(),25));		
//		vesselDtls.setVesselCd("");//not required
//		vesselDtls.setShipTyp(service.getShip_type()); // Line 192 not required
//		vesselDtls.setPurposeOfCall("1"); // always hard coded not required
		List<VesselDtlsSCE> vesselDtlsList = new LinkedList<VesselDtlsSCE>();
		vesselDtlsList.add(vesselDtls);
		
		// ----------------------------
		AuthPrsnSCE authPrsClassObj = new AuthPrsnSCE();
		authPrsClassObj.setSbmtrTyp(settingLength(service.getSubmitter_type(),4)); //
		authPrsClassObj.setSbmtrCd(settingLength(service.getAgentCode(),15)); //
		authPrsClassObj.setAuthReprsntvCd(settingLength(service.getAuthReprsntvCd(),10)); //
//		authPrsClassObj.setShpngLineCd("RCL"); // VALUE AL WAYS RCL //not required
//		authPrsClassObj.setAuthSeaCarrierCd(settingLength(service.getAuthorized_sea_carrier_code(),10)); // LinNo:-211 //not required
//		authPrsClassObj.setMasterName(settingLength(service.getMasterName(),30));// 21 //not required
//		authPrsClassObj.setShpngLineBondNmbr(settingLength(service.getShipping_line_bond_no_r(),10)); // LinNo:-190 //not required
//		authPrsClassObj.setTrmnlOprtrCd(settingLength(service.getCustomTerminalCode(),10)); // LinNo:-132
		
		List<AuthPrsnSCE> authPrsnList = new LinkedList<AuthPrsnSCE>();
		authPrsnList.add(authPrsClassObj);
		
		// ----------------------------
		DecRefSCE decRefClaObj = new DecRefSCE();

	    decRefClaObj.setMsgTyp("F");
//		decRefClaObj.setMsgTyp(settingLength( msgTyp,1));
		decRefClaObj.setPrtofRptng(settingLength(service.getCustomCode(),10)); // value from old screen [Pod]
		decRefClaObj.setJobNo(getSeqNo +1); // sid will give this Number
		decRefClaObj.setJobDt(currDate); //sid told me to keep crunt date
//	    decRefClaObj.setRptngEvent(objForm.getReportEvent()); //
		decRefClaObj.setRptngEvent(settingLength("SCE",4));
//		decRefClaObj.setMnfstNoRotnNo(settingLength(service.getManifest_no_csn_no(),7)); //  not required
//		decRefClaObj.setMnfstDtRotnDt(service.getManifest_date_csn_date()); //  not required
//		decRefClaObj.setVesselTypMvmt(settingLength(service.getVessel_type_movement(),2)); // not required
		// #
		// * decRefClaObj.setDptrMnfstNo(); //
		// *decRefClaObj.setDptrMnfstDt(""); //
		// #
		List<DecRefSCE> decRefList = new LinkedList<DecRefSCE>();
		decRefList.add(decRefClaObj);
	   
		// ----------------------------
	
			
//		PrsnDtlsSCE prsDtls = new PrsnDtlsSCE();
//		prsDtls.setPrsnTypCdd(settingLength("",3));
//		prsDtls.setPrsnFamilyName(settingLength("",70));
//		prsDtls.setPrsnGivenName(settingLength("",70));
//		prsDtls.setPrsnNatnltyCdd(settingLength("",2));
//		prsDtls.setPsngrInTransitIndctr(settingLength("",1));
//		prsDtls.setCrewmemberRankOrRatingCdd("");
//		prsDtls.setPsngrPrtOfEmbrktnCdd(settingLength("",5));
//		prsDtls.setPsngrPrtOfDsmbrktnCdd(settingLength("",5));
//		prsDtls.setPrsnGenderCdd(settingLength("",3));
//		prsDtls.setPrsnDtOfBirth("");
//		prsDtls.setPrsnPlaceOfBirthName(settingLength("",35));
//		prsDtls.setPrsnCntryOfBirthCdd(settingLength("",2));
//		// -----------------------------------------
//
//		PrsnIdSCE prsnIdclassObj = new PrsnIdSCE();
//		prsnIdclassObj.setPrsnIdDocExpiryDt("");
//		prsnIdclassObj.setPrsnIdOrTravelDocIssuingNationCdd(settingLength("",2));
//		prsnIdclassObj.setPrsnIdOrTravelDocNmbr(settingLength("",70));
//		prsnIdclassObj.setPrsnIdOrTravelDocTypCdd(settingLength("",3));
//		List<PrsnIdSCE> prsnIdList = new LinkedList<PrsnIdSCE>();
//		prsnIdList.add(prsnIdclassObj);
//
//		// ---------------
//		List<PrsnDtlsSCE> prsnDtlsList = new LinkedList<PrsnDtlsSCE>();
//		prsnDtlsList.add(prsDtls);
//		PrsnOnBoardSCE prsnOnBoard = new PrsnOnBoardSCE();
//		prsnOnBoard.setPrsnDtls(prsnDtlsList);
//		prsnOnBoard.setPrsnId(prsnIdList);
//		prsnOnBoard.setPrsnOnBoardSeqNmbr(settingLength("",5));
		
//----------------------------------------------------------------------
//		
//		VisaDtlsSCE visaDtlsSCEObj= new VisaDtlsSCE();
//		visaDtlsSCEObj.setPnrNmbr(settingLength(personOnBoardMod.get(g).getPnrNumber(),20));
//		visaDtlsSCEObj.setPrsnVisa(settingLength(personOnBoardMod.get(g).getVisa(),70));
		
	//-------------------------------------------------------------------------------	
//
//		List<PrsnOnBoardSCE> prsnOnBoardList = new LinkedList<PrsnOnBoardSCE>();
//		prsnOnBoardList.add(prsnOnBoard);
			
		
		// --------------------------------------------------------
//		ShipStoresSCE shipStores = new ShipStoresSCE();
//		shipStores.setSeqNmbr(settingLength("",5));
//		shipStores.setArticleNameCdd(settingLength("",18));
//		shipStores.setArticleNameText(settingLength("",512));
//		shipStores.setLocOnbrdText(settingLength("",256));
//		shipStores.setQntyCdOnbrd(settingLength("",3));
//		shipStores.setQntyOnbrd(settingLengthForDouble("",16,6));
//		List<ShipStoresSCE> shipStoresList = new LinkedList<ShipStoresSCE>();
//		shipStoresList.add(shipStores);
//	
		//-------------------------------------------------------
//		 ArvlDtlsSCE arvlDtls = new ArvlDtlsSCE();
//		 arvlDtls.setNmbrOfCrew(generatedFileNameOfJson);
//		 arvlDtls.setNmbrOfPsngrs(generatedFileNameOfJson);
//		 arvlDtls.setTotalNmbrOfPrsnsOnBoard(generatedFileNameOfJson);
//		 arvlDtls.setTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr(generatedFileNameOfJson);
//		 arvlDtls.setTotalNoOfCntrsLanded(generatedFileNameOfJson);
//		 arvlDtls.setTotalNoOfCntrsLoaded(generatedFileNameOfJson);
//		 arvlDtls.setTotalNoOfTrnsprtEqmtRprtdOnArvlDptr(generatedFileNameOfJson);
//		 List<ArvlDtlsSCE> arvlDtlsList = new LinkedList<ArvlDtlsSCE>();
//		 arvlDtlsList.add(arvlDtls);

//-----------------------------------------------------------------------------------------------		
		//newly commented
//		int i = 0;
//		List<VoyageTransportEquipmentSCE> voyageTransportEquipmentList = new LinkedList<VoyageTransportEquipmentSCE>();
//		for (ContainerDetails cntnerDtl : containerDtls) {
//
//			System.out.println("   coneeDtls  " + i + ((String) cntnerDtl.getContainerSealNumber()));
//
//			VoyageTransportEquipmentSCE voyageTransportEquipmentClassObj = new VoyageTransportEquipmentSCE();
//			voyageTransportEquipmentClassObj.setQuipmentSequenceNo( cntnerDtl.getContainerSealNumber());
//			voyageTransportEquipmentClassObj.setQuipmentId(cntnerDtl.getContainerNumber());
//			voyageTransportEquipmentClassObj.setQuipmentType("CN");
//			voyageTransportEquipmentClassObj.setQuipmentLoadStatus(settingLength(cntnerDtl.getEquipmentLoadStatus(),3));
//			voyageTransportEquipmentClassObj.setSocFlag(settingLength(cntnerDtl.getSoc_flag(),1));
//			voyageTransportEquipmentList.add(voyageTransportEquipmentClassObj);
//		}
		
		// ------------------------------------------------------------
		
//		TmSuprtDocsSCE  tmSuprtDocsObj = new TmSuprtDocsSCE();
//		List<TmSuprtDocsSCE> tmSuprtDocsList = new LinkedList<TmSuprtDocsSCE>();
//	tmSuprtDocsList.add(tmSuprtDocsObj);
		
		//--------------------------------------------------------------------

//		TmAdtnlDecSCE tmAdtnlDecObj = new TmAdtnlDecSCE();
//		List<TmAdtnlDecSCE> tmAdtnlDecList = new LinkedList<TmAdtnlDecSCE>();
//		tmAdtnlDecList.add(tmAdtnlDecObj);

		//--------------------------------------------------------------------

//		DigSignSCE digSignClassObj = new DigSignSCE();
//		digSignClassObj.setStartSignature("");
//		digSignClassObj.setStartCertificate("");
//		digSignClassObj.setSignerVersion("");
//
//		List<DigSignSCE> digSignList = new LinkedList<DigSignSCE>();
//		digSignList.add(digSignClassObj);
		// ----------------
		
		mster.setMastrCnsgmtDec(mastrCnsgmtDecList);
		
		voyageDtlsClassObj = null;
		mster.setVesselDtls(vesselDtls);
		vesselDtls = null;
		mster.setAuthPrsn(authPrsClassObj);
		authPrsClassObj = null;
		mster.setDecRef(decRefClaObj);
		 decRefClaObj = null;
//	    mster.setPrsnOnBoard(prsnOnBoardList);
//		mster.setVoyageTransportEquipment(voyageTransportEquipmentList); newly commmented
//		mster.setShipStores(shipStoresList);
		// ----------------------------------
		HeaderFieldSCE headerFieldClassObj = new HeaderFieldSCE();

		headerFieldClassObj.setSenderID(service.getSenderId());
		// headerFieldClassObj.setReceiverID(objForm.getCustomCode());
		// from old disscutions
		headerFieldClassObj.setReceiverID(service.getRecieverId());
		headerFieldClassObj.setVersionNo("SCE1102");
		headerFieldClassObj.setIndicator("T");
		// "Default value: IECHE01"
		headerFieldClassObj.setMessageID("SACHM22");
		headerFieldClassObj.setSequenceOrControlNumber(getSeqNo+1);// old screen String sId (
		headerFieldClassObj.setDate(getTimeHeader());
		headerFieldClassObj.setTime("T" + getIsdTime());
		// "Default value: ES"
		headerFieldClassObj.setReportingEvent("SCE");
		// -------------------------------------------------

		List<MasterSCE> masterList = new LinkedList<MasterSCE>();
		masterList.add(mster);

		List<HeaderFieldSCE> headerFieldList = new LinkedList<HeaderFieldSCE>();
		headerFieldList.add(headerFieldClassObj);

		
		// org.setHeaderField(headerFieldList);
		org.setHeaderField(headerFieldClassObj);
		headerFieldClassObj = null;
//		 org.setDigSign(digSignList);
		org.setMaster(mster);
		
		return org;
	}

	public static String getTime(String currTime) {
		System.out.println("getTime(currTime) = " + currTime);
		if (currTime == null || currTime.length() < 2)
			return currTime;
		return currTime.substring(0, 2) + "" + currTime.substring(2);
	}
	
	public static  String getIsdTime() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("hh:mm");

		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		System.out.println(df.format(date));
		String isdDateAndTime = df.format(date);
		System.out.println(isdDateAndTime);

		return  isdDateAndTime;
		
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
	 

	private static String isNull(String sId) {
		return (sId == null) ? "" : sId;
	}

	public static String removeSlash(String date) {
		if (date.contains("/"))
			date = date.replaceAll("/", "");
		return date;
	}

	public static String removeSlashForBlDate(String date) {
		if (date != null && !date.equals("")) {

			String dateArray[] = date.split("/");
			date = dateArray[2] + dateArray[1] + dateArray[0];
		}
		return date;
	}
	
	
}