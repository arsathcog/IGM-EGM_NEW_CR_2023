  CREATE TABLE "TOS_RESTOW_TMP" 
   (	
    "SESSION_ID" VARCHAR2(250 BYTE), 
	"PK_RESTOW_ID" VARCHAR2(100 BYTE), 
	"FK_LOAD_LIST_ID" VARCHAR2(17 BYTE), 
	"FK_DISCHARGE_LIST_ID" VARCHAR2(100 BYTE), 
	"FK_BOOKING_NO" VARCHAR2(17 BYTE), 
	"FK_BKG_SIZE_TYPE_DTL" VARCHAR2(12 BYTE), 
	"FK_BKG_SUPPLIER" VARCHAR2(2 BYTE), 
	"FK_BKG_EQUIPM_DTL" VARCHAR2(12 BYTE), 
	"DN_EQUIPMENT_NO" VARCHAR2(12 BYTE), 
	"DN_EQ_SIZE" VARCHAR2(2 BYTE), 
	"DN_EQ_TYPE" VARCHAR2(2 BYTE), 
	"DN_SOC_COC" VARCHAR2(1 BYTE), 
	"DN_SHIPMENT_TERM" VARCHAR2(4 BYTE), 
	"DN_SHIPMENT_TYP" VARCHAR2(3 BYTE), 
	"MIDSTREAM_HANDLING_CODE" VARCHAR2(2 BYTE), 
	"LOAD_CONDITION" VARCHAR2(1 BYTE), 
	"RESTOW_STATUS" VARCHAR2(2 BYTE), 
	"STOWAGE_POSITION" VARCHAR2(7 BYTE), 
	"CONTAINER_GROSS_WEIGHT" VARCHAR2(8 BYTE), 
	"DAMAGED" VARCHAR2(1 BYTE), 
	"VOID_SLOT" VARCHAR2(6 BYTE), 
	"FK_SLOT_OPERATOR" VARCHAR2(4 BYTE), 
	"FK_CONTAINER_OPERATOR" VARCHAR2(4 BYTE), 
	"DN_SPECIAL_HNDL" VARCHAR2(3 BYTE), 
	"SEAL_NO" VARCHAR2(20 BYTE), 
	"FK_SPECIAL_CARGO" VARCHAR2(3 BYTE), 
	"PUBLIC_REMARK" VARCHAR2(2000 BYTE), 
	"RECORD_CHANGE_USER" VARCHAR2(10 BYTE), 
	"RECORD_CHANGE_DATE" TIMESTAMP (6), 
	"SEQ_NO" VARCHAR2(5 BYTE), 
	"OPN_STATUS" VARCHAR2(3 BYTE), 
	"ACTIVITY_DATE_TIME" VARCHAR2(16 BYTE)
   ) ;
 
